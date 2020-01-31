package amr;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer implements Runnable{
	int port ; 
	ServerSocket serverSocket;
	boolean flag;
	Thread thread;
	Server server ;

	public MultiThreadServer(int port ,Server server) {
		this.port = port;
		this.server = server;
	}
	
	public void run() {
		synchronized (this) {
			this.thread = Thread.currentThread();
		}
		openServerSocket();
		while(!flag) {
			Socket clientSocket = null;
			
			try {
				clientSocket = this.serverSocket.accept();
				server.clients.add(new Pair(clientSocket, Server.getUniqueID()));
			
			} catch (IOException e) {
				
				e.printStackTrace();
				if(isStopped()) {
					System.out.println("server stopped");
					return;
				}
				throw new RuntimeException("error accepting client",e);
			}
			ServerConnection serverConnection = new ServerConnection(clientSocket, server);
			new Thread(
					serverConnection
					).start();
			this.server.connections.add(serverConnection);
			
		}
		System.out.print("server stopped");
	}
	
	private synchronized boolean isStopped() {
		return this.flag;
	}
	
	public synchronized void stop() {
		this.flag = true;
		try {
			this.serverSocket.close();
			
		}catch (IOException e) {
			throw new RuntimeException("error ",e);
		}
	}
	
	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
