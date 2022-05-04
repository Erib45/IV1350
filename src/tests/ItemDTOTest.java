package tests;

import DTO.ItemDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemDTOTest {
    private int ID, price;
    private float tax;
    private String name, description;
    private ItemDTO itemDTO;

    @BeforeEach
    void setup(){
        ID = 1;
        price = 15;
        tax  = 0.08f;
        name = "name1";
        description = "description1";
        itemDTO = new ItemDTO(1, 15, 0.08f,
                "name1", "description1");
    }

    @AfterEach
    void tearDown(){

    }
    
    @Test
    void equalsIsEqualsTest() {
    	ItemDTO anotherItemDTO = new ItemDTO(1, 23, 0.02f, "Test", "Testing");
    	boolean expected = true;
    	boolean actual = itemDTO.equals(anotherItemDTO);
    	assertEquals(expected, actual, "equalsIsEqualsTest() fail");
    }
    
    @Test
    void equalsIsNotEqualsTest() {
    	ItemDTO anotherItemDTO = new ItemDTO(2, 23, 0.02f, "Test", "Testing");
    	boolean expected = false;
    	boolean actual = itemDTO.equals(anotherItemDTO);
    	assertEquals(expected, actual, "equalsIsNotEqualsTest() fail");
    }
    
    @Test
    void equalsDifferentObjectTest() {
    	Object anotherObject = new Object();
    	boolean expected = false;
    	boolean actual = itemDTO.equals(anotherObject);
    	assertEquals(expected, actual, "equalsDifferentObjectTest() fail");
    }

    @Test
    void getPriceTest(){
        int expected = price;
        int actual = itemDTO.getPrice();
        assertEquals(expected, actual, "getPrice() fail");
    }

    @Test
    void getTaxTest(){
        float expected = tax;
        float actual = itemDTO.getTax();
        assertEquals(expected, actual, "getTax() fail");
    }

    @Test
    void getNameTest(){
        String expected = name;
        String actual = itemDTO.getName();
        assertEquals(expected, actual, "getName() fail");
    }

    @Test
    void getDescriptionTest(){
        String expected = description;
        String actual = itemDTO.getDescription();
        assertEquals(expected, actual, "getDescription() fail");
    }

    @Test
    void getIDTest(){
        int expected = ID;
        int actual = itemDTO.getID();
        assertEquals(expected, actual, "getID() fail");
    }

}

