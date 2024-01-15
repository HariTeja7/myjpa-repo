package main.java.com.myjparepo.query.model;

/**
 * The Enum WhereOperator.
 */
public enum WhereOperator {

	/** The and. */
	AND("AND"),

	/** The or. */
	OR("OR");

	/** The value. */
	private String value;

	/**
	 * Instantiates a new where operator.
	 *
	 * @param value the value
	 */
	private WhereOperator(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
