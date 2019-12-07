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
import client_panels.MessengerPanel;
import client_panels.PointsPanel;

/**
 * @author gumises
 * Client GUI, displays Board, action Buttons, ... TODO add more
 */
public class ClientGUI extends JFrame implements PawnOperations
{
	//panels
	private BoardPanel boardPanel;
	private ActionPanel actionPanel;
	private PointsPanel pointsPanel;
	private MessengerPanel messengerPanel;
	
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
		
		//points panel
		pointsPanel = new PointsPanel();
		
		//messenger panel
		messengerPanel = new MessengerPanel() {
			@Override
			public void sendSignal(String signal) {
				recSignal(signal);
			}
		};
		
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
		
		//points panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(pointsPanel, gbc);
		
		//board panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(boardPanel, gbc);
		
		//messenger panel
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(messengerPanel, gbc);
		
		//action panel
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		add(actionPanel, gbc);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.YELLOW);
		setVisible(true);
		
		//addMessage("QWERTYUIOP");
		//for(int i = 0; i < 20; i ++)
		//	addMessage(Integer.toString(i));
		
		addPawn(100, PAWN_BLACK);
		setPoints("100");
		turnON();
		turnOFF();
		
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
	
	/** Sets the number of points. */
	public void setPoints(String points) {
		pointsPanel.setPoints(points);
	}
	
	/** Sets the turn mode ON. */
	public void turnON() {
		pointsPanel.turnON();
	}
	
	/** Sets the turn mode OFF. */
	public void turnOFF() {
		pointsPanel.turnOFF();
	}

	/** Creating ClientGUI for test. */
	public static void main(String[] args) {
		//TODO delete main method
		ClientGUI clientGUI = new ClientGUI();
		//clientGUI.initStartModule();
		clientGUI.initGame(20);
	}

}
