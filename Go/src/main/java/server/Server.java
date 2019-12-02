
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

class SocketServer
{
	int game[][] = new int[13][13];
	ServerSocket server = null;
	Socket client = null;
	Scanner in = null;
	PrintWriter out = null;
	String line = "";
	int portNumber = 4444;
	SocketServer()
	{
		try
			{
				server = new ServerSocket(portNumber);
			}
		catch(IOException e)
			{
				System.out.println("Nie można utworzyć serwera na porcie " + portNumber);
				System.exit(-1);
			}
	}
	public void listen()
	{
		try
			{
				client = server.accept();
			}
		catch(IOException e)
			{
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}
		try
			{
				in = new Scanner(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(), true);
			}
		catch(IOException e)
			{
				System.out.println("Accept failed: " + portNumber);
				System.exit(-1);
			}
		while(line != null)
			{
				try
					{
						if(!in.hasNextLine())
							{
								break;
							}
						line = in.nextLine();
						out.println("ok");
					}
				catch(Exception e)
					{
						System.out.println("Error: problem z inputem");
						System.exit(-1);
					}
			}
	}
	protected void finalize()
	{
		try
			{
				in.close();
				out.close();
				client.close();
				server.close();
			}
		catch(IOException e)
			{
				System.out.println("Problemy z zamknieciem serwera");
				System.exit(-1);
			}
	}
}

public class Server
{
	public static void main(String[] args)
	{
		SocketServer server = new SocketServer();
		server.listen();
	}
}
