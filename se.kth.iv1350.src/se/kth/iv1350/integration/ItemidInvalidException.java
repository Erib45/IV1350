package se.kth.iv1350.integration;
/**
 * Thrown when item ID is not found in the database.
 * @author Erik Eriksson
 * @author Rolf Dahlberg
 * @author Vanshu (OneShoe) Dutta
 */
public class ItemidInvalidException extends Exception {

	public ItemidInvalidException(String message) {
		super(message);
	}	
}
