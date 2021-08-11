package cs.crownedcomedian.sudoku;

import java.util.ArrayList;

/**<pre>
 * Structure for a classification representing a grid of T objects with size root.
 *
 * root = 2
 * +---+---+
 * | T | T |
 * +---+---+
 * | T | T |
 * +---+---+
 *
 * root = 3
 * +---+---+---+
 * | T | T | T |
 * +---+---+---+
 * | T | T | T |
 * +---+---+---+
 * | T | T | T |
 * +---+---+---+
 *
 * ...etc.
 * </pre>
 *
 * @param <T> classification to fill the cells of the grid object with.
 */
public abstract class SquareGrid<T> {
    private final ArrayList<ArrayList<T>> grid = new ArrayList<>();
    public final int SQROOT;

    /**
     * Creates a new instance with size root.
     *
     * @param root the size of the grid.
     */
    public SquareGrid(int root) {
        SQROOT = root;
        init();
    }

    /**
     * Creates a new instance with a default size of 3.
     */
    public SquareGrid() {
        SQROOT = 3;
        init();
    }

    private void init() {
        for(int i = 0; i < SQROOT; i++) {
            grid.add(new ArrayList<T>());

            for(int j = 0; j < SQROOT; j++) {
                grid.get(i).add(fillCell());
            }
        }
    }

    protected T getCell(int row, int col) {
        return grid.get(row).get(col);
    }

    protected void setCell(int row, int col, T value) {
        grid.get(row).set(col, value);
    }

    protected abstract T fillCell();
}
