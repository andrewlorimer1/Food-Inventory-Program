import java.util.Formatter;
import java.util.Scanner;

/**
 * This class contains field members specific for meta and overrides methods made in the abstract class FoodItem
 * 
 * 
 * @author Andrew Lorimer
 * @version 1.0
 * @since 1.8
 */
public class Meat extends FoodItem {
	/**
	 * A string containing the name of the butcher shop
	 */
	private String butcherShop;

/**
 * Constructor for class meat
 */
	Meat(){}

	/**
	 * Method to print data members specific to butcher shop
	 * @return a string of all the objects properties
	 */
	@Override
	public String toString() {
		return (super.toString() + " butcher shop: " + butcherShop +"\n");

	}
	
	/**
	 * Method to output the food item to a text file 
	 * @param writer the output stream to a file 
	 */
	@Override
	public void outputItem(Formatter writer) {
	     //call the abstract class to add the attributes generic to all food items 

		super.outputItem(writer);
		writer.format("%s\n", butcherShop);
	}
	
	/**
	 * Method to add items specific to meat, calling the abstract class to add information general to all food items
	 * @param fromFile  indicates if the input is coming from a file
	 * @param keyboard the input stream used to add attribute values 
	 * @return a boolean value to indicate if adding was successful
	 */
	@Override
	boolean addItem(Scanner keyboard, boolean fromFile) {
		boolean isValid;
		//if from file dont prompt user
		if (fromFile ==true ) {
			// call the abstract class method to add items general to all food items
			super.addItem(keyboard,true);
			keyboard.nextLine();
			this.butcherShop=keyboard.nextLine();
			return true;}
		else {
		
		
		// call the abstract class method to add items general to all food items
		super.addItem(keyboard, false);
		  keyboard.nextLine();
		  //prompt user to enter butcher shop name and assign it to butcherShop var
		  do { 
				System.out.print("Enter the name of the butcher shop : ");
				this.butcherShop = keyboard.nextLine();
				
				if (butcherShop.isBlank()) {
					System.out.println("Invalid entry");
					isValid=false;
				}
				else isValid=true;
				}while (isValid==false); 
		      return true;
	       }
	}

}