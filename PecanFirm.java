/* CS1101 – Intro to Computer Science 
Instructor: Aguirre

Comprehensive Lab 3

By including my name below I confirm that:
-	I am submitting my original work.
-	If I include code obtained from another source or I received help I am giving attribution to those sources as comments.
-	This submission does not incur in any academic dishonesty practice as described in the course syllabus.
References
//Raquel Gonzalez (IA) - received helped with method (i) 
//Used codebeutify to format my code and improve readability SOURCE: https://codebeautify.org/javaviewer#


Modified and submitted by: [Aaron Zambrano] 
*/

import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
public class PecanFirm {

 public static double sizeOfFirm = 0.0; //
 private static Pecan[] tree;

 //(MAIN)
 public static void main(String[] args) {
  PecanFirm Firm = new PecanFirm();
  System.out.println("Welcome :)");
  Scanner opt = new Scanner(System.in);
  boolean ext = false;

  while (!ext) {
   System.out.println("Please Select an Option: \n");
   System.out.println("(A): Display the firm's yield per acre");
   System.out.println("(B): Display trees that require pruning");
   System.out.println("(C): Update tree information");
   System.out.println("(D): Find trees by a specific attribute");
   System.out.println("(E): Display all trees in firm information");
   System.out.println("(F): Exit");
   String choice = opt.nextLine();
   ext = mainMenu(choice);
  }
  System.out.println("See ya next time!");
  
  
 }
 /**
  * Constructor for PecanFirm
  */
 //Constructor (i)	
 public PecanFirm() {
  File infoFile = null;
  Scanner scanFile = null;
  System.out.println("Please insert your file: ");

  Scanner scnr = new Scanner(System.in);
  String file = scnr.nextLine();

  try {
   infoFile = new File(file);
   scanFile = new Scanner(infoFile);
  } catch (FileNotFoundException e) {
   System.out.print("File Not found. Please try again ");
  }

  int size = 0;
  while (scanFile.hasNextLine()) { //getting the "size" of the txt file
   size++;
   scanFile.nextLine();
  }

  try {
   scanFile = new Scanner(new File(file)); //re initalize the file scanner
  } catch (FileNotFoundException e) {}
  //////////////////////////////////////
  int treeID = 0;
  int treeAge = 0;
  boolean treeCondition = false;
  int treeSpread = 0;
  double treeYield = 0.0;
  //////////////////////////////////////
  tree = new Pecan[size]; //Tree array
  sizeOfFirm = scanFile.nextDouble(); //2.2 from txt file
  int i = 0;
  while (i < tree.length && scanFile.hasNextLine()) {
   String line = scanFile.nextLine(); //line 
   String[] lineArr = line.split(", "); // removing ", " from each line
   try {
    treeID = Integer.parseInt(lineArr[0]);
    treeAge = Integer.parseInt(lineArr[1]);

    if (lineArr[2].equals("No")) {
     lineArr[2] = "false";
    } else if (lineArr[2].equals("Yes")) {
     lineArr[2] = "true";
    }
    treeCondition = Boolean.parseBoolean(lineArr[2]);
    treeSpread = Integer.parseInt(lineArr[3]);
    treeYield = Double.parseDouble(lineArr[4]);
   } catch (NumberFormatException e) {}
   Pecan newTree = new Pecan(treeID, treeAge, treeCondition, treeSpread, treeYield);
   tree[i] = newTree;
   i++;
  } //end of object creator while loop

 } // end of PecanFirm()
 
 /**
  * This method returns the yield per acre of the firm.
  * @return sum / sizeOf firm: the yield per acre of the firm.
  */
 //(ii)
 public static double yieldPerAcre() {
  double sum = 0.0;
  double avg = 0.0;
  for (int i = tree.length - 1; i != 0; i--) {
   sum += tree[i].getYield();
  }
	  avg = sum / sizeOfFirm;
	  try {
		  if(avg == Double.POSITIVE_INFINITY)
			  throw new ArithmeticException("DIVISION BY ZERO");
	  }
	  catch(ArithmeticException e) {
		  System.out.println("Size of Firm is 0.0");
		  return -1;
	  }
  return avg;
 } //end of yieldPerAcre()
 
 /**
  * This method returns a boolean to see if current tree needs pruning
  */
 //(iii)
 public static void needPruning() {
  int i = 0;

  for (i = 1; i < tree.length; i++) {
   if (tree[i].needsPruning(tree[i].getSpread())) {
    System.out.println("Tree " + tree[i].getID() + " requires pruning");
   }
  }
 } //end of needPruning()
 
 /**
  * This method updates any tree attribute except ID's
  * @param id ID of a tree
  */
 //(iv)
 public static void updateTree(int id) {
  int i = 0;
  Scanner user = new Scanner(System.in);

  for (i = 1; i < tree.length; i++) {
   if (tree[i].getID() == id)
    break;
  }

  System.out.println("Select which option to update the current tree information:");
  System.out.println("(A): Update the age of the tree");
  System.out.println("(B): Update the condition of the tree");
  System.out.println("(C): Update the spread of the tree");
  System.out.println("(D): Update the yield of the tree");
  System.out.println("(E): Back to Menu");
  String input = user.nextLine();
  switch (input.toUpperCase()) {
   case "A":
    System.out.print("Insert you new age: ");
    int nAge = user.nextInt();
    //updates tree age
    tree[i].setAge(nAge);
    break;
   case "B":
    System.out.println("Insert you new condition (Yes or No) : ");
    String nCon = user.nextLine();
    //updates tree condition
    if ("No".equalsIgnoreCase(nCon)) {
     nCon = "false";
    } else if ("Yes".equalsIgnoreCase(nCon)) {
     nCon = "true";
    } else {
     System.out.println("Invalid conditon\n");
     break;
    }
    Boolean condition = Boolean.parseBoolean(nCon);
    tree[i].setIsHealthy(condition);
    break;
   case "C":
    System.out.print("Insert your new spread: ");
    int nSpread = user.nextInt();
    tree[i].setSpread(nSpread);
    break;
   case "D":
    System.out.print("Insert your new Yield: ");
    double nYield = user.nextDouble();
    tree[i].setYield(nYield);
    break;
   case "E":
    break;
  } //end of switch

  System.out.println("CURRENT TREE CONDITIONS\n");
  System.out.println(tree[i].toString()); //prints conditions of current tree
 } // end of updateTree()

 ////////////////////////////////////////////(v)
 /**
  * This method helps the user find trees by yields.
  * @param  yield //User yield to compare to all tree yields of firm that are greater or equal to 80
  */
 public static void findByYield(double yield) {
  boolean valid = false;
  int i = 0;
  System.out.println("Trees by a yield greater or equal to : " + yield + "lb");
  for (i = 1; i < tree.length; i++) {
   if (tree[i].getYield() >= yield) {
    System.out.println("Tree: " + tree[i].getID() + " Yield: " + tree[i].getYield());
    valid = true;
   }
  }
  if (!valid)
   System.out.println("No Tree yield greater than " + yield);
 }
 /**
  *This method helps the user find trees by spread.
  * @param spread //User spread to compare to all tree spreads of the firm are greater or equal to 80
  */
 public static void findBySpread(int spread) {
  boolean valid = false;
  int i = 0;
  System.out.println("Trees by a spread greater or equal to: " + spread + "\"");
  for (i = 1; i < tree.length; i++) {
   if (tree[i].getSpread() >= spread) {
    System.out.println("Tree: " + tree[i].getID() + " Spread: " + tree[i].getSpread());
    valid = true;
   }
  }
  if (!valid)
   System.out.println("No Tree yield greater than " + spread);

 }
 /**
  * This method prints all tree ID who have healthy conditions (true).
  */
 public static void printHealthy() {
  boolean valid = false;
  int i = 0;
  System.out.println("All Healthy Trees:");
  for (i = 1; i < tree.length; i++) {
   if (tree[i].getIsHealthy()) {
    System.out.println("Tree: " + tree[i].getID());
    valid = true;
   }
  }
  if (!valid)
   System.out.println("No healthy trees available ");
 }
 /**
  * This method prints all tree ID who have Unhealthy conditions (false).
  */
 public static void printUnHealthy() {
  boolean valid = false;
  int i = 0;
  System.out.println("All Unhealthy Trees:");
  for (i = 1; i < tree.length; i++) {
   if (!tree[i].getIsHealthy()) {
    System.out.println("Tree: " + tree[i].getID());
    valid = true;
   }
  }
  if (!valid)
   System.out.println("No Unhealthy trees :) ");
 }
 ////////////////////////////////////////////(v)

 //(vi)
 /**
  * This method prints all tree attributes.
  */
 public static void printTrees() {
  System.out.println("Trees in Firm:");
  int i = 0;
  for (i = 1; i < tree.length; i++) {
   System.out.println(tree[i].toString());
  }
 }
 /**
  * @param choice //User choice (A-F) from menu selections
  * @return boolean true if choice was not F. false if choice was F
  */
 /////////menu
 public static boolean mainMenu(String choice) {
  Scanner opt = new Scanner(System.in);
  try {
   switch (choice.toUpperCase()) {
    case "A":
     double x = yieldPerAcre();
     System.out.printf("%.2f\n\n",x);
     break;
    case "B":
     needPruning();
     break;
    case "C":
     System.out.println("Insert ID: ");
     int tID = opt.nextInt();
     updateTree(tID); // goes to updateTree menu
     break;
    case "D":
     System.out.println("Which type do you want to find by?\n");
     System.out.println("(1) By yield");
     System.out.println("(2) By spread");
     System.out.println("(3) By healthiness");
     System.out.println("(4) By Unhealthiness");
     int user = opt.nextInt();
     if (user == 1) {
      System.out.println("Enter yield:");
      double y = opt.nextDouble();
      findByYield(y);
     }
     if (user == 2) {
      System.out.println("Enter spread:");
      int s = opt.nextInt();
      findBySpread(s);
     }
     if (user == 3) {
      printHealthy();
     }
     if (user == 4) {
      printUnHealthy();
     }
     if (user < 0 || user >= 5)
      System.out.println("Invalid choice");
     break;
    case "E":
     printTrees();
     break;
    case "F":
     return true;
   }
   return false;
  } // end of menu switch
  catch (InputMismatchException e) {
   System.out.println("Invalid input\n");
   return false;
  }
 }

} //end of class file