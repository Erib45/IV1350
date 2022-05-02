package integration;

import DTO.ItemDTO;
import DTO.SaleDTO;

public class DbHandler {

    private DiscountDatabase discountDb;
    private ExternalInventorySystem invSys;
    private ExternalAccountingSystem accSys;

    /**
     * Class constructor
     */
    public DbHandler(){
        this.discountDb = new DiscountDatabase();
        this.invSys = new ExternalInventorySystem();
        this.accSys = new ExternalAccountingSystem();
    }

    /**
     * Returns an itemDTO of the specified item if it exits in the database.
     * @param itemID Integer identifying an item.
     * @return ItemDTO
     */
    public ItemDTO getItemInfo(int itemID){
       return invSys.getItemInfo(itemID);
    }

    /**
     * Returns total price of sale with discount included.
     * @param customerID Integer identifying customer.
     * @param saleDTO DTO containing information about a sale.
     * @return <code>float<code/> containing the new total price
     * of the sale with discount included.
     */
    public float getDiscount(int customerID, SaleDTO saleDTO){
        float discount = discountDb.checkDiscount(customerID, saleDTO );
        return discount;
    }
    
    /**
     * Logs the sale 
     * @param saleDTO describing a sale
     */
    public void logSale(SaleDTO saleDTO) {
    	accSys.logSale(saleDTO);
    	invSys.logSale(saleDTO);
    }
}
