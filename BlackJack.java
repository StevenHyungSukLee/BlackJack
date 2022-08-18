/*
THIS CODE WAS MY OWN WORK , IT WAS WRITTEN WITHOUT CONSULTING ANY
SOURCES OUTSIDE OF THOSE APPROVED BY THE INSTRUCTOR. HyungSuk_Lee
*/

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class BlackJack {

    private static Card[] cards;

    public static void buildDeck(ArrayList<Card> deck) {
	// Given an empty deck, construct a standard deck of playing cards
        
        deck.clear();//To build a new deck when restart a game
        
        String suit = null;
        String number = null;
        int value = 0;
        int arrayIndex=0;

        for (int i=0; i<4; i++){ // Using for loops, store total of 52 different cards that have three variables(suit, number, and value) in the ArrayList 'deck'.
                                    //number is for showing user a face number and value is for calculating to play blackjack game.
            
            for (int j=0; j < 13; j++){ //Using a for loop and switch function, make 52 combinations of suit and number and store it to the arraylist deck
                switch (i){
                    case 0: suit = "Clubs"; break;
                    case 1: suit = "Diamonds"; break;
                    case 2: suit = "Hearts";  break;
                    case 3: suit = "Spades"; break;
                }

                switch (j){
                    case 0: number = "A"; value = 1; break;
                    case 1: number = "2"; value = 2; break;
                    case 2: number = "3"; value = 3; break;
                    case 3: number = "4"; value = 4; break;
                    case 4: number = "5"; value = 5; break;                             
                    case 5: number = "6"; value = 6; break;
                    case 6: number = "7"; value = 7; break;
                    case 7: number = "8"; value = 8; break;
                    case 8: number = "9"; value = 9; break;
                    case 9: number = "10"; value = 10; break;                           
                    case 10: number = "J"; value = 10; break;
                    case 11: number = "Q"; value = 10; break;
                    case 12: number = "K"; value = 10; break;                           
                }

                Card card = new Card (number, suit, value);  
                deck.add(card); 
                arrayIndex++;
            }
        }


    }
        
    
    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
	// fill in code here
	// Deal two cards from the deck into each of the player's hand and dealer's hand

        playerHand.clear(); //to make sure that the player and dealer don't have any card before start a game
        dealerHand.clear();

        shuffle(deck, 100); //shuffle a deck before dealing

        dealerHand.add(deck.get(0)); //when start a game, dealer will have one card and player will have two cards.
        deck.remove(0);             //if a card dealed to player or dealer, remove the card from the deck
        playerHand.add(deck.get(1));
        deck.remove(1);
        dealerHand.add(deck.get(2));
        deck.remove(2);
        playerHand.add(deck.get(3));
        deck.remove(3);

    }

    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand){
	// this should deal a single card from the deck to the hand
        for(int i=0; i < deck.size(); i++){//a deck is already shuffled, so there is no problem if you distribute cards in order.
            
            if(deck.get(i)!=null){  //data that removed from the deck should be 'null'. So, we can deal a card which is not 'null'
                                    //Using this way helps us to avoid overlapping the cards in one game
                hand.add(deck.get(i));
                deck.remove(i);
                break;
            }
        }
    }

    public static boolean checkBust(ArrayList<Card> hand){
	// This should return whether a given hand's value exceeds 21
        int score = 0;
        for(int i = 0; i < hand.size(); i++){
            score = score + hand.get(i).value;
        }
        if(score > 21){ //if total score is greater than 21, then player busted
            return true;
        }else{
	        return false;
        }
    }

    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
	
        int score = hand.get(0).value + hand.get(1).value; // the dealer already has two cards. So, the total score of dealer start from there.
        int numCard = 2;
        while(score < 17){ // if the total score of dealer is less than 17, dealer gets one more card
                            // if the total score of dealer is 17 or more, dealer stand
            dealOne(deck,hand);
            score = score + hand.get(numCard).value;
            numCard++;
        }
        if(score > 21){// Return true if the dealer busts; false otherwise
            return true;
        }else{
	        return false;
        }
        }

    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
	// fill in code here
	
        int result = 0;
        int playerscore = 0;
        int dealerscore = 0;

        for(int i = 0; i < playerHand.size(); i++){
            playerscore = playerscore + playerHand.get(i).value;
        }

        for(int i = 0; i < dealerHand.size(); i++){
            dealerscore = dealerscore + dealerHand.get(i).value;
        }

        if(dealerscore >= playerscore){// This should return 1 if the player wins and 2 if the dealer wins
            result = 2;
        } else {
            result = 1;
        }

	    return result;
    }

    public static String displayCard(ArrayList<Card> hand){
	// Return a string describing the card which has index 1 in the hand

        String displayCard = hand.get(1).suit + "_" + hand.get(1).number;

	    return displayCard;
    }

    public static String displayHand(ArrayList<Card> hand){
	// fill in code here
	// Return a string listing the cards in the hand

        String displayHand = hand.get(0).suit + "_" + hand.get(0).number;

        for(int i = 1; i < hand.size(); i++){
            displayHand = displayHand + " " + hand.get(i).suit + "_" + hand.get(i).number;
        }
	    return displayHand;
    }


    // feel free to add methods as necessary

    public static void shuffle(ArrayList<Card> deck, int count){ 

        Card tempspace;
        Card tempspace2;
        int randomNum1;
        int randomNum2;

        for(int i=0; i < count; i++){ //picking two random index and save those value to tempspace and tempspace2, and then swap the values.
            randomNum1 = (int)(Math.random()*deck.size());
            tempspace = deck.get(randomNum1);
            randomNum2 = (int)(Math.random()*deck.size());
            tempspace2 = deck.get(randomNum2);
            deck.set(randomNum1,tempspace2);
            deck.set(randomNum2,tempspace);
        }
    }
    

    public static void main(String[] args) {

		int playerChoice, winner;
		ArrayList<Card> deck = new ArrayList<Card>();
		
		
		playerChoice = JOptionPane.showConfirmDialog(
			null, 
			"Ready to Play Blackjack?", 
			"Blackjack", 
			JOptionPane.OK_CANCEL_OPTION
		);

		if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
		    System.exit(0);
		
		Object[] options = {"Hit","Stand"};
		boolean isBusted;	// Player busts? 
		boolean dealerBusted;
		boolean isPlayerTurn;
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> dealerHand = new ArrayList<>();
	
		do{ // Game loop
			buildDeck(deck);  // Initializes the deck for a new game
		    initialDeal(deck, playerHand, dealerHand);
		    isPlayerTurn=true;
		    isBusted=false;
		    dealerBusted=false;
		    
		    while(isPlayerTurn){

				// Shows the hand and prompts player to hit or stand
				playerChoice = JOptionPane.showOptionDialog(
					null,
					"Dealer shows " + displayCard(dealerHand) + "\n Your hand is: " 
						+ displayHand(playerHand) + "\n What do you want to do?",
					"Hit or Stand",
				   JOptionPane.YES_NO_OPTION,
				   JOptionPane.QUESTION_MESSAGE,
				   null,
				   options,
				   options[0]
				);

				if(playerChoice == JOptionPane.CLOSED_OPTION)
				    System.exit(0);
				
				else if(playerChoice == JOptionPane.YES_OPTION){
				    dealOne(deck, playerHand);
				    isBusted = checkBust(playerHand);
				    if(isBusted){
						// Case: Player Busts
						playerChoice = JOptionPane.showConfirmDialog(
							null,
							"Player has busted!", 
							"You lose", 
							JOptionPane.OK_CANCEL_OPTION
						);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
						
						isPlayerTurn=false;
				    }
				}
			    
				else{
				    isPlayerTurn=false;
				}
		    }

		    if(!isBusted){ // Continues if player hasn't busted
				dealerBusted = dealerTurn(deck, dealerHand);
				if(dealerBusted){ // Case: Dealer Busts
				    playerChoice = JOptionPane.showConfirmDialog(
				    	null, 
				    	"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " 
				    		+ displayHand(playerHand) + "\nThe dealer busted.\n Congrautions!", 
				    	"You Win!!!", 
				    	JOptionPane.OK_CANCEL_OPTION
				    );		    

					if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						System.exit(0);
				}
			
			
				else{ //The Dealer did not bust.  The winner must be determined
				    winner = whoWins(playerHand, dealerHand);

				    if(winner == 1){ //Player Wins
						playerChoice = JOptionPane.showConfirmDialog(
							null, 
							"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " 
								+ displayHand(playerHand) + "\n Congrautions!", 
							"You Win!!!", 
							JOptionPane.OK_CANCEL_OPTION
						);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }

				    else{ //Player Loses
						playerChoice = JOptionPane.showConfirmDialog(
							null, 
							"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " 
								+ displayHand(playerHand) + "\n Better luck next time!", 
							"You lose!!!", 
							JOptionPane.OK_CANCEL_OPTION
						); 
					
						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }
				}
		    }
		}while(true);
    }
}



class Card {
     
	// Specify data fields for an individual card
    
    public final String number;
    public final String suit;
    public final int value;

    Card(String number, String suit, int value){//each card is constructed with three variables
        this.number = number;
        this.suit = suit;
        this.value = value;
    }
}