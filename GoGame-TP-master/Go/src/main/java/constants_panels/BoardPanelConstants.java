package constants_panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/*
 * COnstants for Client GUI panels.
 */
public class BoardPanelConstants {
	

	//dimensions
	public final static int THICKNESS = 5;
	public final static int LENGTH_AXIS = 30;
	public final static int LENGTH_BOARD = 600;
	public final static Dimension DIM_BOARD = new Dimension(LENGTH_BOARD, LENGTH_BOARD);
	public final static Dimension DIM_BOARDINIT = new Dimension(LENGTH_BOARD + LENGTH_AXIS, LENGTH_BOARD + LENGTH_AXIS);
	//public final static Dimension DIM_AXIS = new Dimension(WIDTH_AXIS,WIDTH_AXIS);
	public final static Dimension DIM_XAXIS = new Dimension(LENGTH_BOARD, LENGTH_AXIS);
	public final static Dimension DIM_YAXIS = new Dimension(LENGTH_AXIS, LENGTH_BOARD);
	
	//colors
	public final static Color COL_BOARDINIT = Color.WHITE;
	public final static Color COL_AXIS = Color.BLACK;
	public final static Color COL_AXIS_BACKGROUND = Color.white;
	public final static Color COL_GRID = Color.DARK_GRAY;
	
	//fonts
	public final static Font FONT_AXIS = new Font("Helvetica", Font.BOLD, 12);

	private BoardPanelConstants() {}
}
