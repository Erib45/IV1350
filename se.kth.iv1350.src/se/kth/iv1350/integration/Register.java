package se.kth.iv1350.integration;

/**
 * Register containing an amount of money.
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class Register {

    private int balance;
    
    /**
     * Class constructor 
     */
    public Register(){
    	balance = 10000;
    }
    /**
     * Update register balance
     * @param amount Descibes the amount of money to update the balance with.
     */
    public void updateBalance(float amount) {
    	this.balance += (int)amount;
    }

    public int getBalance() {
    	return this.balance;
    }
}
