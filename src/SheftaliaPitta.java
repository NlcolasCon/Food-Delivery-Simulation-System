package hw4;

/**
 * 
 * This class represents a {@link SheftaliaPitta} type of food item that
 * consists of two {@link Sheftalia} sticks. It calculates the time required to
 * cook the {@link SheftaliaPitta} based on the {@link Sheftalia} stick that
 * requires the longest cooking time.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class SheftaliaPitta extends Food {

	private Sheftalia stick1; // First Sheftalia stick
	private Sheftalia stick2; // Second Sheftalia stick
	private int time; // the cooking time

	/**
	 * A constructor for a {@link SheftaliaPitta} object. It creates two
	 * {@link Sheftalia} sticks in the {@link SheftaliaPitta} and sets the cooking
	 * time.
	 */

	public SheftaliaPitta() {
		stick1 = new Sheftalia();
		stick2 = new Sheftalia();
		time = max();
	}

	/**
	 * Getter method for the cooking time of {@link SheftaliaPitta}.
	 *
	 * @return The cooking time for the {@link SheftaliaPitta}.
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
	 * Calculate the maximum cooking time required for the {@link SheftaliaPitta}.
	 * It compares the cooking times of the two {@link Sheftalia} sticks and returns
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
