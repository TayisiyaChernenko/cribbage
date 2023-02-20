package com.company;

import java.util.ArrayList;

public class Test {
    Visualizer viz = new Visualizer();

    public Test() {
    }

    public void manualVizualization(){
        Card c1 = new Card("Heart", 11);
        viz.displayCard(c1);


        Card c2 = new Card("Heart", 11);
        Card c3 =  new Card("Spade", 1);
        Card c4 =  new Card("Heart", 5);
        Card c5 =  new Card("Diamond", 3);
        Card c6 =  new Card("Spade", 10);
        Card c7 =  new Card("Club", 8);
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(c2);
        hand.add(c3);
        hand.add(c4);
        hand.add(c5);
        hand.add(c6);
        hand.add(c7);
        viz.displayHand(hand, "");
    }

    public void vizualizationDeckDealtHand(){
        Deck d = new Deck();
        Player p =  new Player();
        Player dlr = new Player();
        d.dealHand(dlr, p);
        viz.displayHand(p.getHand(),"");
    }
}
