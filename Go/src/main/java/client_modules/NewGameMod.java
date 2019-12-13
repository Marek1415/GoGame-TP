package client_modules;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import client.Client;
import client_interfaces.SignalSender;

import static constants.Signals.*;
import static constants_modules.NewGameModConstants.*;

/**
 * @author gumises Player chooses game mode and board size.
 */
public class NewGameMod extends JFrame implements SignalSender{

	// components
	ModePanel modePanel;
	SizePanel sizePanel;
	StartButton startButton;
	Client client;
	
	public NewGameMod(Client client) {
		
		this.client = client;
		modePanel = new ModePanel();
		sizePanel = new SizePanel();
		startButton =  new StartButton();
		
		// gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);

		// mode panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(modePanel, gbc);
		
		// size panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(sizePanel, gbc);

		//start button
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 5, 10, 5);
		add(startButton, gbc);
		
		pack();
		setTitle(STR_TITLE);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/** Prepares signal fore sending.*/
	public void prepareSignal() {
		client.startButtons(CL_ROOMNEW 
				+ " " + modePanel.getCurrentMode()
				+ " " + sizePanel.getCurrentSize());
		setVisible(false);
		dispose();
	}
	
	/** Sends the signal to the panel. */
	public void sendSignal(String signal) {
		//System.out.println("[SIGNAL]  " + signal);
	}

	/*public static void main(String[] args) {
		// TODO delete main method
		NewGameMod newGameMod = new NewGameMod();
	}*/
	
    /** Starts the game. */
	private class StartButton extends JButton {
    	
    	private StartButton() {
    		
    		super(STR_BUTTON);
    		setPreferredSize(DIM_BUTTON);
    		setBackground(COL_BUTTON);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			prepareSignal();
      			}
    		});
    	}
	}
}
