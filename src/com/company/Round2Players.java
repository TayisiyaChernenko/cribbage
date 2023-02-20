package com.company;

public class Round2Players extends Round {
    Player player;

    public Round2Players(Player dealer, Player player, Deck deck) {
        super(deck, dealer);
        this.player = player;
    }

    public void play() {
        super.play();
        System.out.println(dealer.name + " is the dealer");
        super.deck.dealHand(dealer, player);

        makeCrib(player);
        makeCrib(dealer);
        super.v.displayHand(super.crib, "The Crib ");
        roundOfGame();
        player.getHand().clear();
        dealer.getHand().clear();
        super.pile.clear();
    }


    public void roundOfGame() {
        super.selectFlippedCard(player);
        System.out.println("The flipped card for this round will be ");
        v.displayCard(getFlipped());
        //count hand and crib points before the hand starts being reduced, won't be added until after the round ends here


        //The turns begin with the first non-dealer player
        Player currPlayer = player;
        Boolean playerSaidGo = false;
        Boolean dealerSaidGo = false;
        while (gameCounter < 31) {
            //take turn and see if player has to pass
            if (pile.size() != 0) {
                System.out.println("The last played card is ");
                v.displayCard(pile.get(0));
            }
            if (currPlayer.equals(player)) {
                playerSaidGo = super.turn(currPlayer);
            } else {
                dealerSaidGo = turn(currPlayer);
            }

            //swap whose turn it is
            //if the nondealer just went and the dealer hasn't passed yet, it's dealer's turn
            if (currPlayer.equals(player) && !dealerSaidGo) {
                currPlayer = dealer;
            }
            //if the dealer just went and the non dealer hasn't passed yet, it's non dealer's turn
            else if (currPlayer.equals(dealer) && !playerSaidGo) {
                currPlayer = player;
            }
            //If we're here, both have passed, end of round
            else if (playerSaidGo && dealerSaidGo) {
                //end of turn, add the hand points to the peg of both players here

                player.getHand().clear();
                dealer.getHand().clear();
                break;
            }
        }
    }

    private void makeCrib(Player player) {
        v.displayHand(player.getHand(), player.name + "'s Hand");
        System.out.println("Chose two cards to discard into the crib");
        System.out.println("First Card (Card # )>>> ");
        int indexFirst = super.validateCardSelection(player.getHand(), kb.nextInt() - 1);
        System.out.println("Second Card (Card # )>>> ");
        int indexSecond = validateCardSelection(player.getHand(), kb.nextInt() - 1);
        crib.add(player.removeCard(indexFirst));
        //adjusting the second index to reflect the fact that a card in front of it was removed
        if (indexFirst < indexSecond) {
            indexSecond--;
        }
        crib.add(player.removeCard(indexSecond));
        v.displayHand(player.getHand(), "Your starting hand is ");
    }

    public Player[] getPlayers(){
        return new Player[]{player};
    }


}
