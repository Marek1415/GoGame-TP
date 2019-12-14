package bottest;

import static constants.PawnColors.BLACK;
import static constants.Statuses.STATUS_CANT;
import static org.junit.Assert.*;

import org.junit.Ignore;

import static game.Engine.*;
import org.junit.Test;

import bot.Bot;

public class BotOpportunityTest {

	//@Ignore
	@Test
	public void lookForOpportunitySingleTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init enemy
		bot.putEnemyPawn(0);
	
		for(int i = 0; i < 100; i ++) {
			int position = bot.lookForOpportunity();
			if(position == 1 || position == 5)
				assertTrue(true);
			else
				assertTrue(false);
		}
	}
	
	//@Ignore
	@Test
	public void lookForNoOpportunityTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		assertEquals(bot.lookForOpportunity(), STATUS_CANT);
	}
	
	//@Ignore
	@Test
	public void lookForOpportunityManyTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init enemy
		bot.putEnemyPawn(0);
		bot.putEnemyPawn(1);
		bot.putEnemyPawn(5);
	
		for(int i = 0; i < 100; i ++) {
			int position = bot.lookForOpportunity();
			if(position == 2 || position == 6 || position == 10)
				assertTrue(true);
			else
				assertTrue(false);
		}
	}
	
	//@Ignore
	@Test
	public void lookForOpportunitySuicideTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init enemy
		bot.putEnemyPawn(1);
		bot.putEnemyPawn(5);
		bot.putBotPawn(2);
		bot.putBotPawn(6);
		bot.putBotPawn(10);
	
		for(int i = 0; i < 100; i ++)
			assertEquals(bot.lookForOpportunity(), STATUS_CANT);
	}
	
	//@Ignore
	@Test
	public void lookForOpportunityChoseTest() {
		int size = 5;
		int realSize = 7;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init enemy
		bot.putEnemyPawn(0);
		bot.putEnemyPawn(13);
		bot.putEnemyPawn(18);
		bot.putEnemyPawn(23);
	
		for(int i = 0; i < 100; i ++) {
			int position = bot.lookForOpportunity();
			if(position == 1 || position == 5)
				assertTrue(true);
			else
				assertTrue(false);
		}
	}
}
