/**********************************************************************
 *Program Name   :   WheelOfFortune - Phrase
 *Author         :   Aero Griffin
 *Due Date       :   March 1, 2020
 *Course/Section :   Java Programming
 *Program Description: This class defines what a Phrase/Puzzle is for
 *			use in a game of wheel of fortune
 *
 * Phrase            - constucts a new Phrase object
 * guess             - checks to see if a provided puzzle solve was correct
 * toString          - formats the infomation for Phrase into a String
 * letterGuess       - checks to see how many times a provided guess is in the puzzle
 * getGuessedLetters - returns a string with all the characters guessed so far
 *********************************************************************/

public class Phrase
{
	//class constants
	private final String HARD_DIFFICULTY   = "HARD";
	private final String MEDIUM_DIFFICULTY = "MEDIUM";
	private final String EASY_DIFFICULTY   = "EASY";

	//class variables
	private String phrase;            //the actual phrase players are trying to figure out
	private String displayPhrase;     //the phrase players see at any given time
	private String category;	      //category hint at what the phrase is
	private String guessedLetters;    //a string holding all the letters that the players have guessed
	private String difficulty;        //stores the difficulty of this phrase

	/**********************************************************************
	 *Method Name    :   Phrase(String phrase, String category, String difficulty)
	 *Author         :   Aero Griffin
	 *Due Date       :   March 1, 2020
	 *Course/Section :   Java Programming
	 *Program Description: Constructs a phrase object with given puzzle, category or
	 *			hint, and difficulty sets displayPhrase to its initial state
	 *			and guessedLetters to immedately display spaces hyphens and &
	 *
	 *BEGIN Phrase(String phrase, String category, String difficulty)
	 *	Set phrase to user provided phrase
	 *	Set category to user provided category
	 *	Set difficulty to user provided difficulty
	 *	Set displayPhrase to an empty string
	 *	Set guessedLetters to a string containing characters that need to show up
	 *		FOR each character in phrase
	 *			IF current character is present in guessedLetters String
	 *				Add that character onto the end of displayPhrase
	 *			ELSE character is not present in guessedLetters String
	 *				Add a - character onto the end of displayPhrase
	 *			END IF
	 *		END FOR
	 *END Phrase
	 ***********************************************************************/
	 public Phrase(String phrase, String category, String difficulty)
	 {
		 //local constants

		 //local variables

		 /******************************************************/

		 //Set phrase to user provided phrase
		 this.phrase     = phrase;

		 //Set category to user provided category
		 this.category   = category;

		 //Set difficulty to user provided difficulty
		 this.difficulty = difficulty;

		 //Set displayPhrase to an empty string
		 displayPhrase = "";

         //Set guessedLetters to a string containing characters that need to show up
		 guessedLetters = " -&";

		 //FOR each character in phrase
		 for(int pos = 0; pos < phrase.length(); pos++)
		 {

			 //IF current character is present in guessedLetters String
			 if(guessedLetters.indexOf(phrase.charAt(pos)) >= 0)
			 {

				//Add that character onto the end of displayPhrase
			 	displayPhrase = displayPhrase + phrase.charAt(pos);

			 }//ELSE character is not present in guessedLetters String
			 else

			 	//Add a - character onto the end of displayPhrase
				displayPhrase = displayPhrase + "-";

			 //END IF

		 }//END FOR

	 }//END Phrase

	/**********************************************************************
	*Method Name    :   guess
	*Author         :   Aero Griffin
	*Due Date       :   March 1, 2020
	*Course/Section :   Java Programming
	*Program Description: This method gets a String representing a players
	*			attempt to solve the puzzle and returns if the player was
	*			successful or not
	*
	*BEGIN guess(String guess)
	*	Return true when provided guess is equal to puzzle false otherwise
	*END guess
	***********************************************************************/
	public boolean guess(String guess)
	{
	  //local constants

	  //local variables

	  /*******************************************************************/

	  //Returns true when provided guess is equal to puzzle false otherwise
	  return guess.equals(phrase);

	}//END guess

	/**********************************************************************
	*Method Name    :   letterGuess
	*Author         :   Aero Griffin
	*Due Date       :   March 1, 2020
	*Course/Section :   Java Programming
	*Program Description: This method gets a character guess from a player
	*			and checks to see how many times that guess was present in the
	*			puzzle and returns that also reformats the dispaly String when
	*			nessessary
	*
	*BEGIN letterGuess(char guess)
	*	Set success to zero
	*	Add the players guess to guessedLetters
	*	IF the players guess is present in the puzzle
	*		Set displayPhrase to an empty string
	*		FOR each character in the puzzle
	*			IF current character is the players guess
	*				Add one to success
	*			END IF
	*			IF current character is present in puzzle
	*				Add current character to the end of dispalyPhrase
	*			ELSE current character is not present in puzzle
	*				Add a - character to the end of displayPhrase
	*			END IF
	*		END FOR
	*	END IF
	*	Return success
	*END letterGuess
	***********************************************************************/
	public int letterGuess(char guess)
	{
		  //local constants


		  //local variables
		  int success;    //the number of letters successfully guessed

		  /*******************************************************************/

		  //Set success to zero
		  success = 0;

		  //Add the players guess to guessedLetters
		  guessedLetters = guessedLetters + guess;

		  //IF the players guess is present in the puzzle
		  if(!(phrase.indexOf(guess) < 0))
		  {

				//Set displayPhrase to an empty string
				displayPhrase = "";

			    //FOR each character in the puzzle
				for(int pos = 0; pos < phrase.length(); pos++)
				{

					//IF current character is the players guess
					if(phrase.charAt(pos) == guess)
					{

						//Add one to success
						success++;

					}//END IF

					//IF current character is present in puzzle
					if(guessedLetters.indexOf(phrase.charAt(pos)) >= 0)
					{

						//Add current character to the end of dispalyPhrase
						displayPhrase = displayPhrase + phrase.charAt(pos);

					}//ELSE current character is not present in puzzle
					else

						//Add a - character to the end of displayPhrase
						displayPhrase = displayPhrase + "-";

					//END IF

				}//END FOR

		  }//END IF

		  //Return success
		  return success;

	}//end letterGuess

	 /**********************************************************************
	  *Method Name    :   toString
	  *Author         :   Aero Griffin
	  *Due Date       :   March 1, 2020
	  *Course/Section :   Java Programming
	  *Program Description:This method returns a string formated with category
	  *			centered at the top, puzzle centered underneeth it, and difficulty
	  *			centered underneeth that
	  *
	  *BEGIN toString
	  *		Return formated puzzle information as a String
	  *END toString
	  ***********************************************************************/
	  public String toString()
	  {
		  //local constants

		  //local variables

		  /*******************************************************************/

		  //Return formated puzzle information as a String
		  return Util.setLeft(60 - category.length()/2,category) + "\n" +
		  			Util.setLeft(60 - displayPhrase.length()/2, displayPhrase) + "\n" +
		  			Util.setLeft(60 - difficulty.length()/2, difficulty) + "\n";

	  }//END toString

	  /**********************************************************************
	  *Method Name        :   getGuessedLetters
	  *Author             :   Aero Griffin
	  *Due Date           :   March 1, 2020
	  *Course/Section     :   Java Programming
	  *
	  *Program Description: Gets the string that holds the letters that have been
	  *			guessed
	  *
	  *BEGIN getGuessedLetters
	  *   return guessedLetters
	  *END getGuessedLetters
	  ***********************************************************************/
	  public String getGuessedLetters()
	  {

		  	//local constants

		  	//local variables

			/*******************************************************************/

			//Return guessedLetters
			return guessedLetters;

	  }//END toString

}//END Phrase


