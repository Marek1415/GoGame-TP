package server;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import constants.PawnColors.Pawn;
import constants.Signals;

public class ServerThread extends Thread
{
	Scanner threadIn = null;
	PrintWriter threadOut = null;
	Socket socket = null;
	String line;
	ServerThread opponent;
	Pawn color;
	ServerThread(Socket socket, Pawn color)
	{
		this.color = color;
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
							output = Signals.SE_PUTOK + " " + place;
							threadOut.println(output);
							opponent.threadOut.println(Signals.CL_PUT + " " + place);
						}
						else if(splitString[0].equals(Signals.CL_ROOMNEW))
						{
							output = Signals.START;
							threadOut.println(output);
							opponent.threadOut.println(output);
							if(color == Pawn.WHITE)
							{
								threadOut.println(Signals.COLOR_WHITE);
							}
							else
							{
								threadOut.println(Signals.COLOR_BLACK);
							}
						}
						System.out.println(output);
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