package main.java.com.myjparepo.query.model;

public class ObjectEntityModel {

	private String objectName;

	private Object objectValue;

	private String columnName;

	private String columnValue;

	private boolean isPk;

	public ObjectEntityModel(String objectName, Object objectValue, String columnName, String columnValue,
			boolean isPk) {
		this.objectName = objectName;
		this.objectValue = objectValue;
		this.columnName = columnName;
		this.columnValue = columnValue;
		this.isPk = isPk;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Object getObjectValue() {
		return objectValue;
	}

	public void setObjectValue(Object objectValue) {
		this.objectValue = objectValue;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public boolean isPk() {
		return isPk;
	}

	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}

}
