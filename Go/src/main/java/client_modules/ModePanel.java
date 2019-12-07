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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static constants.ModePanelConstants.*;
import static constants.BoardSizes.SIZES;
import static constants.GameModes.*;


/**
 * @author gumises
 * Chooses the game mode.
 */
public class ModePanel extends JPanel {

	//components
	JLabel infoLabel;
	ActionButton playerButton;
	ActionButton botButton;
	int currentMode;

	
	public ModePanel() {
		
		//info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		
		//end button
		playerButton = new ActionButton(STR_PLAYER, DIM_PLAYER, COL_BUTTON, PLAYER);
		
		//resign button
		botButton = new ActionButton(STR_BOT, DIM_BOT, COL_BUTTON, BOT);
		
		playerButton.setPressed();
		
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
		add(playerButton, gbc);
		
		//resign
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(botButton, gbc);
		
		setVisible(true);
	}
	
    /** Returns the value of current mode. */
    public int getCurrentMode() {
    	return currentMode;
    }
    
    /** Sets the value of current size. */
    public void setCurrentMode(int newMode) {
    	currentMode = newMode;
    }
    
    /** Sets no pressed mode on all buttons. */
    public void setNoPressedOthers(int mode) {
    	if(mode == PLAYER)
    		botButton.setNoPressed();
    	else
    		playerButton.setNoPressed();
    }
	
    public static void main( String[] args ) {
    	//TODO delete main method
		new ModePanel();
    }
    
    /** Action Button for performing action on parent. */
	private class ActionButton extends JButton {
    	
    	int mode;
    	Color colorPressed;
    	Color colorNotPressed;
    	
    	private ActionButton(String text, Dimension dim, Color col, int mode) {
    		
    		super(text);
    		this.mode = mode;
    		this.colorPressed = col.darker();
    		this.colorNotPressed = col.brighter();
    		setNoPressed();
    		setPreferredSize(dim);
    		setBackground(col);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			setPressed();
      			}
    		});
    	}
    	
    	/** Make the button pressed mode.*/
    	public void setPressed() {
    		setNoPressedOthers(mode);
			setCurrentMode(mode);
    		setBackground(colorPressed);
    	}
    	
    	/** Make the button not pressed mode.*/
    	public void setNoPressed() {
    		setBackground(colorNotPressed);
    	}
    }
    
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