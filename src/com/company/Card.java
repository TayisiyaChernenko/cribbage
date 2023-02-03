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
}