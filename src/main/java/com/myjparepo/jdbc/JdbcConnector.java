package main.java.com.myjparepo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.myjparepo.config.PropertyConfig;
import main.java.com.myjparepo.contant.RepositoryContants;

public class JdbcConnector {

	private Connection connection;

	private Statement statement;

	public JdbcConnector() {
		connection = null;
		statement = null;
	}

	private Connection getConnection() throws SQLException {
		try {
			String database = PropertyConfig.getApplicationProperty(RepositoryContants.DATABASE)
					+ RepositoryContants.CONNECTION_PARAMS;
			String username = PropertyConfig.getApplicationProperty(RepositoryContants.USERNAME);
			String password = PropertyConfig.getApplicationProperty(RepositoryContants.PASSWORD);
			return DriverManager.getConnection(database, username, password);
		} catch (Exception e) {
			throw new SQLException("Unable to connect to database, Exception : " + e);
		}
	}

	public void start() throws SQLException {
		connection = getConnection();
		statement = connection.createStatement();
	}

	public ResultSet getResultSet(String query) throws SQLException {
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		return statement.getGeneratedKeys();
	}

	public void close() throws SQLException {
		statement.close();
		connection.close();
	}

	public void execute(String query) throws SQLException {
		statement.execute(query);
	}

	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}

}
