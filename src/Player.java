import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private int score;

    protected char sign;

    public Player(String name, char sign){
        this.name = name;
        this.sign = sign;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(){
        score++;
    }

    public int getMove(ArrayList<Integer> indexOfOpenCells) throws Exception{
        Scanner sc = new Scanner(System.in);
        while (true){
            String input = sc.nextLine();
            //try {
                int index = Integer.parseInt(input);
                return index;
         //   }catch (Exception e){
         //       System.out.println("Needs to be a integer.");
         //   }
        }


    }

    public String toString(){
        return "Name: " + name + " Score: " + score + " Sign: " + sign;
    }

}
