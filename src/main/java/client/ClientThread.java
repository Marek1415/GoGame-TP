package client;

import java.util.Scanner;

class ClientThread extends Thread{
	Scanner threadIn = null;
	String data;
	Client myClient;
	boolean continueRunning = true;
	
	ClientThread(Client client)
	{
		myClient = client;
		threadIn = myClient.in;
	}
	public void run()
	{
		while(continueRunning)
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
