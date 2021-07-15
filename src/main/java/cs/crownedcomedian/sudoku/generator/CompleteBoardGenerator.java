package cs.crownedcomedian.sudoku.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import cs.crownedcomedian.sudoku.GameBoard;
import cs.crownedcomedian.sudoku.SquareInputResponse;

public final class CompleteBoardGenerator implements GameGenerator {
    private Random rand;
    
    public CompleteBoardGenerator() {
		rand = new Random();
	}
    
    public CompleteBoardGenerator(long seed) {
		rand = new Random(seed);
	}

    public void setSeed(long newSeed) {
        rand.setSeed(newSeed);
    }
    
    @Override
    public GameBoard generateNew() {
        GameBoard board = new GameBoard();
        completeSquare(board,0);

        return board;
    }
    
    public GameBoard generateNew(long seed) {
    	setSeed(seed);
    	
    	return generateNew();
    }

    private boolean completeSquare(GameBoard board, int squareNum) {
        if(squareNum >= 81) {
            return true;
        }

        ArrayList<Integer> possibleValues = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        while(possibleValues.size() != 0) {
            int input = rand.nextInt(possibleValues.size());
            SquareInputResponse response = board.setValue(squareNum/9, squareNum%9, possibleValues.get(input));
            possibleValues.remove(input);
            
            if(response.success == true) {
                if(completeSquare(board, ++squareNum)) { return true; }
                
            }
        }

        return false;
    }
}
