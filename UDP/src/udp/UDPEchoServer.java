
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package udp;

/**
 *
 * 
 */
import java.io.*;
import java.net.*;

public class UDPEchoServer
{
    private static final int PORT = 1234;
    private static DatagramSocket dgramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args) {
	System.out.println("Opening port...\n");
	try {
            dgramSocket = new DatagramSocket(PORT);//Step 1.
	} catch(SocketException e) {
            System.out.println("Unable to attach to port!");
	    System.exit(1);
	}
	run();
	}

	private static void run(){
	try {
            String messageIn,messageOut;
            int numMessages = 0;

	do {
            buffer = new byte[256]; 		//Step 2.
            inPacket = new DatagramPacket(buffer, buffer.length); //Step 3.
            dgramSocket.receive(inPacket);	//Step 4.

            InetAddress clientAddress = inPacket.getAddress();	//Step 5.
            int clientPort = inPacket.getPort();		//Step 5.

            messageIn = new String(inPacket.getData(),
                                 0,
                                 inPacket.getLength());	//Step 6.

            System.out.println("Message received.");
            numMessages++;
            messageOut = ("Message " + numMessages+ ": " + messageIn);
            outPacket = new DatagramPacket(messageOut.getBytes(),
                                         messageOut.length(),
                                         clientAddress,	
                                         clientPort);		//Step 7.
            dgramSocket.send(outPacket);	//Step 8.
        }while (true);
    } catch(IOException e){
	e.printStackTrace();
	} finally {		//If exception thrown, close connection.
            System.out.println("\n* Closing connection... *");
            dgramSocket.close();				//Step 9.
	}
    }
}


/* The Client Process

Setting up the corresponding client process requires the eight steps below.

Step 1:  Create a DatagramSocket object

Step 2:  Create the outgoing datagram

Step 3:  Send the datagram message

Step 4:  Create a buffer for incoming datagrams

Step 5:  Create a DatagramPacket object for the incoming datagrams

Step 6:  Accept an incoming datagram

Step 7:  Retrieve the data from the buffer

Step 9:  Close the DatagramSocket*/



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorialudp;

/**
 *
 *
 */
import java.io.*;
import java.net.*;

public class UDPEchoClient
{
private static InetAddress host;
private static final int PORT = 1234;
private static DatagramSocket dgramSocket;
private static DatagramPacket inPacket, outPacket;
private static byte[] buffer;

public static void main(String[] args) {
try {
    host = InetAddress.getLocalHost();
} catch(UnknownHostException e) {
    System.out.println("Host ID not found!");
    System.exit(1);
    }
run();
}

private static void run() {
    try {
        dgramSocket = new DatagramSocket();		//Step 1.
	//Set up stream for keyboard entry...
	BufferedReader userEntry = new BufferedReader(
                                    new InputStreamReader(System.in));
	String message=null;
        String response=null;
	do {
            System.out.print("Enter message: ");
            message = userEntry.readLine();
            if (!message.equals("***CLOSE***")) {
		outPacket = new DatagramPacket(
				message.getBytes(),
                        	message.length(),
                                host,
                                PORT);	//Step 2.
		dgramSocket.send(outPacket);	//Step 3.
				buffer = new byte[256];		//Step 4.
				inPacket = new DatagramPacket(
				buffer, 
                                buffer.length); 		//Step 5.
		dgramSocket.receive(inPacket);	//Step 6.
		response = new String(inPacket.getData(),
                                    0, 
                                    inPacket.getLength());		//Step 7.
		System.out.println("\nSERVER> " + response);
            }
	}while (!message.equals("***CLOSE***"));
    } catch(IOException e) {
        e.printStackTrace();
        } finally {
            System.out.println("\n* Closing connection... *");
            dgramSocket.close();					//Step 8.
        }
}
}
Compile the above server and client code. In order to start the application first open two command windows. Start the server running in one window and the client in the other. (Make sure that the server is running first in order to avoid having the client program crash).


Note in NetBeans in order to stop the TCPEchoServer program click on the red box to the left of the output window. Ctrl-C may have to be entered from the keyboard for other environments.

