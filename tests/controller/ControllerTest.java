package controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testAddItemValidID() {
        String expected = "Total: 25.0 Item: name2\ndescription2";
        String actual = controller.addItem(2);
        assertEquals(expected, actual, "Item with valid ID not added");

    }

    @Test
    void testAddItemInvalidID() {
        String actual = controller.addItem(5);
        assertNull(actual, "Item with invalid ID was added");

    }

    @Test
    void testAddItemWithQuantityValidID() {
        String expected = "Total: 75.0 Item: name2\ndescription2";
        String actual = controller.addItem(2, 3);
        assertEquals(expected, actual, "Item with valid ID and quantity more than one was not added");

    }

    @Test
    void testAddItemWithQuantityInvalidID() {
        String actual = controller.addItem(0, 3);
        assertNull(actual, "Items with invalid IDs were added");

    }

    @Test
    void testApplyDiscountEligable() {
        controller.addItem(1, 2);
        controller.addItem(2);
        float expected = 52.25f;
        float actual = controller.applyDiscount(102);
        assertEquals(expected, actual, "Wrong discount applied");

    }

    @Test
    void testApplyDiscountNotEligable() {
        controller.addItem(1, 2);
        controller.addItem(2);
        float expected = 55f;
        float actual = controller.applyDiscount(107);
        assertEquals(expected, actual, "Discount was applied with invalid ID");

    }
    @Test
    void testApplyDiscountMoreThanTen() {
        controller.addItem(1, 5);
        controller.addItem(2, 3);
        controller.addItem(3, 5);
        float expected = 382.5f;
        float actual = controller.applyDiscount(107);
        assertEquals(expected, actual, "Discount was not applied despite there being more than 10 items");

    }


    @Test
    void testEnterPaymentPaymentValidWithChange() {
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
    void testEnterPaymentPaymentValidNoChange() {
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
    void testEnterPaymentInvalid() {
        controller.addItem(1, 5);
        controller.addItem(2, 3);
        controller.addItem(3, 5);
        sale.endSale();
        Receipt actual = controller.enterPayment(200);
        assertNull(actual, "Controller returned receipt even though payment was not enough");
    }
}