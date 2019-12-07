package client_panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client_interfaces.SignalSender;

import static constants.MessengerPanelConstants.*;
import static constants.Signals.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/** Panel for displaying messenger. */
public class MessengerPanel extends JPanel 
implements SignalSender{

	//components
	private MessengerField messengerField;
	private MessengerButton messengerButton;
	
	public MessengerPanel() {
		super();
		
		//components
		messengerField = new MessengerField(STR_EMPTY);
		messengerButton = new MessengerButton(STR_BUTTON);
		
		messengerField.makeEmpty();
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.weightx = 0;
		gbc.weighty = 0;
		//gbc.fill = GridBagConstraints.BOTH;
		
		//messenger field
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		add(messengerField, gbc);
		
		//messenger button
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.insets = new Insets(5, 0, 5, 5);
		add(messengerButton, gbc);
		
		//pack();
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(COL_BACKGROUND);
		setVisible(true);
	}
	
	/** Sends the signal to the panel.*/
	public void sendSignal(String signal) {
		System.out.println("[SIGNAL]  " + signal);
	}
	
	/** Prepares signal for sending.*/
	public void prepareSignal() {
		String text = messengerField.getText();
		messengerField.makeEmpty();
		if(!text.equals("") && !text.equals(STR_EMPTY))
			sendSignal(CL_MESSEND + " " + text);
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		new MessengerPanel();
	}
	
	private class MessengerField extends JTextField {
		
		private String empty;
		
		public MessengerField(String newEmpty ) {
			super();
			this.empty = newEmpty;
			setPreferredSize(DIM_FIELD);
			setFont(FONT_FIELD);
			setBackground(COL_FIELD);
			setForeground(COL_FIELD_EMPTY);
			setBorder(null);
    		setBorder(BorderFactory.createCompoundBorder(
    		        getBorder(), 
    		        BorderFactory.createEmptyBorder(5, 15, 5, 15)));
			
			addFocusListener(new FocusListener() {
				
				public void focusGained(FocusEvent e) {
					setText("");
					setForeground(COL_FOREGROUND);
				}
				
				public void focusLost(FocusEvent e) {
				}
			});
			
		}
		
		public void makeEmpty() {
			setText(empty);
			setForeground(COL_FIELD_EMPTY);
		}
	}
	
	private class MessengerButton extends JButton {
    	
    	private MessengerButton(String text) {
    		
    		super(text);
    		setPreferredSize(DIM_BUTTON);
    		setBackground(COL_BUTTON);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		setBorderPainted(false);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			prepareSignal();
      			}
    		});
    		
    	}
    }
}
