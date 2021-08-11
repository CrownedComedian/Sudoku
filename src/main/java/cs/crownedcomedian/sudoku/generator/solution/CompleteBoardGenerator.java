package cs.crownedcomedian.sudoku.generator.solution;

import cs.crownedcomedian.sudoku.Difficulty;
import cs.crownedcomedian.sudoku.GameBoard;

/**
 * Interface for strategy classes.
 */
public interface CompleteBoardGenerator {
    public GameBoard generateNew();

    public GameBoard generateNew(long seed);
    public GameBoard generateNew(int root);

    public GameBoard generateNew(long seed, int root);
}
