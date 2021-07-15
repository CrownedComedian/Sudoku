package cs.crownedcomedian.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameBoard extends SquareGrid<Box> {
    private final GameValidator validator = new GameValidator(this);

    public GameBoard() {
        super();
    }
    
    @Override
    protected Box fillCell() {
        return new Box();
    }

    public ImmutableSquare getSquare(int row, int col) {
        return new ImmutableSquare(getCell(row/3, col/3).getCell(row%SQROOT, col%SQROOT).getValue());
    }

    /**
     *
     * @param row the row #, starting at index 0.
     * @param col the col #, starting at index 0.
     * @return true if this is a valid integer for the rules of sudoku
     */
    public SquareInputResponse setValue(int row, int col, int value) throws IndexOutOfBoundsException, UnsupportedOperationException {
        if(value < 0 || value > 9) {
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

    public List<ImmutableSquare> getRow(int rowNum) {
        ArrayList<ImmutableSquare> row = new ArrayList<>();

        for(int i = 0; i < SQROOT*SQROOT; i+=3) { //0, 3, 6
            Box b = getBox(rowNum, i);

            for(int j = 0; j < SQROOT; j++) {
                row.add(new ImmutableSquare(b.getCell(rowNum%SQROOT, j).getValue()));
            }
        }

        return row;
    }

    public List<ImmutableSquare> getCol(int colNum) {
        ArrayList<ImmutableSquare> col = new ArrayList<>();

        for(int i = 0; i < SQROOT*SQROOT; i+=3) { //0, 3, 6
            Box b = getBox(i, colNum);

            for(int j = 0; j < SQROOT; j++) {
                col.add(new ImmutableSquare(b.getCell(j, colNum%SQROOT).getValue()));
            }
        }

        return col;
    }

    public Box getBox(int row, int col) {
        Box base = getCell(row/SQROOT, col/SQROOT);

        return base.makeImmutableCopy();
    }
    
    public Set<Cell> getAllof(int num) {
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
     * Checks to see if the board is solved.
     * @return true if the board is filled.
     */
    public boolean isSolved() {

//        for(int row = 0; row < board.length; row++) {
//            for(int col = 0; col < board[row].length; col++) {
//
//            }
//        }

        return false;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < SQROOT*SQROOT; i++) {
    		for(int j = 0; j < SQROOT*SQROOT; j++) {
    	    	sb.append(getSquare(i, j).getValue() + " ");
        	}
    		
    		sb.deleteCharAt(sb.length() -1);
    		sb.append('\n');
    	}
    	
    	return sb.toString();
    }
    
    public String toString(int drawStyle ) {
		return null;
    }
}
