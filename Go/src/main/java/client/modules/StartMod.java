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

import static constants.StartModConstants.*;


/**
 * @author gumises
 * Start dialog, client chooses the game mode.
 */
@SuppressWarnings("serial")
public class StartMod extends JDialog {

	//action buttons
	AbstractButton playerButton;
	AbstractButton botButton;
	
	//labels
	JLabel infoLabel;
	
	public StartMod() {
		
		//info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		
		//end button
		playerButton = new ActionButton(STR_PLAYER, DIM_PLAYER, COL_PLAYER) {
			@Override
			public void action() {
				player();
			}
		};
		
		//resign button
		botButton = new ActionButton(STR_BOT, DIM_BOT, COL_BOT) {
			@Override
			public void action() {
				bot();
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
		add(playerButton, gbc);
		
		//resign
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(botButton, gbc);
		
		setTitle(STR_TITLE);
		setPreferredSize(DIM_DIALOG);
		setResizable(false);
		
		pack();
		setVisible(true);
	}
	
	/** Playing with the other player method, must be override by parent. */
	public void player() {
		System.out.println("Player!");
	}
	
	/** Playing with bot, must be override by parent. */
	public void bot() {
		System.out.println("Bot!");
	}
	
    public static void main( String[] args ) {
		new StartMod();
    }
    
    /*
     * Action Button for performing action on parent.
     */
    @SuppressWarnings("serial")
	private class ActionButton extends JButton {
    	
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
    	public void action() {
    		//override
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
