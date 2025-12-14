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
		in.read();
		in.read();
		in.read();
		in.read();
		char c = (char)in.read();
		if(c != '$'){
			System.out.println("invalid type,expecting a bulk string");
		}
		int size = Integer.parseInt(in.readLine());
		in.read();
		in.read();
		char[] data = new char[size];
		int totalRead = 0;

		while (totalRead < size) {
			int charsRead = in.read(data, totalRead, size - totalRead);
			if (charsRead == -1) {
				break;
			}
			totalRead += charsRead;
		}
		String name = new String(data,0,totalRead);
		System.out.println(name);
		out.write("+OK\r\n");
		out.flush();
	}
		}catch (Exception e){
			e.getMessage();
		}
	}

}
