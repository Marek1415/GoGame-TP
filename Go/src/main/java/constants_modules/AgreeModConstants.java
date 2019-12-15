package constants_modules;

import java.awt.Color;
import java.awt.Dimension;


public final class AgreeModConstants extends Constants{
	
	//strings
	public final static String STR_TITLE = "Wybierz swoje terytorium";
	public final static String STR_AGREE = "Zakończ";
	public final static String STR_DISAGREE = "Anuluj";
	
	//colors
	public final static Color COL_FOREGROUND = Color.WHITE;
	public final static Color COL_AGREE = Color.GREEN;
	public final static Color COL_DISAGREE = Color.RED;
	
	//dimensions
	public final static Dimension DIM_AGREE = new Dimension(250,75);
	public final static Dimension DIM_DISAGREE = new Dimension(250,75);

	//private constructor
	private AgreeModConstants() {}
}
