package hw4;

/**
 * 
 * {@Fryers} represents a set of {@link Fryers} in a kitchen. It keeps track of
 * the number of {@link Fryers} available and their capacity of
 * {@link PotatoFries}.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class Fryers {

	private int N; // Number of fryers
	private int C; // Capacity of fryers

	/**
	 * Constructor for {@link Fryers} object.
	 *
	 * @param N Number of {@link Fryers}.
	 * @param C Capacity of {@link PotatoFries} in each {@link Fryers}.
	 */

	public Fryers(int N, int C) {
		this.N = N;
		this.C = C;
	}

	/**
	 * Getter method fot the capacity of the {@link Fryers}.
	 * 
	 * @return the capacity of the {@link Fryers}.
	 */

	public int getFSpace() {
		return C;
	}

	/**
	 * Getter method for the numbers of the {@link Fryers}.
	 * 
	 * @return the number of the {@link Fryers}.
	 */

	public int getFryers() {
		return N;
	}

}
