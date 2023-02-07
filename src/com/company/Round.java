package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Round {
    // each round, the crib is made for the dealer
    ArrayList<Card> crib = new ArrayList<>();
    //pile of played cards, with the newest card on "top" being index 0
    ArrayList<Card> pile = new ArrayList<>();
    int gameCounter =  0;
    Deck deck;
    Card flipped;
    Visualizer v = new Visualizer();
    Scanner kb = new Scanner(System.in);


    public Round(Deck deck) {
        this.deck = deck;
    }

    // needs to allow players to take turns putting cards onto the pile
    Boolean turn(Player currPlayer) {
        // the cards that the player can use without going over 31
        ArrayList<Card> playableCards = new ArrayList<>();
        for (int i = 0; i < currPlayer.getHand().size(); i++) {
            if (currPlayer.getHand().get(i).getVal() + gameCounter <= 31) {
                playableCards.add(currPlayer.getHand().get(i));
            }
        }
        if (playableCards.size() > 0) {
            v.displayHand(playableCards, currPlayer.name + ", the cards you can play are ");
            System.out.println(currPlayer.name + " , Pick a card to play (Card #)>>>");
            int choice = validateCardSelection(currPlayer.getHand(), kb.nextInt()-1);
            //removes card from hand and place in pile
            pile.add(0, currPlayer.removeCard(choice));
            gameCounter += pile.get(0).getVal();
            System.out.println("The running total is " + gameCounter);
            // peg points counted for this turn
            //currPlayer.pegAt = Board.pegPoints(pile, pile.get(0));
            return false;
        } else {
            System.out.println( currPlayer.name + " ,you have no valid cards to play, the turn is over");
            return true;
        }
    }

    Integer validateCardSelection(ArrayList<Card> hand, int choice){
        if (0 <= choice && choice < hand.size() ){
            return choice;
        }
        else{
            System.out.println("The number you've entered is not in the range of cards you have. Please select a number between 1 and " + (hand.size()));
            int newChoice = kb.nextInt() -1 ;
            return validateCardSelection(hand, newChoice);
        }
    }
    public void selectFlippedCard(Player p){
        //non-dealer cuts the remaining deck for the
        System.out.println(p.name + " cut the deck (Choose a card # 0-39) >>> ");
        flipped = deck.dealCard(kb.nextInt());
    }
}