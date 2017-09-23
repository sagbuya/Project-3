import java.util.Scanner;

public class Shop {

	private static String[] names;
	private static double[] prices;
	private static int[] amount;
	private static double[] discount;
	private static double[] discountrate;

	private static boolean isSetup = false;
	private static boolean isBuy = false;
	private static boolean isListItems = false;
	private static boolean isCheckout = false;

	public static void Setup(){
		isSetup = true;
		Scanner input = new Scanner(System.in);		
		System.out.println("Please enter the number of items: ");
		int max = input.nextInt();

		names = new String[max];
		prices = new double[max];
		amount = new int[max];
		discount = new double[1];
		discountrate = new double[1];

		for (int i = 0; i < max; i++){			
			System.out.println("Enter name of product " + i + ":");
			names[i] = input.next();

			System.out.println("Enter price of product " + i + ":"); 
			prices[i] = input.nextDouble();			
		}

		for (int i = 0; i < 1; i++){
			System.out.println("Please enter the amount to qualify for discount: ");
			discount[i] = input.nextDouble();

			System.out.println("Please enter the discount rate(0.1 for 10%): ");
			discountrate[i] = input.nextDouble();
		}
	}

	public static void Buy(String[] names, int choice, int max, int[] amount){
		Scanner input = new Scanner(System.in);
		isBuy = true;
		if(isSetup == false){
			System.out.println("Shop is not setup yet! ");
		}
		else {
			for(int i = 0; i < max; i++){
				System.out.println("Enter the amount of " + names[i] + ":");
				amount[i] = input.nextInt();
			}
		}
	}

	public static void ListItems(String[] names, int max, int[] amount, double[] prices){
		isListItems = true;
		if(isSetup  == false){
			System.out.println("Shop is not setup yet! ");
		}
		else if(isBuy  == false){
			System.out.println("Try again: You have not bought anything ");
		}
		else {
			for(int i = 0;i < max; i++){
				System.out.println(amount[i] + " count of " + names[i] + " @ " + prices[i] + " = " + "$" + (amount[i] * prices[i]));
			}
		}
	}
	public static void Checkout(double[] discount, double discountTotal, double[]discountrate, double[] prices, double subtotal, int[] amount, int max){
		isCheckout = true;
		if (isSetup == false){
			System.out.println("Shop is not setup yet! ");
		}
		else if(isBuy == false){
			System.out.println("Try again: You have not bought anything ");
		}
		else if(isListItems == false){
			System.out.println("Try again: You have not bought anything ");
		}
		else {
			for(int i = 0; i < max; i++){
				subtotal = subtotal + amount[i] * prices[i];
			}
			for(int i = 0; i < 1; i++){
				if(subtotal > discount[i]){
					discountTotal = subtotal - (discount[i] * discountrate[i]);
					System.out.println("Thanks for coming!");
					System.out.println("Sub Total: " + "$" + subtotal);
					System.out.println("-Discount: " + "$" + subtotal * discountrate[i]);
					System.out.println("Total    : " + "$" + (subtotal-(subtotal * discountrate[i])));
				}
				else if (subtotal < discount[i]){
					System.out.println("Thanks for coming! ");
					System.out.println("Sub Total: " + "$"+ subtotal);
					System.out.println("-Discount: " + "$0");
					System.out.println("Total    : " + "$"+ subtotal);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int max = 2;
		double discountTotal = 0;
		double subtotal = 0;

		while(true){
			System.out.println("\nThis program supports 4 functions:");
			System.out.println("1. Setup Shop");
			System.out.println("2. Buy");
			System.out.println("3. List Items");
			System.out.println("4. Checkout");
			System.out.println("Please choose the function you want: ");
			int choice = input.nextInt();

			if(choice == 1){
				Setup();
			}	
			if(choice == 2){    
				Buy(names, choice, max, amount);
			}
			if(choice == 3){
				ListItems(names, max, amount, prices);
			}
			if(choice == 4){
				Checkout(discount, discountTotal, discountrate, prices, subtotal, amount, max);
				break; 
			}
		}
	}
}

