package cs.crownedcomedian.sudoku;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Responsible for holding methods to check if new input is valid.
 */
public final class GameValidator {
    private final GameBoard board;

    public GameValidator(GameBoard board) {
        this.board = board;
    }

    public SquareInputResponse validateInput(int row, int col, int value) {
        if(value == 0) {
            return new SquareInputResponse();
        }

        Set<Cell> conflicts = new HashSet<>();

        checkRow(conflicts, row, col, value);
        checkCol(conflicts, row, col, value);
        checkBox(conflicts, row, col, value);
        //TODO - checkBoxRow
        //TODO - checkBoxCol

        if(conflicts.size() == 0) {
            return new SquareInputResponse();
        } else {        	
            return new SquareInputResponse(conflicts);
        }
    }

    private void checkRow(Set<Cell> conflictingCells, int rowNum, int squareNum, int inputVal) {
    	if(inputVal == 0) { return; }
    	
        List<ImmutableSquare> row = board.getRow(rowNum);

        for(int i = 0; i < 9; i++) {
            if(i != squareNum) {
            	if(row.get(i).getValue() == inputVal) {
            		conflictingCells.add(new Cell(rowNum, i));
            	}
            }
        }
    }

    private void checkCol(Set<Cell> conflictingCells, int squareNum, int colNum, int inputVal) {
    	if(inputVal == 0) { return; }
    	
        List<ImmutableSquare> col = board.getCol(colNum);

        for(int i = 0; i < col.size(); i++) {
            if(i != squareNum) {            	
            	if(col.get(i).getValue() == inputVal) {
            		conflictingCells.add(new Cell(i, colNum));
            	}
            }
        }
    }

    private void checkBox(Set<Cell> conflictingCells, int rowNum, int colNum, int inputVal) {
    	if(inputVal == 0) { return; }
    	
    	Box b = board.getBox(rowNum, colNum);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
            	
                if( !(i == rowNum%3 && j == colNum%3) ) {
                	if(b.getCell(i, j).getValue() == inputVal) {
                        conflictingCells.add(new Cell(rowNum/3*3 +i, colNum/3*3 +j));
                	}
                }
            }
        }
    }
    
    private void checkBoxRow() {
//    	to get other rows:
//    	(rowNum+1)%3
//    	(rowNum+2)%3
    }
}
