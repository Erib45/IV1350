package se.kth.iv1350.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.Item;

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

  

    @Test
    void amountOfItemsTest() {
        int actual = 1;
        int expected = saleDTO.amountOfItems();
        assertEquals(expected, actual, "Amount of items did not match actual amount");
    }
}