package gametest;

import static org.junit.Assert.*;

import org.junit.Ignore;

import game.Game;
import static constants.PawnColors.*;
import static constants.Statuses.*;

import org.junit.Test;

public class GameTest {

	//@Ignore
	@Test
	public void testGetSize() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.getSize(), size);
	}
	
	//@Ignore
	@Test
	public void testGetRealSize() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.getRealSize(), size+2);
	}
	
	//@Ignore
	@Test
	public void testInitBoard() {
		int size = 9;
		int realSize;
		Game game = new Game();
		game.initBoard(size);
		realSize = game.getRealSize();
		
		for(int i = 0; i < realSize; i++)
		for(int j = 0; j < realSize; j++)
			if(i == 0 || j == 0 || i == realSize-1 || j == realSize-1)
				assertEquals(game.getField(j,i), BORDER);
			else
				assertEquals(game.getField(j, i), EMPTY);
	}

	//@Ignore
	@Test
	public void testPutWhite() {
		int size = 5;
		int position = 10;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.tryPut(position, WHITE), STATUS_PUT);
		assertEquals(game.getField(position), WHITE);
	}
	
	//@Ignore
	@Test
	public void testPutBlack() {
		int size = 5;
		int position = 1;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.tryPut(position, BLACK), STATUS_PUT);
		assertEquals(game.getField(position), BLACK);
	}
	
	//@Ignore
	@Test
	public void testNoEmpty() {
		int size = 5;
		int position1 = 1;
		int position2 = 2;
		int position3 = 3;
		int position4 = 4;
		
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(position1, WHITE);
		game.tryPut(position2, BLACK);
		game.tryPut(position3, BLACK);
		game.tryPut(position4, WHITE);
		
		assertEquals(game.tryPut(position1, WHITE), STATUS_NOEMPTY);
		assertEquals(game.tryPut(position2, BLACK), STATUS_NOEMPTY);
		assertEquals(game.tryPut(position3, WHITE), STATUS_NOEMPTY);
		assertEquals(game.tryPut(position4, BLACK), STATUS_NOEMPTY);
	}
	
	//@Ignore
	@Test
	public void testKo() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(1, WHITE);
		game.tryPut(5, WHITE);
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		
		game.tryPut(2, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(12, BLACK);
		
		//kill
		assertEquals(game.tryPut(6, BLACK), STATUS_KILL);
		
		//ko
		assertEquals(game.tryPut(7, WHITE), STATUS_KO);
		
		//different move
		assertEquals(game.tryPut(10, WHITE), STATUS_PUT);
		
		//not ko
		assertEquals(game.tryPut(7, WHITE), STATUS_KILL);
		
	}
	
	//@Ignore
	@Test
	public void testSuicideOne() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(1, WHITE);
		game.tryPut(5, WHITE);
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		
		assertEquals(game.tryPut(6, BLACK), STATUS_SUICIDE);	
	}
	
	//@Ignore
	@Test
	public void testSuicideMany() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(6, WHITE);
		game.tryPut(7, WHITE);
		game.tryPut(10, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(16, WHITE);
		game.tryPut(18, WHITE);
		game.tryPut(22, WHITE);

		assertEquals(game.tryPut(11, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(17, BLACK), STATUS_SUICIDE);
	}
	
	//@Ignore
	@Test
	public void testKoInitStatus() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKoStatus() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(1, WHITE);
		game.tryPut(5, WHITE);
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		
		game.tryPut(2, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(12, BLACK);
		assertEquals(game.tryPut(6, BLACK), STATUS_KILL);
		assertEquals(game.getKo(), 7);
	}
	
	//@Ignore
	@Test
	public void testOverRange() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.tryPut(-1, BLACK), STATUS_OVERRANGE);
		assertEquals(game.tryPut(0, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(24, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(25, BLACK), STATUS_OVERRANGE);
		
	}
	
	//@Ignore
	@Test
	public void testPointsFirst() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(0, WHITE);
		game.tryPut(1, WHITE);
		game.tryPut(2, WHITE);
		game.tryPut(3, WHITE);
		game.initTerritory();
		game.clickTerritory(0, BLACK);
		game.clickTerritory(1, BLACK);
		game.clickTerritory(2, BLACK);
		game.clickTerritory(3, BLACK);
		game.clickTerritory(3, BLACK);
		
		assertEquals(game.getTerritoryPoints(BLACK), 3);
		assertEquals(game.getTerritoryPoints(WHITE), 0);
	}
	
	//@Ignore
	@Test
	public void testPointsSecond() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//init black position
		game.tryPut(0, BLACK);
		game.tryPut(1, BLACK);
		game.tryPut(2, BLACK);
		game.tryPut(5, BLACK);
		game.tryPut(6, BLACK);
		game.tryPut(7, BLACK);
		
		//init white position
		game.tryPut(17, WHITE);
		game.tryPut(18, WHITE);
		game.tryPut(19, WHITE);
		game.tryPut(22, WHITE);
		game.tryPut(23, WHITE);
		game.tryPut(24, WHITE);
		
		//step 1
		game.initTerritory();
		assertEquals(game.getTerritoryPoints(BLACK), 0);
		assertEquals(game.getTerritoryPoints(WHITE), 0);
		
		//step 2
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, WHITE);
		game.clickTerritory(1, WHITE);
		game.clickTerritory(2, WHITE);
		game.clickTerritory(17, BLACK);
		game.clickTerritory(17, BLACK);
		game.clickTerritory(19, BLACK);
		game.clickTerritory(22, BLACK);
		game.clickTerritory(23, BLACK);
		game.clickTerritory(24, BLACK);
		assertEquals(game.getTerritoryPoints(BLACK), 4);
		assertEquals(game.getTerritoryPoints(WHITE), 2);
		
		//step 3
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(1, WHITE);
		game.clickTerritory(2, WHITE);
		game.clickTerritory(5, WHITE);
		game.clickTerritory(6, WHITE);
		game.clickTerritory(7, WHITE);
		game.clickTerritory(8, WHITE);
		game.clickTerritory(16, BLACK);
		game.clickTerritory(17, BLACK);
		game.clickTerritory(18, BLACK);
		game.clickTerritory(19, BLACK);
		game.clickTerritory(22, BLACK);
		game.clickTerritory(23, BLACK);
		game.clickTerritory(24, BLACK);
		assertEquals(game.getTerritoryPoints(BLACK), 6);
		assertEquals(game.getTerritoryPoints(WHITE), 6);
	}
	
	//@Ignore
	@Test
	public void testConflictSingle() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		// empty
		game.initTerritory();
		assertEquals(game.getConflicts(), 0);
		
		// white
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		assertEquals(game.getConflicts(), 0);
		
		// black
		game.initTerritory();
		game.clickTerritory(0, BLACK);
		assertEquals(game.getConflicts(), 0);
		
		// white & black
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, BLACK);
		assertEquals(game.getConflicts(), 1);
		
		// white -> white & black
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, BLACK);
		assertEquals(game.getConflicts(), 0);
		
		// black -> black & white
		game.initTerritory();
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, WHITE);
		assertEquals(game.getConflicts(), 0);
		
		// black -> black -> black & white
		game.initTerritory();
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, WHITE);
		assertEquals(game.getConflicts(), 1);
		
		// white -> white -> white & black
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, BLACK);
		assertEquals(game.getConflicts(), 1);
		
		// white -> white -> white & black -> black
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, BLACK);
		assertEquals(game.getConflicts(), 0);
		
		// black -> black -> black & white -> white
		game.initTerritory();
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, WHITE);
		assertEquals(game.getConflicts(), 0);
	}
	
	//@Ignore
	@Test
	public void testConflictMany() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		// step 1
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(1, WHITE);
		game.clickTerritory(1, BLACK);
		game.clickTerritory(2, WHITE);
		game.clickTerritory(2, BLACK);
		assertEquals(game.getConflicts(), 3);
		
		// step 1
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(1, WHITE);
		game.clickTerritory(1, BLACK);
		game.clickTerritory(1, WHITE);
		game.clickTerritory(1, BLACK);
		assertEquals(game.getConflicts(), 1);
		
		// step 1
		game.initTerritory();
		game.clickTerritory(0, WHITE);
		game.clickTerritory(0, BLACK);
		game.clickTerritory(1, WHITE);
		game.clickTerritory(2, BLACK);
		game.clickTerritory(3, WHITE);
		game.clickTerritory(4, BLACK);
		assertEquals(game.getConflicts(), 1);
	}
	
}
