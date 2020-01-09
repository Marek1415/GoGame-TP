package client_gui;

import javax.swing.*;

import client.Client;
import client_interfaces.PawnOperations;

import static constants.PawnColors.*;
import static constants.Signals.*;
import java.awt.*;

import client_modules.AgreeMod;
import client_modules.EndMod;
import client_modules.JoinGameMod;
import client_modules.NewGameMod;
import client_modules.SelectReplyMod;
import client_modules.StartMod;
import client_panels.ActionPanel;
import client_panels.BoardPanel;
import client_panels.MessengerPanel;
import client_panels.PointsPanel;
import constants.PawnColors;
import client.*;
/**
 * @author gumises
 * Client GUI Facade, connects GUI panels and simplifies thiers methods.
 */
public class ClientGUI extends JFrame implements PawnOperations
{
	//panels
	private	BoardPanel boardPanel;
	private ActionPanel actionPanel;
	private PointsPanel pointsPanel;
	private MessengerPanel messengerPanel;
	
	//modules
	private StartMod startMod;
	private EndMod endMod;
	private NewGameMod newGameMod;
	private AgreeMod agreeMod;
	private SelectReplyMod selectReplyMod;

	/** Public constructor. */
	public ClientGUI() {		
		
		//MODULES
		
		//start module
		startMod = new StartMod() {
			@Override
			public void newGame() {
				initNewGameModule();
			}
			
			@Override
			public void joinGame() {
				recSignalNow(CL_ROOMJOIN);
			}
			
			@Override
			public void showReply() {
				initSelectReplyMod();
			}
		};
		
		//end module
		endMod = new EndMod() {
			@Override
			public void sendSignal(String signal) {
				//recSignalNow(signal);
				System.exit(0);
			}
		};
		
		//new game module
		newGameMod = new NewGameMod() {
			@Override
			public void sendSignal(String signal, int size) {
				initGame(size);
				recSignalNow(signal);
			}
		};
		
		//agree module
		agreeMod = new AgreeMod() {
			@Override
			public void sendSignal(String signal) {
				recSignalNow(signal);
			}
		};
		
		//agree module
		selectReplyMod = new SelectReplyMod() {
			@Override
			public void selectGame(int id) {
				initReplyMod(id);
			}
		};
		
		
		//PANELS
		
		//board panel
		boardPanel = new BoardPanel() {
			@Override
			public void sendSignal(String signal) {
				recSignalWait(signal);
			}
		};
		
		//action panel
		actionPanel = new ActionPanel() {
			
			@Override
			public void sendSignalNow(String signal) {
				recSignalNow(signal);
			}
			
			@Override
			public void sendSignalWait(String signal) {
				recSignalWait(signal);
			}
		};
		
		//points panel
		pointsPanel = new PointsPanel();
		
		//messenger panel
		messengerPanel = new MessengerPanel() {
			@Override
			public void sendSignal(String signal) {
				recSignalNow(signal);
			}
		};

		pack();
		
		//TODO DO_NOTGIN_ON_CLOSA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/** Initialize the start module.*/
	public void initStartModule() {
		startMod.init();
		startMod.setLocationRelativeTo(this);
		pack();
	}
	
	/** Initialize the end module.*/
	public void initEndModule(String status, String points) {
		endMod.init(status, points);
		if(agreeMod.isVisible())
			endMod.setLocationRelativeTo(agreeMod);
		else
			endMod.setLocationRelativeTo(this);
		pack();
	}
	
	/** Switch off agree module, switch on main frame. */
	public void hideAgreeModule() {
		agreeMod.setVisible(false);
		this.setVisible(true);
		pack();
	}
	
	/** Initializes new game module. */
	public void initNewGameModule() {
		newGameMod.init();
		newGameMod.setLocationRelativeTo(this);
		pack();
	}
	
	/** Initializes the agree module.*/
	public void initAgreeModule() {
		agreeMod.init(
				boardPanel.getCurrentSize(),
				boardPanel.getCurrentPawns()
				);
		agreeMod.setLocationRelativeTo(this);
		this.setVisible(false);
		pack();
	}
	
	/** Initializes the select reply module.*/
	public void initSelectReplyMod() {
		selectReplyMod.init();
	}
	
	/** Initializes the reply module.*/
	public void initReplyMod(int id) {
		System.out.println("reply selected: " + id);
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
		gbc.weightx = 1;
		gbc.weighty = 0;
		add(pointsPanel, gbc);
		
		//board panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		add(boardPanel, gbc);
		
		//messenger panel
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 0;
		add(messengerPanel, gbc);
		
		//action panel
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.weightx = 0;
		gbc.weighty = 1;

		add(actionPanel, gbc);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.YELLOW);
		setResizable(false);
		setVisible(true);
		
	}
	
	/** Deal with received signal from child for which client can wait.*/
	public void recSignalNow(String signal) {
		
	}
	
	/** Deal with received signal from child which client execute immediately. */
	public void recSignalWait(String signal) {
		
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
	
	/** Invoked when oponent is disconnected. */
	public void disconnected() {
		pointsPanel.disconnected();
	}
	
	/** Invoked when enemy join the game. */
	public void enemyJoin() {
		actionPanel.enemyJoin();
	}
	
	/** Switch on piece of territory in AgreeMod Panel.*/
	public void addTerritory(int position, int status) {
		agreeMod.addTerritory(position, status);
	}
	
	/** Switches the conflict status. */
	public void switchConflict(String conflict) {
		agreeMod.switchConflict(conflict);
	}
	
	/** Sets player color. */
	public void setPlayerColor(Pawn color) {
		actionPanel.setPlayerColor(color);
	}

	/** Creating ClientGUI for test. */
	/*public static void main(String[] args) {
		//TODO delete main method
		ClientGUI clientGUI = new ClientGUI();
		//clientGUI.initStartModule();
		clientGUI.initGame(20);
	}*/

}
