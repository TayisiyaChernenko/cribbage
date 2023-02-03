package com.company;
import java.util.ArrayList;

public class Player {
    ArrayList<Card> hand;
    String name = null;
    int pegAt = 0;

    public Player() {
        this.hand = new ArrayList<Card>();
    }

    public Player(String name){
        this.hand = new ArrayList<Card>();
        this.name = name;
    }


    public ArrayList<Card> getHand() {
        return hand;
    }
    public Card removeCard(int index){
        return hand.remove(index);
    }
}