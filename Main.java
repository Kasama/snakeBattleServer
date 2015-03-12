import java.io.*;
import java.net.*;

public class Main {

	public static void main(String[] args) {
		
		snakeServer server = new snakeServer(2, 25567);
		server.startServer();
		
	}
	

}
