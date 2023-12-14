import java.util.ArrayList;
import java.lang.Math;
public class Deck {
    // Instance variables
    private ArrayList < Card > cards;
    private int cardsLeft;

    // Constructor for each Deck
    public Deck(String[] ranks, String[] suits, int[] points) {
        cards = new ArrayList < > ();
        // Adds a card of each rank for each suit
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                cards.add(new Card(ranks[i], suit, points[i]));
            }
        }
        // Records the size of the deck
        cardsLeft = cards.size();
        // Shuffles the deck
        shuffle();
    }

    // Returns true if the deck is empty, and false if it is not
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    // Returns the number of cards left in the deck
    public int getCardsLeft() {
        return cardsLeft;
    }

    // Deals a card
    public Card deal() {
        // If the deck is empty, refuse to deal
        if (cards.isEmpty()) {
            return null;
        }
        // Remove a card from the deck
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    // Shuffles the deck
    public void shuffle() {
        Card temp;
        // Iterates through each card, switching its place with another, random card
        for (int i = cards.size() - 1; i > 0; i--) {
            int r = (int)(Math.random() * i);
            temp = cards.get(r);
            cards.set(r, cards.get(i));
            cards.set(i, temp);
        }
        // Set the size of the deck
        cardsLeft = cards.size();
    }
}