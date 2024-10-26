import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a UDP server that facilitates a chat between two clients.
 */
public class UDPServer {
    static final int INPORT = 6666; // Port on which the server listens for connections
    private byte[] inbuf = new byte[1000]; // Buffer to store incoming data
    private byte[] outbuf; // Buffer to store outgoing data
    private DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length); // Datagram packet for receiving data
    private DatagramSocket mysocket; // Server's socket
    private List<InetSocketAddress> clients = new ArrayList<>(); // List to store connected clients
    private int currentClientIndex = 0; // Index of the client whose turn it is to send a message

    /**
     * Constructor for the UDPServer class.
     * Initializes the server socket and starts listening for client connections.
     */
    public UDPServer() {
        try {
            mysocket = new DatagramSocket(INPORT); // Create a new DatagramSocket on the specified port
            System.out.println("Server is up and running...");

            // Main server loop
            while (true) {
                mysocket.receive(dp); // Receive data from a client
                String message = new String(dp.getData(), 0, dp.getLength()); // Extract the message from the received data
                InetSocketAddress clientAddress = new InetSocketAddress(dp.getAddress(), dp.getPort()); // Get the client's address

                // If the client is new, add it to the list of clients
                if (!clients.contains(clientAddress)) {
                    clients.add(clientAddress);
                    System.out.println("New client connected: " + clientAddress);

                    // Notify the first client that it's waiting for a second client
                    if (clients.size() == 1) {
                        sendMessage("Waiting for another client to connect...", clientAddress);
                    }
                    // Notify both clients that they can start exchanging messages
                    else if (clients.size() == 2) {
                        sendMessage("Second client connected. You can start chatting.", clients.get(0));
                        sendMessage("Connected to the server. You can start chatting.", clients.get(1));
                    }
                }

                // Allow only the current client to send messages
                if (clients.size() == 2 && clients.get(currentClientIndex).equals(clientAddress)) {
                    System.out.println("Message received from " + clientAddress + ": " + message);

                    // If the message is "END", end the chat
                    if (message.equals("END")) {
                        // Notify the other client about the end of chat
                        sendMessage("Chat ended by the other client.", clients.get(1 - currentClientIndex));
                        System.out.println("Chat ended by " + clientAddress);
                        break; // Exit the server loop
                    }

                    // Relay the message to the other client
                    sendMessage(message, clients.get(1 - currentClientIndex));

                    // Toggle turn
                    currentClientIndex = 1 - currentClientIndex;
                } else if (clients.size() == 2) {
                    sendMessage("Wait for your turn.", clientAddress);
                }
            }

            mysocket.close(); // Close the server socket
            System.out.println("Server closed.");

        } catch (IOException e) {
            System.err.println("Communication error!");
            e.printStackTrace();
        }
    }

    /**
     * Sends a message to a specific client.
     * @param message The message to be sent.
     * @param clientAddress The address of the client to send the message to.
     * @throws IOException If an I/O error occurs.
     */
    private void sendMessage(String message, InetSocketAddress clientAddress) throws IOException {
        outbuf = message.getBytes(); // Convert the message to bytes
        DatagramPacket packet = new DatagramPacket(outbuf, outbuf.length, clientAddress.getAddress(), clientAddress.getPort()); // Create a new DatagramPacket
        mysocket.send(packet); // Send the packet
    }

    /**
     * Main method of the UDPServer class.
     * Creates a new instance of the UDPServer class.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new UDPServer();
    }
}
