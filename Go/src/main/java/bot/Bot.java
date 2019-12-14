package bot;

import static constants.PawnColors.*;
import static game.Engine.*;
import static constants.Statuses.STATUS_KOINIT;

import java.util.ArrayList;
import java.util.Random;

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
	private int lastKo;
	
	//arrays
	private ArrayList<Integer> breathPositionsTemp;
	
	//random
	private Random random;
	
	public Bot() {
		/*
		initBot(5);
		setColor(WHITE);
		lastKo = STATUS_KOINIT;
		printBoard(realSize, board);
		printRealBoard(realSize, board);
		System.out.println("color" + getColor());
		lookForOpportunity();
		*/
	}
	
	/** Initialize game board. */
	public void initBot(int newSize) {
		this.size = newSize;
		this.realSize = getRealSize(size);
		board = new int[realSize][realSize];
		initBorders(realSize, board);
		random = new Random();
		lastKo = STATUS_KOINIT;
		breathPositionsTemp = new ArrayList<Integer>();
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
		
		//gets last ko position
		getLastKo();
		int move;
		
		//help
		move = lookForSafe();
		if(move != -1)
			return move;
		
		//kill
		move = lookForKill();
		if(move != -1)
			return move;
		
		//kill opportunity
		move = lookForOpportunity();
		if(move != -1)
			return move;
		
		//random move
		//zmienic to
		return 0;
		
	}
	
	/** Searches the board for opportunity to kill enemy in the future. */
	public int lookForOpportunity() {
		
		//variables
		int opportunityPosition = -1;
		
		int breaths = Integer.MAX_VALUE;
		int breathsTemp;
		
		for(int i = 1; i < realSize-1; i++)
		for(int j = 1; j < realSize-1; j++) {
			if(board[i][j] == enemyColor) {
				
				breathsTemp = tryOpportune(j, i);
				if(breathPositionsTemp.size() > 0 && breathsTemp < breaths) {
					breaths = breathsTemp;
					opportunityPosition = getRandomPosition(breathPositionsTemp);
				}
			}
		}
		
		return opportunityPosition;
	}
	
	/** Tries to put pawn and looks for amount of breaths. */
	private int tryOpportune(int x, int y) {
		
		ArrayList<Integer> breaths;
		breaths = getBreaths(size, board, getTerritory(size, board, x, y));
		
		int amountOfBreaths = breaths.size();
		for(int i = amountOfBreaths-1; i >= 0; i--)
			if(breaths.get(i) == lastKo || isSuicide(size, board, breaths.get(i), color))
				breaths.remove(i);
		
		breathPositionsTemp = new ArrayList<Integer>(breaths);
		return amountOfBreaths;
	}
	
	/** Returns random value from array. */
	public int getRandomPosition(ArrayList<Integer> positions) {
		int index = random.nextInt(positions.size());
		return positions.get(index);
	}
	
	/** Searches the board to kill as many enemy pawns as possible. */
	public int lookForKill() {
		
		int killPosition = -1;
		int killAmount = 0;
		int tempKillAmount;
		int tempKillPosition;
		
		for(int i = 1; i < realSize-1; i++)
		for(int j = 1; j < realSize-1; j++) {
			if(board[i][j] == EMPTY) {
				
				tempKillAmount = tryKill(j, i);
				tempKillPosition = getPosition(size, j, i);
				
				if(tempKillAmount > killAmount && tempKillPosition != lastKo) {
					killAmount = tempKillAmount;
					killPosition = tempKillPosition;
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
	
	/** Searches the board to safe as many own pawns as possible. */
	public int lookForSafe() {
		
		int safePosition = -1;
		int safeAmount = 0;
		int tempSafeAmount;
		
		for(int i = 1; i < realSize-1; i++)
		for(int j = 1; j < realSize-1; j++) {
			if(board[i][j] == EMPTY) {
				tempSafeAmount = trySafe(j, i);
				if(tempSafeAmount > safeAmount) {
					safeAmount = tempSafeAmount;
					safePosition = getPosition(size, j, i);
				}
			}
		}
		return safePosition;
	}
	
	/** Tries to put pawn and looks for amount of safes. */
	private int trySafe(int x, int y) {
		
		int safes = 0;
		temp = getBoardCopy(realSize, board);
		putPawn(temp, x, y, enemyColor);

		safes += trySingleSafe(temp, x-1,y);
		safes += trySingleSafe(temp, x+1,y);
		safes += trySingleSafe(temp, x,y-1);
		safes += trySingleSafe(temp, x,y+1);

		return safes;
	}
	
	/** Returns amount of current saved field. */
	public int trySingleSafe(int [][] temp, int x, int y) {
		if (temp[y][x] == color) {
			ArrayList<Integer> territory = getTerritory(size, temp, x, y);
			if (!hasBreaths(size, temp, territory)) {
				killThemAll(size, temp, territory);
				return territory.size();
			}
		}
		return 0;
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
	
	/**Gets last ko position from game. Must be override by parent. */
	public void getLastKo() {
		//TODO override by parent
	}
	
	/** Sets value of last ko. */
	public void setLastKo(int ko) {
		this.lastKo = ko;
	}
	
	/** Prints board in the console. */
	public void printBoard() {
		Engine.printBoard(realSize, board);
	}
	
	/** Prints real board in the console. */
	public void printRealBoard() {
		Engine.printRealBoard(realSize, board);
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		new Bot();
	}
}
