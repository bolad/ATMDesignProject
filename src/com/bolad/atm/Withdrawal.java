package com.bolad.atm;

//Instantiate ATM withdrawal transaction
public class Withdrawal extends Transaction {
    
    //attributes of class Withdrawal
    private double amount;
    private Keypad keypad; //reference to keypad
    private CashDispenser cashDispenser; //reference to cash dispenser
    
    
    public Withdrawal() {
        
    }
    @Override
    public void execute() {
        
    }
    
}
