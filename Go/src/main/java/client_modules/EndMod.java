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

import client_interfaces.SignalSender;

import static constants.Signals.*;
import static constants_modules.EndModConstants.*;
import static constants.Statuses.STATUS_WIN;
import static constants.Statuses.STATUS_LOST;


/**
 * @author gumises
 * Displays option for game ending.
 */
public class EndMod extends JDialog implements SignalSender {

	//action buttons
	ActionButton endButton;
	
	//labels
	PointsLabel pointsLabel;
	PointsNumber pointsNumber;
	
	//panel
	JPanel panel;
	
	/** Initialize the dialog window.*/
	public EndMod() {
		
		//panel
		panel = new JPanel();
		
		//points label
		pointsLabel = new PointsLabel();
		
		//points number
		pointsNumber = new PointsNumber();
		
		//end button
		endButton = new ActionButton() {
			@Override
			public void action() {
				sendSignal(CL_END);
				initMe(false);
			}
		};
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(layout);
		
		//gbc init
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		//points label
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(10,10,10,10);
		panel.add(pointsLabel, gbc);
		
		//points number
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(pointsNumber, gbc);
		
		//end button
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(endButton, gbc);
		
		add(panel);
		
		setResizable(false);
		
		pack();
		setVisible(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	/** Sends the signal to the panel.*/
	public void sendSignal(String signal) {
		//System.out.println("[SIGNAL]  " + signal);
	}
	
	/** Makes the dialog visible. */
	public void init(String status, String points) {
		endButton.setStatus(status);
		pointsNumber.setPoints(points);
		setStatus(status);
		setVisible(true);
	}
	
	/** Sets panel status. */
	public void setStatus(String status) {
		if(status == STATUS_WIN) {
			panel.setBackground(COL_WIN);
			setTitle(STR_WIN);
		}
		else {
			panel.setBackground(COL_LOST);
			setTitle(STR_LOST);
		}
	}
	
	/** Makes the dialog visible or invisible. */
	public void initMe(boolean isInit) {
		setVisible(isInit);
	}
	
    public static void main( String[] args ) {
    	//TODO delete main method
		EndMod endMod = new EndMod();
		endMod.init(STATUS_WIN, "2");
    }
    
    /**Performs action on parent. */
	private abstract class ActionButton extends JButton {
    	
		private final Color colorWin;
		private final Color colorLost;
		
    	private ActionButton() {
    		
    		super(STR_ENDBUTTON);
    		this.colorWin = COL_WIN.darker();
    		this.colorLost = COL_LOST.darker();
    		setPreferredSize(DIM_ENDBUTTON);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		setBorderPainted(false);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			action();
      			}
    		});
    	}
    	
    	/** Sets button status. */
    	public void setStatus(String status) {
    		if(status == STATUS_WIN)
    			setBackground(colorWin);
    		else
    			setBackground(colorLost);
    	}
    	
    	/** action method, must be override by parent */
    	public abstract void action();
    }
    
    /** Displays points label. */
    private class PointsLabel extends JLabel {
    	
    	private PointsLabel() {
    		super(STR_POINTSLABEL);
    		setFont(FONT_POINTSLABEL);
    		setPreferredSize(DIM_POINTSLABEL);
    		setHorizontalAlignment(JLabel.RIGHT);
    	    setVerticalAlignment(JLabel.CENTER);
    	    setForeground(COL_FOREGROUND);
    	    
    		//setOpaque(true);
    		//setBackground(Color.YELLOW);
    	}
    }
    
    /** Displays points number. */
    private class PointsNumber extends JLabel {
    	
    	private PointsNumber() {
    		super();
    		setFont(FONT_POINTSNUMBER);
    		setPreferredSize(DIM_POINTSNUMBER);
    		setHorizontalAlignment(JLabel.LEFT);
    	    setVerticalAlignment(JLabel.CENTER);
    	    setForeground(COL_FOREGROUND);
    	    	
    		//setOpaque(true);
    		//setBackground(Color.YELLOW);
    	}
    	
    	/** Sets points number. */
    	private void setPoints(String points) {
    		this.setText(points);
    	}
    }
}
