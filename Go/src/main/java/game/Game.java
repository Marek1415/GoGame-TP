package game;

import server.*;

import static  constants.PawnColors.*;

import java.util.ArrayList;

public class Game
{
	
	//variables
	ServerThread player1, player2;
	int board[][];
	int size;
	int realSize;
	ArrayList<Integer> territory;
	
	public Game() {
		initBoard(5);
		putPawn(WHITE, 15);

		putPawn(BLACK, 16);
		putPawn(BLACK, 10);
		putPawn(BLACK, 20);
		
		printBoard();
		System.out.println(_hasBreath(15));
	}
	
	/** Initialize game board.*/
	public void initBoard(int newSize) {
		this.size = newSize;
		this.realSize = newSize + 2;
		board = new int[size+2][size+2];
		initBorders();
	}
	
	/** Sets the borders. */
	public void initBorders() {
		for(int i = 0; i < realSize; i++)
		for(int j = 0; j < realSize; j ++)
			if(i == 0 | j == 0 | i == realSize-1 | j == realSize-1)
				board[i][j] = BORDER;
			else
				board[i][j] = EMPTY;
	}
	
	/** Prints board in the console. */
	public void printBoard() {
		for(int i = 1; i < realSize-1; i++) {
			for(int j = 1; j < realSize-1; j ++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}
	
	/** Prints real board in the console. */
	public void printRealBoard() {
		for(int i = 0; i < realSize; i++) {
			for(int j = 0; j < realSize; j ++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}
	
	/** Checks if specific board field is empty.*/
	private boolean _isEmpty(int[] coords) {
		if(board[coords[1]][coords[0]] == EMPTY)
			return true;
		else
			return false;
	}
	
	/** Checks if specific board field is empty.*/
	public boolean isEmpty(int position) {
		return _isEmpty(getCoords(position));
	}
	
	/** Puts new pawn into specific field.*/
	private void _putPawn(int color, int[] coords) {
		board[coords[1]][coords[0]] = color;
	}
	
	/** Puts new pawn into specific field.*/
	public void putPawn(int color, int position) {
		_putPawn(color, getCoords(position));
	}
	
	/** Removes pawn from specific field.*/
	private void _removePawn(int[] coords) {
		board[coords[1]][coords[0]] = EMPTY;
	}
	
	/** Removes pawn from specific field.*/
	public void removePawn(int position) {
		_removePawn(getCoords(position));
	}
	
	/** Checks if territory has breaths. */
	public boolean hasBreaths(ArrayList<Integer> territory) {
		
		for(int i = 0; i < territory.size(); i++)
			if(_hasBreath(territory.get(i)))
				return true;
		return false;
	}
	
	/** Check if single field has breaths. */
	private boolean _hasBreath(int coords[]) {
		int x = coords[0];
		int y = coords[1];
		
		if(	board[y][x-1]*
			board[y][x+1]*
			board[y-1][x]*
			board[y+1][x] == 0)
			return true;
		else
			return false;

	}
	
	/** Check if single field has breaths. */
	private boolean _hasBreath(int position) {
		return _hasBreath(getCoords(position));
	}
	
	/** Returns the array of territory.*/
	public void getTerritory(int position) {
		
	}
	
	/** Returns the coordinates of a specific field number.*/
	public int[] getCoords(int number) {
		int[] coords = new int[2];
		coords[0] = (int)number%size + 1;
		coords[1] = (int)number/size + 1;
		return coords;
	}
	
	public static void main(String[] args) {
		//TODO delete main method
		Game game = new Game();
	}
}
