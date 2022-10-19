/**********************************************************
 * Class Name     : Player
 * Author         : Trevor Averill
 * Date           : 3/13/20
 * Course/Section : CSC 264
 * Program Description: This class will hold all information
 * pertinent to each player within the game.
 *
 * Methods:
 * -------
 * Player - constructor
 * successfulGuess -updates round score for the player
 * spendPoints - allow player to spend money and to buy a vowel
 * roundWin - how the players total score changes when a round is won
 * roundLoss - how the players total score changes when a round is loss
 * getTotalScore - getter for total score of each player
 * toString - print information for the player
 *
 **********************************************************/

public class Player
{
	//class variables
	private String name;    //players name
	private int roundScore; //players current score for the round
	private int totalScore; //players current total score

	//class constants

   /**********************************************************
    * Method Name    : Player
    * Author         : Trevor Averill
 	* Date           : 3/13/20
 	* Course/Section : CSC 264
    * Program Description: creates a new player object with a given name.
    *
    * BEGIN Player
    *      set name
    *      initialize roundScore
    *      initialize totalScore
    * END Player
    **********************************************************/

	public Player(String name)
	{
		//local variables

		//local constants

		/****************Start here***************************/

		//Set name
		this.name = name;

		//initialize roundScore
		roundScore = 0;

		//initialize totalScore
		totalScore = 0;

	}//END Player

	/**********************************************************
	* Method Name    : successfulGuess
	* Author         : Trevor Averill
    * Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: this method adds won points to the
	* players round score based on the number of the letter that
	* was present and the amount that was spun.
	*
	* BEGIN successfulGuess
	*      Add spinAmnt times the letterAmnt to the total roundScore
	* END successfulGuess
	**********************************************************/

    public void successfulGuess(int spinAmnt, int letterAmnt)
	{
    	//local variables

		//local constants

		/****************Start here***************************/

		//Add spinAmnt times the letterAmnt to the total roundScore
		roundScore = roundScore + (spinAmnt * letterAmnt);

	}//END successfulGuess

	/**********************************************************
	* Method Name    : spendpoints
	* Author         : Trevor Averill
    * Date           : 3/13/20
    * Course/Section : CSC 264
	* Program Description: this method subtracts a given amount
	* from the players round score.
	*
	* BEGIN spendpoints
	*      Subtract amntSpent from roundScore
	* END spendpoints
	**********************************************************/

    public void spendPoints(int amntSpent)
	{
	 	//local variables

		//local constants

		/****************Start here***************************/

        //Subtract amntSpent from roundScore
		roundScore = roundScore - amntSpent;

	}//END spendpoints

	/**********************************************************
	* Method Name    : roundWin
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
    * Program Description: this method adds the round score to
    * the total score and resets the round score back to zero.
	*
	* BEGIN roundWin
	*      Add roundScore to totalScore
	*      set roundscore = 0
	* END roundWin
	**********************************************************/

    public void roundWin()
	{
     	//local variables

		//local constants

		/****************Start here***************************/

        //Add roundScore to totalScore
		totalScore = roundScore + totalScore;

		//Set roundScore = 0
		roundScore = 0;

	}//END roundWin

	/**********************************************************
	* Method Name    : roundLoss
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: this method sets the round score to zero.
	*
	* BEGIN roundLoss
	*      Set roundScore to 0
	* END roundLoss
	**********************************************************/

	public void roundLoss()
	{
	   	//local variables

		//local constants

		/****************Start here***************************/

        //Set roundScore to 0
		roundScore = 0;

	}//END roundLoss

	/**********************************************************
	* Method Name    : getTotalScore
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: This method returns a players total score.
	*
	* BEGIN getTotalScore
	*      Return totalScore
	* END getTotalScore
	**********************************************************/

	public int getTotalScore()
	{
	   	//local variables

		//local constants

		/****************Start here***************************/

        //Return totalScore
		return totalScore;

	}//END getTotalScore

	/**********************************************************
	* Method Name    : getName
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: This method returns a name
	*
	* BEGIN getTotalScore
	*      Return name
	* END getTotalScore
	**********************************************************/

	public String getName()
	{
		//local variables

		//local constants

		/****************Start here***************************/

		//Return totalScore
		return name;

    }//END getName

	/**********************************************************
	* Method Name    : getRoundScore
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: This method returns a player's round score.
	*
	* BEGIN getRoundScore
	*      Return getRoundScore
	* END getRoundScore
	**********************************************************/

	public int getRoundScore()
	{
	   	//local variables

		//local constants

		/****************Start here***************************/

	    //Return getRoundScore
		return roundScore;

	}//END getRoundScore

	/**********************************************************
	* Method Name    : toString
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: this method formats the players data
	* into a neatly printable string and returns it.
	*
	* BEGIN toString
	*      Set formatted equal to name
	*	   FOR each space needed at the end of name
	*			Add a space at the end of formatted
	*	   END FOR
	*	   Return formatted with roundScore added at the end
	* END toString
	**********************************************************/

	public String toString()
	{
	   	//local variables
	   	String formatted; //String used to assist in formatting players info

		//local constants
		final int MAX_NAME_CHAR = 20;

		/****************Start here***************************/
        //Set formatted equal to name
		formatted = name;

		//FOR each space needed at the end of name
		for(int i = 1; i < MAX_NAME_CHAR - name.length(); i++)
		{

			//Add a space at the end of formatted
			formatted = formatted + " ";

		}//END FOR

		//Return formatted with roundScore added at the end
		return formatted + ": " + Util.setRight(10, roundScore + "");

	}//END toString

} //END Player