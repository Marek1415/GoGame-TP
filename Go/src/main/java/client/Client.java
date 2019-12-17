package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;

import client_gui.*;
import constants.Messages;
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
	boolean myTurn, agreeMode;
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
		agreeMode = false;
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
	
	public synchronized void messageReceived(String command)
	{
		System.out.println(command);
		String splitString[];
		try
		{
			splitString = command.split(" ");
			if(command.equals(Signals.COLOR_BLACK))
			{
				color = Pawn.BLACK;
				myTurn = false;
				enemyColor = Pawn.WHITE;
				GUI.turnOFF();
				GUI.enemyJoin();
				GUI.setPlayerColor(color);
				GUI.addMessage(Messages.SERVER + " " + Messages.COLOR_BLACK);
			}
			else if(command.equals(Signals.DISCONNECT))
			{
				GUI.addMessage(Messages.NO_CLIENT);
				GUI.disconnected();
				in = null;
				out = null;
				clientThread.continueRunning = false;
			}
			else if(splitString[0].equals(Signals.REMOVE))
			{
				int size = splitString.length;
				for(int i = 1; i < size; i++)
				{
					int place = Integer.parseInt(splitString[i]);
					GUI.removePawn(place);
				}
			}
			else if(command.equals(Signals.CL_READY))
			{
				GUI.turnON();
				GUI.enemyJoin();
			}
			else if(command.equals(Signals.COLOR_WHITE))
			{
				myTurn = true;
				color = Pawn.WHITE;
				enemyColor = Pawn.BLACK;
				GUI.setPlayerColor(color);
				GUI.addMessage(Messages.SERVER + " " + Messages.COLOR_WHITE);
			}
			else if(splitString[0].equals(Signals.SE_PUTOK))
			{
				int place = Integer.parseInt(splitString[1]);
				GUI.addPawn(place, color.Symbol());
				GUI.turnOFF();
				myTurn = false;
			}
			else if(splitString[0].equals(Signals.SE_MESSREC))
			{
				String message = "";
				int length = splitString.length;
				for(int i = 1; i < length; i++)
				{
					message = message + splitString[i] + " ";
				}
				GUI.addMessage(message);
			}
			else if(splitString[0].equals(Signals.SE_PUTNO))
			{
				String message = "";
				int length = splitString.length;
				for(int i = 1; i < length; i++)
				{
					message = message + splitString[i] + " ";
				}
				GUI.addMessage(message);
			}
			else if(splitString[0].equals(Signals.SE_WIN) || splitString[0].equals(Signals.SE_LOST))
			{
				System.out.println(splitString[0]);
				GUI.initEndModule(splitString[0], splitString[1]);
				in = null;
				out = null;
				clientThread.continueRunning = false;
			}
			else if(splitString[0].equals(Signals.POINTS))
			{
				GUI.setPoints(splitString[1]);
			}
			else if(splitString[0].equals(Signals.CL_PUT))
			{
				int place = Integer.parseInt(splitString[1]);
				GUI.addPawn(place, enemyColor.Symbol());
				GUI.turnON();
				myTurn = true;
			}
			else if(command.equals(Signals.ENEMY_CHECKED))
			{
				GUI.turnON();
				GUI.addMessage(Messages.SERVER+ " " + Messages.ENEMY_CHECK);
				myTurn = true;
			}
			else if(command.equals(Signals.SE_CHECKED))
			{
				GUI.turnOFF();
				myTurn = false;
			}
			else if(command.equals(Signals.CL_END))
			{
				agreeMode = true;
				GUI.initAgreeModule();
			}
			else if(splitString[0].equals(Signals.START)) 
			{
				int size = Integer.parseInt(splitString[1]);
				GUI.initGame(size);
			}
			else if(splitString[0].equals(Signals.SE_TERRADD)) 
			{
				int position = Integer.parseInt(splitString[1]);
				int status = Integer.parseInt(splitString[2]);
				GUI.addTerritory(position, status);
			}
			else if(splitString[0].equals(Signals.SE_DISAGREE)) 
			{
				agreeMode = false;
				GUI.turnON();
				myTurn = true;
				GUI.hideAgreeModule();
			}
			else if(splitString[0].equals(Signals.ENEMY_DISAGREE)) 
			{
				GUI.turnOFF();
				myTurn = false;
				agreeMode = false;
				GUI.hideAgreeModule();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Problem z obsluga rozkazu");
		}
	}	
	/** Executes GUI signal without waiting for turn. */
	public void executeSignalNow(String signal) {
		System.out.println(signal);
		if(in == null || out == null)
		{
			GUI.addMessage(Messages.NO_CLIENT);
		}
		else
		{
			out.println(signal);
		}
	}
	
	/** Executes GUI signal only if its client turn. */
	public void executeSignalWait(String signal) 
	{
		if(in == null || out == null)
		{
			GUI.addMessage(Messages.NO_CLIENT);
		}
		else if(myTurn && !agreeMode) 
		{
			System.out.println(signal);
			out.println(signal);
		}
		else
		{
			if(!myTurn)
			{
				GUI.addMessage(THIS + NO_TURN);
			}
			else
			{
				GUI.addMessage(THIS + AGREE_MODE);
			}
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
