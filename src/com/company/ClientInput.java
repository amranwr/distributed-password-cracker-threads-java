package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

//import org.omg.CORBA.PUBLIC_MEMBER;

public class ClientInput extends Thread {
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Socket socket;
	Client client;
	boolean flag = true;
	
	public ClientInput(Socket socket,Client client) {
		this.socket = socket;
		this.client = client;
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while(flag) {
			String inputString = scanner.nextLine();
			if(inputString.equals("exit")) break;
			client.clientConnection.sendMsgTServer(inputString);
		}
	}
}
