package bottest;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import bot.Bot;
import game.Game;

import static constants.PawnColors.*;

public class BotTest {

	//@Ignore
	@Test
	public void testSetColorWhite() {
		Bot bot = new Bot();
		bot.setColor(WHITE);
		assertEquals(bot.getColor(), WHITE);
		assertEquals(bot.getEnemyColor(), BLACK);
	}
	
	//@Ignore
	@Test
	public void testSetColorBlack() {
		Bot bot = new Bot();
		bot.setColor(BLACK);
		assertEquals(bot.getColor(), BLACK);
		assertEquals(bot.getEnemyColor(), WHITE);
	}
	
	//@Ignore
	@Test
	public void testPutBotPawnBlack() {
		Bot bot = new Bot();
		bot.initBot(5);
		bot.setColor(WHITE);
		bot.putBotPawn(15);
		bot.putEnemyPawn(20);
		assertEquals(bot.getBotField(15), WHITE);
		assertEquals(bot.getBotField(20), BLACK);
	}
	
	//@Ignore
	@Test
	public void testPutBotPawnWhite() {
		Bot bot = new Bot();
		bot.initBot(5);
		bot.setColor(BLACK);
		bot.putBotPawn(15);
		bot.putEnemyPawn(20);
		assertEquals(bot.getBotField(15), BLACK);
		assertEquals(bot.getBotField(20), WHITE);
	}
	
	//@Ignore
	@Test
	public void testInitBot() {
		int size = 9;
		int realSize = 11;
		Bot bot = new Bot();
		bot.initBot(size);
		
		for(int i = 0; i < realSize; i++)
		for(int j = 0; j < realSize; j++)
			if(i == 0 || j == 0 || i == realSize-1 || j == realSize-1)
				assertEquals(bot.getBotField(j,i), BORDER);
			else
				assertEquals(bot.getBotField(j, i), EMPTY);
	}

}
