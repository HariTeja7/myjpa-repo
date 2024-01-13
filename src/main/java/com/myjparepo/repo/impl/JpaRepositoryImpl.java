package main.java.com.myjparepo.repo.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import main.java.com.myjparepo.annotation.Column;
import main.java.com.myjparepo.annotation.Entity;
import main.java.com.myjparepo.annotation.Id;
import main.java.com.myjparepo.exception.JpaRepositoryException;
import main.java.com.myjparepo.jdbc.JdbcConnector;
import main.java.com.myjparepo.query.builder.impl.SqlQueryBuilder;
import main.java.com.myjparepo.query.model.Condition;
import main.java.com.myjparepo.query.model.ObjectEntityModel;
import main.java.com.myjparepo.query.model.Operator;
import main.java.com.myjparepo.query.model.TableColumn;
import main.java.com.myjparepo.repo.JpaRepository;
import main.java.com.myjparepo.util.MapperUtil;
import main.java.com.myjparepo.util.StringUtil;

public class JpaRepositoryImpl<T, I> implements JpaRepository<T, I> {

	private JdbcConnector jdbcConnector;

	private String tableName;

	private String primaryKeyFieldName;

	private String getPrimaryKeyMethod;

	private Class<T> clazz;

	private Class<I> pirmaryKeyClazz;

	public JpaRepositoryImpl(Class<T> clazz, Class<I> pirmaryKeyClazz) {
		try {
			jdbcConnector = new JdbcConnector();
			this.clazz = clazz;
			this.pirmaryKeyClazz = pirmaryKeyClazz;
			this.primaryKeyFieldName = pkFieldName(clazz);
			this.getPrimaryKeyMethod = constructPrimaryKeyMethod(this.pirmaryKeyClazz);
			constructTableName();
			createTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String pkFieldName(Class<T> clazz) throws JpaRepositoryException {
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(Id.class)) {
				return field.getName();
			}
		}
		throw new JpaRepositoryException("No Id field found");
	}

	private void constructTableName() {
		if (this.clazz.isAnnotationPresent(Entity.class)) {
			Entity entity = clazz.getAnnotation(Entity.class);
			tableName = entity.name();
			if (tableName.equals("")) {
				tableName = StringUtil.toPascalCase(clazz.getSimpleName());
			}
		} else {
			tableName = StringUtil.toPascalCase(clazz.getSimpleName());
		}
	}

	private String constructPrimaryKeyMethod(Class<I> pirmaryKeyClazz) {
		return "get" + StringUtil.toUppercase(MapperUtil.getColumnType(pirmaryKeyClazz.getSimpleName()), 0, 1);
	}

	private void createTable() {
		Set<TableColumn> columnSet = new HashSet<>();
		for (Field field : clazz.getDeclaredFields()) {
			Column column = new Column() {
				@Override
				public Class<? extends Annotation> annotationType() {
					return null;
				}

				@Override
				public boolean unique() {
					return false;
				}

				@Override
				public int size() {
					return 30;
				}

				@Override
				public boolean nullable() {
					return false;
				}

				@Override
				public String name() {
					return field.getName();
				}
			};
			if (field.isAnnotationPresent(Column.class)) {
				column = field.getAnnotation(Column.class);
			}
			TableColumn tableColumn = TableColumn.builder().name(StringUtil.toPascalCase(field.getName()));
			if (field.getType().equals(String.class)) {
				tableColumn.dateType(MapperUtil.getColumnType(String.class.getSimpleName()), column.size());
			} else {
				tableColumn.dateType(MapperUtil.getColumnType(field.getType().getSimpleName()));
			}
			if (field.isAnnotationPresent(Id.class)) {
				tableColumn.isNotNull();
				tableColumn.autoIncreament();
				tableColumn.isPrimaryKey();
			}
			columnSet.add(tableColumn);
		}
		String query = SqlQueryBuilder.builder().createTable(tableName).ifNotExists().createColumns(columnSet).build()
				.toString();
		try {
			jdbcConnector.start();
			jdbcConnector.execute(query);
			jdbcConnector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public T save(T t) {
		try {
			Map<String, ObjectEntityModel> objectEntityMap = MapperUtil.getFieldColumnMap(t);
			Set<Iterable<ObjectEntityModel>> valuesSet = new HashSet<>();
			valuesSet.add(objectEntityMap.values());
			String query = SqlQueryBuilder.builder().insertColumns(tableName, objectEntityMap.keySet())
					.insertValues(valuesSet).build().toString();
			jdbcConnector.start();
			ResultSet resultSet = jdbcConnector.getResultSet(query);
			Field field = t.getClass().getDeclaredField(primaryKeyFieldName);
			Object object = null;
			while (resultSet.next()) {
				object = resultSet.getClass().getMethod(getPrimaryKeyMethod, int.class).invoke(resultSet, 1);
				break;
			}
			MapperUtil.setField(t, object, field);
			jdbcConnector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Iterable<T> saveAll(Iterable<T> list) {
		try {
			Set<Iterable<ObjectEntityModel>> valuesSet = new HashSet<>();
			Set<String> columnNames = new HashSet<>();
			for (T t : list) {
				Map<String, ObjectEntityModel> objectEntityMap = MapperUtil.getFieldColumnMap(t);
				if (columnNames.isEmpty()) {
					columnNames = objectEntityMap.keySet();
				}
				valuesSet.add(objectEntityMap.values());
			}
			String query = SqlQueryBuilder.builder().insertColumns(tableName, columnNames).insertValues(valuesSet)
					.build().toString();
			jdbcConnector.start();
			ResultSet resultSet = jdbcConnector.getResultSet(query);
			Field field = list.iterator().next().getClass().getDeclaredField(primaryKeyFieldName);
			int pk = 0;
			while (resultSet.next()) {
				pk = (int) resultSet.getClass().getMethod(getPrimaryKeyMethod, int.class).invoke(resultSet, 1);
				break;
			}
			for (T t : list) {
				MapperUtil.setField(t, pk++, field);
			}
			jdbcConnector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Optional<T> findById(I id) {
		Optional<T> result = Optional.empty();
		try {
			T t = null;
			String query = SqlQueryBuilder.builder().selectAll().from(tableName).where()
					.condition(Condition.builder().param(StringUtil.toPascalCase(primaryKeyFieldName))
							.operator(Operator.EQUAL_TO).value(MapperUtil.toColumnValue(pirmaryKeyClazz, id)).build())
					.build().toString();
			jdbcConnector.start();
			ResultSet rs = jdbcConnector.executeQuery(query);
			while (rs.next()) {
				t = MapperUtil.toObject(rs, clazz.getConstructor().newInstance());
				result = Optional.ofNullable(t);
				break;
			}
			jdbcConnector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Iterable<T> findBy(String columnName, Object columnData) {
		List<T> list = new ArrayList<>();
		try {
			Field columnField = clazz.getDeclaredField(columnName);
			String query = SqlQueryBuilder.builder().selectAll().from(tableName).where()
					.condition(Condition.builder().param(StringUtil.toPascalCase(columnField.getName()))
							.operator(Operator.EQUAL_TO)
							.value(MapperUtil.toColumnValue(columnField.getType(), columnData)).build())
					.build().toString();
			jdbcConnector.start();
			ResultSet rs = jdbcConnector.executeQuery(query);
			while (rs.next()) {
				list.add(MapperUtil.toObject(rs, clazz.getConstructor().newInstance()));
			}
			jdbcConnector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<T> findAll() {
		List<T> list = new ArrayList<>();
		try {
			String query = SqlQueryBuilder.builder().selectAll().from(tableName).build().toString();
			jdbcConnector.start();
			ResultSet rs = jdbcConnector.executeQuery(query);
			while (rs.next()) {
				list.add(MapperUtil.toObject(rs, clazz.getConstructor().newInstance()));
			}
			jdbcConnector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void delete(T t) {
		try {
			Object value = clazz.getDeclaredMethod(MapperUtil.getter(clazz.getDeclaredField(primaryKeyFieldName)))
					.invoke(t);
			String query = SqlQueryBuilder.builder().delete().from(tableName).where()
					.condition(Condition.builder().param(primaryKeyFieldName).operator(Operator.EQUAL_TO)
							.value(MapperUtil.toColumnValue(value.getClass(), value)).build())
					.build().toString();
			jdbcConnector.start();
			jdbcConnector.execute(query);
			jdbcConnector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		try {
			String query = SqlQueryBuilder.builder().delete().from(tableName).build().toString();
			jdbcConnector.start();
			jdbcConnector.execute(query);
			jdbcConnector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
