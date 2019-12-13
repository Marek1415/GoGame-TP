package board_components;

import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

import client_interfaces.PawnOperations;


/** Grid and buttons layer. */
public class Board extends JLayeredPane implements PawnOperations {
	
	//components
	private LayerGrid layerGrid;
	private LayerButtons layerButtons;
	
	public Board(int size) {
		super();
		
		//components
		layerGrid = new LayerGrid(size);
		layerButtons = new LayerButtons(this, size);
		
		setLayout(new OverlayLayout(this));
		add(layerGrid, 0);
		add(layerButtons, 1);
		
	}
	
	/** Repaints part of area. */
	public void repaintNow(Rectangle rec) {
		layerGrid.repaint(rec);
	}
	
	/** Sends signal, must be override by parent. */
	public void sendSignal(String signal) {
		//System.out.println("[SIGNAL] " + signal);
	}
	
	/** Adds a pawn with specific color on specific position.*/
	public void addPawn(int number, int color) {
		layerGrid.addPawn(number, color);
	}
	
	/** Remove a pawn from specific position.*/
	public void removePawn(int number) {
		layerGrid.removePawn(number);
	}
}