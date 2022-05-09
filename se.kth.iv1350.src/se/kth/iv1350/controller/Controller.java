package se.kth.iv1350.controller;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.integration.DbHandler;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.integration.Register;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;
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
    }

    /**
     * Add an item to a sale.
     * @param itemID An integer identifying an item.
     * @param quantity Quantity of items with the same id added.
     * @return  <code>String</code> containing the running price of the sale and
     * name and description of the item that was added.
     */
    public Sale addItem(int itemID, int quantity){
        ItemDTO item = dbHandler.getItemInfo(itemID);
        if(item == null)
            return null;
        sale.addItem(item, quantity);
        return sale;
    }
    
   /** 
    * @param itemID a integer identifying an item.
    * @return <code>String</code> containing the running price of the sale and
    * name and description of the item that was added.
    */
   public Sale addItem(int itemID){
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
    //Return null should be replaced with an exception
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
}
