package com.kasnex.snakeServer;

import java.io.*;
import java.net.*;

public class Main {

	public static ObjectOutputStream[] output = new ObjectOutputStream[2];
	public static ObjectInputStream[] input = new ObjectInputStream[2];
	public static ServerSocket[] server = new ServerSocket[2];
	public static Socket[] socket = new Socket[2];
	
	public static void main(String[] args) {
		
		startServer();
		
	}
	
	public static void startServer(){
		
		try{
			server[0] = new ServerSocket( 25567, 5);
			while(true){
				
				try{
					waitForConnection(0);
					setupStreams(0);
					waitForConnection(1);
					setupStreams(1);
					gameLoop();
				}catch(Exception e){
					prompt("Disconnected!");
				}finally{
					cleanUpAndClose(2);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private static void cleanUpAndClose(int players) {

		prompt("Closing Connections");
		try{
			
			for(int i = 0; i < players; i++){
				output[i].close();
				input[i].close();
				socket[i].close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private static void gameLoop() {
		// TODO GameLoop
		
	}

	private static void setupStreams(int id) throws IOException{

		output[id] = new ObjectOutputStream(socket[id].getOutputStream());
		output[id].flush();
		input[id] = new ObjectInputStream(socket[id].getInputStream());
		prompt("Streams Setup for "+id);
		
	}

	private static void prompt(String string) {

		System.out.println(string);
		
	}

	private static void waitForConnection(int id) throws IOException{

		prompt("Waiting for user " + id + " to connect...");
		socket[id] = server[id].accept();
		prompt(id+ " connected sucessfully "+ id + " is "+socket[id].getInetAddress().getHostAddress());
		
	}

}
