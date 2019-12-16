package constants;

/** Statuses of territory fields. */
public class Territories {
	
	//inside client
	public final static int EMPTY = 0;
	public final static int ME = 1;
	public final static int ENEMY = -1;
	public final static int CONFLICT = 6;
	
	//inside server
	public final static int WHITE_ADD = 1;
	//public final static int WHITE_REMOVE = -1;
	public final static int BLACK_ADD = 3;
	//public final static int BLACK_REMOVE = -3;
	public final static int ISCONFLICT = 4;
	
	public static final String OK = "OK";
	public static final String NO = "NO";
}
