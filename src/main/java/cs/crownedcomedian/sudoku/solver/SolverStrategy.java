package cs.crownedcomedian.sudoku.solver;

import cs.crownedcomedian.sudoku.Difficulty;
import cs.crownedcomedian.sudoku.GameBoard;

/**
 * Interface for strategy classes.
 */
public interface SolverStrategy {
	public Difficulty solve(GameBoard board);
}
