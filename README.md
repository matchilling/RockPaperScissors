# RockPaperScissors TCP network edition in JAVA
The purpose of the program is to provide a network version of the hand game [Rock-paper-scissors](https://en.wikipedia.org/wiki/Rock-paper-scissors) :fist: :hand: :v: using the [TCP protocol](https://en.wikipedia.org/wiki/Transmission_Control_Protocol).

## The server class – Server.java
![Execution of the server class script (Server.java)](/resources/images/Server.png "Execution of the server class script (Server.java)")
Illustr. 1: Execution of the server class script (Server.java)

By executing the server class (Server.java) the user will be prompt to specify a port number which has to be an integer value strictly greater than zero and less than or equal to 65535. Alternatively “0” is accepted for the standard port (1337) . After submitting the script runs a little validation and checks if the value is within the defined range (0 > x <= 65535) and sets the port value. So far no further validation is implemented, we're not verifying for example if the selected port is reserved or just busy at the moment. If no exception is thrown the program dumps a status message to inform the user, that the server is running on the specified port number.

Since the socket is not closed the server remains in a while() loop waiting for two incoming connections (player one and player two). A corresponding status message will be provided if a new client connects.

The server accepts an input stream from both clients. Once the players have sent their packets, the program computes a result based on the user inputs (R – rock :fist:, S – scissors :v:, P - paper :hand:) and the following rule set. Rock beats scissors, scissors beats paper, paper beats rock.

If the characters received from client one and client two are the same, then the server sends back to both clients the string "DRAW". If the server receives “R” from client one and “S” from client two, it sends the string “YOU WIN" to client one and the string "YOU LOSE" to client two. If the server receives “S” from client one and “R” from client two, it sends the string "YOU LOSE" to client one and the string "YOU WIN" to client two.

The following table shows all nine possible combinations.

<table>
	<thead>
		<tr>
			<th colspan="2">Input</th>
			<th colspan="2">Result</th>
		</tr>
		<tr>
			<th>Client 1</th>
			<th>Client 2</th>
			<th>Client 1</th>
			<th>Client 2</th>
		</tr>	
	</thead>
	<tbody>
		<tr>
			<td>R</td>
			<td>R</td>
			<td>Draw</td>
			<td>Draw</td>
		</tr>
		<tr>
			<td>S</td>
			<td>S</td>
			<td>Draw</td>
			<td>Draw</td>
		</tr>
		<tr>
			<td>P</td>
			<td>P</td>
			<td>Draw</td>
			<td>Draw</td>
		</tr>
		<tr>
			<td>R</td>
			<td>S</td>
			<td>You Win</td>
			<td>You Lose</td>
		</tr>
		<tr>
			<td>S</td>
			<td>R</td>
			<td>You Lose</td>
			<td>You Win</td>
		</tr>
		<tr>
			<td>R</td>
			<td>P</td>
			<td>You Lose</td>
			<td>You Win</td>
		</tr>
		<tr>
			<td>P</td>
			<td>R</td>
			<td>You Win</td>
			<td>You Lose</td>
		</tr>
		<tr>
			<td>S</td>
			<td>P</td>
			<td>You Win</td>
			<td>You Lose</td>
		</tr>
		<tr>
			<td>P</td>
			<td>S</td>
			<td>You Lose</td>
			<td>You Win</td>
		</tr>
	</tbody>
</table>

The result will be echoed out and a correspondent massage will be send to each client. Afterwards the server waits again for two clients to connect.

## The client class – Client.java
![Client one chooses "R" and wins](/resources/images/Client1.png "Client one chooses "R" and wins")
Illustr. 2: Client one chooses "R" and wins

![Client two chooses "S" and loses](/resources/images/Client2.png "Client two chooses "S" and loses")
Illustr. 3: Client two chooses "S" and loses

The client class creates a connection to the server on the local host at the default port 1337. If the connection has been successfully established the script prompts the user to choose a correspondent character to (R)ock, (P)aper or (S)cissors. By typing “-rules” the rule set can be displayed alternatively. After sending the character to the server via the TCP protocol the client waits for a reply from the server and a notification will be dumped.

Once the client receives a response from the server the message will be printed to the screen and the connection will be closed.

## License

The MIT License (MIT)

Copyright (c) 2015 Mathias Schilling <info@pinkbigmacmedia.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished
to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Support or Contact

Having trouble with this repository? Check out the documentation at the repository's site or contact info@[pinkbigmacmedia.com](http://www.pinkbigmacmedia.com/) and we’ll help you sort it out.

*Happy Coding*
