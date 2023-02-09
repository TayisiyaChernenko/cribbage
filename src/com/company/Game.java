package com.company;

import java.util.Scanner;

public class Game {
    Player p1;
    Player p2;
    Player p3;
    Player dealer;
    int locationDealer;
    Player[] players;
    Deck d = new Deck();
    Scanner keyboard = new Scanner(System.in);


    //default constructor used if no names are given, takes number of players
    public Game(int numPlayers) {
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        if(numPlayers == 3){
            p3 = new Player("Player 3");
            players = new Player[]{p1, p2, p3};
            play(3);
        }else{
            players = new Player[]{p1, p2};
            play(2);
        }
    }
    //constructor that takes the names of 2 players for a 2 player game
    public Game(String name1, String name2){
        p1 = new Player(name1);
        p2 = new Player(name2);
        players = new Player[]{p1, p2};
        play(2);
    }
    //constructor that takes the names of 3 players for a 3 player game
    public Game(String name1, String name2,String name3){
        p1 = new Player(name1);
        p2 = new Player(name2);
        p3 = new Player(name3);
        players = new Player[]{p1, p2, p3};
        play(3);
    }

    public void play(int numPlayers){
        //First dealer of the game is based on how the deck is cut. After, dealer role alternates
        locationDealer = pickDealer(d,players );

        //no winner yet, keep having more rounds of gameplay
        boolean noWinner = true;
        while (noWinner) {
            updateDealer();
            Round r;
            // each round will have nuance on how player parameters are ordered based on dealer
            if(numPlayers ==2){
               r = createRound2();
            }
            else{
                r = createRound3();
            }
            Points pointTracker = new Points(r);
            r.play();
            //show points after the round had ended
            pointTracker.showPoints();
            noWinner = checkNoWinner(players);
        }
        //someone won!
        if (p1.getPoints() >= 121){
            System.out.println(p1.name + " won!");
        }
        else if (p2.getPoints() >= 121){
            System.out.println(p2.name + " won!");
        }
        else{
            System.out.println(p3.name + " won!");
        }
    }


    // picks dealer, returns location of the dealer in the players array for later use in Play()
    public int pickDealer(Deck d, Player[] players) {
        int i = 0;
        int[] valuesDrawn = new int[players.length];
        while (i < players.length) {
            System.out.println(players[i].name + " cut the deck (Choose a card # 0-51) >>> ");
            valuesDrawn[i] = d.showCardAt(keyboard.nextInt());
            i++;
        }
        int smallest = 14;
        int index = -1;
        //find the index of the smallest selection, will match player indices
        for (int j = 0; j < valuesDrawn.length; j++) {
            //picks smallest value, if there is a tie goes by who picked first
            if(valuesDrawn[j] < smallest){
                smallest = valuesDrawn[j];
                index = j;
            }
        }
        //dealer is the one with the smallest drawn card
        dealer = players[index];
        //Those cards are not removed, the deck is just reshuffled
        d.shuffle();

        //Since the while loop in play() moves Dealer to the next player, we'll start 1 behind our dealer to get them as dealer for R1
        if (index == 0){
            index = players.length -1 ;
        }
        else{
            index--;
        }
        return index;
    }

    public void updateDealer(){
        // round takes the player inputs in the order of dealer first and then the non-dealer player(s) second
        if (locationDealer ==  players.length - 1){
            locationDealer = 0; // circling around back to first person
            dealer = players[0];
        }
        else{
            locationDealer++;
            dealer = players[locationDealer];
        }
    }

    public Round createRound2(){
        Round r;
        if (dealer.equals(p1)){
            r = new Round2Players(dealer, p2,d);
        }
        else{
            r = new Round2Players(dealer,p1,d);
        }
        return r;
    }

    public Round createRound3(){
        Round r;
        if (dealer.equals(p1)){
            r = new Round3Players(dealer, p2,p3,d);
        }
        else if(dealer.equals(p2)){
            r = new Round3Players(dealer,p1,p3,d);
        }
        else{
            r = new Round3Players(dealer,p1,p2,d);
        }
        return r;
    }



    public boolean checkNoWinner(Player[] players){
        for (Player player : players) {
            if (player.getPoints() >= 121) {
                //there is a winner, noWinner is now false
                return false;
            }
        }
        //no winners yet
        return true;
    }
}
