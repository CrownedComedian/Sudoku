package cs.crownedcomedian.sudoku;

public class SingleSquare implements SquareValue {
    public int value = 0;

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) { this.value = value; }
}
