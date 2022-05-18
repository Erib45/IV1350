package se.kth.iv1350.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.integration.DatabaseConnectionErrorException;
import se.kth.iv1350.integration.DbHandler;
import se.kth.iv1350.integration.ItemIDInvalidException;
import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class ControllerTest {
    private Controller controller;
    private Sale sale;
    private DbHandler dbHandler = new DbHandler();

    @BeforeEach
    void setUp() throws ItemIDInvalidException, DatabaseConnectionErrorException {
        this.controller = new Controller();
        controller.startSale();
        sale = new Sale(dbHandler);
        sale.addItem(dbHandler.getItemInfo(1), 5);
        sale.addItem(dbHandler.getItemInfo(2), 3);
        sale.addItem(dbHandler.getItemInfo(3), 5);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void startSale() {

    }

    @Test
    void testAddItemValidID() throws ItemIDInvalidException, OperationFailedException {
        String expected = "Total: 25.0 Item: name2\ndescription2";
        SaleDTO saleDTO = controller.addItem(2).getSale();
        String actual = "Total: " + saleDTO.getTotal() + " Item: " + saleDTO.getItemWithID(2).getItemDTO().getName() + "\n" + saleDTO.getItemWithID(2).getItemDTO().getDescription();
        assertEquals(expected, actual, "Item with valid ID not added");

    }

    @Test
    void testAddItemInvalidID() throws OperationFailedException{
    	try {
        controller.addItem(5);
    	}catch(ItemIDInvalidException e) {
    		assertTrue(e.getMessage().contains("5"), "The wrong itemID has been registered in the exception"
    				+ " or the exception was not thrown correctly");
    	}

    }

    @Test
    void testAddItemNoConnectionToDatabase() throws ItemIDInvalidException {
        try {
            controller.addItem(50);
        }catch(OperationFailedException e) {
            assertTrue(e.getCause().getMessage().contains("Connection to the inventory database could not be established"),
                     "The exception was not thrown correctly");
        }
    }

    @Test
    void testAddItemWithQuantityValidID() throws ItemIDInvalidException, OperationFailedException {
        String expected = "Total: 75.0 Item: name2\ndescription2";
        SaleDTO saleDTO = controller.addItem(2, 3).getSale();
        String actual = "Total: " + saleDTO.getTotal() + " Item: " + saleDTO.getItemWithID(2).getItemDTO().getName() + "\n" + saleDTO.getItemWithID(2).getItemDTO().getDescription();
        assertEquals(expected, actual, "Item with valid ID and quantity more than one was not added");

    }

    @Test
    void testAddItemWithQuantityInvalidID() throws OperationFailedException{
    	try {
    		 controller.addItem(0, 3);
    	}catch(ItemIDInvalidException e) {
    		assertTrue(e.getMessage().contains("0"), "The wrong itemID has been registered in the exception"
    				+ " or the exception was not thrown correctly");
    	}      
    }

    @Test
    void testApplyDiscountEligable() throws ItemIDInvalidException, OperationFailedException {
        controller.addItem(1, 2);
        controller.addItem(2);
        float expected = 52.25f;
        float actual = controller.applyDiscount(102);
        assertEquals(expected, actual, "Wrong discount applied");

    }

    @Test
    void testApplyDiscountNotEligable() throws ItemIDInvalidException, OperationFailedException {
        controller.addItem(1, 2);
        controller.addItem(2);
        float expected = 55f;
        float actual = controller.applyDiscount(107);
        assertEquals(expected, actual, "Discount was applied with invalid ID");

    }
    @Test
    void testApplyDiscountMoreThanTen() throws ItemIDInvalidException, OperationFailedException {
        controller.addItem(1, 5);
        controller.addItem(2, 3);
        controller.addItem(3, 5);
        float expected = 382.5f;
        float actual = controller.applyDiscount(107);
        assertEquals(expected, actual, "Discount was not applied despite there being more than 10 items");

    }


    @Test
    void testEnterPaymentPaymentValidWithChange() throws ItemIDInvalidException, OperationFailedException {
        controller.addItem(1, 5);
        controller.addItem(2, 3);
        controller.addItem(3, 5);
        sale.endSale();
        Receipt expectedReceipt = sale.createReceipt(500);
        Receipt actualReceipt = controller.enterPayment(500);
        float expected = expectedReceipt.getTotal();
        float actual = actualReceipt.getTotal();
        assertEquals(expected, actual, "Receipt did not match actual total");
        expected = expectedReceipt.getChange();
        actual = actualReceipt.getChange();
        assertEquals(expected, actual, "Receipt did not match actual change");
        expected = expectedReceipt.getTotalTax();
        actual = actualReceipt.getTotalTax();
        assertEquals(expected, actual, "Receipt did not match actual tax ");
        expected = expectedReceipt.getAmountPaid();
        actual = actualReceipt.getAmountPaid();
        assertEquals(expected, actual, "Receipt did not match actual amount paid");
        String expectedTimeOfSale = expectedReceipt.getTimeOfSale();
        String actualTimeOfSale = actualReceipt.getTimeOfSale();
        assertEquals(expectedTimeOfSale, actualTimeOfSale, "Receipt did not match actual time of sale");
        ArrayList<Item> expectedListOfItems = expectedReceipt.getItemsInSale();
        ArrayList<Item> actualListOfItems = expectedReceipt.getItemsInSale();
        assertEquals(expectedListOfItems, actualListOfItems, "Receipt did not match actual list of items");

    }

    @Test
    void testEnterPaymentPaymentValidNoChange() throws ItemIDInvalidException, OperationFailedException {
        controller.addItem(1, 5);
        controller.addItem(2, 3);
        controller.addItem(3, 5);
        sale.endSale();
        Receipt expectedReceipt = sale.createReceipt(425);
        Receipt actualReceipt = controller.enterPayment(425);
        float expected = expectedReceipt.getTotal();
        float actual = actualReceipt.getTotal();
        assertEquals(expected, actual, "Receipt did not match actual total");
        expected = expectedReceipt.getChange();
        actual = actualReceipt.getChange();
        assertEquals(expected, actual, "Receipt did not match actual change");
        expected = expectedReceipt.getTotalTax();
        actual = actualReceipt.getTotalTax();
        assertEquals(expected, actual, "Receipt did not match actual tax");
        expected = expectedReceipt.getAmountPaid();
        actual = actualReceipt.getAmountPaid();
        assertEquals(expected, actual, "Receipt did not match actual amount paid");
        String expectedTimeOfSale = expectedReceipt.getTimeOfSale();
        String actualTimeOfSale = actualReceipt.getTimeOfSale();
        assertEquals(expectedTimeOfSale, actualTimeOfSale, "Receipt did not match actual time of sale");
        ArrayList<Item> expectedListOfItems = expectedReceipt.getItemsInSale();
        ArrayList<Item> actualListOfItems = expectedReceipt.getItemsInSale();
        assertEquals(expectedListOfItems, actualListOfItems, "Receipt did not match actual list of items");

    }

    @Test
    void testEnterPaymentInvalid() throws ItemIDInvalidException, OperationFailedException {
        controller.addItem(1, 5);
        controller.addItem(2, 3);
        controller.addItem(3, 5);
        sale.endSale();
        Receipt actual = controller.enterPayment(200);
        assertNull(actual, "Controller returned receipt even though payment was not enough");
    }
}