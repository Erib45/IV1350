package integration;

public class Register {

    private int balance;

    public Register(){
    	balance = 500;
    }
    
    public void updateBalance(float amount) {
    	this.balance += (int)amount;
    }
    
    public int getBalance() {
    	return this.balance;
    }
}
