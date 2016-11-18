package com.bolad.atm;

public class BalanceInquiry extends Transaction {
    
    public BalanceInquiry(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        
        super (userAccountNumber, atmScreen, atmBankDatabase);
        
    }
    
    @Override
    public void execute() {
        
    //get references to bankDatabase and Screen
    BankDatabase bankDatabase = getBankDatabase();
    Screen screen = getScreen();
    
    //get the available balance for the account involved
    double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
    
    //get the total balance 
    double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
    
    //display balance info on the screen
    screen.displayMessageLine("/nBalance Information:");
    screen.displayMessage(" - Available Balance: ");
    screen.displayDollarAmount(availableBalance);
    screen.displayMessage("\n - Total Balance:     ");
    screen.displayDollarAmount(totalBalance);
    screen.displayMessageLine("");
        
    }
    
}
