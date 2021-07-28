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
        final int size = board.SQROOT*board.SQROOT;

        if(squareNum >= size*size) {
            return true;
        }

        ArrayList<Integer> possibleValues = new ArrayList<Integer>();
        for(int i = 1; i <= size; i++) {
            possibleValues.add(i);
        }

        while(possibleValues.size() != 0) {
            int input = rand.nextInt(possibleValues.size());
            SquareInputResponse response = board.setValue(squareNum/size, squareNum%size, possibleValues.get(input));
            possibleValues.remove(input);
            
            if(response.success == true) {
                if(completeSquare(board, ++squareNum)) { return true; }
            }
        }

        return false;
    }
}
