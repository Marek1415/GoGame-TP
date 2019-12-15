package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;

import client_gui.*;
import constants.PawnColors;
import constants.PawnColors.Pawn;
import constants.Signals;

import static constants.Messages.*;
import static constants.Signals.*;

/**
 * @author ja 
 * Client GUI, displays Board, action Buttons, ... TODO add more
 */
public class Client extends JFrame
{
	Pawn color, enemyColor;
	static boolean myTurn;
	ClientGUI GUI;
	ClientThread clientThread;
	Socket socket = null;
	PrintWriter out = null;
	Scanner in = null;
	
	/** Public constructor. */
	public Client()
	{
		//listen
		listen();
		
		//initialize GUI
		GUI = new ClientGUI() {
			
			@Override
			public void recSignalNow(String signal) {
				executeSignalNow(signal);
			}
			
			@Override
			public void recSignalWait(String signal) {
				executeSignalWait(signal);
			}
		};
		
		
		//start GUI
		GUI.initStartModule();
		
		clientThread = new ClientThread(this);
		clientThread.start();
	}
	
	public void messageReceived(String command)
	{
		String splitString[];
		System.out.println(command);
		try
		{
			splitString = command.split(" ");
			if(command.equals(Signals.COLOR_BLACK))
			{
				color = Pawn.BLACK;
				myTurn = false;
				GUI.turnOFF();
				enemyColor = Pawn.WHITE;
			}
			else if(command.equals(Signals.COLOR_WHITE))
			{
				GUI.turnON();
				myTurn = true;
				color = Pawn.WHITE;
				enemyColor = Pawn.BLACK;
			}
			else if(splitString[0].equals(Signals.SE_PUTOK))
			{
				int place = Integer.parseInt(splitString[1]);
				GUI.addPawn(place, color.Symbol());
				GUI.turnOFF();
				myTurn = false;
			}
			else if(splitString[0].equals(Signals.CL_PUT))
			{
				int place = Integer.parseInt(splitString[1]);
				GUI.addPawn(place, enemyColor.Symbol());
				GUI.turnON();
				myTurn = true;
			}
			else if(splitString[0].equals(Signals.START)) 
			{
				int size = Integer.parseInt(splitString[1]);
				GUI.initGame(size);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Problem z obsluga rozkazu");
		}
	}
	public void startButtons(String signal)
	{
		System.out.println(signal);
		out.println(signal);
	}
	
	/** Executes GUI signal without waiting for turn. */
	public void executeSignalNow(String signal) {
		System.out.println(signal);
		out.println(signal);
	}
	
	/** Executes GUI signal only if its client turn. */
	public void executeSignalWait(String signal) {
		
		if(myTurn) 
		{
			System.out.println(signal);
			out.println(signal);
		}
		else 
		{
			GUI.addMessage(THIS + NO_TURN);
		}
	}
	
	public void listen()
	{
		try
			{
				socket = new Socket("localhost", 4444);
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new Scanner(new InputStreamReader(socket.getInputStream()));
			}
		catch(UnknownHostException e)
			{
				System.out.println("Nieznany host");
				System.exit(1);
			}
		catch(IOException e)
			{
				System.out.println("Brak I/O");
				System.exit(1);
			}
		}

	/** Creating new Client. */
	public static void main(String[] args) {
		Client client = new Client();
	}

}
