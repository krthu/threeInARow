import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player{
    private Board board;
    public HumanPlayer(String name, char sign){
        super(name, sign);
    }
    @Override
    public int getMove() throws Exception{
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int moveIndex = Integer.parseInt(input);
        return moveIndex;
    }

}
