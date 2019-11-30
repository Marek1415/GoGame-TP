package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public final class SizeModConstants extends Constants{
	
	//strings
	public final static String STR_TITLE = "Rozmiar planszy";
	public final static String STR_INFO = "Wybierz rozmiar planszy.";
	
	//dimensions
	public final static Dimension DIM_BUTTON = new Dimension(200,200);
	public final static Dimension DIM_INFO = new Dimension(500,90);
	
	//font
	public final static Font FONT_BUTTON = new Font("Helvetica", Font.PLAIN, 25);
	
	//colors
	public final static Color COL_BUTTON = Color.BLUE.darker();
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	private SizeModConstants() {}
}
