package cs.crownedcomedian.sudoku;

/**
 * Represents an individual square on a Gameboard.
 */
public class SingleSquare implements SquareValue {
    public int value = 0;

    @Override
    public int getValue() {
        return value;
    }

    /**
     * Changes the value of this SingleSquare.
     *
     * @param value the new value.
     */
    public void setValue(int value) { this.value = value; }
}
