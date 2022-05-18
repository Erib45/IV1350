package se.kth.iv1350.integration;
/**
 * Thrown when item ID is not found in the database.
 * @author Erik Eriksson
 * @author Rolf Dahlberg
 * @author Vanshu (OneShoe) Dutta
 */
public class ItemIDInvalidException extends Exception {

	public ItemIDInvalidException(String message) {
		super(message);
	}	
}
