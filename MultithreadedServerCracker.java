package amr;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServerCracker extends Thread{
	int port ; 
	ServerSocket serverSocket;
	boolean flag;
	Thread thread;
	Server server ;
	
	public MultithreadedServerCracker(int port ,Server server) {
		this.port = port;
		this.server = server;
	}
	
	public void run() {
		synchronized (this) {
			this.thread = Thread.currentThread();
		}
		openServerSocket();
		while(!flag) {
			Socket craSocket = null;
			try {
				craSocket = this.serverSocket.accept();
				

			} catch (IOException e) {
				
				e.printStackTrace();
				if(isStopped()) {
					System.out.println("server stopped");
					return;
				}
				throw new RuntimeException("error accepting client",e);
			}
			
			ServerConnectionCracker serverConnection = new ServerConnectionCracker(craSocket, server);
			
			//server.crackerConnections.add(serverConnection);
				new Thread(
					serverConnection
					).start();
			
		}
		System.out.print("server stopped");
	}
	
	private synchronized boolean isStopped() {
		return this.flag;
	}
	
	public synchronized void stopCracker() {
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
