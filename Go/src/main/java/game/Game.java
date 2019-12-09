package game;

import server.*;

import static  constants.PawnColors.*;

public class Game
{
	
	//variables
	ServerThread player1, player2;
	int board[][];
	int size;
	int realSize;
	
	public Game() {
		initBoard(5);
		//printRealBoard();
		printBoard();
		System.out.println(isEmpty(getCoords(15)));
		putPawn(WHITE, getCoords(15));
		printBoard();
		System.out.println(isEmpty(getCoords(15)));
		removePawn(getCoords(15));
		printBoard();
		System.out.println(isEmpty(getCoords(15)));
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
		for(int i = 1; i < size+1; i++) {
			for(int j = 1; j < size+1; j ++)
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
	public boolean isEmpty(int[] coords) {
		if(board[coords[0]][coords[1]] == EMPTY)
			return true;
		else
			return false;
	}
	
	/** Returns the coordinates of a specific field number.*/
	public int[] getCoords(int number) {
		int[] coords = new int[2];
		coords[0] = (int)number/size + 1;
		coords[1] = (int)number%size + 1;
		return coords;
	}
	
	/** Puts new pawn into specific field.*/
	public void putPawn(int color, int[] coords) {
		board[coords[0]][coords[1]] = color;
	}
	
	/** Removes pawn from specific field.*/
	public void removePawn(int[] coords) {
		board[coords[0]][coords[1]] = EMPTY;
	}
	
	public static void main(String[] args) {
		//TODO delete main method
		Game game = new Game();
	}
}
