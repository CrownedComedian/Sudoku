package cs.crownedcomedian.sudoku;

public final class ImmutableSquare implements SquareValue {
    private final int value;

    public ImmutableSquare(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
