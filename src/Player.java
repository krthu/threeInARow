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

    public String toString(){
        return "Name: " + name + " Score: " + score + " Sign: " + sign;
    }

}
