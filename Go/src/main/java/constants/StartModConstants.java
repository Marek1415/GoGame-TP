package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public final class StartModConstants extends Constants{
	
	//strings
	public final static String STR_BOT = "Graj z botem";
	public final static String STR_PLAYER = "Graj z innym graczem";
	public final static String STR_TITLE = "Nowa gra";
	public final static String STR_INFO = "Wybierz tryb rozgrywki.";
	
	//dimensions
	public final static Dimension DIM_PLAYER = new Dimension(250,150);
	public final static Dimension DIM_BOT = new Dimension(250,150);
	public final static Dimension DIM_INFO = new Dimension(500,90);
	public final static Dimension DIM_DIALOG = new Dimension(530,270);
	
	//colors
	public final static Color COL_PLAYER = Color.BLUE;
	public final static Color COL_BOT = Color.BLUE.darker();
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	private StartModConstants() {}
}
