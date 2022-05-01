package controller;

import integration.DbHandler;
import integration.Register;
import integration.Printer;
import DTO.ItemDTO;
import model.Receipt;
import model.Sale;

public class Controller {

    private Register register;
    private DbHandler dbHandler;
    private Printer printer;
    private Sale sale;

    public Controller(Register register){
        this.register = register;
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
     * @param itemID a integer identifying an item.
     * @param quantity Amount of items with the same id added.
     * @return  <code>String</code> containing the running price of the sale and
     * name and description of the item that was added.
     */
    //check item?
    public String addItem(int itemID, int quantity){
        ItemDTO item = dbHandler.getItemInfo(itemID);
        sale.addItem(item, quantity);
        return "Total: "+sale.getTotal()+
                "Item"+item.getName()+"\n"+
                item.getDescription();
    }
    
   /** 
    * @override 
    * @param itemID a integer identifying an item.
    * @return <code>String</code> containing the running price of the sale and
    * name and description of the item that was added.
    */
  //check item?
    public String addItem(int itemID){
        ItemDTO item = dbHandler.getItemInfo(itemID);
        sale.addItem(item, 1);
        return "Total: "+sale.getTotal()+
                "Item"+item.getName()+"\n"+
                item.getDescription();
    }

    /**
     * Return new total price of sale with the discount
     * that the sale is eligible for applied.
     * @param customerID Integer identifying a customer.
     * @return <code>float</code> containing new total price of sale
     * with discount applied.
     */
    public float applyDiscount(int customerID){
    	sale.applyDiscount(dbHandler.getDiscount(customerID, sale.getSale()));
        return sale.getTotal();
    }

    public void endSale(){
    	
    }

    public Receipt enterPayment(int amount){
    	Receipt receipt = sale.getReceipt(amount);
    	if (receipt.getTotal() > amount) {
    		System.out.println("More mani plozx");
    		return null;
    	}
    	else {
    	register.updateBalance(receipt.getTotal());
    	printer.printReceipt(receipt);
        return receipt;
    	}
    }
}
