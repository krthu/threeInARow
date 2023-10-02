public class Game {
    private int boardSize;

    private int maxRound;

    private Character[][] board;

    public Game(int boardSize){
        this.boardSize = boardSize;
        board = new Character[boardSize][boardSize];
        maxRound = boardSize*boardSize;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public boolean placeSign(int index, char sign){
        // Decide which row
        int row = index / boardSize;
        //Decide which column
        int column = index % boardSize;
        if(board[row][column] == null) {
            board[row][column] = sign;
            return true;
        }
        return false;
    }



    public String getGameState() {
        StringBuilder builder = new StringBuilder();
   //     builder.append("   A   B   C\n");
        for (int i = 0; i < board.length; i++) {
           // builder.append(i);
           // builder.append(": ");
            for (int j = 0; j < board[i].length; j++) {

                builder.append(board[i][j] == null ? "   " : " " + board[i][j] +" ");

                if (j != board[i].length - 1) {
                    builder.append("|");
                }
            }
            builder.append("\n");
            if (i != board.length - 1) {
             //   builder.append("  ");
                for (int j = 0; j < board.length; j++) {
                    builder.append("---");
                    if (j != board.length - 1) {
                        builder.append("+");
                    }
                }
                builder.append("\n");
            }
        }
        return  builder.toString();
    }
}
