import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

public class ServerThreadForClient extends Thread{
	HashMap<String,BufferedWriter> clients = null;
	
	Socket client = null;
	String name = null;
	
	public ServerThreadForClient(HashMap<String, BufferedWriter> clients, Socket client) {
		super();
		this.clients = clients;
		this.client = client;
	}

	@Override
	public void run() {
		
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try {
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			br= new BufferedReader(new InputStreamReader(client.getInputStream()));
			name = br.readLine();
			//sinhronizujemo jer sve niti dele istu Hash mapu
			synchronized (clients) {
				clients.put(name, bw);
			}
			String message = "Novi klient : " + name + ", KLIJENTI: " + clients.keySet()+"\n";
			for(BufferedWriter client_writter : clients.values())
			{
				client_writter.write(message);
				client_writter.flush();
			}
			
			while(true) {
				message = br.readLine();
				message = name +": "+message+"\n";
				for(BufferedWriter client_writter : clients.values())
				{
					client_writter.write(message);
					client_writter.flush();
				}
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
