package gametest;

import static constants.PawnColors.*;
import static constants.Statuses.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import game.Game;

public class GameKillTest {

	//@Ignore
	@Test
	public void testKillLeft() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(12, BLACK);
		
		assertEquals(game.tryPut(7, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(11, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(13, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(17, WHITE), STATUS_KILL);
		assertEquals(game.getKo(), 12);
		assertEquals(game.getField(12), EMPTY);
	}
	
	//@Ignore
	@Test
	public void testKillUpper() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(12, BLACK);
		
		assertEquals(game.tryPut(11, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(13, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(17, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(7, WHITE), STATUS_KILL);
		assertEquals(game.getKo(), 12);
		assertEquals(game.getField(12), EMPTY);
	}
	
	//@Ignore
	@Test
	public void testKillBottom() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(12, BLACK);
		
		assertEquals(game.tryPut(11, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(13, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(7, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(17, WHITE), STATUS_KILL);
		assertEquals(game.getKo(), 12);
		assertEquals(game.getField(12), EMPTY);
	}
	
	//@Ignore
	@Test
	public void testKillRight() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		game.tryPut(12, BLACK);
		
		assertEquals(game.tryPut(11, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(7, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(17, WHITE), STATUS_PUT);
		assertEquals(game.tryPut(13, WHITE), STATUS_KILL);
		assertEquals(game.getKo(), 12);
		assertEquals(game.getField(12), EMPTY);
	}
	
	//@Ignore
	@Test
	public void testKillLeftUpper() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		assertEquals(game.tryPut(10, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(2, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(7), EMPTY);
		assertEquals(game.getField(11), EMPTY);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKillLeftBottom() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		assertEquals(game.tryPut(10, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(22, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(17), EMPTY);
		assertEquals(game.getField(11), EMPTY);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKillRightUpper() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		assertEquals(game.tryPut(14, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(2, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(7), EMPTY);
		assertEquals(game.getField(13), EMPTY);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKillRightBottom() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		assertEquals(game.tryPut(14, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(22, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(17), EMPTY);
		assertEquals(game.getField(13), EMPTY);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKillEverySide() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		assertEquals(game.tryPut(2, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(10, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(14, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(22, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(7), EMPTY);
		assertEquals(game.getField(11), EMPTY);
		assertEquals(game.getField(13), EMPTY);
		assertEquals(game.getField(17), EMPTY);
		assertEquals(game.getField(12), BLACK);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKillEveryNoUpper() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		//assertEquals(game.tryPut(2, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(10, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(14, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(22, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(7), WHITE);
		assertEquals(game.getField(11), EMPTY);
		assertEquals(game.getField(13), EMPTY);
		assertEquals(game.getField(17), EMPTY);
		assertEquals(game.getField(12), BLACK);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKillNoLeft() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		assertEquals(game.tryPut(2, BLACK), STATUS_PUT);
		//assertEquals(game.tryPut(10, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(14, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(22, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(7), EMPTY);
		assertEquals(game.getField(11), WHITE);
		assertEquals(game.getField(13), EMPTY);
		assertEquals(game.getField(17), EMPTY);
		assertEquals(game.getField(12), BLACK);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKillNoRight() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		assertEquals(game.tryPut(2, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(10, BLACK), STATUS_PUT);
		//assertEquals(game.tryPut(14, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(22, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(7), EMPTY);
		assertEquals(game.getField(11), EMPTY);
		assertEquals(game.getField(13), WHITE);
		assertEquals(game.getField(17), EMPTY);
		assertEquals(game.getField(12), BLACK);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
	@Test
	public void testKillNoBottom() {
		int size = 5;
		Game game = new Game();
		game.initBoard(size);
		
		//black init
		game.tryPut(6, BLACK);
		game.tryPut(8, BLACK);
		game.tryPut(16, BLACK);
		game.tryPut(18, BLACK);
		
		//white init
		game.tryPut(7, WHITE);
		game.tryPut(11, WHITE);
		game.tryPut(13, WHITE);
		game.tryPut(17, WHITE);
		
		//killing
		assertEquals(game.tryPut(2, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(10, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(14, BLACK), STATUS_PUT);
		//assertEquals(game.tryPut(22, BLACK), STATUS_PUT);
		assertEquals(game.tryPut(12, BLACK), STATUS_KILL);
		
		//in empty
		assertEquals(game.getField(7), EMPTY);
		assertEquals(game.getField(11), EMPTY);
		assertEquals(game.getField(13), EMPTY);
		assertEquals(game.getField(17), WHITE);
		assertEquals(game.getField(12), BLACK);
		assertEquals(game.getKo(), STATUS_KOINIT);
	}
	
	//@Ignore
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
}
