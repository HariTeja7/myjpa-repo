package main.java.com.myjparepo.query.model;

/**
 * The Class TableColumn.
 */
public class TableColumn {

	/** The column builder. */
	private StringBuilder columnBuilder;

	/**
	 * Instantiates a new table column.
	 */
	private TableColumn() {
		columnBuilder = new StringBuilder();
	}

	/**
	 * Builder.
	 *
	 * @return the table column
	 */
	public static TableColumn builder() {
		return new TableColumn();
	}

	/**
	 * Name.
	 *
	 * @param name the name
	 * @return the table column
	 */
	public TableColumn name(String name) {
		if (!name.isBlank() && !name.isEmpty()) {
			columnBuilder.append(name).append(" ");
		}
		return this;
	}

	/**
	 * Date type.
	 *
	 * @param dateType the date type
	 * @return the table column
	 */
	public TableColumn dateType(String dateType) {
		if (!dateType.isBlank() && !dateType.isEmpty()) {
			columnBuilder.append(dateType).append(" ");
		}
		return this;
	}

	/**
	 * Date type.
	 *
	 * @param dateType the date type
	 * @param size the size
	 * @return the table column
	 */
	public TableColumn dateType(String dateType, int size) {
		if (!dateType.isBlank() && !dateType.isEmpty() && size != 0) {
			columnBuilder.append(dateType).append("(").append(size).append(")").append(" ");
		}
		return this;
	}

	/**
	 * Checks if is unique.
	 *
	 * @return the table column
	 */
	public TableColumn isUnique() {
		columnBuilder.append("UNIQUE").append(" ");
		return this;
	}

	/**
	 * Checks if is primary key.
	 *
	 * @return the table column
	 */
	public TableColumn isPrimaryKey() {
		columnBuilder.append("PRIMARY KEY").append(" ");
		return this;
	}

	/**
	 * Auto increament.
	 *
	 * @return the table column
	 */
	public TableColumn autoIncreament() {
		columnBuilder.append("AUTO_INCREMENT").append(" ");
		return this;
	}

	/**
	 * Checks if is foriegn key.
	 *
	 * @return the table column
	 */
	public TableColumn isForiegnKey() {
		columnBuilder.append("FORIEGN KEY").append(" ");
		return this;
	}

	/**
	 * Checks if is not null.
	 *
	 * @return the table column
	 */
	public TableColumn isNotNull() {
		columnBuilder.append("NOT NULL").append(" ");
		return this;
	}

	/**
	 * Default value.
	 *
	 * @param value the value
	 * @return the table column
	 */
	public TableColumn defaultValue(String value) {
		if (!value.isBlank() && !value.isEmpty()) {
			columnBuilder.append("DEFAULT").append(" ").append(value).append(" ");
		}
		return this;
	}

	/**
	 * Builds the.
	 *
	 * @return the table column
	 */
	public TableColumn build() {
		return this;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return columnBuilder.toString();
	}

}
