import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Con_Server {

	public static void main(String[] args) throws IOException{
		int port = 0;
		
		if (args.length != 1) {
			System.out.println("Argument type: <port number>");
			System.exit(1);
		} else {
			port = Integer.parseInt(args[0]);
		}
		
		ServerSocket sock = new ServerSocket(port);
		
		do {
			Socket foot = sock.accept();
			System.out.println("Client Connected");
			new Thread(new Server_Thread(foot)).start();
		} while(true);

	}

}
