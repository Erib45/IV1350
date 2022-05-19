package se.kth.iv1350.integration;

import java.util.HashMap;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;

/**
 * ExternalInventorySystem communicates with external server containing inventory system / database
 * (In this case we have ourselves simulated the external server).
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class ExternalInventorySystem {

    private HashMap<Integer,ItemData> items = new HashMap<>();
    
    private void addItems(){
        items.put(1, new ItemData(10, 1, 15, 0.08f, "name1", "description1"));
        items.put(2, new ItemData(5, 2, 25, 0.1f, "name2", "description2"));
        items.put(3, new ItemData(12, 3, 55, 0.15f, "name3", "description3"));
    }
    
    /**
     * Class constructor.
     */
    ExternalInventorySystem(){
        addItems();
    }
    
    /**
     * Logs the sale in the Inventory system.
     * @param saleDTO SaleDTO describing a sale.
     */
    public void logSale(SaleDTO saleDTO) {
        for(int i = 0; i < saleDTO.getItemsInSale().size(); i++) {
            if(items.containsKey(saleDTO.getItem(i).getItemDTO().getID())) {
                items.get(saleDTO.getItem(i).getItemDTO().getID())
                        .updateQuantity(-saleDTO.getItem(i).getQuantity());
            }
        }
    }
    
    /**
     * Gets a ItemDTO describing the item with the corresponding ID.
     * @param itemID ID of an item.
     * @return <code>ItemDTO</code> DTO containing information about an item.
     * @throws DatabaseConnectionErrorException If connection to the inventory database could not be established.
     */
    ItemDTO  getItemInfo (int itemID) throws ItemIDInvalidException, DatabaseConnectionErrorException{
        if(items.containsKey(itemID)){
                return new ItemDTO(items.get(itemID).ID, items.get(itemID).price,items.get(itemID).tax,
                        items.get(itemID).name, items.get(itemID).description);
        }
        if(itemID == 50) {
        	throw new DatabaseConnectionErrorException("Connection to the inventory database could not be established");
        }
        throw new ItemIDInvalidException("The itemID " + itemID + " is not valid");
    }


    /*Private static inner class representing
    mutable data stored in a database.*/
    private static class ItemData {
        private int quantity;
        private int ID, price;
        private float tax;
        private String name, description;

        public void updateQuantity(int quantity){
            this.quantity += quantity;
        }

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
