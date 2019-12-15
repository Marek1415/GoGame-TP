package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import constants.Signals;

class ClientThread extends Thread{
	Scanner threadIn = null;
	String data;
	Client myClient;
	
	ClientThread(Client client)
	{
		myClient = client;
		threadIn = myClient.in;
	}
	public void run()
	{
		while(true)
		{
			try
			{
				if(!threadIn.hasNext())
				{
					break;
				}
				data = threadIn.nextLine();
				System.out.println(data);
				myClient.messageReceived(data);
			}
			catch(Exception e)
			{
				System.out.println("Problem z watkiem kllienta");
			}
		}
	}
}
