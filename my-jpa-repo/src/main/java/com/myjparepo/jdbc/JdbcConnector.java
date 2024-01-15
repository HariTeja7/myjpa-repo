package main.java.com.myjparepo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.myjparepo.contant.RepositoryContants;
import main.java.com.myjparepo.util.PropertyUtil;

/**
 * The JdbcConnector class is used to access connection and statement.
 */
public class JdbcConnector {

	/** The JDBC connection. */
	private Connection connection;

	/** The statement. */
	private Statement statement;

	/**
	 * Instantiates a new jdbc connector.
	 */
	public JdbcConnector() {
		connection = null;
		statement = null;
	}

	/**
	 * creates the JDBC connection with provided properties.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	private Connection getConnection() throws SQLException {
		try {
			String database = PropertyUtil.getApplicationProperty(RepositoryContants.DATABASE)
					+ RepositoryContants.CONNECTION_PARAMS;
			String username = PropertyUtil.getApplicationProperty(RepositoryContants.USERNAME);
			String password = PropertyUtil.getApplicationProperty(RepositoryContants.PASSWORD);
			return DriverManager.getConnection(database, username, password);
		} catch (Exception e) {
			throw new SQLException("Unable to connect to database, Exception : " + e);
		}
	}

	/**
	 * Start method creates connection and statement.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void start() throws SQLException {
		connection = getConnection();
		statement = connection.createStatement();
	}

	/**
	 * Gets the result set.
	 *
	 * @param query the query
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */
	public ResultSet getResultSet(String query) throws SQLException {
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		return statement.getGeneratedKeys();
	}

	/**
	 * Close the statement and connection.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void close() throws SQLException {
		statement.close();
		connection.close();
	}

	/**
	 * Executes the query.
	 *
	 * @param query the query
	 * @throws SQLException the SQL exception
	 */
	public void execute(String query) throws SQLException {
		statement.execute(query);
	}

	/**
	 * Executes query and returns the result set.
	 *
	 * @param query the query
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}

}
