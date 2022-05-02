package model;

import integration.DbHandler;
import DTO.ItemDTO;
import DTO.SaleDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Sale {

    private float total;
    private String timeOfSale;
    private DbHandler dbHandler;
    private ArrayList<Item> listOfItems = new ArrayList<>();

    public Sale(DbHandler dbHandler){
        this.dbHandler = dbHandler;
    }

    /**
     * Add item to sale and updates sale total.
     * @param itemDTO DTO containing information about the item.
     * @param quantity Item quantity
     */
    public void addItem(ItemDTO itemDTO, int quantity){
    	Item newItem = new Item(itemDTO, quantity);
    	if(!listOfItems.contains(newItem)) {
    		listOfItems.add(newItem);
    	}
    	else {
    		listOfItems.get(listOfItems.indexOf(newItem)).updateQuantity(quantity);
    	}
        updateTotal(itemDTO.getPrice() * quantity) ;
    }
    
    private void updateTotal(float amount){
        total += amount;
    }

    private void setTimeOfSale(){
       DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       timeOfSale = LocalDateTime.now().format(dateTimeFormatter);

    }
    
    /**
     * Applies discount percentage on the total cost
     * @param costumerID Identifies customer
     */
    public void applyDiscount(int customerID) {
    	total *= dbHandler.getDiscount(customerID, this.getSale());
    }

    /**
     * Set a time of sale and log sale
     */
    public void endSale() {
    	setTimeOfSale();
        dbHandler.logSale(getSale());
    }
    
    /**
     * Creates a receipt
     * @param amountPaid The amount paid
     * @return <code>Receipt</code> Receipt contains information about the purchase
     */
    public Receipt createReceipt(int amountPaid){
        float tax = 0;
        for(int i = 0; i < listOfItems.size(); i++) {
        	tax += (listOfItems.get(i).getItem().getPrice() * (listOfItems.get(i).getQuantity()*listOfItems.get(i).getItem().getTax()));
        }
        return new Receipt(this, amountPaid, timeOfSale, tax);
    }
    
    public SaleDTO getSale(){
        return new SaleDTO(total, listOfItems);
    }
    

    public float getTotal() {
        return total;
    }
    

    
    


    
}
