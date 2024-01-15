package main.java.com.myjparepo.query.model;

/**
 * The Class Alias.
 */
public class Alias {

	/** The value. */
	private String value;

	/** The alias value. */
	private String aliasValue;

	/**
	 * Instantiates a new alias.
	 *
	 * @param value the value
	 * @param aliasValue the alias value
	 */
	public Alias(String value, String aliasValue) {
		this.value = value;
		this.aliasValue = aliasValue;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the alias value.
	 *
	 * @return the alias value
	 */
	public String getAliasValue() {
		return aliasValue;
	}

	/**
	 * Sets the alias value.
	 *
	 * @param aliasValue the new alias value
	 */
	public void setAliasValue(String aliasValue) {
		this.aliasValue = aliasValue;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return value + " AS " + aliasValue;
	}

}
