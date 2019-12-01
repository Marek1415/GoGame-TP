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
	public final static Dimension DIM_BOARD = new Dimension(600,600);
	public final static Dimension DIM_MESSENGER = new Dimension(500,100);
	//public final static Dimension DIM_ACTION = new Dimension(200,700);
	public final static Dimension DIM_BUTTON = new Dimension(225,120);
	public final static Dimension DIM_INFOAREA = new Dimension(225,300);
	
	//colors
	public final static Color COL_FOREGROUND = Color.WHITE;
	public final static Color COL_READY = Color.orange;
	public final static Color COL_CHECK = Color.orange;
	public final static Color COL_END = Color.orange;
	
	//color board
	public final static Color COL_BOARDINIT = Color.GRAY;
	
	//fonts
	public final static Font FONT = new Font("Helvetica", Font.PLAIN, 18);
	
	private PanelsConstants() {}
}
