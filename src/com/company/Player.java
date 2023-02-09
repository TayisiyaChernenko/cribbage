package com.company;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    String name = null;
    private int points = 0;

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

    public void addPoints(int p){
        points += p;
    }

    public int getPoints(){
        return points;
    }


}