package cs.crownedcomedian.sudoku;

import java.util.ArrayList;

/**
 * <pre>
 *        |   |
 *     ---+---+---
 *        |   |
 *     ---+---+---
 *        |   |
 * </pre>
 * @param <T>
 */
public abstract class SquareGrid<T> {
    private final ArrayList<ArrayList<T>> grid = new ArrayList<>();
    public final int SQROOT;

    public SquareGrid(int root) {
        SQROOT = root;
        init();
    }

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
