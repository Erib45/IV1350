package se.kth.iv1350.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.ItemDTO;


class ItemTest {
	Item item;
	Item otherItem;

	@BeforeEach
	void setUp()  {
	 item = new Item(new ItemDTO(1, 15, 0.08f,"name1", "description1"),1);
	 otherItem = new Item(new ItemDTO(5, 25, 0.1f, "name2", "description2"),1);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testUpdateQuantity() {
		item.updateQuantity(3);
		int expected = 4;
		int actual = item.getQuantity();
		assertEquals(expected, actual, "Quantity was updated incorrectly or not at all");
	}
	
	@Test
	void testUpdateQuantityZero() {
		item.updateQuantity(0);
		int expected = 1;
		int actual = item.getQuantity();
		assertEquals(expected, actual, "Quantity was changed when param is 0");
	}
	
	@Test
	void testUpdateQuantityNegative() {
		item.updateQuantity(-1);
		int expected = 0;
		int actual = item.getQuantity();
		assertEquals(expected, actual, "Quantity is incorrectly updated with a negative number");
	}

	@Test
	void testEqualsWithNull() {
		Object other = null;
		
		boolean expected = false;
		boolean actual = item.equals(other);
		assertEquals(expected, actual, "Returns true even though param is null");
		
	}
	@Test
	void testEqualsFalse() {
		boolean expected = false;
		boolean actual = item.equals(otherItem);
		assertEquals(expected, actual, "Returns true when not equal");	
	}
	
	@Test 
	void testEqualsTrue() {
		boolean expected = true;
		boolean actual = item.equals(item);
		assertEquals(expected, actual, "Returns false when equal");
	}
}
