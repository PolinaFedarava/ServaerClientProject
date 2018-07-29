package by.htp.server.run;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import by.htp.server.entity.Book;

public class MainServer {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		PrintStream ps = null;
		DataInputStream is = null;

		Book bookList1 = new Book(1, "1Book.");
		Book bookList2 = new Book(2, "2Book.");
		Book bookList3 = new Book(3, "3Book.");
		Book bookList4 = new Book(4, "4Book.");
		Book[] bookList = new Book[4];
		bookList[0] = bookList1;
		bookList[1] = bookList2;
		bookList[2] = bookList3;
		bookList[3] = bookList4;

		try {
			server = new ServerSocket(9595);
			System.out.println("Server started...");

			client = server.accept();
			System.out.println("client input connected...");

			is = new DataInputStream(client.getInputStream());

			int bytes = is.available();
			System.out.println("Bytes available: " + bytes);

			Path path = Paths.get("D:\\Java Fedorova\\ClientID.txt");
			byte[] data = Files.readAllBytes(path);
			//data = is.readAllBytes();

			String dataToString = new String(data);
			System.out.println("Data received, bytes available: " + bytes);
			System.out.println ("bytes read: " + dataToString);
			String[] dataLines = dataToString.split("(\n)");
			for (int x = 0; x < dataLines.length; x++)
				System.out.println(dataLines[x]);

			String tempS = dataLines[1].trim();
			int numOfBook = Integer.parseInt(tempS);
			String currentBookName = bookList[numOfBook].getTitle();
			System.out.println("Book title is: " + currentBookName);

			System.out.println(Instant.now().toEpochMilli());

			is.close();
			client.close();

			client = server.accept();
			System.out.println("accept");
			ps = new PrintStream(client.getOutputStream());
			System.out.println("PrintStream");
			ps.println(currentBookName);
			System.out.println("currentBookName");
			ps.flush();
			System.out.println("flush");

			Thread.sleep(100);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				is.close();
				client.close();
				System.out.println("Server closed.");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
