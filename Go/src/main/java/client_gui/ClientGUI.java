package client_gui;

import javax.swing.*;

import client_interfaces.PawnOperations;

import static constants.PawnColors.*;
import java.awt.*;

import client_modules.AgreeMod;
import client_modules.JoinGameMod;
import client_modules.NewGameMod;
import client_modules.StartMod;
import client_panels.ActionPanel;
import client_panels.BoardPanel;

/**
 * @author gumises
 * Client GUI, displays Board, action Buttons, ... TODO add more
 */
public class ClientGUI extends JFrame implements PawnOperations
{
	//panels
	private BoardPanel boardPanel;
	private ActionPanel actionPanel;
	//private MessengerPanel messengerPanel;
	
	//modules
	private StartMod startMod;
	private JoinGameMod joinGameMod;
	private NewGameMod newGameMod;
	private AgreeMod agreeMod;
	
	/** Public constructor. */
	public ClientGUI()
	{
		
		//MODULES
		
		//start module
		startMod = new StartMod() {
			@Override
			public void newGame() {
				initNewGameModule();
			}
			
			@Override
			public void joinGame() {
				initJoinGameModule();
			}
		};
		
		//join game module
		joinGameMod = new JoinGameMod() {
			@Override
			public void sendSignal(String signal) {
				recSignal(signal);
			}
		};
		
		//new game module
		newGameMod = new NewGameMod() {
			@Override
			public void sendSignal(String signal) {
				recSignal(signal);
			}
		};
		
		//agree module
		agreeMod = new AgreeMod() {
			@Override
			public void sendSignal(String signal) {
				recSignal(signal);
			}
		};
		
		
		//PANELS
		
		//board panel
		boardPanel = new BoardPanel() {
			@Override
			public void sendSignal(String signal) {
				recSignal(signal);
			}
		};
		
		//action panel
		actionPanel = new ActionPanel() {
			@Override
			public void sendSignal(String signal) {
				recSignal(signal);
			}
		};
		
		//messenger panel
		//messengerPanel = new MessengerPanel();
		
		//initStartModule();
		//initJoinGameModule();
		//initAgreeModule();
		//initGame();
	}
	
	/** Initialize the start module.*/
	public void initStartModule() {
		startMod.init();
	}
	
	public void initNewGameModule() {
		newGameMod.init();
	}
	
	public void initJoinGameModule() {
		joinGameMod.init(new int[] {0, 1, 4, 5, 67});
	}
	
	/** Initialize the agree module.*/
	public void initAgreeModule() {
		agreeMod.init();
	}
	
	/** Displays the main game frame.*/
	public void initGame(int size) {
		boardPanel.init(size);
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.insets = new Insets(0,0,0,0);
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
		//add(messengerPanel, gbc);*/
		//setBorder(BorderFactory.createTitledBorder("hahaha"));
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.YELLOW);
		setVisible(true);
		
		addMessage("QWERTYUIOP");
		for(int i = 0; i < 20; i ++)
			addMessage(Integer.toString(i));
	}
	
	/** Deal with received signal from child.*/
	public void recSignal(String signal) {
		System.out.println("[SIGNAL]  " + signal);
	}
	
	/** Adds a pawn with specific color on specific position.*/
	public void addPawn(int number, int color) {
		boardPanel.addPawn(number, color);
	}
	
	/** Remove a pawn from specific position.*/
	public void removePawn(int number) {
		boardPanel.removePawn(number);
	}
	
	/** Adds message to the message panel.*/
	public void addMessage(String message) {
		actionPanel.addMessage(message);
	}

	/** Creating ClientGUI for test. */
	public static void main(String[] args) {
		//TODO delete main method
		ClientGUI clientGUI = new ClientGUI();
		//clientGUI.initStartModule();
		clientGUI.initGame(20);
	}

}
