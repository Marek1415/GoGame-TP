package constants_modules;

import java.awt.Color;
import java.awt.Dimension;


public final class StartModConstants extends Constants{
	
	//strings
	public final static String STR_TITLE = "Nowa gra";
	public final static String STR_INFO = "Dolaczcz do istniejacej gry lub stworz nowa";
	public final static String STR_NEW = "Nowa gra";
	public final static String STR_JOIN = "Dolacz do gry";
	public final static String STR_REPLY = "Powtorka";
	
	//dimensions
	public final static Dimension DIM_INFO = new Dimension(500,90);
	public final static Dimension DIM_NEW = new Dimension(250,150);
	public final static Dimension DIM_JOIN = new Dimension(250,150);
	public final static Dimension DIM_REPLY = new Dimension(250,150);
	
	//colors
	public final static Color COL_NEW= Color.BLUE;
	public final static Color COL_JOIN = Color.BLUE.darker();
	public final static Color COL_REPLY = Color.DARK_GRAY;
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	private StartModConstants() {}
}
