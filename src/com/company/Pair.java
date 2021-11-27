package com.company;

import java.net.Socket;

public class Pair {
	Socket socket;
	int id;
	
	public Pair(Socket socket ,int id ) {
		this.id = id;
		this.socket  = socket;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
