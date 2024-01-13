package main.java.com.myjparepo.query.builder;

import main.java.com.myjparepo.query.model.Alias;
import main.java.com.myjparepo.query.model.ObjectEntityModel;
import main.java.com.myjparepo.query.model.TableColumn;
import main.java.com.myjparepo.query.model.WhereOperator;

public interface QueryBuilder {

	public QueryBuilder build();

	public QueryBuilder createTable(String tableName);

	public QueryBuilder ifNotExists();

	public QueryBuilder createColumn(TableColumn column);

	public QueryBuilder createColumns(Iterable<TableColumn> columns);

	public QueryBuilder selectAll();

	public QueryBuilder select(String columnName);

	public QueryBuilder select(Iterable<Object> columnNames);

	public QueryBuilder select(Alias columnName);

	public QueryBuilder from(String tableName);

	public QueryBuilder from(Alias alias);

	public QueryBuilder where();

	public QueryBuilder condition(String condition);

	public QueryBuilder whereOperator(WhereOperator whereOperator);

	public QueryBuilder insertColumns(String tableName, Iterable<String> columns);

	public QueryBuilder insertValues(Iterable<Iterable<ObjectEntityModel>> values);

	public QueryBuilder delete();

}
