package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    List<Card> cardDeck;
    String[] suit = {"Heart", "Spade", "Diamond", "Club"};

    public Deck() {
        this.cardDeck = new ArrayList<Card>();
        for(int value = 1; value <=13 ; value++) {
            for (int i = 0; i < 4; i++) {
                cardDeck.add(new Card(suit[i], value));
            }
        }
        Collections.shuffle(cardDeck, new Random());
    }

}
