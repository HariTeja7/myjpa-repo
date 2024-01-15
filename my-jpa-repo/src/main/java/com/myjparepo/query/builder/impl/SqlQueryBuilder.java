package main.java.com.myjparepo.query.builder.impl;

import main.java.com.myjparepo.query.builder.QueryBuilder;
import main.java.com.myjparepo.query.model.Alias;
import main.java.com.myjparepo.query.model.ObjectEntityModel;
import main.java.com.myjparepo.query.model.TableColumn;
import main.java.com.myjparepo.query.model.WhereOperator;

/**
 * The Class SqlQueryBuilder.
 */
public class SqlQueryBuilder implements QueryBuilder {

	private StringBuilder queryBuilder;

	private static final String CREATE_CONSTANT = "CREATE TABLE";

	private static final String SELECT_CONTANT = "SELECT";

	private static final String FROM_CONTANT = "FROM";

	private static final String WHERE_CONTANT = "WHERE";

	private static final String INSERT_CONSTANT = "INSERT INTO";

	private static final String VALUE_CONTANT = "VALUES";

	private static final String DELETE_CONSTANT = "DELETE";

	private SqlQueryBuilder() {
		queryBuilder = new StringBuilder();
	}

	public static QueryBuilder builder() {
		return new SqlQueryBuilder();
	}

	@Override
	public QueryBuilder build() {
		return this;
	}

	@Override
	public QueryBuilder createTable(String tableName) {
		queryBuilder.append(CREATE_CONSTANT).append(" ").append(tableName).append(" ").append("();");
		return this;
	}

	@Override
	public QueryBuilder ifNotExists() {
		queryBuilder.insert(queryBuilder.indexOf(CREATE_CONSTANT) + CREATE_CONSTANT.length(), " IF NOT EXISTS ");
		return this;
	}

	@Override
	public QueryBuilder createColumn(TableColumn column) {
		String comma = "";
		if (queryBuilder.lastIndexOf(")") - queryBuilder.indexOf("(") > 1) {
			comma = ",";
		}
		queryBuilder.insert(queryBuilder.lastIndexOf(")"), comma + column);
		return this;
	}

	@Override
	public QueryBuilder createColumns(Iterable<TableColumn> columns) {
		for (TableColumn column : columns) {
			createColumn(column);
		}
		return this;
	}

	@Override
	public QueryBuilder selectAll() {
		queryBuilder.append(SELECT_CONTANT).append(" ").append("*").append(" ");
		return this;
	}

	@Override
	public QueryBuilder select(String columnName) {
		int insertIndex = queryBuilder.indexOf(FROM_CONTANT);
		String comma = "";
		if (insertIndex < 0) {
			insertIndex = queryBuilder.length();
		}
		queryBuilder.insert(insertIndex, comma + columnName);
		return this;
	}

	@Override
	public QueryBuilder select(Alias columnName) {
		int insertIndex = queryBuilder.indexOf(FROM_CONTANT);
		String comma = "";
		if (insertIndex < 0) {
			insertIndex = queryBuilder.length();
		}
		queryBuilder.insert(insertIndex, comma + columnName);
		return this;
	}

	@Override
	public QueryBuilder select(Iterable<Object> columnNames) {
		for (Object columnName : columnNames) {
			if (columnName instanceof Alias column) {
				select(column);
			} else {
				select((String) columnName);
			}
		}
		return this;
	}

	@Override
	public QueryBuilder from(String tableName) {
		queryBuilder.append(FROM_CONTANT).append(" ").append(tableName).append(" ");
		return this;
	}

	@Override
	public QueryBuilder from(Alias tableName) {
		queryBuilder.append(FROM_CONTANT).append(" ").append(tableName).append(" ");
		return this;
	}

	@Override
	public QueryBuilder where() {
		queryBuilder.append(WHERE_CONTANT).append(" ");
		return this;
	}

	@Override
	public QueryBuilder whereOperator(WhereOperator whereOperator) {
		queryBuilder.append(whereOperator.getValue()).append(" ");
		return this;
	}

	@Override
	public QueryBuilder condition(String whereCondition) {
		queryBuilder.append(whereCondition).append(" ");
		return this;
	}

	@Override
	public QueryBuilder insertColumns(String tableName, Iterable<String> columns) {
		StringBuilder columnName = new StringBuilder("(");
		for (String col : columns) {
			if (columnName.length() == 1) {
				columnName.append(col);
			} else {
				columnName.append(",").append(col);
			}
		}
		columnName.append(")");
		queryBuilder.append(INSERT_CONSTANT).append(" ").append(tableName).append(columnName.toString()).append(" ");
		return this;
	}

	@Override
	public QueryBuilder insertValues(Iterable<Iterable<ObjectEntityModel>> values) {
		StringBuilder valueRecords = new StringBuilder("");
		for (Iterable<ObjectEntityModel> value : values) {
			StringBuilder columnValues = new StringBuilder("(");
			for (ObjectEntityModel val : value) {
				if (columnValues.length() == 1) {
					columnValues.append(val.getColumnValue());
				} else {
					columnValues.append(",").append(val.getColumnValue());
				}
			}
			columnValues.append(")");
			if (valueRecords.length() == 0) {
				valueRecords.append(columnValues.toString());
			} else {
				valueRecords.append("," + columnValues.toString());
			}
		}
		queryBuilder.append(VALUE_CONTANT).append(" ").append(valueRecords.toString());
		return this;
	}

	@Override
	public QueryBuilder delete() {
		queryBuilder.append(DELETE_CONSTANT).append(" ");
		return this;
	}

	@Override
	public String toString() {
		return queryBuilder.toString();
	}

}
