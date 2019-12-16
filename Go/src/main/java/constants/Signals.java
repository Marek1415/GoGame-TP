package constants;


/**
 * @author gumises
 * Protocol for asynchronous communication between client and server.
 * CL_* - send by the client
 * SE_* - send by the server
 */
public final class Signals {
	
	//start game
	//public final static String CL_PLAYBOT = "CL_PLAYBOT";	//depreciated
	//public final static String CL_PLAYUSR = "CL_PLAYBOT";	//depreciated
	//public final static String SE_ROOMSGET = "SE_ROOMSGET";
	//public final static String CL_ROOMSET = "CL_ROOMSET";
	public final static String CL_ROOMNEW = "CL_ROOMNEW";
	public final static String CL_ROOMJOIN = "CL_ROOMJOIN";
	public final static String CL_SIZESET = "CL_SIZESET";
	public final static String SE_ENEMYNEW = "SE_ENEMYNEW";
	public final static String CL_READY = "CL_READY";
	public final static String START = "START";
	public final static String SE_COLORSET = "SE_COLORSET";
	
	public final static String COLOR_BLACK = "COLOR_BLACK";
	public final static String COLOR_WHITE = "COLOR_WHITE";
	public final static String DISCONNECT = "DISCONNECT";
	//signals during the game
	public final static String CL_PUT = "CL_PUT";
	public final static String SE_PUTOK = "SE_PUTOK";
	public final static String SE_PUTNO = "SE_PUTNO";
	public final static String CL_TIMEUP = "CL_TIMEUP";
	public final static String CL_CHECK = "CL_CHECK";
	public final static String CL_MESSEND = "CL_MESSEND";
	public final static String SE_MESSREC = "SE_MESSREC";
	public final static String REMOVE = "REMOVE";
	
	//end game
	public final static String CL_END = "CL_END";
	public final static String CL_RESIGN = "CL_RESIGN";
	//public final static String SE_ENDGET = "SE_ENDGET";
	//public final static String SE_ENDOK = "SE_ENDOK";
	//public final static String SE_ENDNO = "SE_ENDNO";
	public final static String SE_WIN = "SE_WIN";
	public final static String SE_LOST = "SE_LOST";
	
	//territory signals
	public final static String SE_TERINIT = "SE_TERINIT";
	public final static String CL_AGREE = "CL_AGREE";
	public final static String CL_DISAGREE = "CL_DISAGREE";
	public final static String CL_MYADD = "CL_MYADD";
	public final static String SE_TERRADD = "SE_TERRADD";
	public final static String SE_CONFLICT = "SE_CONFLICT";
	
	private Signals() {};
	
}
