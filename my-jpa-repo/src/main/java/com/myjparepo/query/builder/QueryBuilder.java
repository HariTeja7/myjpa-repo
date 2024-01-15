package main.java.com.myjparepo.query.builder;

import main.java.com.myjparepo.query.model.Alias;
import main.java.com.myjparepo.query.model.ObjectEntityModel;
import main.java.com.myjparepo.query.model.TableColumn;
import main.java.com.myjparepo.query.model.WhereOperator;

/**
 * The Interface QueryBuilder.
 */
public interface QueryBuilder {

	/**
	 * Builds the.
	 *
	 * @return the query builder
	 */
	public QueryBuilder build();

	/**
	 * Creates the table.
	 *
	 * @param tableName the table name
	 * @return the query builder
	 */
	public QueryBuilder createTable(String tableName);

	/**
	 * If not exists.
	 *
	 * @return the query builder
	 */
	public QueryBuilder ifNotExists();

	/**
	 * Creates the column.
	 *
	 * @param column the column
	 * @return the query builder
	 */
	public QueryBuilder createColumn(TableColumn column);

	/**
	 * Creates the columns.
	 *
	 * @param columns the columns
	 * @return the query builder
	 */
	public QueryBuilder createColumns(Iterable<TableColumn> columns);

	/**
	 * Select all.
	 *
	 * @return the query builder
	 */
	public QueryBuilder selectAll();

	/**
	 * Select.
	 *
	 * @param columnName the column name
	 * @return the query builder
	 */
	public QueryBuilder select(String columnName);

	/**
	 * Select.
	 *
	 * @param columnNames the column names
	 * @return the query builder
	 */
	public QueryBuilder select(Iterable<Object> columnNames);

	/**
	 * Select.
	 *
	 * @param columnName the column name
	 * @return the query builder
	 */
	public QueryBuilder select(Alias columnName);

	/**
	 * From.
	 *
	 * @param tableName the table name
	 * @return the query builder
	 */
	public QueryBuilder from(String tableName);

	/**
	 * From.
	 *
	 * @param alias the alias
	 * @return the query builder
	 */
	public QueryBuilder from(Alias alias);

	/**
	 * Where.
	 *
	 * @return the query builder
	 */
	public QueryBuilder where();

	/**
	 * Condition.
	 *
	 * @param condition the condition
	 * @return the query builder
	 */
	public QueryBuilder condition(String condition);

	/**
	 * Where operator.
	 *
	 * @param whereOperator the where operator
	 * @return the query builder
	 */
	public QueryBuilder whereOperator(WhereOperator whereOperator);

	/**
	 * Insert columns.
	 *
	 * @param tableName the table name
	 * @param columns the columns
	 * @return the query builder
	 */
	public QueryBuilder insertColumns(String tableName, Iterable<String> columns);

	/**
	 * Insert values.
	 *
	 * @param values the values
	 * @return the query builder
	 */
	public QueryBuilder insertValues(Iterable<Iterable<ObjectEntityModel>> values);

	/**
	 * Delete.
	 *
	 * @return the query builder
	 */
	public QueryBuilder delete();

}
