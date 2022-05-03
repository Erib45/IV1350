package tests;

import integration.Register;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    Register register;

    @BeforeEach
    void setUp() {
        register = new Register();
        int balance = 500;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateBalanceTest() {
        register.updateBalance(50);
        int expected = 550;
        int actual = register.getBalance();
        assertEquals(expected, actual, "updateBalance() fail");
    }
    
    @Test
    @Disabled
    void getBalance() {
    }
}