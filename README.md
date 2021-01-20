# Crazy Eights
Java implementation of *Crazy Eights* (card game)

**Game Rules Description:**

Crazy Eights is a popular card game, in which the objective is to be the first player to get rid of all your dealt cards. 
There are many variations of this game, but in the variation that I will be implementing in Java, there will be only two players; the computer, and a human opponent. Using a shuffled virtual deck of 52 cards (no Jokers), 7 cards will be randomly dealt to each player. The rest of the deck is set aside. The top card of the deck is flipped over to form a new separate pile (the ‘discard’ pile). If the top card is an 8, then the whole deck is reshuffled and a new top card is flipped over. The human player will always get the first turn.

During a player's turn, they can play one card face up on top of the discard pile, that matches the current top card of the pile in either suit or rank. For example, if the current top card is the 4 of Diamonds, you may play either a 4 of another suit (Hearts, Spades, or Clubs) or another card with a suit of Diamonds (e.g. 7 of Diamonds). 
All Eights are wild and can be played at any time. When a player plays an Eight, they then get to pick the suit of the card that the opponent must play next, whether that be Hearts, Clubs, Spades, or Diamonds. 

If a player is unable to play any of their cards during their turn, then they must draw a card from the top of the deck. Only one card can be drawn per turn, and that card cannot be played during that turn. Once the deck is empty, if a player doesn’t have a playable card, then they lose their turn.
The Jacks, Twos, and Queen of Spades are special cards. If a player plays a Jack, then they get an additional turn. If a player plays a 2, then their opponent will be forced to take 2 cards from the top of the deck. Lastly, if a player plays the Queen of Spades, then their opponent will be forced to take 5 cards from the top of the deck!
The first player to discard of all their cards first, wins!

If the user decides to play the game more than once, then a point system will be utilized to determine an ultimate winner once the user decides to stop playing. At the end of each game, based on the remaining cards in the loser’s hand, points will be awarded to the winner. 10 points are given for each face card such as the King or Queen, the face value number of points for the number cards (e.g. 5 points for a 5 of Diamonds), 1 point for an Ace, and 50 points for an 8.

**Gameplay Instructions:**

In order to play this game, download all of the files in this repository (excluding this one), and then run the *CrazyEights.java* file through either an IDE such as Eclipse, or a Java code compiler. If you're having any issues, you could also play the game via the online IDE, Repl.it, [here](https://repl.it/@krishchopra8/Crazy-Eights#Main.java)! Have fun :)
