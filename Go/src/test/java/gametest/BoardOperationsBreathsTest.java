package gametest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import static game.BoardOperations.*;
import static constants.PawnColors.*;

public class BoardOperationsBreathsTest {

	//@Ignore
	@Test
	public void hasBreathTestFirst() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 0, WHITE);
		assertEquals(hasBreath(size, board, 0), true);
	}
	
	//@Ignore
	@Test
	public void hasBreathTestSecond() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 0, WHITE);
		putPawn(size, board, 1, WHITE);
		assertEquals(hasBreath(size, board, 0), true);
	}
	
	//@Ignore
	@Test
	public void hasNotBreathTestFirst() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 0, WHITE);
		putPawn(size, board, 1, WHITE);
		putPawn(size, board, 5, WHITE);
		assertEquals(hasBreath(size, board, 0), false);
	}
	
	//@Ignore
	@Test
	public void hasNotBreathTestSecond() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 12, WHITE);
		putPawn(size, board, 7, BLACK);
		putPawn(size, board, 11, BLACK);
		putPawn(size, board, 13, BLACK);
		putPawn(size, board, 17, BLACK);
		assertEquals(hasBreath(size, board, 12), false);
	}
	
	//@Ignore
	@Test
	public void getTerritoryTestFirst() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 0, WHITE);
		putPawn(size, board, 1, WHITE);

		ArrayList<Integer> territory = getTerritory(size, board, 1, 1);
		
		assertEquals(territory.size(), 2);
		assertEquals(territory.contains(0), true);
		assertEquals(territory.contains(1), true);
	}
	
	//@Ignore
	@Test
	public void getTerritoryTestSecond() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 20, BLACK);
		putPawn(size, board, 21, BLACK);
		putPawn(size, board, 16, BLACK);
		putPawn(size, board, 17, BLACK);
		putPawn(size, board, 12, BLACK);
		putPawn(size, board, 13, BLACK);
		putPawn(size, board, 8, BLACK);
		putPawn(size, board, 9, BLACK);
		putPawn(size, board, 4, BLACK);

		ArrayList<Integer> territory = getTerritory(size, board, 20);
		
		assertEquals(territory.size(), 9);
		assertEquals(territory.contains(20), true);
		assertEquals(territory.contains(21), true);
		assertEquals(territory.contains(16), true);
		assertEquals(territory.contains(17), true);
		assertEquals(territory.contains(12), true);
		assertEquals(territory.contains(13), true);
		assertEquals(territory.contains(8), true);
		assertEquals(territory.contains(9), true);
		assertEquals(territory.contains(4), true);
	}
	
	//@Ignore
	@Test
	public void hasBreathsTestFirst() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 0, WHITE);
		putPawn(size, board, 1, WHITE);
		putPawn(size, board, 2, WHITE);
		putPawn(size, board, 3, WHITE);
		putPawn(size, board, 4, WHITE);
		putPawn(size, board, 5, BLACK);
		putPawn(size, board, 6, BLACK);
		putPawn(size, board, 7, BLACK);
		putPawn(size, board, 8, BLACK);
		
		ArrayList<Integer> whities = getTerritory(size, board, 0);
		
		assertEquals(hasBreaths(size, board, whities), true);
	}
	
	//@Ignore
	@Test
	public void hasNoBreathsTestFirst() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 0, WHITE);
		putPawn(size, board, 1, WHITE);
		putPawn(size, board, 2, WHITE);
		putPawn(size, board, 3, WHITE);
		putPawn(size, board, 4, WHITE);
		putPawn(size, board, 5, BLACK);
		putPawn(size, board, 6, BLACK);
		putPawn(size, board, 7, BLACK);
		putPawn(size, board, 8, BLACK);
		putPawn(size, board, 9, BLACK);
		
		ArrayList<Integer> whities = getTerritory(size, board, 0);
		
		assertEquals(hasBreaths(size, board, whities), false);
	}
	
	//@Ignore
	@Test
	public void hasBreathsTestSecond() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 0, BLACK);
		putPawn(size, board, 1, BLACK);
		putPawn(size, board, 2, BLACK);
		putPawn(size, board, 7, BLACK);
		putPawn(size, board, 12, BLACK);
		putPawn(size, board, 17, BLACK);
		putPawn(size, board, 16, BLACK);
		putPawn(size, board, 15, BLACK);
		
		putPawn(size, board, 5, WHITE);
		putPawn(size, board, 6, WHITE);
		putPawn(size, board, 10, WHITE);
		
		
		ArrayList<Integer> whities = getTerritory(size, board, 1, 2);
		
		assertEquals(hasBreaths(size, board, whities), true);
	}
	
	//@Ignore
	@Test
	public void hasNoBreathsTestSecond() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 0, BLACK);
		putPawn(size, board, 1, BLACK);
		putPawn(size, board, 2, BLACK);
		putPawn(size, board, 7, BLACK);
		putPawn(size, board, 12, BLACK);
		putPawn(size, board, 17, BLACK);
		putPawn(size, board, 16, BLACK);
		putPawn(size, board, 15, BLACK);
		
		putPawn(size, board, 5, WHITE);
		putPawn(size, board, 6, WHITE);
		putPawn(size, board, 10, WHITE);
		putPawn(size, board, 11, WHITE);
		
		
		ArrayList<Integer> whities = getTerritory(size, board, 1, 2);
		
		assertEquals(hasBreaths(size, board, whities), false);
	}

	//@Ignore
	@Test
	public void killThemAllTest() {
		int size = 5;
		int realSize = getRealSize(size);
		int board[][] = new int[realSize][realSize];
		initBorders(realSize, board);
		
		putPawn(size, board, 20, BLACK);
		putPawn(size, board, 21, BLACK);
		putPawn(size, board, 16, BLACK);
		putPawn(size, board, 17, BLACK);
		putPawn(size, board, 12, BLACK);
		putPawn(size, board, 13, BLACK);
		putPawn(size, board, 8, BLACK);
		putPawn(size, board, 9, BLACK);
		putPawn(size, board, 4, BLACK);
		
		assertEquals(getField(size, board, 20), BLACK);
		assertEquals(getField(size, board, 21), BLACK);
		assertEquals(getField(size, board, 16), BLACK);
		assertEquals(getField(size, board, 17), BLACK);
		assertEquals(getField(size, board, 12), BLACK);
		assertEquals(getField(size, board, 13), BLACK);
		assertEquals(getField(size, board, 8), BLACK);
		assertEquals(getField(size, board, 9), BLACK);
		assertEquals(getField(size, board, 4), BLACK);
		
		ArrayList<Integer> whities = getTerritory(size, board, 20);
		killThemAll(size, board, whities);
		
		assertEquals(getField(size, board, 20), EMPTY);
		assertEquals(getField(size, board, 21), EMPTY);
		assertEquals(getField(size, board, 16), EMPTY);
		assertEquals(getField(size, board, 17), EMPTY);
		assertEquals(getField(size, board, 12), EMPTY);
		assertEquals(getField(size, board, 13), EMPTY);
		assertEquals(getField(size, board, 8), EMPTY);
		assertEquals(getField(size, board, 9), EMPTY);
		assertEquals(getField(size, board, 4), EMPTY);	
	}
}
