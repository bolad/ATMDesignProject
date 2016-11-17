package com.bolad.atm;


public class CashDispenser {
    
    //default initail number of bills in the cash dispenser
    private final static int INITIAL_COUNT = 500;
    private int count; // number of $20 bills remaining
    
    //no-argument constructor initializes count to default
    public CashDispenser() {
        
        count = INITIAL_COUNT;
    }
    
    public void dispenseCash(int amount) {
        
        int billsRequired = amount / 20; //number of $20 bills required
        count -= billsRequired; //update count of bills
        
    }
    
    public boolean isSufficientCashAvailable(int amount) {
        
        int billsRequired = amount / 20;
        
        if (count >= billsRequired){
            return true;
        }
        else{
            return false;
        }
    }
}
