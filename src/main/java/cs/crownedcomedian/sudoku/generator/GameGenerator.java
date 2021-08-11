package cs.crownedcomedian.sudoku.generator;

import cs.crownedcomedian.sudoku.Difficulty;
import cs.crownedcomedian.sudoku.GameBoard;

public interface GameGenerator {
    public GameBoard generateNew();
    /*

     */

    public GameBoard generateNew(long seed);
    public GameBoard generateNew(Difficulty desiredDifficulty);
    public GameBoard generateNew(int root);

    public GameBoard generateNew(long seed, Difficulty desiredDifficulty);
    public GameBoard generateNew(long seed, int root);
    public GameBoard generateNew(Difficulty desiredDifficulty, int root);

    public GameBoard generateNew(long seed, Difficulty desiredDifficulty, int root);

    public Difficulty getCalculatedDifficulty();
}
