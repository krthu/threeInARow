
import java.util.ArrayList;


public class HardComputerPlayer extends Player {
    private Board board;

    private char opponentSign = 'X';

    public HardComputerPlayer(Board board) {
        super("Hardy the Comp", 'O');
        this.board = board;
    }

    public int getMove() {
        int move = getBestMove();

        return move;
    }

    public int getBestMove() {
        int bestValue = -1000;
        int bestMove = -1000;

        for (int move : board.getIndexOfOpenCells()) {
            board.getBoard()[move] = sign;
            int moveValue = minMax(false, sign, move);
            board.getBoard()[move] = null;

            if (moveValue > bestValue) {
                bestMove = move;
                bestValue = moveValue;
            }

        }
        return bestMove;
    }

    public boolean noMovesLeft() {
        for (int i = 0; i < board.getBoard().length; i++) {
            if (board.getBoard()[i] == null) {
                return false;
            }
        }
        return true;
    }

    public int minMax(boolean isComp, char lastSign, int lastMoveIndex) {

        if (board.doWeHaveAWinner(lastSign, lastMoveIndex)) {
            if (lastSign == sign) {
                return 10;
            }
            return -10;
        }
        if (noMovesLeft()) {
            return 0;
        }

        if (isComp) {   // max
            int bestScore = -1000;

            for (int i = 0; i < board.getBoard().length; i++) {
                if (board.getBoard()[i] == null) {
                    board.getBoard()[i] = sign;

                    bestScore = Math.max(bestScore, minMax(false, sign, i));
                    board.getBoard()[i] = null;
                }
            }
            return bestScore;
        } else { // min

            int bestScore = 1000;

            for (int i = 0; i < board.getBoard().length; i++) {

                if (board.getBoard()[i] == null) {
                    board.getBoard()[i] = opponentSign;
                    bestScore = Math.min(bestScore, minMax(true, opponentSign, i));
                    board.getBoard()[i] = null;

                }
            }
            return bestScore;
        }
    }
}
