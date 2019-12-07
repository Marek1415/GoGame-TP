package constants_modules;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public final class NewGameModConstants extends Constants{
	
	//strings
	public final static String STR_TITLE = "Nowa gra";
	public final static String STR_BUTTON = "START";
	
	//dimensions
	public final static Dimension DIM_BUTTON = new Dimension(620,150);
	
	//font
	public final static Font FONT_BUTTON = new Font("Helvetica", Font.PLAIN, 30);
	
	//colors
	public final static Color COL_BUTTON = Color.GREEN;
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	private NewGameModConstants() {}
}
