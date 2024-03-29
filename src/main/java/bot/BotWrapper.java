package bot;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import constants.Signals;
import constants.Statuses;
import constants.PawnColors.Pawn;
import game.Game;

public class BotWrapper extends Thread
{
	Socket socket = null;
	PrintWriter out = null;
	Scanner in = null;
	Bot bot;
	String line;
	Pawn color = Pawn.BLACK;
	
	public BotWrapper(Game game)
	{
		listen();
		bot = new Bot();
		bot.game = game;
		bot.setColor(color.Symbol());
		out.println(Signals.CL_ROOMJOINBOT);
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				if(!in.hasNext())
				{
					break;
				}
				line = in.nextLine();
				String splitString[] = line.split(" ");
				if(splitString[0].equals(Signals.CL_PUT))
				{
					int place = Integer.parseInt(splitString[1]);
					bot.putEnemyPawn(place);
					int move = bot.makeBotMove();
					if(move == Statuses.STATUS_CANT)
					{
						//TODO tutaj można dać check zamiast resign
						out.println(Signals.CL_RESIGN);
						break;
					}
					else {
						out.println(Signals.CL_PUT + " " + move);
					}
					//else
					//{
					//	out.println(Signals.CL_PUT + " " + move);
					//	bot.putBotPawn(move);
					//}
				}
				else if(splitString[0].equals(Signals.SE_PUTOK)) {
					int place = Integer.parseInt(splitString[1]);
					bot.putBotPawn(place);
				}
				else if(splitString[0].equals(Signals.SE_PUTNO)) {
					out.println(Signals.CL_CHECK);
				}
				else if(splitString[0].equals(Signals.SE_WIN)
						|| splitString[0].equals(Signals.SE_LOST)
						|| splitString[0].equals(Signals.SE_REMIS))
				{
					break;
				}
				else if(splitString[0].equals(Signals.SE_AGREE) || splitString[0].equals(Signals.SE_TERRADD))
				{
					System.out.println("Ask bot for agreeing");
					out.println(Signals.CL_AGREE);
				}
				else if(splitString[0].equals(Signals.REMOVE))
				{
					int size = splitString.length;
					for(int i = 1; i < size; i++)
					{
						int place = Integer.parseInt(splitString[i]);
						bot.removePawn(place);
					}
				}
				else if(splitString[0].equals(Signals.ENEMY_CHECKED))
				{
					int move = bot.makeBotMove();
					System.out.println(move);
					if(move == Statuses.STATUS_CANT)
					{
						System.out.println("Checked!");
						out.println(Signals.CL_CHECK);
						break;
					}
					else
					{
						out.println(Signals.CL_PUT + " " + move);
					}
				}
				else if(splitString[0].equals(Signals.START))
				{
					int size = Integer.parseInt(splitString[1]);
					bot.initBot(size);
				}
				/*
				else if(splitString[0].equals(Signals.SE_PUTNO))
				{
					//TODO to trzeba usunąć, bot nie możę robić nieprawidłowych ruchów
					int move = bot.makeBotRandomMove();
					System.out.println("__bad bot mode__");
					if(move == Statuses.STATUS_CANT)
					{
						out.println(Signals.CL_CHECK);
						break;
					}
					else
					{
						out.println(Signals.CL_PUT + " " + move);
					}
				}
				*/
			}
			catch(Exception e)
			{
				System.out.println("Problem z watkiem klienta");
			}
		}
	}
	
	void listen()
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
}
