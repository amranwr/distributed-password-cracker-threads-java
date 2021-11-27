package com.company;

import java.awt.SystemTray;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import javax.print.attribute.HashAttributeSet;

public class CrackerConnection extends Thread{
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Socket socket;
	Cracker cracker;
	boolean flag = true;
	

	
	public CrackerConnection(Socket socket , Cracker cracker) {
		this.socket = socket  ; 
		this.cracker = cracker;
	}
	
	public void sendMsgToServer(String input) {
		try {
			outputStream.writeUTF(input);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			    if(replyString.equals("mabrrrooooook anta m3ana <3")) {
			    	System.out.println(replyString);
			    	cracker.crackerInput.stop();
			    }else if(replyString.equals("invalid password enter another password::::")){
			    	System.out.print(replyString);
			    }else {	
				    String[] inputString = replyString.split(" ");
				    String hashString = inputString[0];
				    System.out.println(replyString);
				    int clientId = Integer.parseInt(inputString[1]);
				    cracker.requests.add(new Request(hashString, clientId,Integer.parseInt(inputString[2])));
//				    Request request = cracker.requests.element();
//				    cracker.getReqId();
//				    String outputString =  decryption(request.hash, request.key);
//				    sendMsgToServer("solved: "+outputString+ " "+request.clientId+" requestID: "+request.reqId);
//				    cracker.requests.remove();
			    }    
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
	}}
	
	
	
	
	
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
