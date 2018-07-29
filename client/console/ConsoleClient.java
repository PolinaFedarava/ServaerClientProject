package by.htp.client.console;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import by.htp.client.entity.Book;

public class ConsoleClient {

	private String title;
	private String type;
	private Socket socket;

	public void connect(String host, int port) throws FailedConnectionException {

		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			throw new FailedConnectionException("Cannot conect", e);
		}
	}

	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Book getBook(int id) {
		sendBookRequest(id);
		Book book = recieveBookResponse();
		book.setId(id);
		return book;
	}

	private void sendBookRequest(int id) {
		try {
			OutputStream os = socket.getOutputStream();
			os.write((id + "").getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Book recieveBookResponse() {
		Book book = new Book();
		try (InputStream is = socket.getInputStream()) {
			byte[] data = new byte[1024];

			is.read(data);

			book.setTitle(new String(data));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return book;
	}
}
