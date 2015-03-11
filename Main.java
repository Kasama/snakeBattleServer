import java.io.*;
import java.net.*;

public class Main {

	public static ObjectOutputStream[] output = new ObjectOutputStream[2];
	public static ObjectInputStream[] input = new ObjectInputStream[2];
	public static ServerSocket[] server = new ServerSocket[2];
	public static Socket[] socket = new Socket[2];
	
	public static void main(String[] args) {
		
		snakeServer server = new snakeServer(2, 25567);
		server.startServer();
		
	}
	

}
