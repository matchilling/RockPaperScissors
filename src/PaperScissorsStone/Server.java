/**
 * The server class waits for the connection of the two clients client_1
 * and client_2 (in either order) on port 1337. Receives one character
 * from client_1 and one character from client_2 (in either order) and
 * calculates the winner of the game based on a rule set. After sending a
 * correspondent massage to each client the server waits again for two
 * clients to connect.
 *
 * @author Mathias Schilling <https://github.com/pinkbigmacmedia>
 * @version 1.0
 * 
 */

package PaperScissorsStone;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

	/**
	 * The defautl port number
	 * 
	 * @var integer
	 * 
	 */
	private static Integer port = 1337;

	/**
	 * The script version number
	 * 
	 * @var integer
	 * 
	 */
	private static Double versionNumber = 1.0;

	/**
	 * The welcome boiler plate
	 * 
	 * @var string
	 * 
	 */
	private static String welcomeMsg = "--- Welcome to Paper Scissors Stone Server V. " + versionNumber + " --- \n";

	/**
	 * Function takes an integer x as an input and returns the boolean value
	 * true if the input is strictly greater than 0 and less than or equal to
	 * 65535.
	 * 
	 * @param integer
	 *            x
	 * @return boolean
	 */
	private static boolean validPort(Integer x) {
		return x >= 1 && x <= 65535 ? true : false;
	}

	/**
	 * Function prompts the user to choose a specific port number or to press
	 * enter in order to continue with default setting (Server.port).
	 * 
	 * The returned integer strictly greater than 0 and less than or equal to
	 * 65535.
	 * 
	 * @return integer
	 */
	private static int getPort() {

		Integer input;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.print("Please select a port by entering an integer value between 1 and 65535 or\n");
			System.out.print("insert \"0\" in order to continue with the default setting (" + Server.port + "): ");
			input = sc.nextInt();

		} while (input != 0 && !Server.validPort(input));

		sc.close();

		return input == 0 ? Server.port : input;
	}

	public static void main(String args[]) throws Exception {

		String resClient_1 = "";
		String resClient_2 = "";
		String inputClient_1;
		String inputClient_2;

		// Print welcome msg
		System.out.println(Server.welcomeMsg);

		// Set port
		Server.port = Server.getPort();

		// Create new server socket & dump out a status msg
		ServerSocket welcomeSocket = new ServerSocket(Server.port);
		System.out.println("\nOk, we're up and running on port " + welcomeSocket.getLocalPort() + " ...");

		while (!welcomeSocket.isClosed()) {

			// Player one
			Socket client_1 = welcomeSocket.accept();
			if (client_1.isConnected()) {
				System.out.println("\nPlayer one (" + (client_1.getLocalAddress().toString()).substring(1) + ":"
						+ client_1.getLocalPort() + ") has joined ... waiting for player two ...");
			}
			DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
			BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

			// Player two
			Socket client_2 = welcomeSocket.accept();
			if (client_2.isConnected()) {
				System.out.println("Player two (" + (client_2.getLocalAddress().toString()).substring(1) + ":"
						+ client_1.getLocalPort() + ") has joined ... lets start ...");
			}
			DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
			BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

			// Get client inputs
			inputClient_1 = inClient_1.readLine();
			inputClient_2 = inClient_2.readLine();

			/**
			 * If the characters received from C1 and C2 are the same then the
			 * server sends back to both clients the string "DRAW".
			 */
			if (inputClient_1.equals(inputClient_2)) {
				resClient_1 = "Draw";
				resClient_2 = "Draw";
				System.out.println("It's a draw.");
			}
			/**
			 * If the server receives ’R’ from C1 and ’S’ from C2 it sends the
			 * string "YOU WIN" to C1 and the string "YOU LOSE" to C2.
			 */
			else if (inputClient_1.equals("R") && inputClient_2.equals("S")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");

			}
			/**
			 * If the server receives ’S’ from C1 and ’R’ from C2 it sends the
			 * string "YOU LOSE" to C1 and the string "YOU WIN" to C2.
			 */
			else if (inputClient_1.equals("S") && inputClient_2.equals("R")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two wins.");
			}
			/**
			 * If the server receives ’R’ from C1 and ’P’ from C2 it sends the
			 * string "YOU LOSE" to C1 and the string "YOU WIN" to C2.
			 */
			else if (inputClient_1.equals("R") && inputClient_2.equals("P")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two wins.");
			}
			/**
			 * If the server receives ’P’ from C1 and ’R’ from C2 it sends the
			 * string "YOU WIN" to C1 and the string "YOU LOSE" to C2.
			 */
			else if (inputClient_1.equals("P") && inputClient_2.equals("R")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");
			}
			/**
			 * If the server receives ’S’ from C1 and ’P’ from C2 it sends the
			 * string "YOU WIN" to C1 and the string "YOU LOSE" to C2.
			 */
			else if (inputClient_1.equals("S") && inputClient_2.equals("P")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");
			}
			/**
			 * If the server receives ’P’ from C1 and ’S’ from C2 it sends the
			 * string "YOU LOSE" to C1 and the string "YOU WIN" to C2.
			 */
			else if (inputClient_1.equals("P") && inputClient_2.equals("S")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two wins.");
			}

			// Send responses in uppercase and close sockets
			outClient_1.writeBytes(resClient_1.toUpperCase());
			outClient_2.writeBytes(resClient_2.toUpperCase());
			client_1.close();
			client_2.close();

			System.out.println("\nWaiting for new players ...\n");

		}
	}
}