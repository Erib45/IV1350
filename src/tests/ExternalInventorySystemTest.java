package integration;

import DTO.ItemDTO;
import DTO.SaleDTO;
import integration.DbHandler;
import integration.ExternalInventorySystem;
import model.Sale;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExternalInventorySystemTest {

    private ExternalInventorySystem invSys;
    private HashMap<Integer, ItemData> items;
    private SaleDTO saleDTO;
    private ItemDTO itemDTO;



    @BeforeEach
    void setUp() {
        items = new HashMap<>();
        items.put(1, new ItemData(10, 1, 15, 0.08f, "name1", "description1"));
        items.put(2, new ItemData(5, 2, 25, 0.1f, "name2", "description2"));
        items.put(3, new ItemData(12, 3, 55, 0.15f, "name3", "description3"));
        invSys = new ExternalInventorySystem();
        DbHandler dbHandler = new DbHandler();
        Sale sale = new Sale(dbHandler);
        itemDTO = dbHandler.getItemInfo(1);
        sale.addItem(itemDTO,1);
        saleDTO = sale.getSale();
    }

    @AfterEach
    void tearDown() {
        items.clear();
        invSys = null;
    }

    @Test
    void logSaleTest() {
        invSys.logSale(saleDTO);
        int expected = 9;
        int actual = items.get(1).quantity;
        assertEquals(expected, actual, "logSale() fail");

    }

    @Test
    @Disabled
    void getItemInfoTest() {
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