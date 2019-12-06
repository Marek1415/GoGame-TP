package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public final class NewGameModConstants extends Constants{
	
	//strings
	public final static String STR_TITLE = "Nowa gra.";
	public final static String STR_SIZE_INFO = "Wybierz rozmiar planszy.";
	public final static String STR_MODE_INFO = "Wybierz tryb rozgrywki.";
	public final static String STR_MODE_PLAYER = "Graj z innym graczem";
	public final static String STR_MODE_BOT = "Graj z botem";
	
	//dimensions
	public final static Dimension DIM_MODE_BUTTON = new Dimension(200,200);
	public final static Dimension DIM_SIZE_BUTTON = new Dimension(200,200);
	public final static Dimension DIM_MODE_INFO = new Dimension(500,90);
	public final static Dimension DIM_SIZE_INFO = new Dimension(500,90);
	
	//font
	public final static Font FONT_MODE_BUTTON = new Font("Helvetica", Font.PLAIN, 18);
	public final static Font FONT_SIZE_BUTTON = new Font("Helvetica", Font.PLAIN, 25);
	
	//colors
	public final static Color COL_MODE_BUTTON = Color.BLUE;
	public final static Color COL_SIZE_BUTTON = Color.GREEN;
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	private NewGameModConstants() {}
}
