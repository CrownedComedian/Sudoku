package cs.crownedcomedian.sudoku;

/** <pre>
 * Represents a square box of values in a Gameboard.
 * In a 9*9 board, this would be one of the nine 3*3 boxes containing the values 1-9.
 * 
 * +---+---+---+
 * | 5 | 7 | 3 |
 * +---+---+---+
 * | 4 | 1 | 8 |
 * +---+---+---+
 * | 6 | 2 | 9 |
 * +---+---+---+
 * </pre>
 */
public class Box extends SquareGrid<SquareValue> {

    /**
     * Creates a new instance.
     *
     * @param root the square root value of the Gameboard this Box belongs to.
     */
    public Box(int root) {
        super(root);
    }

    @Override
    protected SquareValue fillCell() {
        return new SingleSquare();
    }

    /**
     * Creates an immutable representation of this Box without editing the state or data of the original object.
     *
     * @return a hard copy of this object containing only ImmutableSquare objects.
     */
    public Box makeImmutableCopy() {
        Box copy = new Box(SQROOT);

        for(int i = 0; i < SQROOT; i++) {
            for(int j = 0; j < SQROOT; j++) {
                copy.setCell(i, j, new ImmutableSquare(this.getCell(i, j).getValue()));
            }
        }

        return copy;
    }
}
