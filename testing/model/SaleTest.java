package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DTO.ItemDTO;
import integration.DbHandler;


class SaleTest {
	ItemDTO itemDTO = new ItemDTO(5, 45, 0.02f, "Test", "Testing");
	DbHandler dbHandler = new DbHandler();
	Sale sale;
	
	@BeforeEach
	void setUp(){
		sale = new Sale(dbHandler);
	}

	@AfterEach
	void tearDown(){
		
	}

	@Test
	void addItemItemNotInSaleTest() {
		sale.addItem(itemDTO, 3);
		assertEquals(sale.getSale().getItemsInSale().get(0).getQuantity(), 3,  "Array itemsInSale in sale has the wrong quantity");
		assertEquals(sale.getSale().getItemsInSale().get(0).getItemDTO(), itemDTO, "Array itemsInSale in sale has wrong DTO");
		assertEquals(sale.getSale().getItemsInSale().size(), 1, "Array itemsInSale in sale has wrong size");
	}
	
	@Test
	void addItemItemInSaleTest() {
		sale.addItem(itemDTO, 3);
		sale.addItem(itemDTO, 2);
		assertEquals(sale.getSale().getItemsInSale().get(0).getQuantity(), 5, "Array itemsInSale in sale has the wrong quantity");
		assertEquals(sale.getSale().getItemsInSale().get(0).getItemDTO(), itemDTO, "Array itemsInSale in sale has wrong DTO");
		assertEquals(sale.getSale().getItemsInSale().size(), 1, "Array itemsInSale in sale has wrong size");
	}
	
	@Test
	void addItemOtherItemInSaleTest() {
		sale.addItem(new ItemDTO(6, 23, 0.02f, "test2", "testing2"), 2);
		sale.addItem(itemDTO, 3);
		assertEquals(sale.getSale().getItemsInSale().get(1).getQuantity(), 3, "Array itemsInSale in sale has the wrong quantity");
		assertEquals(sale.getSale().getItemsInSale().get(1).getItemDTO(), itemDTO, "Array itemsInSale in sale has wrong DTO");
		assertEquals(sale.getSale().getItemsInSale().size(), 2, "Array itemsInSale in sale has wrong size");
	}
	
	@Test
	void applyDiscountCostumerTest() {
		sale.addItem(itemDTO, 2);
		float expected = (itemDTO.getPrice() * 2) * (1 - 0.02f);
		sale.applyDiscount(101);
		float actual = sale.getTotal();
		assertEquals(expected, actual, "Discount was not applied or applied incorrectly");
	}
	
	@Test
	void applyDiscountOver10Test() {
		sale.addItem(itemDTO, 11);
		float expected = (itemDTO.getPrice() * 11) * (1 - 0.1f);
		sale.applyDiscount(107);
		float actual = sale.getTotal();
		assertEquals(expected, actual, "Discount was not applied even though items are more than 10");
	}

	@Test
	void applyDiscountOver10AndCostumerTest() {
		sale.addItem(itemDTO, 11);
		float expected = (itemDTO.getPrice() * 11) * (1 - 0.12f);
		sale.applyDiscount(101);
		float actual = sale.getTotal();
		assertEquals(expected, actual, "Discount was not applied correctly when items are 10+ and ID is valid");
	}
	
	@Test
	void applyDiscountInvalidTest() {
		sale.addItem(itemDTO, 2);
		float expected = (itemDTO.getPrice() * 2);
		sale.applyDiscount(108);
		float actual = sale.getTotal();
		assertEquals(expected, actual, "Discount was applied even though ID is invalid.");
	}
	
	
	@Test
	void endSaleTest() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		sale.endSale(); 
		String timeOfSale = LocalDateTime.now().format(dateTimeFormatter);
		assertEquals(timeOfSale, sale.createReceipt(1000).getTimeOfSale(), "Receipt's time of sale does not match actual time of sale");
	}
	
	@Test
	void createReceiptTest() {
		sale.addItem(itemDTO, 1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String timeOfSale = LocalDateTime.now().format(dateTimeFormatter);
		sale.endSale();
		Receipt receipt = sale.createReceipt(500);
		assertEquals(receipt.getAmountPaid(), 500, "Receipt does not match with actual amouunt paid");
		assertEquals(receipt.getChange(), 500-45, "Receipt  does not match with actual change");
		assertEquals(receipt.getItemsInSale().size(), 1, "Receipt does not match with actual size of array ItemsInSale");
		assertEquals(receipt.getItemsInSale().get(0).getItemDTO(), itemDTO, "Receipt itemDTO does not match with actual itemDTO");
		assertEquals(receipt.getTimeOfSale(), timeOfSale, "Receipt does not match with actual time of sale");
		assertEquals(receipt.getTotal(), 45, "Receipt total does not match with actual total ");
		assertEquals(receipt.getTotalTax(), 45 * 0.02f, "Receipt tax does not match with actual tax");
	}
}
