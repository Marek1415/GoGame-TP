package boardtest;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import static constants_panels.BoardPanelConstants.*;

public class LengthTest {

	//@Ignore
	@Test
	public void getLengthTest() {
		
		final int length = LENGTH_BOARD;
		
		for(int size = 2; size < 100; size++) {
			int realLength = Math.round(length/size) * size;
			//System.out.println("SIZE: " + size);
			//System.out.println("\treal length: " + realLength);
			//System.out.println("\treal length/size: " + (double)realLength/size);
			assertEquals(realLength, getLength(size));
		}
	}
	
	//@Ignore
	@Test
	public void getLengthDeltaTest() {
		
		final int length = LENGTH_BOARD;
		int deltaMax = 0;
		int deltaTemp;
		
		for(int size = 2; size < length; size++) {
			int realLength = Math.round(length/size) * size;
			deltaTemp = Math.abs(length - realLength);
			deltaMax = (deltaMax < deltaTemp) ? deltaTemp : deltaMax;
			System.out.println("SIZE: " + size);
			System.out.println("\tdelta: " + deltaTemp);
		}
		System.out.println("deltaMax: " + deltaMax);
	}

}
