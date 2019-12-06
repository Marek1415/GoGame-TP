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
import javax.swing.JPanel;

import client_interfaces.SignalSender;

import static constants.NewGameModConstants.*;
import static constants.Signals.*;
import static constants.BoardSizes.SIZES;

/**
 * @author gumises Player chooses game mode and board size.
 */
public class NewGameMod extends JDialog implements SignalSender {

	// components
	JPanel modePanel;
	JPanel sizePanel;
	// JButton okButton = new OkButton();

	public NewGameMod() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(STR_TITLE);
		setResizable(false);
	}

	public void init() {

		modePanel = new ModePanel();
		sizePanel = new SizePanel();
		
		// gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);

		// mode panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(modePanel, gbc);
		
		// size panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(sizePanel, gbc);

		pack();
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/** Sends the signal to the panel. */
	public void sendSignal(String signal) {
		System.out.println("[SIGNAL]  " + signal);
	}

	public static void main(String[] args) {
		// TODO delete main method
		NewGameMod newGameMod = new NewGameMod();
		newGameMod.init();
	}

	/** Displays game mode options. */
	private class ModePanel extends JPanel {

		// components
		AbstractButton playerButton;
		AbstractButton botButton;
		JLabel infoLabel;

		public ModePanel() {

			// info label
			infoLabel = new InfoLabel(STR_MODE_INFO, DIM_MODE_INFO);

			// end button
			playerButton = new ModeButton(STR_MODE_PLAYER, DIM_MODE_BUTTON, COL_MODE_BUTTON) {
				@Override
				public void action() {
					System.out.println("Player button pressed!");
				}
			};

			// resign button
			botButton = new ModeButton(STR_MODE_BOT, DIM_MODE_BUTTON, COL_MODE_BUTTON) {
				@Override
				public void action() {
					System.out.println("Bot button pressed!");
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
			add(playerButton, gbc);

			// resign
			gbc.gridx = 1;
			gbc.gridy = 1;
			add(botButton, gbc);

			setTitle(STR_TITLE);
			setResizable(false);

			pack();
			setVisible(true);
		}

	}

	/** Displays available board sizes. */
	private class SizePanel extends JPanel {

		//components
		AbstractButton sizeButton;
		JLabel infoLabel;

		public SizePanel() {
	  		
	  		//info label
	  		infoLabel = new InfoLabel(STR_SIZE_INFO, DIM_SIZE_INFO);
	  		
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
	  			
	  			sizeButton = new SizeButton(DIM_SIZE_BUTTON, COL_SIZE_BUTTON, SIZES[i]) {
	  				
	  				@Override
	  				public void action() {
	  					System.out.println("Size button pressed!");
	  				}
	  			};
	  			
	  			gbc.gridx = i%3;
	  			gbc.gridy = 1 + i/3;
	  			add(sizeButton, gbc);
	  		}
	  		
	  		pack();
	  		setVisible(true);
	  	}
	}

	/** Button to chose game mode. */
	public abstract class ModeButton extends JButton {

		private ModeButton(String name, Dimension dim, Color col) {

			super();
			setText(name);
			setPreferredSize(dim);
			setBackground(col);
			setForeground(COL_FOREGROUND);
			setFont(FONT_MODE_BUTTON);

			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					action();
				}
			});

		}

		/** Action method, must be override by parent. */
		public abstract void action();
	}

	private abstract class SizeButton extends JButton {

		// board size
		final int size;

		/** constructor*/
		private SizeButton(Dimension dim, Color col, int newSize) {

			super();
			setText(getName(newSize));
			this.size = newSize;
			setPreferredSize(dim);
			setBackground(col);
			setForeground(COL_FOREGROUND);
			setFont(FONT_SIZE_BUTTON);

			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					action();
				}
			});

		}

		/** action method, must be override by parent*/
		public abstract void action();

		/**
		 * Calculate the button text by the size.
		 * 
		 * @param size
		 * @return name of the button
		 */
		private String getName(int size) {
			return Integer.toString(size) + "x" + Integer.toString(size);
		}
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
