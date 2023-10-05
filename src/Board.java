import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private int boardSize;

    private int maxMoves;

    private int numberInARowToWin;

    private ArrayList<Integer> indexOfAvaliableMoves;

    //private Character[][] board;
    private Character[] board;

    public Board(int boardSize, int numberInARowToWin) {
        this.boardSize = boardSize;
        this.numberInARowToWin = numberInARowToWin;
        indexOfAvaliableMoves = new ArrayList<>();
        resetBoard();
        maxMoves = boardSize * boardSize;
    }

    public int getNumberInARowToWin() {
        return numberInARowToWin;
    }

    public ArrayList<Integer> getIndexOfOpenCells() {
        return indexOfAvaliableMoves;
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public boolean placeSign(int index, char sign) {

        if (board[index] == null) {
            board[index] = sign;
            for (int i = 0; i < indexOfAvaliableMoves.size(); i++) {
                if (indexOfAvaliableMoves.get(i) == index) {
                    indexOfAvaliableMoves.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doWeHaveAWinner(char signToSearchFor, int indexOfPlacedSign) {
        HashMap<String, Integer> cells = getNumberOfCellsInAllDirections(indexOfPlacedSign);
        // Add one for the placed sign
        int nrInARow = 1;
        //Check horizontal win -
        nrInARow += horizontalCount(signToSearchFor, indexOfPlacedSign, cells);
        if (nrInARow == numberInARowToWin) {
            return true;
        }

        nrInARow = 1;
        // Check vertical win |
        nrInARow += verticalCount(signToSearchFor, indexOfPlacedSign, cells);
        if (nrInARow == numberInARowToWin) {
            return true;
        }
        nrInARow = 1;
        // Check Diagonal win \
        nrInARow += topLeftToBottomRightCount(signToSearchFor, indexOfPlacedSign, cells);
        if (nrInARow == numberInARowToWin) {
            return true;
        }
        nrInARow = 1;
        // Check Diagonal win /
        nrInARow += bottomLeftToTopRightCount(signToSearchFor, indexOfPlacedSign, cells);
        if (nrInARow == numberInARowToWin) {
            return true;
        }

        return false;
    }

    public int horizontalCount(char signToSearchFor, int indexOfPlacedSign, HashMap<String, Integer> cells){
        int nrInARow = 0;
        nrInARow += howManyInARow(cells.get("toTheLeft"), -1, indexOfPlacedSign, signToSearchFor); // Search Left
        nrInARow += howManyInARow(cells.get("toTheRight"), 1, indexOfPlacedSign, signToSearchFor); // Search Right

        return nrInARow;
    }

    public int verticalCount(char signToSearchFor, int indexOfPlacedSign, HashMap<String, Integer> cells){
        int nrInARow = 0;
        nrInARow += howManyInARow(cells.get("above"), -boardSize, indexOfPlacedSign, signToSearchFor);//Search Upp
        nrInARow += howManyInARow(cells.get("under"), boardSize, indexOfPlacedSign, signToSearchFor); //Search down
        return nrInARow;
    }

    public int topLeftToBottomRightCount(char signToSearchFor, int indexOfPlacedSign, HashMap<String, Integer> cells){
        int nrInARow = 0;
        nrInARow += howManyInARow(cells.get("aboveLeft"), (-1 - boardSize), indexOfPlacedSign, signToSearchFor);
        nrInARow += howManyInARow(cells.get("underRight"), 1 + boardSize, indexOfPlacedSign, signToSearchFor);
        return nrInARow;
    }

    public int bottomLeftToTopRightCount(char signToSearchFor, int indexOfPlacedSign, HashMap<String, Integer> cells){
        int nrInARow = 0;
        nrInARow += howManyInARow(cells.get("underLeft"), -1 + boardSize, indexOfPlacedSign, signToSearchFor);
        nrInARow += howManyInARow(cells.get("aboveRight"), 1 - boardSize, indexOfPlacedSign, signToSearchFor);
        return nrInARow;
    }

    public int howManyInARow(int maxIterations, int steps, int indexOfPlacedSign, char signToSearchFor) {

        int countsOfSignsInARow = 0;
        for (int i = 1; i <= maxIterations; i++) {
            if (board[indexOfPlacedSign + (i * steps)] != null && board[indexOfPlacedSign + (i * steps)] == signToSearchFor) {
                countsOfSignsInARow++;
            } else break;
        }
        return countsOfSignsInARow;
    }

    public HashMap<String, Integer> getNumberOfCellsInAllDirections(int indexOfCell){
        HashMap<String, Integer> numberOfCellsInAllDirections = new HashMap<>();

        int cellsToTheLeft = indexOfCell % boardSize;
        int cellsToTheRight = boardSize - 1 - cellsToTheLeft;

        int cellsAbove = indexOfCell / boardSize;
        int cellsUnder = boardSize - 1 - cellsAbove;

        int cellsAboveLeft = Math.min(cellsAbove, cellsToTheLeft);
        int cellsUnderRight = cellsUnder > cellsToTheRight ? cellsToTheRight : cellsUnder;

        int cellsUnderLeft = cellsUnder > cellsToTheLeft ? cellsToTheLeft : cellsUnder;
        int cellsAboveRight = cellsAbove > cellsToTheRight ? cellsToTheRight : cellsAbove;

        numberOfCellsInAllDirections.put("toTheLeft", cellsToTheLeft);
        numberOfCellsInAllDirections.put("toTheRight", cellsToTheRight);
        numberOfCellsInAllDirections.put("above", cellsAbove);
        numberOfCellsInAllDirections.put("under", cellsUnder);
        numberOfCellsInAllDirections.put("aboveLeft", cellsAboveLeft);
        numberOfCellsInAllDirections.put("underRight", cellsUnderRight);
        numberOfCellsInAllDirections.put("underLeft", cellsUnderLeft);
        numberOfCellsInAllDirections.put("aboveRight", cellsAboveRight);

        return numberOfCellsInAllDirections;
    }

    public String getGameState() {
        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (int i = 0; i < board.length; i++) { // Loops through whole board

            if (i != 0 && i % boardSize == 0) { // If it is a right edge
                builder.append("\n\t");
                for (int j = 0; j < boardSize; j++) {
                    builder.append("---");
                    if (j < boardSize - 1) {
                        builder.append("+");
                    }
                }
                builder.append("\n\t"); // \t
            }

            builder.append(board[i] == null ? "   " : " " + board[i] + " ");
            builder.append((i + 1) % boardSize == 0 ? "" : "|");
        }
        return builder.toString();
    }

    public void resetBoard() {
        // Need work
        board = new Character[boardSize * boardSize];
        indexOfAvaliableMoves.clear();
        for (int i = 0; i < boardSize * boardSize; i++) {
            indexOfAvaliableMoves.add(i);
        }
    }

}
