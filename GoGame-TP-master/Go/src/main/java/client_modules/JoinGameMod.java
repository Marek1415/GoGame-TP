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

import static constants.Signals.*;
import static constants_modules.JoinGameModConstants.*;

/**
 * @author gumises
 * Player chooses the room to join.
 */
public class JoinGameMod extends JDialog 
implements SignalSender{
	
	//buttons
	JButton roomButton;
	
	//labels
	JLabel infoLabel;
	JLabel roomLabel;
	
	public JoinGameMod() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(STR_TITLE);
		setResizable(false);
	}
	
	public void init(final int [] rooms) {
		
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
			
			roomButton = new ActionButton(DIM_BUTTON, COL_BUTTON, rooms[i]) {
				
				@Override
				public void action(String signal) {
					sendSignal(signal);
					dispose();
				}
			};
			
			//button
			gbc.gridx = i%3;
			gbc.gridy = 1 + i/3;
			gbc.insets = new Insets(5, 5, 5, 5);
			add(roomButton, gbc);
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
		joinGameMod.init(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
    }
    
    /** Action Button for performing action on parent.*/
    private abstract class ActionButton extends JButton {
    	
    	private ActionButton(Dimension dim, Color col, final int newRoom) {
    		
    		super();
    		setText(Integer.toString(newRoom));
    		setPreferredSize(dim);
    		setBackground(col);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			action(CL_ROOMSET + " " + newRoom);
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
}
