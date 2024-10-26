# 🌐 UDP Server and Client

Welcome to the **UDP Server and Client** application 🖥️! This project is a Java implementation of a simple client-server architecture using the User Datagram Protocol (UDP). UDP is a connectionless protocol that allows sending messages (datagrams) between computers on a network. This application illustrates how a server can receive messages from clients and echo them back, demonstrating basic network communication concepts.

## 📝 Features

- **UDP Server**:
  - Listens for incoming UDP packets on a specified port.
  - Processes received messages and sends them back to the client.
- **UDP Client**:
  - Connects to the server and sends a greeting message.
  - Receives and prints the echoed response from the server.
- **Threaded Execution**:
  - The client runs in its own thread, allowing for non-blocking communication.
- **Error Handling**:
  - Handles various exceptions such as socket errors, unknown hosts, and IO issues.

---

## 🚀 Getting Started

### 1️⃣ **Prerequisites**

Ensure you have the following installed on your machine:

- ☕ **Java JDK** (Java Development Kit) — Version 8 or above. You can download it from the [Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/).

### 2️⃣ **Installation**

Clone the repository and navigate to the folder:

```bash
git clone https://github.com/sean-njela/udp_server_client.git
cd udp_server_client
```

### 3️⃣ **Usage**

#### Start the UDP Server

To start the server, open a terminal and run the following command:

```bash
java UDPServer
```

You should see a message indicating that the server is up.

#### Start the UDP Client

In another terminal window (keeping the server running), execute the client:

```bash
java UDPClient
```

The client will send a message to the server and print the response.

---

## 📂 How It Works

### UDP Server

1. **Initialization**:

   - The server creates a `DatagramSocket` bound to port **6666**.
   - It listens for incoming datagram packets in an infinite loop.

2. **Receiving Messages**:

   - Upon receiving a packet, the server converts the received byte array into a string message.
   - It prints the message, along with the sender's IP address and port, to the console.

3. **Echoing Responses**:
   - The server constructs an echo message and sends it back to the client using the same address and port from which the message originated.

### UDP Client

1. **Initialization**:

   - The client creates a `DatagramSocket` and resolves the server address (localhost).
   - It connects the socket to the server on the specified port.

2. **Sending Messages**:

   - The client prepares a message ("Hello from the client!") and sends it to the server.
   - It waits for a response from the server.

3. **Receiving Responses**:
   - Upon receiving the echo response, the client converts the byte array back into a string and prints it to the console.

### Example Output

**Server Output**:

When the server is started, it will display a message indicating it is up:

```bash
$ java UDPServer
The server is up!
```

When a message is received from the client, it displays:

```bash
Message received: Hello from the client!, from the host: /127.0.0.1, port: 54321
```

**Client Output**:

Upon starting the client, it prints:

```bash
$ java UDPClient
The UDP client is up
Successfully connected to /127.0.0.1:6666
Received from the address: /127.0.0.1, port: 6666: Message received: Hello from the client!, from the host: /127.0.0.1, port: 54321
```

---

## 🔍 Technical Overview

### UDP vs TCP

- **UDP (User Datagram Protocol)**:

  - Connectionless protocol: No need to establish a connection before sending data.
  - No guarantee of message delivery: Messages may be lost or arrive out of order.
  - Lightweight and faster than TCP, making it suitable for applications like live streaming or gaming where speed is more critical than reliability.

- **TCP (Transmission Control Protocol)**:
  - Connection-oriented protocol: Establishes a connection before transmitting data.
  - Guarantees message delivery and order, but at the cost of speed.

### Code Structure

- **UDPServer.java**: Implements the server logic.
  - Listens for incoming packets and handles received messages.
- **UDPClient.java**: Implements the client logic.
  - Sends messages to the server and handles responses.

---

## ⚠️ Error Handling

- 🚫 **Socket Exception**: If the server cannot open the socket, it will display:

  ```bash
  Unable to open the socket!
  ```

- 🛑 **Unknown Host**: If the host is not found, the client will show:

  ```bash
  Unable to locate this server!
  ```

- ⚠️ **Communication Errors**: Any issues during communication will print a detailed error message, helping with debugging.

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## ❤️ Contributions

Feel free to submit pull requests or raise issues. Contributions are always welcome!

---

## 👤 Author

**TNjela**

- GitHub: [@seannjela](https://github.com/sean-njela/udp_server_client.git)
- Project Link: [@Project_link](https://roadmap.sh/projects/udp-server-client)

---

## 🎉 Acknowledgements

This project serves as an educational tool to demonstrate basic UDP communication principles. Feel free to contribute with enhancements and additional features!

Happy Coding! 🎉
