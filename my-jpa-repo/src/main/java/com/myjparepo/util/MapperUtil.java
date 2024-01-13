package main.java.com.myjparepo.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import main.java.com.myjparepo.annotation.Id;
import main.java.com.myjparepo.exception.JpaRepositoryException;
import main.java.com.myjparepo.query.model.ObjectEntityModel;

public class MapperUtil {

	private static final String DOUBLE = "double";
	private static Map<String, String> typeMap = null;

	private MapperUtil() {

	}

	static {
		typeMap = new HashMap<>();
		typeMap.put("int", "int");
		typeMap.put("Integer", "int");
		typeMap.put("String", "varchar");
		typeMap.put(DOUBLE, DOUBLE);
		typeMap.put("Double", DOUBLE);
	}

	public static String getColumnType(String objectType) {
		return typeMap.get(objectType);
	}

	public static <T> String toColumnValue(Class<T> clazz, Object object) {
		if (Objects.isNull(object)) {
			return null;
		} else if (String.class.equals(clazz) || Character.class.equals(clazz) || char.class.equals(clazz)) {
			return "'" + object + "'";
		} else
			return String.valueOf(object);
	}

	public static <T> T toObject(ResultSet resultSet, T t) throws NoSuchFieldException, SecurityException, SQLException,
			JpaRepositoryException, IllegalArgumentException, IllegalAccessException {
		Map<String, ObjectEntityModel> fieldColumnMap = getFieldColumnMap(t);
		for (ObjectEntityModel objectEntityModel : fieldColumnMap.values()) {
			Object obj = resultSet.getObject(objectEntityModel.getColumnName());
			Field field = t.getClass().getDeclaredField(objectEntityModel.getObjectName());
			setField(t, obj, field);
		}
		return t;
	}

	public static <T> Map<String, ObjectEntityModel> getFieldColumnMap(T t) throws JpaRepositoryException {
		Map<String, ObjectEntityModel> map = new HashMap<>();
		for (Field field : t.getClass().getDeclaredFields()) {
			boolean isPk = false;
			if (field.isAnnotationPresent(Id.class)) {
				isPk = true;
			}
			try {
				Object fieldValue = t.getClass().getDeclaredMethod(getter(field)).invoke(t);
				map.put(field.getName(), new ObjectEntityModel(field.getName(), fieldValue,
						StringUtil.toPascalCase(field.getName()), toColumnValue(field.getType(), fieldValue), isPk));
			} catch (Exception e) {
				throw new JpaRepositoryException(
						"No getter found for " + field.getName() + " in " + t.getClass().getSimpleName());
			}
		}
		return map;
	}

	public static String getter(Field field) {
		return "get" + StringUtil.toUppercase(field.getName(), 0, 1);
	}

	public static String setter(Field field) {
		return "set" + StringUtil.toUppercase(field.getName(), 0, 1);
	}

	public static <T> void setField(T t, Object obj, Field field) throws IllegalAccessException {
		field.setAccessible(true);
		field.set(t, obj);
	}

}
