package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.controller.OperationFailedException;

/**
 * DbHandler is a class communicating with the classes that communicates with the external databases. 
 * It was created to lower the amount of calls that controller itself has to make.
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
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
     * @return <code>ItemDTO</code> DTO containing information about an item.
     * @throws ItemidInvalidException 
     * @throws OperationFailedException 
     * @throws DatabaseConnectionErrorException 
     */
    public ItemDTO getItemInfo(int itemID) throws ItemidInvalidException, DatabaseConnectionErrorException{
       
       return invSys.getItemInfo(itemID);
    }

    /**
     * Returns total price of sale with discount included.
     * @param customerID Integer identifying customer.
     * @param saleDTO DTO containing information about a sale.
     * @return <code>float</code> containing the new total price
     * of the sale with discount included.
     */
    public float getDiscount(int customerID, SaleDTO saleDTO){
        return discountDb.checkDiscount(customerID, saleDTO );
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
