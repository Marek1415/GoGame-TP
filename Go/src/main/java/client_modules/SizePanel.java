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

import static constants.BoardSizes.SIZES;
import static constants_panels.SizePanelConstants.*;


/**
 * @author gumises
 * Chooses the board size.
 */
public class SizePanel extends JPanel {
	
	//components
	ActionButton[] sizeButton;
	JLabel infoLabel;
	int currentSize;
	
	public SizePanel() {
		
		//info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		sizeButton = new ActionButton[SIZES.length];
		
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
		
		for(int i = 0; i < SIZES.length; i++) {
			
			sizeButton[i] = new ActionButton(DIM_BUTTON, COL_BUTTON, SIZES[i]);
			
			gbc.gridx = i%3;
			gbc.gridy = 1 + i/3;
			add(sizeButton[i], gbc);
		}
		
		sizeButton[0].setPressed();
		
		setVisible(true);
	}
	
    public static void main( String[] args ) {
    	//TODO delete main method
		new SizePanel();
    }
    
    /** Returns the value of current size. */
    public int getCurrentSize() {
    	return currentSize;
    }
    
    /** Sets the value of current size. */
    public void setCurrentSize(int newSize) {
    	currentSize = newSize;
    }
    
    /** Sets no pressed mode on all buttons. */
    public void setNoPressedOthers() {
    	for(int i = 0; i < SIZES.length; i++)
    		sizeButton[i].setNoPressed();
    }
    
    /** Action Button for performing action on parent. */
    private class ActionButton extends JButton {
    	
    	int size;
    	Color colorPressed;
    	Color colorNotPressed;
    	
    	private ActionButton(Dimension dim, Color col, int newSize) {
    		
    		super();
    		this.size = newSize;
    		this.colorPressed = col.darker();
    		this.colorNotPressed = col.brighter();
    		setNoPressed();
    		setText(getName(newSize));
    		setPreferredSize(dim);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			setPressed();
      			}
    		});
    		
    	}
    	
    	/** Make the button pressed mode. */
    	public void setPressed() {
    		System.out.println("Now pressed!");
			setCurrentSize(size);
			setNoPressedOthers();
    		setBackground(colorPressed);
    	}
    	
    	/** Make the button not pressed mode. */
    	public void setNoPressed() {
    		setBackground(colorNotPressed);
    	}
    	
    	/**
    	 * Calculate the button text by the size.
    	 * @param size
    	 * @return name of the button
    	 */
    	private String getName(int size) {
    		return Integer.toString(size) + "x" + Integer.toString(size);
    	}
    	
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
