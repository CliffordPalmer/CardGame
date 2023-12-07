import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    final private String[] suits = {"Clubs", "Spades", "Diamonds", "Hearts"};
    final private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    final private int[] points = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private ArrayList<Player> players;
    private Scanner input;
    public static void main(String[] args) {
        Game g1 = new Game();
        g1.playGame();
    }

    public void setUp(){
        players = new ArrayList<>();
        input = new Scanner(System.in);
        while(true){
            System.out.println("Do you wish to add another player? (y/n)");
            String response = input.nextLine();
            if(response.equals("y")){
                System.out.println("What is the players name?");
                players.add(new Player(input.nextLine()));
            }
            if(response.equals("n")){
                System.out.println("Great! Let's begin!");
                break;
            }
        }
    }
    public void playGame(){
        setUp();
        Deck d1 = new Deck(ranks, suits, points);

        //d1.test();

        while(!d1.isEmpty()){
            for (Player player : players){
                player.addCard(d1.deal());
                if (d1.isEmpty()){
                    break;
                }
            }
        }
        for(int i = 0; i < 100; i++){
            System.out.println("round" + (i+1));
            playRound();
        }

    }

    public void playRound(){
        ArrayList<Card> pool = new ArrayList<Card>();
        ArrayList<Card> spoils = new ArrayList<Card>();
        for(Player player : players){
            pool.add(player.removeCard(player.getHand().size() - 1));
        }
        for(Card card : pool){
            System.out.println(card);
        }
        System.out.println();

        while(true){
            for (Card card : pool){
                spoils.add(card);
            }

            ArrayList<Player> winners = getWinner(pool, players);
            if (winners.size() == 1){
                for(Card card : spoils) {
                    winners.get(0).addCard(0, card);
                }
                System.out.println(spoils);
                break;
            }
            pool = new ArrayList<Card>();
            if (winners.size() > 1){
                for (Player player : winners){
                    for(int i = 0; i < 3; i++){
                        spoils.add(player.removeCard(player.getHand().size()- 1));
                    }
                    pool.add(player.removeCard(player.getHand().size()- 1));
                }
                System.out.println("tie");
            }
            getWinner(pool, winners);

        }

    }

    public ArrayList<Player> getWinner(ArrayList<Card> pool, ArrayList<Player> players){
        int maxPoints = 0;
        ArrayList<Player> winners = new ArrayList<Player>();
        for (Card card: pool){
            if (card.getPoints() > maxPoints){
                maxPoints = card.getPoints();
            }
        }
        for (int i = 0; i < pool.size(); i++){
            if (pool.get(i).getPoints() == maxPoints){
                winners.add(players.get(i));
            }
        }
        return winners;
    }
}

