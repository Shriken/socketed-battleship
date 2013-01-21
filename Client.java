import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Client {

    Scanner stdIn;
    Scanner in;
    PrintWriter out;
    Socket echoSocket;
    String userInput;

    public Client(int port) {
	stdIn = new Scanner(System.in);

	//open socket with host
	try {
	    System.out.println("opening socket with host: Kosh...");
            echoSocket = new Socket("127.0.0.1", port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new Scanner(echoSocket.getInputStream());
	    System.out.println("opened");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: Kosh.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: Kosh");
            System.exit(1);
        }

	//route user input to host
	try {
	    System.out.println("Say something to Kosh!");
	    while ((userInput = stdIn.nextLine()) != null) {
		out.println(userInput);
		System.out.println("echo: " + in.nextLine());
	    }
	    
	    out.close();
	    in.close();
	    stdIn.close();
	    echoSocket.close();
	    System.out.println("bye!");
	} catch (IOException e) {
	    System.err.println("Error communicating with: Kosh");
	}
    }

    public static void main(String[] args) {
	new Client(Integer.parseInt(args[0]));
    }

}