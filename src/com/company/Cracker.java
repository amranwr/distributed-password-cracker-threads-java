package com.company;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

public class Cracker {
	Socket crackerSocket; 
	boolean flag= true;
	CrackerInput crackerInput;
	CrackerConnection crackerConnection;
	Queue<Request> requests = new LinkedList<Request>();
	static int id;
	CrackerCracking crackerCracking;
	
	public static void main(String[] args) {
		new Cracker();
	}
	
	public Cracker() {
		
		try {
			crackerSocket = new Socket("localhost",8881);
			crackerInput = new CrackerInput(crackerSocket, this);
			crackerConnection = new CrackerConnection(crackerSocket, this);
			crackerCracking = new CrackerCracking(crackerSocket,this);
			
			crackerInput.start();
			crackerConnection.start();
			crackerCracking.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String genratehashedpassword(String actualPassword) {
		StringBuffer sb = new StringBuffer();
		try {    
	           System.out.println("\nRandomly generated password: " + actualPassword);
	           
	           MessageDigest md = MessageDigest.getInstance("MD5");
	           md.update(( actualPassword + getSystemDate()).getBytes());
	           byte[] byteData = md.digest();

	           for (int i = 0; i < byteData.length; i++) {
	               sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	           }
	       } catch (NoSuchAlgorithmException ex) {

	       }

	       return new String(sb);
	   }

	  
	 
	   
	  public static String getSystemDate() {

	       DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	       Calendar cal = Calendar.getInstance();
	       String date = (dateFormat.format(cal.getTime())).toString();
	       return date;
	   }

	
}
