package WarCardGame;

import java.util.*;

public class GameLoop {
    public static void main(String[] args) {

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
        System.out.println(CardMap.get("5 of Diamonds"));
        System.out.println(CardMap.get("King of Spades"));
    }

}
