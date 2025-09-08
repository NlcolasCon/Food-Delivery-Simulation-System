package hw4;

/**
 * 
 * This class represents a {@link ChickenPitta} type of food item that consists
 * of two {@link ChickenSouvlaki} sticks. It calculates the time required to
 * cook the {@link ChickenPitta} based on the {@link ChickenSouvlaki} stick that
 * requires the longest cooking time.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class ChickenPitta extends Food {

	private ChickenSouvlaki stick1; // First ChickenSouvlaki stick
	private ChickenSouvlaki stick2; // Second ChickenSouvlaki stick
	private int time; // the cooking time

	/**
	 * A constructor for a {@link ChickenPitta} object. It creates two
	 * {@link ChickenSouvlaki} sticks in the {@link ChickenPitta} and sets the
	 * cooking time.
	 */

	public ChickenPitta() {
		stick1 = new ChickenSouvlaki();
		stick2 = new ChickenSouvlaki();
		time = max();
	}

	/**
	 * Getter method for the cooking time of {@link ChickenPitta}
	 *
	 * @return The cooking time for the {@link ChickenPitta}.
	 */

	public int getTime() {
		return time;
	}

	/**
	 * This method returns the time that the stick1 needs to be cooked
	 * 
	 * @return the time that the stick1 needs to be cooked
	 */

	public int getTimeStick1() {
		return stick1.getTime();
	}

	/**
	 * This method returns the time that the stick2 needs to be cooked
	 * 
	 * @return the time that the stick2 needs to be cooked
	 */

	public int getTimeStick2() {
		return stick2.getTime();
	}

	/**
	 * Calculate the maximum cooking time required for the {@link ChiceknPitta}. It
	 * compares the cooking times of the two {@link ChiceknSouvlaki} sticks and
	 * returns the maximum time.
	 *
	 * @return The maximum cooking time between the two sticks.
	 */

	public int max() {
		if (stick1.getTime() > stick2.getTime())
			return stick1.getTime();
		return stick2.getTime();
	}

}
