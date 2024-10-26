import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * This class implements a UDP client that can connect to a UDP server and exchange messages.
 */
public class UDPClient extends Thread {
    private DatagramSocket mysocket; // Client's socket
    private InetAddress hostAddress; // Server's address
    private byte[] inbuf = new byte[1000]; // Buffer to store incoming data
    private byte[] outbuf; // Buffer to store outgoing data
    private DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length); // Datagram packet for receiving data
    private static final int SERVER_PORT = 6666; // Port on which the server is listening

    /**
     * Constructor for the UDPClient class.
     * Initializes the client socket and connects to the server.
     */
    public UDPClient() {
        try {
            mysocket = new DatagramSocket(); // Create a new DatagramSocket
            hostAddress = InetAddress.getByName("localhost"); // Get the server's address
            mysocket.connect(hostAddress, SERVER_PORT); // Connect to the server

            System.out.println("Connected to the server.");

            Scanner scanner = new Scanner(System.in); // Scanner to read user input
            boolean chatting = true; // Flag to indicate if the chat is active

            // Main client loop
            while (chatting) {
                // Receive message from the server
                mysocket.receive(dp); // Receive data from the server
                String serverMessage = new String(dp.getData(), 0, dp.getLength()); // Extract the message from the received data
                System.out.println("Server: " + serverMessage); // Print the server's message

                // If the server message contains "END", end the chat
                if (serverMessage.contains("END")) {
                    chatting = false;
                    System.out.println("Chat ended.");
                    break; // Exit the client loop
                } else if (serverMessage.contains("You can start chatting") || serverMessage.contains("connected")) {
                    // Client is prompted to start typing a message
                    System.out.print("Your turn: ");
                    String message = scanner.nextLine(); // Read the user's message
                    outbuf = message.getBytes(); // Convert the message to bytes
                    mysocket.send(new DatagramPacket(outbuf, outbuf.length, hostAddress, SERVER_PORT)); // Send the message to the server

                    // If the user typed "END", end the chat
                    if (message.equals("END")) {
                        chatting = false;
                    }
                }
            }
            scanner.close(); // Close the scanner
            mysocket.close(); // Close the client socket

        } catch (IOException e) {
            System.err.println("Communication error!");
            e.printStackTrace();
        }
    }

    /**
     * Main method of the UDPClient class.
     * Creates a new instance of the UDPClient class.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new UDPClient();
    }
}
