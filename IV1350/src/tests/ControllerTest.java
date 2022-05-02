package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Controller;
import integration.DbHandler;
import integration.Register;
import model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
private Controller controller;
private Register tegister;
private Sale sale;
private 

    @BeforeEach
    void setUp() {
    	Register register = new Register();
    	this.controller = new Controller(register);
    	DbHandler dbHandler = new DbHandler();
    	Sale sale = new Sale(dbHandler);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void startSale() {
    	
    }

    
    @Test
    void addItemValidID() {
    	int itemID = 2;
    	
    }
    
    @Test
    void addItemInvalidID() {
    	int itemID = 5;
    	
    }
    
    @Test
    void addItemWithQuantityValidID() {
    	int itemID = 2;
    	int quantity = 3;
    	
    }
    
    @Test
    void addItemWithQuantityInvalidID() {
    	int itemID = 5;
    	int quantity = 3;
    	
    }

    @Test
    void testAddItem() {
    }

    @Test
    void applyDiscount() {
    }

    @Test
    void endSale() {
    }

    @Test
    void enterPayment() {
    }
}