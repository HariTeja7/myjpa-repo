package main.java.com.myjparepo.query.model;

public class TableColumn {

	private StringBuilder columnBuilder;

	private TableColumn() {
		columnBuilder = new StringBuilder();
	}

	public static TableColumn builder() {
		return new TableColumn();
	}

	public TableColumn name(String name) {
		if (!name.isBlank() && !name.isEmpty()) {
			columnBuilder.append(name).append(" ");
		}
		return this;
	}

	public TableColumn dateType(String dateType) {
		if (!dateType.isBlank() && !dateType.isEmpty()) {
			columnBuilder.append(dateType).append(" ");
		}
		return this;
	}

	public TableColumn dateType(String dateType, int size) {
		if (!dateType.isBlank() && !dateType.isEmpty() && size != 0) {
			columnBuilder.append(dateType).append("(").append(size).append(")").append(" ");
		}
		return this;
	}

	public TableColumn isUnique() {
		columnBuilder.append("UNIQUE").append(" ");
		return this;
	}

	public TableColumn isPrimaryKey() {
		columnBuilder.append("PRIMARY KEY").append(" ");
		return this;
	}

	public TableColumn autoIncreament() {
		columnBuilder.append("AUTO_INCREMENT").append(" ");
		return this;
	}

	public TableColumn isForiegnKey() {
		columnBuilder.append("FORIEGN KEY").append(" ");
		return this;
	}

	public TableColumn isNotNull() {
		columnBuilder.append("NOT NULL").append(" ");
		return this;
	}

	public TableColumn defaultValue(String value) {
		if (!value.isBlank() && !value.isEmpty()) {
			columnBuilder.append("DEFAULT").append(" ").append(value).append(" ");
		}
		return this;
	}

	public TableColumn build() {
		return this;
	}

	@Override
	public String toString() {
		return columnBuilder.toString();
	}

}
