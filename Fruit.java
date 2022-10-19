import java.util.Formatter;
import java.util.Scanner;

/**
 * This class contains field members specific for fruit and overrides methods
 * made in the abstract class FoodItem
 * 
 * 
 * @author Andrew Lorimer
 * @version 1.0
 * @since 1.8
 */
public class Fruit extends FoodItem {

	/**
	 * A string containing the name of an orchard
	 */
	private String orchardName;

	/**
	 * This method prints the data members for fruit
	 * @return a string of all the objects properties
	 */
	@Override
	public String toString() {
		return (super.toString() + " orchard supplier:" + orchardName + "\n");

	}
	
	/**
	 * Method to output the food item to a text file 
	 * @param writer the output stream used to write to a file
	 */
	@Override
	public void outputItem(Formatter writer) {
     //call the abstract class to add the attributes generic to all food items 
		super.outputItem(writer);
	 //write the attribute specific to fruit to the file
		writer.format("%s\n", orchardName);
	}
	
	
	
	

	/**
	 * This method that allows you to add items specific to fruit class
	 * @param keyboard the input stream used to declare property values
	 * @param fromFile a boolean variable used to indicate if the input is coming from a file 
	 * @return if name for the orchard is valid
	 */
	@Override
	boolean addItem(Scanner keyboard,boolean fromFile) {
		boolean isValid =false;
		//if from file dont prompt user
if (fromFile ==true ) {
	// call the abstract class method to add items general to all food items
	super.addItem(keyboard,true);
	keyboard.nextLine();
	//add the attribute specific to Fruit
	this.orchardName=keyboard.nextLine();
	return true;
	//if not from a file execute this chunk
} else {
		super.addItem(keyboard,false);
		keyboard.nextLine();
		  //prompt user to enter orchard name and assign it to orchardName var
		do { 
			System.out.print("Enter the name of the orchard supplier : ");
			this.orchardName = keyboard.nextLine();
			//if name blank trap user in a do while loop 
			if (orchardName.isBlank()) {
				System.out.println("Invalid entry");
				isValid=false;
			}
			else isValid=true;
			}while (isValid==false); 
			return true;
		}
	}
}


