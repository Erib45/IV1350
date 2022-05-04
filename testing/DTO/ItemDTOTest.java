package DTO;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemDTOTest {
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
    	assertEquals(expected, actual, "equals returned false even though itemDTOs have same ID");
    }
    
    @Test
    void equalsIsNotEqualsTest() {
    	ItemDTO anotherItemDTO = new ItemDTO(2, 23, 0.02f, "Test", "Testing");
    	boolean expected = false;
    	boolean actual = itemDTO.equals(anotherItemDTO);
    	assertEquals(expected, actual, "equals return true even though itemDTOs do not have same ID");
    }
    
    @Test
    void equalsDifferentObjectTest() {
    	Object anotherObject = new Object();
    	boolean expected = false;
    	boolean actual = itemDTO.equals(anotherObject);
    	assertEquals(expected, actual, "equals is true even if the param is an instance of an object that's not itemDTO");
    }

    @Test
    void getPriceTest(){
        int expected = price;
        int actual = itemDTO.getPrice();
        assertEquals(expected, actual, "returns wrong value");
    }

    @Test
    void getTaxTest(){
        float expected = tax;
        float actual = itemDTO.getTax();
        assertEquals(expected, actual, "returns wrong value");
    }

    @Test
    void getNameTest(){
        String expected = name;
        String actual = itemDTO.getName();
        assertEquals(expected, actual, "returns wrong value");
    }

    @Test
    void getDescriptionTest(){
        String expected = description;
        String actual = itemDTO.getDescription();
        assertEquals(expected, actual, "returns wrong value");
    }

    @Test
    void getIDTest(){
        int expected = ID;
        int actual = itemDTO.getID();
        assertEquals(expected, actual, "returns wrong value");
    }

}

