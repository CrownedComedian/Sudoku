package cs.crownedcomedian.sudoku;

/**
 * Represents a square value that cannot be changed.
 */
public final class ImmutableSquare implements SquareValue {
    private final int value;

    /**
     * Creates a new instance.
     *
     * @param value the unchangeable value of the square.
     */
    public ImmutableSquare(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
