package com.company;

public class Request {
	String hash;
 
	int clientId ;
	int reqId;
	
	public Request(String hash, int clientId,int reqId) {
		this.clientId = clientId;
		this.hash = hash;
		this.reqId = reqId;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}


	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
