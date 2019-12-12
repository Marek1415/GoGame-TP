
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import constants.PawnColors.Pawn;

import java.io.*;

class SocketServer
{
	static int game[][] = new int[20][20];
	ServerSocket server = null;
	Socket socket = null;
	Scanner in = null;
	PrintWriter out = null;
	String line = "";
	ServerThread[] tuple = new ServerThread[2];
	static ArrayList<ServerThread> serverThreads;
	int portNumber = 4444;
	SocketServer()
	{
		serverThreads = new ArrayList<ServerThread>();
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
		/*try
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
		  String splitString[] = line.split("X|Y");
		  int dimensionsX = Integer.parseInt(splitString[1]);
		  int dimensionsY = Integer.parseInt(splitString[2]);
		  if(game[dimensionsX][dimensionsY] == 0)
		  {
		  game[dimensionsX][dimensionsY] = 1;
		  threadOut.println("ok");
		  }
		  }
		  catch(Exception e)
		  {
		  System.out.println("Problem z inputem w wątku");
		  System.exit(-1);
		  }
		  }*/
		while(true)
			{
				try
				{
						socket = server.accept();
				}
				catch(Exception e)
				{
						System.out.println("Błąd przy tworzeniu wątku");
						System.exit(-1);
				}
				
				if(serverThreads.size() % 2 == 0)
				{
					ServerThread serverThread = new ServerThread(socket, Pawn.WHITE);
					serverThreads.add(serverThread);
					tuple[0] = serverThread;
				}
				else if(serverThreads.size() % 2 == 1)
				{
					ServerThread serverThread = new ServerThread(socket, Pawn.BLACK);
					serverThreads.add(serverThread);
					tuple[1] = serverThread;
					serverThread.opponent = tuple[0];
					tuple[0].opponent = serverThread;
					for(ServerThread t: tuple)
					{
						t.start();
					}
					tuple = new ServerThread[2];
				}	
			}
	}
	
	protected void finalize()
	{
		try
			{
				in.close();
				out.close();
				socket.close();
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
