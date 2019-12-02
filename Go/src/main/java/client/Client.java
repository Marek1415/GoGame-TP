package client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;

import client_interfaces.AgreeMethod;
import client_interfaces.EndMethod;
import client_interfaces.ModInits;
import client_interfaces.RoomMethod;
import client_interfaces.SizeMethod;
import client_interfaces.StartMethod;
import client_panels.ActionPanel;
import client_panels.BoardPanel;
import client_panels.MessengerPanel;

/**
 * @author gumises
 * Client GUI, displays Board, action Buttons, ... TODO add more
 */
public class Client extends JFrame 
implements AgreeMethod, EndMethod, RoomMethod, SizeMethod, StartMethod, ModInits {

	//components
	private BoardPanel boardPanel;
	private ActionPanel actionPanel;
	//private MessengerPanel messengerPanel;
	Socket socket = null;
	PrintWriter out = null;
	Scanner in = null;
	/** Public constructor. */
	public Client() {
		
		boardPanel = new BoardPanel();
		boardPanel.init(7);
		
		//action panel
		actionPanel = new ActionPanel() {
			
			@Override
			public void ready() {
				readyInit();
			}
			
			@Override
			public void check() {
				checkInit();
			}
			
			@Override
			public void end() {
				endInit();
			}
		};
		
		//messengerPanel = new MessengerPanel();
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		
		//gbc init
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//board panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(boardPanel, gbc);
		
		//action panel
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		add(actionPanel, gbc);
		
		//messenger panel
		//gbc.gridx = 0;
		//gbc.gridy = 1;
		//gbc.gridheight = 1;
		//add(messengerPanel, gbc);
		listen();
		pack();
		setVisible(true);
		out.println("checking");
	}
	
	public void readyInit() {
		System.out.println("Ready!");
	}
	
	public void checkInit() {
		System.out.println("Check!");
	}
	
	public void endInit() {
		System.out.println("End!");
	}
	
	public void startMode(int mode) {
		// TODO Auto-generated method stub

	}

	public void sizeBoard(int size) {
		// TODO Auto-generated method stub

	}

	public void roomNoumber(int roomNoumber) {
		// TODO Auto-generated method stub

	}

	public void roomNew() {
		// TODO Auto-generated method stub

	}

	public void endMode(int mode) {
		// TODO Auto-generated method stub

	}

	public void agreeMode(int mode) {
		// TODO Auto-generated method stub

	}

	public void listen()
	{
		try
			{
				socket = new Socket("localhost", 4444);
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new Scanner(new InputStreamReader(socket.getInputStream()));
			}
		catch(UnknownHostException e)
			{
				System.out.println("Nieznany host");
				System.exit(1);
			}
		catch(IOException e)
			{
				System.out.println("Brak I/O");
				System.exit(1);
			}
	}

	/** Creating new Client. */
	public static void main(String[] args) {
		Client client = new Client();
	}

}
