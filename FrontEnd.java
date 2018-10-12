/*
 * WHAT NEED
 * Methods for each transaction
 *		login
 *		logout
 *		createservice
 *		deleteservice
 *		sellticket
 *		cancelticket
 *		changeticket
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class FrontEnd {

	private static Scanner consoleInput = new Scanner(System.in);
	private static boolean PLANNER = false;
	private static boolean AGENT = false;
	private static boolean LOGIN = false;
	private static boolean LOGOUT = false;
	private static boolean CREATESERVICE = false;
	private static boolean DELETESERVICE = false;
	private static boolean SELLTICKET = false;
	private static boolean CANCELTICKET = false;
	private static boolean CHANGETICKET = false;

	// Main method that continously reads input to the console until user has successfully logged in and logged out
	public static void main(String[] args) {
		while (!LOGIN) {
			String prompt = "Login to start: ";
			String input = FrontEnd.getString(prompt);
			while (!input.equals("login")) {
				input = FrontEnd.getString(prompt);
			}
			System.out.println("login accepted");
			login(); //true = planner, false = agent, won't continue until agent or planner is selected
			LOGIN = true;
		}
		System.out.println("out of first while loop");

		while (!LOGOUT) {
			String input = FrontEnd.getString();
			while (PLANNER || AGENT) {
				switch (input) {
				case "login": login(); //call login(). If already logged in, ignore
				break;
				case "logout": PLANNER = false; AGENT = false;
				break;
				case "createservice": createservice();
				break;
				case "deleteservice": deleteservice();
				break;
				case "sellticket": sellticket();
				break;
				case "cancelticket": cancelticket();
				break;
				case "changeticket": changeticket();
				break;
				}
				if (PLANNER || AGENT)
					input = FrontEnd.getString();
			}
			LOGOUT = true;
			logout();
		}
	}

	// Login method that prompts for session ("agent" or "planner") and changes a global flag
	public static void login() {
		if (LOGIN) {
			System.out.println("Already logged in.");
			return;
		}
		String prompt = "Choose session: ";
		String input = FrontEnd.getString(prompt);
		while (!input.equals("planner") && !input.equals("agent")) {
			input = FrontEnd.getString(prompt);
		}
		if (input.equals("planner"))
			PLANNER = true;
		else if (input.equals("agent"))
			AGENT = true;
		//return;
	}

	public static void logout() {
		// writes to Transaction Summary File
	}

	public static void createservice() {
		if (AGENT) {
			System.out.println("Insufficient permission.");
		}
		CREATESERVICE = true;
		// functionality in here
		CREATESERVICE = false;
	}

	public static void deleteservice() {
		if (AGENT) {
			System.out.println("Insufficient permission.");
		}
		DELETESERVICE = true;
		// functionality in here
		DELETESERVICE = false;
	}

	// Asks for service number and number of tickets. If service number input has leading zero(s)
	// getInt() will remove them
	public static void sellticket() {
		SELLTICKET = true;
		String prompt = "Service number: ";
		int min = 10000;
		int max = 99999;
		int serviceNum = FrontEnd.getInt(min, prompt, max);
		prompt = "Number of tickets: ";
		int ticketNum = FrontEnd.getInt(1, prompt, 1000);
		System.out.println("out of sellticket");
		SELLTICKET = false;
	}

	public static void cancelticket() {

	}

	public static void changeticket() {

	}

	public static String getString(String prompt) {
		String userText;
		System.out.print(prompt);
		userText = consoleInput.nextLine();
		return userText;
	} // end one parameter getString method

	public static String getString() {
		String userText;
		userText = consoleInput.nextLine();
		return userText;
	} // end one parameter getString method

	public static int getInt() {
		int low = Integer.MIN_VALUE;
		int high = Integer.MAX_VALUE;
		String prompt = "Please enter any integer: ";
		return getInt(low, prompt, high);
	} // end no parameter getInt method

	public static int getInt(int low, String prompt, int high) {
		int numFromUser = 0;
		String dummy;
		boolean numericEntryOK;
		do {
			System.out.print(prompt);
			numericEntryOK = false;
			try {
				numFromUser = consoleInput.nextInt();
				numericEntryOK = true;
			} catch (InputMismatchException e) {
				dummy = consoleInput.nextLine();
				System.out.println(dummy + " is not an integer!");
				numFromUser = low;
			} // end try-catch
			// Indicate to the user why he is being prompted again.
			if (numFromUser < low || numFromUser > high) {
				System.out.println("The number is outside the legal limits.");
				continue;
			}
			System.out.println(numFromUser);
		} while (!numericEntryOK || numFromUser < low || numFromUser > high);
		return numFromUser;
	} // end full parameter getInt method

}
