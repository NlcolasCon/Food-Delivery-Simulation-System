package hw4;

/**
 * 
 * This class represents the {@link Pitta} food item. It requires 5 minutes of
 * cooking time.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class Pitta {

	private int time; // the cooking time

	/**
	 * This is a constructor for a {@link Pitta} object. It initialises the cooking
	 * time to 5 minutes.
	 */

	public Pitta() {
		this.time = 5;
	}

	/**
	 * Getter method for the cooking time of {@link Pitta}
	 *
	 * @return The cooking time for the {@link Pitta}.
	 */

	public int getTime() {
		return time;
	}

}
