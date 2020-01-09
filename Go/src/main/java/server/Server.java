
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
	ServerThread serverThread;
	static ArrayList<ServerThread> waiting;
	static ArrayList<ServerThread> waitingBot;
	static ArrayList<ServerThread> serverThreads;
	int portNumber = 4444;
	SocketServer()
	{
		serverThreads = new ArrayList<ServerThread>();
		waiting = new ArrayList<ServerThread>();
		waitingBot = new ArrayList<ServerThread>();
		try
			{
				server = new ServerSocket(portNumber);
			}
		catch(IOException e)
			{
				System.out.println("Nie mozna utworzyc serwera na porcie " + portNumber);
				System.exit(-1);
			}
	}
	
	public void listen()
	{
		while(true)
			{
				try
				{
						socket = server.accept();
				}
				catch(Exception e)
				{
						System.out.println("Blad przy tworzeniu watku");
						System.exit(-1);
				}
				serverThread = new ServerThread(socket);
				serverThreads.add(serverThread);	
				serverThread.start();
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
