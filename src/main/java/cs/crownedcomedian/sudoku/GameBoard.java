package cs.crownedcomedian.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a Sudoku Gameboard made of a square grid of Box objects, each holding SingleSquare values.
 * The Gameboard object also holds a GameValidator object to validate new guess input passed through the setValue method.
 */
public class GameBoard extends SquareGrid<Box> {
    private final GameValidator validator = new GameValidator(this);

    /**
     * Creates a new instance with a default size of a 9*9 board.
     */
    public GameBoard() {
        super();
    }

    /**
     * Creates a new instance with a size of root^2.
     *
     * @param root the square root size of the desired board.
     */
    public GameBoard(int root) {
        super(root);
    }
    
    @Override
    protected Box fillCell() {
        return new Box(SQROOT);
    }

    /**
     * Returns an ImmutableSquare object holding the same value as the Cell in the specified row and column of the Gameboard.
     * Cannot be used to modify the Gameboard.
     *
     * @param row the desired row of the value.
     * @param col the desired coulmn of the value.
     *
     * @return an ImmutableSquare copy of the value.
     */
    public ImmutableSquare getSquare(int row, int col) {
        return new ImmutableSquare(getCell(row/SQROOT, col/SQROOT).getCell(row%SQROOT, col%SQROOT).getValue());
    }

    /**
     * Attempts to set the value of the Cell at (row, col) to value.
     *
     * @param row the row #, starting at index 0.
     * @param col the col #, starting at index 0.
     * @param value the desired input value.
     *
     * @return true if this is a valid integer for the rules of sudoku.
     *
     * @throws IndexOutOfBoundsException if value is a negative number or greater than the Gameboard root^2.
     * @throws UnsupportedOperationException if the SingleSquare object at (row, col) is already an ImmutableSquare.
     */
    public SquareInputResponse setValue(int row, int col, int value) throws IndexOutOfBoundsException, UnsupportedOperationException {
        if(value < 0 || value > (SQROOT*SQROOT)) {
            throw new IndexOutOfBoundsException("input value is out of bounds!");
        }

        SquareInputResponse response = validator.validateInput(row, col, value);

        if(response.success) {
            SquareValue val = getCell(row/SQROOT, col/SQROOT).getCell(row%SQROOT, col%SQROOT);

            if(val instanceof SingleSquare) {
                SingleSquare ss = (SingleSquare) val;
                ss.setValue(value);
            } else {
                throw new UnsupportedOperationException("Not a mutable square");
            }
        }

        return response;
    }

    /**
     * Returns a List of ImmutableSquare objects representing the values of the Cells of rowNum of the Gameboard.
     * Cannot be used to modify the Gameboard.
     *
     * @param rowNum the desired row to fetch.
     *
     * @return a List of ImmutableSquare objects holding the same values of the Cells of the specified rowNum of the Gameboard.
     *
     * @throws IndexOutOfBoundsException if rowNum is a negative number or greater than the GameBoard root^2.
     */
    public List<ImmutableSquare> getRow(int rowNum) throws IndexOutOfBoundsException {
        if(rowNum < 0 || rowNum > (SQROOT*SQROOT)) {
            throw new IndexOutOfBoundsException("input value is out of bounds!");
        }

        ArrayList<ImmutableSquare> row = new ArrayList<>();

        for(int i = 0; i < SQROOT*SQROOT; i+=SQROOT) { //0, 3, 6
            Box b = getBox(rowNum, i);

            for(int j = 0; j < SQROOT; j++) {
                row.add(new ImmutableSquare(b.getCell(rowNum%SQROOT, j).getValue()));
            }
        }

        return row;
    }

    /**
     * Returns a List of ImmutableSquare objects representing the values of the Cells of colNum of the Gameboard.
     * Cannot be used to modify the Gameboard.
     *
     * @param colNum the desired row to fetch.
     *
     * @return a List of ImmutableSquare objects holding the same values of the Cells of the specified colNum of the Gameboard.
     *
     * @throws IndexOutOfBoundsException if colNum is a negative number or greater than the Gameboard root^2.
     */
    public List<ImmutableSquare> getCol(int colNum) throws IndexOutOfBoundsException {
        if(colNum < 0 || colNum > (SQROOT*SQROOT)) {
            throw new IndexOutOfBoundsException("input value is out of bounds!");
        }

        ArrayList<ImmutableSquare> col = new ArrayList<>();

        for(int i = 0; i < SQROOT*SQROOT; i+=SQROOT) { //0, 3, 6
            Box b = getBox(i, colNum);

            for(int j = 0; j < SQROOT; j++) {
                col.add(new ImmutableSquare(b.getCell(j, colNum%SQROOT).getValue()));
            }
        }

        return col;
    }

    /**
     * Returns an immutable deep copy of the Box of the Gameboard holding the desired row and col intersection.
     * Cannot be used to modify the Gameboard.
     *
     * @param row the row number of the SingleSquare objects on the Gameboard.
     * @param col the column number of the SingleSquare objects on the Gameboard.
     *
     * @return
     *
     * @throws IndexOutOfBoundsException if either row or col is a negative number or greater than the Gameboard root^2.
     */
    public Box getBox(int row, int col) throws IndexOutOfBoundsException {
        if(row < 0 || row > (SQROOT*SQROOT) || col < 0 || col > (SQROOT*SQROOT)) {
            throw new IndexOutOfBoundsException("input value is out of bounds!");
        }

        Box base = getCell(row/SQROOT, col/SQROOT);

        return base.makeImmutableCopy();
    }

    /**
     * Returns a Set of Cells representing all of the SingleSquare locations on the Gameboard with the specified num.
     * Cannot be used to modify the Gameboard.
     *
     * @param num the desired value to search for.
     *
     * @return a Set of Cells holding the (row, col) location of all Cells in the Gameboard that hold the same value as num.
     *
     * @throws IndexOutOfBoundsException if num is a negative number or greater than the Gameboard root^2.
     */
    public Set<Cell> getAllof(int num) throws IndexOutOfBoundsException {
        if(num < 0 || num > (SQROOT*SQROOT)) {
            throw new IndexOutOfBoundsException("input value is out of bounds!");
        }

    	Set<Cell> cells = new HashSet<>();
    	
    	for(int i = 0; i < SQROOT*SQROOT; i++) {
    		for(int j = 0; j < SQROOT*SQROOT; j++) {
    			
        		if(getSquare(i, j).getValue() == num) {
        			cells.add(new Cell(i, j));
        		}
        	}
    	}
    	
    	return cells;
    }

    /**
     * Checks to see if the board is completely filled, and thus solved.
     *
     * @return true if the board is filled.
     */
    public boolean isSolved() {
        for(int row = 0; row < (SQROOT*SQROOT); row++) {
            for(int col = 0; col < (SQROOT*SQROOT); col++) {

                if(getSquare(row, col).getValue() == 0) {
                    return false;
                }
            }
        }

        return true;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < SQROOT*SQROOT; i++) {
    		for(int j = 0; j < SQROOT*SQROOT; j++) {
    	    	sb.append(getSquare(i, j).getValue() + " ");
        	}
    		
    		sb.deleteCharAt(sb.length() -1); //remove trailing whitespace space
    		sb.append('\n');
    	}
    	
    	return sb.toString();
    }
    
    public String toString(int drawStyle ) {
		return null;
    }
}
