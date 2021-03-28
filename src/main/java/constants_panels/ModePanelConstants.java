package constants_panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import constants_modules.Constants;


public final class ModePanelConstants extends Constants{
	
	//strings
	public final static String STR_BOT = "Graj z botem";
	public final static String STR_PLAYER = "Graj z innym graczem";
	public final static String STR_TITLE = "Nowa gra";
	public final static String STR_INFO = "Wybierz tryb rozgrywki.";
	
	//dimensions
	public final static Dimension DIM_PLAYER = new Dimension(300,150);
	public final static Dimension DIM_BOT = new Dimension(300,150);
	public final static Dimension DIM_INFO = new Dimension(500,50);
	
	//font
	public final static Font FONT_BUTTON = new Font("Helvetica", Font.PLAIN, 22);
	
	//colors
	public final static Color COL_BUTTON = Color.BLUE;
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	private ModePanelConstants() {}
}