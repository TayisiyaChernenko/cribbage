package com.company;

public class Round3Players extends Round{
    Player dealer;
    Player player1;
    Player player2;

    public Round3Players(Player dealer, Player player1, Player player2, Deck deck) {
        super(deck);
        this.player1 = player1;
        this.player2 = player2;
        this.dealer = dealer;
        System.out.println(dealer.name + " is the dealer");
        super.deck.dealHand(dealer,player1, player2);
        System.out.println("Cards Dealt");
        makeCrib(dealer);
        makeCrib(player1);
        makeCrib(player2);
        super.v.displayHand(super.crib, "The Crib ");
        roundOfGame();
        player1.hand.clear();
        player2.hand.clear();
        dealer.hand.clear();
        pile.clear();
    }
    public void roundOfGame() {
        selectFlippedCard(player1);
        System.out.println("The flipped card for this round will be ");
        v.displayCard(flipped);

        //int handPointsPlayer1 = hcPoints(player1.getHand(), flipped, false);
        //int handPointsPlayer2 = hcPoints(player2.getHand(), flipped, false);
        //int dealerHandCribPoints = hcPoints(dealer.getHand(), flipped, true);

        Player currPlayer = player1;
        Boolean player1SaidGo  = false;
        Boolean player2SaidGo = false;
        Boolean dealerSaidGo  = false;

        while (gameCounter < 31 ) {
            //take turn and update if player has to pass
            if (currPlayer.equals(player1)) {
                player1SaidGo = turn(currPlayer);
            } else if(currPlayer.equals(player2)) {
                player2SaidGo = turn(currPlayer);
            }
            else {
                dealerSaidGo = turn(currPlayer);
            }
            //swap whose turn it is
            //if the first non-dealer just went and the second non-dealer hasn't passed yet, it's their turn
            if (currPlayer.equals(player1) && !player2SaidGo){
                currPlayer = player2;
            }
            // if first non dealer went and second can't go, it's the dealer's turn if he can
            else if(currPlayer.equals(player1) && player2SaidGo && !dealerSaidGo){
                currPlayer = dealer;
            }
            //second non-dealer just went and dealer is up next and they can go
            else if(currPlayer.equals(player2) && !dealerSaidGo){
                currPlayer = dealer;
            }
            //second non-dealer just went,dealer can't go, first non-dealer is up next and they can go
            else if (currPlayer.equals(player2) && dealerSaidGo && !player1SaidGo){
                currPlayer = player1;
            }
            //dealer just went and now player 1 is up
            else if (currPlayer.equals(dealer) && !player1SaidGo){
                currPlayer = player1;
            }//dealer just went, player 1 can't go, and now player 2 is up
            else if (currPlayer.equals(dealer) && player1SaidGo && !player2SaidGo){
                currPlayer = player2;
            }
            else if (player1SaidGo && player2SaidGo && dealerSaidGo){
                //end of turn, add the hand points to the peg of all players
                //player1.pegAt += handPointsPlayer1;
                //player2.pegAt += handPointsPlayer2;
                //dealer.pegAt += dealerHandCribPoints;
                player1.hand.clear();
                player2.hand.clear();
                dealer.hand.clear();
                break;
            }
        }
    }
    private  void makeCrib(Player player){
        v.displayHand(player.getHand(), player.name + "'s Hand" ) ;
        System.out.println("Chose a card to discard into the crib");
        System.out.println("Card (Card # )>>> ");
        int index = validateCardSelection(player.getHand(), kb.nextInt()) - 1 ;
        crib.add(player.removeCard(index));

        //need additional card added to crib from deck, do it on dealer's discard step
        if(player.equals(dealer)){
            crib.add(deck.dealCard(0));
            System.out.println("The dealer put's a card from the deck into the crib. The card is >> ");
            v.displayCard(crib.get(1));
        }
        v.displayHand(player.getHand(), "Your starting hand is ");
    }
}
