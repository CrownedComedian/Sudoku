package cs.crownedcomedian.sudoku;

import java.util.Set;

/**
 * Holds a boolean value representing the result of the input.
 * If the input was a valid move, success = true, and conflictingSquares = null.
 * If the input was an invalid move, success = false, and the conflictingSquares are stored as a Set of Cell objects.
 */
public class SquareInputResponse {
    public final boolean success;
    public final Set<Cell> conflictingSquares;

    /**
     * Constructor representing a valid input response.
     */
    public SquareInputResponse() {
        success = true;
        conflictingSquares = null;
    }

    /**
     * Constructor representing an invalid input response.
     * @param conflictingSquares the squares that break the rules of Sudoku for the desired input.
     */
    public SquareInputResponse(Set<Cell> conflictingSquares) {
        success = false;
        this.conflictingSquares = conflictingSquares;
    }
}
