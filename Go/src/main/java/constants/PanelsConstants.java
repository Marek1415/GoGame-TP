package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/*
 * COnstants for Client GUI panels.
 */
public class PanelsConstants {
	
	//strings
	public final static String STR_READY = "Gotowy";
	public final static String STR_CHECK = "Czekaj";
	public final static String STR_END = "Koniec";

	//dimensions
	public final static Dimension DIM_MESSENGER = new Dimension(500,100);
	//public final static Dimension DIM_ACTION = new Dimension(200,700);
	public final static Dimension DIM_BUTTON = new Dimension(225,120);
	public final static Dimension DIM_INFOAREA = new Dimension(225,300);
	
	//dimensions board
	public final static int THICKNESS = 5;
	public final static int LENGTH_AXIS = 30;
	public final static int LENGTH_BOARD = 600;
	public final static Dimension DIM_BOARD = new Dimension(LENGTH_BOARD, LENGTH_BOARD);
	public final static Dimension DIM_BOARDINIT = new Dimension(LENGTH_BOARD + LENGTH_AXIS, LENGTH_BOARD + LENGTH_AXIS);
	//public final static Dimension DIM_AXIS = new Dimension(WIDTH_AXIS,WIDTH_AXIS);
	public final static Dimension DIM_XAXIS = new Dimension(LENGTH_BOARD, LENGTH_AXIS);
	public final static Dimension DIM_YAXIS = new Dimension(LENGTH_AXIS, LENGTH_BOARD);
	
	//colors
	public final static Color COL_FOREGROUND = Color.WHITE;
	public final static Color COL_READY = Color.orange;
	public final static Color COL_CHECK = Color.orange;
	public final static Color COL_END = Color.orange;
	
	//colors board
	public final static Color COL_BOARDINIT = Color.WHITE;
	public final static Color COL_AXIS = Color.BLACK;
	public final static Color COL_AXIS_BACKGROUND = Color.white;
	public final static Color COL_GRID = Color.DARK_GRAY;
	
	//fonts
	public final static Font FONT = new Font("Helvetica", Font.PLAIN, 15);
	public final static Font FONT_AXIS = new Font("Helvetica", Font.BOLD, 12);
	
	private PanelsConstants() {}
}
