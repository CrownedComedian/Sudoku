package cs.crownedcomedian.sudoku;

/**
 * POJO that holds integer values for the row and column of a Cell.
 */
public class Cell {
    public final int row, col;

    /**
     * Creates a new instance.
     *
     * @param row the row number of this Cell.
     * @param col the column number of this Cell.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) { return true; }
        if(o == null || getClass() != o.getClass()) { return false; }

        Cell cell = (Cell) o;

        return (row == cell.row && col == cell.col);
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		
		return result;
	}
}
