public class Card {

    private String rank;
    private String suit;
    private int point;

    public Card(String rank, String suit, int point){
        this.rank = rank;
        this.suit = suit;
        this.point = point;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getPoint() {
        return point;
    }

    public String toString(){
        return rank + " of " + suit;
    }
}
