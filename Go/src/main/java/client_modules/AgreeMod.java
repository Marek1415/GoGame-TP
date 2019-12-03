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

import static constants.AgreeModConstants.*;


/**
 * @author gumises
 * Dialog for agree or disagree with enemy proposal of game end.
 */
@SuppressWarnings("serial")
public class AgreeMod extends JDialog {
	//TODO make class abstract

	//action buttons
	AbstractButton agreeButton;
	AbstractButton disagreeButton;
	
	//labels
	JLabel infoLabel;
	
	public AgreeMod() {
		
		//info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		
		//end button
		agreeButton = new ActionButton(STR_AGREE, DIM_AGREE, COL_AGREE) {
			@Override
			public void action() {
				agree();
			}
		};
		
		//resign button
		disagreeButton = new ActionButton(STR_DISAGREE, DIM_DISAGREE, COL_DISAGREE) {
			@Override
			public void action() {
				disagree();
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
		add(agreeButton, gbc);
		
		//resign
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(disagreeButton, gbc);
		
		setTitle(STR_TITLE);
		setResizable(false);
		
		pack();
		setVisible(true);
	}
	
	/** Agree to end the game. */
	public void agree() {
		//TODO make this abstract
		System.out.println("Agree!");
	}
	
	/** Disagree to end the game. */
	public void disagree() {
		//TODO make this abstract
		System.out.println("Disagree!");
	}
	
    public static void main( String[] args ) {
    	//TODO delete main method
		new AgreeMod();
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
