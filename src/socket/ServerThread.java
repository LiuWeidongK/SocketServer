package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{
	Socket socket = null;
	BufferedReader bReader = null;
	public ServerThread(Socket socket ) {
		this.socket = socket;
		try {
			bReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			String content = null;
			while((content = readFromClient())!=null){ 			//¶ÁÈ¡¿Í»§¶Ë
				String[] str = content.split(" ");
				OutputStream oStream = socket.getOutputStream();
				switch (str[2]) {
				case "REGISTER":
					Register register = new Register(str[0], str[1]);
					if(register.check()){
						if(register.regist()){
							oStream.write(("R_SUCCESS\n").getBytes("utf-8"));
						}else{
							oStream.write(("R_FAIL\n").getBytes("utf-8"));
						}
					}else {
						oStream.write(("REPEAT\n").getBytes("utf-8"));
					}
					break;
				case "LOGIN":
					Login login = new Login(str[0], str[1]);
					if(login.login()){
						oStream.write(("L_SUCCESS\n").getBytes("utf-8"));
					}else {
						oStream.write(("L_FAIL\n").getBytes("utf-8"));
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readFromClient() {
		try {
			return bReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
