import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    final private String[] suits = {"Clubs", "Spades", "Diamonds", "Hearts"};
    final private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack",
            "Queen", "King"};
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
    public void setUp() {
        // Initializing variables
        players = new ArrayList<>();
        isGameOver = false;
        input = new Scanner(System.in);

        // Collects the players and their names
        while (true) {
            System.out.println("Do you wish to add another player? (y/n)");
            String response = input.nextLine();
            if (response.equals("y")) {
                System.out.println("What is the players name?");
                players.add(new Player(input.nextLine()));
            }
            if (response.equals("n")) {
                System.out.println("Great! Let's begin!");
                break;
            }
        }
    }

    public void playGame() {
        // Print out the instructions for the game
        printInstructions();
        // Initialize variables and players
        setUp();
        Deck d1 = new Deck(ranks, suits, points);

        // Deal out the deck until empty
        while (!d1.isEmpty()) {
            for (Player player : players) {
                player.addCard(d1.deal());
                if (d1.isEmpty()) {
                    break;
                }
            }
        }

        int i = 0;
        while (isGameOver == false) {
            while (true) {
                System.out.println("Would you like to play the next round? (y/n)");
                String response = input.nextLine();
                if (response.equals("y")) {
                    break;
                }
            }
            System.out.println("round" + (i + 1));
            playRound(players);

            checkLoss();
            if (checkWin()) {
                isGameOver = true;
                break;
            }
            for (Player player : players) {
                System.out.println(player.getName() + " has " + player.getHand().size() + " cards");
            }
            i++;
        }

    }

    // Recursive function to handle a round and any subsequent wars that occur during that round
    public void playRound(ArrayList<Player> roundPlayers) {
        // Reset the cards in play to a new ArrayList
        pool = new ArrayList<Card>();
        // Add each player's top card into play
        for (Player player : roundPlayers) {
            pool.add(player.removeCard(player.getHand().size() - 1));
        }

        // Print the cards in play
        for (int i = 0; i < pool.size(); i++) {
            System.out.println(roundPlayers.get(i).getName() + ": " + pool.get(i));
        }
        System.out.println();

        // Add the cards in play to the war spoils
        for (Card card : pool) {
            spoils.add(card);
        }

        // Get the winners of the current cards in play
        ArrayList<Player> winners = getWinner(pool, roundPlayers);

        // If only one winner, they win the entire war spoils, and this while loop is broken
        if (winners.size() == 1) {
            for (Card card : spoils) {
                winners.get(0).addCard(0, card);
            }
            // Print out the winner's winnings
            System.out.println(winners.get(0).getName() + " wins " + spoils);
            // Reset war spoils to a new ArrayList
            spoils = new ArrayList<>();
            return;
        }

        // If there is more than one winner, a war happens!
        System.out.println("War!");
        // Add the top 3 cards of the players' hands into the pool
        for (Player player : winners) {
            for (int i = 0; i < 3; i++) {
                // If any of the players run out of cards, remove that player
                if (checkLoss()) {
                    winners.remove(i);
                }
                //
                spoils.add(player.removeCard(player.getHand().size() - 1));
            }
        }
        // Recursively play another round with just the players in the war
        playRound(winners);
    }

    // Function which takes in the cards in play and the players who played those cards
    // and determines the winner of that round
    public ArrayList<Player> getWinner(ArrayList<Card> pool, ArrayList<Player> players) {
        // Declare and initialize a variable which holds the largest card point value
        int maxPoints = 0;
        // Declare and initializes an ArrayList to hold the winners, which will later be returned
        ArrayList<Player> winners = new ArrayList<Player>();
        // Finds the biggest card in play, and sets maxPoints to the value of that card
        for (Card card : pool) {
            if (card.getPoints() > maxPoints) {
                maxPoints = card.getPoints();
            }
        }
        // Finds any players whose card matches the values of MaxPoints, and adds that player
        // to winners
        for (int i = 0; i < pool.size(); i++) {
            if (pool.get(i).getPoints() == maxPoints) {
                winners.add(players.get(i));
            }
        }
        // Returns the winners
        return winners;
    }

    // Function to check whether any play has lost (run out of cards), and removes that player
    // if so
    public boolean checkLoss() {
        // Iterates through all players, checking if their hand has any cards
        for (Player player : players) {
            // If the player's hand is empty, remove them from the game and return true
            if (player.getHand().size() == 0) {
                System.out.println(player.getName() + " is out!");
                players.remove(player);
                return true;
            }
        }
        // otherwise, return false
        return false;
    }

    // Function to check whether the game is won (only one player left)
    public boolean checkWin() {
        // If only one player left, let the players know and return true
        if (players.size() == 1) {
            System.out.println("Game Over! " + players.get(0).getName() + " wins!");
            return true;
        }
        // Otherwise, return false
        return false;
    }

    public void printInstructions() {
        System.out.println("Welcome to War! This game is simple: all the players place the top " +
                "card from their hand into the middle, and the player with the highest card wins " +
                "the entire pool. If two players tie for the highest card, a war ensues. When a " +
                "war happens, the players involved each place 3 card face down as the spoils for " +
                "the war, and then each place the next card from their hand in the middle. The " +
                "player with the highest card wins the entire pool including all the spoils. " +
                "If two players tie for first again, another war ensues, and this cycle continues " +
                "until one player has won the entire pool. If you run out of cards, you lose, " +
                "and are removed from the game. The game ends when one player holds the entire " +
                "deck.");
    }
}