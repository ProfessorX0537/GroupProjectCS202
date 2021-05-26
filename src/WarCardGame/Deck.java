package WarCardGame;

import java.util.*;
public class Deck {
    public static Stack<String> deckStack = new Stack<>();

    public Deck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        int i = 2;
        for (String rank: ranks) {
            for (String suit: suits) {
                deckStack.add(rank + " of " + suit);
            }
            i++;
        }
    }

    public Stack<String> getDeckStack() {
        return deckStack;
    }

    public int getSize() {
        return deckStack.size();
    }

    public void printStack() {
        System.out.println(deckStack);  //Print the current deck's state
    }

    public void shuffle() {
        Collections.shuffle(deckStack);
    }

    public void splitDeck(Stack<String> playerOne, Stack<String> playerTwo) {
        Stack<String> tempStack = new Stack<>();

        for (int i = 0; i < 26; i++) {  //INITIALIZING PLAYER ONE
            String word = deckStack.pop();
            playerOne.push(word);
            tempStack.push(word);
        }
        int size = deckStack.size();
        for (int i = 0; i < size; i++) {  //INITIALIZING PLAYER ONE
            String word = deckStack.pop();
            playerTwo.push(word);
            tempStack.push(word);
        }

        for (String word: tempStack) {  //REINTIALIZING MAIN DECK
            deckStack.push(word);
        }



    }


}
