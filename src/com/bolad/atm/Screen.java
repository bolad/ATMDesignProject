package com.bolad.atm;

public class Screen {
    
    //display message without a carriage return
    public void displayMessage(String message) {
        
        System.out.print(message);
        
    }
    
    //display message with a carriage return
    public void displayMessageLine(String message) {
        
        System.out.println(message);
        
    }
    
    public void displayDollarAmount (double amount) {
        
        System.out.printf("$%,.2f", amount);
        
    }
}
