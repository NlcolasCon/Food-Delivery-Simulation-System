package hw4;

import java.util.ArrayList;

/**
 * 
 * {@link Order} represents a food order. It contains information about the
 * order ID, order time, required time, and the quantity of various food items
 * in the order. The quantity of each food is stored in an {@link ArrayList}.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class Order {

	private int orderID; // Order ID
	private int tOrder; // Order time
	private int tRequired; // The required time
	private ArrayList<PorkPitta> pork = new ArrayList<PorkPitta>(); // List of PorkPitta in the order
	private ArrayList<ChickenPitta> chicken = new ArrayList<ChickenPitta>();; // List of ChickenPitta in the order
	private ArrayList<SheftaliaPitta> sheftalia = new ArrayList<SheftaliaPitta>();; // List of SheftaliaPitta in the
																					// order
	private ArrayList<MixPitta> mix = new ArrayList<MixPitta>();; // List of MixPitta in the order
	private ArrayList<PotatoFries> potato = new ArrayList<PotatoFries>();; // List of PotatoFries in the order
	private int time = 0; // Total cooking time for the order
	private int numOfPitta; // Total number of pittas in the order

	/**
	 * This is a constructor for an {@link Order} object.
	 *
	 * @param num        Order ID.
	 * @param tOrder     Order time.
	 * @param tReq       Required time.
	 * @param pp         Number of {@link PorkPitta} in the order.
	 * @param pc         Number of {@link ChickenPitta} in the order.
	 * @param ps         Number of {@link SheftaliaPitta} in the order.
	 * @param pm         Number of {@link MixPitta} in the order.
	 * @param pf         Number of {@link PotatoFries} in the order.
	 * @param numOfPitta Number of {@link Pitta} required for the order.
	 */

	public Order(int num, int tOrder, int tReq, int pp, int pc, int ps, int pm, int pf, int numOfPitta) {
		orderID = num;
		this.tOrder = tOrder;
		this.tRequired = tReq;
		this.numOfPitta = numOfPitta;
		for (int i = 0; i < pp; i++) {
			pork.add(new PorkPitta());
		}
		for (int i = 0; i < pc; i++) {
			chicken.add(new ChickenPitta());
		}
		for (int i = 0; i < ps; i++) {
			sheftalia.add(new SheftaliaPitta());
		}
		for (int i = 0; i < pm; i++) {
			mix.add(new MixPitta());
		}
		for (int i = 0; i < pf; i++) {
			potato.add(new PotatoFries());
		}
		Food[] food = setArray(pp, pc, ps, pm, pf);
		time = max(food);
	}

	/**
	 * This is a copy constructor for an {@link Order} object.
	 *
	 * @param order Another {@link Order} object to be copied.
	 */

	public Order(Order order) {
		this.orderID = order.getID();
		this.tOrder = order.getOrderTime();
		this.tRequired = order.getReq();
		this.numOfPitta = order.getNumOfPitta();
		for (int i = 0; i < order.pork.size(); i++) {
			this.pork.add(order.pork.get(i));
		}
		for (int i = 0; i < order.chicken.size(); i++) {
			this.chicken.add(order.chicken.get(i));
		}
		for (int i = 0; i < order.sheftalia.size(); i++) {
			this.sheftalia.add(order.sheftalia.get(i));
		}
		for (int i = 0; i < order.mix.size(); i++) {
			this.mix.add(order.mix.get(i));
		}
		for (int i = 0; i < order.potato.size(); i++) {
			this.potato.add(order.potato.get(i));
		}
		Food[] food = setArray(order.pork.size(), order.chicken.size(), order.sheftalia.size(), order.mix.size(),
				order.potato.size());
		this.time = max(food);
	}

	/**
	 * This method calculates the total cooking time for the {@link Order}.
	 *
	 * @return The total cooking time for the {@link Order}.
	 */

	public int getTime() {
		return time;
	}

	/**
	 * 
	 * Creates an array of {@link Food} objects based on the specified quantities of
	 * different types of food items.
	 * 
	 * @param pp number of {@link PorkPitta} objects
	 * @param pc number of {@link ChickenPitta} objects
	 * @param ps number of {@link SheftaliaPitta} objects
	 * @param pm number of {@link MixPitta} objects
	 * @param pf number of {@link PotatoFries} objects
	 * @return an array of food objects
	 */

	private Food[] setArray(int pp, int pc, int ps, int pm, int pf) {
		Food[] food = new Food[pp + pc + ps + pm + pf];
		int k = 0;
		for (int i = 0; i < pp; i++) {
			food[k] = pork.get(i);
			k++;
		}
		for (int i = 0; i < pc; i++) {
			food[k] = chicken.get(i);
			k++;
		}
		for (int i = 0; i < ps; i++) {
			food[k] = sheftalia.get(i);
			k++;
		}
		for (int i = 0; i < pm; i++) {
			food[k] = mix.get(i);
			k++;
		}
		for (int i = 0; i < pf; i++) {
			food[k] = potato.get(i);
			k++;
		}
		return food;
	}

	/**
	 * This method takes an array of {@link Food} objects and returns the maximum
	 * time that a food needs to be prepared.
	 * 
	 * @param food an array containing {@link Food} objects.
	 * @return the maximum time that is required for a {@link Food} object to be
	 *         cooked
	 */

	private int max(Food[] food) {
		int maxTime = Integer.MIN_VALUE;
		for (int i = 0; i < food.length; i++) {
			if (food[i].getTime() > maxTime) {
				maxTime = food[i].getTime();
			}
		}
		return maxTime;
	}

	/**
	 * This method is a string representation of the {@link Order} object.
	 *
	 * @return A string representation of the {@link Order}.
	 */

	public String toString() {
		return orderID + "\t" + tOrder + "\t" + tRequired + ";\t" + pork.size() + "\t" + chicken.size() + "\t"
				+ sheftalia.size() + "\t" + mix.size() + "\t" + potato.size();
	}

	/**
	 * Getter method for the required time for the {@link Order}.
	 *
	 * @return The required time for the {@link Order}.
	 */

	public int getReq() {
		return tRequired;
	}

	/**
	 * Getter method for the order ID.
	 *
	 * @return order ID.
	 */

	public int getID() {
		return orderID;
	}

	/**
	 * This method calculates and returns the total space required for cooking
	 * {@link PorkSouvlaki}, {@link ChickenSouvlaki} and {@link Sheftalia} items in
	 * the {@link Order}.
	 *
	 * @param x Space required for cooking a single meat item ({@link PorkSouvlaki}
	 *          or {@link ChickenSouvlaki}).
	 * @param y Space required for cooking a single {@link Sheftalia} item.
	 * @return The total space required for cooking meat items in the order.
	 */

	public int getMeatSpace(int x, int y) {
		int space = 0;
		for (int i = 0; i < pork.size(); i++) {
			space += 2 * x;
		}
		for (int i = 0; i < chicken.size(); i++) {
			space += 2 * x;
		}
		for (int i = 0; i < sheftalia.size(); i++) {
			space += 2 * y;
		}
		for (int i = 0; i < mix.size(); i++) {
			space += x;
			space += y;
		}
		return space;
	}

	/**
	 * This method returns the total space required for cooking {@link PotatoFries}
	 * in the {@link Order}.
	 *
	 * @return The total space required for cooking {@link PotatoFries} in the
	 *         {@link Order}.
	 */

	public int getFriesSpace() {
		return potato.size();
	}

	/**
	 * Getter method for the {@link Order} time.
	 *
	 * @return The {@link Order} time.
	 */

	public int getOrderTime() {
		return tOrder;
	}

	/**
	 * Get the total space required for cooking {@link Pitta} items in the
	 * {@link Order}.
	 *
	 * @param z Space required for cooking a single {@link Pitta} item.
	 * @return The total space required for cooking {@link Pitta} items in the
	 *         {@link Order}.
	 */

	public int getPittaSpace(int z) {
		return z * numOfPitta;
	}

	/**
	 * Getter method for the number of {@link Pitta} required for the {@link Order}.
	 *
	 * @return The number of {@link Pitta} required for the {@link Order}.
	 */

	public int getNumOfPitta() {
		return numOfPitta;
	}

	/**
	 * This method calculates the weight of an {@link Order}.
	 * 
	 * @param time the current time.
	 * @return the weight of the {@link Order}.
	 */

	public double getWeight(int time) {
		return (double) ((getTime() - (getReq() - time)) / (getTime()));
	}

	/**
	 * Getter method for the ArrayList of {@link ChickenPitta} objects.
	 * 
	 * @return
	 */

	public ArrayList getChicken() {
		return chicken;
	}

	/**
	 * Getter method for the ArrayList of {@link PorkPitta} objects.
	 * 
	 * @return
	 */

	public ArrayList getPork() {
		return pork;
	}

	/**
	 * Getter method for the ArrayList of {@link SheftaliaPitta} objects.
	 * 
	 * @return
	 */

	public ArrayList getSheftalia() {
		return sheftalia;
	}

	/**
	 * Getter method for the ArrayList of {@link MixPitta} objects.
	 * 
	 * @return
	 */

	public ArrayList getMix() {
		return mix;
	}

}
