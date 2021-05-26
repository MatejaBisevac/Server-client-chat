import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		try {
				Socket socket = new Socket(InetAddress.getByName("localhost"),Server.PORT);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				Scanner scanner = new Scanner(System.in);
				
				Thread fromServer = new Thread(new Runnable() {				
					public void run() {
						String line = null;
						while(true) {
							
								try {
									line = br.readLine();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
							System.out.println(line);
						}
					}
				});
				fromServer.start();
				
				Thread toServer = new Thread(new Runnable() {
					
					public void run() {
						String line = "";
						System.out.println("Unesite ime");
						while(true) {
							line = scanner.nextLine();
							
							try {
								bw.write(line + "\n");
								bw.flush();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
					
				});
				toServer.start();
				
				try {
					fromServer.join();
					toServer.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
