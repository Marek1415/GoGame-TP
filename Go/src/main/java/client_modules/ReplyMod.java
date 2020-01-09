package client_modules;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import replymod_components.Board;

import static constants_modules.ReplyModConstants.*;
import static constants.PawnColors.*;

import database.DatabaseConnector;
import database.Game;
import database.Move;

/**
 * @author gumises
 * Shows game reply step by step.
 */
public class ReplyMod extends JDialog{

	// action button
	ActionButton exitButton;
	ActionButton startButton;

	//board
	Board board;
	
	//database elements
	private Game game;
	private List<Move> moves;
	
	public ReplyMod() {

		//start button
		startButton = new ActionButton(STR_START, DIM_START, COL_START) {
			
			@Override
			public void action() {
				makeMoves();
			}
		};
		
		// exit button
		exitButton = new ActionButton(STR_EXIT, DIM_EXIT, COL_EXIT) {
			
			public void action() {
				dispose();
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
		
		// start
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		add(startButton, gbc);
		
		// exit
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(exitButton, gbc);

		pack();
		setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(STR_TITLE);
		setResizable(false);
	}

	/** Initialize dialog window.*/
	public void init(int id) {
		game = DatabaseConnector.getGame(id);
		moves = DatabaseConnector.getMoves(id);
		
		board.init(game.getSize());
		pack();
		setVisible(true);
	}
	
	/** Initialize dialog window.*/
	public void initMe(boolean mode) {
		setVisible(mode);
	}
	
	/** Returns instance of this class. */
	public ReplyMod getMe() {
		return this;
	}
	
	/** Adds a pawn with specific color on specific position.*/
	public void addPawn(int number, int color) {
		board.addPawn(number, color);
	}
	
	/** Remove a pawn from specific position.*/
	public void removePawn(int number) {
		board.removePawn(number);
	}
	
	/** Puts players moves on board. */
	public void makeMoves() {
		
		int position;
		char player;
		
		for(int i = 0; i < moves.size(); i++) {
			System.out.println("Start!");
			position = moves.get(i).getPosition();
			player = moves.get(i).getPlayer();
			if(player == 'W') {
				addPawn(position, Pawn.WHITE.Symbol());
				makeDelay(500);
			}
			else if(player == 'B'){
				addPawn(position, Pawn.BLACK.Symbol());
				makeDelay(500);
			}
			else if(player == 'D'){
				removePawn(position);
				makeDelay(100);
			}
			repaint();
			pack();
			System.out.println("Stop!");
		}
	}
	
	/** Falls asleep for a specified time. */
	public void makeDelay(int value) {
		
		try
		{
		    Thread.sleep(value);
		}
		catch(InterruptedException ex)
		{
			System.out.println("Some exception");
		    Thread.currentThread().interrupt();
		}
		
		/*
		for(int i = 0; i < 10000; i ++)
				System.out.print(".");
		*/
		
	}
	
    /** Action Button for performing action on parent.*/
	public abstract class ActionButton extends JButton {
    	
    	public ActionButton(String text, Dimension dim, Color col) {
    		
    		super(text);
    		setPreferredSize(dim);
    		setBackground(col);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT);
    		setBorderPainted(false);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			action();
      			}
    		});
    		
    	}
    	
    	/** Performs action, must be override by parent. */
    	public abstract void action();
    }
	
	public static void main(String [] args) {
		//TODO delete main method
		ReplyMod agreeMod = new ReplyMod();
		
		//init board
		int id = 17;
		agreeMod.init(id);
	}
}
