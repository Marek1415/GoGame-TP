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

import static constants.RoomModConstants.*;


/**
 * @author gumises
 * Room dialog, client chooses the room or creates new.
 */
@SuppressWarnings("serial")
public class RoomMod extends JDialog {
	//TODO make class abstract
	
	//buttons
	AbstractButton roomButton;
	AbstractButton newRoomButton;
	
	//labels
	JLabel infoLabel;
	JLabel roomLabel;
	
	public RoomMod(final String [] rooms) {
		
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
		
		gbc.gridwidth = 1;
		
		//rooms
		int i;
		for(i = 0; i < rooms.length; i++) {
			
			roomLabel = new RoomLabel(i, DIM_LABEL);
			roomButton = new ActionButton(DIM_BUTTON, COL_BUTTON, i, rooms[i]) {
				
				@Override
				public void action() {
					room(room);
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
		
		//new room
		newRoomButton = new ActionButton(DIM_BUTTON, COL_NEWROOM, i, STR_NEWROOM) {
			
			@Override
			public void action() {
				newRoom();
			}
		};
		
		//new room
		gbc.gridx = i%3;
		gbc.gridy = 1 + 2*(i/3);
		gbc.insets = new Insets(5, 5, 0, 5);
		add(newRoomButton, gbc);
		
		setTitle(STR_TITLE);
		setResizable(false);
		
		pack();
		setVisible(true);
	}
	
	/** Sets the room, must be override by parent. */
	public void room(int newRoom) {
		//TODO make this abstract
		System.out.println(newRoom);
	}
	
	/** Sets the board size, must be override by parent. */
	public void newRoom() {
		//TODO make this abstract
		System.out.println("newRoom");
	}
	
    public static void main( String[] args ) {
    	//TODO delete main method
		new RoomMod(new String[] {"pierwszy", "drugi", "trzeci", "czwarte"});
    }
    
    /*
     * Action Button for performing action on parent.
     */
    private abstract class ActionButton extends JButton {
    	
    	//room noumber
    	int room;
    	
    	/*
    	 * constructor
    	 */
    	private ActionButton(Dimension dim, Color col, int newRoom, String newRoomName) {
    		
    		super();
    		setText(newRoomName);
    		this.room = newRoom;
    		setPreferredSize(dim);
    		setBackground(col);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			action();
      			}
    		});
    		
    	}
    	
    	/*
    	 * action method, must be override by parent
    	 */
    	public abstract void action();
    	
    }
    
    /*
     * Label for displaying info about dialog.
     */
    private class InfoLabel extends JLabel {
    	
    	private InfoLabel(String text, Dimension dim) {
    		super(text);
    		setFont(FONT);
    		setPreferredSize(dim);
    		setHorizontalAlignment(JLabel.CENTER);
    	    setVerticalAlignment(JLabel.CENTER);
    	}
    }
    
    /*
     * Label for displaying room number.
     */
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
