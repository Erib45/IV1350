package DTO;

import java.util.ArrayList;

import model.Item;

public class SaleDTO {

    private final float total;
    private final ArrayList<Item> listOfItems;

    /**
     * Class Constructor
     * @param total <code>float</code> The total cost of the sale
     * @param itemsInSale <code>ArrayList</code> Containing all the items that are in the sale
     */
    public SaleDTO(float total, ArrayList<Item> itemsInSale){
        this.total = total;
        this.listOfItems = itemsInSale;
    }

    public float getTotal() {
        return total;
    }

    public Item getItem(int index){
       return listOfItems.get(index);
    }

    public Item getLastItem(){
        return listOfItems.get((listOfItems.size()-1));
    }
    
    /**
     * 
     * @return The amount of items that are in the sale
     */
    public int amountOfItems() {
    	int amount = 0;
    	for(int i = 0; i < listOfItems.size(); i++) {
    		amount += listOfItems.get(i).getQuantity();
    	}
    	return amount;
    }

    public ArrayList<Item> getItemsInSale() {
        return listOfItems;
    }
}
