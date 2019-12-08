package server;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread
{
	Scanner threadIn = null;
	PrintWriter threadOut = null;
	Socket socket = null;
	String line;
	ServerThread opponent;
	int color;
	ServerThread(Socket socket, int color)
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
		if(color == 1)
		{
			opponent = SocketServer.serverThreads.get(1);
		}
		else
		{
			opponent = SocketServer.serverThreads.get(0);
		}
		System.out.println(this.color + " wątek zadziałał");
		System.out.println(opponent + " to mój przeciwnik");
		if(color == 1)
			{
				threadOut.println("black");
			}
		else
			{
				threadOut.println("green");
			}
		while(true)
			{
				try
					{
						try
							{
								if(!threadIn.hasNextLine())
									{
										break;
									}
								line = threadIn.nextLine();
								String splitString[] = line.split("X|Y");
								int dimensionsX = Integer.parseInt(splitString[1]);
								int dimensionsY = Integer.parseInt(splitString[2]);
								if(SocketServer.game[dimensionsX][dimensionsY] == 0)
									{
										SocketServer.game[dimensionsX][dimensionsY] = color;
										threadOut.println("ok");
										opponent.threadOut.println(line);
									}
							}
						catch(Exception e)
							{
								System.out.println("Problem z inputem w wątku");
								break;
							}
					}
				catch(Exception e)
					{
						System.out.println("Błąd odczytu w wątku");
						break;
					}
			}
		SocketServer.serverThreads.remove(this);
		close();
	}
	public void close()
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