package game;

import server.*;

import static constants.PawnColors.*;
import static constants.Messages.*;
import static constants.Statuses.*;
import static constants.Territories.*;
import static constants.Signals.*;

import java.util.ArrayList;

import constants.PawnColors;

import static game.Engine.*;

/**
 * Engine for GO game.
 */
public class Game {

	// variables
	ServerThread player1, player2;
	int board[][];
	int whities[][];
	int blacks[][];
	int conflicts;
	int size;
	int realSize;
	int lastKo; 
	public ArrayList<Integer> currentKilled = new ArrayList<Integer>();
	ArrayList<Integer> currentTerritory = new ArrayList<Integer>();

	public Game() {
	}

	/** Main method for communication between parent and game engine. */
	public int tryPut(int position, int color) {

		// is over range
		if (isOverRange(position)) {
			return STATUS_OVERRANGE;
		}

		// init coords
		int[] cords = getCoords(size, position);

		// is empty
		if (!isEmpty(cords)) {
			noEmpty();
			//printBoard(realSize, board);
			return STATUS_NOEMPTY;
		}

		// is ko
		if (isKo(cords)) {
			ko();
			//printBoard(realSize, board);
			return STATUS_KO;
		}

		// is kill
		if (isKill(cords, color)) {
			prepareKill();
			//printBoard(realSize, board);
			return STATUS_KILL;
		}

		// is suicide
		if (isSuicide(position, color)) {
			suicide();
			//printBoard(realSize, board);
			return STATUS_SUICIDE;
		}

		putPawn(size, board, position, color);
		lastKo = STATUS_KOINIT;
		put();
		//printBoard(realSize, board);
		return STATUS_PUT;
	}

	/** Initialize game board. */
	public void initBoard(int newSize) {
		this.size = newSize;
		this.realSize = Engine.getRealSize(size);
		board = new int[realSize][realSize];
		initBorders(realSize, board);
		lastKo = STATUS_KOINIT;
	}

	/** Checks if specific board field is empty. */
	private boolean isEmpty(int[] coords) {
		if (board[coords[1]][coords[0]] == PawnColors.EMPTY)
			return true;
		else
			return false;
	}

	/** Checks if current move is ko move. */
	private boolean isKo(int[] coords) {
		if (lastKo == STATUS_KOINIT)
			return false;
		int[] coordsKo = getCoords(size, lastKo);
		if (coordsKo[0] == coords[0] && coordsKo[1] == coords[1])
			return true;
		else
			return false;
	}

	/** Checks if move is suicide. */
	private boolean isSuicide(int position, int color) {
		int[][] temp = getBoardCopy(realSize, board);
		putPawn(size, temp, position, color);
		return !hasBreaths(size, temp, getTerritory(size, temp, position));
	}

	/** Checks if move is over range. */
	private boolean isOverRange(int position) {
		if (position < 0 || position >= size * size)
			return true;
		else
			return false;
	}

	/** Checks if move kills enemy. */
	private boolean isKill(int[] coords, int color) {
		int enemyColor = ((color == WHITE) ? BLACK : WHITE);
		int x = coords[0];
		int y = coords[1];
		boolean killStatus[] = { false, false, false, false };

		int[][] temp = getBoardCopy(realSize, board);
		putPawn(temp, x, y, color);
		currentKilled.clear();

		killStatus[0] = isKilled(temp, x + 1, y, enemyColor);
		killStatus[1] = isKilled(temp, x - 1, y, enemyColor);
		killStatus[2] = isKilled(temp, x, y - 1, enemyColor);
		killStatus[3] = isKilled(temp, x, y + 1, enemyColor);

		if (killStatus[0] || killStatus[1] || killStatus[2] || killStatus[3]) {
			putPawn(board, x, y, color);
			return true;
		} else {
			return false;
		}
	}

	/** Checks if area is killed. */
	private boolean isKilled(int[][] tempBoard, int x, int y, int enemyColor) {
		if (tempBoard[y][x] == enemyColor) {
			currentTerritory = getTerritory(size, tempBoard, x, y);
			if (!hasBreaths(size, tempBoard, currentTerritory)) {
				killThemAll(size, board, currentTerritory);
				currentKilled.addAll(currentTerritory);
				return true;
			}
		}
		return false;
	}

	/** Prepares array of killed pawns. */
	public void prepareKill() {
		int[] killed = new int[currentKilled.size()];

		// copy array
		for (int i = 0; i < currentKilled.size(); i++)
			killed[i] = currentKilled.get(i);

		// make ko
		if (currentKilled.size() == 1)
			lastKo = currentKilled.get(0);

		// invoke kill method
		kill(killed);
	}
	
	/** Invoked when field isn't empty. This method must be override by parent. */
	public void noEmpty() {
		//System.out.println("[GAME] " + NO_EMPTY);
	}

	/** Invoked when move is suicide without kills. sMust be override by parent. */
	public void suicide() {
		//System.out.println("[GAME] " + SUICIDE);
	}

	/** Invoked when move is ko. Must be override by parent. */
	public void ko() {
		//System.out.println("[GAME] " + KO);
	}

	/** Invoked when move kills enemy. Must be override by parent. */
	public void kill(int[] killed) {
		//System.out.println("[GAME] " + "killing");
		for (int i = 0; i < currentKilled.size(); i++)
			//System.out.println("killed " + i + " : " + currentKilled.get(i));
	}

	/** Invoked when pawn was put. Must be override by parent. */
	public void put() {
		//System.out.println("[GAME] " + "putting");
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
		return Engine.getField(size, board, position);
	}

	/** Return the value of specific field. */
	public int getField(int x, int y) {
		return Engine.getField(board, x, y);
	}

	/** Returns the status of current ko. */
	public int getKo() {
		return this.lastKo;
	}
	
	/** Initialize territory. */
	public void initTerritory() {
		this.whities = new int [size][size];
		this.blacks = new int [size][size];
		this.conflicts = 0;
	}
	
	/** Adds conflict in conflicts counter. */
	private synchronized void addConflict() {
		conflicts += 1;
		sendSignal(SE_CONFLICT + " " + 1);
	}
	
	/** Removes conflict from conflicts counter. */
	private synchronized void removeConflict() {
		conflicts -= 1;
		if(conflicts == 0)
			sendSignal(SE_CONFLICT + " " + 0);
	}
	
	/** Switch territory status on specific position and color, returns current status. */
	public synchronized int clickTerritory(int position, int color) {
		
		int x = position%size;
		int y = position/size;
		
		boolean wasConflict = (whities[y][x] + blacks[y][x] == CONFLICT);
		
		//sets new value
		switch(color) {
		case WHITE:
			whities[y][x] = (whities[y][x] == NOBODY) ? TER_WHITE : NOBODY;
			break;
		case BLACK:
			blacks[y][x] = (blacks[y][x] == NOBODY) ? TER_BLACK : NOBODY;
			break;
		}
		
		//sets current value
		int val = whities[y][x] + blacks[y][x];
		
		//returns current value
		if(val == NOBODY) {
			return NOBODY;
		}
		else if(val == CONFLICT) {
			addConflict();
			return CONFLICT;
		}
		else if(val == color) {
			if(wasConflict) 	removeConflict();
			return ME;
		}
		else {
			if(wasConflict) 	removeConflict();
			return ENEMY;
		}
	}
	
	/** Returns amount of points for player specific color. */
	public int getTerritoryPoints(int color) {
		int points = 0;
		
		switch(color) {
		case WHITE:
			for(int i = 0; i < size; i ++)
			for(int j = 0; j < size; j ++)
				if(whities[i][j] == TER_WHITE && board[i+1][j+1] == BLACK)
					points ++;
			break;
			
		case BLACK:
			for(int i = 0; i < size; i ++)
			for(int j = 0; j < size; j ++)
				if(blacks[i][j] == TER_BLACK && board[i+1][j+1] == WHITE)
					points ++;
			break;
		}
		return points;
	}
	
	/** Sends signal, must be override by parent.*/
	public void sendSignal(String signal) {
		
	}
	
	/** Returns current amount of conflicts. */
	public int getConflicts() {
		return this.conflicts;
	}
	
	public static void main(String [] args) {
		Game game = new Game();
	}
}
