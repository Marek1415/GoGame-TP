package constants;

/** Statuses of put operation inside Game engine. */
public class Statuses {

	public final static int STATUS_PUT = 0;
	public final static int STATUS_KO = 1;
	public final static int STATUS_KOINIT = -1;
	public final static int STATUS_KILL = 2;
	public final static int STATUS_SUICIDE = 3;
	public final static int STATUS_NOEMPTY = 4;
	public final static int STATUS_OVERRANGE = -100;
	
	public final static String STATUS_WIN = "STATUS_WIN";
	public final static String STATUS_LOST = "STATUS_LOST";
	
	private Statuses() {}
}
