package constants_modules;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public final class EndModConstants extends Constants{
	
	//strings
	public final static String STR_WIN = "Wygrałeś!";
	public final static String STR_LOST = "Przegrałeś!";
	public final static String STR_POINTSLABEL = "punkty:";
	public final static String STR_ENDBUTTON = "Zakończ";
	
	//colors
	public final static Color COL_WIN = Color.GREEN;
	public final static Color COL_LOST = Color.RED;
	public final static Color COL_ENDBUTTON = Color.RED;
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	//dimensions
	public final static Dimension DIM_POINTSLABEL = new Dimension(105,130);
	public final static Dimension DIM_POINTSNUMBER = new Dimension(55,130);
	public final static Dimension DIM_ENDBUTTON = new Dimension(400,100);
	
	//fonts
	public final static Font FONT_INFO = new Font("Helvetica", Font.PLAIN, 20);
	public final static Font FONT_POINTSLABEL = new Font("Helvetica", Font.PLAIN, 20);
	public final static Font FONT_POINTSNUMBER = new Font("Helvetica", Font.PLAIN, 23);
	
	private EndModConstants() {}
}
