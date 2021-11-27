package amr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerConnectionCracker> crackerConnections = new ArrayList<ServerConnectionCracker>();
	ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
	ArrayList<Request> requests = new ArrayList<Request>();
	ArrayList<Pair> crackers = new ArrayList<Pair>();
	ArrayList<Pair> clients = new ArrayList<Pair>();
	boolean flag =true;
	static int idCounter=0;
	static int idRequest=0;

	public static void  main(String[] args) {
		new Server();
	}
		
	
	public Server() {
		//serverSocket = new ServerSocket(9999);
		while(flag) {
			//Socket clientSocket  =serverSocket.accept();
			//System.out.println("azyk 3amel eh");
			MultiThreadServer multiThreadServer = new MultiThreadServer(8888,this);
			new Thread(multiThreadServer).start();
			MultithreadedServerCracker multithreadedServerCracker = new MultithreadedServerCracker(8881, this);
			new Thread(multithreadedServerCracker).start();
			try {
				Thread.sleep(2000000000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.print("stopping server");
			multiThreadServer.stop();
			//ServerConnection serverConnection = new ServerConnection(clientSocket);
			//serverConnection.start();
			//connections.add(serverConnection);
			
		}
		
	}
	
	public static int getUniqueID() {
		return ++idCounter;
	}
	
	public static int getUniqueIDReq() {
		return ++idRequest;
	}
}
