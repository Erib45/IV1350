package se.kth.iv1350.DTO;

import java.util.ArrayList;
import se.kth.iv1350.model.Item;

/**
 * SaleDTO is a DTO class made to contain information about a specific sale.
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class SaleDTO {

    private final float total;
    private final ArrayList<Item> itemsInSale;

    /**
     * Class Constructor
     * @param total Total cost of the sale.
     * @param itemsInSale <code>ArrayList</code> Containing all the items in the sale.
     */
    public SaleDTO(float total, ArrayList<Item> itemsInSale){
        this.total = total;
        this.itemsInSale = itemsInSale;
    }

    /**
     * @return The amount of items that are in the sale
     */
    public int amountOfItems() {
    	int amount = 0;
    	for(int i = 0; i < itemsInSale.size(); i++) {
    		amount += itemsInSale.get(i).getQuantity();
    	}
    	return amount;
    }
    
    public Item getItemWithID(int id){
    	Item item = null;
    	for(int i = 0; i < itemsInSale.size(); i++) {
    		if(itemsInSale.get(i).getItemDTO().getID() == id)
    			item = itemsInSale.get(i);
    	}
    	return item;
    }
    
    public ArrayList<Item> getItemsInSale() {
        return itemsInSale;
    }

    public Item getItem(int index){
        return itemsInSale.get(index);
    }

    public float getTotal(){
        return total;
    }
}
