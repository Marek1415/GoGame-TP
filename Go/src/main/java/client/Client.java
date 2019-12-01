package client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import client.interfaces.AgreeMethod;
import client.interfaces.EndMethod;
import client.interfaces.ModInits;
import client.interfaces.RoomMethod;
import client.interfaces.SizeMethod;
import client.interfaces.StartMethod;
import client.panels.ActionPanel;
import client.panels.BoardPanel;
import client.panels.MessengerPanel;

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
	
	/** Public constructor. */
	public Client() {
		
		boardPanel = new BoardPanel();
		
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
		
		pack();
		setVisible(true);
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

	/** Creating new Client. */
	public static void main(String[] args) {
		new Client();
	}

}
