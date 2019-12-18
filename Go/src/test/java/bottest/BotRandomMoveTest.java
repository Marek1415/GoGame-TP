package bottest;

import static constants.PawnColors.BLACK;
import static org.junit.Assert.*;
import static constants.Statuses.STATUS_CANT;

import org.junit.Ignore;
import org.junit.Test;

import bot.Bot;

public class BotRandomMoveTest {

	@Ignore
	@Test
	public void loorForRandomNotTestFirst() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		for(int i = 0; i < size*size; i++)
			bot.putEnemyPawn(i);
		assertEquals(bot.lookForRandom(), STATUS_CANT);
	}
	
	@Ignore
	@Test
	public void loorForRandomNotTestSecond() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		for(int i = 0; i < size*size; i++)
			bot.putEnemyPawn(i);
		
		bot.removePawn(0);
		bot.removePawn(6);
		bot.removePawn(12);
		assertEquals(bot.lookForRandom(), STATUS_CANT);
	}
	
	@Ignore
	@Test
	public void loorForRandomTestFirst() {
		int size = 5;
		int realSize = 7;
		int move;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		for(int i = 0; i < size*size; i++)
			bot.putEnemyPawn(i);
		
		bot.removePawn(0);
		bot.removePawn(1);
		bot.removePawn(2);
		
		for(int i = 0; i < 100; i ++) {
			move = bot.lookForRandom();
			if(move == 0 || move == 1 || move == 2)
				assertTrue(true);
			else
				assertTrue(false);
		}
	}
	
	@Ignore
	@Test
	public void loorForRandomTestSecond() {
		int size = 5;
		int realSize = 7;
		int move;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		for(int i = 0; i < size*size; i++)
			bot.putEnemyPawn(i);
		
		bot.removePawn(0);
		bot.removePawn(1);
		bot.removePawn(10);
		bot.removePawn(11);
		bot.removePawn(24);
		
		for(int i = 0; i < 100; i ++) {
			move = bot.lookForRandom();
			if(move == 0 || move == 1 || move == 10 || move == 11)
				assertTrue(true);
			else
				assertTrue(false);
		}
	}
	
	//@Ignore
	@Test
	public void loorForRandomFullBoardTest() {
		int size = 5;
		int move;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		for(int i = 0; i < size*size; i++)
			bot.putBotPawn(i);
		
		for(int i = 0; i < 100; i ++) {
			System.out.println(bot.makeBotMove());
		}
	}
}
