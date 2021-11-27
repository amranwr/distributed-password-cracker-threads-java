package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection extends Thread{
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Socket socket;
	Client client;
	boolean flag = true;
	
	public ClientConnection(Socket socket,Client client) {
		this.client = client;
		this.socket = socket; 
	}
	
	public void sendMsgTServer(String txt) {
		try {
			outputStream.writeUTF(txt);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		}
	}
	
	
	public void run() {
		try {
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
			while(flag) {
				while(inputStream.available()==0) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			    String replyString = inputStream.readUTF();
			    System.out.println(replyString);
			    
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		}
		
		
	}
	
	public void close() {
		try {
			inputStream.close();
			outputStream.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
