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
        shuffleDeck(deckStack);
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

    public void shuffleDeck(Stack<String> deck) {
        ArrayList<String> a = new ArrayList<>();
        Random rand = new Random();
        rand.nextInt();
        System.out.println(deck.size());
        while(!deck.isEmpty()) { // fills array list with the values in the stack passed to it
         a.add(deck.pop());
        }
        System.out.println(a.size());
        int length = a.size(); // collects size of ArrayList
        for (int i = 0; i < length; i++) {
            int change = i + rand.nextInt(length - i); // gets a random value in arrayList
            swap(a, i, change);
        }
        System.out.println(a.size());
        for (int i = 0; i < length; i++) { // fills deck stack up with the now shuffled values
            deck.push(a.get(i)); // since it is non primitive we shouldn't have to return value
                                 // for deck stack passed in to be shuffled in game loop further testing required.
        }
        System.out.println(deck.size());
    }

    public static void swap(ArrayList<String> a, int i, int change) {
        String temp = a.get(i); // collects temp value at i index
        a.set(i, a.get(change)); // sets index i to the random string at index change
        a.set(change, temp); // sets the change value to the temp index
    }


}
