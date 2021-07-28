package cs.crownedcomedian.sudoku.generator;

import cs.crownedcomedian.sudoku.GameBoard;

import java.util.Random;

public class ChistleStrategy implements GameGenerator {

	@Override
	public GameBoard generateNew() {
		GameBoard gb = new CompleteBoardGenerator().generateNew();
		Random rand = new Random();

		for(int i = 0; i < 15; i++) {
			gb.setValue(rand.nextInt(gb.SQROOT*gb.SQROOT), rand.nextInt(gb.SQROOT*gb.SQROOT), 0);
		}

		return gb;
	}
}
