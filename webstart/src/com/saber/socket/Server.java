package com.saber.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket ss;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public Server() {
		try {
			ss = new ServerSocket(9999);
			while(true) {
				socket = ss.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(System.out, true);
				
				String line = in.readLine();
				out.println("your input is: "+line);
//				System.out.println("your input is: "+line);
				out.close();
				in.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
