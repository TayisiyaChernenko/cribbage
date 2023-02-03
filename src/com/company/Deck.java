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

}
