package hw4;

/**
 * 
 * This class represents a {@link PorkSouvlaki} type of food item. It has a
 * random cooking time between 20 to 25 minutes.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class PorkSouvlaki {

	private int time; // the cooking time

	/**
	 * This is a constructor for a {@link PorkSouvlaki} object. It sets the cooking
	 * time to a random value between 20 to 25 minutes.
	 */

	public PorkSouvlaki() {
		this.time = (int) (Math.random() * (25 - 20 + 1) + 20);
	}

	/**
	 * Getter method for the cooking time of {@link PorkSouvlaki}.
	 *
	 * @return The cooking time for the {@link PorkSouvlaki}.
	 */

	public int getTime() {
		return time;
	}

}
