package constants_panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import constants_modules.Constants;


public final class SizePanelConstants extends Constants{
	
	//strings
	public final static String STR_TITLE = "Rozmiar planszy";
	public final static String STR_INFO = "Wybierz rozmiar planszy.";
	
	//dimensions
	public final static Dimension DIM_BUTTON = new Dimension(200,200);
	public final static Dimension DIM_INFO = new Dimension(500,50);
	
	//font
	public final static Font FONT_BUTTON = new Font("Helvetica", Font.PLAIN, 30);
	
	//colors
	public final static Color COL_BUTTON = Color.BLUE;
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	private SizePanelConstants() {}
}
