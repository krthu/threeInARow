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
        System.out.println("V " + checkVericalWinner(signToSearchFor, indexOfPlacedSign));

        return true;
    }

    public boolean checkVericalWinner(char signToSearchFor, int indexOfPlacedSign){
        int countsOfSignsInARow = 1;
        int rowIndex = indexOfPlacedSign / boardSize;
        System.out.println(rowIndex);

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
        for (int i = 1;  i < boardSize; i++ ){
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
