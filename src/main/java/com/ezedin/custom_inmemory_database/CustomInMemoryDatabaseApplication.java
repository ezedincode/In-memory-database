package com.ezedin.custom_inmemory_database;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import static com.ezedin.custom_inmemory_database.responseCode.ARRAY;
import static com.ezedin.custom_inmemory_database.responseCode.STRING;

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
		String line = in.readLine();
		System.out.println(line);

		char c = (char)in.read();
		if(c != '$'){
			System.out.println("invalid type,expecting a bulk string");
		}
//		in.read();
//		in.read();
//		char[] data = new char[size];
//		int totalRead = 0;
//
//		while (totalRead < size) {
//			int charsRead = in.read(data, totalRead, size - totalRead);
//			if (charsRead == -1) {
//				break;
//			}
//			totalRead += charsRead;
//		}
//		String name = new String(data,0,totalRead);
//		System.out.println(name);
//		out.write("+OK\r\n");
//		out.flush();
	}
		}catch (Exception e){
			e.getMessage();
		}

	}
	public static String readBlock(BufferedReader in) throws IOException {
		char c = (char) in.read();
		String line ="";
		while(c=='\r'){
			line =line.concat(String.valueOf(c));
			c = (char) in.read();
		}
		return line;
	}

	public static int readInteger(BufferedReader in) throws IOException {
		in.read();
		String line = readBlock(in);
		return Integer.parseInt(line);
	}
	public static request readRequest(BufferedReader in) throws IOException {
		char c = (char) in.read();
		responseCode type = responseCode.valueOf(String.valueOf(c));
		switch (type){
			case ARRAY:
				return readArray(in);
			case BULK:
				return readBulk();
			default:
				System.out.println("invalid type");
				break;
		}
	}

	private static request readBulk(BufferedReader in) throws IOException {


	}

	private static request readArray(BufferedReader in) throws IOException {
		int length  = readInteger(in);
		request[] requests = new request[length];
		for (int i = 0; i < length; i++) {

		var re  =	readRequest(in);
		requests[i] = re;

		}
		return new request(
				"array",
				null,
				0,
				null,
				requests

		);
	}

}
