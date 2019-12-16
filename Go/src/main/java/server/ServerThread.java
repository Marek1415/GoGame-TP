package server;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import constants.Messages;
import constants.PawnColors.Pawn;
import constants.Signals;
import constants.Statuses;
import game.Game;

import static constants.Messages.*;
import static constants.Territories.*;

public class ServerThread extends Thread
{
	Game game = null;
	Scanner threadIn = null;
	PrintWriter threadOut = null;
	Socket socket = null;
	String line;
	boolean check = false;
	ServerThread opponent;
	Pawn color;
	int points = 0;
	boolean agree = false;
	ServerThread(Socket socket)
	{
		this.socket = socket;
		try
			{
				threadIn = new Scanner(new InputStreamReader(socket.getInputStream()));
				threadOut = new PrintWriter(socket.getOutputStream(), true);
			}
		catch(IOException e)
			{
				System.out.println("Nie można utworzyć wątku na porcie");
				System.exit(-1);
			}
	}
		
	public void run()
	{
		while(true)
			{
				try
					{
						if(!threadIn.hasNextLine())
							{
								break;
							}
						line = threadIn.nextLine();
						check = false;
						agree = false;
						if(opponent != null)
							opponent.agree = false;
						System.out.println(line);
						String splitString[] = line.split(" ");
						String output = null;
						if(splitString[0].equals(Signals.CL_PUT))
						{
							int place = Integer.parseInt(splitString[1]);
							int status = game.tryPut(place, this.color.Symbol());
							if(status == Statuses.STATUS_PUT)
							{
								output = Signals.SE_PUTOK + " " + place;
								threadOut.println(output);
								opponent.threadOut.println(Signals.CL_PUT + " " + place);
							}
							else if(status == Statuses.STATUS_KILL)
							{
								output = Signals.REMOVE + " ";
								for(int i: game.currentKilled)
								{
									output = output + i + " ";
								}
								points += game.currentKilled.size();
								threadOut.println(output);
								opponent.threadOut.println(output);
								output = Signals.POINTS + " " + points;
								threadOut.println(output);
								output = Signals.SE_PUTOK + " " + place;
								threadOut.println(output);
								opponent.threadOut.println(Signals.CL_PUT + " " + place);
							}
							else
							{
								output = Signals.SE_PUTNO + " ";
								if(status == Statuses.STATUS_SUICIDE)
								{
									output = output + SERVER + Messages.SUICIDE;
								}
								else if(status == Statuses.STATUS_KO)
								{
									output = output + SERVER + " " + Messages.KO;
								}
								else if(status == Statuses.STATUS_NOEMPTY)
								{
									output = output + SERVER + Messages.NO_EMPTY;
								}
								threadOut.println(output);
							}
						}
						else if(splitString[0].equals(Signals.CL_MESSEND))
						{
							if(opponent != null)
							{
								String message = "";
								String messageThis = Messages.THIS;
								String messageEnemy = Messages.CLIENT;
								int size = splitString.length;
								for(int i = 1; i < size; i++)
								{
									message = message + splitString[i] + " ";
								}
								threadOut.println(Signals.SE_MESSREC + " " + messageThis + " " + message);
								opponent.threadOut.println(Signals.SE_MESSREC + " " + messageEnemy + " " + message);
							}
							else
							{		
								threadOut.println(Signals.SE_MESSREC + " " + Messages.SERVER + " " + Messages.NO_CLIENT);
							}
						}
						else if(splitString[0].equals(Signals.CL_ROOMNEW))
						{
							int bot = Integer.parseInt(splitString[1]);
							color = Pawn.WHITE;
							threadOut.println(Signals.COLOR_WHITE);
							if(bot != 10)
							{
								synchronized(this)
								{
									SocketServer.waiting.add(this);
								}
								game = new Game();
								game.initBoard(Integer.parseInt(splitString[2]));
							}
							else
							{
								
							}
						}
						else if(splitString[0].equals(Signals.CL_ROOMJOIN))
						{
							synchronized(this)
							{
								while(SocketServer.waiting.isEmpty()) { TimeUnit.SECONDS.sleep(1); }
								color = Pawn.BLACK;
								threadOut.println(Signals.COLOR_BLACK);
								opponent = SocketServer.waiting.get(0);
								SocketServer.waiting.get(0).opponent = this;
								System.out.println("musi dzialac");
								this.game = SocketServer.waiting.get(0).game;
								threadOut.println(Signals.START + " " + game.getSize());
								opponent.threadOut.println(Signals.CL_READY);
								SocketServer.waiting.remove(0);
							}
						}
						else if(splitString[0].equals(Signals.CL_CHECK))
						{
							check = true;
							if(!opponent.check)
							{
								threadOut.println(Signals.SE_CHECKED);
								opponent.threadOut.println(Signals.ENEMY_CHECKED);
							}
							else
							{
								threadOut.println(Signals.CL_END);
								opponent.threadOut.println(Signals.CL_END);
								game.initTerritory();
							}
						}
						else if(splitString[0].equals(Signals.CL_RESIGN))
						{
							threadOut.println(Signals.SE_LOST + " " + points);
							opponent.threadOut.println(Signals.SE_WIN + " " + opponent.points);
						}
						else if(splitString[0].equals(Signals.CL_MYADD))
						{
							int position = Integer.parseInt(splitString[1]);
							int status = game.clickTerritory(position, color.Symbol());
							if(status == NOBODY)
							{
								String msg = Signals.SE_TERRADD + " " + position + " " + status;
								threadOut.println(msg);
								opponent.threadOut.println(msg);
							}
							else if(status == CONFLICT)
							{
								String msg = Signals.SE_TERRADD + " " + position + " " + status;
								threadOut.println(msg);
								opponent.threadOut.println(msg);
							}
							else if(status == ME)
							{
								String msg = Signals.SE_TERRADD + " " + position + " " + status;
								String msg2 = Signals.SE_TERRADD + " " + position + " " + ENEMY;
								threadOut.println(msg2);
								opponent.threadOut.println(msg);
							}
							else if(status == ENEMY)
							{
								String msg = Signals.SE_TERRADD + " " + position + " " + status;
								String msg2 = Signals.SE_TERRADD + " " + position + " " + ME;
								threadOut.println(msg2);
								opponent.threadOut.println(msg);
							}
						}
						else if(splitString[0].equals(Signals.CL_AGREE))
						{
							agree = true;
							points += game.getTerritoryPoints(color.Symbol());
							if(opponent.agree == true)
							{
								if(opponent.points > points)
								{
									threadOut.println(Signals.SE_LOST + " " + points);
									opponent.threadOut.println(Signals.SE_WIN + " " + opponent.points);
								}
								else if(opponent.points == points)
								{
									threadOut.println(Signals.SE_WIN + " " + points);
									opponent.threadOut.println(Signals.SE_WIN + " " + opponent.points);
								}
								else
								{
									threadOut.println(Signals.SE_WIN + " " + points);
									opponent.threadOut.println(Signals.SE_LOST + " " + opponent.points);
								}
							}
							else
							{
								threadOut.println(Signals.SE_MESSREC + " " + Messages.SERVER + " " + WAITING);
							}
						}
						else if(splitString[0].equals(Signals.CL_DISAGREE))
						{
							opponent.threadOut.println(Signals.SE_DISAGREE);
							threadOut.println(Signals.SE_DISAGREE);
						}
					}
				catch(Exception e)
					{
						System.out.println("Błąd odczytu w wątku");
						break;
					}
			}
		try
		{
			if(opponent != null)
			{
				if(opponent.opponent != null)
				{
					opponent.threadOut.println(Signals.DISCONNECT);
					opponent.opponent = null;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Problemy here");
		}
		synchronized(this)
		{
			SocketServer.waiting.remove(this);
			SocketServer.serverThreads.remove(this);
		}
	}
}