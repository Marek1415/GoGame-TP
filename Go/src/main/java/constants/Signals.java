package constants;


/**
 * @author gumises
 * Signals for asynchronously communication between client and server.
 * CL_* - send by the client
 * SE_* - send by the server
 */
public final class Signals {

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
	
	//end game
	
	private Signals() {};
	
}
