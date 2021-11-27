package com.company;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class Client {
	Socket clientSocket;
	boolean flag = true;
	
	ClientConnection clientConnection;
	
	
	public static void  main(String[] args) {
		new Client();
	}
	
	public Client() {
		try {
			clientSocket = new Socket("localhost",8888);
			clientConnection = new ClientConnection(clientSocket, this);
			ClientInput clientInput = new ClientInput(clientSocket, this);
			clientInput.start();
			clientConnection.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String genratehashedpassword() {

	       StringBuilder stmp = new StringBuilder();
	       char[] symbols, buffer = new char[5];
	       String date = getSystemDate();
	       Random random = new Random();
	       StringBuffer sb = new StringBuffer();

	       for (char ch = '0'; ch <= '9'; ++ch) {
	           stmp.append(ch);
	       }
	       for (char ch = 'A'; ch <= 'Z'; ++ch) {
	           stmp.append(ch);
	       }
	       for (char ch = 'a'; ch <= 'z'; ++ch) {
	           stmp.append(ch);
	       }
	       
	       symbols = stmp.toString().toCharArray();

	       for (int index = 0; index < buffer.length; ++index) {
	           buffer[index] = symbols[random.nextInt(symbols.length)];
	       }
	       
	       try {
	           String actualPassword = new String(buffer);
	           System.out.println("\nRandomly generated password: " + actualPassword);
	           
	           MessageDigest md = MessageDigest.getInstance("MD5");
	           md.update(( actualPassword + date).getBytes());
	           byte[] byteData = md.digest();

	           for (int i = 0; i < byteData.length; i++) {
	               sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	           }
	       } catch (NoSuchAlgorithmException ex) {

	       }

	       return new String(sb);
	   }

	  
	   
	   
	  // method to get system date (from the internet)
	   public static String getSystemDate() {
	       DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	       Calendar cal = Calendar.getInstance();
	       String date = (dateFormat.format(cal.getTime())).toString();
	       return date;
	   }

	
	
}
