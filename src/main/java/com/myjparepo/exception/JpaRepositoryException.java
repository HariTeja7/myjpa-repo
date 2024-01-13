package main.java.com.myjparepo.exception;

public class JpaRepositoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2355633761647377518L;

	public JpaRepositoryException(String message) {
		super(message);
	}

	public JpaRepositoryException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
