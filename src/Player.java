import java.util.ArrayList;
public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;

    public Player(String name){
        this.name = name;
        hand = new ArrayList<Card>();
        points = 0;
    }

    public Player(String name, ArrayList<Card> hand){
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int newPoints){
        points += newPoints;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public Card removeCard(int index){
        return hand.remove(index);
    }

    public String toString(){
        String str =  name + " has " + points + "\n" + name + "\'s cards:";
        for (Card card : hand){
            str += card.toString();
        }
        return str;
    }
}