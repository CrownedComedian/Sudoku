package cs.crownedcomedian.sudoku.generator.chisel;

import cs.crownedcomedian.sudoku.Difficulty;
import cs.crownedcomedian.sudoku.GameBoard;
import cs.crownedcomedian.sudoku.generator.GameGenerator;
import cs.crownedcomedian.sudoku.generator.solution.recursive.BasicRecursiveStrategy;

import java.util.Random;

public class ChiselStrategy implements GameGenerator {
	private Difficulty difficulty;

	@Override
	public GameBoard generateNew() {
		return generateNew(Difficulty.MEDIUM);
	}

	@Override
	public GameBoard generateNew(long seed) {
		return null;
	}

	@Override
	public GameBoard generateNew(Difficulty desiredDifficulty) {
		GameBoard gb = new BasicRecursiveStrategy().generateNew();
		Random rand = new Random();

		for(int i = 0; i < 15; i++) {
			gb.setValue(rand.nextInt(gb.SQROOT*gb.SQROOT), rand.nextInt(gb.SQROOT*gb.SQROOT), 0);
		}

		return gb;
	}

	@Override
	public GameBoard generateNew(int root) {
		return null;
	}

	@Override
	public GameBoard generateNew(long seed, Difficulty desiredDifficulty) {
		return null;
	}

	@Override
	public GameBoard generateNew(long seed, int root) {
		return null;
	}

	@Override
	public GameBoard generateNew(Difficulty desiredDifficulty, int root) {
		return null;
	}

	@Override
	public GameBoard generateNew(long seed, Difficulty desiredDifficulty, int root) {
		return null;
	}

	@Override
	public Difficulty getCalculatedDifficulty() {
		return difficulty;
	}
}
