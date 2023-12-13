import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    final private String[] suits = {"Clubs", "Spades", "Diamonds", "Hearts"};
    final private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    final private int[] points = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private ArrayList<Player> players;
    private Scanner input;
    private boolean isGameOver;
    private ArrayList<Card> pool = new ArrayList<Card>();
    private ArrayList<Card> spoils = new ArrayList<Card>();
    public static void main(String[] args) {
        Game g1 = new Game();
        g1.playGame();
    }

    // Initializes variables, and sets the amount of players and their names
    public void setUp(){
        // Initializing variables
        players = new ArrayList<>();
        isGameOver = false;
        input = new Scanner(System.in);

        // Collects the players and their names
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
        // Initialize variables and players
        setUp();
        Deck d1 = new Deck(ranks, suits, points);

        // Deal out the deck until empty
        while(!d1.isEmpty()){
            for (Player player : players){
                player.addCard(d1.deal());
                if (d1.isEmpty()){
                    break;
                }
            }
        }

        int i = 0;
        while (isGameOver == false){
            while(true) {
                System.out.println("Would you like to play the next round? (y/n)");
                String response = input.nextLine();
                if (response.equals("y")) {
                    break;
                }
            }
            System.out.println("round" + (i+1));
            playRound(players);

            checkLoss();
            if(checkWin()){
                isGameOver = true;
                break;
            }
            for(Player player : players){
                System.out.println(player.getName() + " has " + player.getHand().size() + " cards");
            }
            i++;
        }

    }

    public void playRound(ArrayList<Player> roundPlayers){
        // Create ArrayLists to hold the war spoils and current cards in play
        pool = new ArrayList<Card>();
        // Add each player's top card into play
        for(Player player : roundPlayers){
            pool.add(player.removeCard(player.getHand().size() - 1));
        }

        // Print the cards in play
        for (int i = 0; i < pool.size(); i++){
            System.out.println(roundPlayers.get(i).getName() + ": " + pool.get(i));
        }
        System.out.println();

        // Plays the round and any war rounds until broken out of

        // Add the cards in play to the war spoils
        for (Card card : pool){
            spoils.add(card);
        }

        // Get the winners of the current cards in play
        ArrayList<Player> winners = getWinner(pool, roundPlayers);

        // If only one winner, they win the entire war spoils, and this while loop is broken
        if (winners.size() == 1){
            for(Card card : spoils) {
                winners.get(0).addCard(0, card);
            }
            System.out.println(winners.get(0).getName() + " wins" + spoils);
            spoils = new ArrayList<>();
            return;
        }

        // Otherwise, create a new pool for the next
        System.out.println("War!");
        for (Player player : winners){
            for(int i = 0; i < 3; i++){
                if (checkLoss()){
                    return;
                }
                spoils.add(player.removeCard(player.getHand().size()- 1));
            }
        }
        playRound(winners);
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

    public boolean checkLoss(){
        for(Player player : players){
            if (player.getHand().size() == 0){
                System.out.println(player.getName()+ " is out!");
                players.remove(player);
                return true;
            }
        }
        return false;
    }

    public boolean checkWin(){
        if (players.size() == 1){
            System.out.println("Game Over! " + players.get(0).getName() + " wins!");
            return true;
        }
        return false;
    }
}

