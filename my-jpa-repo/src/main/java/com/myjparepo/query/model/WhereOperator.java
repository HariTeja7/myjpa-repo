package main.java.com.myjparepo.query.model;

public enum WhereOperator {

	AND("AND"),

	OR("OR");

	private String value;

	private WhereOperator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
