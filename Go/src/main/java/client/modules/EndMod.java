package client.modules;

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

import static constants.EndModConstants.*;
import static constants.StartModConstants.DIM_DIALOG;


/**
 * @author gumises
 * End dialog 
 */
@SuppressWarnings("serial")
public class EndMod extends JDialog {

	//action buttons
	AbstractButton endButton;
	AbstractButton resignButton;
	AbstractButton cancelButton;
	
	//labels
	JLabel infoLabel;
	
	public EndMod() {
		
		//info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		
		//end button
		endButton = new ActionButton(STR_END, DIM_END, COL_END) {
			@Override
			public void action() {
				end();
			}
		};
		
		//resign button
		resignButton = new ActionButton(STR_RESIGN, DIM_RESIGN, COL_RESIGN) {
			@Override
			public void action() {
				resign();
			}
		};
		
		//cancel button
		cancelButton = new ActionButton(STR_CANCEL, DIM_CANCEL, COL_CANCEL) {
			@Override
			public void action() {
				cancel();
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
		setPreferredSize(DIM_DIALOG);
		setPreferredSize(DIM_DIALOG);
		
		pack();
		setVisible(true);
	}
	
	/** Giving up, must be override by parent. */
	public void resign() {
		//TODO make this abstract
		System.out.println("Resign!");
	}
	
	/** Proposing end, must be override by parent. */
	public void end() {
		//TODO make this abstract
		System.out.println("End!");
	}
	
	/** Cancel method, close the window. */
	public void cancel() {
		dispose();
	}
	
    public static void main( String[] args ) {
		new EndMod();
    }
    
    /*
     * Action Button for performing action on parent.
     */
    @SuppressWarnings("serial")
	private abstract class ActionButton extends JButton {
    	
    	/*
    	 * constructor
    	 */
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
}
