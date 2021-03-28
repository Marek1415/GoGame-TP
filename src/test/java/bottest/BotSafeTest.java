package bottest;

import static constants.PawnColors.BLACK;
import static constants.PawnColors.WHITE;
import static constants.Statuses.STATUS_CANT;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import bot.Bot;

public class BotSafeTest {

	//@Ignore
	@Test
	public void lookForSafeNotTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(WHITE);
		
		bot.putBotPawn(12);
		bot.putEnemyPawn(7);
		bot.putEnemyPawn(11);
		
		assertEquals(bot.lookForSafe(), STATUS_CANT);
	}
	
	//@Ignore
	@Test
	public void lookForSafeFirstTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(WHITE);
		
		bot.putBotPawn(12);
		bot.putEnemyPawn(7);
		bot.putEnemyPawn(11);
		bot.putEnemyPawn(17);
		
		assertEquals(bot.lookForSafe(), 13);
	}
	
	//@Ignore
	@Test
	public void lookForSafeSecondTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(WHITE);
		
		//init bot
		bot.putBotPawn(12);
		bot.putBotPawn(13);
		bot.putBotPawn(14);
		
		//init enemy
		bot.putEnemyPawn(7);
		bot.putEnemyPawn(8);
		bot.putEnemyPawn(9);
		bot.putEnemyPawn(11);
		bot.putEnemyPawn(17);
		bot.putEnemyPawn(18);
		
		assertEquals(bot.lookForSafe(), 19);
	}
	
	//@Ignore
	@Test
	public void lookForSafeManyTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init bot
		bot.putBotPawn(7);
		bot.putBotPawn(10);
		bot.putBotPawn(11);
		bot.putBotPawn(13);
		bot.putBotPawn(14);
		bot.putBotPawn(15);
		
		//init enemy
		bot.putEnemyPawn(5);
		bot.putEnemyPawn(6);
		bot.putEnemyPawn(8);
		bot.putEnemyPawn(12);
		bot.putEnemyPawn(16);
		bot.putEnemyPawn(18);
		bot.putEnemyPawn(19);
		
		assertEquals(bot.lookForSafe(), 20);
	}
	
	@Test
	public void lookForSafeSucideTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init bot
		bot.putBotPawn(7);
		
		//init enemy
		bot.putEnemyPawn(2);
		bot.putEnemyPawn(3);
		bot.putEnemyPawn(12);
		bot.putEnemyPawn(13);
		bot.putEnemyPawn(6);
		bot.putEnemyPawn(9);
		
		assertEquals(bot.lookForSafe(), STATUS_CANT);
		
		
	}

}
