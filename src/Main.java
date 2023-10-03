import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TicTacToe");

        System.out.println("What is the name of player 1:");
        String player1Name = sc.nextLine();
        System.out.println("What is the name of player 2");
        String player2Name = sc.nextLine();
        Player p1 = new Player(player1Name, 'x');
        Player p2 = new Player(player2Name, 'O');

        // Add to Player - array
        System.out.println("How many row and columns do you whant?");
        System.out.println("Type 3 for a 3*3 board.");
        int boardSize = sc.nextInt();
        sc.nextLine();

        System.out.println("How many in a row do you need to win?");
        int inARowToWin = sc.nextInt();
        sc.nextLine();

        Game game = new Game(boardSize, inARowToWin);
        boolean winner = false;
 //       Player[][] array = new Player[3][3];
        int gameRound = 0;
        Player activePlayer = null;

        String again = "y";
        while (again.equalsIgnoreCase("y")) {
            System.out.println(game.getGameState());
            while (!winner && gameRound != game.getMaxRound()) {
                if (activePlayer == null || activePlayer == p2) {
                    activePlayer = p1;
                } else {
                    activePlayer = p2;
                }
                boolean validMove = false;
                while (!validMove) {
                    System.out.println(activePlayer.getName() + ": It´s your turn.");
                    int index = sc.nextInt();
                    sc.nextLine();
                    index = index - 1;
                    validMove = game.placeSign(index, activePlayer.sign);
                    if(validMove){
                       winner = game.doWeHaveAWinner(activePlayer.sign, index);
                    }

                    System.out.println(game.getGameState());
                }
                gameRound += 1;
            }
            if (winner){
                winner = false;
                activePlayer.addScore();
                System.out.println("Congratulations " + activePlayer.getName() + " you won!");

            }else {
                System.out.println("The game is Tied");
            }
            gameRound = 0;

            System.out.println("The score is:");
            System.out.println(p1.getName() + " " + p1.getScore());
            System.out.println(p2.getName() + " " + p2.getScore());
            System.out.println("Do you want to go again?");
            System.out.println("y for again. Anything else to quit");
            again = sc.nextLine();
        }

    }

}