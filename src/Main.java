import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Introduce the game

        // Ask for players name
        //Create the players
        // Add to Player - array


        // Create the GameBoard

        // Loop until players are done with game
            //Loop over players until someone wins Or Scoreboard is full .
                //Ask player for input
                //Save the input
                //Check for winner
                //if weHaveAWinner
                    //Player gets score
                    // breaks loop
                //else if Game board is full
                    // Print that it is a tie game
                    // Break loop
            //Ask for another game

        Player p1 = new Player("Knut", 'x');
        Player p2 = new Player("Stina", 'O');
        System.out.println(p1);
        System.out.println(p2);
        boolean winner = false;
 //       Player[][] array = new Player[3][3];

        Game game = new Game(3);
        int index = sc.nextInt();
        index = index-1;

        game.placeSign(index, p1.sign);
        System.out.println(game.getGameState());

        index = sc.nextInt();
        index -= 1;
        System.out.println(game.placeSign(index, p2.sign));
        System.out.println(game.getGameState());

//        array[0][2] = p1.sign;
//        array[0][1] = p2.sign;
//        array[0][0] = p1.sign;
//
//        array[1][2] = p1.sign;
//        array[2][1] = p2.sign;
//        array[0][0] = p1.sign;
//
//        array[2][2] = p2.sign;


     //   System.out.println(array[0][0][0]);

        // Print a row


    }


    public static Character[][] getBoard(int sizeOfBoard){
        return new Character[sizeOfBoard][sizeOfBoard];
    }




}