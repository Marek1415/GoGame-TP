package client_gui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;

import static constants.PawnColors.PAWN_WHITE;
import static constants.Signals.*;

import java.awt.*;
import java.awt.event.*;

import client_interfaces.AgreeMethod;
import client_interfaces.EndMethod;
import client_interfaces.ModInits;
import client_interfaces.RoomMethod;
import client_interfaces.SizeMethod;
import client_interfaces.StartMethod;
import client_modules.JoinGameMod;
import client_modules.StartMod2;
import client_panels.ActionPanel;
import client_panels.BoardPanel;
import client_panels.MessengerPanel;

/**
 * @author gumises
 * Client GUI, displays Board, action Buttons, ... TODO add more
 */
public class ClientGUI extends JFrame
{
	//panels
	private BoardPanel boardPanel;
	private ActionPanel actionPanel;
	//private MessengerPanel messengerPanel;
	
	//modules
	private StartMod2 startMod;
	private JoinGameMod joinGameMod;
	
	/** Public constructor. */
	public ClientGUI()
	{
		
		//MODULES
		
		//start module
		startMod = new StartMod2() {
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
		
		
		//PANELS
		
		//board panel
		boardPanel = new BoardPanel();
		
		//action panel
		actionPanel = new ActionPanel() {
			@Override
			public void sendSignal(String signal) {
				recSignal(signal);
			}
		};
		
		//messenger panel
		//messengerPanel = new MessengerPanel();
		
		initStartModule();
		//initJoinGameModule();
		
	}
	
	/** Displays the start screen.*/
	public void initStartModule() {
		startMod.init();
	}
	
	public void initNewGameModule() {
		
	}
	
	public void initJoinGameModule() {
		joinGameMod.init(new String[] {"pierwszy", "drugi", "trzeci", "czwarte"});
	}
	
	/** Displays the main game frame.*/
	public void initGame() {
		boardPanel.init(7);
		startMod.init();
		boardPanel.addPawn(5, PAWN_WHITE);
		
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
		//add(messengerPanel, gbc);*/
		//setBorder(BorderFactory.createTitledBorder("hahaha"));
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//setVisible(true);
	}
	
	/** Deal with received signal from child.*/
	public void recSignal(String signal) {
		System.out.println("[SIGNAL]  " + signal);
	}

	/** Creating ClientGUI for test. */
	public static void main(String[] args) {
		//TODO delete main method
		new ClientGUI();
	}

}
