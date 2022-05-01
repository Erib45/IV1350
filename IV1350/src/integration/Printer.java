package integration;

import java.text.DecimalFormat;

import model.Receipt;

public class Printer {
    public Printer(){
    	
    }
    public void printReceipt(Receipt receipt) {
    	DecimalFormat df = new DecimalFormat("#.##");
    	System.out.println("#1 Paid: " +  df.format(receipt.getAmountPaid()));
    	System.out.println("#2 Change: " + df.format(receipt.getChange()));
    	System.out.println("#3 Total cost including tax: " + df.format(receipt.getTotal()));
    	System.out.println("#4 Tax: " + df.format(receipt.getTotalTax()));
    	System.out.println("#5 Time of sale: " + receipt.getTimeOfSale());
    	System.out.println("#6 Items in sale:");
    	for(int i = 0; i < receipt.getItemsInSale().size(); i++) {
    		System.out.println("#" + (i + 7) + " " + receipt.getItemsInSale().get(i).getItem().getName()
    			+	"   "+receipt.getItemsInSale().get(i).getItem().getPrice()+"   *" + receipt.getItemsInSale().get(i).getQuantity() 
    			+ "(" + receipt.getItemsInSale().get(i).getQuantity()*receipt.getItemsInSale().get(i).getItem().getPrice() +")");
    	}
    }
}
