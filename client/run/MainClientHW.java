package by.htp.client.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainClientHW {
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			System.out.println("Server started...");
			server = new ServerSocket(9595);
			Socket client = server.accept();
			System.out.println("Client connected ...");
			try (FileInputStream fin = new FileInputStream("D:\\Java Fedorova\\ClientID.txt")) {
				System.out.printf("File size: %d bytes \n", fin.available());

				int i = -1;
				while ((i = fin.read()) != -1) {
					System.out.print((char) i);
				}
			} catch (IOException ex) {

				System.out.println(ex.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
				System.out.println("Server stopped... ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
