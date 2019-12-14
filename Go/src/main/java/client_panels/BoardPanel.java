package client_panels;

import javax.swing.JFrame;
import javax.swing.JPanel;

import board_components.Board;
import board_components.XAxis;
import board_components.YAxis;

import static constants_panels.BoardPanelConstants.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import client_interfaces.*;

import constants.PawnColors;


/**
 * Facade for connecting BoardPanel with board components and their methods.
 * @author gumises
 */
public class BoardPanel extends JPanel implements PawnOperations, SignalSender{

	//components
	private Board board;
	private XAxis xAxis;
	private YAxis yAxis;
	
	public BoardPanel() {
		super();
	}
	
	/** Initializes the board panel.*/
	public void init(int size) {
		
		//components
		xAxis = new XAxis(size);
		yAxis = new YAxis(size);
		board = new Board(size) {
			
			@Override
			public void sendSignal(String signal) {
				getMe().sendSignal(signal);
			}
		};
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.insets = new Insets(0,0,0,0);
		
		//x axis
		gbc.gridx = 1;
		gbc.gridy = 0;
		//gbc.weightx = size;
		//gbc.weighty = 1;
		//gbc.weighty = 0.5;
		add(xAxis, gbc);
		
		//y axis
		gbc.gridx = 0;
		gbc.gridy = 1;
		//gbc.weightx = 1.5;
		//gbc.weightx = 0.5;
		//gbc.weighty = size;
		add(yAxis, gbc);
		
		//board
		gbc.gridx = 1;
		gbc.gridy = 1;
		//gbc.weightx = size;
		//gbc.weighty = size;
		add(board, gbc);
		
		setVisible(true);
		setBackground(BACKGROUND);
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		//pack();
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		BoardPanel boardPanel = new BoardPanel();
		int size = 13;
		boardPanel.init(size);
		for(int i = 0; i < size-2; i++)
		for(int j = 0; j < size-3; j++)
			boardPanel.addPawn(i*size+j, PawnColors.Pawn.WHITE.Symbol());
	}
	
	/**Sends signal to the parent, must be override by parent. */
	public void sendSignal(String signal) {
		System.out.println(signal);
	}
	
	/** Adds a pawn with specific color on specific position.*/
	public void addPawn(int number, int color) {
		board.addPawn(number, color);
	}
	
	/** Remove a pawn from specific position.*/
	public void removePawn(int number) {
		board.removePawn(number);
	}
	
	/** Gets instance of this class. */
	public BoardPanel getMe() {
		return this;
	}
}
