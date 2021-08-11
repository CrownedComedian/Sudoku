package cs.crownedcomedian.sudoku;

import cs.crownedcomedian.sudoku.generator.solution.recursive.BasicRecursiveStrategy;

/**
 * Classification used for stand-alone execution entry.
 * Not of use to applications that use the sudoku module as a part of a whole.
 */
public class Sudoku {

    public static void main(String[] args) {
        GameBoard gb = new BasicRecursiveStrategy().generateNew();
//        gb.setValue(0, 0, 2);
//        gb.setValue(1, 2, 2);
//        gb.setValue(1, 2, 4);
//        gb.setValue(2, 2, 7);
//        gb.setValue(0, 0, 0);
//        SquareInputResponse res = gb.setValue(7, 1, 5);
        System.out.println(gb.toString());
    }
    
    /**
     *
     * @return true if the game is solvable;
     */
    public static boolean solveGame() {
    	

        return false;
    }
}