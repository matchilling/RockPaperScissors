/**
 * The client class creates a connection to the server at default port 1337.
 * Waits for the user to input a single character with the keyboard. The
 * character has to be ’R’ (rock), ’P’ (paper) or ’S’ (scissors). Sends the
 * character to the server via the TCP protocol. Waits for a reply from the
 * Server. Prints the message received from the Server. Closes the connection.
 *
 * @author Mathias Schilling <https://github.com/pinkbigmacmedia>
 * @version 1.0
 * 
 */

package PaperScissorsStone;

import java.io.*;
import java.net.*;

class Client {

    /**
     * The host
     * 
     * @var string
     * 
     */
    private static String host = "localhost";

    /**
     * The port
     * 
     * @var integer
     */
    private static Integer port = 1337;

    /**
     * The version of the client class
     * 
     * @var double
     */
    private static Double versionNumber = 1.0;

    /**
     * A short welcome msg
     * 
     * @var string
     */
    private static String msgWelcome = "--- Welcome to Paper Scissors Stone V. "
	    + versionNumber + " --- \n";

    /**
     * The help context
     * 
     * @var string
     * 
     */
    private static String msgRules = "\nRule set:\n - (R)ock beats (S)cissors\n - (S)cissors beats (P)aper\n - (P)aper beats (R)ock\n";

    public static void main(String args[]) throws Exception {

	String input = "";
	String response;

	System.out.println(Client.msgWelcome);

	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
		System.in));
	Socket clientSocket = new Socket(Client.host, Client.port);
	DataOutputStream outToServer = new DataOutputStream(
		clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
		clientSocket.getInputStream()));

	do {

	    if (input.equals("-rules")) {
		System.out.println(Client.msgRules);
	    }

	    // Prompt user for select rock, paper or scissors ...
	    System.out
		    .println("Start the game by selecting (R)ock (P)aper, (S)cissors");
	    System.out.print("or type \"-rules\" in order to see the rules: ");
	    input = inFromUser.readLine();

	} while (!input.equals("R") && !input.equals("P") && !input.equals("S"));

	// Transmit input to the server and provide some feedback for the user
	outToServer.writeBytes(input + "\n");
	System.out
		.println("\nYour input ("
			+ input
			+ ") was successfully transmitted to the server. Now just be patient and wait for the result ...");

	// Catch respones
	response = inFromServer.readLine();

	// Display respones
	System.out.println("Response from server: " + response);

	// Close socket
	clientSocket.close();

    }
}
