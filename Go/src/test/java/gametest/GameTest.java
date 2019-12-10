package gametest;

import static org.junit.Assert.*;

import org.junit.Ignore;

import game.Game;
import static constants.PawnColors.*;
import static constants.Statuses.*;

import org.junit.Test;

public class GameTest {

	@Ignore
	@Test
	public void testGetSize() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.getSize(), size);
	}
	
	@Ignore
	@Test
	public void testGetRealSize() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.getRealSize(), size+2);
	}
	
	@Ignore
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

	@Ignore
	@Test
	public void testPut() {
		int size = 5;
		int position = 10;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.tryPut(position, WHITE), STATUS_PUT);
	}
	
	@Ignore
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
	
	@Ignore
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
	
	@Ignore
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
	
	@Ignore
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
	
	@Ignore
	@Test
	public void testKillOne() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(6, BLACK);
		
		assertEquals(game.tryPut(1, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(5, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(7, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(11, WHITE), STATUS_KILL);
	}
	
	@Ignore
	@Test
	public void testKillManyFirst() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(11, BLACK);
		game.tryPut(12, BLACK);
		game.tryPut(17, BLACK);

		assertEquals(game.tryPut(6, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(7, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(10, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(13, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(16, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(18, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(22, WHITE), STATUS_KILL);
	}
	
	@Test
	public void testKillManySecond() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);

		//black init
		assertEquals(game.tryPut(2, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(6, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(8, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(10, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(14, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(16, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(18, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(22, BLACK), STATUS_PUT);
		
		//black kill
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		assertEquals(game.getField(11), EMPTY);
		assertEquals(game.getField(7), EMPTY);
		assertEquals(game.getField(13), EMPTY);
		assertEquals(game.getField(17), EMPTY);
	}
	
	@Ignore
	@Test
	public void testKoInitStatus() {
		int size = 5;
		int position = 10;
		Game game = new Game();
		game.initBoard(size);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	
	@Ignore
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
	
	@Ignore
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
	
}
