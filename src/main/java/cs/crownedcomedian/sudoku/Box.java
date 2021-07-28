package cs.crownedcomedian.sudoku;

public class Box extends SquareGrid<SquareValue> {

    public Box(int root) {
        super(root);
    }

    @Override
    protected SquareValue fillCell() {
        return new SingleSquare();
    }

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
