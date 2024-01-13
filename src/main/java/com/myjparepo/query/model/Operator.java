package main.java.com.myjparepo.query.model;

public enum Operator {

	EQUAL_TO("="),

	GREATER_THAN(">"),

	GREATER_THAN_OR_EQUAL_TO(">="),

	LESS_THAN("<"),

	LESS_THAN_OR_EQUAL_TO("<="),

	BETWEEN("BETWEEN"),

	LIKE("LIKE"),

	IN("IN");

	private String operation;

	Operator(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

}
