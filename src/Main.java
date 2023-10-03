import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TicTacToe");


        GameUI gameUI = new GameUI();
        gameUI.createNewGame();
        gameUI.startGame();

    }
}