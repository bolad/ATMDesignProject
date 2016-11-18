package com.bolad.atm;

//Instantiate ATM withdrawal transaction
public class Withdrawal extends Transaction {
    
    //attributes of class Withdrawal
    private int amount;
    private Keypad keypad; //reference to keypad
    private CashDispenser cashDispenser; //reference to cash dispenser
    private final static int CANCELED = 6; //Option to cancel
    
    public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,
    Keypad atmKeypad, CashDispenser atmCashDispenser) {
        
        //initialize superclasss variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }
    
        //display menu of amounts withdrawn and the option to cancel
        //return the chosen ammount or 0 if the user chooses to cancel
    private int displayMenuOfAmounts() {
            
            int userChoice = 0; //local variable to store return value
            
            //create screen object here cos screen has private access in Transaction
            Screen screen = getScreen();
            
            int[] amounts = {0, 20, 40, 60, 100, 200};
            
            while (userChoice == 0) {
                screen.displayMessageLine("\nWithdrawal Menu:");
                screen.displayMessageLine("1 - $20");
                screen.displayMessageLine("2 - $40");
                screen.displayMessageLine("3 - $60");
                screen.displayMessageLine("4 - $100");
                screen.displayMessageLine("5 - $200");
                screen.displayMessageLine("6 - Cancel Transaction");
                screen.displayMessageLine("\nChoose a withdrawal amount: ");
                
                int input = keypad.getInput();
                
                switch(input) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        userChoice = amounts[input]; //save user's choice
                        break;
                    case CANCELED:
                        userChoice = CANCELED;
                        break;
                    default: //user did not enter any value from 1-6
                        screen.displayMessageLine("\nInvalid selection. Try again!");
                }
                
            }
            return userChoice;
        }
    
    @Override
    public void execute() {
        
        boolean cashDispensed = false;//cash not dispensed yet
        double availableBalance;
        
        //get references to bankDatabase and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        do {
            amount = displayMenuOfAmounts();
            
            if (amount != CANCELED) {
                availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
                
                if (amount <= availableBalance){//enough money in account
                    
                    //check if cashdispenser has sufficient money
                    if (cashDispenser.isSufficientCashAvailable(amount)){
                        //update account to reflect withdrawal
                        bankDatabase.debit(getAccountNumber(), amount);
                        
                        cashDispenser.dispenseCash(amount);
                        cashDispensed = true; //cash was dispensed
                        
                        screen.displayMessageLine("\nYour cash has been dispensed.\nPlease take your cash now. ");
                        
                    }
                    else {
                        screen.displayMessageLine("\nInsufficient cash available in the ATM." +
                                "\n\nPlease choose a smaller amount");
                    }
                }
                else {
                    screen.displayMessageLine("\nInsufficient funds in your account." +
                            "\n\nPlease choose a smaller amount.");
                  
                }
            }
            else {
                screen.displayMessageLine("\nCanceling transaction...");
                return; //return to main menu
            }
            
        } while (!cashDispensed);
        
    }
    
}
