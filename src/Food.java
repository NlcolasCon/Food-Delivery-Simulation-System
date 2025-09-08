package hw4;

/**
 * 
 * {@link Food} is an abstract class representing a food item. Subclasses of
 * {@link Food} must implement the getTime() method to provide the cooking time
 * for the specific food item.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public abstract class Food {

	/**
	 * An abstract method that returns the cooking time required for the
	 * {@link Food} item.
	 *
	 * @return The cooking time for the food item.
	 */

	public abstract int getTime();

}