package integration;

import DTO.ItemDTO;
import DTO.ItemInSale;
import DTO.SaleDTO;

import java.util.ArrayList;
import java.util.HashMap;


public class ExternalInventorySystem {

    private HashMap<Integer,ItemData> items = new HashMap<>();
    
    private void addItems(){
        items.put(1, new ItemData(10, 1, 15, 0.08f, "name1", "description1"));
        items.put(2, new ItemData(5, 2, 25, 0.1f, "name2", "description2"));
        items.put(3, new ItemData(12, 3, 55, 0.15f, "name3", "description3"));
    }

    ExternalInventorySystem(){
        addItems();
    }
    
    /**
     * Logs the sale in the Inventory system
     * @param sale SaleDTO describing the current sale
     */
    public void logSale(SaleDTO sale) {
    	ArrayList<ItemInSale> list = sale.getItemsInSale();
    	for(int i = 0; i < list.size(); i++) {
    		if(items.containsKey(list.get(i).getItem().getID())) {
    			items.get(list.get(i).getItem().getID()).quantity--;
    		}
    	}
    }
    
    /**
     * Check if item identifier exits in inventorySystem
     * @param itemID Integer identifying an item.
     * @return true if item exists.
     */
    
    private boolean checkItem(int itemID){
        return items.containsKey(itemID);
    }

    //
    ItemDTO getItemInfo(int itemID){
        if(items.containsKey(itemID)){
                return new ItemDTO(items.get(itemID).ID, items.get(itemID).price,items.get(itemID).tax,
                        items.get(itemID).name, items.get(itemID).description);
        }
        return null;
    }

    /*Private static inner class representing
    mutable data stored in a database.*/
    private static class ItemData {
        private int quantity;
        private int ID, price;
        private float tax;
        private String name, description;

        public ItemData(int quantity, int ID, int price, float tax,
                        String name, String description){
            this.quantity = quantity;
            this.ID = ID;
            this.price = price;
            this.tax = tax;
            this.name = name;
            this.description = description;
        }
    }

}
