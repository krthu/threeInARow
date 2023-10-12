import java.util.ArrayList;
import java.util.HashMap;

public class MediumComputerPlayer extends Player {
    ArrayList<Integer> indexOfAvailableMoves;
    Board board;

    char otherPlayerSign;

    public MediumComputerPlayer(String name, char sign, ArrayList<Integer> indexOfAvailableMoves, Board board, char otherPlayerSign) {
        super(name, sign);
        this.indexOfAvailableMoves = indexOfAvailableMoves;
        this.board = board;
        this.otherPlayerSign = otherPlayerSign;
    }

    public int getMove() {
        HashMap<String, Integer> bestMoveToWin = getBestMove(sign);
        HashMap<String, Integer> bestMoveToBlock = getBestMove(otherPlayerSign);

        if (bestMoveToWin.get("signsInARow") >= bestMoveToBlock.get("signsInARow")) {
            return bestMoveToWin.get("bestMove");
        }
        return bestMoveToBlock.get("bestMove");
    }

    public HashMap<String, Integer> getBestMove(char signToSearchFor) {

        HashMap<String, Integer> bestMove = new HashMap<>();
        bestMove.put("bestMove", -1); // Adding a value to replace in evaluateMoveInDirection.
        bestMove.put("signsInARow", -1); // Adding a value to replace in evaluateMoveInDirection. Put negative value so it is always replaced
        for (int move : indexOfAvailableMoves) {

            int signsInARow;
            HashMap<String, Integer> numberOfCellsInAllDirections = board.getNumberOfCellsInAllDirections(move);

            // Horizontal count - counts how many cells in a row if move is placed here
            signsInARow = board.horizontalCount(signToSearchFor, move, numberOfCellsInAllDirections);
            evaluateMoveInDirection(signsInARow, move, bestMove);
            if (bestMove.containsKey("winningMove")) {
                return bestMove;
            }

            // Vertical count | counts how many cells in a row if move is placed here
            signsInARow = board.verticalCount(signToSearchFor, move, numberOfCellsInAllDirections);
            evaluateMoveInDirection(signsInARow, move, bestMove);
            if (bestMove.containsKey("winningMove")) {
                return bestMove;
            }


            // Diagonal top left to bottom right count \ counts how many cells in a row if move is placed here
            signsInARow = board.topLeftToBottomRightCount(signToSearchFor, move, numberOfCellsInAllDirections);
            evaluateMoveInDirection(signsInARow, move, bestMove);
            if (bestMove.containsKey("winningMove")) {
                return bestMove;
            }

            // Diagonal bottom left to top right count / counts how many cells in a row if move is placed here
            signsInARow = board.bottomLeftToTopRightCount(signToSearchFor, move, numberOfCellsInAllDirections);
            evaluateMoveInDirection(signsInARow, move, bestMove);
            if (bestMove.containsKey("winningMove")) {
                return bestMove;
            }
        }
        return bestMove;
    }

    public void evaluateMoveInDirection(int signsInARow, int move, HashMap<String, Integer> bestMoveToWin) {
        // Decide if it is vital for winning/losing if so MAKE the MOVE!
        if (signsInARow + 1 == board.getNumberInARowToWin()) {
            bestMoveToWin.replace("bestMove", move);
            bestMoveToWin.replace("signsInARow", signsInARow);
            bestMoveToWin.put("winningMove", 0);

        }
        // Decide if it is the best move seen
        else if (bestMoveToWin.get("signsInARow") < signsInARow) {
            bestMoveToWin.replace("bestMove", move);
            bestMoveToWin.replace("signsInARow", signsInARow);
        }
    }
}
