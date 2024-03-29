package constants_panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import constants_modules.Constants;


public final class PointsPanelConstants extends Constants{
	
	//strings
	public final static String STR_POINTS = "liczba punktów: ";
	public final static String STR_TURN_ON = "twoja kolej";
	public final static String STR_TURN_OFF = "kolej przeciwnika";
	public final static String STR_TURN_INIT = "czekaj na przeciwnika";
	public final static String STR_DISCONNECTED = "rozłączono przeciwnika";
	
	//dimensions
	public final static int DOT_LENGTH = 25;
	public final static Dimension DIM_DOT = new Dimension(DOT_LENGTH,DOT_LENGTH);
	public final static Dimension DIM_POINTS = new Dimension(80,40);
	public final static Dimension DIM_TURNLABEL = new Dimension(220,40);
	
	//font
	public final static Font FONT_LABEL = new Font("Helvetica", Font.PLAIN, 18);
	public final static Font FONT_POINTS_NOUMBER = new Font("Helvetica", Font.PLAIN, 20);
	
	//colors
	public final static Color COL_ON = Color.GREEN.darker();
	public final static Color COL_OFF = Color.RED.brighter();
	public final static Color COL_DC = Color.LIGHT_GRAY;
	public final static Color COL_POINTS_LABEL = Color.LIGHT_GRAY;
	public final static Color COL_POINTS_NUMBER = Color.WHITE;
	public final static Color COL_BACKGROUND = Color.DARK_GRAY;
	
	private PointsPanelConstants() {}
}
