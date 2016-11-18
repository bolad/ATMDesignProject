package com.bolad.atm;

public class Deposit extends Transaction{
    
    private double amount;
    private Keypad keypad;
    private DepositSlot depositSlot;
    private final static int CANCELED = 0;
    
    public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmbankDatabase,
            Keypad atmKeypad, DepositSlot atmDepositSlot) {
        super(userAccountNumber, atmScreen,atmbankDatabase);
        
        //get reference to keypad and deposit slot
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    }
        
    public double promptForDepositAmount() {
            
        Screen screen = getScreen();
            
        screen.displayMessage("\nPlease enter a deposit amount in " + 
                    "CENTS (or 0 to cancel): ");
        int input = keypad.getInput();
            
        if (input == CANCELED){
            return CANCELED;
        }
        else{
            return (double) input / 100; //return dollar amount
        }
    }
        
    @Override
    public void execute() {
        
        BankDatabase bankDatabase = getBankDatabase(); //get reference
        Screen screen = getScreen(); //get reference
        
        amount = promptForDepositAmount();
        
        if (amount != CANCELED){
            screen.displayMessage("\nPlease insert a deposit envelope containing ");
            screen.displayDollarAmount(amount);
            screen.displayMessageLine(".");
            
            boolean envelopeReceived = depositSlot.isEnvelopeReceived();
            
            if(envelopeReceived){
                screen.displayMessageLine("\nYour envelope has been " + "received.\nNote:"
                        + "The money just deposited will not be availabe until we verify the"
                        + "amount of any enclosed cash and your checks clear.");
                
                //credit account to reflect deposit
                bankDatabase.credit(getAccountNumber(), amount);
            }
            else{
                screen.displayMessageLine("\nYou did not insert an envelope, so the ATM"
                        + "has canceled your transaction.");
            }
        }
        else{
            screen.displayMessageLine("\nCanceling transaction...");
        }
        
    }
    
}
