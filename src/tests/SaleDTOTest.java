package tests;

import DTO.ItemDTO;
import model.Item;
import DTO.SaleDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        itemsInSale.add(item);
        saleDTO = new SaleDTO(total, itemsInSale);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotal() {
    }

    @Test
    void getItem() {
    }

    @Test
    void getLastItem() {
    }

    @Test
    void getItemsInSale() {
    }
}