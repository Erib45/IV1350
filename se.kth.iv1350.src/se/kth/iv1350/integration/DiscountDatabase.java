package se.kth.iv1350.integration;

import java.util.HashMap;

import se.kth.iv1350.DTO.SaleDTO;

/**
 * DiscountDatabase communicates with external server containing a database with discounts linked with costumer id's
 * (In this case we have ourselves simulated the external server).
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class DiscountDatabase {

    private HashMap<Integer, Float> discounts = new HashMap<>();
    private void addDiscounts(){
        discounts.put(101, 0.02f);
        discounts.put(102, 0.05f);
        discounts.put(103, 0.10f);
    }

    /**
     * Class constructor
     */
    DiscountDatabase(){
        addDiscounts();
    }

    /**
     * Check discount percentage a sale is eligible for.
     * @param customerID Integer identifying a customer.
     * @param saleDTO  DTO containing information about a sale.
     * @return <code>float</code> containing the discount
     * percentage the sale is eligible for.
     */
    //Maximum discount is set to 90%
    float checkDiscount(int customerID, SaleDTO saleDTO){
        float percentage = 1;
        if(discounts.containsKey(customerID)){
            percentage -= discounts.get(customerID);
        }
        if(saleDTO.amountOfItems() >= 10){
            percentage -= 0.10;
        }
        if (percentage < 0.1) 
        	percentage = 0.1f;
        return percentage;
    }
}
