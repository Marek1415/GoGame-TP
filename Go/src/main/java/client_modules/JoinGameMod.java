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

import static constants.JoinGameModConstants.*;
import static constants.Signals.*;

/**
 * @author gumises
 * Player chooses the room to join.
 */
public class JoinGameMod extends JDialog 
implements SignalSender{
	
	//buttons
	AbstractButton roomButton;
	
	//labels
	JLabel infoLabel;
	JLabel roomLabel;
	
	public JoinGameMod() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(STR_TITLE);
		setResizable(false);
	}
	
	public void init(final String [] rooms) {
		
		//info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//info
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(infoLabel, gbc);
		
		//rooms
		gbc.gridwidth = 1;
		for(int i = 0; i < rooms.length; i++) {
			
			roomLabel = new RoomLabel(i, DIM_LABEL);
			roomButton = new ActionButton(DIM_BUTTON, COL_BUTTON, i, rooms[i]) {
				
				@Override
				public void action(String signal) {
					sendSignal(signal);
					dispose();
				}
			};
			
			//button
			gbc.gridx = i%3;
			gbc.gridy = 1 + 2*(i/3);
			gbc.insets = new Insets(5, 5, 0, 5);
			add(roomButton, gbc);
			
			//label
			gbc.gridx = i%3;
			gbc.gridy = 2 + 2*(i/3);
			gbc.insets = new Insets(0, 5, 5, 5);
			add(roomLabel, gbc);
		}
		
		pack();
		setVisible(true);
	}
	
	/** Sends the signal to the panel.*/
	public void sendSignal(String signal) {
		//System.out.println("[SIGNAL]  " + signal);
	}
	
    public static void main( String[] args ) {
    	//TODO delete main method
		JoinGameMod joinGameMod = new JoinGameMod();
		joinGameMod.init(new String[] {"pierwszy", "drugi", "trzeci", "czwarte"});
    }
    
    /** Action Button for performing action on parent.*/
    private abstract class ActionButton extends JButton {
    	
    	//room noumber
    	int room;
    	
    	/*
    	 * constructor
    	 */
    	private ActionButton(Dimension dim, Color col, int newRoom, final String newRoomName) {
    		
    		super();
    		setText(newRoomName);
    		this.room = newRoom;
    		setPreferredSize(dim);
    		setBackground(col);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			action(CL_ROOMSET + " " + newRoomName);
      			}
    		});
    		
    	}
    	
    	/** Action method, must be override by parent. */
    	public abstract void action(String signal);
    }
    
    /** Label for displaying info about dialog. */
    private class InfoLabel extends JLabel {
    	
    	private InfoLabel(String text, Dimension dim) {
    		super(text);
    		setFont(FONT);
    		setPreferredSize(dim);
    		setHorizontalAlignment(JLabel.CENTER);
    	    setVerticalAlignment(JLabel.CENTER);
    	}
    }
    
    /** Label for displaying room number.*/
    private class RoomLabel extends JLabel {
    	
    	private RoomLabel(int number, Dimension dim) {
    		super();
    		setText(getName(number));
    		setFont(FONT);
    		setPreferredSize(dim);
    		setHorizontalAlignment(JLabel.CENTER);
    	    setVerticalAlignment(JLabel.CENTER);
    	}
    	
    	/**
    	 * Calculate the label text by the number.
    	 * @param number
    	 * @return text for the button
    	 */
    	private String getName(int number) {
    		return STR_LABEL + " " + Integer.toString(number+1);
    	}
    }
}
