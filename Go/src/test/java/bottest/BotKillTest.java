package bottest;

import static constants.PawnColors.BLACK;
import static constants.PawnColors.WHITE;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import bot.Bot;

public class BotKillTest {

	//@Ignore
	@Test
	public void lookForKillOneFirstTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		bot.putEnemyPawn(12);
		bot.putBotPawn(7);
		bot.putBotPawn(11);
		bot.putBotPawn(17);
		assertEquals(bot.lookForKill(), 13);
	}
	
	//@Ignore
	@Test
	public void lookForKillOneSecondTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		bot.putEnemyPawn(0);
		bot.putBotPawn(1);
		assertEquals(bot.lookForKill(), 5);
	}
	
	//@Ignore
	@Test
	public void lookForKillNotTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		bot.putEnemyPawn(12);
		bot.putBotPawn(11);
		bot.putBotPawn(17);
		assertEquals(bot.lookForKill(), -1);
	}
	
	//@Ignore
	@Test
	public void lookForKillManyFirstTest() {
		int size = 6;
		int realSize = 8;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init black
		bot.putBotPawn(2);
		bot.putBotPawn(7);
		bot.putBotPawn(9);
		bot.putBotPawn(10);
		
		//init white
		bot.putEnemyPawn(1);
		bot.putEnemyPawn(3);
		bot.putEnemyPawn(4);
		
		assertEquals(bot.lookForKill(), 5);
	}
	
	//@Ignore
	@Test
	public void lookForKillManySecondTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init black
		bot.putEnemyPawn(5);
		bot.putEnemyPawn(10);
		bot.putEnemyPawn(11);
		bot.putEnemyPawn(13);
		bot.putEnemyPawn(17);
		bot.putEnemyPawn(22);
		
		//init white
		bot.putBotPawn(6);
		bot.putBotPawn(12);
		bot.putBotPawn(15);
		bot.putBotPawn(16);
		bot.putBotPawn(18);
		bot.putBotPawn(23);
		
		assertEquals(bot.lookForKill(), 0);
	}
}
