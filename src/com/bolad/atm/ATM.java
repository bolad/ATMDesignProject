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
            //loop while user is not yet authenticated
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser();
            }
            performTransactions(); //user authenticated
            userAuthenticated = false; //reset before next ATM session
            currentAccountNumber = 0; //reset before next ATM session
            screen.displayMessageLine("\nThank you! Goodbye!");
            
        }
        
    }
    //authenticate user against database
    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); //input account number
        screen.displayMessage("\nEnter your PIN: ");
        int pin = keypad.getInput();
        
        //set userAuthenticated to boolean value returned by database
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
        
        //check whether authentication suceeded
        if (userAuthenticated) {
            currentAccountNumber = accountNumber; //save user's account number
        }
        else {
            screen.displayMessageLine("Invalid account number or PIN. Please try again.");
        }
    }
    
    //display the main menu and perform transactions
    private void performTransactions() {
        //local variable to store transaction currently being processed
        Transaction currentTransaction = null;
        boolean userExited = false; //user has not chosen to exit
        
        while (!userExited) {
            
            //show main menu and get user's menu selection
            int mainMenuSelection = displayMainMenu();
            
            //decide how to proceed based on user's menu selection
            switch (mainMenuSelection) {
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                
                    //initialize as new object of chosen type
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                case EXIT:
                    screen.displayMessageLine("\nExiting the system...");
                    userExited = true;
                    break;
                default: //user did not enter an integer from 1-4
                    screen.displayMessageLine("\nYou did not enter a valid selection. Try again!");
                    break;
            }
        }
    }

    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        
        return keypad.getInput(); //return user's selection
    }

    //return object of specified Transaction subclass
    private Transaction createTransaction(int type) {
        
        Transaction temp = null; //temporary Transaction variable
        
        //determine which type of transaction to create
        switch (type){
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
                break;
        }
        return temp;
    }
    
}
