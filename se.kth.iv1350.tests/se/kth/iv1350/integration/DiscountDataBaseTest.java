package se.kth.iv1350.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.Item;

class DiscountDataBaseTest {
	DiscountDatabase database = new DiscountDatabase();
	int customerID;
	SaleDTO saleDTO;
	ArrayList<Item> itemsInSale = new ArrayList<Item>();
	Item item;
	Item anotherItem;
	
	@BeforeEach
	void setUp() {
	item = new Item(new ItemDTO(1, 15, 0.08f,"name1", "description1"),1);	
	anotherItem = new Item(new ItemDTO(5, 25, 0.1f, "name2", "description2"),1);

	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testCheckDiscount() {
		itemsInSale.add(item);
		itemsInSale.add(item);
		customerID=101; 
		float expected =  0.98f;
		float actual = database.checkDiscount( customerID,saleDTO = new SaleDTO(30,itemsInSale));
		assertEquals(expected, actual, "Discount was not applied or applied incorrectly");
	}
	
	@Test
	void testCheckInvalidCustomerIDDiscount() {
		itemsInSale.add(item);
		itemsInSale.add(item);
		customerID=107;  
		int expected = 1;
		float actual = database.checkDiscount( customerID,saleDTO = new SaleDTO(30,itemsInSale));
		assertEquals(expected, actual, "Discount was applied even though there is an invalid ID");
	}
	
	@Test
	void testCheckDiscount10ItemsAndCustomerID() {

		for(int i = 0; i<10; i++)
			itemsInSale.add(item);

		customerID=101; 
		float expected =  1f-0.1f-0.02f;
		float actual = database.checkDiscount( customerID,saleDTO = new SaleDTO(30,itemsInSale));
		assertEquals(expected, actual, "discount is not applied correctly, either wrong discount from Customer ID or not accounting for 10+ items ");
	}
	@Test
	void testCheckDiscount10Items() {

		for(int i = 0; i<10; i++)
			itemsInSale.add(item);

		customerID=000; 
		float expected =  1f-0.1f;
		float actual = database.checkDiscount( customerID,saleDTO = new SaleDTO(30,itemsInSale));
		assertEquals(expected, actual, "discount is not applied even though items are more than 10");
	}

}
