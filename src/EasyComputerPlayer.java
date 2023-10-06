import java.util.ArrayList;
import java.util.Random;

public class EasyComputerPlayer extends Player {
    private ArrayList<Integer> indexOfAvailableMoves;

    public EasyComputerPlayer(String name, char sign, ArrayList<Integer> indexOfAvailableMoves) {
        super(name, sign);
        this.indexOfAvailableMoves = indexOfAvailableMoves;
    }

    @Override
    public int getMove() {
        Random rand = new Random();
        int indexOfRandomMove = rand.nextInt(indexOfAvailableMoves.size());
        return indexOfAvailableMoves.get(indexOfRandomMove);
    }

}

