package main.java.com.myjparepo.query.model;

public class Alias {

	private String value;

	private String aliasValue;

	public Alias(String value, String aliasValue) {
		this.value = value;
		this.aliasValue = aliasValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAliasValue() {
		return aliasValue;
	}

	public void setAliasValue(String aliasValue) {
		this.aliasValue = aliasValue;
	}

	@Override
	public String toString() {
		return value + " AS " + aliasValue;
	}

}
