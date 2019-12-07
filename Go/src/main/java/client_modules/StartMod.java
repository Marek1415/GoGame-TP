package client_modules;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import client_interfaces.SignalSender;

import static constants.StartModConstants.*;
import static constants.Signals.*;


/**
 * @author gumises
 * Player chooses the game mode.
 */
public class StartMod extends JDialog {

	//action buttons
	AbstractButton newGameButton;
	AbstractButton joinGameButton;
	
	//labels
	JLabel infoLabel;
	
	public StartMod() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(STR_TITLE);
		setResizable(false);
	}
	
	/** Initialize the dialog window.*/
	public void init() {
		
		//info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		
		//end button
		newGameButton = new ActionButton(STR_NEW, DIM_NEW, COL_NEW) {
			@Override
			public void action() {
				newGame();
				dispose();
			}
		};
		
		//resign button
		joinGameButton = new ActionButton(STR_JOIN, DIM_JOIN, COL_JOIN) {
			@Override
			public void action() {
				joinGame();
				dispose();
			}
		};
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//info
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(infoLabel, gbc);
		
		//end
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(newGameButton, gbc);
		
		//resign
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(joinGameButton, gbc);
		
		pack();
		setVisible(true);
	}
	
    public static void main( String[] args ) {
    	//TODO delete main method
		StartMod startMod = new StartMod();
		startMod.init();
    }
    
    /** New game mode, must be override by parent.*/
    public void newGame() {	
    }
    
    /** Join game mode, must be override by parent.*/
    public void joinGame() {
    }
	
    /** Action Button for performing action on parent.*/
	private abstract class ActionButton extends JButton {
    	
    	private ActionButton(String text, Dimension dim, Color col) {
    		
    		super(text);
    		setPreferredSize(dim);
    		setBackground(col);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			action();
      			}
    		});
    		
    	}
    	
    	/** Action method, must be override by parent. */
    	public abstract void action();
    }
    
    /** Label for displaying info about dialog.*/
    private class InfoLabel extends JLabel {
    	
    	private InfoLabel(String text, Dimension dim) {
    		super(text);
    		setFont(FONT);
    		setPreferredSize(dim);
    		setHorizontalAlignment(JLabel.CENTER);
    	    setVerticalAlignment(JLabel.CENTER);
    	}
    }
}
