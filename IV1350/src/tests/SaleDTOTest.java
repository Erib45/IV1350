package tests;

import DTO.ItemDTO;
import DTO.SaleDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SaleDTOTest {

    private float total;
    private ArrayList<ItemDTO> itemsInSale;
    private SaleDTO saleDTO;

    @BeforeEach
    void setUp() {
        total = 100;
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