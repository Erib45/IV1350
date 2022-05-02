package integration;

public class Register {

    private int balance;
    
    /**
     * Class constructor 
     */
    public Register(){
    	balance = 500;
    }
    /**
     * Update register balance
     * @param amount
     */
    public void updateBalance(float amount) {
    	this.balance += (int)amount;
    }

    public int getBalance() {
    	return this.balance;
    }
}
