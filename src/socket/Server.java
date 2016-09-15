package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) 
		throws IOException
	{
		ServerSocket ss = new ServerSocket(30000);
		while(true){
			Socket socket = ss.accept();
			new Thread(new ServerThread(socket)).start();
		}
	}
}

