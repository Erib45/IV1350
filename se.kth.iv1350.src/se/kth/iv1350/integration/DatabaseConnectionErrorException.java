package se.kth.iv1350.integration;

/**
 * Thrown when database connection fails.
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class DatabaseConnectionErrorException extends Exception {
	public DatabaseConnectionErrorException(String message) {
		super(message);
	}
}
