import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientTranslatorGUI  extends JFrame implements ActionListener
{
	private JLabel tittle, anslbl;
	private JButton bm, arb, krn;
	private JTextField input;
	
	private int width = 750;
	private int height = 200;
	
	private String textinput = "";
	private String language = "";
	
	private boolean pressed = false;
	
	public ClientTranslatorGUI()
	{
		this.setLayout(new BorderLayout());
		this.setTitle("TCP Translator: ");
		this.setSize(new Dimension(width, height));
		
		// Must close on X
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//center window
		this.setLocationRelativeTo(null);
		
		this.tittle = new JLabel("Type to translate: ");
		this.anslbl = new JLabel("");
		
		this.input = new JTextField (20);
		
		this.bm = new JButton("Malay");
		this.arb = new JButton("Arabic");
		this.krn = new JButton("Korean");
		
		
		bm.addActionListener(this);
		arb.addActionListener(this);
		krn.addActionListener(this);
		
		loadForm();
	}
	
	private JPanel topPanel(Font font)
	{
		JPanel panel = new JPanel();
		tittle.setFont(font);
		input.setFont(font);
		input.setOpaque(true);
		tittle.setOpaque(true);
		panel.add(tittle);
		panel.add(input);
		return panel;
	}
	
	private JPanel centerPanel(Font font)
	{
		JPanel panel = new JPanel();
		bm.setFont(font);
		arb.setFont(font);
		krn.setFont(font);
		bm.setOpaque(true);
		arb.setOpaque(true);
		krn.setOpaque(true);
		panel.add(bm);
		panel.add(arb);
		panel.add(krn);
		return panel;
	}
	
	private JPanel bottomPanel(Font font)
	{
		JPanel panel = new JPanel();
		anslbl.setOpaque(true);
		anslbl.setFont(font);
		panel.add(anslbl);
		return panel;
	}
	
	private Font getFontStyle() 
	{
		
		Font font = new Font ("Serif", Font.PLAIN, 30);
		
		return font;
		
	}

	private void loadForm()
	{
		Font font = this.getFontStyle();
		
		JPanel top = this.topPanel(font);
		this.add(top, BorderLayout.NORTH);
		
		JPanel center = this.centerPanel(font);
		this.add(center, BorderLayout.CENTER);
		
		JPanel btm = this.bottomPanel(font);
		this.add(btm, BorderLayout.SOUTH);
	}
	
	
	public void setAnsLbl(String translated)
	{
		anslbl.setText(translated);
	}
	
	public void updateConnectionStatus (boolean connStatus) 
	{
		
		// Default status. Assuming for the worst case scenario.
		this.setTitle("TCP Translator: No connection to server.");
		
		// Validate status of connection
		if (connStatus)
			this.setTitle("TCP Translator: Connection has established.");
	}
	
	public String getLanguage() 
	{
		return this.language;
	}
	
	public String getText() 
	{
		return this.textinput;
	}
	
	private void setFonttoArabic()
	{
		anslbl.setFont(new Font("Arabic",Font.PLAIN,30));
	}
	
	private void setFonttoKorean()
	{
		anslbl.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
	}
	
	public boolean ispressed()
	{
		return pressed;
	}
	
	public void updatebtn(boolean b)
	{
		pressed = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if(e.getSource()== bm)
		{
			updatebtn(true);
			textinput = input.getText();
			language = "malay";	
			anslbl.setFont(getFontStyle());	
			ClientTranslationApplication.btnPressed();
//			try
//			{
//				Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
//				
//				updateConnectionStatus(socket.isConnected());
//				
//				DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
//				
//				String textinput = getText();
//				String language = getLanguage();
//				
//				DataOutputStream dataoutputStream = new DataOutputStream(socket.getOutputStream());
//
//				dataoutputStream.writeUTF(textinput);
//				dataoutputStream.writeUTF(language);
//					
//				String translated = datainputstream.readUTF();
//					
//				setAnsLbl(translated);
//				updatebtn(false);
//								
//				
//				datainputstream.close();
//				dataoutputStream.close();
//				socket.close();
//			}
//			catch(IOException e1)
//			{
//				e1.printStackTrace();
//			} 

		}
		else if(e.getSource()== arb)
		{
			updatebtn(true);
			textinput = input.getText();
			language = "arab";
			setFonttoArabic();
			ClientTranslationApplication.btnPressed();
//			try
//			{
//				Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
//				
//				updateConnectionStatus(socket.isConnected());
//				
//				DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
//				
//				String textinput = getText();
//				String language = getLanguage();
//				
//				DataOutputStream dataoutputStream = new DataOutputStream(socket.getOutputStream());
//
//				dataoutputStream.writeUTF(textinput);
//				dataoutputStream.writeUTF(language);
//					
//				String translated = datainputstream.readUTF();
//					
//				setAnsLbl(translated);
//				updatebtn(false);
//								
//				
//				datainputstream.close();
//				dataoutputStream.close();
//				socket.close();
//			}
//			catch(IOException e1)
//			{
//				e1.printStackTrace();
//			} 
		}
		else
		{
			updatebtn(true);
			textinput = input.getText();
			language = "korean";
			setFonttoKorean();
			ClientTranslationApplication.btnPressed();
//			try
//			{
//				Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
//				
//				updateConnectionStatus(socket.isConnected());
//				
//				DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
//				
//				String textinput = getText();
//				String language = getLanguage();
//				
//				DataOutputStream dataoutputStream = new DataOutputStream(socket.getOutputStream());
//
//				dataoutputStream.writeUTF(textinput);
//				dataoutputStream.writeUTF(language);
//					
//				String translated = datainputstream.readUTF();
//					
//				setAnsLbl(translated);
//				updatebtn(false);
//								
//				
//				datainputstream.close();
//				dataoutputStream.close();
//				socket.close();
//			}
//			catch(IOException e1)
//			{
//				e1.printStackTrace();
//			} 
		}	
		
	}
}
