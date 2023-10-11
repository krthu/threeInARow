import java.util.Scanner;

public class HumanPlayer extends Player {
    private Board board;

    public HumanPlayer(String name, char sign) {
        super(name, sign);
    }

    @Override
    public int getMove() throws Exception {
        // Throws so to show out of bounds message in game
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int moveIndex = Integer.parseInt(input);
        return moveIndex - 1;
    }

}
