import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private int boardSize;

    private int maxMoves;

    private int numberInARowToWin;

    private ArrayList<Integer> indexOfAvaliableMoves;

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

    public Character[] getBoard() {
        return board;
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
        HashMap<String, Integer> nrOfCellsInAllDirections = getNumberOfCellsInAllDirections(indexOfPlacedSign);
        // Add one for the placed sign
        int signsInARow = 1;
        //Check horizontal win -
        signsInARow += horizontalCount(signToSearchFor, indexOfPlacedSign, nrOfCellsInAllDirections);
        if (signsInARow == numberInARowToWin) {
            return true;
        }

        signsInARow = 1;
        // Check vertical win |
        signsInARow += verticalCount(signToSearchFor, indexOfPlacedSign, nrOfCellsInAllDirections);
        if (signsInARow == numberInARowToWin) {
            return true;
        }
        signsInARow = 1;
        // Check Diagonal win \
        signsInARow += topLeftToBottomRightCount(signToSearchFor, indexOfPlacedSign, nrOfCellsInAllDirections);
        if (signsInARow == numberInARowToWin) {
            return true;
        }
        signsInARow = 1;
        // Check Diagonal win /
        signsInARow += bottomLeftToTopRightCount(signToSearchFor, indexOfPlacedSign, nrOfCellsInAllDirections);
        if (signsInARow == numberInARowToWin) {
            return true;
        }

        return false;
    }

    public int horizontalCount(char signToSearchFor, int indexOfPlacedSign, HashMap<String, Integer> nrOfCellsInDirections) {
        int signsInARow = 0;
        signsInARow += countSignsInARow(nrOfCellsInDirections.get("toTheLeft"), -1, indexOfPlacedSign, signToSearchFor); // Search Left
        signsInARow += countSignsInARow(nrOfCellsInDirections.get("toTheRight"), 1, indexOfPlacedSign, signToSearchFor); // Search Right

        return signsInARow;
    }

    public int verticalCount(char signToSearchFor, int indexOfPlacedSign, HashMap<String, Integer> nrOfCellsInDirections) {
        int signsInARow = 0;
        signsInARow += countSignsInARow(nrOfCellsInDirections.get("above"), -boardSize, indexOfPlacedSign, signToSearchFor);//Search Upp
        signsInARow += countSignsInARow(nrOfCellsInDirections.get("under"), boardSize, indexOfPlacedSign, signToSearchFor); //Search down
        return signsInARow;
    }

    public int topLeftToBottomRightCount(char signToSearchFor, int indexOfPlacedSign, HashMap<String, Integer> nrOfCellsInDirections) {
        int signsInARow = 0;
        signsInARow += countSignsInARow(nrOfCellsInDirections.get("aboveLeft"), (-1 - boardSize), indexOfPlacedSign, signToSearchFor);
        signsInARow += countSignsInARow(nrOfCellsInDirections.get("underRight"), 1 + boardSize, indexOfPlacedSign, signToSearchFor);
        return signsInARow;
    }

    public int bottomLeftToTopRightCount(char signToSearchFor, int indexOfPlacedSign, HashMap<String, Integer> nrOfCellsInDirections) {
        int signsInARow = 0;
        signsInARow += countSignsInARow(nrOfCellsInDirections.get("underLeft"), -1 + boardSize, indexOfPlacedSign, signToSearchFor);
        signsInARow += countSignsInARow(nrOfCellsInDirections.get("aboveRight"), 1 - boardSize, indexOfPlacedSign, signToSearchFor);
        return signsInARow;
    }

    public int countSignsInARow(int maxIterations, int steps, int indexOfPlacedSign, char signToSearchFor) {

        int countOfSignsInARow = 0;
        for (int i = 1; i <= maxIterations; i++) {
            if (board[indexOfPlacedSign + (i * steps)] != null && board[indexOfPlacedSign + (i * steps)] == signToSearchFor) {
                countOfSignsInARow++;
            } else break;
        }
        return countOfSignsInARow;
    }

    public HashMap<String, Integer> getNumberOfCellsInAllDirections(int indexOfCell) {
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
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";


        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (int i = 0; i < board.length; i++) { // Loops through whole board

            if (i != 0 && i % boardSize == 0) { // If it is a right edge
                builder.append("\n\t");
                for (int j = 0; j < boardSize; j++) {
                    builder.append("-----");
                    if (j < boardSize - 1) {
                        builder.append("+");
                    }
                }
                builder.append("\n\t"); // \t
            }

            // Centered for 1-9 and X O Slightly of center for  10 -> 99
            String emptyCell = i + 1 < 10 ? "  " + (i + 1) + "  " : ("  " + (i + 1) + " ");
            if (board[i] == null) {
                builder.append(emptyCell);
            } else {
                builder.append(board[i] == 'X' ? GREEN + "  " + board[i] + "  " + RESET : RED + "  " + board[i] + "  " + RESET);
            }
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
