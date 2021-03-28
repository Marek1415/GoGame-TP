package replymod_components;

import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;


/** Grid and buttons layer. */
public class Board extends JLayeredPane {
	
	//components
	private LayerGrid layerGrid;
	
	public Board() {
		super();
	}
	
	/** Initializes board panel with specific pawn positions. */
	public void init(int size) {
		
		//components
		layerGrid = new LayerGrid(size);
			
		setLayout(new OverlayLayout(this));
		add(layerGrid, 0);
	}
	
	/** Adds a pawn with specific color on specific position.*/
	public void addPawn(int number, int color) {
		layerGrid.addPawn(number, color);
	}
	
	/** Remove a pawn from specific position.*/
	public void removePawn(int number) {
		layerGrid.removePawn(number);
	}
	
	/** Returns instance of this class. */
	public Board getBoard() {
		return this;
	}
}