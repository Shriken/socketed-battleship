import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Host {

    Scanner in;
    PrintWriter out;
    ServerSocket serverSocket;
    Socket clientSocket;
    String clientInput;

    public Host(int port) {
	//open server socket on given port
	serverSocket = null;
	try {
	    System.out.println("opening server socket...");
	    serverSocket = new ServerSocket(port);
	    System.out.println("opened");
	} catch (IOException e) {
	    System.err.println("Could not listen on port " + port);
	    System.exit(-1);
	}

	//accept client socket on given port
	clientSocket = null;
	try {
	    System.out.println("accepting client socket...");
	    clientSocket = serverSocket.accept();
	    out = new PrintWriter(clientSocket.getOutputStream(), true);
	    in = new Scanner(clientSocket.getInputStream());
	    System.out.println("accepted");
	} catch (IOException e) {
	    System.err.println("Accept failed: " + port);
	    System.exit(-1);
	}

	//echo client input, print it to screen
	try {
	    System.out.println("communicating with client");
	    while ((clientInput = in.nextLine()) != null) {
		System.out.println("the client says: " + clientInput);
		out.println(clientInput);
	    }
	    out.close();
	    in.close();
	    clientSocket.close();
	    serverSocket.close();
	    System.out.println("bye!");
	} catch (IOException e) {
	    System.err.println("Error communicating with client.");
	}
    }

    public static void main(String[] args) {
	new Host(Integer.parseInt(args[0]));
    }

}