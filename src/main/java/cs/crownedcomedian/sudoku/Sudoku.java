package cs.crownedcomedian.sudoku;

import cs.crownedcomedian.sudoku.generator.ChistleStrategy;
import cs.crownedcomedian.sudoku.generator.CompleteBoardGenerator;

public class Sudoku {

    public static void main(String[] args) {
        GameBoard gb = new ChistleStrategy().generateNew();//CompleteBoardGenerator().generateNew();
//        gb.setValue(3, 0, 5);
//        gb.setValue(0, 2, 5);
//        gb.setValue(1, 2, 4);
//        gb.setValue(2, 2, 7);
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