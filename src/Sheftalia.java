package hw4;

/**
 * 
 * This class represents a {@link Sheftalia} type of food item. It has a
 * predefined cooking time of 25 minutes.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class Sheftalia {

	private int time; // the cooking time

	/**
	 * This is a constructor for {@link Sheftalia}. It sets the cooking time to 25
	 * minutes.
	 */

	public Sheftalia() {
		this.time = 25;
	}

	/**
	 * Getter method for the cooking time of {@link Sheftalia}.
	 *
	 * @return The cooking time for the {@link Sheftalia}.
	 */

	public int getTime() {
		return time;
	}

}
