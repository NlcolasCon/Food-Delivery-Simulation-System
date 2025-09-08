package hw4;

import java.util.ArrayList;

/**
 * 
 * This interface represents the methods used for the 3 algorithms of the
 * program.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public interface Algorithms {

	/**
	 * {@inheritDoc}
	 */

	void algorithm(int x, int y, int z);

	/**
	 * {@inheritDoc}
	 */

	public void print(ArrayList<Order> delivered, ArrayList<Integer> timeDelivered, int happy, double avgSad);
}