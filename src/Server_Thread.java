import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server_Thread implements Runnable{
	Socket foot;
	Server_Thread(Socket socket) {
		foot = socket;
	}
	
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(foot.getInputStream()));
			
			String choice = in.readLine();
			
			switch(choice) {
			case "1":
				command("date", foot);
				break;
			case "2":
				command("uptime", foot);
				break;
			case "3":
				command("free", foot);
				break;
			case "4":
				command("netstat -an", foot);
				break;
			case "5":
				command("who", foot); //could also use "users" but it only displays the login name and it sorts them
				break;
			case "6":
				command("ps -A", foot);
				break;
			default:
				System.out.println("Quit");
			}
			foot.close();
			System.out.println("Client has disconnected");
		} catch (IOException e) {
			System.out.println("Input or output issue");
			
		}
	}
	
	public static void command(String command, Socket foot) {
		String linereader = null;
		try {
			Process runner = Runtime.getRuntime().exec(command); //executes the "command" on the command line of the current environment that Java is running on
			PrintWriter print = new PrintWriter(foot.getOutputStream(), true);//this will take the output stream and send it to the client
			BufferedReader input = new BufferedReader(new InputStreamReader(runner.getInputStream())); //reader that will read from the command line
			
			while((linereader = input.readLine()) != null) {
				print.println(linereader);
			}
			print.println("Finished");
			
		} catch (IOException e) {
			System.out.println("There was an issue with the input ofthe command or the output.");
		}
		
	}

}
