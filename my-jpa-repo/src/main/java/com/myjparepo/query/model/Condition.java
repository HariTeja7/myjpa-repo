package main.java.com.myjparepo.query.model;

/**
 * The Class Condition.
 */
public class Condition {

	/** The condition builder. */
	private StringBuilder conditionBuilder;

	/**
	 * Instantiates a new condition.
	 */
	private Condition() {
		conditionBuilder = new StringBuilder();
	}

	/**
	 * Builder.
	 *
	 * @return the condition
	 */
	public static Condition builder() {
		return new Condition();
	}

	/**
	 * Not.
	 *
	 * @return the condition
	 */
	public Condition not() {
		conditionBuilder.append("NOT").append(" ");
		return this;
	}

	/**
	 * Param.
	 *
	 * @param param the param
	 * @return the condition
	 */
	public Condition param(String param) {
		conditionBuilder.append(param).append(" ");
		return this;
	}

	/**
	 * Operator.
	 *
	 * @param operator the operator
	 * @return the condition
	 */
	public Condition operator(Operator operator) {
		conditionBuilder.append(operator.getOperation()).append(" ");
		return this;
	}

	/**
	 * Value.
	 *
	 * @param value the value
	 * @return the condition
	 */
	public Condition value(String value) {
		conditionBuilder.append(value).append(" ");
		return this;
	}

	/**
	 * Value.
	 *
	 * @param value the value
	 * @return the condition
	 */
	public Condition value(Iterable<String> value) {
		conditionBuilder.append(value).append(" ");
		return this;
	}

	/**
	 * Builds the.
	 *
	 * @return the string
	 */
	public String build() {
		return conditionBuilder.toString();
	}

}
