import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ClientFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	// Private components
	private JLabel statusTittle;
	private JLabel status;
	private JTextArea textToCount;
	private JButton btn;
	private JScrollPane scroll;
	
	// Private attributes for frame size
	private int width = 800;
	private int height = 400;
	
	public ClientFrame()
	{
		// Default frame setting
		this.setLayout(new BorderLayout());
		this.setTitle("TCP Words Counter: Client Side");
		this.setSize(new Dimension(width, height));
		
		// Must close on X
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Center the frame on the screen
        this.setLocationRelativeTo(null);
		// Initialize component
		this.statusTittle = new JLabel ("Words Counter \nServer Connection: ");
		this.status = new JLabel ("-");
		
		// text Area
		this.textToCount  = new JTextArea(3, 16);
		scroll = new JScrollPane(textToCount);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    
		//button
		this.btn = new JButton("Count");
		btn.addActionListener(this);
		
		// Organize components
		loadComponent();
	}
	
	/**
	 * This method update the status of connection to the server.
	 * 
	 * @param connStatus: Connection status (true/false)
	 */
	public void updateConnectionStatus (boolean connStatus) {
		

		// Default status. Assuming for the worst case scenario.
		String status = "No connection to server.";
		
		// Validate status of connection
		if (connStatus)
			status = "Connection has established.";
		
				
		// Update the status on frame
		this.status.setText(status);
	}
	
	private JPanel getConnectionStatusPanel(Font font)
	{
		JPanel panel = new JPanel();
		statusTittle.setFont(font);
		status.setFont(font);
		
		statusTittle.setOpaque(true);
		status.setOpaque(true);

		panel.add(statusTittle);
		panel.add(status);

		return panel;
	}
	
	private JPanel getClientTextArea(Font font)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		textToCount.setFont(font);
		textToCount.setOpaque(true);
		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel getClientButton(Font font)
	{
		JPanel panel = new JPanel();
		btn.setFont(font);
		btn.setOpaque(true);
		panel.add(btn);
		return panel;
	}
	
	
	private void loadComponent() 
	{
		
		// Get font
		Font font = this.getFontStyle();
		
		// Get server status's panel and add to frame
		JPanel northPanel = this.getConnectionStatusPanel(font);		
		this.add(northPanel, BorderLayout.NORTH);
		
		// Get server date's panel and add to frame
		JPanel center = getClientTextArea(font);
		this.add(center, BorderLayout.CENTER);
		
		// Get server date's panel and add to frame
		JPanel southPanel = getClientButton(font);
		this.add(southPanel, BorderLayout.SOUTH);
		
	}
	
	private Font getFontStyle() 
	{
		
		Font font = new Font ("Serif", Font.PLAIN, 30);
		
		return font;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btn)
		{
			
			try
			{
				Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
				
				// Update the status of the connection
				updateConnectionStatus(socket.isConnected());
				
				// Read from network
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// get the output stream from the socket.
		        OutputStream outputStream = socket.getOutputStream();
		        
				// create a data output stream from the output stream so we can send data through it
		        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				  
		        // write the message we want to send
		        String txt = textToCount.getText();
		        dataOutputStream.writeUTF(txt);
		        
				// Display the words counted
				String counted = bufferedReader.readLine();
				System.out.print(counted);
				JOptionPane.showMessageDialog(null, "Words Counted: " + counted);
				
				// Close everything
				dataOutputStream.flush(); // send the message
		        dataOutputStream.close(); // close the output stream when we're done.
				bufferedReader.close();
				socket.close();

			}
			catch(Exception error)
			{
				error.printStackTrace();
			}
			finally
			{
				//updateConnectionStatus(false);
			}
		}
		
	}
}
