package hw4;

/**
 * 
 * {@link Rotisserie} represents a cooking appliance that can cook various types
 * of food items. It keeps track of the space available for cooking, fuel time,
 * and space for {@link ChickenSouvlaki}, {@link PorkSouvlaki},
 * {@link Sheftalia} sticks and {@link Pitta}.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class Rotisserie {

	private int M; // Cooking space available
	private int T; // the fuel time
	private int x; // Space for ChickenSouvlaki, PorkSouvlaki sticks
	private int y; // Space for Sheftalia sticks
	private int z; // Space for Pitta

	/**
	 * This is a constructor for a {@link Rotisserie} object.
	 *
	 * @param M Cooking space available.
	 * @param T Fuel time.
	 * @param x Space for {@link ChickenSouvlaki}, {@link PorkSouvlaki} sticks.
	 * @param y Space for {@link Sheftalia} sticks.
	 * @param z Space for {@link Pitta}.
	 */

	public Rotisserie(int M, int T, int x, int y, int z) {
		this.M = M;
		this.T = T;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Getter method for the space available in the {@link Rotisserie}.
	 * 
	 * @return the space available in the {@link Rotisserie}.
	 */

	public int getRSpace() {
		return M;
	}

	/**
	 * Getter method for the fuel time required for the {@link Rotisserie}.
	 *
	 * @return The fuel time required.
	 */

	public int getFuelTime() {
		return T;
	}

	/**
	 * Getter method for the space available for cooking {@link ChickenSouvlaki},
	 * {@link PorkSouvlaki} sticks.
	 *
	 * @return The space available for cooking {@link ChickenSouvlaki},
	 *         {@link PorkSouvlaki} sticks.
	 */

	public int getSouvlakiSpace() {
		return x;
	}

	/**
	 * Getter method for the space available for cooking {@link Sheftalia} sticks.
	 *
	 * @return The space available for cooking {@link Sheftalia} sticks.
	 */

	public int getSheftaliaSpace() {
		return y;
	}

	/**
	 * Get the space available for cooking {@link Pitta} in the {@link Rotisserie}.
	 *
	 * @return The space available for cooking {@link Pitta}.
	 */

	public int getPittaSpace() {
		return z;
	}

}