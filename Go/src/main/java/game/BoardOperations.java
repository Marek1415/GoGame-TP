package game;

import static constants.PawnColors.BORDER;
import static constants.PawnColors.EMPTY;
import static constants.Statuses.STATUS_OVERRANGE;
import static game.BoardOperations.getCoords;

import java.util.ArrayList;

/** Board static operations needed to game engine. */ 
public class BoardOperations {

	/** Prints board in the console. */
	public static void printBoard(int realSize, int [][] board) {
		for (int i = 1; i < realSize - 1; i++) {
			for (int j = 1; j < realSize - 1; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}
	
	/** Prints real board in the console. */
	public static void printRealBoard(int realSize, int [][] board) {
		for (int i = 0; i < realSize; i++) {
			for (int j = 0; j < realSize; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}
	
	/** Sets the borders. */
	public static void initBorders(int realSize, int [][] board) {
		for (int i = 0; i < realSize; i++)
			for (int j = 0; j < realSize; j++)
				if (i == 0 | j == 0 | i == realSize - 1 | j == realSize - 1)
					board[i][j] = BORDER;
				else
					board[i][j] = EMPTY;
	}
	
	/** Checks if territory has breaths. */
	public static boolean hasBreaths(int size, int[][] board, ArrayList<Integer> territory) {

		for (int i = 0; i < territory.size(); i++)
			if (hasBreath(board, getCoords(size, territory.get(i))))
				return true;
		return false;
	}
	
	/** Check if single field has breaths. */
	private static boolean hasBreath(int[][] board, int coords[]) {
		int x = coords[0];
		int y = coords[1];

		if (board[y][x - 1] * board[y][x + 1] * board[y - 1][x] * board[y + 1][x] == 0)
			return true;
		else
			return false;
	}
	
	/** Returns the array of territory. */
	public static ArrayList<Integer> getTerritory(int size, int[][] newBoard, int coords[]) {
		int x = coords[0];
		int y = coords[1];
		ArrayList<Integer> territory = new ArrayList<Integer>();
		_getRecursiveTerritory(size, newBoard, newBoard[y][x], x, y, territory);
		return territory;
	}

	/** Recursive adds fields to the array of territory. */
	private static void _getRecursiveTerritory(int size, int[][] newBoard, int color, int x, int y, ArrayList<Integer> territory) {
		if (newBoard[y][x] == color && !territory.contains(getPosition(size, x, y))) {
			territory.add(getPosition(size, x, y));
			_getRecursiveTerritory(size, newBoard, color, x - 1, y, territory);
			_getRecursiveTerritory(size, newBoard, color, x + 1, y, territory);
			_getRecursiveTerritory(size, newBoard, color, x, y - 1, territory);
			_getRecursiveTerritory(size, newBoard, color, x, y + 1, territory);
		}
	}
	
	/** Removes all pawn from array. */
	public static void killThemAll(int size, int [][] board, ArrayList<Integer> territory) {
		for (int i = 0; i < territory.size(); i++) {
			removePawn(board, getCoords(size, territory.get(i)));
		}
	}
	
	/** Removes pawn from specific field. */
	public static void removePawn(int board[][], int[] coords) {
		board[coords[1]][coords[0]] = EMPTY;
	}
	
	/** Puts pawn in a specific board field. */
	public static void putPawn(int [][] board, int x, int y, int color) {
		board[y][x] = color;
	}
	
	/** Puts pawn in a specific board field. */
	public static void putPawn(int size, int [][] board, int position, int color) {
		int [] coords = getCoords(size, position);
		board[coords[1]][coords[0]] = color;
	}
	
	/** Returns the value of specific field. */
	public static int getField(int size, int [][] board, int position) {
		int[] coords = getCoords(size, position);
		return board[coords[1]][coords[0]];
	}
	
	/** Returns the value of specific field. */
	public static int getField(int [][] board, int x, int y) {
		return board[y][x];
	}
	
	/** Returns the coordinates of a specific field position. */
	public static int[] getCoords(int size, int position) {
		int[] coords = new int[2];
		coords[0] = (int) position % size + 1;
		coords[1] = (int) position / size + 1;

		if (position < 0 || position >= size*size)
			coords[0] = STATUS_OVERRANGE;
		
		return coords;
	}
	
	/** Returns the coordinates of a specific field number. */
	public static int[] getCoords(int size, int x, int y) {
		return new int[] { x, y };
	}
	
	/** Returns position of a specific field coordinates. */
	public static int getPosition(int size, int[] coords) {
		return (coords[1] - 1) * size + (coords[0] - 1);
	}
	
	/** Returns position of a specific field coordinates. */
	public static int getPosition(int size, int x, int y) {
		return getPosition(size, new int[] { x, y });
	}
	
	/** Return copy of current board. */
	public static int[][] getBoardCopy(int realSize, int[][] board) {
		int[][] temp = new int[realSize][realSize];
		for (int i = 0; i < realSize; i++)
			for (int j = 0; j < realSize; j++)
				temp[i][j] = board[i][j];
		return temp;
	}
	
	/** Returns value of real size. */
	public static int getRealSize(int size) {
		return size + 2;
	}
}
