import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the abstract class that child classes vegetable, fruit, meat and preserve override. It provides methods present in all child classes
 * 

 * @author Andrew Lorimer
 * @version 1.0
 * @since 1.8
 */
public abstract class FoodItem {
/** 
 * Store the itemCode for this food item
 */
	protected int itemCode ;
	/**
	 * Stores the item name for the food item
	 */
	private String itemName = "";
	/**
	 * Store the item price for this food item
	 */
	private float itemPrice;
	/**
	 * Stores the amount of food items in stock
	 */
	private int itemQuantityInStock;
	/**
	 * Stores the item cost for the item 
	 */
	private float itemCost;

	/**
	 Public constructor for the food item class
	 */
	FoodItem() {};


	/**
	 * Method for creating a string containing all of the class's data members
	 * @return a string of all the objects properties
	 */
	@Override
	public String toString() {
      //creates a string of all food item attributes
		String str = ("Item " + itemCode + " " + itemName + " " + itemQuantityInStock + " price: $" + itemPrice
				+ " cost: $" + itemCost);
		//return string to the method that called it
		return str;
	}

	/**
	 * Method to update the quantity of a food item
	 * @param amount the amount being added or subtracted
	 * @return true or false depending on if the operation was successful
	 */
	boolean updateItem(int amount) {
		// ensure we don't have negative amount
		if (0 > amount + itemQuantityInStock) {
			System.out.println("Insufficient stock in inventory");
			return false;
		}
		//add the amount to the quantity
		itemQuantityInStock = itemQuantityInStock + amount;
		//return true if update was successful
		return true;
	}

/**
 * Method for determining if this item has the correct item code
 * @param item the Food item being acted on 
 * @return true or false if this item code is the same item code being acted on
 */
	boolean isEqual(FoodItem item) {
    //return if this objects item code is equal to the item code
		return item.itemCode == itemCode;

	}
/**
 * An instance of inventory to be used in class FoodItem
 */
	Inventory inventory = new Inventory();

	/**
	 * Method for adding values for the properties of every food item
	 * @param keyboard the input stream for changing values
	 * @param fromFile indicates if the input is coming from a file 
	 * @return a boolean if the method was successful 
	 */
	boolean addItem(Scanner keyboard, boolean fromFile) {
	    //check if input is from a text file
		if (fromFile ==true ) {
	        //if from text file directly add all the food item attributes
			keyboard.nextLine();
			itemName=keyboard.nextLine();
			itemQuantityInStock=keyboard.nextInt();
			itemCost=keyboard.nextFloat();
			itemPrice=keyboard.nextFloat();
		}
		//if not from file prompt user for input
		else { 
        //Initialize boolean so that user is trapped until good input is used
		boolean goodInput = false;
	    //prompt user for item name
		while (goodInput == false) {
			System.out.print("Enter the name for the item:");
			try {
				goodInput = true;
				itemName = keyboard.nextLine();
				if(itemName.isBlank()) {
					System.out.println("Invalid entry");
					goodInput=false;
				}
			//catch bad input 
			} catch (InputMismatchException ime) {
				keyboard.nextLine();
				System.out.println("Invalid entry");
				goodInput = false;
			}
		}
		//prompt user for item quantity and reset the boolean variable
		goodInput = false;
		while (goodInput == false) {
			System.out.print("Enter the quantity for the item:");
			try {
				goodInput = true;
				itemQuantityInStock = keyboard.nextInt();
				//check if a negative quantity is typed in 
				if (itemQuantityInStock < 0) {
					System.out.println("Invalid entry");
					goodInput = false;
				}
			//catch bad input 
			} catch (InputMismatchException ime) {
				keyboard.nextLine();
				System.out.println("Invalid entry");
				goodInput = false;
			}
       	}
		//prompt user for item cost
		goodInput = false;
		while (goodInput == false) {
			System.out.print("Enter the cost of the item:");
			try {
				goodInput = true;
				itemCost = keyboard.nextFloat();
				//check if negative cost entered
				if (itemCost <= 0 ) {
					System.out.println("Invalid entry");
					goodInput=false;
				}
			//catch letters as input for cost
			} catch (InputMismatchException ime) {
				keyboard.nextLine();
				System.out.println("Invalid entry");
				goodInput = false;
			}
		}
		//prompt user for sales price of item 
		goodInput = false;
		while (goodInput == false) {
			System.out.print("Enter the sales price of the item:");
			try {
				goodInput = true;
				itemPrice = keyboard.nextFloat();
				//catch case where user enter negative sales price
			    if (itemPrice <= 0 ) { 
			    	System.out.println("Invalid entry");
			    	goodInput = false;
			    }
			   //catch letters in the input stream
			} catch (InputMismatchException ime) {
				keyboard.nextLine();
				System.out.println("Invalid entry");
				goodInput = false;
			}
		}   
	}//return true because we force user to enter good input
		return true;
}
	/**
	 * This method gets the item code for 
	 * @return the item code the the food item 
	 */
	public int getItemCode( ) {
		//return the item code 
	return  itemCode;
	}
	
	/**
	 * Method to output food item attributes to a designated text file
	 * @param writer The Output stream
	 */
	public void outputItem(Formatter writer ) {
		//In order write all the food item data members to a text file
		writer.format("%d\n", getItemCode() );
		writer.format("%s\n", itemName );
		writer.format("%s\n", itemQuantityInStock );
		writer.format("%.2f\n", itemCost );
		writer.format("%.2f\n", itemPrice);		
	}

	/**
	 * Method for inputing item codes for food items, can read from text file or manually enter code
	 * @param keyboard the input stream for reading in the item name
	 * @param fromFile indicates if the input stream is coming from a file 
	 * @return if the input is a valid code
	 */
	boolean inputCode(Scanner keyboard, boolean fromFile) {
		boolean isValid = true;
		//if from file don't prompt user and set item code to the input stream
		if(fromFile) {
			try {
			  itemCode = keyboard.nextInt();
			  //catch bad input 
			} catch (InputMismatchException ime) {
				keyboard.nextLine();
				System.out.println("Invalid input");
				isValid = false;
			}
			return true;
		}
		//if not from a file prompt user to enter item code number
		else {
		do {
			System.out.print("Enter the code for the item:");
			try {
				//set user input to the item code
				this.itemCode = keyboard.nextInt();
				keyboard.nextLine();
				return true;
				//catch letters in the input stream and trap user in do while
			} catch (InputMismatchException ime) {
				keyboard.nextLine();
				System.out.println("Invalid input");
				isValid = false;}
		} while (isValid == false);
		//if code is valid return true
		  return true;
	}
  }
}