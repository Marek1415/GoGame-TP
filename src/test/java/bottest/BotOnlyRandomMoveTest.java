package bottest;

import static constants.PawnColors.BLACK;
import static constants.Statuses.*;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import bot.Bot;

public class BotOnlyRandomMoveTest {

	//@Ignore
	@Test
	public void randomCannotTest() {
		int size = 5;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init enemy
		for(int i = 0; i < size*size; i ++)
			bot.putEnemyPawn(i);
		
		assertEquals(bot.makeBotRandomMove(), STATUS_CANT);
	}
	
	//@Ignore
	@Test
	public void randomSuicideTest() {
		int size = 5;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init enemy
		for(int i = 0; i < size*size; i ++)
			bot.putEnemyPawn(i);
		bot.removePawn(0);
		
		assertEquals(bot.makeBotRandomMove(), STATUS_CANT);
	}
	
	//@Ignore
	@Test
	public void randomCanTest() {
		int size = 5;
		int move;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init enemy
		for(int i = 0; i < size*size; i ++)
			bot.putEnemyPawn(i);
		bot.removePawn(0);
		bot.removePawn(1);
		 
		for(int i = 0; i < 100; i ++) {
			move = bot.makeBotRandomMove();
			if(move == 0 || move == 1)
				assertTrue(true);
			else
				assertTrue(false);
		}
	}
	
	//@Ignore
	@Test
	public void randomManyTest() {
		int size = 5;
		int move;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		//init enemy
		for(int i = 0; i < size*size; i ++)
			bot.putEnemyPawn(i);
		bot.removePawn(0);
		bot.removePawn(1);
		bot.removePawn(5);
		bot.removePawn(6);
		bot.removePawn(23);
		bot.removePawn(24);
		
		for(int i = 0; i < 100; i ++) {
			move = bot.makeBotRandomMove();
			System.out.println(move);
			if(move == 0 || move == 1 || move == 5 || move == 6 || move == 23 || move == 24)
				assertTrue(true);
			else
				assertTrue(false);
		}
	}
	
	//@Ignore
	@Test
	public void randomCoverageTest() {
		int size = 5;
		int [] sizes = new int [size*size];
		int move;
		Bot bot = new Bot();
		bot.initBot(size);
		bot.setColor(BLACK);
		
		
		for(int i = 0; i < 1000; i ++) {
			move = bot.makeBotRandomMove();
			sizes[move] = 1;
		}
		
		
		for(int i = 0; i < size*size; i ++)
			if(sizes[i] == 0)
				System.out.println("not move here: " + i);
	}
}
