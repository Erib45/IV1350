package DTO;

import java.util.ArrayList;

public class SaleDTO {

    private final float total;
    private final ArrayList<ItemInSale> itemsInSale;

    public SaleDTO(float total, ArrayList<ItemInSale> itemsInSale){
        this.total = total;
        this.itemsInSale = itemsInSale;
    }

    public float getTotal() {
        return total;
    }

    public ItemInSale getItem(int index){
       return itemsInSale.get(index);
    }

    public ItemInSale getLastItem(){
        return itemsInSale.get((itemsInSale.size()-1));
    }
    
    public int amountOfItems() {
    	int amount = 0;
    	for(int i = 0; i < itemsInSale.size(); i++) {
    		amount += itemsInSale.get(i).getQuantity();
    	}
    	return amount;
    }

    public ArrayList<ItemInSale> getItemsInSale() {
        return itemsInSale;
    }
}
