package se.kth.iv1350.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.controller.OperationFailedException;
import se.kth.iv1350.integration.ItemIDInvalidException;

/**
 * View represent the user interface and contains hard coded calls to the controller
 * showing an example run of the program.
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

    public void runProgram(){
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
        System.out.println("Applying a discount for customerID 101");
        System.out.println("New total: " + controller.applyDiscount(101) + "\n");
        System.out.println("Customer pays 1000\n");
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
        System.out.println("Adding the hardcoded \"lose connection to database\" id");
        addItem(50);
        System.out.println("Adding 4 of item with itemID 2");
        addItem(2, 4);
        System.out.println("Adding item with itemID 3");
        addItem(3);
        System.out.println("Applying a discount for customerID 103");
        System.out.println("New total: " + controller.applyDiscount(101) + "\n");
        System.out.println("Customer pays 1000\n");
        controller.enterPayment(1000);
    }
    
    private void addItem(int itemID){
        addItem(itemID, 1);
    }

    private void addItem(int itemID, int quantity){
        try {
            SaleDTO saleDTO = controller.addItem(itemID, quantity).getSale();
            System.out.println("New total: " + saleDTO.getTotal() + "\nItem: " +
                    saleDTO.getItemWithID(itemID).getItemDTO().getName() + "\n" +
                    saleDTO.getItemWithID(itemID).getItemDTO().getDescription() +"\n");
            }catch(ItemIDInvalidException e) {
                System.out.println("\n*TO USER INTERFACE* Item id is invalid\n");
                logMsg(e);
            }catch(OperationFailedException e) {
            	System.out.println("\n*TO USER INTERFACE* Operation failed\n");
                logMsg(e);
            }
        }

    private void logMsg(Exception e){
    	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(dateTimeFormatter);
    	System.out.println("*LOG MESSAGE*\nError message: " + e.getMessage() + "\nTime of error: " + time);
    	e.printStackTrace();
    	System.out.println("*END OF LOG MESSAGE*\n");
    }
}
