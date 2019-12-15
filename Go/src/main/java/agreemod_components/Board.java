package agreemod_components;

import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

import client_interfaces.EnemyOperations;


/** Grid and buttons layer. */
public class Board extends JLayeredPane implements EnemyOperations {
	
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
	
	/** Switch on enemy territory field.*/
	public void addEnemy(int number) {
		layerButtons.addEnemy(number);
	}
	
	/** Switch off enemy territory field.*/
	public void removeEnemy(int number) {
		layerButtons.removeEnemy(number);
	}
}