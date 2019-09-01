/* CS1101  Intro to Computer Science 
Instructor: Aguirre 

Comprehensive Lab 1 

By including my name below, I confirm that:
-	I am submitting my original work.
-	If I include code obtained from another source or I received help I am giving attribution to those sources as comments.
-	This submission does not incur in any academic dishonesty practice as described in the course syllabus.

//Diego Rodriguez (Peer Leader) - recieved helped with count varibles 

//Used codebeutify to format my code and improve readability SOURCE: https://codebeautify.org/javaviewer#

//Raquel Gonzalez (IA) - received helped with file check loop - if username and pin number are in users.txt 
Modified and submitted by: [Aaron Zambrano] 
*/
import java.io.File;
import java.util.Scanner;

public class minerBank {
 public static void main(String[] args) throws Exception {
  Scanner bank = new Scanner(System.in);
  boolean valid = true; //variable to indicate to exit or ask the user for username and password again
  int countExit = 0; //variable to exit the menu when countExit increments
  int count = 0; //variable to count the number of opportunites
  while (count < 3) {
   if (countExit != 0) {
    System.out.println("Goodbye :) Thanks for choosing minerBank!");
    break;
   }
   System.out.print("Enter username: ");
   String userName = bank.next(); //variable to store username
   System.out.print("Enter pin: ");
   int pinNum = bank.nextInt(); //variable to store pin number

   File myFile = new File("users.txt"); //variable to initialize the file
   Scanner scnr = new Scanner(myFile); // variable to initialize the scanner to read myFile

   String name = scnr.next(); //reads username from the first line on users.txt
   int password = scnr.nextInt(); //reads username from the first line on users.txt
   int checkingAccount = scnr.nextInt(); //reads username from the first line on users.txt
   int savingsAccount = scnr.nextInt(); //reads username from the first line on users.txt


   while (!userName.equals(name) && scnr.hasNextLine()) {
    name = scnr.next(); //reads username from the next line on users.txt
    password = scnr.nextInt(); //reads username from the next line on users.txt
    checkingAccount = scnr.nextInt(); //reads username from the next line on users.txt
    savingsAccount = scnr.nextInt(); //reads username from the next line on users.txt 

   } //end of file check while loop


   if (userName.equals(name) && pinNum == password) {
    // Display menu
    int userOption = 0;
    while (userOption != 5) {
     System.out.println("\t\t\t1. Check Balance");
     System.out.println("\t\t\t2. Withdraw Money");
     System.out.println("\t\t\t3. Deposit Money");
     System.out.println("\t\t\t4. Transfer Money");
     System.out.println("\t\t\t5. Exit");
     System.out.println("");
     System.out.print("Welcome to minerBank! Enter the menu option you want (1 to 5): ");
     userOption = bank.nextInt(); //variable to store menu options

     switch (userOption) {
      case 1:
       /*----------------------------------------------3.3-----------------------------------------------------------------------------*/
       System.out.print(("Select one of the following options:\n ") + ("\t\t1. Checking Account\n") + ("\t\t2. Savings Account\n"));
       userOption = bank.nextInt();

       if (userOption == 1) {
        System.out.println("Your current checking account is: $" + checkingAccount);
        System.out.println("");
       } else if (userOption == 2) {
        System.out.println("Your current savings account is: $" + savingsAccount);
        System.out.println("");
       } else {// if userOption is not 1 or 2
		userOption = 0;
        System.out.println("Sorry, I don't recognize that option under CHECK BALANCE. Please try again.");
        System.out.println("");
       }

       break;
      case 2:
       /*----------------------------------------------3.4------------------------------------------------------------------------------*/
       System.out.print(("Select one of the following options withdraw:\n ") + ("\t\t1. Checking Account\n") + ("\t\t2. Savings Account\n"));
       userOption = bank.nextInt();
       if (userOption == 1) ///////////////////////////////////////////////////////////checkingAccount
       {
        System.out.println("Enter the amount to withdraw (limit: $200) [YOUR CURRENT CHECKING BALANCE: $" + checkingAccount + "]: ");
        userOption = bank.nextInt();
        checkingAccount -= userOption;
        if (checkingAccount >= 0 && userOption <= 200) {
         System.out.println("Your current checking balance is: $" + checkingAccount);
		 userOption = 0;
         System.out.println("");
        } else {
         System.out.println("Sorry, You have passed the withdraw limit or have insufficent funds under your Savings account. Please try again.");
         checkingAccount += userOption;
		 userOption = 0;
         System.out.println("");
        }

       } else if (userOption == 2) //////////////////////////////////////////////////////savingsAccount
       {
        System.out.print("Enter the amount to withdraw (limit: $200) [YOUR CURRENT SAVINGS BALANCE: $" + savingsAccount + "]: ");
        userOption = bank.nextInt();
        savingsAccount -= userOption;
        if (savingsAccount >= 0 && userOption <= 200) {
         System.out.println("Your current savings balance is: $" + savingsAccount);
		 userOption = 0;
         System.out.println("");
        } else {
         System.out.println("Sorry, You have passed the withdraw limit or have insufficent funds under your Savings account. Please try again.");
         savingsAccount += userOption;
		 userOption = 0;
         System.out.println("");
        }
       } else { // if userOption is not 1 or 2
		userOption = 0;
        System.out.println(" Sorry, I don't recognize that option under WITHDRAW MONEY. Please try again.");
        System.out.println("");
       }
       break;
      case 3:
       /*----------------------------------------------3.5---------------------------------------------------------------------------------*/
       System.out.print(("Select one of the following options to deposit:\n ") + ("\t\t1. Checking Account\n") + ("\t\t2. Savings Account\n"));
       userOption = bank.nextInt();
       if (userOption == 1) { ///////////////////////////////////////////checkingAccount
        System.out.println("Enter the amount to deposit (limit: $1200) [YOUR CURRENT CHECKING BALANCE: $" + checkingAccount + "]: ");
        userOption = bank.nextInt();
        checkingAccount += userOption;
        if (userOption <= 1200) {
         System.out.println(" Your current checking balance is: $" + checkingAccount);
		 userOption = 0;
         System.out.println("");

        } else if (userOption > 1200) {
         System.out.println("Sorry, you cannot deposit more than $1200. Please try again");
         checkingAccount -= userOption;
		 userOption = 0;
         System.out.println("");
        }
       } else if (userOption == 2) { //////////////////////////////////////////savingsAccount
        System.out.println("Enter the amount to deposit (limit: $1200) [YOUR CURRENT SAVINGS BALANCE: $" + savingsAccount + "]: ");
        userOption = bank.nextInt();
        savingsAccount += userOption;
        if (userOption <= 1200) {
         System.out.println(" Your current savings balance is: $" + savingsAccount);
		 userOption = 0;
         System.out.println("");

        } else if (userOption > 1200) {
         System.out.println("Sorry, you cannot deposit more than $1200. Please try again");
         savingsAccount -= userOption;
		 userOption = 0;
         System.out.println("");
        }
       } else {// if userOption is not 1 or 2
	    userOption = 0;
        System.out.println("Sorry, I don't recognize that option under DEPOSIT MONEY. Please try again.");
        System.out.println("");
       }
       break;
      case 4:
       /*----------------------------------------------3.6-------------------------------------------------------------------------------------------*/
       System.out.print(("Select one of the following options to withdraw:\n ") + ("\t\t1. Checking Account\n") + ("\t\t2. Savings Account\n"));
       userOption = bank.nextInt();
       if (userOption == 1) { //////////////////////////////////////checkingAccount
        System.out.print("Enter the amount you would like to transfer to you savings account (limit: $200) [YOUR CURRENT CHECKING BALANCE: $" + checkingAccount + "]: ");
        userOption = bank.nextInt();
        checkingAccount -= userOption;
        if (checkingAccount >= 0 && userOption <= 200) {
         savingsAccount += userOption;
         System.out.println("TRANSFERRED to SAVINGS");
         System.out.println("Your current checking account is : $" + checkingAccount);
         System.out.println("Your current savings account is : $" + savingsAccount);
		 userOption = 0;
         System.out.println("");

        } else {
         checkingAccount += userOption;
         System.out.println("TRANSFER DENIED: You have passed the withdraw limit or have insufficent funds under your Savings account. Please try again.");
		 userOption = 0;
         System.out.println("");
        }
       } else if (userOption == 2) { //////////////////////////////////savingsAccount
        System.out.print("Enter the amount you would like to transfer to you checking account (limit: $200) [YOUR CURRENT SAVINGS BALANCE: $" + savingsAccount + "]: ");
        userOption = bank.nextInt();
        savingsAccount -= userOption;
        if (savingsAccount >= 0 && userOption <= 200) {
         checkingAccount += userOption;
         System.out.println("TRANSFERRED to CHECKINGS");
         System.out.println("Your current checking account is : $" + checkingAccount);
         System.out.println("Your current savings account is : $" + savingsAccount);
		 userOption = 0;
         System.out.println("");

        } else {
         savingsAccount += userOption;
         System.out.println("TRANSFER DENIED: You have passed the withdraw limit or have insufficent funds under your Savings account. Please try again.");
		 userOption = 0;
         System.out.println("");
        }
       } else {// if userOption is not 1 or 2
	    userOption = 0;
        System.out.println("Sorry, I don't recognize that option under TRANSFER MONEY. Please try again.");
        System.out.println("");
       }
       break;
      default:
       /*----------------------------------------------3.7---------------------------------------------------------*/
       if (userOption == 5) {
        break;
       } else {// if userOption is not 1-5
        System.out.println("Invalid option please try again");
       }
       break;
     }

    } //end of menu while loop

   } else if (count < 2) {
    System.out.println("Your username and/or password is not correct.");
    ++count;
    valid = false;
   } else {
    System.out.print("Your username and/or password is not correct. Goodbye!");
    break;
   }

   if (valid) {
    ++countExit; //increments countExit so that program can terminate from the menu (3.7)
   }
  } //end of count while loop
 }
}