import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameUI gameUI = new GameUI();

        System.out.println("""
                ________________________________               
                    
                      Welcome to TicTacToe
                       
                ________________________________        
                        """);
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("""
                            Main Menu
                        ---------------------
                        1: Multiplayer
                        2: Single player - Only random
                        0: Quit
                    """);
            String input = sc.nextLine();

            switch (input) {
                case "1" -> {
                   gameUI.multiPlayerMenu();
                }
                case "2" -> {
                   gameUI.createNewGame(3, 3, false);
                }
                case "0" -> {
                    keepGoing = false;
                }
                default -> {
                    System.out.println("Invalid input");
                }

            }
        }

        //gameUI.mainMenu();


        System.out.println("Thanks for playing!");
    }
}