package DTO;

import model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SaleDTOTest {

    private float total;
    private ArrayList<Item> itemsInSale;
    private SaleDTO saleDTO;

    @BeforeEach
    void setUp() {
        total = 100;
        ItemDTO itemDTO = new ItemDTO(1, 15, 0.08f,
                "name1", "description1");
        Item item = new Item(itemDTO, 1);
        itemsInSale = new ArrayList<>();
        itemsInSale.add(item);
        saleDTO = new SaleDTO(total, itemsInSale);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void getTotal() {
    }

    @Test
    @Disabled
    void getItem() {
    }

    @Test
    @Disabled
    void getLastItem() {
    }

    @Test
    @Disabled
    void getItemsInSale() {
    }

    @Test
    void amountOfItemsTest() {
        int actual = 1;
        int expected = saleDTO.amountOfItems();
        assertEquals(expected, actual, "amountOfItems() fail");
    }
}