package cs.crownedcomedian.sudoku;

import cs.crownedcomedian.sudoku.generator.CompleteBoardGenerator;

public class Sudoku {

    public static void main(String[] args) {
        GameBoard gb = new CompleteBoardGenerator().generateNew();
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