package client_panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import static constants.Signals.*;
import static constants_panels.ActionPanelConstants.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Panel for displaying board. */
public class ActionPanel extends JPanel{

	//components
	private ActionButton readyButton;
	private ActionButton checkButton;
	private ActionButton endButton;
	private TextArea infoArea;
	
	/** Constructor.*/
	public ActionPanel() {
		super();
		
		//ready Button
		readyButton = new ActionButton(STR_READY, DIM_BUTTON, COL_READY) {
			@Override
			public void action() {
				sendSignalNow(CL_READY);
			}
		};
		readyButton.setEnabled(false);
		
		//check Button
		checkButton = new ActionButton(STR_CHECK, DIM_BUTTON, COL_CHECK) {
			@Override
			public void action() {
				sendSignalWait(CL_CHECK);
			}
		};
		
		//end Button
		endButton = new ActionButton(STR_END, DIM_BUTTON, COL_END) {
			@Override
			public void action() {
				sendSignalNow(CL_END);
			}
		};
		
		//info area
		infoArea = new InfoArea();
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,15,15,0);
	
		//ready button
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(readyButton, gbc);
		
		//infoArea
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		add(infoArea, gbc);
		
		//checkButton
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		add(checkButton, gbc);
		
		//endButton
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(0,15,0,0);
		add(endButton, gbc);
	
		//pack();
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(COL_BACKGROUND);
		setVisible(true);
		
	}
	
	/** Gets the instance of this object.*/
	public ActionPanel getMe() {
		return this;
	}
	
	/** Sends the signal to the frame.*/
	public void sendSignalNow(String signal) {
		//System.out.println("[SIGNAL]  " + signal);
	}
	
	/** Sends the signal to the frame.*/
	public void sendSignalWait(String signal) {
		//System.out.println("[SIGNAL]  " + signal);
	}
	
	/** Adds message to the message panel.*/
	public void addMessage(String message) {
		System.out.println("[MESSAGE] " + message);
		infoArea.append("\n" + message);
	}
	
	/** Invoked when enemy join the game. */
	public void enemyJoin() {
		readyButton.setEnabled(true);
	}
	
	public static void main(String [] args) {
		new ActionPanel();
	}
	
	/** Button for performing action on parent.*/
	private abstract class ActionButton extends JButton {
    	
    	private ActionButton(String text, Dimension dim, Color col) {
    		
    		super(text);
    		setPreferredSize(dim);
    		setBackground(col);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		setBorderPainted(false);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			action();
      			}
    		});
    		
    	}
    	
    	/** Action method, must be override by parent. */
    	public abstract void action();
    }
	
	/** TextArea for displaying current game info. */
	private class InfoArea extends TextArea {
		
		private InfoArea() {
			super("", 10, 20, SCROLLBARS_VERTICAL_ONLY);
			setPreferredSize(DIM_INFOAREA);
			setEditable(false);
			setFont(FONT_INFO);
		}
	}
}
