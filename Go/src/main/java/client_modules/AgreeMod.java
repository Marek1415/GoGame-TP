package client_modules;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;

import agreemod_components.ActionButton;
import agreemod_components.Board;
import client_interfaces.EnemyOperations;
import client_interfaces.SignalSender;

import static constants.Signals.*;
import static constants_modules.AgreeModConstants.*;

/**
 * @author gumises
 * Agrees or disagree with enemy proposal of game end.
 */
public class AgreeMod extends JDialog implements SignalSender, EnemyOperations {

	// action buttons
	ActionButton agreeButton;
	ActionButton disagreeButton;

	//board
	Board board;
	
	public AgreeMod() {


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
		
		//board
		board = new Board();

		// gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);

		//board
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		//gbc.insets = new Insets(5,5,5,5);
		add(board, gbc);
		
		// agree
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		//gbc.insets = new Insets(0,0,0,0);
		add(agreeButton, gbc);

		// disagree
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(disagreeButton, gbc);

		pack();
		setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(STR_TITLE);
		//setResizable(false);
	}

	/** Initialize dialog window.*/
	public void init(int size, int [][] pawns) {
		board.init(size, pawns);
		pack();
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
	
	/** Switch on enemy territory field.*/
	public void addEnemy(int number) {
		board.addEnemy(number);
	}
	
	/** Switch off enemy territory field.*/
	public void removeEnemy(int number) {
		board.removeEnemy(number);
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		AgreeMod agreeMod = new AgreeMod();
		
		//init board
		int size = 5;
		int [][] pawns = new int[size][size];
		pawns[0][0] = -1;
		pawns[0][1] = 1;
		pawns[3][3] = 1;
		agreeMod.init(size, pawns);
		agreeMod.addEnemy(0);
		agreeMod.addEnemy(1);
		agreeMod.addEnemy(5);
		agreeMod.addEnemy(7);
		agreeMod.removeEnemy(0);

	}
	
}
