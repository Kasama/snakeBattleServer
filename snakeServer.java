import java.io.*;
import java.net.*;

public class snakeServer {

	private ObjectOutputStream[] output;
	private ObjectInputStream[] input;
	private ServerSocket server;
	private Socket[] socket;
	private int players;
	private int port;

	public snakeServer(int players, int port){

		this.players = players;
		this.port = port;
		this.input = new ObjectInputStream[players];
		this.output = new ObjectOutputStream[players];
		this.socket = new Socket[players];

	}

	public void startServer(){
		
		try{
			server = new ServerSocket(this.port, this.players);
			while(true){
				try{
					for (int i = 0; i < this.players; i++) {
						waitForConnection(i);
						setupStreams(i);
						prompt("Player connected "+i+"/"+this.players); 
					}
					prompt("All players connected, starting game");
					gameLoop();
				}catch(Exception e){
					prompt("ERROR: Disconnected!");
				}finally{
					cleanUpAndClose(this.players);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void cleanUpAndClose(int players) {

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

	private void gameLoop() {
		// TODO GameLoop
		while(true){}
		
	}

	private void setupStreams(int id) throws IOException{

		output[id] = new ObjectOutputStream(socket[id].getOutputStream());
		output[id].flush();
		input[id] = new ObjectInputStream(socket[id].getInputStream());
		prompt("Streams Setup for "+id);
		
	}

	private void prompt(String string) {

		System.out.println(string);
		
	}

	private void waitForConnection(int id) throws IOException{

		prompt("Waiting for user " + id + " to connect...");
		socket[id] = server.accept();
		prompt(id+ " connected sucessfully "+ id + " is "+socket[id].getInetAddress().getHostAddress());
		
	}

}
