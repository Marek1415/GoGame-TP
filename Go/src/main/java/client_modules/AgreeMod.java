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

import client_interfaces.SignalSender;

import static constants.Signals.*;
import static constants_modules.AgreeModConstants.*;

/**
 * @author gumises
 * Agrees or disagree with enemy proposal of game end.
 */
public class AgreeMod extends JDialog implements SignalSender {

	// action buttons
	AbstractButton agreeButton;
	AbstractButton disagreeButton;

	// labels
	JLabel infoLabel;

	public AgreeMod() {

		// info label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);

		// end button
		agreeButton = new ActionButton(STR_AGREE, DIM_AGREE, COL_AGREE) {
			@Override
			public void action() {
				sendSignal(CL_AGREE);
				initMe(false);
			}
		};

		// resign button
		disagreeButton = new ActionButton(STR_DISAGREE, DIM_DISAGREE, COL_DISAGREE) {
			@Override
			public void action() {
				sendSignal(CL_DISAGREE);
				initMe(false);
			}
		};

		// gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);

		// info
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(infoLabel, gbc);

		// end
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(agreeButton, gbc);

		// resign
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(disagreeButton, gbc);

		pack();
		setVisible(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(STR_TITLE);
		setResizable(false);
	}

	/** Initialize dialog window.*/
	public void init() {
		setVisible(true);
	}
	
	/** Initialize dialog window.*/
	public void initMe(boolean mode) {
		setVisible(mode);
	}

	/** Sends the signal to the panel. */
	public void sendSignal(String signal) {
		System.out.println("[SIGNAL]  " + signal);
	}

	/** Action Button for performing action on parent. */
	private abstract class ActionButton extends JButton {

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

		/** action method, must be override by parent. */
		public abstract void action();
	}

	/** Label for displaying info about dialog. */
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
