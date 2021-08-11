package cs.crownedcomedian.sudoku;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Responsible for holding methods to check if new input into a Gameboard object is valid.
 */
public final class GameValidator {
    private final GameBoard board;

    /**
     * Creates a new Instance and uses values from board to perform checks.
     *
     * @param board the Gameboard to check against.
     */
    public GameValidator(GameBoard board) {
        this.board = board;
    }

    /**
     * Returns a SquareInputResponse of the results of the attempt to change the value of the square at the given (row, col) location.
     *
     * @param row the row of the value.
     * @param col the column of the value.
     * @param value the new value for the specified square.
     *
     * @return response object to show if the attempt succeeded or failed.
     */
    public SquareInputResponse validateInput(int row, int col, int value) {
        if(value == 0) {
            return new SquareInputResponse();
        }

        Set<Cell> conflicts = new HashSet<>();

        checkRow(conflicts, row, col, value);
        checkCol(conflicts, row, col, value);
        checkBox(conflicts, row, col, value);
        //TODO - implement check box row&col for varying size boards
//        checkBoxRow(conflicts, row, col, value);
//        checkBoxCol(conflicts, row, col, value);

        if(conflicts.size() == 0) {
            return new SquareInputResponse();
        } else {        	
            return new SquareInputResponse(conflicts);
        }
    }

    private void checkRow(Set<Cell> conflictingCells, int rowNum, int squareNum, int inputVal) {
    	if(inputVal == 0) { return; }
    	
        List<ImmutableSquare> row = board.getRow(rowNum);

        for(int i = 0; i < row.size(); i++) {
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

        for(int i = 0; i < board.SQROOT; i++) {
            for(int j = 0; j < board.SQROOT; j++) {
            	
                if( !(i == rowNum% board.SQROOT && j == colNum% board.SQROOT) ) {
                	if(b.getCell(i, j).getValue() == inputVal) {
                        conflictingCells.add(new Cell(rowNum/board.SQROOT*board.SQROOT +i, colNum/board.SQROOT*board.SQROOT +j));
                	}
                }
            }
        }
    }
    
    private void checkBoxRow(Set<Cell> conflictingCells, int rowNum, int colNum, int inputVal) {
        int adjacentRow1 = (rowNum+1)%3;
        int adjacentRow2 = (rowNum+2)%3;
        int adjacentBox1;
        int adjacentBox2;

        switch(colNum/3) {
            case 0:
                adjacentBox1 = 1;
                adjacentBox2 = 2;
                break;
            case 1:
                adjacentBox1 = 0;
                adjacentBox2 = 2;
                break;
            default:
                adjacentBox1 = 0;
                adjacentBox2 = 1;
                break;
        }

        conflictingCells.addAll(findConflictingRows(adjacentRow1, adjacentBox1, adjacentRow2, adjacentBox2, inputVal));
        conflictingCells.addAll(findConflictingRows(adjacentRow1, adjacentBox2, adjacentRow2, adjacentBox1, inputVal));
        conflictingCells.addAll(findConflictingRows(adjacentRow2, adjacentBox1, adjacentRow1, adjacentBox2, inputVal));
        conflictingCells.addAll(findConflictingRows(adjacentRow2, adjacentBox2, adjacentRow1, adjacentBox1, inputVal));
    }

    private Set<Cell> findConflictingRows(int adjRow1, int box1, int adjRow2, int box2, int targetVal) {
        Set<Cell> potentialConflicts = new HashSet<>();

        for(int i = 0; i < 3; i++) {
            int val = board.getSquare(adjRow1, box1*3 +i).getValue();

            if(val == targetVal) {
                potentialConflicts.add(new Cell(adjRow1, box1*3 +i));

                //check  for filled squares in row2 of box2
                for(int j = 0; j < 3; j++) {
                    int filler = board.getSquare(adjRow2, box2*3 +j).getValue();

                    if(filler == 0 || filler == targetVal) {
                        potentialConflicts = new HashSet<>();
                        break;
                    } else {
                        potentialConflicts.add(new Cell(adjRow2, box2*3 +j));
                    }
                }

                break;
            }
        }

        return potentialConflicts;
    }

    private void checkBoxCol(Set<Cell> conflictingCells, int rowNum, int colNum, int inputVal) {
        int adjacentCol1 = (colNum+1)%3;
        int adjacentCol2 = (colNum+2)%3;
        int adjacentBox1;
        int adjacentBox2;

        switch(rowNum/3) {
            case 0:
                adjacentBox1 = 1;
                adjacentBox2 = 2;
                break;
            case 1:
                adjacentBox1 = 0;
                adjacentBox2 = 2;
                break;
            default:
                adjacentBox1 = 0;
                adjacentBox2 = 1;
                break;
        }

        conflictingCells.addAll(findConflictingCols(adjacentCol1, adjacentBox1, adjacentCol2, adjacentBox2, inputVal));
        conflictingCells.addAll(findConflictingCols(adjacentCol1, adjacentBox2, adjacentCol2, adjacentBox1, inputVal));
        conflictingCells.addAll(findConflictingCols(adjacentCol2, adjacentBox1, adjacentCol1, adjacentBox2, inputVal));
        conflictingCells.addAll(findConflictingCols(adjacentCol2, adjacentBox2, adjacentCol1, adjacentBox1, inputVal));
    }

    private Set<Cell> findConflictingCols(int adjCol1, int box1, int adjCol2, int box2, int targetVal) {
        Set<Cell> potentialConflicts = new HashSet<>();

        for(int i = 0; i < 3; i++) {
            int val = board.getSquare(box1*3 +i, adjCol1).getValue();

            if(val == targetVal) {
                potentialConflicts.add(new Cell(adjCol1, box1*3 +i));

                for(int j = 0; j < 3; j++) {
                    int filler = board.getSquare(box2*3 +j, adjCol2).getValue();

                    if(filler == 0 || filler == targetVal) {
                        potentialConflicts = new HashSet<>();
                        break;
                    } else {
                        potentialConflicts.add(new Cell(box2*3 +j, adjCol2));
                    }
                }

                break;
            }
        }

        return potentialConflicts;
    }
}
