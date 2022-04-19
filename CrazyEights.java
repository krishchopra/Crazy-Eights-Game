import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/**
 * Last Updated: July 21, 2021
 * Name: Krish Chopra
 * Description: Java implementation of Crazy Eights card game
 */

public class CrazyEights {
	
	// initializes global variables that will be referenced throughout the program	
	
	static ArrayList<String> playerHand = new ArrayList<String>(); // initializes ArrayList object (of dynamic size) to store the cards that are in the player's hand 
	static ArrayList<String> computerHand = new ArrayList<String>(); // initializes ArrayList object (of dynamic size) to store the cards that are in the computer's hand 
	static ArrayList<String> deck = new ArrayList<String>(); // initializes ArrayList object (of dynamic size) to store the cards that are in the deck
	static String topCard; // initializes string variable to hold the top card of the deck
	static String nextSuit; // initializes string variable to hold the suit that the player has chosen the next card to be (if they just played an 8)
	static Scanner scan = new Scanner(System.in); // initializes a scanner to receive user input from console
 	
	// defines method that will take an array of card strings (deck) as input, and output the deck shuffled
	public static String[] shuffleDeck(String[] deck) {
		
		Random randNumGen = new Random(); // initializes Random object to generate random numbers
		
		// iterates through each index of deck
		for (int i = 0; i < deck.length; i++) {
			int randIndex = randNumGen.nextInt(deck.length); // sets randIndex to equal a random number index
			
			// swaps the card at randIndex with the card at the current index
			String temp = deck[randIndex];
			deck[randIndex] = deck[i];
			deck[i] = temp;
		}
		
		return deck; // returns the shuffled deck
	}
	
	// defines method that will deal cards to player and computer (takes card strings from deck and adds them to playerHand and computerHand)
	public static void dealCards() {
		
		// iterates through loop (adds a card to playerHand) 7 times
		for (int i = 0; i < 7; i++) {
			int lastIndex = deck.size() - 1;
			playerHand.add(deck.get(lastIndex)); // adds the last card of the deck to playerHand
			deck.remove(lastIndex); // removes the last card of the deck, from the deck
		}
		
		// repeats the same process to add 7 cards to computerHand 
		for (int i = 0; i < 7; i++) {
			int lastIndex = deck.size() - 1;
			computerHand.add(deck.get(lastIndex)); 
			deck.remove(lastIndex);
		}
		
		topCard = deck.get(deck.size() - 1); // sets topCard to be the last card of the deck
		deck.remove(deck.get(deck.size() - 1)); // removes that last card from the deck
		
	}
	
	// defines method that will take an array of card strings as input, and output the cards sorted in ascending order 
	public static String[] sortCards(String[] cardsInHand) {
		
		// initializes card counter variables
		int numberCardCount = 0;
		int pictureCardCount = 0;
		int aceCardCount = 0;
		
		// iterates through each card index in cardsInHand
		for (int i = 0; i < cardsInHand.length; i++) {
			
			// increments aceCardCount by 1 if the card is an Ace
			if (cardsInHand[i].charAt(0) == 'A') {
				aceCardCount++;
			// increments pictureCardCount by 1 if the card is a Jack, Queen, or King
			} else if ((cardsInHand[i].charAt(0) == 'J') || (cardsInHand[i].charAt(0) == 'Q') || (cardsInHand[i].charAt(0) == 'K')) {
				pictureCardCount++;
			// increments numberCardCount by 1 if the card isn't an Ace or picture card
			} else {
				numberCardCount++;
			}
			
		}
		
		// initializes different arrays of appropriate sizes, to store sorted cards of each type
		String[] numberCards = new String[numberCardCount];
		String[] pictureCards = new String[pictureCardCount];
		String[] aceCards = new String[aceCardCount];
		
		// initializes index counter variables
		int numberCardsIndex = 0;
		int pictureCardsIndex = 0;
		int aceCardsIndex = 0;
		
		// iterates through each card index in cardsInHand
		for (int i = 0; i < cardsInHand.length; i++) {
			
			if (cardsInHand[i].charAt(0) == 'A') {
				aceCards[aceCardsIndex] = cardsInHand[i]; // sets the current card to be the card in the aceCards array at aceCardsIndex
				aceCardsIndex++; // increments aceCardsIndex by 1
			} else if ((cardsInHand[i].charAt(0) == 'J') || (cardsInHand[i].charAt(0) == 'Q') || (cardsInHand[i].charAt(0) == 'K')) {
				pictureCards[pictureCardsIndex] = cardsInHand[i]; // sets the current card to be the card in the pictureCards array at pictureCardsIndex
				pictureCardsIndex++; // increments pictureCardsIndex by 1
			} else {
				numberCards[numberCardsIndex] = cardsInHand[i]; // sets the current card to be the card in the numberCards array at numberCardsIndex
				numberCardsIndex++; // increments numberCardsIndex by 1
			}
			
		}
		
		// sorts numberCards in ascending order
		for (int i = 0; i < numberCards.length - 1; i++) { // represents the number of passes
			
			// the comparisons
			for (int j = 0; j < (numberCards.length - 1) - i; j++) {
				
				String currCard = numberCards[j]; // sets variable currCard to equal the card in numberCards at the current index
				int currNum = Integer.parseInt(currCard.substring(0, currCard.length() - 1)); // sets variable currNum to equal the number value of currCard
				String nextCard = numberCards[j + 1]; // sets variable nextCard to equal the card in numberCards at the index of the card after the current one
				int nextNum = Integer.parseInt(nextCard.substring(0, nextCard.length() - 1)); // sets variable nextNum to equal the number value of nextCard
				
				if (currNum > nextNum) { // compares values of adjacent cards
					
					// swaps cards (strings)
					numberCards[j] = nextCard;
					numberCards[j + 1] = currCard;
					
				}
			}
		}
		
		String sortedPictureCards = "JC JD JH JS QC QD QH QS KC KD KH KS"; // sets the string sortedPictureCards to equal all picture cards sorted, separated by spaces
		
		// sorts pictureCards in ascending order
		for (int i = 0; i < pictureCards.length - 1; i++) { // represents the number of passes
			
			// the comparisons
			for (int j = 0; j < (pictureCards.length - 1) - i; j++) {
				String currCard = pictureCards[j]; // sets variable currCard to equal the card in pictureCards at the current index
				String nextCard = pictureCards[j + 1]; // sets variable nextCard to equal the card in pictureCards at the index of the card after the current one
				if (sortedPictureCards.indexOf(currCard) > sortedPictureCards.indexOf(nextCard)) { // compares adjacent card strings
					
					// swaps cards (strings)
					pictureCards[j] = nextCard;
					pictureCards[j + 1] = currCard;
					
				}
			}
		}
		
		int currIndex = 0; // initializes variable currIndex to keep track of the current index of cardsInHand
		
		// iterates through each index of aceCards (since Aces are the lowest value cards)
		for (int i = 0; i < aceCards.length; i++) {
			cardsInHand[currIndex] = aceCards[i]; // replaces the card in cardsInHand at index currIndex with the card at the current index (i) of aceCards
			currIndex++; // increments currIndex by 1
		}
		
		// next, iterates through each index of numberCards (since the values of number cards are in between the value of Aces and picture cards)
		for (int i = 0; i < numberCards.length; i++) {
			cardsInHand[currIndex] = numberCards[i]; // replaces the card in cardsInHand at index currIndex with the card at the current index (i) of numberCards
			currIndex++; // increments currIndex by 1
		}
		
		// finally, iterates through each index of pictureCards (since picture cards possess the highest values)
		for (int i = 0; i < pictureCards.length; i++) {
			cardsInHand[currIndex] = pictureCards[i]; // replaces the card in cardsInHand at index currIndex with the card at the current index (i) of pictureCards
			currIndex++; // increments currIndex by 1
		}
		
		return cardsInHand; // return the original array, cardsInHand, sorted in ascending order
	}
	
	// defines method that will prompt user to choose a suit as well as store the entered suit, if the user just played an 8
	public static void newSuit() {
		
		boolean suitVerified = false; // initializes a boolean variable to validate the user's response
		
		do {

			// prompts the user to enter a suit that the opponent must play next
			System.out.println("Enter the suit that the next card must be (S, H, D, or C): ");
			String suit = scan.nextLine(); // stores response in variable suit
			suit = suit.toUpperCase(); // converts response to upper case to account for both lower case and upper case
			
			// sets string variable nextSuit to equal the suit that the user specified - only the first character of suit is checked, to account for typos
			if (suit.charAt(0) == 'S') {
				nextSuit = "Spades";
				suitVerified = true;
			} else if (suit.charAt(0) == 'H') {
				nextSuit = "Hearts";
				suitVerified = true;
			} else if (suit.charAt(0) == 'D') {
				nextSuit = "Diamonds";
				suitVerified = true;
			} else if (suit.charAt(0) == 'C') {
				nextSuit = "Clubs";
				suitVerified = true;
			
			// prompts the user to re-enter a suit if they didn't enter a valid suit
			} else {
				System.out.println("That wasn't a valid suit. Please try again.\n");
			}
		
		} while (suitVerified == false); // continues to prompt user to enter a suit, if the suit that was just entered was invalid
		System.out.println("You set the suit to " + nextSuit); // prints the suit that the user entered, as validation
		
	}
	
	// defines method that will execute the user's turn
	public static void playerTurn() {
		
		String card; // initializes variable to keep track of the card that was just played
		
		do {
		
			boolean isValidPlay = false; // initially sets isValidPlay to false, since the user has not chosen a card yet
			
			// sorts the cards in the user's hand
			String playerHand1[] = playerHand.toArray(new String[playerHand.size()]);
			sortCards(playerHand1);
			
			for (int i = 0; i < playerHand1.length; i++) {
				playerHand.set(i, playerHand1[i]);
			}
			
			// prints the sorted cards in the user's hand
			System.out.println("\nCards in your hand (sorted in ascending order):");
			for (int i = 0; i < playerHand.size() - 1; i++) {
				System.out.print(playerHand.get(i) + ", ");
			}
			System.out.println(playerHand.get(playerHand.size() - 1));
			
			System.out.println("\nTop card of discard pile: " + topCard + "\n"); // prints the top card of the discard pile
			System.out.println("What would you like to do?"); 
			
			do {
				
				// prompts the user to perform an action (play a card, or draw a card)
				System.out.println("Enter a card to play from your hand, or enter 'draw' to take a card from the deck: ");
				card = scan.nextLine().toUpperCase(); // assigns the user's response (in upper case) to card, to account for both upper case and lower case input
				
				// executes the following if the user's card is an 8, and they possess the card
				if ((card.charAt(0) == '8') && playerHand.contains(card)) {
					playerHand.remove(card); // removes the card from the user's hand
					System.out.println("You played: " + card + "\n"); // prints the card that they just played
					topCard = card; // updates top card of discard pile to now be set to the card that was just played by the user 
					newSuit(); // calls the newSuit method
					isValidPlay = true; // validates the user's play
				
				// executes the following if the user entered 'draw'
				} else if (card.equals("DRAW")) {
					
					// executes the following if the deck isn't empty
					if (deck.size() > 0) {
						
						// removes top card of deck from deck, adds it to playerHand, and validates the user's play
						int lastIndex = deck.size() - 1;
						String drawnCard = deck.get(lastIndex);
						deck.remove(lastIndex);
						playerHand.add(drawnCard);
						isValidPlay = true;
						
						System.out.println("You drew: " + drawnCard); // prints the card that the user just drew, as validation
					
					// executes the following if the deck is empty
					} else {
						
						System.out.println("Sorry, the draw pile is empty. Your turn must be passed."); // prints that the deck is empty and user's turn must be passed
						isValidPlay = true; // validates the user's play
						
					}
				
				// executes the following if the last card played by the computer was an 8, and the user's entered card exists within their hand
				} else if ((topCard.charAt(0) == '8') && (playerHand.contains(card))) {
					
					// executes the following if the user enters a card of the suit previously specified by the computer 
					if (card.charAt(card.length() - 1) == nextSuit.charAt(0)) {
						isValidPlay = true; // validates the user's play
					// executes the following if the card entered by the user is an 8
					} else if (card.charAt(0) == '8') {
						newSuit(); // calls the newSuit method
						isValidPlay = true; // validates the user's play
					// executes the following if the user enters an invalid response
					} else {
						System.out.println("Sorry, that was invalid. Please try again.");
					}
				
				// executes the following if the suit of card is equal to topCard's suit, or if the rank of card is equal to topCard's rank, and if the user actually possesses card
				} else if ((card.charAt(card.length() - 1) == topCard.charAt(topCard.length() - 1) || card.charAt(0) == topCard.charAt(0)) && (playerHand.contains(card))) {
					isValidPlay = true; // validates the user's play
					
				} else {
					System.out.println("Sorry, that was invalid. Please try again.");
				}
					
			} while (isValidPlay == false); // executes all the code enclosed in the do-while loop until the user enters a valid response
			
			// executes the following if the user didn't enter an 8 or 'draw', since those special cases were already accounted for
			if ((!card.equals("DRAW")) && (!(card.charAt(0) == '8'))) {
				playerHand.remove(card); // removes card from playerHand
				System.out.println("You played: " + card); // prints the card that the user just played, as validation
				topCard = card; // sets topCard to now be the card the user just played
			}
			
			// special card cases
			
			// executes the following if the card is a 2			
			if (card.charAt(0) == '2') {
				
				// adds two cards from the top of the deck, to computerHand
				for (int i = 0; i < 2; i++) {
					String drawnCard = deck.get(deck.size() - 1);
					deck.remove(deck.size() - 1);
					computerHand.add(drawnCard);
				}
				System.out.println("You made the computer draw 2 cards!"); // prints that the user made the computer draw 2 cards, as validation
				
			}
			
			// executes the following if the card is the Queen of Spades
			if (card.equals("QS")) {
				
				// similar to what would happen if the card is a 2, but instead makes the computer draw 5 cards
				for (int i = 0; i < 5; i++) {
					String drawnCard = deck.get(deck.size() - 1);
					deck.remove(deck.size() - 1);
					computerHand.add(drawnCard);
				}
				System.out.println("You made the computer draw 5 cards!");
				
			}
			
			// executes the following if the card is a Jack
			if (card.charAt(0) == 'J') {
				System.out.println("You received an additional turn!"); // prints that the user received an additional turn
			}
		
		} while (card.charAt(0) == 'J'); // executes all of the code in this method, while the card just played is a Jack
		
	}
	
	// defines method that will execute the computer's turn
	public static void computerTurn() {
		
		String choice = " "; // initializes variable to keep track of the card that was just chosen to be played by the computer
		
		do {
			
			ArrayList<String> options = new ArrayList<String>(); // initializes the ArrayList object options, to store all of the computer's playable cards
			
			// iterates through loop for every index of computerHand
			for (int i = 0; i < computerHand.size(); i++) {
				
				String currCard = computerHand.get(i); 
				
				// executes the following if the user played an 8 before this
				if (topCard.charAt(0) == '8') {
					// executes the following if currCard is a card of the suit previously specified by the user
					if ((currCard.charAt(currCard.length() - 1) == nextSuit.charAt(0)) || (currCard.charAt(0) == '8')) {
						options.add(currCard); // adds currCard to options
					}
				
				// executes the following if the suit of currCard is equal to topCard's suit, or if the rank of currCard is equal to topCard's rank
				} else if ((currCard.charAt(currCard.length() - 1) == topCard.charAt(topCard.length() - 1)) || (currCard.charAt(0) == topCard.charAt(0)) || (currCard.charAt(0) == '8')) {
					options.add(currCard); // adds currCard to options
				}
				
			}
			
			// executes the following if options is not empty
			if (options.size() > 0) {
				
				// selects a random card from options
				Random randNumGen = new Random();
				int randIndex = randNumGen.nextInt(options.size());
				choice = options.get(randIndex);
				
				computerHand.remove(choice); // removes the choice from computerHand
				System.out.println("\nComputer played: " + choice); // prints the card that the computer played (choice)
				
				topCard = choice; // sets topCard to be equal to the card that the computer just played (choice)
				
				// special card cases
				
				// executes the following if the card is a 2			
				if (choice.charAt(0) == '2') {
					
					System.out.print("The computer made you draw 2 cards. You drew: "); // prints that the computer made the user draw 2 cards
					
					// adds two cards from the top of the deck, to playerHand, and prints them
					String drawnCard = deck.get(deck.size() - 1);
					deck.remove(deck.size() - 1);
					playerHand.add(drawnCard);
					System.out.print(drawnCard + " and ");
					drawnCard = deck.get(deck.size() - 1);
					deck.remove(deck.size() - 1);
					playerHand.add(drawnCard);
					System.out.println(drawnCard);
					
				}
				
				// executes the following if the card is the Queen of Spades
				if (choice.equals("QS")) {
					
					System.out.print("The computer made you draw 5 cards. You drew: "); 
					
					// adds five cards from the top of the deck, to playerHand, and prints them
					for (int i = 0; i < 4; i++) {
						String drawnCard = deck.get(deck.size() - 1);
						deck.remove(deck.size() - 1);
						playerHand.add(drawnCard);
						System.out.print(drawnCard + ", ");
					}
					String drawnCard = deck.get(deck.size() - 1);
					deck.remove(deck.size() - 1);
					playerHand.add(drawnCard);
					System.out.println("and " + drawnCard);
					
				}
				
				// executes the following is the card is a Jack
				if (choice.charAt(0) == 'J') {
					System.out.println("The computer received an additional turn!"); 
				}
				
				// executes the following is the card is an 8
				if (choice.charAt(0) == '8') {
					
					// initializes suit counter variables
					int spadesCount = 0;
					int heartsCount = 0;
					int diamondsCount = 0;
					int clubsCount = 0;
					
					// iterates through loop for every index of computerHand
					for (int i = 0; i < computerHand.size(); i++) {
						String currCard = computerHand.get(i);
						int lastIndexOfCard = currCard.length() - 1;
						
						// increments suit counter variables, depending on which suit currCard is
						if (currCard.charAt(lastIndexOfCard) == 'S') {
							spadesCount++;
						} else if (currCard.charAt(lastIndexOfCard) == 'H') {
							heartsCount++;
						} else if (currCard.charAt(lastIndexOfCard) == 'D') {
							diamondsCount++;
						} else {
							clubsCount++;
						}
					}
					
					// sets nextSuit to be whichever suit exists the most in computerHand
					if ((spadesCount >= heartsCount) && (spadesCount >= diamondsCount) && (spadesCount >= clubsCount)) {
						nextSuit = "Spades";
					} else if ((heartsCount >= spadesCount) && (heartsCount >= diamondsCount) && (heartsCount >= clubsCount)) {
						nextSuit = "Hearts";
					} else if ((diamondsCount >= spadesCount) && (diamondsCount >= heartsCount) && (diamondsCount >= clubsCount)) {
						nextSuit = "Diamonds";
					} else {
						nextSuit = "Clubs";
					}
					
					System.out.println("Computer set the suit to " + nextSuit); // prints the suit that the computer just set the next card to be
					
				}
			
			// executes the following if options is empty
			} else {
				
				choice = " "; // sets choice to be a blank space character, since there are no playable cards in computerHand
				
				// executes the following if the deck is not empty
				if (deck.size() > 0) {
					
					// removes top card of deck from deck, and adds it to computerHand
					int lastIndex = deck.size() - 1;
					String drawnCard = deck.get(lastIndex);
					deck.remove(lastIndex);
					computerHand.add(drawnCard);
					
					System.out.println("\nComputer drew a card."); 
				
				// executes the following if the deck is empty
				} else {
					System.out.println("\nThe computer tried to draw a card, but the draw pile is empty. It had to pass its turn.");
				}
				
			}
			
		} while (choice.charAt(0) == 'J'); // executes all of the code in this method, while the card just played by the computer is a Jack
			
	}
	
	// main method that will make use of the methods defined above
	public static void main(String[] args) throws Exception {
	
		// initializes variables gameOver and programOver to control the flow of the game/program overall
		boolean gameOver = false;
		boolean programOver = false;
		
		// initial setup
		File cardsFile = new File("cards.txt"); // initializes File object to be set to the file cards.txt
	 	String[] initialDeck = new String[52]; // initializes the deck of cards to be a string array of size 52
	 	File playerPoints = new File("CrazyEightsPlayerPoints.txt"); // initializes File object to be set to the file CrazyEightsPlayerPoints.txt 
		File computerPoints = new File("CrazyEightsComputerPoints.txt"); // initializes File object to be set to the file CrazyEightsComputerPoints.txt 
 		PrintWriter playerScoreOutput = new PrintWriter(playerPoints); // initializes PrintWriter object to write to the playerPoints file
		PrintWriter computerScoreOutput = new PrintWriter(computerPoints); // initializes PrintWriter object to write to the computerPoints file
	 	Scanner cardsFileScan = new Scanner(cardsFile); // initializes Scanner fileScan to read cardsFile
		
	 	// welcomes user to the game, introduces them to the format of the cards 
	 	System.out.println("Welcome to Crazy Eights!");
	 	System.out.println("**Each card will be written in (and should be entered in) abbreviated form. For instance, 3 of Diamonds will be expressed as 3D, 10 of Spades as 10S, Queen of Clubs as QC, etc.**");
	 	
	 	while (programOver == false) {
	 		
		 	// sets the value at each index of the array to equal a card from the file
		 	for (int i = 0; i < initialDeck.length; i++) {
		 		initialDeck[i] = cardsFileScan.next();
		 	}
		 	
		 	// continues to call the shuffleDeck method until the top card after dealing is NOT an 8 (the top card after dealing is initially 14th from the top)
		 	do {
		 		shuffleDeck(initialDeck);
		 	} while (initialDeck[initialDeck.length - 15].charAt(0) == '8');
		 	
		 	// adds cards from the initialDeck array to the deck ArrayList object
		 	for (int i = 0; i < initialDeck.length; i++) {
		 		deck.add(initialDeck[i]);
		 	}
	 		
		 	// calls dealCards method, dealing initial cards to playerHand and computerHand
	 		System.out.println("\nDealer is dealing cards...");
	 		dealCards();
	 		
	 		while (gameOver == false) {
	 			
	 			playerTurn(); // calls the method playerTurn to execute the user's turn
	 			
	 			// executes the following if the user has no more cards in their hand
	 			if (playerHand.size() == 0) {
	 				
	 				System.out.println("\nYou won! Congratulations!");
	 				
	 				int pointTotal = 0; // initializes pointTotal to keep track of the points that the user will accumulate from computerHand 
	 				// iterates through loop for each index of computerHand
	 				for (int i = 0; i < computerHand.size(); i++) {
	 					String currCard = computerHand.get(i);
	 					
	 					if (currCard.charAt(0) == '8') {
	 						pointTotal += 50; // adds 50 points to pointTotal if the card is an 8
	 					} else if (currCard.charAt(0) == 'A') {
	 						pointTotal += 1; // adds 1 point to pointTotal if the card is an A
	 					} else if ((currCard.charAt(0) == 'J') || (currCard.charAt(0) == 'Q') || (currCard.charAt(0) == 'K')) {
	 						pointTotal += 10; // adds 10 points to pointTotal if the card is a picture card
	 					} else {
	 						pointTotal += (int) currCard.charAt(0); // adds the face value number of points to pointTotal if the card is a number card 
	 					}
	 					
	 				}
	 				
	 				// prints the number of points that the user accumulated from computerHand, to both the console and to the file playerPoints
	 				System.out.println("You accumulated " + pointTotal + " point(s) from this game.");
	 				playerScoreOutput.println(pointTotal);
	 				
	 				// breaks the current while loop, ending the game
	 				gameOver = true;
	 				break;
	 				
	 			} 
	 			
	 			computerTurn(); // calls the method computerTurn to execute the computer's turn
	 			
	 			// same logic as before, except now the points that the COMPUTER accumulates from playerHand are counted (when the computer wins)
	 			
	 			if (computerHand.size() == 0) {
	 				
	 				System.out.println("\nThe computer won! Better luck next time...");
	 				
	 				int pointTotal = 0;
	 				for (int i = 0; i < playerHand.size(); i++) {
	 					String currCard = playerHand.get(i);
	 					
	 					if (currCard.charAt(0) == '8') {
	 						pointTotal += 50;
	 					} else if (currCard.charAt(0) == 'A') {
	 						pointTotal += 1;
	 					} else if ((currCard.charAt(0) == 'J') || (currCard.charAt(0) == 'Q') || (currCard.charAt(0) == 'K')) {
	 						pointTotal += 10;
	 					} else {
	 						pointTotal += Integer.parseInt(currCard.substring(0, currCard.length() - 1));
	 					}
	 					
	 				}
	 				
	 				System.out.println("The computer accumulated " + pointTotal + " point(s) from this game.");
	 				computerScoreOutput.println(pointTotal);
	 				gameOver = true;
	 				break;
	 			
	 			}
	 			
	 		}
	 		
	 		char newResponse = ' '; // initializes char variable newResponse to store the user's response when prompted to play again
 			boolean validResponse = false; // initializes boolean variable validResponse to validate the user's response
 			do {
	 			System.out.println("Would you like to play again? (y/n)"); 
	 			String response = scan.nextLine().toLowerCase(); // stores the user's response as a lower-case string, to account for both lower and upper case
	 			newResponse = response.charAt(0); // stores only the first character of response in newResponse, to account for typos in the initial response
	 			
	 			if (newResponse == 'y' || newResponse == 'n') {
	 				validResponse = true; // validates the user's response if they enter 'yes' or 'no'
	 			} else {
	 				System.out.println("That was not a valid response. Please re-enter."); // prompts the user to re-enter a response
	 			}
	 			
 			} while (validResponse == false); // executes the above code in the loop, while the user's response is not validated
 			
 			// restarts game - resets the fileScan scanner (which scans the file cards.txt), as well as ArrayList objects, if user entered yes
 			if (newResponse == 'y') {		
 				gameOver = false;
 				cardsFileScan = new Scanner(cardsFile);
 				playerHand = new ArrayList<String>();
 				computerHand = new ArrayList<String>();
 				deck = new ArrayList<String>();
 			
 			// breaks out of outer loop and ends the execution of any further games, if user entered no
 			} else {
 				programOver = true;
 			}
	 		
	 	}
	 	
	 	// closes PrintWriter objects, saving the data on the files playerPoints and computerPoints
		playerScoreOutput.close();
		computerScoreOutput.close();
	 	
		// initializes point counter variables to count the user and computer's total points
	 	int totalPlayerPoints = 0;
		int totalComputerPoints = 0;
		
		// initializes Scanner objects to read the data from playerPoints and computerPoints
		Scanner playerPointsScan = new Scanner(playerPoints);
		Scanner computerPointsScan = new Scanner(computerPoints);
		
		// adds all the points from file playerPoints to totalPlayerPoints
		while (playerPointsScan.hasNext()) {
			totalPlayerPoints += playerPointsScan.nextInt();
		}
		
		// adds all the points from file computerPoints to totalComputerPoints
		while (computerPointsScan.hasNext()) {
			totalComputerPoints += computerPointsScan.nextInt();
		}
	
		// prints the final scores and ultimate winner of the game, based on the values of totalPlayerPoints and totalComputerPoints
		System.out.println("\nFinal Score - Player (you): " + totalPlayerPoints + " point(s), Computer: " + totalComputerPoints + " point(s)");
		System.out.print("The ultimate winner is ");
		if (totalPlayerPoints > totalComputerPoints) {
			System.out.println("YOU, by " + (totalPlayerPoints - totalComputerPoints) + " point(s)! Well done.");
		} else if (totalComputerPoints > totalPlayerPoints) {
			System.out.println("the computer, by " + (totalComputerPoints - totalPlayerPoints) + " point(s)! Good try though.");
		} else {
			System.out.println("no one; it's surprisingly a tie!");
		}
		System.out.print("\nThanks for playing! Until next time...");
		
		// closes user input and file scanners
		playerPointsScan.close();
		computerPointsScan.close();
		scan.close();
		cardsFileScan.close();
	}
}
