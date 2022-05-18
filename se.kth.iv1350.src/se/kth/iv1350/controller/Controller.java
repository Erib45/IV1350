package se.kth.iv1350.controller;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.integration.DatabaseConnectionErrorException;
import se.kth.iv1350.integration.DbHandler;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.Register;
import se.kth.iv1350.integration.ItemIDInvalidException;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.TotalRevenueObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller is the central class that communicates with many other classes to retrieve values for the view 
 * or change states in different objects by adding items or applying a discount
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class Controller {

    private Register register;
    private DbHandler dbHandler;
    private Printer printer;
    private Sale sale;
    private List<TotalRevenueObserver> TotalRevenueObservers = new ArrayList<>();

    /**
     * Class constructor
     */
    public Controller(){
        register = new Register();
        dbHandler = new DbHandler();
        printer = new Printer();
    }

    /**
     * Start a new sale.
     */
    public void startSale(){
        sale = new Sale(dbHandler);
        sale.addTotalRevenueObservers(TotalRevenueObservers);
    }

    /**
     * Add an item to a sale.
     * @param itemID An integer identifying an item.
     * @param quantity Quantity of items with the same id added.
     * @return  <code>String</code> containing the running price of the sale and
     * name and description of the item that was added.
     * @throws ItemIDInvalidException If itemID could not be found in database.
     * @throws OperationFailedException If item could not be added.
     */
    public Sale addItem(int itemID, int quantity) throws ItemIDInvalidException, OperationFailedException{
    	try {
    	ItemDTO itemDTO = dbHandler.getItemInfo(itemID);
        sale.addItem(itemDTO, quantity);
        return sale;
    	}catch(DatabaseConnectionErrorException e) {
     	   throw new OperationFailedException("Item could not be added", e);
        }
    }
    
   /** 
    * @param itemID a integer identifying an item.
    * @return <code>String</code> containing the running price of the sale and
    * name and description of the item that was added.
    * @throws ItemIDInvalidException If itemID could not be found in database.
    * @throws OperationFailedException If item could not be added.
    */
   public Sale addItem(int itemID) throws ItemIDInvalidException, OperationFailedException{
	   return addItem(itemID, 1);
   }

    /**
     * Return new total price of sale with the discount
     * that the sale is eligible for applied.
     * @param customerID Integer identifying a customer.
     * @return <code>float</code> containing new total price of sale
     * with discount applied.
     */
    public float applyDiscount(int customerID){
    	sale.applyDiscount(customerID);
        return sale.getTotal();
    }

    /**
     * Confirm payment and create receipt.
     * @param amount Amount paid.
     * @return <code>Receipt</code> containing information about the purchase.
     */
    public Receipt enterPayment(int amount){
    	sale.endSale();
    	Receipt receipt = sale.createReceipt(amount);
    	if (receipt.getTotal() > amount) {
    		return null;
    	}
    	else {
    	register.updateBalance(receipt.getTotal());
    	printer.printReceipt(receipt);
        return receipt;
    	}
    }

    /**
     * Adds a observer to the list of observers in controller
     * @param totalRevenueObserver a observer observing the total revenue since the start of the program
     */
    public void addTotalRevenueObserver(TotalRevenueObserver totalRevenueObserver)
    {
        TotalRevenueObservers.add(totalRevenueObserver);
    }
}
