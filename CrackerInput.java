package amr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CrackerInput extends Thread{
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Socket socket;
	Cracker cracker;
	boolean flag = true;
	
	public CrackerInput(Socket socket , Cracker cracker) {
		this.cracker = cracker;
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("enter password : ");
		while(flag) {
			Scanner scanner = new Scanner(System.in);
			while(!scanner.hasNextLine()) {
				System.out.print("lsdjkfhkasdj");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String inputString = scanner.nextLine();
			
			
			if(inputString.equals("exit")) break;
			cracker.crackerConnection.sendMsgToServer(inputString);
		}
	}
}
