package client.panels;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import static constants.PanelsConstants.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Panel for displaying board.
 */
public abstract class ActionPanel extends JPanel {

	//components
	private AbstractButton readyButton;
	private AbstractButton checkButton;
	private AbstractButton endButton;
	private JTextArea infoArea;
	
	/** Constructor.*/
	public ActionPanel() {
		//super();
		
		//ready Button
		readyButton = new ActionButton(STR_READY, DIM_BUTTON, COL_READY) {
			@Override
			public void action() {
				ready();
			}
		};
		
		//check Button
		checkButton = new ActionButton(STR_CHECK, DIM_BUTTON, COL_CHECK) {
			@Override
			public void action() {
				check();
			}
		};
		
		//end Button
		endButton = new ActionButton(STR_END, DIM_BUTTON, COL_END) {
			@Override
			public void action() {
				end();
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
	
		setVisible(true);
		
	}
	
	/** Runs after pressing ready button.*/
	public abstract void ready();
	
	/** Runs after pressing check button.*/
	public abstract void check();
	
	/** Runs after pressing end button.*/
	public abstract void end();
	
	/** Button for performing action on parent.*/
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
	
	/** TextArea for displaying current game info.*/
	@SuppressWarnings("serial")
	private class InfoArea extends JTextArea {
		
		private InfoArea() {
			super("New info area!");
			setPreferredSize(DIM_INFOAREA);
			setEditable(false);
			setFont(FONT);
		}
	}
}
