import java.util.*;
import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;
package GoGameThroughServer;

class GameServer
{
	private Socket socket;
	public Server(Socket socket)
	{
		this.socket = socket;
	}
}

public class Server
{
	public static void main()
	{
		Server server = new Server();
	}
}


