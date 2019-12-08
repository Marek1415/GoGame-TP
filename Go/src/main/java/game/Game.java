package game;

import server.*;

public class Game
{
	ServerThread player1, player2;
	int board[][];
	public Game(int x, int y)
	{
		board = new int[x][y];
	}
}
