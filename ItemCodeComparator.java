import java.util.Comparator;

/**
 *This class compares item codes of food items and sorts them in the arraylist 
 * 

 * 
 * @author Andrew Lorimer
 * @version 1.0
 * @since 1.8
 */
public class ItemCodeComparator implements Comparator<FoodItem>{
	
/**
 * Method to compare food item objects 
 * @param o1 the first food item object
 * @param o2 the second food item object
 * @return an integer representing if the first object item code is greater than the next
 */
		@Override
		public int compare(FoodItem o1, FoodItem o2) {

			if (o1.itemCode > o2.itemCode) {return 1;}
			else {return -1;}
	}
}