package constants;

/**
 * @author gumises
 * Available colors of pawn.
 */
public class PawnColors {
	/*public final static int PAWN_WHITE = -1;
	public final static int PAWN_BLACK = 1;
	public final static int NONE = 0;*/
	public enum Pawn{
		BLACK(-1),
		WHITE(1),
		NONE(0);
		
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
