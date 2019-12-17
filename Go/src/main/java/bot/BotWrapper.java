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
	
	public BotWrapper()
	{
		listen();
		bot = new Bot();
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
					out.println("bot is putting here" + place);
					int move = bot.makeBotMove();
					out.println("bot is moving here" + move);
					if(move == Statuses.STATUS_CANT)
					{
						out.println(Signals.CL_RESIGN);
						break;
					}
					else
					{
						out.println(Signals.CL_PUT + " " + move);
					}
				}
				else if(splitString[0].equals(Signals.CL_CHECK))
				{
					int move = bot.makeBotMove();
					System.out.println(move);
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
				else if(splitString[0].equals(Signals.START))
				{
					int size = Integer.parseInt(splitString[1]);
					bot.initBot(size);
				}
			}
			catch(Exception e)
			{
				System.out.println("Problem z watkiem kllienta");
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
