import java.util.ArrayList;

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
        //  index < boardSize*boardSize && index >= 0 &&
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

        // Add one for the placed sign
        int nrInARow = 1;
        //Check horizontal

        int cellsToTheLeft = indexOfPlacedSign % boardSize;
        int cellsToTheRight = boardSize - 1 - cellsToTheLeft;
        nrInARow += howManyInARow(cellsToTheLeft, -1, indexOfPlacedSign, signToSearchFor); // Search Left
        nrInARow += howManyInARow(cellsToTheRight, 1, indexOfPlacedSign, signToSearchFor); // Search Right
        if (nrInARow == numberInARowToWin) {
            return true;
        }
        nrInARow = 1;

        // Check Vertical
        int cellsAbove = indexOfPlacedSign / boardSize;
        int cellsUnder = boardSize - 1 - cellsAbove;
        nrInARow += howManyInARow(cellsAbove, -boardSize, indexOfPlacedSign, signToSearchFor);//Search Upp
        nrInARow += howManyInARow(cellsUnder, boardSize, indexOfPlacedSign, signToSearchFor); //Search down
        if (nrInARow == numberInARowToWin) {
            return true;
        }
        nrInARow = 1;

        // check Diagonal TopLeft-BottomRight
        int topLeftMax = Math.min(cellsAbove, cellsToTheLeft);
        int bottomRightMax = cellsUnder > cellsToTheRight ? cellsToTheRight : cellsUnder;

        nrInARow += howManyInARow(topLeftMax, (-1 - boardSize), indexOfPlacedSign, signToSearchFor);
        nrInARow += howManyInARow(bottomRightMax, 1 + boardSize, indexOfPlacedSign, signToSearchFor);

        if (nrInARow == numberInARowToWin) {
            return true;
        }
        nrInARow = 1;

        // Check Diagonal BottomLeft-TopRight
        int bottomLeftMax = cellsUnder > cellsToTheLeft ? cellsToTheLeft : cellsUnder;
        int topRightMax = cellsAbove > cellsToTheRight ? cellsToTheRight : cellsAbove;

        nrInARow += howManyInARow(bottomLeftMax, -1 + boardSize, indexOfPlacedSign, signToSearchFor);
        nrInARow += howManyInARow(topRightMax, 1 - boardSize, indexOfPlacedSign, signToSearchFor);
        if (nrInARow == numberInARowToWin) {
            return true;
        }

        return false;
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

    public String getGameState() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            if (i != 0 && i % boardSize == 0) {
                builder.append("\n");
                for (int j = 0; j < boardSize; j++) {
                    builder.append("---");
                    if (j < boardSize - 1) {
                        builder.append("+");
                    }
                }
                builder.append("\n");
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
