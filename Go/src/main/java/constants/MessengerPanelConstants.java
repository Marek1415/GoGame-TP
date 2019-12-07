package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public final class MessengerPanelConstants extends Constants{
	
	//strings
	public final static String STR_EMPTY = "wiadomość:";
	public final static String STR_BUTTON = "wyślij";
	
	//dimensions
	public final static Dimension DIM_FIELD = new Dimension(200,50);
	public final static Dimension DIM_BUTTON = new Dimension(100,50);
	
	//fonts
	public final static Font FONT_FIELD = new Font("Helvetica", Font.PLAIN, 20);
	public final static Font FONT_BUTTON = new Font("Helvetica", Font.PLAIN, 20);
	
	//colors
	public final static Color COL_BACKGROUND = Color.WHITE;
	public final static Color COL_FOREGROUND = Color.WHITE;
	public final static Color COL_FIELD_EMPTY = Color.LIGHT_GRAY;
	public final static Color COL_BUTTON = Color.LIGHT_GRAY;
	public final static Color COL_FIELD = Color.DARK_GRAY;
	
	private MessengerPanelConstants() {}
}
