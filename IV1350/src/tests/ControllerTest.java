package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Controller;
import integration.DbHandler;
import model.Item;
import model.Receipt;
import model.Sale;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class ControllerTest {
private Controller controller;
private Sale sale;
private DbHandler dbHandler = new DbHandler();

    @BeforeEach
    void setUp() {
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
    void addItemValidID() {
    	String expected = "Total: 25.0 Item: name2\ndescription2";
    	String actual = controller.addItem(2);
    	assertEquals(expected, actual, "addItemValidID() fail");	
    	
    }
    
    @Test
    void addItemInvalidID() {
    	String expected = null;
    	String actual = controller.addItem(5);
    	assertEquals(expected, actual, "addItemInvalidID() fail");
    	
    }
    
    @Test
    void addItemWithQuantityValidID() {
    	String expected = "Total: 75.0 Item: name2\ndescription2";
    	String actual = controller.addItem(2, 3);
    	assertEquals(expected, actual, "addItemQuantityValidID() fail");
    	
    }
    
    @Test
    void addItemWithQuantityInvalidID() {
    	String expected = null;
    	String actual = controller.addItem(0, 3);
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	
    }

    @Test
    void applyDiscountEligable() {
    	controller.addItem(1, 2);
    	controller.addItem(2);
    	float expected = 52.25f;
    	float actual = controller.applyDiscount(102);
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	
    }
    
    @Test
    void applyDiscountNotEligable() {
    	controller.addItem(1, 2);
    	controller.addItem(2);
    	float expected = 55f;
    	float actual = controller.applyDiscount(107);
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	
    }
    
    void applyDiscountMoreThanTen() {
    	controller.addItem(1, 5);
    	controller.addItem(2, 3);
    	controller.addItem(3, 5);
    	float expected = 382.5f;
    	float actual = controller.applyDiscount(107);
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	
    }
    

    @Test
    void enterPaymentPaymentValidWithChange() {
    	controller.addItem(1, 5);
    	controller.addItem(2, 3);
    	controller.addItem(3, 5);
    	sale.endSale();
    	Receipt expectedReceipt = sale.createReceipt(500);
    	Receipt actualReceipt = controller.enterPayment(500);
    	float expected = expectedReceipt.getTotal();
    	float actual = actualReceipt.getTotal();
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	expected = expectedReceipt.getChange();
    	actual = actualReceipt.getChange();
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	expected = expectedReceipt.getTotalTax();
    	actual = actualReceipt.getTotalTax();
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	expected = expectedReceipt.getAmountPaid();
    	actual = actualReceipt.getAmountPaid();
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	String expectedTimeOfSale = expectedReceipt.getTimeOfSale();
    	String actualTimeOfSale = actualReceipt.getTimeOfSale();
    	assertEquals(expectedTimeOfSale, actualTimeOfSale, "addItemQuantityInvalidID() fail");
    	ArrayList<Item> expectedListOfItems = expectedReceipt.getItemsInSale();
    	ArrayList<Item> actualListOfItems = expectedReceipt.getItemsInSale();
    	assertEquals(expectedListOfItems, actualListOfItems, "addItemQuantityInvalidID() fail");
    	
    }
    
    @Test
    void enterPaymentPaymentValidNoChange() {
    	controller.addItem(1, 5);
    	controller.addItem(2, 3);
    	controller.addItem(3, 5);
    	sale.endSale();
    	Receipt expectedReceipt = sale.createReceipt(425);
    	Receipt actualReceipt = controller.enterPayment(425);
    	float expected = expectedReceipt.getTotal();
    	float actual = actualReceipt.getTotal();
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	expected = expectedReceipt.getChange();
    	actual = actualReceipt.getChange();
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	expected = expectedReceipt.getTotalTax();
    	actual = actualReceipt.getTotalTax();
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	expected = expectedReceipt.getAmountPaid();
    	actual = actualReceipt.getAmountPaid();
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	String expectedTimeOfSale = expectedReceipt.getTimeOfSale();
    	String actualTimeOfSale = actualReceipt.getTimeOfSale();
    	assertEquals(expectedTimeOfSale, actualTimeOfSale, "addItemQuantityInvalidID() fail");
    	ArrayList<Item> expectedListOfItems = expectedReceipt.getItemsInSale();
    	ArrayList<Item> actualListOfItems = expectedReceipt.getItemsInSale();
    	assertEquals(expectedListOfItems, actualListOfItems, "addItemQuantityInvalidID() fail");
    	
    }
    
    @Test
    void enterPaymentInvalid() {
    	controller.addItem(1, 5);
    	controller.addItem(2, 3);
    	controller.addItem(3, 5);
    	sale.endSale();
    	Receipt expected = null;
    	Receipt actual = controller.enterPayment(200);
    	assertEquals(expected, actual, "addItemQuantityInvalidID() fail");
    	
    }
}