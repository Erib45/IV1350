package se.kth.iv1350.integration;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    Register register;

    @BeforeEach
    void setUp() {
        register = new Register();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateBalanceTest() {
        register.updateBalance(50);
        int expected = 10050;
        int actual = register.getBalance();
        assertEquals(expected, actual, "Balance was updated incorrectly");
    }
    

}