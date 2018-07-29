package by.htp.client.run;

import by.htp.client.console.ConsoleClient;
import by.htp.client.console.FailedConnectionException;
import by.htp.client.entity.Book;

public class MainClient {
	public static void main(String[] args) {
		ConsoleClient client = new ConsoleClient();

		try {
			client.connect("localhost", 9595);
			Book book = client.getBook(3);
			System.out.println(book);
		} catch (FailedConnectionException e) {
			System.out.println("Sorry, you cannot connet to the server");
			e.printStackTrace();
		}

	}

}
