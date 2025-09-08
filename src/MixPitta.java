package hw4;

/**
 * 
 * {@link MixPitta} class represents a type of {@link Food} that has a mix of
 * {@link PorkSouvlaki} and {@link Sheftalia} sticks. It calculates the time
 * required to cook the mix based on the stick that requires the longest time to
 * be cooked.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class MixPitta extends Food {

	private PorkSouvlaki stick1;// A PorkSouvlaki stick
	private Sheftalia stick2; // A Sheftalia
	private int time; // Time required to cook the mix

	/**
	 * This is the constructor for {@link MixPitta}. It creates the
	 * {@link PorkSouvlaki} and {@link Sheftalia} sticks in the mix and sets the
	 * cooking time.
	 */
	public MixPitta() {
		stick1 = new PorkSouvlaki();
		stick2 = new Sheftalia();
		time = max();
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
	 * This is a getter method for the required time to cook the {@link MixPitta}
	 *
	 * @return The cooking time for the {@link MixPitta}.
	 */

	public int getTime() {
		return time;
	}

	/**
	 * Calculate the maximum cooking time required for the {@link MixPitta}. It
	 * compares the cooking times of the {@link PorkSouvlaki} and {@link Sheftalia}
	 * sticks and returns the maximum time.
	 *
	 * @return The maximum cooking time between the two sticks.
	 */

	public int max() {
		if (stick1.getTime() > stick2.getTime())
			return stick1.getTime();
		return stick2.getTime();
	}

}
