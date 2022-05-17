package se.kth.iv1350.integration;

import java.text.DecimalFormat;

import se.kth.iv1350.model.Receipt;

/**
 * Printer would be used to print out the receipt to the physical printer
 * (In this case we have ourself simulated what the printer would have printed in console)
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class Printer {
    public Printer(){
    }
    /**
     * Prints receipt
     * @param receipt Containing information about the purchase
     */
    public void printReceipt(Receipt receipt) {
    	DecimalFormat df = new DecimalFormat("#.##");
    	System.out.println("#1 Paid: " +  df.format(receipt.getAmountPaid()));
    	System.out.println("#2 Change: " + df.format(receipt.getChange()));
    	System.out.println("#3 Total cost including tax: " + df.format(receipt.getTotal()));
    	System.out.println("#4 Tax: " + df.format(receipt.getTotalTax()));
    	System.out.println("#5 Time of sale: " + receipt.getTimeOfSale());
    	System.out.println("#6 Items in sale:");
    	for(int i = 0; i < receipt.getItemsInSale().size(); i++) {
    		System.out.println("#" + (i + 7) + " " + receipt.getItemsInSale().get(i).getItemDTO().getName()
    			+	"   "+receipt.getItemsInSale().get(i).getItemDTO().getPrice()+"   *" + receipt.getItemsInSale().get(i).getQuantity()
    			+ "(" + receipt.getItemsInSale().get(i).getQuantity()*receipt.getItemsInSale().get(i).getItemDTO().getPrice() +")");
    	}
    }
}
