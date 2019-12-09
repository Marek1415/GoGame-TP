package client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import client_panels.ActionPanel;
import client_panels.BoardPanel;
import client_panels.MessengerPanel;
import client_gui.*;
import constants.PawnColors;
import constants.PawnColors.Pawn;
import constants.Signals;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author gumises
 * Client GUI, displays Board, action Buttons, ... TODO add more
 */
public class Client extends JFrame
{
	Pawn color, enemyColor;
	static boolean myTurn;
	ClientGUI GUI;
	SocketChannel clientSocket;
	//Socket socket = null;
	PrintWriter out = null;
	Scanner in = null;
	
	/** Public constructor. */
	public Client()
	{
		GUI = new ClientGUI(this);
		InetSocketAddress port = new InetSocketAddress("localhost", 4444);
		try 
		{
			clientSocket = SocketChannel.open(port);
			listen();
		} 
		catch (Exception e) 
		{
			System.out.println("Problem w kliencie");
		}
	}
	
	public void readyInit() {
		System.out.println("Ready!");
	}
	
	public void checkInit() {
		System.out.println("Check!");
	}
	
	public void endInit() {
		System.out.println("End!");
	}
	/*public void boardButtonClicked(String signal)
	{
		if(myTurn)
		{
			String data;
			System.out.println(signal);
			String splitString[] = null;
			out.println(signal);
			try
			{
				data = in.nextLine();
				try
				{
					splitString = data.split(" ");
					System.out.println(data);
					if(splitString[0].equals(Signals.SE_PUTOK))
					{
						int place = Integer.parseInt(splitString[1]);
						GUI.addPawn(place, color.Symbol());
					}
					GUI.turnOFF();
					data = in.nextLine();
					System.out.println(data);
					splitString = data.split(" ");
					if(splitString[0].equals(Signals.SE_PUTOK))
					{
						int place = Integer.parseInt(splitString[1]);
						GUI.addPawn(place, enemyColor.Symbol());
					}
				}
				catch(Exception ex)
				{
					
					
				}
			}
			catch(Exception e)
			{
				
			}
			GUI.turnON();
		}
	}*/
	/*public void startMode(int mode) {
	// TODO Auto-generated method stub

	}

	public void sizeBoard(int size) {
	// TODO Auto-generated method stub

	}

	public void roomNoumber(int roomNoumber) {
	// TODO Auto-generated method stub

	}

	public void roomNew() {
	// TODO Auto-generated method stub

	}

	public void endMode(int mode) {
	// TODO Auto-generated method stub

	}

	public void agreeMode(int mode) {
	// TODO Auto-generated method stub

	}*/
	public void listen() throws Exception
	{
		byte[] message = new String("aha").getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(message);
		clientSocket.write(buffer);

		buffer.clear();
	}

	/** Creating new Client. */
	public static void main(String[] args) {
		Client client = new Client();
	}

}
