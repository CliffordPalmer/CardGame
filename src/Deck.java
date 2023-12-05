import java.util.ArrayList;
import java.lang.Math;
public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] points){
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++){
            for (String suit : suits) {
                cards.add(new Card(ranks[i], suit, points[i]));
            }
        }
        cardsLeft = cards.size();
        shuffle();
    }

    public boolean isEmpty(){
        if(cardsLeft == 0){
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal(){
        if (cards.isEmpty()){
            return null;
        }
        cardsLeft --;
        return cards.get(cardsLeft);
    }

    public void shuffle(){
        Card temp;
        for (int i = cards.size() - 1; i > 0; i--){
            int r = (int)(Math.random() * i);
            temp = cards.get(r);
            cards.set(r, cards.get(i));
            cards.set(i, temp);
        }
        cardsLeft = cards.size();
    }

    public void test(){
        for (Card card : cards){
            System.out.println(card);
        }
    }
}
