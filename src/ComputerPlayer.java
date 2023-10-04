import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player{

    public ComputerPlayer(String name, char sign){
        super(name, sign);

    }

    @Override
    public int getMove(ArrayList<Integer> indexOfOpenCells){
        Random rand = new Random();
        int indexOfRandomMove = rand.nextInt(indexOfOpenCells.size());

        return indexOfOpenCells.get(indexOfRandomMove) +1;
    }

}
