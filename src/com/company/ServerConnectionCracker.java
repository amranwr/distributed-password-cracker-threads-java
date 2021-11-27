package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnectionCracker extends Thread{
	Socket crackerSocket;
	Server server;
	boolean flag = true;
	boolean mee = false;
	DataOutputStream outputStream;
	DataInputStream inputStream;
	public ServerConnectionCracker(Socket caraSocket , Server server) {
		this.crackerSocket = caraSocket;
		this.server = server;
	}
	
	
	
	public  void sendMsg(String txt) {
		try {
			outputStream.writeUTF(txt);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public void sendStringToSpecificClient(String txt) {
//		for(int i =0 ; i < server.connections.size(); i++) {
//			ServerConnection serverConnection = server.connections.get(i);
//			serverConnection.sendMsg(txt);
//		}
//	}
	
	boolean checkPass(String pass) {
		if(pass.equals("1111")) {
			sendMsg("mabrrrooooook anta m3ana <3");
			server.crackers.add(new Pair(crackerSocket,Server.getUniqueID()));
			mee = true;
			return true;
		}else {
			sendMsg("invalid password enter another password::::");
			return false;
		}
			
	}
	
	public void run() {
		 try {
			outputStream = new DataOutputStream(crackerSocket.getOutputStream());
			inputStream = new DataInputStream(crackerSocket.getInputStream());
			while(flag) {
				while(inputStream.available()==0) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				String input = inputStream.readUTF();
				if(!mee) { 
					if(checkPass(input)) server.crackerConnections.add(this);
				}
				else {
					String[] inputString = input.split(" ");
					for(int i=0 ; i < server.clients.size();i++){
						if(server.clients.get(i).id== Integer.parseInt(inputString[1])) {
							server.connections.get(i).sendMsg("cracked password : "+inputString[0]);
							//data.writeUTF(input);
							//data.flush();
						}
					}	
				}
 		    }
			inputStream.close();
			outputStream.close();
			crackerSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
}
