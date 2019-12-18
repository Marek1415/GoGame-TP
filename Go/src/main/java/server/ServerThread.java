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
import constants.Territories;
import game.Game;
import bot.*;

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
	BotWrapper bot = null;
	ServerThread(Socket socket)
	{
		this.socket = socket;
		try
			{
				threadIn = new Scanner(new InputStreamReader(socket.getInputStream()));
				threadOut = new PrintWriter(socket.getOutputStream(), true);
				System.out.println(socket);
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
						String splitString[] = line.split(" ");
						String output = null;
						if(splitString[0].equals(Signals.CL_PUT))
						{
							int place = Integer.parseInt(splitString[1]);
							int status = game.tryPut(place, this.color.Symbol());
							if(status == Statuses.STATUS_PUT)
							{
								output = Signals.SE_PUTOK + " " + place;
								System.out.println(opponent);
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
									output = output + SERVER + Messages.BAD;
								}
								else if(status == Statuses.STATUS_KO)
								{
									output = output + SERVER + " " + Messages.BAD;
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
							int botCond = Integer.parseInt(splitString[1]);
							color = Pawn.WHITE;
							threadOut.println(Signals.COLOR_WHITE);
							if(botCond != 10)
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
								synchronized(this)
								{
									SocketServer.waitingBot.add(this);
								}
								game = new Game();
								game.initBoard(Integer.parseInt(splitString[2]));
								bot = new BotWrapper();
								bot.start();
								threadOut.println(Signals.CL_READY);
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
								this.game = SocketServer.waiting.get(0).game;
								threadOut.println(Signals.START + " " + game.getSize());
								opponent.threadOut.println(Signals.CL_READY);
								SocketServer.waiting.remove(0);
							}
						}
						else if(splitString[0].equals(Signals.CL_ROOMJOINBOT))
						{
							synchronized(this)
							{
								while(SocketServer.waitingBot.isEmpty()) { TimeUnit.SECONDS.sleep(1); }
								color = Pawn.BLACK;
								opponent = SocketServer.waitingBot.get(0);
								SocketServer.waitingBot.get(0).opponent = this;
								this.game = SocketServer.waitingBot.get(0).game;
								threadOut.println(Signals.START + " " + game.getSize());
								opponent.threadOut.println(Signals.CL_READY);
								SocketServer.waitingBot.remove(0);
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
							agree = false;
							if(opponent != null)
							{
								opponent.agree = false;
							}
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
							
							System.out.println("amount of conflicts: " + game.getConflicts());
							if(game.getConflicts() > 0) {
								String msg = Signals.SE_CONFLICT + " " + Territories.CONFLICT_ON;
								threadOut.println(msg);
								opponent.threadOut.println(msg);
							}
							else {
								String msg = Signals.SE_CONFLICT + " " + Territories.CONFLICT_OFF;
								threadOut.println(msg);
								opponent.threadOut.println(msg);
							}
						}
						else if(splitString[0].equals(Signals.CL_AGREE))
						{
							agree = true;
							System.out.println(opponent.agree);
							opponent.threadOut.println(Signals.SE_AGREE);
							
							if(opponent.agree == true)
							{
								points += game.getTerritoryPoints(color.Symbol());
								agree = false;
								opponent.agree = false;
								threadOut.println(Signals.POINTS + " " + points);
								opponent.threadOut.println(Signals.POINTS + " " + opponent.points);
								if(opponent.points > points)
								{
									threadOut.println(Signals.SE_LOST + " " + points);
									opponent.threadOut.println(Signals.SE_WIN + " " + opponent.points);
								}
								else if(opponent.points == points)
								{
									threadOut.println(Signals.SE_REMIS + " " + points);
									opponent.threadOut.println(Signals.SE_REMIS + " " + opponent.points);
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
							agree = false;
							if(opponent != null)
							{
								opponent.agree = false;
							}
							opponent.threadOut.println(Signals.ENEMY_DISAGREE);
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