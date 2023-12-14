public class Card {
    // Instance variables
    private String rank;
    private String suit;
    private int point;

    // Constructor for Cards
    public Card(String rank, String suit, int point) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
    }

    // Returns the rank of the card
    public String getRank() {
        return rank;
    }

    // Returns the suit of the card
    public String getSuit() {
        return suit;
    }

    // Returns the point value of the card
    public int getPoints() {
        return point;
    }

    // Returns a String containing all the information of the card
    public String toString() {
        return rank + " of " + suit;
    }
}