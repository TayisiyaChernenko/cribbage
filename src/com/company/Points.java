package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//calculates points for a game
public class Points {
    Round r;

    public Points(Round round) {
        this.r = round;
    }


    public int calculateRun(ArrayList<Card> hand){
        int total = 0;
        ArrayList<Integer> values = new ArrayList<>();
        for (Card card : hand){
            values.add(card.getVal());
        }
        values.add(r.getFlipped().getVal());
        Collections.sort(values);

        // calculate run


        return total;
    }

    public int calculateRunHelper(){
        return 0;

    }


    public int calculatedSuitPoints(ArrayList<Card> hand){
        // if the player has the Jack with the same suit as the starter card, they get a point
        Card flipped = r.getFlipped();
        int jackOfSameSuit = 0;
        String starterSuit = flipped.getSuit();
        Card matchingJack = new Card(starterSuit,10);
        if (hand.contains(matchingJack)){
            jackOfSameSuit = 1;
        }

        HashMap<String, Integer> suits = ofASuit(hand);
        //if a player's hand and the flipped card are all the same suit, they score 5 points
        if (suits.size() == 1 && suits.containsKey(flipped.getSuit())){
            return 5 + jackOfSameSuit;
        }
        // if a player's hand is all the same suit, they score 4 points
        else if (suits.size() == 1){
            return 4 + jackOfSameSuit;
        }
        return jackOfSameSuit;
    }

    public int calculatedPairings(ArrayList<Card> hand) {
        HashMap<Integer, Integer> pairings = ofAValue(hand);
        int total = 0;
        for (int value : pairings.keySet()){
            switch(value){
                case 4:
                    total +=12;
                    break;
                case 3:
                    total +=6;
                    break;
                case 2:
                    total +=2;
                    break;
                default:
                    break;
            }
        }
        return total;
    }

    public HashMap<String,Integer> ofASuit(ArrayList<Card> hand){
        HashMap<String, Integer> ofAKind = new HashMap<>();
        for(Card card : hand){
            String suit = card.getSuit();
            if(ofAKind.containsKey(suit)){
                ofAKind.put(suit,ofAKind.get(suit) + 1);
            }
            else{
                ofAKind.put(suit,1);
            }
        }
        return ofAKind;
    }



    public HashMap<Integer,Integer> ofAValue(ArrayList<Card> hand){
        HashMap<Integer, Integer> ofAKind = new HashMap<>();
        for(Card card : hand){
            int cardVal = card.getVal();
            if(ofAKind.containsKey(cardVal)){
                ofAKind.put(cardVal,ofAKind.get(cardVal) + 1);
            }
            else{
                ofAKind.put(cardVal,1);
            }
        }
        return ofAKind;
    }

    public int calculateHand(Player player){
        int totalHandPoints = 0;
        totalHandPoints += calculatedPairings(player.getHand());
        //4-flush and 5-flush, Jack suit matches starting card
        totalHandPoints += calculatedSuitPoints(player.getHand());

        return totalHandPoints;


    }

    public int addsToFifteen(){
        return 2;
    }


}
