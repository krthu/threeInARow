import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Scanner sc = new Scanner(System.in);

    public enum GameType {
        MULTIPLAYER,
        SINGLE_PLAYER_EASY,
        SINGLE_PLAYER_MEDIUM
    }
    
    public Game() {
    }

    public void createNewGame(int boardSize, int inARowToWin, GameType gameType) {
        board = new Board(boardSize, inARowToWin);
        System.out.println("What is the name of player 1:");
        String player1Name = sc.nextLine();

        player1 = new HumanPlayer(player1Name, 'X');
        
        switch (gameType){
            case MULTIPLAYER ->{
                System.out.println("What is the name of player 2");
                String player2Name = sc.nextLine();
                player2 = new HumanPlayer(player2Name, 'O');
            }
            case SINGLE_PLAYER_EASY -> {
                player2 = new EasyComputerPlayer("Rando the Comp", 'O', board.getIndexOfOpenCells());
            }
            case SINGLE_PLAYER_MEDIUM -> {
                player2 = new MediumComputerPlayer("Smarto the comp", 'O', board.getIndexOfOpenCells(), board, player1.sign);
            }
        }
        startGame();
    }

    public void startGame() {
        System.out.println("First to get " + board.getNumberInARowToWin() + " in a row gets a point.");

        String again = "y";
        while (again.equalsIgnoreCase("y")) {
            System.out.println(board.getGameState());

            // Place a match of tictactoe and returns if active player won
            boolean winner = playMatch();

            if (winner) {

                activePlayer.addScore();
                System.out.println("Congratulations " + activePlayer.getName() + " you won!");

            } else {
                System.out.println("The game is Tied");
            }
            // Not reseting active player so other player goes first.

            printScoreSummary();
            System.out.println("\nDo you want to go again? (y)");
            System.out.println("Anything else for main menu.");
            board.resetBoard();

            again = sc.nextLine();
        }
    }

    public boolean playMatch() {
        boolean winner = false;
        int movesMade = 0;
        while (!winner && movesMade != board.getMaxMoves()) {

            changeActivePlayer();
            boolean validMove = false;
            // Gets a valid move.
            while (!validMove) {

                System.out.println(activePlayer.getName() + ": It´s your turn.");

                try {
                    int indexOfMove = activePlayer.getMove();

                    validMove = board.placeSign(indexOfMove, activePlayer.sign);
                    // Checks winner
                    if (validMove) {
                        winner = board.doWeHaveAWinner(activePlayer.sign, indexOfMove);
                    } else {
                        System.out.println("Square already taken!");
                    }
                } catch (Exception e) {
                    System.out.println("Need to be a Integer between 1-" + board.getBoardSize() * board.getBoardSize());
                }
            }
            System.out.println(board.getGameState());
            // Moved outside loop to show more clearly that no move was made.
            movesMade += 1;
        }
        return winner;
    }

    public void changeActivePlayer() {
        if (activePlayer == null || activePlayer == player2) {
            activePlayer = player1;
        } else {
            activePlayer = player2;
        }
    }

    public void printScoreSummary() {
        System.out.println("\nThe score is:");
        System.out.println(player1.getName() + " " + player1.getScore());
        System.out.println(player2.getName() + " " + player2.getScore());
    }

    public void multiPlayerMenu() {
        int boardSize = 3;
        int inARowToWin = 3;
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("""
                       Multiplayer Menu
                    ---------------------   
                    1: Start
                    2: Set Board size (Set now: %s)
                    3: Set How many in a row to win (Set now: %s)
                    0: Back
                """.formatted(boardSize, inARowToWin));

            String input = sc.nextLine();
            switch (input) {
                case "1" -> {
                    createNewGame(boardSize, inARowToWin, GameType.MULTIPLAYER);
                    keepGoing = false;
                }
                case "2" -> {

                    boardSize = getIntSafe("How large should the board be? \nType 3 for a 3*3 board.", 3, 50);

                }
                case "3" -> {

                    inARowToWin = getIntSafe("How many in a row do you need to win? Board size is " + boardSize, 3, boardSize);
                }
                case "0" -> {
                    keepGoing = false;
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    public void singlePlayerMenu(){
        int boardSize = 3;
        int inARowToWin = 3;
        GameType gameType = GameType.SINGLE_PLAYER_EASY;

        boolean keepGoing = true;
        while (keepGoing) {
            String levelText = gameType == GameType.SINGLE_PLAYER_EASY ? "EASY" : "MEDIUM";
            System.out.println("""
                           Single player Menu
                        ---------------------   
                        1: Start
                        2: Set Board size (Set now: %s)
                        3: Set How many in a row to win (Set now: %s)
                        4: Difficulty level (Set now: %s)
                        0: Back
                    """.formatted(boardSize, inARowToWin, levelText));

            String input = sc.nextLine();
            switch (input) {
                case "1" -> {
                    createNewGame(boardSize, inARowToWin, gameType);
                    keepGoing = false;
                }
                case "2" -> {
                    // Set boardsize
                    boardSize = getIntSafe("How large should the board be? \nType 3 for a 3*3 board.", 3, 50);

                }
                case "3" -> {
                    // Set How many in a row
                    inARowToWin = getIntSafe("How many in a row do you need to win? Board size is " + boardSize, 3, boardSize);
                }

                case "4" -> {
                    int level = getIntSafe("What difficulty level? \n 1: Easy \n 2: Medium", 1,2);
                    gameType = level == 1 ? GameType.SINGLE_PLAYER_EASY: GameType.SINGLE_PLAYER_MEDIUM;
                }
                case "0" -> {
                    keepGoing = false;
                }
                default -> {
                    System.out.println("Invalid input");
                }


            }
        }
    }

    public int getIntSafe(String questionToRepeat, int notUnder, int notOver) {

        String errorMessage = (notOver == notUnder ? "Has to be " + notUnder : "Has to be between " + notUnder + "-" + notOver + ".");

        while (true) {
            System.out.println(questionToRepeat);
            String input = sc.nextLine();
            try {
                int safeInt = Integer.parseInt(input);
                if (safeInt >= notUnder && safeInt <= notOver) {
                    return safeInt;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }
}
