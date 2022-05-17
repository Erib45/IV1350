package se.kth.iv1350.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.controller.OperationFailedException;
import se.kth.iv1350.integration.DatabaseConnectionErrorException;
import se.kth.iv1350.integration.ItemidInvalidException;

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
        controller.addTotalRevenueObserver(new TotalRevenueView());
        controller.addTotalRevenueObserver(new TotalRevenueFileOutput());
    }

    public void runProgram() throws OperationFailedException{
    	System.out.println("Starting new sale");
    	controller.startSale();
    	System.out.println("Adding item with itemID 1");
        addItem(1);
        System.out.println("Adding item with itemID 2");
        addItem(2);
        System.out.println("Adding 2 of item with itemID 3");
        addItem(3, 2);
        System.out.println("Adding 3 of item with itemID 8");
        addItem(8, 3);
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
        
        System.out.println("\nStarting new sale");
    	controller.startSale();
    	System.out.println("Adding item with itemID 2");
        addItem(2);
        System.out.println("Adding item with itemID 2");
        addItem(2);
        System.out.println("Adding 2 of item with itemID 1");
        addItem(1, 2);
        System.out.println("Adding 3 of item with itemID 8");
        addItem(8, 3);
        System.out.println("Adding item with itemID 3");
        addItem(3);
        System.out.println("Adding 4 of item with itemID 2");
        addItem(2, 4);
        System.out.println("Adding item with itemID 3");
        addItem(3);
        System.out.println("Applying a discount for costumerID 103");
        System.out.println("New total: " + controller.applyDiscount(101) + "\n");
        System.out.println("Costumer pays 1000\n");
        controller.enterPayment(1000);
    }
    
    void addItem(int itemID) throws OperationFailedException {
    	addItem(itemID, 1);
    }
    
    void addItem(int itemID, int quantity) throws OperationFailedException {
    	try {
    	SaleDTO saleDTO = controller.addItem(itemID, quantity).getSale();
    	System.out.println("New total: " + saleDTO.getTotal() + "\nItem: " + saleDTO.getItemWithID(itemID).getItemDTO().getName() + "\n" + saleDTO.getItemWithID(itemID).getItemDTO().getDescription() +"\n");
    	}catch(ItemidInvalidException e) {
    		System.out.println("\n*TO USER INTERFACE* " + e.getMessage() + "\n");
    		logMsg(e);
    	}
    	}
    
    void logMsg(Exception e){
    	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(dateTimeFormatter);
    	System.out.println("*LOG MESSAGE*\nError message: " + e.getMessage() + "\nTime of error: " + time);
    	e.printStackTrace();
    	System.out.println("*END OF LOG MESSAGE\n");
    }
}
