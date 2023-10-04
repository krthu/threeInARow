import java.util.Scanner;

public class GameUI {
    private Game game;

    private Player p1;

    private Player p2;

    private Player activePlayer;

    private Scanner sc = new Scanner(System.in);

    public GameUI() {

    }

    public void createNewGame(int boardSize, int inARowToWin, boolean multiplayer) {
        System.out.println("What is the name of player 1:");
        String player1Name = sc.nextLine();

        p1 = new Player(player1Name, 'x');
        if(multiplayer) {
            System.out.println("What is the name of player 2");
            String player2Name = sc.nextLine();
            p2 = new Player(player2Name, 'O');
        }else {
            p2 = new ComputerPlayer("Rando the Comp", 'O');
        }
        // Add to Player - array
        game = new Game(boardSize, inARowToWin);
        startGame();
    }



    public void startGame() {
        System.out.println("First to get " + game.getNumberInARowToWin() + " in a row gets a point.");
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
                    // Gets a valid move own function?
                    System.out.println(activePlayer.getName() + ": It´s your turn.");
                 //   int index = getIntSafe((activePlayer.getName() + ": It´s your turn."), 1, game.getBoardSize() * game.getBoardSize());
                    try{
                        int index = activePlayer.getMove(game.getIndexOfOpenCells());

                    index = index - 1;

                    validMove = game.placeSign(index, activePlayer.sign);

                    if (validMove) {
                        winner = game.doWeHaveAWinner(activePlayer.sign, index);
                    } else {
                        System.out.println("Square already taken!");

                    }
                    }catch (Exception e){
                        System.out.println("Need to be a Integer between 1-"+game.getBoardSize()*game.getBoardSize() );

                    }
                }
                System.out.println(game.getGameState());
                // Moved outside loop to show more clearly that no move was made.

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
            // Not reseting player so loser goes first.

            printScoreSummary();
            System.out.println("Do you want to go again?");
            System.out.println("y for again. Anything else to quit");
            game.resetBoard();
            again = sc.nextLine();
        }

    }

    public void printMainMenu(){
        System.out.println("""
            ________________________________               
                
                  Welcome to TicTacToe
                   
            ________________________________        
                    """);
        System.out.println("""
                        Main Menu
                    ---------------------
                    1: Multiplayer
                    2: Single player - Only random
                    0: Quit
                """);
        while (true){
            String input = sc.nextLine();

            switch (input){
                case "1" -> {
                    multiPlayerMenu();
                }
                case "2" -> {
                    createNewGame(3,3, false);
                }
                case "0" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid input");
                }

            }
        }

    }

    public void multiPlayerMenu(){
        int boardSize = 3;
        int inARowToWin = 3;
        printMultiPlayerMenu(boardSize,inARowToWin);
        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "1" -> {
                    createNewGame(boardSize, inARowToWin, true);
                }
                case "2" -> {
                    // Set boardsize
                     boardSize = getIntSafe("How large should the board be? \nType 3 for a 3*3 board.", 3);
                    printMultiPlayerMenu(boardSize,inARowToWin);
                }
                case "3" -> {
                    inARowToWin = getIntSafe("How many in a row do you need to win?", 3, boardSize);
                    // Set How many in a row
                    printMultiPlayerMenu(boardSize,inARowToWin);
                }
                case "0" -> {
                    printMainMenu();
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }
       public void  printMultiPlayerMenu(int boardSize, int inARowToWin){
            System.out.println("""
                       Multiplayer Menu
                    ---------------------   
                    1: Start
                    2: Set Board size (Set now: %s)
                    3: Set How many in a row to win (Set now: %s)
                    0: Back
                """.formatted(boardSize, inARowToWin));
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
