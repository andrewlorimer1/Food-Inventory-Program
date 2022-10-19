
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents the inventory for all food items, it contains methods
 * for updating amounts of food, adding items to inventory, reading food items from a text file,
 * saving food items to a text file. Searching for an item by item code and printing the inventory contents
 * 
 * 
 * @author Andrew Lorimer
 * @version 1.0
 * @since 1.8
 */
public class Inventory {
	/**
	 * Stores an array list of Food Items.
	 */
	private ArrayList<FoodItem> inventory = new ArrayList<>();

	/**
	 * Stores the number of items currently in the inventory
	 */
	private int numItems;

	/**
	 * Public constructor for class Inventory
	 */
	 Inventory() {
		this.numItems = 0;
	}
	/**
	 * Method to print all the Food Items's fields in the inventory array
	 * @return a string of all the objects properties
	 */
	@Override
	public String toString() {
        //intialize local variables
		String str = "Inventory:\n";
		int i = 0;
		// loop through entire inventory printing the data members
		for (i = 0; i < numItems; i++)
			if (inventory.get(i) != null)   str = str + inventory.get(i).toString();
		//return string to print inventory for user
		return str;
	}

	/**
	 * Method to determine if an Food Item's inventory code already exists,
	 * returning the index if it already exists
	 * 
	 * @param item The item being checked to see if it already exists
	 * @return the index of the food item
	 */
	int alreadyExists(FoodItem item) {

		int code = item.itemCode;
		// loop across entire inventory and check if item code is in inventory
		try {
			for (int i = 0; i < numItems; i++) {
				//return item code index if found
				if (code == inventory.get(i).itemCode) return i;
			}
		//catch case where index is not found
		} catch (NullPointerException npe) {
			return -1;
        }
        //if not found return -1 and print error message in calling method
		return -1;
	}

	/**
	 * Method to add items into the inventory array
	 * @param fromFile Indicates if we are adding a food item from a text file
	 * @param keyboard User input stream used to declare food item's field values
	 * @return a boolean to determine if addition of an item was succesful
	 */
	boolean addItem(Scanner keyboard, boolean fromFile) {
		//initialize local variables
        boolean endOfFile = false;
	    boolean isValid = true;
    	boolean validInput = false;
	    String option; 
		FoodItem foodItem=null;
		//wrap code in do while for reading from file to add all file food items
		do {	
		isValid=true;
		//check if we are reading from a text file, if true do not display prompts for users
		if( !fromFile ) System.out.println("Do you wish to add a fruit(f), vegetable(v), preserve(p) or a meat(m)");					
	        //use case switch to select the Food item type based on input 
			option=keyboard.nextLine();
			switch (option) {
			 // if f is selected, create an instance of fruit and add to inventory
			case "f":
				validInput = true;
				foodItem = new Fruit();
				 //add to inventory 
			     inventory.add(foodItem);
			break; 
			
			  // if v is selected, create an instance of vegetable
			case "v":
				validInput=true;
				foodItem = new Vegetable();
			    inventory.add(foodItem);
			    break;
			// if p is selected, create an instance of preserve
			case "p":
				validInput=true;
			    foodItem = new Preserve();
			      inventory.add(foodItem);				
			break;
			// if m is selected, create a new meat object
			case "m":
				validInput=true;
				foodItem  = new Meat();
			    inventory.add(foodItem);
		       break;
			//if none of the other cases are selected continue to end of loop 
			default: 
				isValid = false;
				System.out.println("Invalid food item");
		       continue;
			}	
			//if from file execute this version 
			if( fromFile ) {
				validInput = foodItem.inputCode(keyboard,true);
				}else {
					validInput = foodItem.inputCode(keyboard,false);
				}
			//check if item code already exists
				if (alreadyExists(foodItem) == -1) validInput = true; 
			    else { System.out.println("Item code already exists");
					   validInput=false;
					   break;
					}
			// increment numItems each time a food item is added
			if(validInput==true) {
			numItems++;
			//add the remaining food item attributes
			foodItem.addItem(keyboard, fromFile);
			//after an item is added, sort the items based on item code using a comparator
			Collections.sort(inventory, new ItemCodeComparator());
			//check to see if there is more lines to read in the file 
			if(fromFile && keyboard.hasNextLine()) endOfFile =false;
		    else endOfFile=true;
			}
			//if valid input is not true break out of method 
			else { return false;}
			//if not at end of file loop back to top
			}while( endOfFile ==false || isValid ==false );
			return true;
		}
/**
 * Method to add FoodItems by reading a text file 
 * @param keyboard User input stream used to identify which file to read from 
 */
	public void readFromFile (Scanner keyboard ) {
		boolean isValid = true;
		keyboard.nextLine();
		do {
			//prompt user to enter file name 
			System.out.println("Enter name of the file to read from");
			String fileName = keyboard.nextLine();
			// open file
			try {
				File file = new File(fileName);
				Scanner readFile = new Scanner(file);
			//pass input stream to method item and indicate it is from a text file
				addItem(readFile, true);
				isValid=true;
				readFile.close();
			//catch instance where file is not found
			} catch (FileNotFoundException e1) {
				System.out.println("Please enter a file that exists!");
				break;
			} 
		} while (isValid == false);
	}
	
	/**
	 * Method to update the amount of an item in the inventory array list. This method
	 * will buy or sell depending on the value of buyOrSell
	 * 
	 * @param keyboard  the input stream for declaring amount
	 * @param buyOrSell determines whether the method adds or subtracts qauntities
	 *                  from the inventory
	 * @return if valid quantities and item code return true
	 */
	boolean updateQauntity(Scanner keyboard, boolean buyOrSell) {
		int amount;
		int index;
		boolean isValid;
		// check to see if there are any items to buy or sell
		if (numItems == 0) return false;
		
		// make random instance of food item
		FoodItem item = new Vegetable();
		// input code item, if it is not valid exit method 
		isValid = item.inputCode(keyboard,false);
		if ( !isValid || !item.equals(item) ) {return false;}
		
		// see if item code already exists
		index = alreadyExists(item);
		// if code doesn't exist -1 is returned from method alreadyExists()
		if (index < 0) {
			System.out.println("Code entered is not in inventory");
			return false;
		}
		// case for buying a food item
		if (buyOrSell) {
			System.out.println("Please enter amount to buy");
		}else {	System.out.println("Please enter amount to sell");}
			//ask user how much they want to buy, if negative print error
			try {
				amount = keyboard.nextInt();
				if (amount < 0) {
					System.out.println("Invalid Quantity");
				}
           // catch letters typed into input stream
			} catch (InputMismatchException ime) {
				System.out.println("Not a valid number ");
				return false;
			}
			//Update item if no errors have occured, determine if buying or selling before adding
			if (buyOrSell) inventory.get(index).updateItem(amount);
	        if (!buyOrSell) inventory.get(index).updateItem(-amount);
				return true;
	}
	
	/**
	 * Method for searching an food item based off of item code 
	 * @param keyboard the input stream used for declaring the item code to search 
	 */
	public void searchForItem(Scanner keyboard) {
		//declare variables needed for using helper method binary search 
		int index;
		int high = inventory.size();
		int low =0;
		int codeToSearch=0;
		//prompt user for item code to search
		try {
		System.out.println("Please enter the code for the item");
	     codeToSearch = keyboard.nextInt();
		}catch(InputMismatchException ime ) {}
	    //call helper method binary search to determine the index of the item code if it exists
		index = binarySearch(codeToSearch, inventory, low, high );
		if (index <= -1 ) {
			System.out.println("Code not found in inventory");
		}else {
			//if item code is found display details for that food item 
		   System.out.println(inventory.get(index).toString());
		}
	}
	/**
	 * Method to save an inventory to a file 
	 * @param keyboard the input stream used to make the file name
	 */
	public void saveToFile(Scanner keyboard) {
        //prompt user for file name to save to
		System.out.println("Please enter file name to save to");
		keyboard.nextLine();
		String fileName = keyboard.nextLine();
		try {
			// create an output stream
			Formatter fileOutput = new Formatter(fileName);
			//loop across entire inventory if index is an instance of a food item write the first letter
			for (int i = 0; i < numItems; i++) {
			if (inventory.get(i) instanceof Fruit) fileOutput.format("%s\n","f");
			if(inventory.get(i) instanceof Vegetable) fileOutput.format("%s\n", "v");
			if(inventory.get(i) instanceof Preserve) fileOutput.format("%s\n", "p");
			if(inventory.get(i) instanceof Meat) fileOutput.format("%s\n", "m");
		     //add remaining food item attributes 
				inventory.get(i).outputItem(fileOutput);
			}
			// close output stream
			fileOutput.close();
         // catch unexpected output errors
		} catch (IOException ioe) {
			System.err.println("Cannot have blank file name");
		}

	}
	/**
	 * Method for performing a binary search on an Array List to find an item code
	 * @param itemCode the item code that will be searched
	 * @param inventory the arraylist containing all food items holding item codes
	 * @param low the lowest index to search
	 * @param high the highest index to search
	 * @return the index where the item code was found 
	 */
private int binarySearch(int itemCode, ArrayList<FoodItem> inventory, int low, int high) {
		//determine middle element for the array list
		int mid = (low + high) / 2;
        try {
		//if the number is the middle, low or high index print the element 
		if (inventory.get(mid).itemCode == itemCode) {
			return mid;
		}
		if (inventory.get(low).itemCode == itemCode) {
			return low;
		}
		//case so when the number cannot be found in the array list, end method to prevent stack over flow 
		if (low == high) {
			return -1;
		}
		if (inventory.get(high - 1).itemCode == itemCode) {
			return (high-1);
		}
        }catch (IndexOutOfBoundsException ioobe) {
        	return -1;
        }
        // check if the search number is less than the middle element 
		if (itemCode < inventory.get(mid).itemCode) {
			// if the middle element is greater than search number, search left side of
			// ordered array by setting high to mid-1
			return binarySearch(itemCode, inventory, low, mid - 1);
		}

		// if the middle element is smaller than the search number
		else {
			return binarySearch(itemCode, inventory, mid + 1, high);

		}	
    }
}

