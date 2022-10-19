import java.util.Formatter;
import java.util.Scanner;

/**
 * This class contains field members specific for preserve and overrides methods
 * made in the abstract class FoodItem
 * 

 * @author Andrew Lorimer
 * @version 1.0
 * @since 1.8
 */
public class Preserve extends FoodItem {

	/**
	 * An int storing the value for the jarSize
	 */
	private int jarSize;

	/**
	 * This method prints the data members specific to preserve
	 * @return A string of all the object attributes
	 */
	@Override
	public String toString() {
		return (super.toString() + " size:" + jarSize + "mL"+"\n");

	}
	/**
	 * Method to output the food item to a text file 
	 * @param writer the output stream to a file 
	 */
    @Override
	public void outputItem(Formatter writer) {
     //call the abstract class to add the attributes generic to all food items 
	super.outputItem(writer);
	 //write the attribute specific to preserve to the file
	writer.format("%s\n", jarSize);
	}	
	/**
	 * This method that allows you to add items specific to vegetable class
	 * @param fromFile  indicates if the input is coming from a file
	 * @param keyboard the input stream used to add object attribute values 
	 * @return a boolean value to indicate if adding was successful
	 */
	@Override
	boolean addItem(Scanner keyboard, boolean fromFile) {
		boolean isValid =false;
		//if from file dont prompt the user for input
		if (fromFile ==true ) {
			//call the abstract class to add generic attributes of Food Item
			super.addItem(keyboard,true);
			keyboard.nextLine();
			//add the attribute specific to Preserve
			this.jarSize=keyboard.nextInt();
			keyboard.nextLine();
			return true;
			//if not from a file execute this chunk
		}else {
		// call the abstract class method to add items general to all food items
		super.addItem(keyboard,false);
		keyboard.nextLine();
		do {
		  //prompt user to enter size of jar and assign it to jarSize var
		System.out.print("Enter the size of the jar in millimeters: ");
		this.jarSize = keyboard.nextInt();
		keyboard.nextLine();
		//if jar size is not valid trap user until they enter valid input
		if (jarSize > 0)  isValid =true;
		else System.out.println("Invalid entry");
		}while (isValid ==false);
		}
		return true;
	}
}

