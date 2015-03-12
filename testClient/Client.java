package testClient;

import java.io.*;
import java.net.*;

public class Client{

	public ObjectOutputStream output;
	public ObjectInputStream input;
	public Socket socket;
	public int port;
	public String ip;

	public Client(String ip, int port){
		this.port = port;
		this.ip = ip;
	}

	public void startclient(){

		try {
			connectToServer();
			setupStreams();
			gameLoop();
		} catch(EOFException eofe){
			prompt("Client ended the connection");
		} catch(Exception e){
			e.printStackTrace();
		}

	}

	public void connectToServer() throws IOException{

		prompt("trying to connect to Server");
		socket = new Socket(InetAddress.getByName(this.ip), this.port);
		prompt("connected to server "+socket.getInetAddress().getHostName());

	}

	public void setupStreams() throws IOException{

		prompt("setting up Streams");
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
		output.flush();
		prompt("streams setup");


	}
	public void gameLoop(){

		while(true){}

	}

	public void prompt(String str){

		System.out.println(str);
	}


}
