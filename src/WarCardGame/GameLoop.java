package WarCardGame;

import java.util.*;

public class GameLoop {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // CardMap so that all so that card strings can be compared to another and one is greater than another.
       HashMap<String, Integer> CardMap = new HashMap<>();
       String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}; // ranks of cards
       String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"}; // suits.
        int i = 2; // used to assign number value to cards
        for (String rank : ranks) { // nest loops to assign each rank to each suit and then give a value int Map
            for (String suit : suits) {
                CardMap.put(rank + " of " + suit, i);
            }
            i++;
        }
        //test cases ignore
//        System.out.println(CardMap.get("5 of Diamonds"));
//        System.out.println(CardMap.get("King of Spades"));

        boolean Player1 = false;
        boolean Player2 = false;
        Stack<String> player1deck = new Stack<>();
        Stack<String> player2deck = new Stack<>();
        Stack<String> player1discard = new Stack<>();
        Stack<String> player2discard = new Stack<>();

        while (Player1 && Player2) {
            //TODO - check if players stacks are empty if so empty
            //TODO - discard stack into normal stack and call shuffle
            System.out.println("Enter Flip to play a card for the round");
            if (scnr.next().equalsIgnoreCase("Flip")) {
                int flipP1 = CardMap.get(player1deck.peek());
                int flipP2 = CardMap.get(player2deck.peek());
                String P1card = player1deck.pop();
                String P2card = player2deck.pop();

                if (flipP1 > flipP2) {
                    System.out.println("Player 1 wins this round!");
                    System.out.println(P1card + " is bigger than " + P2card);
                    System.out.println(P1card + " goes to Player 1's discard " + P2card);
                    player1discard.push(P1card);
                    player1discard.push(P2card);
                    if (player1deck.size() == 52) {
                        System.out.println("Player 1 has won the game, they have acquired all 52 cards");
                    }

                } else if (flipP1 < flipP2) {
                    System.out.println("Player 2 wins this round!");
                    System.out.println(P2card + " is bigger than " + P1card);
                    System.out.println(P2card + " goes to Player 1's discard " + P1card);
                    player2discard.push(P1card);
                    player2discard.push(P2card);
                } else {
                    //TODO - create tie rules
                }

            } else {
                System.out.println("Do you wish to quite the game? If so Enter quite.");
                if (scnr.next().equalsIgnoreCase("quite")) {
                    System.out.println("Game Over...");
                    break;
                }
            }
        }
    }
}
