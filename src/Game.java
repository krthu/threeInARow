public class Game {
    private int boardSize;

    private int maxRound;

    private int numberInARowToWin;

    //private Character[][] board;
    private  Character[] board;

    public Game(int boardSize){
        this.boardSize = boardSize;
       // board = new Character[boardSize][boardSize];
        this.board = new  Character[boardSize*boardSize];
        maxRound = boardSize*boardSize;
        numberInARowToWin = 3;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public boolean placeSign(int index, char sign){
        // Decide which row
        int row = index / boardSize;
        //Decide which column
        int column = index % boardSize;
        if(board[index] == null){
            board[index] = sign;
            System.out.println(doWeHaveAWinner(sign, index));
            return true;
        }




//        if(board[row][column] == null) {
//            board[row][column] = sign;
//            return true;
//        }
        return false;
    }

    public boolean doWeHaveAWinner(char signToSearchFor, int indexOfPlacedSign){
        // CheckForHorizontalWin
       // System.out.println("H " + checkHorizontalWinner(signToSearchFor, indexOfPlacedSign));
        //
        //System.out.println("V " + checkVericalWinner(signToSearchFor, indexOfPlacedSign));

        // add one for the placed sign
        int nrInARow = 1;
        //Check horizontal

        int cellsToTheLeft = indexOfPlacedSign % boardSize;
        int cellsToTheRight = boardSize - 1 - cellsToTheLeft;
        nrInARow += howManyInARow(cellsToTheLeft, -1, indexOfPlacedSign, signToSearchFor); // Search Left
        nrInARow += howManyInARow(cellsToTheRight, 1, indexOfPlacedSign, signToSearchFor); // Search Right
        if (nrInARow == numberInARowToWin){
            return true;
        }
        nrInARow = 1;

        // Check Vertical
        int cellsAbove = indexOfPlacedSign / boardSize;
        int cellsUnder = boardSize -1 - cellsAbove;
        nrInARow += howManyInARow(cellsAbove, -boardSize, indexOfPlacedSign, signToSearchFor);
        nrInARow += howManyInARow(cellsUnder, boardSize, indexOfPlacedSign, signToSearchFor);
        if (nrInARow == numberInARowToWin){
            return true;
        }
        nrInARow = 1;

        // check Horizontal TopLeft-BottomRight
        int topLeftMax;
        int bottomRightMax;
        if (cellsAbove > cellsToTheLeft){
            topLeftMax = cellsToTheLeft;
        }else {
            topLeftMax = cellsAbove;
        }
        if (cellsUnder > cellsToTheRight){
            bottomRightMax = cellsToTheRight;
        }else {
            bottomRightMax = cellsUnder;
        }
        nrInARow += howManyInARow(topLeftMax, (-1 - boardSize), indexOfPlacedSign, signToSearchFor);
        nrInARow += howManyInARow(bottomRightMax,1 + boardSize, indexOfPlacedSign, signToSearchFor);

        if (nrInARow == numberInARowToWin){
            return true;
        }
        nrInARow = 1;

        // Check Horizontal BottomLeft-TopRight
        int bottomLeftMax = cellsUnder > cellsToTheLeft ? cellsToTheLeft : cellsUnder;
        int topRightMax = cellsAbove > cellsToTheRight ? cellsToTheRight : cellsAbove;

        nrInARow += howManyInARow(bottomLeftMax, -1 + boardSize, indexOfPlacedSign, signToSearchFor);
        nrInARow += howManyInARow(topRightMax, 1 - boardSize, indexOfPlacedSign, signToSearchFor);

        if(nrInARow == numberInARowToWin){
            return true;
        }


        return false;
    }



    public int howManyInARow(int maxIterations, int steps, int indexOfPlacedSign, char signToSearchFor){

        int countsOfSignsInARow = 0;
        for (int i = 1; i <= maxIterations; i++){
            if(board[indexOfPlacedSign + (i * steps)] != null && board[indexOfPlacedSign + (i * steps)] == signToSearchFor ){
                countsOfSignsInARow++;
            }else break;
        }
        return countsOfSignsInARow;
    }

    public boolean checkVericalWinner(char signToSearchFor, int indexOfPlacedSign){
        int countOfSignsInARow = 1;
        int rowIndex = indexOfPlacedSign / boardSize;
        System.out.println(rowIndex);

        for (int i = 1; i <= rowIndex; i++){
        //    System.out.println("Down");
            if(board[indexOfPlacedSign - (i * boardSize)] == null ||
                    board[indexOfPlacedSign - (i * boardSize)] != signToSearchFor){
                break;
            }else {
                countOfSignsInARow++;
            }
        }
        for (int i = rowIndex; i < boardSize -1; i++){
           // System.out.println("Up");
            if(board[indexOfPlacedSign + (i * boardSize)] == null ||
                    board[indexOfPlacedSign + (i * boardSize)] != signToSearchFor){
                break;
            }else {
                countOfSignsInARow++;
            }
        }
        if(countOfSignsInARow == numberInARowToWin){
            return true;
        }
        return false;
    }

    public boolean checkHorizontalWinner(char signToSearchFor, int indexOfPlacedSign){
        int countOfSignsInARow = 1;
        int colIndex = indexOfPlacedSign % boardSize;
        System.out.println(colIndex);
        for (int i = 1; i <= colIndex; i++){
            if(board[indexOfPlacedSign - i] == null || board[indexOfPlacedSign -i] != signToSearchFor){
                break;
            }else{
                countOfSignsInARow++;
            }
        }
        for (int i = colIndex;  i < boardSize -1; i++ ){
            if(board[indexOfPlacedSign + i] == null || board[indexOfPlacedSign + i ] != signToSearchFor){
                break;
            }else{
                countOfSignsInARow++;
            }
        }
        System.out.println("Number in a row " + countOfSignsInARow);
        if(countOfSignsInARow == numberInARowToWin){
            return true;
        }
        //System.out.println(colIndex);
        return false;
    }

    public String getGameState(){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < board.length; i++){
            if(i != 0 && i % boardSize == 0){
                builder.append("\n");
                for(int j = 0; j < boardSize; j++){
                    builder.append("---");
                    if (j < boardSize -1){
                        builder.append("+");
                    }
                }
                builder.append("\n");
            }

            builder.append(board[i] == null ? "   ": " " + board[i] + " ");
            builder.append((i+1) % boardSize == 0 ? "" : "|" );


        }
        return builder.toString();

    }

//    public String getGameState() {
//        StringBuilder builder = new StringBuilder();
//   //     builder.append("   A   B   C\n");
//        for (int i = 0; i < board.length; i++) {
//           // builder.append(i);
//           // builder.append(": ");
//            for (int j = 0; j < board[i].length; j++) {
//
//                builder.append(board[i][j] == null ? "   " : " " + board[i][j] +" ");
//
//                if (j != board[i].length - 1) {
//                    builder.append("|");
//                }
//            }
//            builder.append("\n");
//            if (i != board.length - 1) {
//             //   builder.append("  ");
//                for (int j = 0; j < board.length; j++) {
//                    builder.append("---");
//                    if (j != board.length - 1) {
//                        builder.append("+");
//                    }
//                }
//                builder.append("\n");
//            }
//        }
//        return  builder.toString();
//    }
}
