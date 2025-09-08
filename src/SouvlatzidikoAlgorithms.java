package hw4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * This class has as inner classes all the 3 {@link Algorithms}. It also has
 * factory methods for all the 3 {@link Algorithms}. The first algorithm
 * prepares the orders in the order that it receives them. The second algorithm
 * prepares the orders that come at a certain time based on the one that has the
 * lowest preparing time. The third algorithm prepares the orders that come at a
 * certain time based on the one that has the most weight.
 * 
 * @author Nikolas Konstantinou, Lefteris Panteli
 * @since 14/04/2024
 */

public class SouvlatzidikoAlgorithms {

	/**
	 * Creates an instance of the First Come First Served {@link Algorithms}.
	 * 
	 * @param orders     The array of {@link Order} to process.
	 * @param rotisserie The {@link Rotisserie} object.
	 * @param fryers     The {@link Fryers} object.
	 * @return An instance of the First Come First Served {@link Algorithms}.
	 */

	public Algorithms makeFCFS(Order[] orders, Rotisserie rotisserie, Fryers fryers) {
		return new FirstComeFirstServed(orders, rotisserie, fryers);
	}

	/**
	 * Creates an instance of the Time-Based {@link Algorithms}.
	 * 
	 * @param orders     The array of {@link Order} to process.
	 * @param rotisserie The {@link Rotisserie} object.
	 * @param fryers     The {@link Fryers} object.
	 * @return An instance of the First Come First Served {@link Algorithms}.
	 */

	public Algorithms makeTimeBased(Order[] orders, Rotisserie rotisserie, Fryers fryers) {
		return new TimeBased(orders, rotisserie, fryers);
	}

	/**
	 * Creates an instance of the Weight-Based {@link Algorithms}.
	 * 
	 * @param orders     The array of {@link Order} to process.
	 * @param rotisserie The {@link Rotisserie} object.
	 * @param fryers     The {@link Fryers} object.
	 * @return An instance of the First Come First Served {@link Algorithms}.
	 */

	public Algorithms makeWeightBased(Order[] orders, Rotisserie rotisserie, Fryers fryers) {
		return new WeightBased(orders, rotisserie, fryers);
	}

	/**
	 * This class represents the FCFS (First come first served) {@link Algorithms}.
	 */

	private class FirstComeFirstServed implements Algorithms {

		private Order[] orders;
		private Rotisserie rotisserie;
		private Fryers fryers;

		/**
		 * Constructs a {@link FirstComeFirstServed} object with the parameter orders,
		 * rotisserie, and fryers.
		 * 
		 * @param orders     An array of {@Order} objects.
		 * @param rotisserie A {@Rotisserie} object.
		 * @param fryers     A {@Fryers} object.
		 */

		public FirstComeFirstServed(Order[] orders, Rotisserie rotisserie, Fryers fryers) {
			copyOrder(orders);
			this.rotisserie = rotisserie;
			this.fryers = fryers;
		}

		/**
		 * This method executes the FCFS {@link Algorithms}. It prepares the
		 * {@link Order} starting from the first {@link Order} ordered to the last one.
		 * 
		 * @param x The space of meat needed in the {@link Rotisserie}.
		 * @param y The space of {@link Sheftalia} needed in the {@link Rotisserie}.
		 * @param z The amount of space needed for each {@link Pitta} in the
		 *          {@link Rotisserie}.
		 */

		public void algorithm(int x, int y, int z) {

			int currentR = rotisserie.getRSpace();
			int currentF = fryers.getFryers() * fryers.getFSpace();
			int happy = 0;
			int counter = 0;
			double delDev = 0;

			int time = 0;
			int fuelTime = rotisserie.getFuelTime();

			ArrayList<Order> waitList = new ArrayList<Order>();
			ArrayList<Order> preparing = new ArrayList<Order>();
			ArrayList<Integer> startTime = new ArrayList<Integer>();
			ArrayList<Integer> orderPrep = new ArrayList<Integer>();
			ArrayList<Order> delivered = new ArrayList<Order>();
			ArrayList<Integer> delTime = new ArrayList<Integer>();
			ArrayList<Order> fitWaitList = new ArrayList<Order>();
			ArrayList<Order> unFitWaitList = new ArrayList<Order>();

			Order next = null;

			addValidOrders(orders, fitWaitList, unFitWaitList, x, y, z);

			while (time <= 360) {
				if (time <= 300 && time >= 0)
					getCurrentOrders(time, waitList, fitWaitList);

				if (time >= fuelTime - 30) {
					boolean stillFits = true;
					while (stillFits) {

						if (waitList.size() >= 1) {
							next = new Order(getPriority(waitList));

							if ((currentR - next.getMeatSpace(x, y) - next.getPittaSpace(z) >= 0)
									&& (currentF - next.getFriesSpace() >= 0)) {
								removeFromList(waitList, next);
								currentR = currentR - next.getMeatSpace(x, y) - next.getPittaSpace(z);
								currentF = currentF - next.getFriesSpace();
								preparing.add(next);
								startTime.add(time);
								orderPrep.add(0);
							} else {
								stillFits = false;
							}
						} else {
							stillFits = false;
						}
					}

					for (int i = 0; i < preparing.size(); i++) {

						if (20 + startTime.get(i) == time) {
							currentF = currentF + preparing.get(i).getFriesSpace();
							int num = orderPrep.get(i);
							orderPrep.set(i, ++num);
						}

						if (5 + startTime.get(i) == time) {
							currentR = currentR + preparing.get(i).getPittaSpace(z);
							int num = orderPrep.get(i);
							orderPrep.set(i, ++num);
						}

						for (int j = 0; j < preparing.get(i).getChicken().size(); j++) {
							if (((ChickenPitta) preparing.get(i).getChicken().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((ChickenPitta) preparing.get(i).getChicken().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getPork().size(); j++) {
							if (((PorkPitta) preparing.get(i).getPork().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((PorkPitta) preparing.get(i).getPork().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getSheftalia().size(); j++) {
							if (((SheftaliaPitta) preparing.get(i).getSheftalia().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((SheftaliaPitta) preparing.get(i).getSheftalia().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getMix().size(); j++) {
							if (((MixPitta) preparing.get(i).getMix().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((MixPitta) preparing.get(i).getMix().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						if (orderPrep.get(i) == 2 + (2 * preparing.get(i).getChicken().size())
								+ (2 * preparing.get(i).getPork().size()) + (2 * preparing.get(i).getMix().size())
								+ (2 * preparing.get(i).getSheftalia().size())) {
							if (time <= preparing.get(i).getReq()) {
								happy++;
							}
							counter++;
							delDev += Math.abs(time - preparing.get(i).getReq());

							delivered.add(preparing.get(i));
							delTime.add(time);
							preparing.remove(i);
							startTime.remove(i);
							orderPrep.remove(i);
						}

					}

				}
				time++;
			}

			double avgDel = 0.00;
			if (counter != 0) {
				avgDel = (double) (delDev / counter);
			}
			print(delivered, delTime, happy, avgDel);
		}

		/**
		 * This method adds in the parameter {@link ArrayList} waitList all the orders
		 * that have the same orderTime as the parameter time from the {@link ArrayList}
		 * fitWaitList.
		 * 
		 * @param time        The current time.
		 * @param waitList    The list with all the waiting {@link Order}.
		 * @param fitWaitList The list with all the {@link Order} of the day that can
		 *                    fit on the {@link Rotisserie} and the {@link Fryers}.
		 */

		private void getCurrentOrders(int time, ArrayList<Order> waitList, ArrayList<Order> fitWaitList) {
			for (int i = 0; i < fitWaitList.size(); i++) {
				if (fitWaitList.get(i).getOrderTime() == time) {
					waitList.add(fitWaitList.get(i));
				}
			}
		}

		/**
		 * This method finds and returns the {@link Order} with the earliest order time
		 * or if many orders have have been ordered at the same time it returns the
		 * method with the lowest orderID
		 * 
		 * @param waitList The list containing the {@link Order}.
		 * @return The {@link Order} that will be prepared next.
		 */

		private Order getPriority(ArrayList<Order> waitList) {

			int min = Integer.MAX_VALUE;
			ArrayList<Order> priorities = new ArrayList<Order>();

			for (int i = 0; i < waitList.size(); i++) {
				if (min >= waitList.get(i).getOrderTime()) {
					min = waitList.get(i).getOrderTime();
				}
			}

			for (int i = 0; i < waitList.size(); i++) {
				if (min == waitList.get(i).getOrderTime()) {
					priorities.add(waitList.get(i));
				}
			}
			if (priorities.size() == 1) {
				return new Order(priorities.get(0));
			}

			int minID = Integer.MAX_VALUE;
			Order next = null;
			for (int i = 0; i < priorities.size(); i++) {
				if (minID > priorities.get(i).getID()) {
					minID = priorities.get(i).getID();
					next = new Order(priorities.get(i));
				}
			}
			return new Order(next);
		}

		/**
		 * Prints the delivery details to a file named "deliveries.txt".
		 * 
		 * @param delivered     The list of delivered {@link Order}.
		 * @param timeDelivered The list of times when {@link Order} were delivered.
		 * @param happy         The number of customers satisfied with their
		 *                      {@link Order}.
		 * @param avgSad        The average satisfaction level of unhappy customers.
		 */

		public void print(ArrayList<Order> delivered, ArrayList<Integer> timeDelivered, int happy, double avgSad) {

			PrintWriter output = null;
			try {
				output = new PrintWriter("deliveries.txt");
				output.printf("%d\t%.2f\t%d\n", orders.length, avgSad, happy);
				for (int i = 0; i < delivered.size(); i++) {
					output.println(delivered.get(i).getID() + "\t" + delivered.get(i).getOrderTime() + "\t"
							+ timeDelivered.get(i) + "\t" + (timeDelivered.get(i) - delivered.get(i).getReq()) + "    "
							+ (delivered.get(i).getNumOfPitta() + "\t" + delivered.get(i).getFriesSpace()));
				}
			} catch (IOException e) {
				System.out.print("input/output exception...");
				System.exit(0);
			}
			output.close();

		}

		/**
		 * This method deep copies the parameter array of {@link Order} in the attribute
		 * array of {@link Order} of the object that called the method.
		 * 
		 * @param orders the parameter to be copied.
		 */
		private void copyOrder(Order[] orders) {
			this.orders = new Order[orders.length];
			for (int i = 0; i < orders.length; i++) {
				this.orders[i] = new Order(orders[i]);
			}
		}

		/**
		 * Adds valid {@link Order} from the {@link Order} array to the fitWaitList list
		 * based on available spaces in the {@link Rotisserie} and the {@link Fryers}.
		 *
		 * @param unFitWaitList The list of {@link Order} that can not fit in the
		 *                      {@link Rotisserie} or the {@link Fryers}.
		 * @param fitWaitList   The list of {@link Order} that can fit in the
		 *                      {@link Rotisserie}and the {@link Fryers}.
		 * @param orders        The array of {@link Order} for the day.
		 * @param x             The required meat space dimension for the {@link Order}.
		 * @param y             The required {@link Sheftalia} space dimension for the
		 *                      {@link Order}.
		 * @param z             The required {@link Pitta} space dimension for the
		 *                      {@link Order}.
		 */

		private void addValidOrders(Order[] orders, ArrayList<Order> fitWaitList, ArrayList<Order> unFitWaitList, int x,
				int y, int z) {
			for (int i = 0; i < orders.length; i++) {
				if ((rotisserie.getRSpace() - orders[i].getMeatSpace(x, y) - orders[i].getPittaSpace(z) >= 0)
						&& (fryers.getFSpace() - orders[i].getFriesSpace() >= 0)
						&& (notIncluded(orders[i], fitWaitList))) {
					fitWaitList.add(orders[i]);
				} else if ((rotisserie.getRSpace() - orders[i].getMeatSpace(x, y) - orders[i].getPittaSpace(z) < 0)
						|| (fryers.getFSpace() - orders[i].getFriesSpace() < 0)) {
					unFitWaitList.add(orders[i]);
				}
			}
		}

	}

	/**
	 * This inner class represents the time-based {@link Algorithms}, based on the
	 * required time for the {@link Order} to be prepaired.
	 */

	private class TimeBased implements Algorithms {

		private Order[] orders;
		private Rotisserie rotisserie;
		private Fryers fryers;

		/**
		 * Constructs a {@link TimeBased} object with the parameter orders, rotisserie,
		 * and fryers.
		 * 
		 * @param orders     An array of {@link Order} objects.
		 * @param rotisserie A {@link Rotisserie} object.
		 * @param fryers     A {@link Fryers} object.
		 */

		public TimeBased(Order[] orders, Rotisserie rotisserie, Fryers fryers) {
			copyOrder(orders);
			this.rotisserie = rotisserie;
			this.fryers = fryers;
		}

		/**
		 * This method executes the {@link TimeBased} {@link Algorithms}. It prepares
		 * the {@link Order} based on the available {@link Order} that can be put on the
		 * {@link Rotisserie} and the {@link Fryers} that have the least preparing time.
		 * 
		 * @param x The space of meat needed in the {@link Rotisserie}.
		 * @param y The space of {@link Sheftalia} needed in the {@link Rotisserie}.
		 * @param z The amount of space needed for each {@link Pitta} in the
		 *          {@link Rotisserie}.
		 */

		public void algorithm(int x, int y, int z) {

			int currentR = rotisserie.getRSpace();
			int currentF = fryers.getFryers() * fryers.getFSpace();
			int happy = 0;
			int counter = 0;
			double delDev = 0;

			int time = 0;
			int fuelTime = rotisserie.getFuelTime();

			ArrayList<Order> waitList = new ArrayList<Order>();
			ArrayList<Order> preparing = new ArrayList<Order>();
			ArrayList<Integer> startTime = new ArrayList<Integer>();
			ArrayList<Integer> orderPrep = new ArrayList<Integer>();
			ArrayList<Order> delivered = new ArrayList<Order>();
			ArrayList<Integer> delTime = new ArrayList<Integer>();
			ArrayList<Order> fitWaitList = new ArrayList<Order>();
			ArrayList<Order> unFitWaitList = new ArrayList<Order>();

			Order next = null;

			addValidOrders(orders, fitWaitList, unFitWaitList, x, y, z);

			while (time <= 360) {
				if (time <= 300)
					getCurrentOrders(time, waitList, fitWaitList);

				if (time >= fuelTime - 30) {
					boolean stillFits = true;
					while (stillFits) {

						if (waitList.size() >= 1) {
							next = new Order(getPriority(waitList));
							ArrayList<Order> skip = new ArrayList<Order>();
							boolean stop = false;

							while (!((currentR - next.getMeatSpace(x, y) - next.getPittaSpace(z) >= 0)
									&& (currentF - next.getFriesSpace() >= 0)) && !stop) {
								skip.add(next);
								if (skip.size() != waitList.size() && waitList.size() >= 1) {
									next = new Order(getPriority(waitList, time, skip));
								} else {
									stop = true;
								}
							}

							if ((currentR - next.getMeatSpace(x, y) - next.getPittaSpace(z) >= 0)
									&& (currentF - next.getFriesSpace() >= 0)) {
								removeFromList(waitList, next);
								currentR = currentR - next.getMeatSpace(x, y) - next.getPittaSpace(z);
								currentF = currentF - next.getFriesSpace();
								preparing.add(next);
								startTime.add(time);
								orderPrep.add(0);
							} else {
								stillFits = false;
							}
						} else {
							stillFits = false;
						}
					}

					for (int i = 0; i < preparing.size(); i++) {

						if (20 + startTime.get(i) == time) {
							currentF = currentF + preparing.get(i).getFriesSpace();
							int num = orderPrep.get(i);
							orderPrep.set(i, ++num);
						}

						if (5 + startTime.get(i) == time) {
							currentR = currentR + preparing.get(i).getPittaSpace(z);
							int num = orderPrep.get(i);
							orderPrep.set(i, ++num);
						}

						for (int j = 0; j < preparing.get(i).getChicken().size(); j++) {
							if (((ChickenPitta) preparing.get(i).getChicken().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((ChickenPitta) preparing.get(i).getChicken().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getPork().size(); j++) {
							if (((PorkPitta) preparing.get(i).getPork().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((PorkPitta) preparing.get(i).getPork().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getSheftalia().size(); j++) {
							if (((SheftaliaPitta) preparing.get(i).getSheftalia().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((SheftaliaPitta) preparing.get(i).getSheftalia().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getMix().size(); j++) {
							if (((MixPitta) preparing.get(i).getMix().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((MixPitta) preparing.get(i).getMix().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						if (orderPrep.get(i) == 2 + (2 * preparing.get(i).getChicken().size())
								+ (2 * preparing.get(i).getPork().size()) + (2 * preparing.get(i).getMix().size())
								+ (2 * preparing.get(i).getSheftalia().size())) {
							if (time <= preparing.get(i).getReq()) {
								happy++;
							}
							counter++;
							delDev += Math.abs(time - preparing.get(i).getReq());

							delivered.add(preparing.get(i));
							delTime.add(time);
							preparing.remove(i);
							startTime.remove(i);
							orderPrep.remove(i);
						}

					}

				}
				time++;
			}

			double avgDel = 0.00;
			if (counter != 0) {
				avgDel = (double) (delDev / counter);
			}
			print(delivered, delTime, happy, avgDel);
		}

		/**
		 * Prints the delivery details to a file named "deliveries.txt".
		 * 
		 * @param delivered     The list of delivered {@link Order}.
		 * @param timeDelivered The list of times when {@link Order} were delivered.
		 * @param happy         The number of customers satisfied with their
		 *                      {@link Order}.
		 * @param avgSad        The average satisfaction level of unhappy customers.
		 */

		public void print(ArrayList<Order> delivered, ArrayList<Integer> timeDelivered, int happy, double avgSad) {

			PrintWriter output = null;
			try {
				output = new PrintWriter("deliveries.txt");
				output.printf("%d\t%.2f\t%d\n", orders.length, avgSad, happy);
				for (int i = 0; i < delivered.size(); i++) {
					output.println(delivered.get(i).getID() + "\t" + delivered.get(i).getOrderTime() + "\t"
							+ timeDelivered.get(i) + "\t" + (timeDelivered.get(i) - delivered.get(i).getReq()) + "    "
							+ (delivered.get(i).getNumOfPitta() + "\t" + delivered.get(i).getFriesSpace()));
				}
			} catch (IOException e) {
				System.out.print("input/output exception...");
				System.exit(0);
			}
			output.close();

		}

		/**
		 * This method deep copies the parameter array of {@link Order} in the attribute
		 * array of {@link Order} of the object that called the method.
		 * 
		 * @param orders the parameter to be copied.
		 */

		private void copyOrder(Order[] orders) {
			this.orders = new Order[orders.length];
			for (int i = 0; i < orders.length; i++) {
				this.orders[i] = new Order(orders[i]);
			}
		}

		/**
		 * This method adds in the parameter {@link ArrayList} waitList all the orders
		 * that have the same orderTime as the parameter time from the {@link ArrayList}
		 * fitWaitList.
		 * 
		 * @param time        The current time.
		 * @param waitList    The list with all the waiting {@link Order}.
		 * @param fitWaitList The list with all the {@link Order} of the day that can
		 *                    fit on the {@link Rotisserie} and the {@link Fryers}.
		 */

		private void getCurrentOrders(int time, ArrayList<Order> waitList, ArrayList<Order> fitWaitList) {
			for (int i = 0; i < fitWaitList.size(); i++) {
				if (fitWaitList.get(i).getOrderTime() == time) {
					waitList.add(fitWaitList.get(i));
				}
			}
		}

		/**
		 * This method finds and returns the {@link Order} with the least preparing time
		 * or if many {@link Order} have the least preparing time it returns the method
		 * with the lowest orderID.
		 * 
		 * @param waitList The list containing the {@link Order}.
		 * @return The {@link Order} that will be prepared next.
		 */

		private Order getPriority(ArrayList<Order> waitList) {

			int min = Integer.MAX_VALUE;
			ArrayList<Order> priorities = new ArrayList<Order>();

			for (int i = 0; i < waitList.size(); i++) {
				if (min >= waitList.get(i).getTime()) {
					min = waitList.get(i).getTime();
				}
			}

			for (int i = 0; i < waitList.size(); i++) {
				if (min == waitList.get(i).getTime()) {
					priorities.add(waitList.get(i));
				}
			}
			if (priorities.size() == 1) {
				return new Order(priorities.get(0));
			}

			int minID = Integer.MAX_VALUE;
			Order next = null;
			for (int i = 0; i < priorities.size(); i++) {
				if (minID > priorities.get(i).getID()) {
					minID = priorities.get(i).getID();
					next = new Order(priorities.get(i));
				}
			}
			return new Order(next);
		}

		/**
		 * Finds the priority {@link Order} from the parameter list of {@link Order}
		 * based on their preparing time and ID.
		 *
		 * @param fitWaitList The list of orders that fit into the {@link Rotisserie}
		 *                    and are waiting to be processed.
		 * @param time        The current time.
		 * @param skip        The list of {@link Order} that will be skipped because
		 *                    they don't fit in the {@link Rotisserie}.
		 * @return the order that will be prepared next.
		 */

		private Order getPriority(ArrayList<Order> fitWaitList, int time, ArrayList<Order> skip) {

			int min = Integer.MAX_VALUE;
			ArrayList<Order> priorities = new ArrayList<Order>();

			for (int i = 0; i < fitWaitList.size(); i++) {
				if (notIncluded(fitWaitList.get(i), skip) && min >= fitWaitList.get(i).getTime()) {
					min = fitWaitList.get(i).getTime();
				}
			}

			for (int i = 0; i < fitWaitList.size(); i++) {
				if (notIncluded(fitWaitList.get(i), skip) && min == fitWaitList.get(i).getTime()) {
					priorities.add(fitWaitList.get(i));
				}
			}

			if (priorities.size() == 1) {
				return new Order(priorities.get(0));
			}

			int minID = Integer.MAX_VALUE;
			Order next = null;
			for (int i = 0; i < priorities.size(); i++) {
				if (minID >= priorities.get(i).getID()) {
					minID = priorities.get(i).getID();
					next = new Order(priorities.get(i));
				}
			}
			return new Order(next);
		}

		/**
		 * Adds valid {@link Order} from the {@link Order} array to the fitWaitList list
		 * based on available spaces in the {@link Rotisserie} and the {@link Fryers}.
		 *
		 * @param unFitWaitList The list of {@link Order} that can not fit in the
		 *                      {@link Rotisserie} or the {@link Fryers}.
		 * @param fitWaitList   The list of {@link Order} that can fit in the
		 *                      {@link Rotisserie}and the {@link Fryers}.
		 * @param orders        The array of {@link Order} for the day.
		 * @param x             The required meat space dimension for the {@link Order}.
		 * @param y             The required {@link Sheftalia} space dimension for the
		 *                      {@link Order}.
		 * @param z             The required {@link Pitta} space dimension for the
		 *                      {@link Order}.
		 */

		private void addValidOrders(Order[] orders, ArrayList<Order> fitWaitList, ArrayList<Order> unFitWaitList, int x,
				int y, int z) {
			for (int i = 0; i < orders.length; i++) {
				if ((rotisserie.getRSpace() - orders[i].getMeatSpace(x, y) - orders[i].getPittaSpace(z) >= 0)
						&& (fryers.getFSpace() - orders[i].getFriesSpace() >= 0)
						&& (notIncluded(orders[i], fitWaitList))) {
					fitWaitList.add(orders[i]);
				} else if ((rotisserie.getRSpace() - orders[i].getMeatSpace(x, y) - orders[i].getPittaSpace(z) < 0)
						|| (fryers.getFSpace() - orders[i].getFriesSpace() < 0)) {
					unFitWaitList.add(orders[i]);
				}
			}
		}

	}

	/**
	 * This inner class represents the {@link WeightBased} {@link Algorithms}.
	 */
	private class WeightBased implements Algorithms {

		private Order[] orders;
		private Rotisserie rotisserie;
		private Fryers fryers;

		/**
		 * Constructs a {@link WeightBased} object with the parameter orders,
		 * rotisserie, and fryers.
		 * 
		 * @param orders     An array of {@link Order} objects.
		 * @param rotisserie A {@link Rotisserie} object.
		 * @param fryers     A {@link Fryers} object.
		 */

		public WeightBased(Order[] orders, Rotisserie rotisserie, Fryers fryers) {
			copyOrder(orders);
			this.rotisserie = rotisserie;
			this.fryers = fryers;
		}

		/**
		 * This method executes the {@link WeightBased} {@link Algorithms}. It prepares
		 * the {@link Order} based on the available {@link Order} that can be put on the
		 * {@link Rotisserie} and the {@link Fryers} that have the most weight.
		 * 
		 * @param x The space of meat needed in the {@link Rotisserie}.
		 * @param y The space of {@link Sheftalia} needed in the {@link Rotisserie}.
		 * @param z The amount of space needed for each {@link Pitta} in the
		 *          {@link Rotisserie}.
		 */

		public void algorithm(int x, int y, int z) {

			int currentR = rotisserie.getRSpace();
			int currentF = fryers.getFryers() * fryers.getFSpace();
			int happy = 0;
			int counter = 0;
			double delDev = 0;

			int time = 0;
			int fuelTime = rotisserie.getFuelTime();

			ArrayList<Order> waitList = new ArrayList<Order>();
			ArrayList<Order> preparing = new ArrayList<Order>();
			ArrayList<Integer> startTime = new ArrayList<Integer>();
			ArrayList<Integer> orderPrep = new ArrayList<Integer>();
			ArrayList<Order> delivered = new ArrayList<Order>();
			ArrayList<Integer> delTime = new ArrayList<Integer>();
			ArrayList<Order> fitWaitList = new ArrayList<Order>();
			ArrayList<Order> unFitWaitList = new ArrayList<Order>();

			Order next = null;

			addValidOrders(orders, fitWaitList, unFitWaitList, x, y, z);

			while (time <= 360) {
				if (time <= 300)
					getCurrentOrders(time, waitList, fitWaitList);

				if (time >= fuelTime - 30) {
					boolean stillFits = true;
					while (stillFits) {

						if (waitList.size() >= 1) {
							next = new Order(getPriority(waitList, time));

							ArrayList<Order> skip = new ArrayList<Order>();
							boolean stop = false;

							while (!((currentR - next.getMeatSpace(x, y) - next.getPittaSpace(z) >= 0)
									&& (currentF - next.getFriesSpace() >= 0)) && !stop) {
								skip.add(next);
								if (skip.size() != waitList.size() && waitList.size() >= 1)
									next = new Order(getPriority(waitList, time, skip));
								else {
									stop = true;
								}
							}

							if ((currentR - next.getMeatSpace(x, y) - next.getPittaSpace(z) >= 0)
									&& (currentF - next.getFriesSpace() >= 0)) {
								removeFromList(waitList, next);
								currentR = currentR - next.getMeatSpace(x, y) - next.getPittaSpace(z);
								currentF = currentF - next.getFriesSpace();
								preparing.add(next);
								startTime.add(time);
								orderPrep.add(0);
							} else {
								stillFits = false;
							}
						} else {
							stillFits = false;
						}
					}

					for (int i = 0; i < preparing.size(); i++) {

						if (20 + startTime.get(i) == time) {
							currentF = currentF + preparing.get(i).getFriesSpace();
							int num = orderPrep.get(i);
							orderPrep.set(i, ++num);
						}

						if (5 + startTime.get(i) == time) {
							currentR = currentR + preparing.get(i).getPittaSpace(z);
							int num = orderPrep.get(i);
							orderPrep.set(i, ++num);
						}

						for (int j = 0; j < preparing.get(i).getChicken().size(); j++) {
							if (((ChickenPitta) preparing.get(i).getChicken().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((ChickenPitta) preparing.get(i).getChicken().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getPork().size(); j++) {
							if (((PorkPitta) preparing.get(i).getPork().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((PorkPitta) preparing.get(i).getPork().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getSheftalia().size(); j++) {
							if (((SheftaliaPitta) preparing.get(i).getSheftalia().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((SheftaliaPitta) preparing.get(i).getSheftalia().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						for (int j = 0; j < preparing.get(i).getMix().size(); j++) {
							if (((MixPitta) preparing.get(i).getMix().get(j)).getTimeStick1()
									+ startTime.get(i) == time) {
								currentR = currentR + x;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
							if (((MixPitta) preparing.get(i).getMix().get(j)).getTimeStick2()
									+ startTime.get(i) == time) {
								currentR = currentR + y;
								int num = orderPrep.get(i);
								orderPrep.set(i, ++num);
							}
						}

						if (orderPrep.get(i) == 2 + (2 * preparing.get(i).getChicken().size())
								+ (2 * preparing.get(i).getPork().size()) + (2 * preparing.get(i).getMix().size())
								+ (2 * preparing.get(i).getSheftalia().size())) {
							if (time <= preparing.get(i).getReq()) {
								happy++;
							}
							counter++;
							delDev += Math.abs(time - preparing.get(i).getReq());

							delivered.add(preparing.get(i));
							delTime.add(time);
							preparing.remove(i);
							startTime.remove(i);
							orderPrep.remove(i);
						}

					}

				}
				time++;
			}

			double avgDel = 0.00;
			if (counter != 0) {
				avgDel = (double) (delDev / counter);
			}

			print(delivered, delTime, happy, avgDel);
		}

		/**
		 * Prints the delivery details to a file named "deliveries.txt".
		 * 
		 * @param delivered     The list of delivered {@link Order}.
		 * @param timeDelivered The list of times when {@link Order} were delivered.
		 * @param happy         The number of customers satisfied with their
		 *                      {@link Order}.
		 * @param avgSad        The average satisfaction level of unhappy customers.
		 */

		public void print(ArrayList<Order> delivered, ArrayList<Integer> timeDelivered, int happy, double avgSad) {

			PrintWriter output = null;
			try {
				output = new PrintWriter("deliveries.txt");
				output.printf("%d\t%.2f\t%d\n", orders.length, avgSad, happy);
				for (int i = 0; i < delivered.size(); i++) {
					output.println(delivered.get(i).getID() + "\t" + delivered.get(i).getOrderTime() + "\t"
							+ timeDelivered.get(i) + "\t" + (timeDelivered.get(i) - delivered.get(i).getReq()) + "    "
							+ (delivered.get(i).getNumOfPitta() + "\t" + delivered.get(i).getFriesSpace()));
				}
			} catch (IOException e) {
				System.out.print("input/output exception...");
				System.exit(0);
			}
			output.close();

		}

		/**
		 * This method deep copies the parameter array of {@link Order} in the attribute
		 * array of {@link Order} of the object that called the method
		 * 
		 * 
		 * @param orders the parameter object to be copied.
		 */

		private void copyOrder(Order[] orders) {
			this.orders = new Order[orders.length];
			for (int i = 0; i < orders.length; i++) {
				this.orders[i] = new Order(orders[i]);
			}
		}

		/**
		 * This method adds in the parameter {@link ArrayList} waitList all the orders
		 * that have the same orderTime as the parameter time from the {@link ArrayList}
		 * fitWaitList.
		 * 
		 * @param time        The current time.
		 * @param waitList    The list with all the waiting {@link Order}.
		 * @param fitWaitList The list with all the {@link Order} of the day that can
		 *                    fit on the {@link Rotisserie} and the {@link Fryers}.
		 */

		private void getCurrentOrders(int time, ArrayList<Order> waitList, ArrayList<Order> fitWaitList) {
			for (int i = 0; i < fitWaitList.size(); i++) {
				if (fitWaitList.get(i).getOrderTime() == time) {
					waitList.add(fitWaitList.get(i));
				}
			}
		}

		/**
		 * This method finds and returns the {@link Order} with the most weight or if
		 * many {@link Order} have the same weight it returns the method with the lowest
		 * orderID.
		 * 
		 * 
		 * @param waitList The awaiting {@link Order} to be prepared.
		 * @param time     The current time.
		 * @return The {@link Order} that will be prepared next.
		 */

		private Order getPriority(ArrayList<Order> waitList, int time) {

			double max = -99999;
			ArrayList<Order> priorities = new ArrayList<Order>();

			for (int i = 0; i < waitList.size(); i++) {
				if (max <= waitList.get(i).getWeight(time)) {
					max = waitList.get(i).getWeight(time);
				}
			}

			for (int i = 0; i < waitList.size(); i++) {
				if (max == waitList.get(i).getWeight(time)) {
					priorities.add(waitList.get(i));
				}
			}
			if (priorities.size() == 1) {
				return new Order(priorities.get(0));
			}

			int minID = Integer.MAX_VALUE;
			Order next = null;
			for (int i = 0; i < priorities.size(); i++) {
				if (minID > priorities.get(i).getID()) {
					minID = priorities.get(i).getID();
					next = new Order(priorities.get(i));
				}
			}
			return new Order(next);
		}

		/**
		 * Finds the priority {@link Order} from the waitList based on their weight at
		 * the given time. If many {@link Order} have the same weight, it returns the
		 * {@link Order} with the lowest order ID.
		 *
		 * @param waitList The list of {@link Order} waiting to be processed.
		 * @param time     The current time.
		 * @param skip     The list of {@link Order} that should be skipped because they
		 *                 don't fit in the {@link Rotisserie}.
		 * @return The {@link Order} that will be prepared next.
		 */

		private Order getPriority(ArrayList<Order> waitList, int time, ArrayList<Order> skip) {

			double max = -99999;
			ArrayList<Order> priorities = new ArrayList<Order>();

			for (int i = 0; i < waitList.size(); i++) {
				if (notIncluded(waitList.get(i), skip) && max <= waitList.get(i).getWeight(time)) {
					max = waitList.get(i).getWeight(time);
				}
			}

			for (int i = 0; i < waitList.size(); i++) {
				if (notIncluded(waitList.get(i), skip) && max == waitList.get(i).getWeight(time)) {
					priorities.add(waitList.get(i));
				}
			}
			if (priorities.size() == 1) {
				return new Order(priorities.get(0));
			}

			int minID = Integer.MAX_VALUE;
			Order next = null;
			for (int i = 0; i < priorities.size(); i++) {
				if (minID > priorities.get(i).getID()) {
					minID = priorities.get(i).getID();
					next = new Order(priorities.get(i));
				}
			}
			return new Order(next);
		}

		/**
		 * Adds valid {@link Order} from the {@link Order} array to the fitWaitList list
		 * based on available spaces in the {@link Rotisserie} and the {@link Fryers}.
		 *
		 * @param unFitWaitList The list of {@link Order} that can not fit in the
		 *                      {@link Rotisserie} or the {@link Fryers}.
		 * @param fitWaitList   The list of {@link Order} that can fit in the
		 *                      {@link Rotisserie}and the {@link Fryers}.
		 * @param orders        The array of {@link Order} for the day.
		 * @param x             The required meat space dimension for the {@link Order}.
		 * @param y             The required {@link Sheftalia} space dimension for the
		 *                      {@link Order}.
		 * @param z             The required {@link Pitta} space dimension for the
		 *                      {@link Order}.
		 */

		private void addValidOrders(Order[] orders, ArrayList<Order> fitWaitList, ArrayList<Order> unFitWaitList, int x,
				int y, int z) {
			for (int i = 0; i < orders.length; i++) {
				if ((rotisserie.getRSpace() - orders[i].getMeatSpace(x, y) - orders[i].getPittaSpace(z) >= 0)
						&& (fryers.getFSpace() - orders[i].getFriesSpace() >= 0)
						&& (notIncluded(orders[i], fitWaitList))) {
					fitWaitList.add(orders[i]);
				} else if ((rotisserie.getRSpace() - orders[i].getMeatSpace(x, y) - orders[i].getPittaSpace(z) < 0)
						|| (fryers.getFSpace() - orders[i].getFriesSpace() < 0)) {
					unFitWaitList.add(orders[i]);
				}
			}
		}

	}

	/**
	 * Checks if an {@link Order} is not included in a parameter list.
	 *
	 * @param order The {@link Order} to be checked if it's in the
	 *              {@link ArrayList}.
	 * @param list  The list to check if the {@link Order} is stored in.
	 * @return true if the {@link Order} is not included in the list, false
	 *         otherwise.
	 */
	private boolean notIncluded(Order order, ArrayList<Order> list) {
		for (int i = 0; i < list.size(); i++) {
			if (order.getID() == list.get(i).getID())
				return false;
		}
		return true;
	}

	/**
	 * Removes the specified {@link Order} from the given list.
	 *
	 * @param list  The list from which the {@link Order} will be removed.
	 * @param order The {@link Order} to be removed from the list.
	 */
	private void removeFromList(ArrayList<Order> list, Order order) {
		for (int i = 0; i < list.size(); i++) {
			if (order.getID() == list.get(i).getID()) {
				list.remove(i);
			}
		}
	}

}
