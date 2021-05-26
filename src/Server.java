import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

	public static int PORT = 6789;
	public static void main(String[] args) {
		//stream
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server started ad port "+PORT);
			
			HashMap<String,BufferedWriter> clients = new HashMap<String, BufferedWriter>();
			
			while(true)
			{
				//blokirajuci poziv
				Socket client = serverSocket.accept();
				ServerThreadForClient nit = new ServerThreadForClient(clients,client);
				nit.start();				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
