package bot;

import static constants.PawnColors.*;
import static game.Engine.*;

import java.util.ArrayList;

import game.Engine;
/**
 * @author gumises
 * Bot class performs simple moves to kill enemy and safe own life.
 */
public class Bot {

	//bot variables
	private int color;
	private int enemyColor;
	
	//board variables
	private int size;
	private int realSize;
	private int board[][];
	private int temp[][];
	
	public Bot() {
		initBot(5);
		setColor(WHITE);
		printBoard(realSize, board);
		printRealBoard(realSize, board);
		System.out.println("color" + getColor());
		lookForKill();
	}
	
	/** Sets bot color. */
	public void setColor(int newColor) {
		if(newColor == WHITE) {
			this.color = WHITE;
			this.enemyColor = BLACK;
		}
		else {
			this.color = BLACK;
			this.enemyColor = WHITE;
		}
	}
	
	/** Returns bot color. */
	public int getColor() {
		return this.color;
	}
	
	/** Returns bot's enemy color. */
	public int getEnemyColor() {
		return this.enemyColor;
	}
	
	/** Initialize game board. */
	public void initBot(int newSize) {
		this.size = newSize;
		this.realSize = getRealSize(size);
		board = new int[realSize][realSize];
		initBorders(realSize, board);
		//lastKo = STATUS_KOINIT;
	}
	
	/** Searches the board to kill as many enemies as possible. */
	private int lookForKill() {
		
		int killPosition = -1;
		int killAmount = 0;
		int tempKillAmount;
		
		for(int i = 1; i < realSize-1; i++)
		for(int j = 1; j < realSize-1; j++) {
			if(board[i][j] == EMPTY) {
				tempKillAmount = tryKill(j, i);
				if(tempKillAmount > killAmount) {
					killAmount = tempKillAmount;
					killPosition = getPosition(j, i, size);
				}
			}
		}
		
		return killPosition;
	}
	
	/** Tries to put pawn and looks for amount of kills. */
	private int tryKill(int x, int y) {
		
		int kills = 0;
		temp = getBoardCopy(realSize, board);
		//putPawnTemp(temp, x, y);
		/*
		kills += trySingleKill(temp, x-1,y);
		kills += trySingleKill(temp, x+1,y);
		kills += trySingleKill(temp, x,y-1);
		kills += trySingleKill(temp, x,y+1);
		*/
		return kills;
	}
	
	/** Returns amount of current killed field. */
	/*
	public int trySingleKill(int [][] tempBoard, int x, int y) {
		if (tempBoard[y][x] == enemyColor) {
			ArrayList<Integer> currentTerritory = getTerritory(size, tempBoard, getCoords(x, y));
			if (!hasBreaths(size, tempBoard, currentTerritory)) {
				killThemAll(size, tempBoard, currentTerritory);
				return currentTerritory.size();
			}
		}
		return 0;
	}
	*/
	
	/** Searches the board to prevent an opponent kill. */
	private void lookForHelp() {
		
	}
	
	/** Puts pawn in a specific field. */
	public void putPawn(int position, int color) {
		Engine.putPawn(size, board, position, color);
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		new Bot();
	}
}
