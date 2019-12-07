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

import static constants.EndModConstants.*;
import static constants.Signals.*;


/**
 * @author gumises
 * Displays option for game ending.
 */
public class EndMod extends JDialog implements SignalSender {
	//TODO make class abstract

	//action buttons
	AbstractButton endButton;
	AbstractButton resignButton;
	AbstractButton cancelButton;
	
	//labels
	JLabel infoLabel;
	
	/** Initialize the dialog window.*/
	public EndMod() {
		
		//info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		
		//end button
		endButton = new ActionButton(STR_END, DIM_END, COL_END) {
			@Override
			public void action() {
				sendSignal(CL_END);
				initMe(false);
			}
		};
		
		//resign button
		resignButton = new ActionButton(STR_RESIGN, DIM_RESIGN, COL_RESIGN) {
			@Override
			public void action() {
				sendSignal(CL_RESIGN);
				initMe(false);
			}
		};
		
		//cancel button
		cancelButton = new ActionButton(STR_CANCEL, DIM_CANCEL, COL_CANCEL) {
			@Override
			public void action() {
				initMe(false);
			}
		};
		
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
		
		//end
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(endButton, gbc);
		
		//resign
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(resignButton, gbc);
		
		//cancel
		gbc.gridx = 2;
		gbc.gridy = 1;
		add(cancelButton, gbc);
		
		setTitle(STR_TITLE);
		setResizable(false);
		
		pack();
		setVisible(false);
	}
	
	/** Sends the signal to the panel.*/
	public void sendSignal(String signal) {
		//System.out.println("[SIGNAL]  " + signal);
	}
	
	/** Makes the dialog visible. */
	public void init() {
		setVisible(true);
	}
	
	/** Makes the dialog visible or invisible. */
	public void initMe(boolean isInit) {
		setVisible(isInit);
	}
	
    public static void main( String[] args ) {
    	//TODO delete main method
		EndMod endMod = new EndMod();
		endMod.init();
    }
    
    /**Performs action on parent. */
	private abstract class ActionButton extends JButton {
    	
    	private ActionButton(String text, Dimension dim, Color col) {
    		
    		super(text);
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
    	
    	/** action method, must be override by parent */
    	public abstract void action();
    }
    
    /** Displaying info about dialog. */
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
