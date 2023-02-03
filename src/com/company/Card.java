package com.company;

public class Card {
    String suit;
    Integer val;

    public Card(String suit, Integer val) {
        this.suit = suit;
        this.val = val;
    }

    public String getSuit() {
        return this.suit;
    }

    public Integer getVal() {
        return this.val;
    }

    public String getValAsString(){
        String v ;
        switch(val) {
            case 10: v = "Jack";
                break;
            case 11 : v = "Queen";
                break;
            case 12 : v = "King";
                break;
            case 13 : v = "Ace";
                break;
            default : v = val.toString();
        }
        return v;
    }
}