package constants;

import java.awt.Color;
import java.awt.Dimension;


public final class AgreeModConstants extends Constants{
	
	//strings
	public final static String STR_AGREE = "Koniec";
	public final static String STR_DISAGREE = "Graj dalej";
	public final static String STR_TITLE = "Propozycja zakończenia rozgrywki";
	public final static String STR_INFO = "Przeciwnik proponuje zakończenia rozgrywki.";
	
	//dimensions
	public final static Dimension DIM_AGREE = new Dimension(250,150);
	public final static Dimension DIM_DISAGREE = new Dimension(250,150);
	public final static Dimension DIM_INFO = new Dimension(500,90);
	
	//colors
	public final static Color COL_AGREE = Color.GREEN;
	public final static Color COL_DISAGREE = Color.GREEN.darker();
	public final static Color COL_FOREGROUND = Color.WHITE;
	
	private AgreeModConstants() {}
}
