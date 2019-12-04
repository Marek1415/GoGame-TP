package client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import client_interfaces.AgreeMethod;
import client_interfaces.EndMethod;
import client_interfaces.ModInits;
import client_interfaces.RoomMethod;
import client_interfaces.SizeMethod;
import client_interfaces.StartMethod;
import client_panels.ActionPanel;
import client_panels.BoardPanel;
import client_panels.MessengerPanel;

/**
 * @author gumises
 * Client GUI, displays Board, action Buttons, ... TODO add more
 */
public class Client extends JFrame
{
	Color color, enemyColor;
	//implements AgreeMethod, EndMethod, RoomMethod, SizeMethod, StartMethod, ModInits {
	SimpleGuiForTest myPanel;
	//components
	int Game[][] = new int[13][13];
	private BoardPanel boardPanel;
	private ActionPanel actionPanel;
	//private MessengerPanel messengerPanel;
	Socket socket = null;
	PrintWriter out = null;
	Scanner in = null;
	
	/** Public constructor. */
	public Client()
	{
		myPanel = new SimpleGuiForTest();
		add(myPanel);
		/*boardPanel = new BoardPanel();
		  boardPanel.init(7);
		
		  //action panel
		  actionPanel = new ActionPanel() {
			
		  @Override
		  public void ready() {
		  readyInit();
		  }
			
		  @Override
		  public void check() {
		  checkInit();
		  }
			
		  @Override
		  public void end() {
		  endInit();
		  }
		  };
		
		  //messengerPanel = new MessengerPanel();
		
		  //gridBagLayout, gridBagConstraint
		  GridBagLayout layout = new GridBagLayout(); 
		  GridBagConstraints gbc = new GridBagConstraints();
		  setLayout(layout);
		
		
		  //gbc init
		  gbc.insets = new Insets(5, 5, 5, 5);
		  gbc.weightx = 1;
		  gbc.weighty = 1;
		  gbc.fill = GridBagConstraints.BOTH;
		
		  //board panel
		  gbc.gridx = 0;
		  gbc.gridy = 0;
		  add(boardPanel, gbc);
		
		  //action panel
		  gbc.gridx = 1;
		  gbc.gridy = 0;
		  gbc.gridheight = 1;
		  add(actionPanel, gbc);
		
		  //messenger panel
		  //gbc.gridx = 0;
		  //gbc.gridy = 1;
		  //gbc.gridheight = 1;
		  //add(messengerPanel, gbc);*/
		listen();
		setBounds(100, 100, 800, 800); 
		
		//pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
	}
	
	public void readyInit() {
		System.out.println("Ready!");
	}
	
	public void checkInit() {
		System.out.println("Check!");
	}
	
	public void endInit() {
		System.out.println("End!");
	}

	class ButtonsListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(in.nextLine().equals("black"))
			{
				color = Color.BLACK;
				enemyColor = Color.GREEN;
			}
			else
			{
				color = Color.GREEN;
				enemyColor = Color.BLACK;
			}
			String data = in.nextLine();
			String splitString[] = data.split("X|Y");
			int dimensionsX = Integer.parseInt(splitString[1]);
			int dimensionsY = Integer.parseInt(splitString[2]);
			myPanel.panelButtons[dimensionsX][dimensionsY].setBackground(enemyColor);
			repaint();
			Button button = (Button)e.getSource();
			String command = button.getActionCommand();
			out.println(command);
			try
				{
					data = in.nextLine();
					try
					{
						splitString = command.split("X|Y");
						dimensionsX = Integer.parseInt(splitString[1]);
						dimensionsY = Integer.parseInt(splitString[2]);
						if(data.equals("ok"))
							{
								myPanel.panelButtons[dimensionsX][dimensionsY].setBackground(color);
								repaint();
							}
					}
					catch(Exception ex)
					{
						System.out.println("Error parsing int");
					}
				}
			catch(Exception ex)
				{
					System.out.println("Error");
					System.exit(1);
				}
		}
	}
	/*public void startMode(int mode) {
	// TODO Auto-generated method stub

	}

	public void sizeBoard(int size) {
	// TODO Auto-generated method stub

	}

	public void roomNoumber(int roomNoumber) {
	// TODO Auto-generated method stub

	}

	public void roomNew() {
	// TODO Auto-generated method stub

	}

	public void endMode(int mode) {
	// TODO Auto-generated method stub

	}

	public void agreeMode(int mode) {
	// TODO Auto-generated method stub

	}*/
	
	public class SimpleGuiForTest extends JPanel{
		Button panelButtons[][];
		SimpleGuiForTest()
		{
			panelButtons = new Button[13][13];
			setLayout(new GridLayout(13, 13));
			panelButtons = new Button[13][13];
			for(int i = 0; i < 13; i++)
				{
					for(int j = 0; j < 13; j++)
						{
							panelButtons[i][j] = new Button("X" + i + "Y" + j);
							panelButtons[i][j].addActionListener(new ButtonsListener());
							add(panelButtons[i][j]);
						}
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
