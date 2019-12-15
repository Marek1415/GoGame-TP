package server;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import constants.PawnColors.Pawn;
import constants.Signals;
import constants.Statuses;
import game.Game;

public class ServerThread extends Thread
{
	Game game = null;
	Scanner threadIn = null;
	PrintWriter threadOut = null;
	Socket socket = null;
	String line;
	ServerThread opponent;
	Pawn color;
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
						String splitString[] = line.split(" ");
						String output = null;
						if(splitString[0].contentEquals(Signals.CL_PUT))
						{
							int place = Integer.parseInt(splitString[1]);
							if(game.tryPut(place, this.color.Symbol()) == Statuses.STATUS_PUT)
							{
								output = Signals.SE_PUTOK + " " + place;
								threadOut.println(output);
								opponent.threadOut.println(Signals.CL_PUT + " " + place);
							}
							else
							{
								output = Signals.SE_PUTNO;
								threadOut.println(output);
							}
						}
						else if(splitString[0].equals(Signals.CL_ROOMNEW))
						{
							int bot = Integer.parseInt(splitString[1]);
							color = Pawn.WHITE;
							threadOut.println(Signals.COLOR_WHITE);
							if(bot != 10)
							{
								SocketServer.waiting.add(this);
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
								while(SocketServer.waiting.isEmpty()) { wait(1); }
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
					}
				catch(Exception e)
					{
						System.out.println("Błąd odczytu w wątku");
						break;
					}
			}
		SocketServer.serverThreads.remove(this);
	}
	protected void finalize()
	{
		try
			{
				threadIn.close();
				threadOut.close();
				socket.close();
			}
		catch(Exception e)
			{
				System.out.println("Problemy z zamknięciem wątku");
				System.exit(-1);
			}
	}
}