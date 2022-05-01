package model;

import integration.DbHandler;
import DTO.ItemDTO;
import DTO.ItemInSale;
import DTO.SaleDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Sale {

    private float total;
    private String timeOfSale;
    private DbHandler dbHandler;
    private ArrayList<ItemInSale> itemsInSale = new ArrayList<>();

    public Sale(DbHandler dbHandler){
        this.dbHandler = dbHandler;
    }

    /**
     * Add item to sale and updates sale total.
     * @param itemDTO DTO containing information about the item.
     */
    public void addItem(ItemDTO itemDTO, int quantity){
        /*for (int i = 0; i < quantity; i++){
            itemsInSale.add(itemDTO);
        }*/
    	ItemInSale newItem = new ItemInSale(itemDTO, quantity);
    	if(!itemsInSale.contains(newItem)) {
    		itemsInSale.add(newItem);
    	}
    	else {
    		itemsInSale.get(itemsInSale.indexOf(newItem)).updateQuantity(quantity);
    	}
    	//itemsInSale.indexOf(itemDTO);
        updateTotal(itemDTO.getPrice() * quantity) ;
    }

    public SaleDTO getSale(){
        return new SaleDTO(total, itemsInSale);
    }
    

    public float getTotal() {
        return total;
    }
    
    public void applyDiscount(float discount) {
    	total *= discount;
    }

    public Receipt getReceipt(int amount){
        setTimeOfSale();
        dbHandler.logSale(getSale());
        float tax = 0;
        for(int i = 0; i < itemsInSale.size(); i++) {
        	tax += (itemsInSale.get(i).getItem().getPrice() * (itemsInSale.get(i).getQuantity()*itemsInSale.get(i).getItem().getTax()));
        }
        return new Receipt(this, amount, timeOfSale, tax);
    }
    

    private void updateTotal(float amount){
        total += amount;
    }

    private void setTimeOfSale(){
       DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       timeOfSale = LocalDateTime.now().format(dateTimeFormatter);

    }
    
}
