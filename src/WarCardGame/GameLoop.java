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

        //CHECKING DECKS
        Deck mainDeck = new Deck();
        mainDeck.printStack();
        mainDeck.shuffle();
        //mainDeck.shuffleDeck();
        mainDeck.printStack();
        System.out.println();

        //GET THE SIZE OF MAIN DECK OR STACK
        System.out.println("Size of Main Deck " + mainDeck.getSize());
        System.out.println();


        //CREATING STACKS FOR PLAYERS
        Stack<String> player1deck = new Stack<>();
        Stack<String> player2deck = new Stack<>();
        System.out.println();

        //Splitting Decks
        mainDeck.splitDeck(player1deck, player2deck);
        System.out.println("Splitting Decks \n");

        //PRINTING THE STACKS OF EACH PLAYER
        System.out.println("THE STACKS OF EACH PLAYER");
        System.out.println("Player One (Size " + player1deck.size() + " ) " + player1deck);
        System.out.println("Player Two (Size " + player1deck.size() + " ) " + player2deck);
        System.out.println()
        ;
        //CHECKING THE MAIN STACK
        System.out.println("\n Printing Deck again...");
        mainDeck.printStack();
        System.out.println("Size of Main Deck " + mainDeck.getSize());

        //START OF GAME
        //SPLIT DECKS
        //RUN GAME
        //CHECK WHO HAS HIGHER HAND
        //IF IT IS TIE
        //POP TILL THE NEXT THIRD CARD  ---> CHECK WHO WON AGAIN
        //IF PLAYER(ONE OR TWO) WINS
        //


        Stack<String> player1discard = new Stack<>();
        Stack<String> player2discard = new Stack<>();

        while (!gamesLoop(player1deck, player2deck, player1discard, player2discard, CardMap)) {
            //TODO - check if players stacks are empty, if so empty
            //TODO - discard stack into normal stack and call shuffle
            System.out.println("Enter \"Flip\" to play a card for the round");
            String userInput = scnr.next();
            if (userInput.equalsIgnoreCase("Flip")) {
                continue;
            } else {
                while (!userInput.equalsIgnoreCase("Flip") || !userInput.equalsIgnoreCase("Quite")) {
                    System.out.println("Please enter \"Flip\" to play the next round or enter \"Quite\" to end game.");
                    userInput = scnr.next();
                    if (userInput.equalsIgnoreCase("Flip") || userInput.equalsIgnoreCase("Quite")) {
                        break;
                    }
                }
            }

            if (userInput.equalsIgnoreCase("Flip")) {
                gamesLoop(player1deck, player2deck, player1discard, player2discard, CardMap);
            } else {
                System.out.println("Game over...");
                break;
            }
        }
    }

    // GAME LOOP MAY NEED TO BE ITS OWN METHOD UNSURE FOR NOW
    public static boolean gamesLoop(Stack<String> player1deck, Stack<String> player2deck, Stack<String> player1discard,
                                Stack<String> player2discard, HashMap<String, Integer> CardMap) { //
        boolean Player1 = false;
        boolean Player2 = false;

        //if (scnr.next().equalsIgnoreCase("Flip")) {

            int flipP1 = CardMap.get(player1deck.peek()); // collects value of top card according to the map and stores it in for player 1
            int flipP2 = CardMap.get(player2deck.peek()); // collects value of top card according to the map and stores it in for player 2
            String P1card = player1deck.pop(); // takes top card off of player 1's stack and stores it
            String P2card = player2deck.pop(); // takes top card off of player 2's stack and stores it

            if (flipP1 > flipP2) {  // checks if player 1's card is greater
                System.out.println("Player 1 wins this round!"); // prints round info
                System.out.println(P1card + " is bigger than " + P2card);
                System.out.println(P1card + " and " + P2card + " goes to Player 1's discard!");
                player1discard.push(P1card); // puts both cards from the round into the winning players
                player1discard.push(P2card); // discard stack for later
                if (player1deck.size() + player1discard.size() == 52) { // checks to see if the player has won
                    System.out.println("Player 1 has won the game, they have acquired all 52 cards");
                     return true; // wins game
                }

            } else if (flipP1 < flipP2) { // checks if player 2's card is greater
                System.out.println("Player 2 wins this round!"); // prints round info
                System.out.println(P2card + " is bigger than " + P1card);
                System.out.println(P1card + " and " + P2card + " goes to Player 2's discard!");
                player2discard.push(P1card); // puts both cards from the round into the winning players
                player2discard.push(P2card); // discard stack for later
                if (player2deck.size() + player2discard.size() == 52) { // checks to see if the player has won
                    System.out.println("Player 2 has won the game, they have acquired all 52 cards");
                    return true; // wins game
                }
            } else { // this means that the cards were the same value
                //TODO - create tie rules
                // probably needs two temp stacks to hold extra cards
                // flip 3 cards the 3rd card decides who wins the "war"
                // winning player collects all cards flipped. go to discard stack
            }
       // }
        return false;
    }
}
