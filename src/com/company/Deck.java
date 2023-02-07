package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;
    String[] suit = {"Heart", "Spade", "Diamond", "Club"};

    public Deck() {
        this.deck = new ArrayList<Card>();
        for(int value = 1; value <=13 ; value++) {
            for (int i = 0; i < 4; i++) {
                deck.add(new Card(suit[i], value));
            }
        }
        Collections.shuffle(deck, new Random());
    }


    public void dealCard(Player player){
        Card removedCard = deck.remove(0);
        player.getHand().add(removedCard);
    }

    public Card dealCard(int index){
        return deck.remove(index);
    }

     public void dealHand(Player dealer, Player player){
        //both players gets 6 cards initially, discards down to 4 later
        //alternate the dealing of cards between the player and dealer
        for (int i =1; i < 7; i++){
                dealCard(player);
                dealCard(dealer);
            }
    }
     public void dealHand(Player dealer, Player player1, Player player2){
        //each player gets 5 cards initially, discards down to 4 later
         for (int i = 1; i < 6; i++){
             dealCard(player1);
             dealCard(player2);
             dealCard(dealer);
         }
    }

}
