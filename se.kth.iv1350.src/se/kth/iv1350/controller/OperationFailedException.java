package se.kth.iv1350.controller;

/**
 * Thrown when an operation fails.
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class OperationFailedException extends Exception{
	public OperationFailedException(String message, Exception e) {
		super(message, e);
	}
}
