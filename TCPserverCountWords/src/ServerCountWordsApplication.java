import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCountWordsApplication 
{
	public static void main(String[] args) throws IOException 
	{
		serverFrame server = new serverFrame();
		server.setVisible(true);
		
		// Binding to a port or any other port
		int portNo = 4228;
		ServerSocket serverSocket = new ServerSocket(portNo);
		
		WordsCounter words = new WordsCounter();
		
		
		// Counter to keep track the number of requested connection
		int totalRequest = 0;
		
		// Server needs to be alive forever
		while (true) 
		{
			
			// Accept client request for connection
			Socket clientSocket = serverSocket.accept();
			
			// Message to indicate server is alive
			server.updateServerStatus(clientSocket.isConnected());
			
			// get the input stream from the connected socket
	        InputStream inputStream = clientSocket.getInputStream();
	        
	        // create a DataInputStream so we can read data from it.
	        DataInputStream dataInputStream = new DataInputStream(inputStream);
	        
	        String txt = dataInputStream.readUTF();
	        String wordsCounted = words.Counter(txt);
	        
			// Create stream to write data on the network
			DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
			
			// Send current date back to the client
			outputStream.writeBytes(wordsCounted);
			
			// Close the socket
			clientSocket.close();
		
			// Update the request status
			server.updateRequestStatus("Data sent to the client: " + wordsCounted + " WORDS COUNTED");
			server.updateRequestStatus("Accepted connection to from the " + "client. Total request = " + ++totalRequest);
			
		}
	}
}
