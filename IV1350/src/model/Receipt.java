package model;

import DTO.ItemDTO;
import DTO.ItemInSale;
import DTO.SaleDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Receipt {
    private final String  timeOfSale;
    private final float total, amountPaid, change, totalTax;
    private final ArrayList<ItemInSale> itemsInSale;
    
    Receipt(Sale sale, int amount, String timeOfSale, float tax){
        this.timeOfSale = timeOfSale;
        total = sale.getTotal();
        itemsInSale = sale.getSale().getItemsInSale();
        totalTax = tax;
        amountPaid = amount;
        change = amount - total;
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
    public ArrayList<ItemInSale> getItemsInSale() {
    	return this.itemsInSale;
 }

}
