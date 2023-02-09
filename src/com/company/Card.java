package com.company;

public class Card {
    private final String suit;
    private final Integer val;

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
            case 0 : v = "Ace";
                break;
            default : v = val.toString();
        }
        return v;
    }
}