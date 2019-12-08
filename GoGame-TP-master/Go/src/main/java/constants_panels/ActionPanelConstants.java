package constants_panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/*
 * COnstants for Client GUI panels.
 */
public class ActionPanelConstants {
	
	//strings
	public final static String STR_READY = "Gotowy";
	public final static String STR_CHECK = "Czekaj";
	public final static String STR_END = "Koniec";

	//dimensions
	public final static Dimension DIM_BUTTON = new Dimension(250,100);
	public final static Dimension DIM_INFOAREA = new Dimension(300,300);
	
	//colors
	public final static Color COL_FOREGROUND = Color.WHITE;
	public final static Color COL_BACKGROUND = Color.WHITE;
	public final static Color COL_READY = Color.BLUE;
	public final static Color COL_CHECK = Color.BLUE;
	public final static Color COL_END = Color.BLUE.darker();
	
	//fonts
	public final static Font FONT_INFO = new Font("Helvetica", Font.PLAIN, 15);
	public final static Font FONT_BUTTON = new Font("Helvetica", Font.PLAIN, 20);
	
	private ActionPanelConstants() {}
}
