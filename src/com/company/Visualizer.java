package com.company;
import java.util.*;
public class Visualizer{
    public Visualizer(){}
    private String getSuit(Card cardToDisplay){
        Map<String, String> stringToSuit = new HashMap<String, String>();
        stringToSuit.put("Heart", "♥");
        stringToSuit.put("Spade", "♠");
        stringToSuit.put("Club", "♣");
        stringToSuit.put("Diamond", "♦");
        return (stringToSuit.get(cardToDisplay.getSuit()));
    }

    public void displayCard(Card cardToDisplay) {
        String suit = getSuit(cardToDisplay);
        String[] ascciCardForm = {"┌────────────┐",("│" + suit + "           │"),("│     " +cardToDisplay.getValAsString().charAt(0) + "      │"), "│            │",("│           " + suit + "│") ,  "└────────────┘"};

        System.out.println(ascciCardForm[0]);
        System.out.println(ascciCardForm[1]);
        for (int i = 0; i <3; i++){
            if (i == 1){
                System.out.println(ascciCardForm[2]);
            }
            else {
                System.out.println(ascciCardForm[3]);
            }
        }
        System.out.println(ascciCardForm[4]);
        System.out.println(ascciCardForm[5]);
    }

    public void displayHand( ArrayList<Card> hand, String message){
        System.out.println("                               " + message);
        System.out.println("                   ___________________________________________");
        String[] suit = new String[hand.size()];
        for(int a = 1; a <= hand.size();a++){
            System.out.print("    Card " + a + "        ");
        }
        System.out.println();
        for(int i =0; i < hand.size();i++){
            System.out.print("┌────────────┐    ");
        }
        System.out.println();
        for(int j = 0; j < hand.size(); j++ ){
            suit[j] = getSuit(hand.get(j));
            System.out.print("│" + suit[j] + "           │    ");
        }
        System.out.println();
        for (int rows = 0; rows < 3; rows++){
            for (int k = 0; k < hand.size(); k++){
                if(rows== 1) {
                    System.out.print("│     " + hand.get(k).getValAsString().charAt(0) + "      │    ");
                }
                else {
                    System.out.print("│            │    ");
                }
            }
            System.out.println();
        }
        for(int l = 0; l < hand.size(); l++){
            System.out.print("│           " + suit[l] + "│    ");
        }
        System.out.println();
        for(int m = 0; m < hand.size(); m++){
            System.out.print("└────────────┘    ");
        }
        System.out.println();
    }
}
