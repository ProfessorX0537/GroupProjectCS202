package WarCardGame;

import com.sun.security.jgss.GSSUtil;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.*;

public class GameLoop {

                                        // CardMap
    // - STORES THE VALUES FOR EACH Card Type (Ace of Spades, 2 of Diamonds, etc.)
    private static HashMap<String, Integer> CardMap = new HashMap<>();
            /* PURPOSE:
                * USED TO COMPARE EACH CARD OF EACH PLAYER
            */
//***-------------------------------------------------------------------------------------------------------------------------------------------***//
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
                                        //RANK AND SUITS
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}; // Stores the ranks of cards
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"}; // Stores the type of suits.
                /* PURPOSE:
                    * USED TO INITIALIZE CARD MAP WITH STRING KEYS (Ace of Spades, 2 of Diamonds, etc.)
                    * USED TO INITIALIZE EACH STRING KEY THEIR CORRESPONDING VALUES BETWEEN 2 TO 14.  2 = lowest and 14 highest
                */
    //-------------------------------------------------------------------------------------------------------------------------------------------//
                                        //INITIALIZING CardMap
                            // Keys = ranks + " of " + suit  :::: Value = 2, 3, 4, 5 etc.
        int i = 2;
        for (String rank : ranks) {
            for (String suit : suits) {
                CardMap.put(rank + " of " + suit, i);
            }
            i++;
        }
    //-------------------------------------------------------------------------------------------------------------------------------------------//
                            //CREATING STACKS FOR PLAYERS-- PURPOSE: TO STORE THEIR CARDS
        Stack<String> player1deck = new Stack<>();
        Stack<String> player2deck = new Stack<>();
                            //CREATING STACKS FOR PLAYERS-- PURPOSE: TO STORE EACH PLAYER'S DISCARDED CARDS EACH ROUND
        Stack<String> player1discard = new Stack<>();
        Stack<String> player2discard = new Stack<>();

    //-------------------------------------------------------------------------------------------------------------------------------------------//
                                        //CREATING DECK CLASS
        Deck mainDeck = new Deck();
            /*DECK CLASS METHODS
                * getDeckStack() -- RETURNS A Stack STORED IN THE DECK CLASS (CONTAINS 52 STRING VALUES- REPRESENTS EACH CARD TYPE)
                * getSize()      -- RETURNS OF THE SIZE OF THE Stack STORED IN THE DECK CLASS
                * printStack()   -- PRINTS THE Stack STORED IN THE DECK CLASS
                * shuffle()      -- SHUFFLES THE Stack STORED IN THE DECK CLASS

                * splitDeck(Stack<String> s1, Stack<String> s2)
                                 -- SPLIT THE VALUES STORED IN THE Stack OF THE DECK CLASS IN TO TWO STACKS PASSED AS PARAMETERs
                * shuffleDeck(Stack<String> s)
                                 -- SHUFFLES THE Stack PASSED AS A PARAMETER
            */

    //-------------------------------------------------------------------------------------------------------------------------------------------//
                                        //GAME START UP
                            System.out.println("GAME STARTING...");  //CONSOLE NOTIFIER
        //WAIT TIME (SOLELY FOR STYLE)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //NOTIFY THE PLAYER HOW TO PLAY THE GAME
        System.out.println("\t\t\t\t\t\t\t\tHOW TO PLAY WAR GAME: \n" +
                "YOU HAVE TWO CHOICES: \"FLIP\" OR \"QUITE\"\n" +
                "\tENTER \"FLIP\" TO PLAY THE NEXT ROUND\n" +
                "\tENTER \"QUITE\" TO END THE GAME\n\n" +
                "THERE ARE TWO PLAYERS, EACH PLAYER HAS A DECK OF CARDS CONTAINING 26 CARDS.\n" +
                "EACH ROUND THE FIRST CARD ON TOP OF THEIR DECKS ARE COMPARED.\n" +
                "THE PLAYER THAT HAS A CARD WITH A HIGHER VALUE WINS THE ROUND,\n" +
                "AND OBTAIN THE LOSING PLAYER'S CARD. IF THERE IS A TIE, \n" +
                "EACH PLAYER PULLS THREE CARDS OF THEIR DECK. THEN THIRD CARD \n" +
                "THEY PULL IS COMPARED, AND WHOEVER HAS THE CARD WITH A HIGHER \n" +
                "VALUE WINS, AND OBTAIN THE LOSING PLAYER'S CARDS (4 CARDS IN TOTAL.\n" +
                "THE FIRST CARD, AND THE LAST THREE CARDS PULLED)\n" +
                "THE GAME ENDS WHEN ONE PLAYER HAS NO MORE CARDS TO PLAY.\n");

        //WAIT TIME (SOLELY FOR STYLE)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //SHUFFLING CARDS-- PURPOSE: MAKE SURE THE DECK IS SHUFFLED BEFORE SPLIT INTO EACH PLAYER'S STACK OR DECK
        System.out.println("Shuffling Cards...");
        mainDeck.shuffle();

        //WAIT TIME (SOLELY FOR STYLE)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //SPLITTING CARDS BETWEEN PLAYER 1 AND PLAYER 2'S DECKS
        mainDeck.splitDeck(player1deck, player2deck);

        //NOTIFYING PLAYER OF SPLIT AND START OF ROUND
        System.out.println("Splitting Decks... \n\n\t\t\t\t\t\t\t\tGAME STARTED\n");

        //WAIT TIME (SOLELY FOR STYLE)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    //-------------------------------------------------------------------------------------------------------------------------------------------//
                                        //STARTING GAME LOOP
        while (!gamesLoop(player1deck, player2deck, player1discard, player2discard, mainDeck)) {
            //CHECKS IF EACH PLAYERS DECKS ARE EMPTY, SHUFFLES THEM, AND ADD THEM TO THEIR CURRENT STACK/DECK
            if (player1deck.isEmpty()) {
                System.out.println("SHUFFLING DISCARD DECK FOR PLAYER 1");
                mainDeck.shuffleDeck(player1discard);
                while(!player1discard.isEmpty()) {
                    player1deck.push(player1discard.pop());
                }
            }
            if (player2deck.isEmpty()) {
                System.out.println("SHUFFLING DISCARD DECK FOR PLAYER 2");
                mainDeck.shuffleDeck(player2discard);
                while(!player2discard.isEmpty()) {
                    player2deck.push(player2discard.pop());
                }
            }

                        //ASKING PLAYER INPUT-- FLIP TO PLAY ROUND, QUITE TO END GAME.
            System.out.println("Enter \"Flip\" to play a card for the next round");

            //STORE PLAYER'S INPUT EACH ROUND
            String userInput = scnr.next();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");


            //IF THE PLAYER'S INPUT IS NOT Flip OR Quite ASK FOR A DIFFERENT INPUT-- NOT CASE SENSTIVE
            while (!userInput.equalsIgnoreCase("Flip")) { // runs loop while user input isn't flip
                if (userInput.equalsIgnoreCase("Quite")) { // break out of while loop if player types quite
                    break;
                }
                System.out.println("WRONG INPUT\nPlease enter \"Flip\" to play the next round or enter \"Quite\" to end game.");
                userInput = scnr.next(); // asks user to try again
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }

            //IF INPUT IS QUITE IT ENDS THE GAME, IF NOT, CONTINUES LOOPING
            if (userInput.equalsIgnoreCase("Quite")) { // if user wants to quite this will end program
                System.out.println("Game over...");
                break;
            }
        }
    }
//***-------------------------------------------------------------------------------------------------------------------------------------------***//
                                                //GAME MAIN SYSTEM/MECHANIC
            /* gamesLoop()
            *   PURPOSES
                    * RETURN TRUE IF DETECTS A WINNER AND ENDS GAME
                    * ...
            */
    public static boolean gamesLoop(Stack<String> player1deck, Stack<String> player2deck, Stack<String> player1discard,
                                    Stack<String> player2discard, Deck mainDeck) {
        if (player2deck.isEmpty()) { // checks to see if the player has won
            System.out.println("Player 1 has won the game, they have acquired all 52 cards");
            return true; // wins game
        }
        if (player1deck.isEmpty()) { // checks to see if the player has won
            System.out.println("Player 2 has won the game, they have acquired all 52 cards");
            return true; // wins game
        }

        //flipP1 AND flipP2 USED TO COMPARE THE VALUE OF THE CARDS OF EACH PLAYER DURING THE ROUND USING CardMap
        int flipP1 = CardMap.get(player1deck.peek()); // PLAYER
        int flipP2 = CardMap.get(player2deck.peek()); // PLAYER TWO

        //TAKES EACH PLAYER'S CARDS FROM THEIR STACK/DECK
        String P1card = player1deck.pop(); // PLAYER ONE
        String P2card = player2deck.pop(); // PLAYER TWO

        //PRINT THE CARDS OF EACH PLAYER DURING THE ROUND
        System.out.println("===========================================================================================\n");
        System.out.println("Player 1's Card is the " + P1card + "\t: \tPlayer 2's Card is the " + P2card + "\n");

        //CHECK WHO HAS THE HIGHEST CARDS OR A TIE
        if (flipP1 > flipP2) {  // checks if player 1's card is greater
            System.out.println("RESULT: " + P1card + " is bigger than " + P2card + "\nNOTICE: " +
                            P1card + " and " + P2card + " goes to Player 1's discard!\n"
                    );
            System.out.println("\t\t\t\tPlayer 1 wins this round!"); // prints round info
            System.out.println("===========================================================================================\n\n");

            player1discard.push(P1card); // puts both cards from the round into the winning players
            player1discard.push(P2card); // discard stack for later

        } else if (flipP1 < flipP2) { // checks if player 2's card is greater
            System.out.println("RESULT: " + P2card + " is bigger than " + P1card + "\nNOTICE: " +
                    P2card + " and " + P1card + " goes to Player 1's discard!\n"
            );
            System.out.println("\t\t\t\tPlayer 2 wins this round!"); // prints round info
            System.out.println("===========================================================================================\n\n");

            player2discard.push(P1card); // puts both cards from the round into the winning players
            player2discard.push(P2card); // discard stack for later

            //System.out.println(player2discard); // (TESTING) to make sure that cards entering discard
        } else { // this means that the cards were the same value
            ArrayList <String> a = new ArrayList<>(); // initialize arraylist
            a.add(P1card); //input current tied cards
            a.add(P2card);

            if (player1deck.size() < 3) { // has to have at least 3 cards to perform tie operation.
                //System.out.println("SHUFFLING DISCARD DECK FOR PLAYER 1");
                mainDeck.shuffleDeck(player1discard);
                while(!player1discard.isEmpty()) {
                    player1deck.push(player1discard.pop());
                }
                if (player1deck.size() + player1discard.size() < 3) { //SPECIAL CASE WHEN PLAYER 1 HAS LESS THAN 3 CARDS TOTAL
                    System.out.println("Player 2 Wins");
                    return true;
                }
            }
            if (player2deck.size() < 3) { // has to have at least 3 cards to perform tie operation.
                //System.out.println("SHUFFLING DISCARD DECK FOR PLAYER 2");
                mainDeck.shuffleDeck(player2discard);
                while(!player2discard.isEmpty()) {
                    player2deck.push(player2discard.pop());
                }
                if (player2deck.size() + player2discard.size() < 3) { //SPECIAL CASE WHEN PLAYER 2 HAS LESS THAN 3 CARDS TOTAL
                    System.out.println("Player 1 Wins");
                    return true;
                }

            }

            for (int i = 0; i < 3; i++) { // flips three cards from each deck into Array
                a.add(player1deck.pop());
                a.add(player2deck.pop());
            }

            if (CardMap.get(a.get(a.size() - 1)) > CardMap.get(a.get(a.size() - 2))) { // if P2 card is greater than P1
                for (int i = 0; i < a.size(); i++) {
                    player2discard.push(a.get(i));
                }
            } else { // player one wins tie
                for (int i = 0; i < a.size(); i++) {
                    player1discard.push(a.get(i));
                }
            }
        }
        return false;
    }
}
/*
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
flip
 */
