package constants;

/**
 * @author gumises
 * Available colors of pawn.
 */
public class PawnColors {
	public final static int WHITE = -1;
	public final static int BLACK = 1;
	public final static int EMPTY = 0;
	public final static int BORDER = 9;
	
	
	
	public enum Pawn{
		BLACK(-1),
		WHITE(1),
		EMPTY(0),
		BORDER(9);
		
		int number;
		private Pawn(int number) 
		{
			this.number = number;
		}
		public int Symbol()
		{
			return this.number;
		}
	}
	
}
