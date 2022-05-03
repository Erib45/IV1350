package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import DTO.ItemDTO;
import model.Item;

class ItemTest {
	

/*	int ID = 1;
	int  price = 15;
	float tax  = 0.08f;
	String name = "name1";
	String   description = "description1"; */
   
    Item item = new Item(new ItemDTO(1, 15, 0.08f,"name1", "description1"),1);
    Item otherItem = new Item(new ItemDTO(5, 25, 0.1f, "name2", "description2"),1);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testUpdateQuantity() {
		item.updateQuantity(3);
		int expected = 4;
		int actual = item.getQuantity();
		assertEquals(expected, actual, "updateQuantity() fail");
	}
	
	@Test
	void testUpdateQuantityZero() {
		item.updateQuantity(0);
		int expected = 1;
		int actual = item.getQuantity();
		assertEquals(expected, actual, "updateQuantity() fail");
	}
	
	@Test
	void testUpdateQuantityNegative() {
		item.updateQuantity(-1);
		int expected = 0;
		int actual = item.getQuantity();
		assertEquals(expected, actual, "updateQuantity() fail");
	}

	@Test
	void testEqualsWithNull() {
		Object other = null;
		
		boolean expected = false;
		boolean actual = item.equals(other);
		assertEquals(expected, actual, "Equals() fail");
		
	}
	@Test
	void testEqualsFalse() {
		boolean expected = false;
		boolean actual = item.equals(otherItem);
		assertEquals(expected, actual, "Equals() fail");	
	}
	
	@Test 
	void testEqualsTrue() {
		boolean expected = true;
		boolean actual = item.equals(item);
		assertEquals(expected, actual, "Equals() fail");
	}
}
