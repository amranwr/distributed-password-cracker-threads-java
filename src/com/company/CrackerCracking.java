package com.company;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class CrackerCracking extends Thread{
	Cracker cracker;
	Socket socket;
	String tempString ="etaoinshrdlucmwfgypbvkjxqz";
	boolean flag = false;
	public CrackerCracking(Socket socket, Cracker cracker) {
		this.cracker = cracker;
		this.socket = socket;
	}
	
	
	public  String getKey(String input) {
		tempString = tempString.toUpperCase();
		int freq;
		char w;
		String key="";
		ArrayList<Segmant> myData = new ArrayList<Segmant>();
		for(int f =0 ; f < 26;f++) {
			freq=0;
			for(int j =0 ;j<input.length();j++) {
				if((char)(65+f)==input.charAt(j)) {
					System.out.print(input.charAt(j)+" ");
					freq++;
				}
			}
			myData.add(new Segmant((char)(f+65), freq));
		}
		sort(myData);
		for(int i =0 ; i< 26;i++) {
			key+=myData.get(i).getS();
		}
		return key;
	}
	
	
	
	
	public void sort(ArrayList<Segmant> myData) {
		for(int i =0 ; i <myData.size(); i++) {
			for(int j =0 ; j< myData.size()-1;j++) {
				if(myData.get(j).frequency<myData.get(j+1).frequency)Collections.swap(myData, j,j+1);
			}
		}
		
		
		
	}
	
	
	
	
	public String decryption(String encrypted , String key) {
		String solved="";
		for(int j =0 ; j < encrypted.length();j++) {
			for(int i =0 ; i< 26 ;i++) {
				if(key.charAt(i)==encrypted.charAt(j)) {
					solved+=tempString.charAt(i);
					break;
				}
			}
		}
		return solved;
	}
	
	
	public void run() {
		while(true) {
			while(cracker.requests.size()==0) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Request rq = cracker.requests.element();
			String keyString = getKey(rq.getHash());
			String decryptedMsg = decryption(rq.hash, keyString);
			cracker.requests.remove();
			cracker.crackerConnection.sendMsgToServer(decryptedMsg+" "+rq.clientId+" requestId"+" "+rq.reqId);
		}	
	}

}
