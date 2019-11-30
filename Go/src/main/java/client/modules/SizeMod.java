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

import static constants.SizeModConstants.*;


/**
 * @author gumises
 * Size dialog, client chooses the board size.
 */
@SuppressWarnings("serial")
public class SizeMod extends JDialog {
	
	//buttons
	AbstractButton sizeButton;
	
	//labels
	JLabel infoLabel;
	
	public SizeMod(final int [] sizes) {
		
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
		
		for(int i = 0; i < sizes.length; i++) {
			
			sizeButton = new ActionButton(DIM_BUTTON, COL_BUTTON, sizes[i]) {
				
				@Override
				public void action() {
					boardSize(size);
				}
			};
			
			gbc.gridx = i%3;
			gbc.gridy = 1 + i/3;
			gbc.gridwidth = 1;
			add(sizeButton, gbc);
		}
		
		setTitle(STR_TITLE);
		setResizable(false);
		
		pack();
		setVisible(true);
	}
	
	/** Sets the board size, must be override by parent. */
	public void boardSize(int newSize) {
		//TODO make this abstract
		System.out.println(newSize);
	}
	
    public static void main( String[] args ) {
		new SizeMod(new int[] {7,9,13, 25, 253});
    }
    
    /*
     * Action Button for performing action on parent.
     */
    private abstract class ActionButton extends JButton {
    	
    	//board size
    	int size;
    	
    	/*
    	 * constructor
    	 */
    	private ActionButton(Dimension dim, Color col, int newSize) {
    		
    		super();
    		setText(getName(newSize));
    		this.size = newSize;
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
    	

    	/**
    	 * Calculate the button name by the size.
    	 * @param size
    	 * @return name of the button
    	 */
    	private String getName(int size) {
    		return Integer.toString(size) + "x" + Integer.toString(size);
    	}
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
