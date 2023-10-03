import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    private int size;
    private Character[] board;

    private ArrayList<Integer> indexOfOpenCells;


    public Board (int size){
        this.size = size;
        board = new Character[size*size];
        indexOfOpenCells = new ArrayList<>();
        for(int i = 0; i < size*size; i++){
            indexOfOpenCells.add(i);
        }
    }

    public int getSize(){
        return size;
    }

    public Character[] getBoard() {
        return board;
    }

    public void setBoard(Character[] board) {
        this.board = board;
    }

    public boolean placeSign(int index, char sign){

        if(board[index] == null){
            board[index] = sign;
            indexOfOpenCells.remove(index);
            return true;
        }
        return false;
    }

//    public HashMap<String, Integer> getDistanceToAllEdges(int indexOfPlacedSign){
//        int cellsToTheLeft = indexOfPlacedSign % size;
//        int cellsToTheRight = size - 1 - cellsToTheLeft;
//
//        int cellsAbove = indexOfPlacedSign / size;
//        int cellsUnder = size -1 - cellsAbove;
//
//        int topLeftMax = cellsAbove > cellsToTheLeft ? cellsToTheLeft : cellsAbove;
//        int bottomRightMax = cellsUnder > cellsToTheRight ? cellsToTheRight : cellsUnder;
//
//        int bottomLeftMax = cellsUnder > cellsToTheLeft ? cellsToTheLeft : cellsUnder;
//        int topRightMax = cellsAbove > cellsToTheRight ? cellsToTheRight : cellsAbove;
//
//        HashMap<String, Integer> mapToEdges = new HashMap<>();
//        mapToEdges
//
//
//        return

    //}


// Rewrite this as toString() ?
    public String getBoardState(){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < board.length; i++){
            if(i != 0 && i % size == 0){
                builder.append("\n");
                for(int j = 0; j < size; j++){
                    builder.append("---");
                    if (j < size -1){
                        builder.append("+");
                    }
                }
                builder.append("\n");
            }

            builder.append(board[i] == null ? "   ": " " + board[i] + " ");
            builder.append((i+1) % size == 0 ? "" : "|" );
        }
        return builder.toString();
    }

    public void resetBoard(){
        board = new Character[size*size];
    }



}
