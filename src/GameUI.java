import java.util.Scanner;

public class GameUI {
    private Game game;

    private Player p1;

    private Player p2;

    private Player activePlayer;

    private Scanner sc = new Scanner(System.in);

    public GameUI() {

    }

    public void createNewGame() {

        System.out.println("What is the name of player 1:");
        String player1Name = sc.nextLine();
        System.out.println("What is the name of player 2");
        String player2Name = sc.nextLine();
        p1 = new Player(player1Name, 'x');
        p2 = new Player(player2Name, 'O');
        // Add to Player - array
        int boardSize = getIntSafe("How many row and columns do you want? \nType 3 for a 3*3 board.", 3);
        int inARowToWin = getIntSafe("How many in a row do you need to win?", 3, boardSize);
        System.out.println("First to get " + inARowToWin + " in a row gets a point.");
        game = new Game(boardSize, inARowToWin);
    }

    public void startGame() {
        boolean winner = false;
        int gameRound = 0;
       // Player activePlayer = null;
        String again = "y";
        while (again.equalsIgnoreCase("y")) {
            System.out.println(game.getGameState());
            while (!winner && gameRound != game.getMaxRound()) {

                changeActivePlayer();
                boolean validMove = false;

                // Gets a valid move. And checks winner
                while (!validMove) {

                    int index = getIntSafe((activePlayer.getName() + ": It´s your turn."), 1, game.getBoardSize() * game.getBoardSize());
                    index = index - 1;
                    validMove = game.placeSign(index, activePlayer.sign);
                    if (validMove) {
                        winner = game.doWeHaveAWinner(activePlayer.sign, index);
                    } else {
                        System.out.println("Square already taken!");
                    }
                    System.out.println(game.getGameState());
                }

                gameRound += 1;
            }
            if (winner) {
                winner = false;
                activePlayer.addScore();
                System.out.println("Congratulations " + activePlayer.getName() + " you won!");

            } else {
                System.out.println("The game is Tied");
            }
            gameRound = 0;

            printScoreSummary();
            System.out.println("Do you want to go again?");
            System.out.println("y for again. Anything else to quit");
            game.resetBoard();
            again = sc.nextLine();
        }

    }

    public void changeActivePlayer(){
        if (activePlayer == null || activePlayer == p2) {
            activePlayer = p1;
        } else {
            activePlayer = p2;
        }
    }

    public void printScoreSummary() {
        System.out.println("The score is:");
        System.out.println(p1.getName() + " " + p1.getScore());
        System.out.println(p2.getName() + " " + p2.getScore());
    }

    // Removing this if limit on boardsize. What happens if int is too big?
    public int getIntSafe(String questionToRepeat, int notUnder) {
        while (true) {
            System.out.println(questionToRepeat);
            String input = sc.nextLine();
            try {
                int safeInt = Integer.parseInt(input);
                if (safeInt >= notUnder) {
                    return safeInt;
                }
                System.out.println("Has to be bigger than " + notUnder + ".");

            } catch (Exception e) {
                System.out.println("Has to be a Integer");
            }
        }
    }

    public int getIntSafe(String questionToRepeat, int notUnder, int notOver) {
        if (notOver == notUnder) { // Need to decide what to do here
            return notOver;
        }

        String errorMessage = (notOver == notUnder ? "Has to be " + notUnder : "Has to be between " + notUnder + "-" + notOver + ".");

        while (true) {
            System.out.println(questionToRepeat);
            String input = sc.nextLine();
            try {
                int safeInt = Integer.parseInt(input);
                if (safeInt >= notUnder && safeInt <= notOver) {
                    return safeInt;
                }else {
                    System.out.println(errorMessage);
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }


}
