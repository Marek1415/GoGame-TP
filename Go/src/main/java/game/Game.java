package game;

import server.*;

import static constants.PawnColors.*;
import static constants.Messages.*;

import java.util.ArrayList;

/**
 * Engine for GO game. Use only public methods.
 *
 */
public class Game
{
	
	//variables
	ServerThread player1, player2;
	int board[][];
	int size;
	int realSize;
	ArrayList<Integer> currentKilled = new ArrayList<Integer>();
	ArrayList<Integer> currentTeritory = new ArrayList<Integer>();
	
	public Game() {
		
		initBoard(5);
		
		tryPut(1,WHITE);
		tryPut(1,BLACK);
		tryPut(1,WHITE);
		/*
		
		putPawn(board, WHITE, getCoords(15));
		putPawn(board, WHITE, getCoords(16));

		putPawn(board, BLACK, getCoords(10));
		putPawn(board, BLACK, getCoords(11));
		putPawn(board, BLACK, getCoords(17));
		putPawn(board, BLACK, getCoords(20));
		putPawn(board, BLACK, getCoords(21));
		
		printBoard();

		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp = getTerritory(board, getCoords(17));
		System.out.println(hasBreaths(temp));
		
		for(int i = 0; i < temp.size(); i++)
			System.out.println("temp" + i + "  =  " + temp.get(i));
		//printBoard();
		//System.out.println(getPosition(new int[] {2,4}));
		
		//isSuicide(WHITE, getCoords(4));
		*/
	}
	
	/** Main method for communication between parent and game engine. */
	public void tryPut(int position, int color) {
		int [] cords = getCoords(position);
		
		//is empty
		if(!isEmpty(cords)) {
			noEmpty();
			return;
		}
		
		//is kill
		if(isKill(cords, color)) {
			kill();
			return;
		}
		
		//is suicide
		if(!isSuicide(cords, color)) {
			suicide();
			return;
		}
		
		printBoard();
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
	private boolean isEmpty(int[] coords) {
		if(board[coords[1]][coords[0]] == EMPTY)
			return true;
		else
			return false;
	}
	
	/** Checks if move is suicide. */
	private boolean isSuicide(int[]coords, int color) {
		int[][] temp = board;
		putPawn(temp, color, coords);
		return hasBreaths(getTerritory(temp, coords));
	}
	
	/** Checks if move kills enemy. */
	private boolean isKill(int[]coords, int color) {
		int enemyColor = ((color == WHITE) ? BLACK : WHITE);
		int x = coords[0];
		int y = coords[1];
		int[][] temp = board;
		currentKilled.clear();
		
		if(		isKilled(temp, x-1, y, enemyColor) ||
				isKilled(temp, x+1, y, enemyColor) ||
				isKilled(temp, x, y-1, enemyColor) ||
				isKilled(temp, x, y+1, enemyColor) ) {
			putPawn(board, color, coords);
			return true;
		}
		else
			return false;
	}
	
	/** Checks if area is killed. */
	private boolean isKilled(int [][] tempBoard, int x, int y, int color) {
		if(tempBoard[y][x] == color) {
			currentTeritory = getTerritory(tempBoard, getCoords(x,y));
			if(!hasBreaths(currentTeritory)) {
				killThemAll(currentTeritory);
				return true;
			}
		}
		return false;
	}
	
	/**Removes all pawn from array. */
	private void killThemAll(ArrayList<Integer> territory) {
		for(int i = 0 ; i < territory.size(); i++) {
			removePawn(getCoords(territory.get(i)));
			currentKilled.add(territory.get(i));
		}
	}
	
	/** Puts new pawn into specific field.*/
	private void putPawn(int[][] board, int color, int[] coords) {
		board[coords[1]][coords[0]] = color;
		put();
	}
	
	/** Removes pawn from specific field.*/
	private void removePawn(int[] coords) {
		board[coords[1]][coords[0]] = EMPTY;
	}
	
	/** Checks if territory has breaths. */
	public boolean hasBreaths(ArrayList<Integer> territory) {
		
		for(int i = 0; i < territory.size(); i++)
			if(_hasBreath(getCoords(territory.get(i))))
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
	
	/** Returns the array of territory.*/
	public ArrayList<Integer> getTerritory(int [][] board, int coords[]) {
		int x = coords[0];
		int y = coords[1];
		ArrayList<Integer> territory = new ArrayList<Integer>();
		_getRecursiveTerritory(board, board[y][x], x, y, territory);
		return territory;
	}
	
	/** Recursive adds fields to the array of territory.*/
	private void _getRecursiveTerritory
	(int[][] board, int color, int x, int y, ArrayList<Integer> territory) {
		if(board[y][x] == color && !territory.contains(getPosition(x,y))) {
			territory.add(getPosition(x,y));
			_getRecursiveTerritory(board, color, x-1, y, territory);
			_getRecursiveTerritory(board, color, x+1, y, territory);
			_getRecursiveTerritory(board, color, x, y-1, territory);
			_getRecursiveTerritory(board, color, x, y+1, territory);
		}
	}
	
	/** Returns the coordinates of a specific field number.*/
	public int[] getCoords(int number) {
		int[] coords = new int[2];
		coords[0] = (int)number%size + 1;
		coords[1] = (int)number/size + 1;
		
		if(coords[1] > size)
			System.out.println("[ERROR] stack overflow");
		
		return coords;
	}
	
	/** Returns the coordinates of a specific field number.*/
	public int[] getCoords(int x, int y) {
		return new int[] {x, y};
	}
	
	/** Returns position of a specific field coordinates.*/
	public int getPosition(int[] coords) {
		return (coords[1]-1) * size + (coords[0]-1);
	}
	
	/** Returns position of a specific field coordinates.*/
	public int getPosition(int x, int y) {
		return getPosition(new int[] {x, y});
	}
	
	public static void main(String[] args) {
		//TODO delete main method
		Game game = new Game();
	}
	
	/** Invoked when field isn't empty. This method must be override by parent. */
	public void noEmpty() {
		System.out.println("[GAME] " + NO_EMPTY);
	}
	
	/** Invoked when move is suicide without kills. This method must be override by parent. */
	public void suicide() {
		System.out.println("[GAME] " + SUICIDE);
	}
	
	/** Invoked when move kills enemy. This method must be override by parent. */
	public void kill() {
		System.out.println("[GAME] " + "kill");
	}
	
	/** Invoked when pawn was put. This method must be override by parent. */
	public void put() {
		System.out.println("[GAME] " + "put");
	}
}
