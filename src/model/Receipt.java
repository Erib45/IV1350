package model;

import java.util.ArrayList;

public class Receipt {
    private final String  timeOfSale;
    private final float total, amountPaid, change, totalTax;
    private final ArrayList<Item> listOfItem;
    /**
     * Class constructor
     * @param sale The current sale
     * @param amountPaid The amount paid 
     * @param timeOfSale The time of the sale
     * @param totalTax Amount of tax
     */
    Receipt(Sale sale, int amountPaid, String timeOfSale, float totalTax){
        this.timeOfSale = timeOfSale;
        total = sale.getTotal();
        listOfItem = sale.getSale().getItemsInSale();
        this.totalTax = totalTax;
        this.amountPaid = amountPaid;
        change = amountPaid - total;
    }
    
    public String getTimeOfSale() {
    	return this.timeOfSale;
    }
    public float getTotal() {
    	return this.total;
    }
    public float getAmountPaid() {
    	return this.amountPaid;
    }
    public float getChange() {
    	return this.change;
 }
    public float getTotalTax() {
    	return this.totalTax;
 }
    public ArrayList<Item> getItemsInSale() {
    	return this.listOfItem;
 }

}
