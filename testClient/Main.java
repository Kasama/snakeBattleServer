package testClient;

import java.io.*;
import java.net.*;

public class Main{

	public static void main(String[] args) {
		String ip = "localhost";
		int port = 25567;
		Client cli;

		cli = new Client(ip, port);
		cli.startclient();

	}

}
