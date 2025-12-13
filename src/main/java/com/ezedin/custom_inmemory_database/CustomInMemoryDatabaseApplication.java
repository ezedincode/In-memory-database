package com.ezedin.custom_inmemory_database;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomInMemoryDatabaseApplication {

	public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(6379);
			Socket clientSocket = serverSocket.accept();
			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(output));
	while(true) {

		System.out.println(in.readLine());

		out.write("+OK\r\n");
		out.flush();
	}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
