/**********************************************************
 * Class Name     : Round
 * Author         : Trevor Averill
 * Date           : 3/13/20
 * Course/Section : CSC 264
 * Program Description: This class will hold all information
 * pertinent to each round within the game.
 *
 * Methods:
 * -------
 * Round - constructor
 * playRound - plays the round until a player wins or quits the game
 * spin - allow the player to spin the wheel
 * guess - allow the player to guess a letter or solve the puzzle
 * buyVowel - allow player to buy a vowel
 * removeChar - removes a correctly guessed letter
 * nextPlayer - starts the next players turn
 *
 **********************************************************/

public class Round
{

	//class variables
	private Player players[];               //array holding players
	private Wheel wheel;                    //instance of a wheel
	private Phrase phrase;                  //the particular phrase
	private int playerCount;                //count of players in the game
	private int menuChoice;                 //player input for menu choice
	private int spinAmnt;                   //monetary value of the users spin
	private int currentPlayer;              //current user in game
	private boolean roundStatus;            //whether or not the round is over
	private String availableConsanents;     //available consanents in letter pool
	private String availableVowels;         //available vowels in letter pool
	private int winner;                     //integer representing the player index of the winning player

	//class constants
	private final int VOWEL_PRICE = 250;


	/**********************************************************
	* Method Name    : Round
	* Author         : Trevor Averill
 	* Date           : 3/13/20
 	* Course/Section : CSC 264
    * Program Description: creates a round to be played with a
    * given array of players and number of players in the array
    * and a phrase.
    *
    * BEGIN Round
    *	Set players to provided players
	*	Set playerCount to provided playerCount
	*	Set phrase to provided phrase
	*	Set wheel to provided wheel
	*	Set availableConsanents to all available consanents
	*	Set availableVowels to all available vowels
	*	Set currentPlayer the provided starting player
	*	Set roundStatus to true
    * END Round
    **********************************************************/

	public Round(Player []players, int playerCount, Phrase phrase, Wheel wheel, int firstPlayer)
	{
		//local variables

		//local constants

		/****************Start here***************************/

		//Set players to provided players
		this.players = players;

		//Set playerCount to provided playerCount
		this.playerCount = playerCount;

		//Set phrase to provided phrase
		this.phrase = phrase;

		//Set wheel to provided wheel
		this.wheel = wheel;

		//Set availableConsanents to all available consanents
		availableConsanents = "BCDFGHJKLMNPQRSTVWXYZ";

		//Set availableVowels to all available vowels
		availableVowels = "AEIOU";

		//Set currentPlayer to the provided starting player
		currentPlayer = firstPlayer;

		//Set roundStatus to true
		roundStatus = true;

	}//END Round

	/**********************************************************
	* Method Name    : playRound
	* Author         : Trevor Averill
 	* Date           : 3/13/20
 	* Course/Section : CSC 264
    * Program Description: plays the round until a player wins
    *
    * BEGIN playRound
    *	FOR each of the players
	*		Reset their roundScores
	*	END FOR
	*	Call play turn method
	*	WHILE (roundStatus is true(happens when the puzzle is not solved))
	*		Call play turn method
	*	END WHILE
	*	Set winner to current player
	*	Return winner
    * END playRound
    **********************************************************/

	public int playRound()
	{
		//local variables

		//local constants

		/****************Start here***************************/

		//FOR each of the players
		for(int pos = 0; pos < players.length; pos++)
		{

			//Reset their roundScores
			players[pos].roundLoss();

		}//END FOR

		//Call play turn method
		playTurn();

		//WHILE (roundStatus is true(happens when the puzzle is not solved))
		while(roundStatus)
		{

			//Call play turn method
			playTurn();

		}//END WHILE

		//Set winner to current player
		winner = currentPlayer;

		//Return winner
		return winner;

	}//END playRound

	/**********************************************************
	* Method Name    : playTurn
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: Plays one turn of a round
	*
	* BEGIN playTurn
	*	Set menu choice to the result of calling the game screen
	*	IF(menu choice is spin)
	*		Call spin method
	*	ELSE IF(menu choice is guess)
	*		Call guess method
	*	ELSE IF(menu choice is buy vowel)
	*		Call buyVowel method
	*	END IF
	* END playTurn
	**********************************************************/

	public void playTurn()
	{
		//local variables

		//local constants

		/****************Start here***************************/

		//Set menu choice to the result of calling the game screen
		menuChoice = Screen.gameScreen(players, playerCount, phrase, currentPlayer,
						availableConsanents, availableVowels);

		//IF(menu choice is spin)
		if(menuChoice == Screen.SPIN)
		{

			//Call spin method
			spin();

		}

		//ELSE IF(menu choice is guess)
		else if(menuChoice == Screen.GUESS)
		{

			//Call guess method
			guess();

		}

		//ELSE IF(menu choice is buy vowel)
		else if(menuChoice == Screen.BUY_VOWEL)
		{

			//Call buy vowel method
			buyVowel();

		}//END IF

	}//END playTurn

		/**********************************************************
		* Method Name    : spin
		* Author         : Trevor Averill
		* Date           : 3/13/20
		* Course/Section : CSC 264
		* Program Description: this method is run when a user chooses
		* to spin the wheel, Can bankrupt a player, make them loose a
		* turn, or give them an amount of points based on the spin
		* Whether a player has guessed correctly if player did
		* not guess correctly allows next player to play.
		*
		* BEGIN spin
		*	Store wheel value
		*	IF(wheel vaulue = bankrupt)
		*		use roundLoss method to reset roundScore
		*		Call nextPlayer method
		*	ELSE IF(wheel value = lose a turn)
		*		Call nextPlayer method
		*	ELSE
		*		set consanent variable = to input from user
		*		set number of consanents = to integer returned by letterguess method
		*		IF(number of consanents = 0)
		*			call next player method
		*			set available consanents = to the list of consanents returned by removeChar class
		*		ELSE
		*			call successful guess method for our current player
		*			set available consanents = consanents in removeChar class
		*		END IF
		*	END IF
		* END spin
		**********************************************************/

		private void spin()
		{
			//local variables
			int numConsanents; //the number of consanents successfully guessed
			char consanent;    //consanent entered by the player

			//local constants

			/****************Start here***************************/

			//Store wheel value
			spinAmnt = wheel.spin();

			//IF(wheel vaulue = bankrupt)
			if(spinAmnt == wheel.BANKRUPT)
			{

				//use roundLoss method to reset roundScore
				players[currentPlayer].roundLoss();

				//Call nextPlayer method
				nextPlayer();

			}
			//ELSE IF(wheel value = lose a turn)
			else if(spinAmnt == wheel.LOSE_A_TURN)
			{

				//Call nextPlayer method
				nextPlayer();

			}//ELSE
			else
			{

				//set consanent variable = to input from user
				consanent = Screen.inputConsanentsScreen(phrase, availableConsanents);

				//set number of consanents = to integer returned by letterguess method
				numConsanents = phrase.letterGuess(consanent);

				//IF(number of consanents = 0)
				if(numConsanents == 0)
				{

					//call next player method
					nextPlayer();

					//set available consanents = to the list of consanents returned by removeChar class
					availableConsanents = removeChar(availableConsanents, consanent);

				}//ELSE
				else
				{

					//call successful guess method for our current player
					players[currentPlayer].successfulGuess(spinAmnt, numConsanents);

					//set available consanents = consanents in removeChar class
					availableConsanents = removeChar(availableConsanents, consanent);

				}//END IF

			}//END IF

	    }//END spin

	    /**********************************************************
		* Method Name    : guess
		* Author         : Trevor Averill
		* Date           : 3/13/20
		* Course/Section : CSC 264
		* Program Description: this method allows a player to attempt
		* to solve a puzzle when the player guesses wrong passes turn
		* on to the next player otherwise ends the round with a win
		* for that player.
		*
		* BEGIN guess
		*	IF(user input is not equal to the phrase)
		*		Call next player
		*	ELSE
		*		Set roundStatus to false
		*	END IF
		* END guess
		**********************************************************/

		private void guess()
		{
			//local variables
			String guessedPhrase;       //the users guessed phrase

			//local constants

			/****************Start here***************************/

			//IF(user input is not equal to the phrase)
			if(!Screen.solveScreen(phrase))
			{

				//Call next player
				nextPlayer();

			}//ELSE
			else
			{

				//Set roundStatus to false
				roundStatus = false;

			}//END IF

		}//END guess

		/**********************************************************
		* Method Name    : buyVowel
		* Author         : Trevor Averill
		* Date           : 3/13/20
		* Course/Section : CSC 264
		* Program Description: this method allows a player to buy a
		* vowel when they have enough money. When the player guesses
		* a correct vowel it remains their turn otherwise the turn
		* goes to the next player.
		*
		* BEGIN buyVowel
		*	Store roundScore as score
		*	IF(score greater or equal to 250
		*		Subtract VOWEL_PRICE from roundScore
		*		Set vowel to vowel user enters
		*		Set numVowels = integer returned from the letterGuess method
		*		IF(numVowels is equal to 0
		*			Call nextPlayer method
		*			Set available vowels = available vowels returned from removeChar
		*		ELSE
		*			set available vowels = available vowels returned from removeChar
		*		END IF
		*	END IF
		* END buyVowel
		**********************************************************/

		private void buyVowel()
		{
			//local variables
			int score;     //players round score
			int numVowels; //number of vowels succesfully guessed
			char vowel;    //vowel player guessed

			//local constants

			/****************Start here***************************/

			//Store roundScore as score
		    score = players[currentPlayer].getRoundScore();

            //IF(score greater or equal to 250
		    if(score >= 250 && !availableVowels.equals(""))
		    {
				//Subtract VOWEL_PRICE from roundScore
				players[currentPlayer].spendPoints(VOWEL_PRICE);

				//Set vowel to vowel user enters
				vowel = Screen.inputVowelScreen(phrase, availableVowels);

                //Set numVowels = integer returned from the letterGuess method
				numVowels = phrase.letterGuess(vowel);

                //IF(numVowels is equal to 0
				if(numVowels == 0)
				{
					//Call nextPlayer method
					nextPlayer();

					//Set available vowels = available vowels returned from removeChar
					availableVowels = removeChar(availableVowels, vowel);

				}//ELSE
				else
				{

					//set available vowels = available vowels returned from removeChar
					availableVowels = removeChar(availableVowels, vowel);

				}//END IF

			}//END IF

		}//END buyVowel

		/**********************************************************
		* Method Name    : removeChar
		* Author         : Trevor Averill
		* Date           : 3/13/20
		* Course/Section : CSC 264
		* Program Description: this method removes the first instance
		* of a given character from a given string.
		*
		* BEGIN removeChar
		*		return available characters
		* END removeChar
		**********************************************************/

		private String removeChar(String string, char character)
		{
			//local variables

			//local constants

			/****************Start here***************************/

			//return available characters
			return string.substring(0, string.indexOf(character)) +
				   string.substring(string.indexOf(character) + 1);


	    }//END removeChar

	    /**********************************************************
		* Method Name    : nextPlayer
		* Author         : Trevor Averill
		* Date           : 3/13/20
		* Course/Section : CSC 264
		* Program Description: this method set current player to the
		* next player in line.
		*
		* BEGIN nextPlayer
		*	IF(current player = 2)
		*	   set current player = 0
		*   ELSE
		*      incremement current player
		*   END IF
		* END nextPlayer
		**********************************************************/

		private void nextPlayer()
		{
			//local variables

			//local constants

			/****************Start here***************************/

			//IF(current player = 2)
			if(currentPlayer == 2)
			{

				//set current player = 0
				currentPlayer = 0;

			}
			//ELSE
			else
			{
				//incremement current player
				currentPlayer++;

			}//END IF

	    }//END nextPlayer

}//END Round