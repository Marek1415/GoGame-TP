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
	
	/** Initialize game board. */
	public void initBot(int newSize) {
		this.size = newSize;
		this.realSize = getRealSize(size);
		board = new int[realSize][realSize];
		initBorders(realSize, board);
		//lastKo = STATUS_KOINIT;
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
	
	/** Performs the best bot move. */
	public int makeBotMove() {
		
		//help
		
		//kill
		int killPosition = lookForKill();
		if(killPosition != -1)
			return killPosition;
		
		//zmienic to
		return 0;
		
	}
	
	/** Searches the board to kill as many enemy pawns as possible. */
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
					killPosition = getPosition(size, j, i);
				}
			}
		}
		return killPosition;
	}
	
	/** Tries to put pawn and looks for amount of kills. */
	private int tryKill(int x, int y) {
		
		int kills = 0;
		temp = getBoardCopy(realSize, board);
		putPawn(temp, x, y, color);

		kills += trySingleKill(temp, x-1,y);
		kills += trySingleKill(temp, x+1,y);
		kills += trySingleKill(temp, x,y-1);
		kills += trySingleKill(temp, x,y+1);

		return kills;
	}
	
	/** Returns amount of current killed field. */
	public int trySingleKill(int [][] temp, int x, int y) {
		if (temp[y][x] == enemyColor) {
			ArrayList<Integer> territory = getTerritory(size, temp, x, y);
			if (!hasBreaths(size, temp, territory)) {
				killThemAll(size, temp, territory);
				return territory.size();
			}
		}
		return 0;
	}
	
	/** Searches the board to safe as many onw pawns as possible. */
	private void lookForHelp() {
		
		int safePosition = -1;
		int safeAmount = 0;
		int tempSafeAmount;
		/*
		for(int i = 1; i < realSize-1; i++)
		for(int j = 1; j < realSize-1; j++) {
			if(board[i][j] == EMPTY) {
				tempSafeAmount = trySafe(j, i);
				if(tempKillAmount > killAmount) {
					killAmount = tempKillAmount;
					killPosition = getPosition(size, j, i);
				}
			}
		}
		return killPosition;
		*/
	}
	
	/** Puts enemy pawn in a specific field. */
	public void putEnemyPawn(int position) {
		putPawn(size, board, position, enemyColor);
	}
	
	/** Puts bot pawn in a specific field. */
	public void putBotPawn(int position) {
		putPawn(size, board, position, color);
	}
	
	/** Returns the value of specific field. */
	public int getBotField(int position) {
		return getField(size, board, position);
	}
	
	/** Returns the value of specific field. */
	public int getBotField(int x, int y) {
		return getField(board, x, y);
	}
	
	/** Returns bot color. */
	public int getColor() {
		return this.color;
	}
	
	/** Returns bot's enemy color. */
	public int getEnemyColor() {
		return this.enemyColor;
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		new Bot();
	}
}
