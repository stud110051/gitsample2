package bollengame;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class bollenggame {
	Game bg = null;
	private void rollMany(int n,int pins) {
		for(int i=0;i<n;i++) {
			bg.roll(pins);
		}
	}
	@BeforeEach
	public void before() {
		bg=new Game();
	}

	@AfterEach
	public void after() {
		bg=null;
	}
	
	@Test
	public void testGutter() {
		rollMany(20,0);
//		for (int i = 0; i < 20; i++) {
//			bg.roll(0);
//		}
		assertEquals(0, bg.score());
	}

	@Test
	public void testAllone() {
		rollMany(20,1);

		assertEquals(20, bg.score());
	}
	@Test
	public void testonespace() {
		rollSpace();
		bg.roll(3);
		rollMany(17,0);

		assertEquals(16, bg.score());
	}
	@Test
	public void testonestrick() {
		
		bg.roll(10);
		bg.roll(3);
		bg.roll(4);
		rollMany(16,0);

		assertEquals(24, bg.score());
	}
	@Test
	public void allstrick() {
		
		
		rollMany(12,10);

		assertEquals(300, bg.score());
	}
	private void rollSpace() {
		bg.roll(5);
		bg.roll(5);
	}
	private void rollStrick() {
		bg.roll(10);
	}
	
	
	
	class Game {
		//private int score = 0;

		private int rolls[] = new int [21];
		private int currentRoll = 0;
		
		public void roll(int pins) {
//			score += pins;
			rolls[currentRoll++]=pins;
		}

		public int score() {
			int score = 0;
			int frameindex=0;
			for(int frame=0;frame<10;frame++) {
				if(isStrick(frameindex)) {
					score +=10+strickBonus(frameindex);
					frameindex+=1;
				}		
				else if(isSpare(frameindex)) {
					score += 10 +spaceBonus(frameindex);
					frameindex+=2;
				}else {
					score +=sumofballsIndexfeame(frameindex);
					frameindex+=2;
				}
				
				
			}
			
			return score;
		}
		private int sumofballsIndexfeame(int frameindex) {
			return rolls[frameindex]+rolls[frameindex+1];
		}
		private int spaceBonus(int frameindex) {
			return rolls[frameindex+2];
		}
		private int strickBonus(int frameindex) {
			return rolls[frameindex+1]+rolls[frameindex+2];
		}
		private boolean isSpare(int frameindex) {
			return rolls[frameindex]+rolls[frameindex+1]==10;
		}
		private boolean isStrick(int frameindex) {
			return rolls[frameindex]==10;
		}
	}
}
