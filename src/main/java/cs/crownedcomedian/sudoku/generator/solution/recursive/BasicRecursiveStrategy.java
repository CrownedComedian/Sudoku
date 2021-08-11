package cs.crownedcomedian.sudoku.generator.solution.recursive;

import cs.crownedcomedian.sudoku.GameBoard;
import cs.crownedcomedian.sudoku.SquareInputResponse;
import cs.crownedcomedian.sudoku.generator.solution.CompleteBoardGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates a completely filled board by recursively attempting to set the next square's value until the whole board is full.
 */
public class BasicRecursiveStrategy implements CompleteBoardGenerator {
    private final Random rand;
    private int root = 3;

    public BasicRecursiveStrategy() { rand = new Random(); }

    public BasicRecursiveStrategy(long seed) { rand = new Random(seed); }

    public BasicRecursiveStrategy(int root) {
        this();
        setRoot(root);
    }

    public BasicRecursiveStrategy(long seed, int root) {
        this(seed);
        setRoot(root);
    }

    public int getRoot() { return root; }

    public void setRoot(int newRoot) { root = newRoot; }

    public void setSeed(long seed) {rand.setSeed(seed); }

    @Override
    public GameBoard generateNew() {
        GameBoard board = new GameBoard(root);
        completeSquare(board,0);

        return board;
    }

    @Override
    public GameBoard generateNew(long seed) {
        setSeed(seed);

        return generateNew();
    }

    @Override
    public GameBoard generateNew(int root) {
        setRoot(root);

        return generateNew();
    }

    @Override
    public GameBoard generateNew(long seed, int root) {
        setSeed(seed);
        setRoot(root);

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

        //TODO - sdfsd

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
