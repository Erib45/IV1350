package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.model.Sale;

import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExternalInventorySystemTest {

    private ExternalInventorySystem invSys;
    private HashMap<Integer, ItemData> items;


    @BeforeEach
    void setUp() throws ItemIDInvalidException, DatabaseConnectionErrorException {
        items = new HashMap<>();
        items.put(1, new ItemData(10, 1, 15, 0.08f, "name1", "description1"));
        items.put(2, new ItemData(5, 2, 25, 0.1f, "name2", "description2"));
        items.put(3, new ItemData(12, 3, 55, 0.15f, "name3", "description3"));
        invSys = new ExternalInventorySystem();
        DbHandler dbHandler = new DbHandler();
        Sale sale = new Sale(dbHandler);
        ItemDTO itemDTO = dbHandler.getItemInfo(1);
        sale.addItem(itemDTO,1);
    }

    @AfterEach
    void tearDown() {
        items.clear();
        invSys = null;
    }

    @Test
    void getItemInfoTest() throws ItemIDInvalidException, DatabaseConnectionErrorException {
        int itemID = 1;
        ItemDTO actual = invSys.getItemInfo(itemID);
        ItemDTO expected = new ItemDTO(items.get(itemID).ID, items.get(itemID).price,items.get(itemID).tax,
                items.get(itemID).name, items.get(itemID).description);
        assertEquals(expected, actual, "Correct item was not returned");
    }

    @Test
    void getItemInfoInvalidIDTest() throws ItemIDInvalidException, DatabaseConnectionErrorException {
    	try {
    		ItemDTO actual = invSys.getItemInfo(0);
    	}catch(Exception e) {
    		assertTrue(e.getMessage().contains("0"), "The wrong itemID has been registered in the exception"
    				+ " or the exception was not thrown correctly");
    	}
    }
    

    /*Private static inner class representing
    mutable data stored in a database.*/
    private static class ItemData {
        private int quantity;
        private int ID, price;
        private float tax;
        private String name, description;

        public void updateQuantity(int quantity){
            this.quantity += quantity;
        }

        public ItemData(int quantity, int ID, int price, float tax,
                        String name, String description){
            this.quantity = quantity;
            this.ID = ID;
            this.price = price;
            this.tax = tax;
            this.name = name;
            this.description = description;
        }
    }
}