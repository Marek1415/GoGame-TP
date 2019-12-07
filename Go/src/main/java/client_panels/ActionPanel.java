package client_panels;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client_interfaces.SignalSender;
import client_modules.EndMod;

import static constants.PanelsConstants.*;
import static constants.Signals.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Panel for displaying board.
 */
public class ActionPanel extends JPanel implements SignalSender {

	//components
	private AbstractButton readyButton;
	private AbstractButton checkButton;
	private AbstractButton endButton;
	private TextArea infoArea;
	
	//modules
	private EndMod endModule;
	
	/** Constructor.*/
	public ActionPanel() {
		super();
		
		//end module
		endModule = new EndMod() {
			@Override
			public void sendSignal(String signal) {
				getMe().sendSignal(signal);
			}
		};
		
		//ready Button
		readyButton = new ActionButton(STR_READY, DIM_BUTTON, COL_READY) {
			@Override
			public void action() {
				sendSignal(CL_READY);
			}
		};
		
		//check Button
		checkButton = new ActionButton(STR_CHECK, DIM_BUTTON, COL_CHECK) {
			@Override
			public void action() {
				sendSignal(CL_CHECK);
			}
		};
		
		//end Button
		endButton = new ActionButton(STR_END, DIM_BUTTON, COL_END) {
			@Override
			public void action() {
				endModule.init();
			}
		};
		
		//info area
		infoArea = new InfoArea();
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1;
		gbc.weighty = 1;
		//gbc.fill = GridBagConstraints.BOTH;
		
		//ready button
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(readyButton, gbc);
		
		//infoArea
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(infoArea, gbc);
		
		//checkButton
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(checkButton, gbc);
		
		//endButton
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(endButton, gbc);
	
		//pack();
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setVisible(true);
		
	}
	
	/** Gets the instance of this object.*/
	public ActionPanel getMe() {
		return this;
	}
	
	/** Sends the signal to the frame.*/
	public void sendSignal(String signal) {
		//System.out.println("[SIGNAL]  " + signal);
	}
	
	/** Adds message to the message panel.*/
	public void addMessage(String message) {
		System.out.println("[MESSAGE] " + message);
		infoArea.append("\n" + message);
	}
	
	public static void main(String [] args) {
		new ActionPanel();
	}
	
	/** Button for performing action on parent.*/
	private abstract class ActionButton extends JButton {
    	
    	/*
    	 * constructor
    	 */
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
    	
    	/*
    	 * action method, must be override by parent
    	 */
    	public abstract void action();
    }
	
	/** TextArea for displaying current game info.*/
	private class InfoArea extends TextArea {
		
		private InfoArea() {
			super("", 10, 20, SCROLLBARS_VERTICAL_ONLY);
			setPreferredSize(DIM_INFOAREA);
			setEditable(false);
			setFont(FONT);
		}
	}
}
