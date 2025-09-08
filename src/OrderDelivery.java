package hw4;

import java.io.*;
import java.util.Scanner;

/**
 * The class takes as arguments in the command line the capacity of the
 * {@link Rotisserie}, the fuel time, the number of {@link Fryers} and their
 * capacity, the space that the meat and the {@link Pitta} takes and the
 * algorithm that is going to be used. Then the program reads from the
 * "orders.txt" file all the orders. It prepares the orders based on the
 * algorithm given and prints in the "deliveries.txt" file the numbers of
 * {@link Order}, the average deviation of the required time and the number
 * happy customers. Then it prints on the same file all the orders that were
 * prepared from the time that the restaurant opened until the time it closed.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class OrderDelivery {

	/**
	 * The main method of the program. Every input comes from the Command line
	 * arguments. Based on the input, a {@link Rotisserie}, a {@link Order} array
	 * and a {@link Fryers}. Then based on the algorithm chosen, the complete
	 * deliveries are printed in the "deliveries.txt" file.
	 * 
	 * @param args Command line arguments.
	 */

	public static void main(String[] args) {
		int M = 0;
		int T = 0;
		int N = 0;
		int C = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		int alg = 0;
		try {
			M = Integer.parseInt(args[0]);
			T = Integer.parseInt(args[1]);
			N = Integer.parseInt(args[2]);
			C = Integer.parseInt(args[3]);
			x = Integer.parseInt(args[4]);
			y = Integer.parseInt(args[5]);
			z = Integer.parseInt(args[6]);
			alg = Integer.parseInt(args[7]);
			if (M <= 0 || T <= 0 || N <= 0 || C <= 0 || x <= 0 || y <= 0 || z <= 0 || alg <= 0) {
				throw new NegativeArraySizeException();
			}
		} catch (ArrayIndexOutOfBoundsException e1) {
			System.err.println("Argumetns excpected int String[] args...");
			System.exit(0);
		} catch (IllegalArgumentException e2) {
			System.err.println("Integer argument in String[] args excpected...");
			System.exit(0);
		} catch (NegativeArraySizeException e3) {
			System.err.println("Positive argument in String[] args excpected...");
			System.exit(0);
		}

		Rotisserie rotisserie = new Rotisserie(M, T, x, y, z);
		Fryers fryer = new Fryers(N, C);
		Order[] orders = null;
		try {
			orders = readOrders("orders.txt");
			if (orders == null)
				throw new NullPointerException();
		} catch (FileNotFoundException e1) {
			System.err.println("File excpected in readOrders()...");
			System.exit(0);
		} catch (NullPointerException e2) {
			System.err.println("orders array null...");
			System.exit(0);
		}

		SouvlatzidikoAlgorithms s = new SouvlatzidikoAlgorithms();
		Algorithms handle = null;
		switch (alg) {
		case (1):
			handle = s.makeFCFS(orders, rotisserie, fryer);
			break;
		case (2):
			handle = s.makeTimeBased(orders, rotisserie, fryer);
			break;
		case (3):
			handle = s.makeWeightBased(orders, rotisserie, fryer);
			break;
		}

		handle.algorithm(x, y, z);

	}

	/**
	 * This method reads the orders of the parameter file and stores them in an
	 * array of {@link Order} objects.
	 * 
	 * @param name The name of the file to read {@link Order} from.
	 * @return An array of {@link Order} objects.
	 * @throws FileNotFoundException If the file is not found.
	 */

	public static Order[] readOrders(String name) throws FileNotFoundException {
		int orderID;
		int tOrder;
		int tReq;
		int pp;
		int pc;
		int ps;
		int pm;
		int pf;
		Scanner read = new Scanner(new FileInputStream("orders.txt"));
		int c = 0;
		// Counting the number of lines in the file
		while (read.hasNextLine()) {
			c++;
			String str = read.nextLine();
		}
		read.close();
		Order[] arr = new Order[c - 1];
		read = new Scanner(new FileInputStream("orders.txt"));
		int i = 0;
		read.nextLine();
		while (read.hasNextLine()) {
			orderID = read.nextInt();
			tOrder = read.nextInt();
			String st = read.next();
			tReq = Integer.parseInt(st.substring(0, st.length() - 1));
			pp = read.nextInt();
			pc = read.nextInt();
			ps = read.nextInt();
			pm = read.nextInt();
			pf = read.nextInt();
			arr[i] = new Order(orderID, tOrder, tReq, pp, pc, ps, pm, pf, pp + pc + ps + pm);
			i++;
		}
		read.close();
		return arr;
	}

}
