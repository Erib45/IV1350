package DTO;

import java.util.ArrayList;

import model.Item;

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
