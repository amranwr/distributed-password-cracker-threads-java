package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class ServerConnection extends Thread{
	Socket clientSocket;
	Server server;
	boolean flag = true;
	DataOutputStream outputStream;
	DataInputStream inputStream;
	public ServerConnection(Socket clienSocket,Server server) {
		this.clientSocket = clienSocket;
		this.server = server;
	}
	
	
	
	public void sendMsg(String txt) {
		try {
			outputStream.writeUTF(txt);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendStringToSpecificClient(String txt) {
		for(int i =0 ; i < server.connections.size(); i++) {
			ServerConnection serverConnection = server.connections.get(i);
			serverConnection.sendMsg(txt);
		}
	}
	
	public int checkId() {
		for( int i =0 ; i < server.clients.size();i++) {
			if(server.clients.get(i).socket==clientSocket) {
				return server.clients.get(i).id;
			}
		}
		return 0;
	}
	
	
	public String helpFun() {
		return ("==USE <cracker name> ON <cracker parameter 1> <cracker parameter 2> \n"
				+ "-crackers name: \n"
				+ "1-cipher.\n"
				+ "-cracker parameter 1:\n"
				+ "1-hashed code.\n"
				+ "-cracker parameter 2: \n"
				+ "1- key.\n");
	}
	
	
	public boolean validate(String input) {
		String[] arr = input.split(" ");
		boolean flag = true;
		if(arr.length<5) {
			return false;
		}else {
			if(arr[0].toLowerCase().equals("use")
					&& arr[1].toLowerCase().equals("cipher")
						&&arr[2].toLowerCase().equals("on"))return true;
			else return false;
			
		}
	}
	
	public void run() {
		 try {
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			inputStream = new DataInputStream(clientSocket.getInputStream());
			outputStream.writeUTF("Welcome client number" + checkId());
			outputStream.writeUTF("(H) or (help) to show help.");
			String input;
			while(flag) {
				while(inputStream.available()==0) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				input = inputStream.readUTF();
				if(input.toLowerCase().equals("h")||input.toLowerCase().equals("help")) {
					outputStream.writeUTF(helpFun());
				}else {
					if(validate(input)) {
						String[] inputStrings = input.split(" ");
						Random random = new Random();
						String hash = "";
						for(int i =3 ;i < inputStrings.length;i++ )hash+=inputStrings[i];
						System.out.println(input);
						int index = random.nextInt(server.crackerConnections.size());
						int reqid = server.getUniqueIDReq();
						System.out.print(hash+" "+reqid);
						//server.requests.add(new Request(inputStrings[3],checkId(),reqid));
						server.crackerConnections.get(index).sendMsg(hash+" "+checkId()+" "+reqid);
					}else {
						sendMsg("invalid input ya 3m fe eh !!! \nda5l input tani aw3a tkon z3lt <3::");
					}
				}
				
				
				
				
 		    }
			inputStream.close();
			outputStream.close();
			clientSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
}
