package hw4;

/**
 * 
 * This class represents a {@link ChickenSouvlaki} type of food item. It has a
 * random cooking time between 15 to 20 minutes.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class ChickenSouvlaki {

	private int time; // the cooking time

	/**
	 * This is a constructor for a {@link ChickenSouvlaki} object. It sets the
	 * cooking time to a random value between 15 to 20 minutes.
	 */

	public ChickenSouvlaki() {
		this.time = (int) (Math.random() * (20 - 15 + 1) + 15);
	}

	/**
	 * Getter method for the cooking time of {@link ChickenSouvlaki}
	 *
	 * @return The cooking time for the {@link ChickenSouvlaki}.
	 */

	public int getTime() {
		return time;
	}

}