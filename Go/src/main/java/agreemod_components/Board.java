package agreemod_components;

import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

import board_components.LayerButtons;

import static constants.Signals.*;

/** Grid and buttons layer. */
public class Board extends JLayeredPane {
	
	//components
	private LayerGrid layerGrid;
	private LayerButtons layerButtons;
	
	public Board() {
		super();
	}
	
	/** Initializes board panel with specific pawn positions. */
	public void init(int size, int[][] pawns) {
		
		//components
		layerGrid = new LayerGrid(size, pawns);
		layerButtons = new LayerButtons(size) {
			
			@Override
			public void repaintNow(Rectangle rec) {
				getBoard().repaintNow(rec);
			}
			
			@Override
			public void sendSignal(int signal) {
				getBoard().sendSignal(CL_MYADD + " " + signal);
			}
		};
		
		setLayout(new OverlayLayout(this));
		add(layerGrid, 0);
		add(layerButtons, 1);
	}
	
	/** Repaints part of area. */
	public void repaintNow(Rectangle rec) {
		layerGrid.repaint(rec);
	}
	
	/** Returns instance of this class. */
	public Board getBoard() {
		return this;
	}
	
	/** Sends signal, must be override by parent. */
	public void sendSignal(String signal) {
		//System.out.println("[SIGNAL] " + signal);
	}
	
	/** Switch on piece of territory in specific status.*/
	public void addTerritory(int position, int status) {
		layerGrid.addTerritory(position, status);
		layerGrid.repaint();
	}
}