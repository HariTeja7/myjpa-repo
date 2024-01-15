package main.java.com.myjparepo.exception;

/**
 * The JpaRepositoryException is custom exception for repository.
 */
public class JpaRepositoryException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2355633761647377518L;

	/**
	 * Instantiates a new jpa repository exception.
	 *
	 * @param message the message
	 */
	public JpaRepositoryException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new jpa repository exception.
	 *
	 * @param message the message
	 * @param throwable the throwable
	 */
	public JpaRepositoryException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
