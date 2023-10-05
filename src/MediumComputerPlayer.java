import java.util.ArrayList;
import java.util.HashMap;

public class MediumComputerPlayer extends Player{
    ArrayList<Integer> indexOfAvailableMoves;
    Board board;

    public MediumComputerPlayer(String name, char sign, ArrayList<Integer> indexOfAvailableMoves, Board board){
        super(name, sign);
        this.indexOfAvailableMoves = indexOfAvailableMoves;
        this.board = board;
    }

    public int getMove(){
        int bestMove;

        // find if can win best move
        for (int move: indexOfAvailableMoves) {
            HashMap<String, Integer> cells = board.getNumberOfCellsInAllDirections(move);







        }


        // find move to block





        return 0;
    }




}
