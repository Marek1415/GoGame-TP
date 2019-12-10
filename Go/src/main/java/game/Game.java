package game;

import server.*;

import static constants.PawnColors.*;
import static constants.Messages.*;
import static constants.Statuses.*;

import java.util.ArrayList;

/**
 * Engine for GO game.
 */
public class Game {

	// variables
	ServerThread player1, player2;
	int board[][];
	int size;
	int realSize;
	int lastKo;
	ArrayList<Integer> currentKilled = new ArrayList<Integer>();
	ArrayList<Integer> currentTerritory = new ArrayList<Integer>();

	public Game() {
		/*
		initBoard(5);

		tryPut(1, BLACK);
		tryPut(5, BLACK);
		tryPut(7, BLACK);
		tryPut(11, BLACK);
		
		tryPut(2, WHITE);
		tryPut(8, WHITE);
		tryPut(12, WHITE);
		tryPut(6, WHITE);
		
		tryPut(7, BLACK);
		*/
		
	}

	/** Main method for communication between parent and game engine. */
	public int tryPut(int position, int color) {
		
		int[] cords = getCoords(position);

		//is over range
		if(cords[0] == STATUS_OVERRANGE) {
			return STATUS_OVERRANGE;
		}
		
		// is empty
		if (!isEmpty(cords)) {
			noEmpty();
			printBoard();
			return STATUS_NOEMPTY;
		}
		
		//is ko
		if(isKo(cords)) {
			ko();
			printBoard();
			return STATUS_KO;
		}

		// is kill
		if (isKill(cords, color)) {
			prepareKill();
			printBoard();
			return STATUS_KILL;
		}

		// is suicide
		if (isSuicide(cords, color)) {
			suicide();
			printBoard();
			return STATUS_SUICIDE;
		}

		putPawn(board, color, cords);
		lastKo = STATUS_KOINIT;
		put();
		printBoard();
		return STATUS_PUT;
	}

	/** Initialize game board. */
	public void initBoard(int newSize) {
		this.size = newSize;
		this.realSize = newSize + 2;
		board = new int[size + 2][size + 2];
		initBorders();
		lastKo = STATUS_KOINIT;
	}

	/** Sets the borders. */
	public void initBorders() {
		for (int i = 0; i < realSize; i++)
			for (int j = 0; j < realSize; j++)
				if (i == 0 | j == 0 | i == realSize - 1 | j == realSize - 1)
					board[i][j] = BORDER;
				else
					board[i][j] = EMPTY;
	}

	/** Prints board in the console. */
	public void printBoard() {
		for (int i = 1; i < realSize - 1; i++) {
			for (int j = 1; j < realSize - 1; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}

	/** Prints real board in the console. */
	public void printRealBoard() {
		for (int i = 0; i < realSize; i++) {
			for (int j = 0; j < realSize; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}

	/** Checks if specific board field is empty. */
	private boolean isEmpty(int[] coords) {
		if (board[coords[1]][coords[0]] == EMPTY)
			return true;
		else
			return false;
	}
	
	/** Checks if current move is ko move. */
	private boolean isKo(int[] coords) {
		if(lastKo == STATUS_KOINIT)
			return false;
		int [] coordsKo = getCoords(lastKo);
		if(coordsKo[0] == coords[0] && coordsKo[1] == coords[1])
			return true;
		else
			return false;
	}

	/** Checks if move is suicide. */
	private boolean isSuicide(int[] coords, int color) {
		int[][] temp = getBoardCopy();
		putPawn(temp, color, coords);
		return !hasBreaths(temp, getTerritory(temp, coords));
	}

	/** Checks if move kills enemy. */
	private boolean isKill(int[] coords, int color) {
		int enemyColor = ((color == WHITE) ? BLACK : WHITE);
		int x = coords[0];
		int y = coords[1];
		boolean killStatus[] = {false, false, false, false};
		
		int[][] temp = getBoardCopy();
		putPawn(temp, color, coords);
		currentKilled.clear();
		
		killStatus[0] = isKilled(temp, x + 1, y, enemyColor);
		killStatus[1] = isKilled(temp, x - 1, y, enemyColor);
		killStatus[2] = isKilled(temp, x, y - 1, enemyColor);
		killStatus[3] = isKilled(temp, x, y + 1, enemyColor);
		
		if (killStatus[0] || killStatus[1] || killStatus[2] || killStatus[3]) {
			putPawn(board, color, coords);
			return true;
		}
		else {
			return false;
		}
	}

	/** Checks if area is killed. */
	private boolean isKilled(int[][] tempBoard, int x, int y, int enemyColor) {
		if (tempBoard[y][x] == enemyColor) {
			currentTerritory = getTerritory(tempBoard, getCoords(x, y));
			if (!hasBreaths(tempBoard, currentTerritory)) {
				killThemAll(currentTerritory);
				return true;
			}
		}
		return false;
	}

	/** Removes all pawn from array. */
	private void killThemAll(ArrayList<Integer> territory) {
		for (int i = 0; i < territory.size(); i++) {
			removePawn(getCoords(territory.get(i)));
			currentKilled.add(territory.get(i));
		}
	}

	/** Puts new pawn into specific field. */
	private void putPawn(int[][] newBoard, int color, int[] coords) {
		newBoard[coords[1]][coords[0]] = color;
	}

	/** Removes pawn from specific field. */
	private void removePawn(int[] coords) {
		board[coords[1]][coords[0]] = EMPTY;
	}

	/** Checks if territory has breaths. */
	public boolean hasBreaths(int[][] board, ArrayList<Integer> territory) {

		for (int i = 0; i < territory.size(); i++)
			if (hasBreath(board, getCoords(territory.get(i))))
				return true;
		return false;
	}

	/** Check if single field has breaths. */
	private boolean hasBreath(int[][] board, int coords[]) {
		int x = coords[0];
		int y = coords[1];

		if (board[y][x - 1] * board[y][x + 1] * board[y - 1][x] * board[y + 1][x] == 0)
			return true;
		else
			return false;

	}

	/** Returns the array of territory. */
	public ArrayList<Integer> getTerritory(int[][] newBoard, int coords[]) {
		int x = coords[0];
		int y = coords[1];
		ArrayList<Integer> territory = new ArrayList<Integer>();
		_getRecursiveTerritory(newBoard, newBoard[y][x], x, y, territory);
		return territory;
	}

	/** Recursive adds fields to the array of territory. */
	private void _getRecursiveTerritory(int[][] newBoard, int color, int x, int y, ArrayList<Integer> territory) {
		if (newBoard[y][x] == color && !territory.contains(getPosition(x, y))) {
			territory.add(getPosition(x, y));
			_getRecursiveTerritory(newBoard, color, x - 1, y, territory);
			_getRecursiveTerritory(newBoard, color, x + 1, y, territory);
			_getRecursiveTerritory(newBoard, color, x, y - 1, territory);
			_getRecursiveTerritory(newBoard, color, x, y + 1, territory);
		}
	}

	/** Returns the coordinates of a specific field number. */
	public int[] getCoords(int number) {
		int[] coords = new int[2];
		coords[0] = (int) number % size + 1;
		coords[1] = (int) number / size + 1;

		if (number < 0 || number >= size*size)
			coords[0] = STATUS_OVERRANGE;
		
		return coords;
	}

	/** Returns the coordinates of a specific field number. */
	public int[] getCoords(int x, int y) {
		return new int[] { x, y };
	}

	/** Returns position of a specific field coordinates. */
	public int getPosition(int[] coords) {
		return (coords[1] - 1) * size + (coords[0] - 1);
	}

	/** Returns position of a specific field coordinates. */
	public int getPosition(int x, int y) {
		return getPosition(new int[] { x, y });
	}
	
	/** Prepares array of killed pawns. */
	public void prepareKill() {
		int [] killed = new int[currentKilled.size()];
		
		//copy array
		for(int i = 0; i < currentKilled.size(); i++)
			killed[i] = currentKilled.get(i);
		
		//make ko
		if(currentKilled.size() == 1)
			lastKo = currentKilled.get(0);
		
		//invoke kill method
		kill(killed);
	}

	public static void main(String[] args) {
		// TODO delete main method
		Game game = new Game();
	}

	/** Invoked when field isn't empty. This method must be override by parent. */
	public void noEmpty() {
		System.out.println("[GAME] " + NO_EMPTY);
	}

	/**
	 * Invoked when move is suicide without kills. This method must be override by
	 * parent.
	 */
	public void suicide() {
		System.out.println("[GAME] " + SUICIDE);
	}
	
	/** Invoked when move is ko. */
	public void ko() {
		System.out.println("[GAME] " + KO);
	}

	/** Invoked when move kills enemy. This method must be override by parent. */
	public void kill(int [] killed) {
		System.out.println("[GAME] " + "killing");
		for(int i = 0; i < currentKilled.size(); i++)
			System.out.println("killed " + i + " : " + currentKilled.get(i));
	}

	/** Invoked when pawn was put. This method must be override by parent. */
	public void put() {
		System.out.println("[GAME] " + "putting");
	}

	public int[][] getBoardCopy() {
		int[][] temp = new int[realSize][realSize];
		for (int i = 0; i < realSize; i++)
			for (int j = 0; j < realSize; j++)
				temp[i][j] = board[i][j];
		return temp;
	}
	
	/** Returns current board size. */
	public int getSize() {
		return this.size;
	}
	
	/** Returns current board real size. */
	public int getRealSize() {
		return this.realSize;
	}
	
	/** Returns current status of specific field. */
	public int getField(int position) {
		int[] coords = getCoords(position);
		return board[coords[1]][coords[0]];
	}
	
	/** Return the value of specific field. */
	public int getField(int x, int y) {
		return board[y][x];
	}
	
	/** Returns the status of current ko. */
	public int getKo() {
		return this.lastKo;
	}
}
