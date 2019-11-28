package constants;


/**
 * @author gumises
 * Signals for asynchronously communication between client and server.
 * CL_* - send by the client
 * SE_* - send by the server
 */
public final class Signals {

	//import static constants.Signals.*;
	
	//start game
	public final static String CL_PLAYBOT = "CL_PLAYBOT";
	public final static String CL_PLAYUSR = "CL_PLAYBOT";
	public final static String SE_ROOMSGET = "CL_ROOMSGET";
	public final static String CL_ROOMSET = "CL_ROOMSET";
	public final static String CL_ROOMNEW = "CL_ROOMNEW";
	public final static String SE_SIZEGET = "SE_SIZEGET";
	public final static String CL_SIZESET = "CL_SIZESET";
	public final static String SE_ENEMYNEW = "SE_ENEMYNEW";
	public final static String CL_START = "CL_START";
	public final static String SE_COLORSET = "SE_COLORSET";
	
	//signals during the game
	public final static String CL_PUT = "CL_PUT";
	public final static String SE_PUTOK = "SE_PUTOK";
	public final static String SE_PUTNO = "SE_PUTNO";
	public final static String CL_TIMEUP = "CL_TIMEUP";
	public final static String CL_CHECK = "CL_CHECK";
	public final static String CL_MESSSEND = "CL_MESSSEND";
	public final static String SE_MESSREC = "SE_MESSREC";
	
	//end game
	public final static String CL_END = "CL_END";
	public final static String SE_ENDGET = "CL_ENDGET";
	public final static String CL_ENDSET = "CL_ENDSET";
	public final static String SE_ENDOK = "SE_ENDOK";
	public final static String SE_ENDNO = "SE_ENDNO";
	public final static String CL_RESIGN = "CL_RESIGN";
	public final static String SE_WIN = "CL_WIN";
	public final static String SE_LOST = "CL_LOST";
	
	private Signals() {};
	
}
