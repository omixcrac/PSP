import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		final int port = 8080;
		ServerSocket serverSocket = new ServerSocket(port);

			//SimpleServer.Process(socket);
			while (true) {
				Socket socket = serverSocket.accept();
				
				Runnable runnable = new ThreadServer(socket);
				Thread thread = new Thread(runnable);
				thread.start();
			}

		//serverSocket.close();
	}


}