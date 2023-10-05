import java.util.ArrayList;
import java.util.HashMap;

public class MediumComputerPlayer extends Player{
    ArrayList<Integer> indexOfAvailableMoves;
    Board board;

    char otherPlayerSign;

    public MediumComputerPlayer(String name, char sign, ArrayList<Integer> indexOfAvailableMoves, Board board, char otherPlayerSign){
        super(name, sign);
        this.indexOfAvailableMoves = indexOfAvailableMoves;
        this.board = board;
        this.otherPlayerSign = otherPlayerSign;
    }

    public int getMove(){
        HashMap<String, Integer> bestMoveToWin = getBestMove(sign);
        HashMap<String, Integer> bestMoveToBlock = getBestMove(otherPlayerSign);

        if(bestMoveToWin.get("nrInARow") >= bestMoveToBlock.get("nrInARow")){
            return bestMoveToWin.get("bestMove");
        }
        return bestMoveToBlock.get("bestMove");
    }

    public HashMap<String, Integer>getBestMove(char signToSearchFor){
        int bestMove = -1;
        int highestInARow = -1;


        for (int move: indexOfAvailableMoves) {

            int nrInARow;
            HashMap<String, Integer> cells = board.getNumberOfCellsInAllDirections(move);
            nrInARow = board.horizontalCount(signToSearchFor, move, cells);

            if (nrInARow + 1 == board.getNumberInARowToWin()){
                bestMove = move;
                highestInARow = nrInARow;
                break;
            } else if (highestInARow < nrInARow) {
                bestMove = move;
                highestInARow = nrInARow;
            }

            nrInARow = board.verticalCount(signToSearchFor, move, cells);

            if (nrInARow + 1 == board.getNumberInARowToWin()){
                bestMove = move;
                highestInARow = nrInARow;
                break;
            } else if (highestInARow < nrInARow) {
                bestMove = move;
                highestInARow = nrInARow;
            }

            nrInARow = board.topLeftBottomRightCount(signToSearchFor, move, cells);

            if (nrInARow + 1 == board.getNumberInARowToWin()){
                bestMove = move;
                highestInARow = nrInARow;
                break;
            } else if (highestInARow < nrInARow) {
                bestMove = move;
                highestInARow = nrInARow;
            }

            nrInARow = board.bottomLeftTopRightCount(signToSearchFor, move, cells);

            if (nrInARow + 1 == board.getNumberInARowToWin()){
                bestMove = move;
                highestInARow = nrInARow;
                break;
            } else if (highestInARow < nrInARow) {
                bestMove = move;
                highestInARow = nrInARow;
            }

        }
        HashMap<String, Integer> bestMoveToWin = new HashMap<>();
        bestMoveToWin.put("bestMove", bestMove);
        bestMoveToWin.put("nrInARow", highestInARow);
        return bestMoveToWin;
    }

   // public int





}
