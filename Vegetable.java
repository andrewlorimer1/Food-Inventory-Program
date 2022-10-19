import java.util.Formatter;
import java.util.Scanner;

/**
 * This class contains field members specific for vegetable and overrides
 * methods made in the abstract class FoodItem

 * 
 * @author Andrew Lorimer
 * @version 1.0
 * @since 1.8
 */
public class Vegetable extends FoodItem {
	/**
	 * A String containing the farm name associated with the
	 */
	private String farmName;

	/**
	 * Public constructor for vegetable class
	 */
	Vegetable() {}

	/**
	 * This method prints the data members specific to vegetables.
	 * @return a string of all the objects properties
	 */
	@Override
	public String toString() {
		//return the built string to the calling method
		return (super.toString() + " farm supplier: " + farmName + "\n");
	}
	
	/**
	 * Method to output the food item to a text file 
	 * @param writer the output stream to a file 
	 */
	@Override
	public void outputItem(Formatter writer) {
	     //call the abstract class to add the attributes generic to all food items 
		super.outputItem(writer);
		 //write the attribute specific to vegetable to the file
		writer.format("%s\n", farmName);
	}
	
	/**
	 * This method that allows you to add items specific to vegetable class
	 * @param fromFile  indicates if the input is coming from a file
	 * @param keyboard the input stream for adding item attributes
	 * @return a boolean value to indicate if adding was successful
	 */
	@Override
	boolean addItem(Scanner keyboard , boolean fromFile) {
		boolean isValid = false;
		//if from file dont prompt the user for input
		if (fromFile ==true ) {
			//call the abstract class to add generic attributes of Food Item
			super.addItem(keyboard,true);
			keyboard.nextLine();
			//add the attribute specific to Vegetables
			this.farmName=keyboard.nextLine();
			return true;}
		//if not from a file execute this chunk
		else {
		// call the abstract class method to add items general to all food items
		super.addItem(keyboard, false);
		keyboard.nextLine();
		//prompt user to enter orchard supplier name and assign it to farmName var
		do { 
		System.out.print("Enter the farm supplier : ");
		this.farmName = keyboard.nextLine();
		//if farm name is blank trap user in do while 
		if (farmName.isBlank()) {
			System.out.println("Invalid entry");
			isValid=false;
		}
		else isValid=true;
		}while (isValid==false); 
		return true;
		}
	}
}


