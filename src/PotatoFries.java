package hw4;

/**
 * 
 * This class represents {@link PotatoFries} type of food item. It has a
 * predefined cooking time of 20 units.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class PotatoFries extends Food {

	private int time;

	/**
	 * This is theF constructor for {@link PotatoFries}. It sets the cooking time to 20
	 * minutes.
	 */

	public PotatoFries() {
		this.time = 20;
	}

	/**
	 * Getter method for the cooking time of {@link PotatoFries}.
	 *
	 * @return The cooking time for the {@link PotatoFries}.
	 */

	public int getTime() {
		return time;
	}

}