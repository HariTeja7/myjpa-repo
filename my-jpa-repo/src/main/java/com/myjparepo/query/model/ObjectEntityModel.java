package main.java.com.myjparepo.query.model;

/**
 * The Class ObjectEntityModel.
 */
public class ObjectEntityModel {

	/** The object name. */
	private String objectName;

	/** The object value. */
	private Object objectValue;

	/** The column name. */
	private String columnName;

	/** The column value. */
	private String columnValue;

	/** The is pk. */
	private boolean isPk;

	/**
	 * Instantiates a new object entity model.
	 *
	 * @param objectName the object name
	 * @param objectValue the object value
	 * @param columnName the column name
	 * @param columnValue the column value
	 * @param isPk the is pk
	 */
	public ObjectEntityModel(String objectName, Object objectValue, String columnName, String columnValue,
			boolean isPk) {
		this.objectName = objectName;
		this.objectValue = objectValue;
		this.columnName = columnName;
		this.columnValue = columnValue;
		this.isPk = isPk;
	}

	/**
	 * Gets the object name.
	 *
	 * @return the object name
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * Sets the object name.
	 *
	 * @param objectName the new object name
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * Gets the object value.
	 *
	 * @return the object value
	 */
	public Object getObjectValue() {
		return objectValue;
	}

	/**
	 * Sets the object value.
	 *
	 * @param objectValue the new object value
	 */
	public void setObjectValue(Object objectValue) {
		this.objectValue = objectValue;
	}

	/**
	 * Gets the column name.
	 *
	 * @return the column name
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Sets the column name.
	 *
	 * @param columnName the new column name
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * Gets the column value.
	 *
	 * @return the column value
	 */
	public String getColumnValue() {
		return columnValue;
	}

	/**
	 * Sets the column value.
	 *
	 * @param columnValue the new column value
	 */
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	/**
	 * Checks if is pk.
	 *
	 * @return true, if is pk
	 */
	public boolean isPk() {
		return isPk;
	}

	/**
	 * Sets the pk.
	 *
	 * @param isPk the new pk
	 */
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}

}
