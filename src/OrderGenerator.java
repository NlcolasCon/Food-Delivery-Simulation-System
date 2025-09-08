package hw4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class generates {@link Order} objects based on statistics and prints
 * them on the "orders.txt" file. It takes the amount of {@link Order} objects
 * as an argument in the command line.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class OrderGenerator {

	/**
	 * The main method of the class. The number for the amount of {@link Order} is
	 * being input by the Command line arguments. An {@link Order} array is being
	 * created and sorted, then printed out in a file.
	 * 
	 * @param args Command line arguments.
	 */

	public static void main(String[] args) {

		int nums = 0;

		try {
			nums = Integer.parseInt(args[0]);
			if (nums <= 0) {
				throw new NegativeArraySizeException();
			}
		} catch (NegativeArraySizeException e1) {
			System.err.println("Positive argument in String[] args excpected...");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e2) {
			System.err.println("Argument in String[] args excpected...");
			System.exit(0);
		} catch (IllegalArgumentException e3) {
			System.err.println("Integer argument in String[] args excpected...");
			System.exit(0);
		}

		Order[] orders = createOrder(nums);
		bubbleSort(orders);
		printOrder(orders);

	}

	// This method creates an array of Orders

	private static Order[] createOrder(int nums) {
		Order[] order = new Order[nums];
		int numOfPitta = 0;
		int pp = 0;
		int pc = 0;
		int ps = 0;
		int pm = 0;
		int pf = 0;

		// The amount of orders to be created
		for (int i = 0; i < order.length; i++) {
			numOfPitta = 0;
			pp = 0;
			pc = 0;
			ps = 0;
			pm = 0;
			pf = 0;

			int orderID = i + 1;
			int tReq = 0;
			Random nextG = new Random();
			// Calculating time of the order, tOrder
			int tOrder = (int) (Math.sqrt(60) * nextG.nextGaussian() + 180);
			// Calculating the numbers of Pittas
			numOfPitta = pittaProb(i + 1, nums);
			// Using Math.Random() to choose what Pitta to create
			for (int j = 0; j < numOfPitta; j++) {
				int meatProb = (int) ((Math.random() * (4)) + 1);
				switch (meatProb) {
				case 1:
					pp++;
					break;
				case 2:
					pc++;
					break;
				case 3:
					ps++;
					break;
				case 4:
					pm++;
					break;
				}
				// Calculating the amount of fries
				int friesProb = (int) (Math.random() * (100) + 1);
				if (friesProb >= 1 && friesProb <= 60) {
					pf++;
				} else if (friesProb >= 61 && friesProb <= 65) {
					pf += 2;
				}
			}

			// Calculating the required time, tReq
			if (numOfPitta > 10) {
				tReq = (int) ((Math.random() * (180 - 60 + 1)) + tOrder + 60);
			} else {
				tReq = (int) ((Math.random() * (180 - 30 + 1)) + tOrder + 30);
			}
			while (tReq > 360) {
				if (numOfPitta > 10) {
					tReq = (int) ((Math.random() * (180 - 60 + 1)) + tOrder + 60);
				} else {
					tReq = (int) ((Math.random() * (180 - 30 + 1)) + tOrder + 30);
				}
			}

			// Creating an order with attributes as calculated above
			order[i] = new Order(orderID, tOrder, tReq, pp, pc, ps, pm, pf, numOfPitta);
		}
		return order;
	}

	// This method is used to print the order
	// objects of the parameter array of
	// Orders in the "orders.txt" file

	private static void printOrder(Order[] orders) {
		PrintWriter output = null;
		try {
			output = new PrintWriter("orders.txt");
			output.println(orders.length);
			for (int i = 0; i < orders.length; i++) {
				if (i != orders.length - 1)
					output.println(orders[i]);
				else
					output.print(orders[i]);
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		output.close();
	}

	// This method sorts the parameter order array based on the order time. If
	// the order time is the same it sorts the orders based on the orderID

	private static void bubbleSort(Order[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j].getOrderTime() > arr[j + 1].getOrderTime()) {
					Order temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				} else if (arr[j].getOrderTime() == arr[j + 1].getOrderTime()) {
					if (arr[j].getID() > arr[j + 1].getID()) {
						Order temp = arr[j];
						arr[j] = arr[j + 1];
						arr[j + 1] = temp;
					}
				}
			}
	}

	// This method calculates the amount of pittas that the order will have

	private static int pittaProb(int i, int nums) {
		int numOfPitta = 0;
		if (i >= 1 && i <= (int) (nums * 0.20)) {
			numOfPitta = 1;
		} else if (i > (int) (nums * 0.20) && i <= (int) (nums * 0.55)) {
			numOfPitta = 2;
		} else if (i > (int) (nums * 0.55) && i <= (int) (nums * 0.65)) {
			numOfPitta = 3;
		} else if (i > (int) (nums * 0.65) && i <= (int) (nums * 0.85)) {
			numOfPitta = 4;
		} else if (i > (int) (nums * 0.85) && i <= nums) {
			numOfPitta = (int) (Math.random() * (20 - 5 + 1) + 5);
		}
		return numOfPitta;
	}

}
