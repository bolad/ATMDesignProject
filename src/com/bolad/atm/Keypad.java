package com.bolad.atm;

import java.util.Scanner;

public class Keypad {
    
    private Scanner input; //reads data from the command line
    
    //no argument constructor initializing Scanner
    public Keypad() {
        
        input = new Scanner(System.in);
        
    }
    
    //get user input
    public int getInput() {
        
        return input.nextInt();
    }
    
}
