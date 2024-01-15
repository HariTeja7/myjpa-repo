package main.java.com.myjparepo.query.model;

/**
 * The Enum Operator.
 */
public enum Operator {

	/** The equal to. */
	EQUAL_TO("="),

	/** The greater than. */
	GREATER_THAN(">"),

	/** The greater than or equal to. */
	GREATER_THAN_OR_EQUAL_TO(">="),

	/** The less than. */
	LESS_THAN("<"),

	/** The less than or equal to. */
	LESS_THAN_OR_EQUAL_TO("<="),

	/** The between. */
	BETWEEN("BETWEEN"),

	/** The like. */
	LIKE("LIKE"),

	/** The in. */
	IN("IN");

	/** The operation. */
	private String operation;

	/**
	 * Instantiates a new operator.
	 *
	 * @param operation the operation
	 */
	Operator(String operation) {
		this.operation = operation;
	}

	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

}
