package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DTO.ItemDTO;
import integration.DbHandler;
import model.Receipt;
import model.Sale;

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
		assertEquals(sale.getSale().getItemsInSale().get(0).getQuantity(), 3, "addItemItemNotInSaleTest() fail");
		assertEquals(sale.getSale().getItemsInSale().get(0).getItemDTO(), itemDTO, "addItemItemNotInSaleTest() fail");
		assertEquals(sale.getSale().getItemsInSale().size(), 1);
	}
	
	@Test
	void addItemItemInSaleTest() {
		sale.addItem(itemDTO, 3);
		sale.addItem(itemDTO, 2);
		assertEquals(sale.getSale().getItemsInSale().get(0).getQuantity(), 5, "addItemItemInSaleTest() fail");
		assertEquals(sale.getSale().getItemsInSale().get(0).getItemDTO(), itemDTO, "addItemItemInSaleTest() fail");
		assertEquals(sale.getSale().getItemsInSale().size(), 1);
	}
	
	@Test
	void addItemOtherItemInSaleTest() {
		sale.addItem(new ItemDTO(6, 23, 0.02f, "test2", "testing2"), 2);
		sale.addItem(itemDTO, 3);
		assertEquals(sale.getSale().getItemsInSale().get(1).getQuantity(), 3, "addItemOtherItemInSaleTest() fail");
		assertEquals(sale.getSale().getItemsInSale().get(1).getItemDTO(), itemDTO, "addItemOtherItemInSaleTest() fail");
		assertEquals(sale.getSale().getItemsInSale().size(), 2);
	}
	
	@Test
	void applyDiscountCostumerTest() {
		sale.addItem(itemDTO, 2);
		float expected = (itemDTO.getPrice() * 2) * (1 - 0.02f);
		sale.applyDiscount(101);
		float actual = sale.getTotal();
		assertEquals(expected, actual, "applyDiscountTest() fail");
	}
	
	@Test
	void applyDiscountOver10Test() {
		sale.addItem(itemDTO, 11);
		float expected = (itemDTO.getPrice() * 11) * (1 - 0.1f);
		sale.applyDiscount(107);
		float actual = sale.getTotal();
		assertEquals(expected, actual, "applyDiscountTest() fail");
	}

	@Test
	void applyDiscountOver10AndCostumerTest() {
		sale.addItem(itemDTO, 11);
		float expected = (itemDTO.getPrice() * 11) * (1 - 0.12f);
		sale.applyDiscount(101);
		float actual = sale.getTotal();
		assertEquals(expected, actual, "applyDiscountTest() fail");
	}
	
	@Test
	void applyDiscountInvalidTest() {
		sale.addItem(itemDTO, 2);
		float expected = (itemDTO.getPrice() * 2);
		sale.applyDiscount(108);
		float actual = sale.getTotal();
		assertEquals(expected, actual, "applyDiscountTest() fail");
	}
	
	
	@Test
	void endSaleTest() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		sale.endSale(); 
		String timeOfSale = LocalDateTime.now().format(dateTimeFormatter);
		assertEquals(timeOfSale, sale.createReceipt(1000).getTimeOfSale(), "endSaleTest() fail");
	}
	
	@Test
	void createReceiptTest() {
		sale.addItem(itemDTO, 1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String timeOfSale = LocalDateTime.now().format(dateTimeFormatter);
		sale.endSale();
		Receipt receipt = sale.createReceipt(500);
		assertEquals(receipt.getAmountPaid(), 500, "createReceiptTest() fail");
		assertEquals(receipt.getChange(), 500-45, "createReceiptTest() fail");
		assertEquals(receipt.getItemsInSale().size(), 1, "createReceiptTest() fail");
		assertEquals(receipt.getItemsInSale().get(0).getItemDTO(), itemDTO, "createReceiptTest() fail");
		assertEquals(receipt.getTimeOfSale(), timeOfSale, "createReceiptTest() fail");
		assertEquals(receipt.getTotal(), 45, "createReceiptTest() fail");
		assertEquals(receipt.getTotalTax(), 45 * 0.02f, "createReceiptTest() fail");
	}
}
