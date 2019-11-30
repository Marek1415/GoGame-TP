package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public final class EndModConstants {
	
	//strings
	public final static String STR_END = "Koniec";
	public final static String STR_RESIGN = "Poddana";
	public final static String STR_CANCEL = "Anuluj";
	public final static String STR_TITLE = "Koniec rozgrywki";
	public final static String STR_INFO = "Zaproponuj przeciwnikowi koniec lub poddaj się.";
	
	//dimensions
	public final static Dimension DIM_END = new Dimension(250,150);
	public final static Dimension DIM_RESIGN = new Dimension(250,150);
	public final static Dimension DIM_CANCEL = new Dimension(250,150);
	public final static Dimension DIM_INFO = new Dimension(600,100);
	
	//font
	public final static Font FONT = new Font("Helvetica", Font.PLAIN, 18);
	
	//colors
	public final static Color COL_END = Color.GREEN;
	public final static Color COL_RESIGN = Color.RED;
	public final static Color COL_CANCEL = Color.DARK_GRAY;
	public final static Color COL_FOREGROUND = Color.WHITE;
}
