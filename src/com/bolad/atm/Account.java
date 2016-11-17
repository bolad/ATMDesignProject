package com.bolad.atm;

public class Account {
    
    //account attributes
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;
    
    
    //account constructor initializing attributes
    public Account(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
        
    }
    
    //determine whether pin specified by user is valid
    public boolean validatePIN(int userPIN) {
        if (userPIN == pin) {
            return true;
        }
        else{
            return false;
        }
    }
    
    public double getAvailableBalance() {
        
        return availableBalance;
        
    }
    
    public double getTotalbalance() {
        
        return totalBalance;
        
    }
    
    public void credit(double amount) {
        
        totalBalance += amount;
        
    }
    
    public void debit(double amount) {
        
        availableBalance -= amount;
        totalBalance -= amount;
        
    }
    
    public int getAccountNumber() {
        
        return accountNumber;
        
    }
}
