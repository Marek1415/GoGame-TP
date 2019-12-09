
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;


import java.io.*;
import java.nio.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

class SocketServer
{
	static int game[][] = new int[20][20];
	ServerSocket server = null;
	Scanner in = null;
	PrintWriter out = null;
	String line = "";
	Selector selector;
	ServerSocketChannel socket;
	static ArrayList<ServerThread> serverThreads;
	int portNumber = 4444;
	SocketServer()
	{
		try
		{
			selector = Selector.open();
			socket = ServerSocketChannel.open();
			socket.bind(new InetSocketAddress(4444));
	        socket.configureBlocking(false);
			int ops = socket.validOps();
			SelectionKey selectKy = socket.register(selector, ops, null); 
	        socket.register(selector, SelectionKey.OP_ACCEPT);
		}
		catch(Exception e)
		{
			System.out.println("Nie można utworzyc serwera");
		}

	}
	
	public void listen() throws Exception
	{
		while (true) {
			 
			selector.select();
 
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();
 
			while (iterator.hasNext()) {
				SelectionKey myKey = iterator.next();
 
				if (myKey.isAcceptable()) 
				{
					SocketChannel client = socket.accept();
					client.configureBlocking(false);
					client.register(selector, SelectionKey.OP_READ);
 
				}
				else if (myKey.isReadable()) 
				{
					
					SocketChannel client = (SocketChannel) myKey.channel();
					ByteBuffer buffer = ByteBuffer.allocate(256);
					client.read(buffer);
					String result = new String(buffer.array()).trim();
					System.out.println(result); 
				}
				iterator.remove();
			}
		}
	}
}

public class Server
{
	public static void main(String[] args)
	{
		SocketServer server = new SocketServer();
		try
		{
			server.listen();
		}
		catch(Exception e)
		{
			System.out.println("Problem z podlaczeniem do gniazda");
		}
	}
}
