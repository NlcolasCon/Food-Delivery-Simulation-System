package hw4;

/**
 * 
 * This class represents a {@link PorkPitta} type of food item that consists of
 * two {@link PorkSouvlaki} sticks. It calculates the time required to cook the
 * {@link PorkPitta} based on the {@link PorkSouvlaki} stick that requires the
 * longest cooking time.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class PorkPitta extends Food {

	private PorkSouvlaki stick1; // First PorkSouvlaki stick
	private PorkSouvlaki stick2; // Second PorkSouvlaki stick
	private int time; // The required cooking time

	/**
	 * A constructor for a {@link PorkPitta} object. It creates two
	 * {@link PorkSouvlaki} sticks in the {@link PorkPitta} and sets the cooking
	 * time.
	 */

	public PorkPitta() {
		stick1 = new PorkSouvlaki();
		stick2 = new PorkSouvlaki();
		time = max();
	}

	/**
	 * Getter method for the cooking time of {@link PorkPitta}.
	 *
	 * @return The cooking time for the {@link PorkPitta}.
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
	 * Calculate the maximum cooking time required for the {@link PorkPitta}. It
	 * compares the cooking times of the two {@link PorkSouvlaki} sticks and returns
	 * the maximum time.
	 *
	 * @return The maximum cooking time between the two sticks.
	 */

	public int max() {
		if (stick1.getTime() > stick2.getTime())
			return stick1.getTime();
		return stick2.getTime();
	}

}
