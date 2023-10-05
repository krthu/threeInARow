import java.util.Scanner;

public class GameUI {
    private Game game;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Scanner sc = new Scanner(System.in); // Is this okey?
    
    public GameUI() {
    }

    public void createNewGame(int boardSize, int inARowToWin, boolean multiplayer) {
        System.out.println("What is the name of player 1:");
        String player1Name = sc.nextLine();

        player1 = new Player(player1Name, 'x');
        if (multiplayer) {
            System.out.println("What is the name of player 2");
            String player2Name = sc.nextLine();
            player2 = new Player(player2Name, 'O');
        } else {
            player2 = new ComputerPlayer("Rando the Comp", 'O');
        }
        // Add to Player - array
        game = new Game(boardSize, inARowToWin);
        startGame();
    }


    public void startGame() {
        System.out.println("First to get " + game.getNumberInARowToWin() + " in a row gets a point.");

        String again = "y";
        while (again.equalsIgnoreCase("y")) {
            System.out.println(game.getGameState());

            // Place a match of tictactoe an returns if active player won
            boolean winner = playMatch();

            if (winner) {
                winner = false;
                activePlayer.addScore();
                System.out.println("Congratulations " + activePlayer.getName() + " you won!");

            } else {
                System.out.println("The game is Tied");
            }
            // Not reseting active player so loser goes first.

            printScoreSummary();
            System.out.println("Do you want to go again? (y)");
            System.out.println("Anything else for main menu.");
            game.resetBoard();
            again = sc.nextLine();
        }

    }

    public boolean playMatch() {
        boolean winner = false;
        int movesMade = 0;
        while (!winner && movesMade != game.getMaxMoves()) {

            changeActivePlayer();
            boolean validMove = false;
            // Gets a valid move.
            while (!validMove) {
                // Get a valid move own function?
                System.out.println(activePlayer.getName() + ": It´s your turn.");
                //   int index = getIntSafe((activePlayer.getName() + ": It´s your turn."), 1, game.getBoardSize() * game.getBoardSize());
                try {
                    int indexOfMove = activePlayer.getMove(game.getIndexOfOpenCells());
                    indexOfMove = indexOfMove - 1;

                    validMove = game.placeSign(indexOfMove, activePlayer.sign);
                    // Checks winner
                    if (validMove) {
                        winner = game.doWeHaveAWinner(activePlayer.sign, indexOfMove);
                    } else {
                        System.out.println("Square already taken!");
                    }
                } catch (Exception e) {
                    System.out.println("Need to be a Integer between 1-" + game.getBoardSize() * game.getBoardSize());
                }
            }
            System.out.println(game.getGameState());
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
        System.out.println("The score is:");
        System.out.println(player1.getName() + " " + player1.getScore());
        System.out.println(player2.getName() + " " + player2.getScore());
    }

    public void multiPlayerMenu() {
        int boardSize = 3;
        int inARowToWin = 3;
        boolean keepGoing = true;
        while (keepGoing) {
            printMultiPlayerMenu(boardSize, inARowToWin);
            String input = sc.nextLine();
            switch (input) {
                case "1" -> {
                    createNewGame(boardSize, inARowToWin, true);
                    keepGoing = false;
                }
                case "2" -> {
                    // Set boardsize
                    boardSize = getIntSafe("How large should the board be? \nType 3 for a 3*3 board.", 3, 50);
                    //  printMultiPlayerMenu(boardSize, inARowToWin);
                }
                case "3" -> {
                    inARowToWin = getIntSafe("How many in a row do you need to win?", 3, boardSize);
                    // Set How many in a row
                    //    printMultiPlayerMenu(boardSize, inARowToWin);
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

    public void printMultiPlayerMenu(int boardSize, int inARowToWin) {
        System.out.println("""
                       Multiplayer Menu
                    ---------------------   
                    1: Start
                    2: Set Board size (Set now: %s)
                    3: Set How many in a row to win (Set now: %s)
                    0: Back
                """.formatted(boardSize, inARowToWin));
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
