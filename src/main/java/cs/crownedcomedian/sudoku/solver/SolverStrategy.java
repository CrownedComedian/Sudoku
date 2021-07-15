package cs.crownedcomedian.sudoku.solver;

import cs.crownedcomedian.sudoku.GameBoard;

public interface SolverStrategy {
	public void solve(GameBoard board);
}
