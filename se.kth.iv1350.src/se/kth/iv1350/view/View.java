package se.kth.iv1350.view;

import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.controller.Controller;

/**
 * View represent the user interface which in this program is hard coded to run through a possible scenario
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class View {

    private Controller controller;

    public View(se.kth.iv1350.controller.Controller controller){
        this.controller = controller;

    }

    public void runProgram() {
    	System.out.println("Starting new sale");
    	controller.startSale();
    	System.out.println("Adding item with itemID 1");
        addItem(1);
        System.out.println("Adding item with itemID 2");
        addItem(2);
        System.out.println("Adding 2 of item with itemID 3");
        addItem(3, 2);
        System.out.println("Adding 3 of item with itemID 3");
        addItem(3, 3);
        System.out.println("Adding item with itemID 1");
        addItem(1);
        System.out.println("Adding 4 of item with itemID 1");
        addItem(1, 4);
        System.out.println("Adding item with itemID 3");
        addItem(3);
        System.out.println("Applying a discount for costumerID 101");
        System.out.println("New total: " + controller.applyDiscount(101) + "\n");
        System.out.println("Costumer pays 1000\n");
        controller.enterPayment(1000);
    }
    
    void addItem(int itemID) {
    	addItem(itemID, 1);
    	
    }
    
    void addItem(int itemID, int quantity) {
    	SaleDTO saleDTO = controller.addItem(itemID, quantity).getSale();
    	System.out.println("New total: " + saleDTO.getTotal() + " Item: " + saleDTO.getItemWithID(itemID).getItemDTO().getName() + "\n" + saleDTO.getItemWithID(itemID).getItemDTO().getDescription() +"\n");
    }
}
