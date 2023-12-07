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
        playRound();

    }

    public void playRound(){
        ArrayList<Card> pool = new ArrayList<Card>();
        ArrayList<Card> spoils;
        for(Player player : players){
            pool.add(player.removeCard(player.getHand().size() - 1));
        }
        for(Card card : pool){
            System.out.println(card);
        }

        while(true){
            Card winner = getWinner(pool);

            if (winner == null){
                spoils = pool;
                pool = new ArrayList<Card>();

            }
            else{
                break;
            }
        }

    }

    public ArrayList<Card> getWinner(ArrayList<Card> pool){
        Card winner = pool.get(0);
        ArrayList<Integer> tiePoints = new ArrayList<Integer>();
        for(int i = 0; i < pool.size(); i++){
            for(int j = i + 1; j < pool.size(); j++){
                if(pool.get(i).getPoints() == pool.get(j).getPoints()){
                    tiePoints.add(pool.get(i).getPoints());
                }
            }
        }
        for (Card card: pool){
            if (card.getPoints() > winner.getPoints()){
                winner = card;
            }
        }
        ArrayList<Card> winners = new ArrayList<Card>();
        winners.add(winner);
        return winners;
    }

    public ArrayList<Player> getTiedPlayers(ArrayList<Card> pool){

    }
}

