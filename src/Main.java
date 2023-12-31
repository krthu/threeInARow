import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();

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
                        2: Single player
                        0: Quit
                    """);
            String input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    game.multiPlayerMenu();
                }
                case "2" -> {
                    game.singlePlayerMenu();
                }
                case "0" -> {
                    keepGoing = false;
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
        System.out.println("Thanks for playing!");
    }
}