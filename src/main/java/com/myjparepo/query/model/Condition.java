package main.java.com.myjparepo.query.model;

public class Condition {

	private StringBuilder conditionBuilder;

	private Condition() {
		conditionBuilder = new StringBuilder();
	}

	public static Condition builder() {
		return new Condition();
	}

	public Condition not() {
		conditionBuilder.append("NOT").append(" ");
		return this;
	}

	public Condition param(String param) {
		conditionBuilder.append(param).append(" ");
		return this;
	}

	public Condition operator(Operator operator) {
		conditionBuilder.append(operator.getOperation()).append(" ");
		return this;
	}

	public Condition value(String value) {
		conditionBuilder.append(value).append(" ");
		return this;
	}

	public Condition value(Iterable<String> value) {
		conditionBuilder.append(value).append(" ");
		return this;
	}

	public String build() {
		return conditionBuilder.toString();
	}

}
