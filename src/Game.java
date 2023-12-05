import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    final private static String[] suits = {"Clubs", "Spades", "Diamonds", "Hearts"};
    final private static String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    final private static int[] points = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private static ArrayList<Player> players;
    private static Scanner input;
    public static void main(String[] args) {
        setUp();
        playGame(players);
    }

    public static void setUp(){
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
    public static void playGame(ArrayList<Player> players){

        Deck d1 = new Deck(ranks, suits, points);

        System.out.println("Player one, please enter your name");

        Player p1 = new Player();
        d1.test();

        while(!d1.isEmpty()){

        }

    }
}

