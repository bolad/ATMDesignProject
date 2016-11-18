package com.bolad.atm;


public class ATM {
    
    //ATM's components
    private boolean userAuthenticated; //whether user is authenticated
    private int currentAccountNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase;
    
    //constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;
    
    //no argument ATM constructor which initializes instance variables
    public ATM() {
        userAuthenticated = false; //before user enters correct info
        currentAccountNumber = 0; //before user enters correct account number
        screen = new Screen(); //create screen
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();
    }
    
    //start ATM
    public void run() {
        
        //welcome and authenticate user
        while (true) {
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
            }
            
        }
        
    }
}
