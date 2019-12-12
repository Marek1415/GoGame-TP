package gametest;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import java.util.Random;
import static game.Engine.*;
import static constants.PawnColors.*;

public class EngineTest {

	//@Ignore
	@Test
	public void testGetRealSize() {
		int size = 5;
		assertEquals(getRealSize(size), 5+2);
	}
	
	//@Ignore
	@Test
	public void testGetBoardCopy() {
		
		//init board
		int realSize = 10;
		int board[][] = new int[realSize][realSize];
		
		//randomize board
		for(int i = 0; i < realSize; i++)
		for(int j = 0; j < realSize; j++)
			board[i][j] = new Random().nextInt();
		
		//copy board
		int temp[][] = getBoardCopy(realSize, board);
		
		assertNotEquals(board, temp);
		for(int i = 0; i < realSize; i++)
		for(int j = 0; j < realSize; j++)
			assertEquals(board[i][j], temp[i][j]);
	}
	
	//@Ignore
	@Test
	public void testGetCoordsFirst() {
		int size = 5;
		int position = 15;
		int coords[] = getCoords(size, position);
		
		assertEquals(coords[0], 1);
		assertEquals(coords[1], 4);
	}
	
	//@Ignore
	@Test
	public void testGetCoordsSecond() {
		int size = 5;
		int x = 4;
		int y = 7;
		int coords[] = getCoords(size, x, y);
		
		assertEquals(coords[0], x);
		assertEquals(coords[1], y);
	}
	
	//@Ignore
	@Test
	public void testGetPositionFirst() {
		int size = 5;
		int x = 4;
		int y = 2;
		
		assertEquals(getPosition(size, x, y), 8);
	}
	
	//@Ignore
	@Test
	public void testGetPositionSecond() {
		int size = 5;
		int [] coords = new int[] {5, 4};
		
		assertEquals(getPosition(size, coords), 19);
	}
	
	//@Ignore
	@Test
	public void testPutPawnFirst() {
		int size = 7;
		int color = WHITE;
		int position = 15;
		int board[][] = new int[size][size];
		
		putPawn(size, board, position, color);
		assertEquals(getField(size, board, position), color);
	}
	
	//@Ignore
	@Test
	public void testPutPawnSecond() {
		int size = 7;
		int color = WHITE;
		int x = 4;
		int y = 6;
		int board[][] = new int[size][size];
		
		putPawn(board, x, y, color);
		assertEquals(getField(board, x, y), color);
	}
	
	//@Ignore
	@Test
	public void testRemovePawnFirst() {
		int size = 7;
		int color = WHITE;
		int position = 15;
		int board[][] = new int[size][size];
		
		putPawn(size, board, position, color);
		assertEquals(getField(size, board, position), color);
		removePawn(size, board, position);
		assertEquals(getField(size, board, position), EMPTY);
	}
	
	//@Ignore
	@Test
	public void testRemovePawnSecond() {
		int size = 7;
		int color = BLACK;
		int x = 2;
		int y = 3;
		int board[][] = new int[size][size];
		
		putPawn(board, x, y, color);
		assertEquals(getField(board, x, y), color);
		removePawn(board, x, y);
		assertEquals(getField(board, x, y), EMPTY);
	}
	
	//@Ignore
	@Test
	public void testInitBorders() {
		int realSize = 10;
		int board[][] = new int[10][10];
		initBorders(realSize, board);
		
		for(int i = 0; i < realSize; i ++)
		for(int j = 0; j < realSize; j ++)
			if(i ==0 || j == 0 || i == realSize-1 || j == realSize-1)
				assertEquals(getField(board, j, i), BORDER);
			else
				assertEquals(getField(board, j, i), EMPTY);
	}
}
