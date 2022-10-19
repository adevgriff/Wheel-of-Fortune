/**********************************************************************
 *Program Name   :   WheelOfFortune - Screen
 *Author         :   Aero Griffin
 *Due Date       :   March 1, 2020
 *Course/Section :   Java Programming
 *Program Description: This class provides methods that handle most of
 *			the programs input and output so other classes can focus on logic
 *
 * getPlayerChoice       - displays a menu of choices to spin guess or buy vowel
 *				gets the users choice returns negitive one when choice invalid
 * gameScreen            - displays games main screen calls getPlayerChoice to get input
 * inputConsanentsScreen - Prompts user for and gets users input for a unguessed
 *				consonant
 * inputVowelsScreen     - displays info needed and gets a vowel guess from the user
 * solveScreen           - displays info needed and gets a players attempt to solve the puzzle
 * winScreen             - displays the name of the winner and the number amount of money they won
 * roundEndScreen        - displays all the players updated total scores
 *********************************************************************/

 import java.util.*;

public class Screen
{
	//class constants
	public static final int SPIN      = 1;
	public static final int GUESS     = 2;
	public static final int BUY_VOWEL = 3;


	//class variables

	/**********************************************************************
	*Method Name    :   getPlayerChoice
	*Author         :   Aero Griffin
	*Due Date       :   March 1, 2020
	*Course/Section :   Java Programming
	*Program Description: Prompts a player to choose the next action they would
	*		like to preform returns the value the player entered or a negitive one
	*		if the value entered was invalid and caused an exception
	*
	*BEGIN getPlayerChoice()
	*   Prompt user for input
	*	TRY
	*		Set choice to player input
	*	END TRY
	*	CATCH InputMismatchException
	*		Set choice to negitive one
	*		Refresh scanner so a new input can be entered
	*	END CATCH
	*	Return choice
	*END getPlayerChoice
	***********************************************************************/
	public static int getPlayerChoice()
	{
	  //local constants
	  Scanner scan = new Scanner(System.in);

	  //local variables
	  int choice;  //stores the value the player entered

	  /*******************************************************************/

	  //Prompt user for input
	  System.out.print("\n");
	  System.out.print( Util.setLeft(45, SPIN + ": Spin the wheel") + "\n" +
	  				Util.setLeft(45, GUESS + ": Solve the puzzle") + "\n" +
	  				Util.setLeft(45, BUY_VOWEL + ": Buy a vowel\n\n"));
	  System.out.print(Util.setLeft(45, "Enter choice: "));

	  //TRY
	  try
	  {
		  //Set choice to player input
		  choice = scan.nextInt();

  	  }//END TRY

  	  //CATCH InputMismatchException
  	  catch(InputMismatchException ex)
  	  {

		  //Set choice to negitive one
		  choice = -1;

		  //Refresh scanner so a new input can be entered
		  scan = new Scanner(System.in);

	  }//END CATCH

	  //Return choice
	  return choice;

	}//END guess

	/**********************************************************************
	*Method Name    :   gameScreen
	*Author         :   Aero Griffin
	*Due Date       :   March 1, 2020
	*Course/Section :   Java Programming
	*Program Description: This method displays all the information the user needs
	*			to decide what to do next calls getPlayerChoice until an input is valid
	*			when an input is not valid redraws the screen to try again
	*
	*BEGIN gameScreen(Player players[], int playerCount, Phrase phrase, int currentPlayer,
	*		String availConsanents, String availVowels)
	*	Clear screen
	*	Display phrase information
	*	Display used(RED) and unused letters(WHITE)
	*	Display players with a > pointing to current player
	*	Get current players choice and store it in choice
	*	WHILE choice is not a spin, guess, or buy vowel
	*		Clear screen
	*		Display phrase information
	*		Display used(RED) and unused letters(WHITE)
	*		Display players with a > pointing to current player
	*		Get current players choice and store it in choice
	*	END WHILE
	*	Return choice
	*END gameScreen
	***********************************************************************/
	public static int gameScreen(Player players[], int playerCount, Phrase phrase,
				int currentPlayer, String availConsanents, String availVowels)
	{
	  //local constants
	  final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	  //local variables
	  int choice;            //Holds the players current choice
	  String playerString;   //String to hold the string to display a players information

	  /*******************************************************************/

	  //Clear screen
	  Util.cls();

	  //Display phrase information
	  System.out.print("\n\n\n");
	  System.out.println(phrase);

	  //Display used(RED) and unused letters(WHITE)
	  System.out.print(Util.setLeft(60 - 13, ""));

	  for(int pos = 0; pos < ALPHABET.length(); pos++)
	  {
		  if(availConsanents.indexOf(ALPHABET.charAt(pos)) < 0 && availVowels.indexOf(ALPHABET.charAt(pos)) < 0)
		  {
			  Util.changeColor(Util.RED);
			  System.out.print(ALPHABET.charAt(pos));
			  Util.changeColor(Util.WHITE);
		  }
		  else
			System.out.print(ALPHABET.charAt(pos));
	  }

	  //Display players with a > pointing to current player
	  System.out.print("\n\n");

	  for(int pos = 0; pos < playerCount; pos++)
	  {
		  if(pos == currentPlayer)
		  	playerString = "> ";
		  else
		  	playerString = "  ";
		  playerString = playerString + players[pos];

		  System.out.print(Util.setLeft(59 - playerString.length()/2, playerString) + "\n");
	  }

	  //Get current players choice and store it in choice
	  choice = getPlayerChoice();

	  //WHILE choice is not a spin, guess, or buy vowel
	  while(choice < SPIN || choice > BUY_VOWEL)
	  {
		  //Clear screen
		  Util.cls();

		  //Display phrase information
		  System.out.print("\n\n\n");
		  System.out.println(phrase);

		  //Display used(RED) and unused letters(WHITE)
		  System.out.print(Util.setLeft(60 - 13, ""));

		  for(int pos = 0; pos < ALPHABET.length(); pos++)
		  {
			  if(availConsanents.indexOf(ALPHABET.charAt(pos)) < 0 && availVowels.indexOf(ALPHABET.charAt(pos)) < 0)
			  {
				  Util.changeColor(Util.RED);
				  System.out.print(ALPHABET.charAt(pos));
				  Util.changeColor(Util.WHITE);
			  }
			  else
				System.out.print(ALPHABET.charAt(pos));
		  }

		  //Display players with a > pointing to current player
		  System.out.print("\n\n");

		  for(int pos = 0; pos < playerCount; pos++)
		  {
			  if(pos == currentPlayer)
				playerString = "> ";
			  else
				playerString = "  ";
			  playerString = playerString + players[pos];

			  System.out.print(Util.setLeft(59 - playerString.length()/2, playerString) + "\n");
		  }

		  //Get current players choice and store it in choice
	 	  choice = getPlayerChoice();

	  }//END WHILE

	  //Return choice
	  return choice;

	}//END gameScreen

	 /**********************************************************************
	  *Method Name    :   inputConsanentsScreen
	  *Author         :   Aero Griffin
	  *Due Date       :   March 1, 2020
	  *Course/Section :   Java Programming
	  *Program Description: Displays information needed to make a choice of
	  *				what consonants to guess and gets that choice from the player
	  *			    will continue to prompt user for input until valid guess is entered
	  *
	  *BEGIN inputConsanentsScreen(Phrase phrase, String availConsanents)
	  *		Clear screen
	  *		Display phrase information and enough spaces to center next line
	  *		Display all consonants previously guessed in red and avaliable guesses in white
	  *		Prompt user for a guess
	  *		TRY
	  *		   Set consonant to the first character in the string that was entered
	  *		END TRY
	  *		CATCH InputMismatchException
	  *		   Set consonant to an invalid entry
	  *		   Refresh scanner so a new input can be entered
	  *		END CATCH
	  *		WHILE consonant is not valid
	  *		   TRY
	  *		      Clear screen
	  *		      Display phrase information and enough spaces to center next line
	  *		      Display all consonants previously guessed in red and avaliable guesses in white
	  *		      Prompt user for a guess
	  *		      Set consonant to the first character in the string that was entered
	  *		   END TRY
	  *		   CATCH InputMismatchException
	  *		      Set consonant to an invalid entry
	  *		      Refresh scanner so a new input can be entered
	  *		   END CATCH
	  *		END WHILE
	  *		Return consonant
	  *END inputConsanentsScreen
	  ***********************************************************************/
	  public static char inputConsanentsScreen(Phrase phrase, String availConsanents)
	  {
		  //local constants
		  char consanent;
		  Scanner scan = new Scanner(System.in);
		  final String allConsanents = "BCDFGHJKLMNPQRSTVWXYZ";

		  //local variables

		  /*******************************************************************/

		  //Clear screen
		  Util.cls();

		  //Display phrase information and enough spaces to center next line
		  System.out.print("\n\n\n\n\n\n\n\n" + phrase + "\n                                                  ");

		  //Display all consonants previously guessed in red and avaliable guesses in white
		  for(int pos = 0; pos < allConsanents.length(); pos++)
		  {
			  	if(availConsanents.indexOf(allConsanents.charAt(pos)) < 0)
			  	{
				 	 Util.changeColor(Util.RED);
				  	System.out.print(allConsanents.charAt(pos));
				  	Util.changeColor(Util.WHITE);
			  	}
			  	else
			  		System.out.print(allConsanents.charAt(pos));
		  }

		  //Prompt user for a guess
		  System.out.print("\n\n" + Util.setLeft(50, "Enter consonant: "));

		  //TRY
		  try
		  {
			  	//Set consonant to the first character in the string that was entered
		  	  	consanent = scan.next().toUpperCase().charAt(0);

		  }//END TRY

		  //CATCH InputMismatchException
		  catch(InputMismatchException ex)
		  {
			  	//Set consonant to an invalid entry
		  	  	consanent = 'a';

		  	  	//Refresh scanner so a new input can be entered
		  	  	scan = new Scanner(System.in);

		  }//END CATCH

		  //WHILE consonant is not valid
		  while(availConsanents.indexOf(consanent) < 0)
		  {
			    //TRY
				try
				{

					//Clear screen
					Util.cls();

					//Display phrase information and enough spaces to center next line
					System.out.print("\n\n\n\n\n\n\n\n" + phrase + "\n                                                  ");

					//Display all consonants previously guessed in red and avaliable guesses in white
					for(int pos = 0; pos < allConsanents.length(); pos++)
					{
						  if(availConsanents.indexOf(allConsanents.charAt(pos)) < 0)
						  {
							 	 Util.changeColor(Util.RED);
							 	 System.out.print(allConsanents.charAt(pos));
							 	 Util.changeColor(Util.WHITE);
						  }
						  else
								System.out.print(allConsanents.charAt(pos));
					}

					//Prompt user for a guess
					System.out.print("\n\n" + Util.setLeft(50, "Enter consonant: "));

					//Set consonant to the first character in the string that was entered
					consanent = scan.next().toUpperCase().charAt(0);

				}//END TRY

				//CATCH InputMismatchException
				catch(InputMismatchException ex)
				{

					//Set consonant to an invalid entry
					consanent = 'a';

					//Refresh scanner so a new input can be entered
					scan = new Scanner(System.in);

				}//END CATCH

		  }//END WHILE

		  //Return consonant
		  return consanent;

	  }//END inputConsanentsScreen

	  /**********************************************************************
	  *Method Name    :   inputVowelsScreen
	  *Author         :   Aero Griffin
	  *Due Date       :   March 1, 2020
	  *Course/Section :   Java Programming
	  *Program Description:
	  *
	  *BEGIN inputVowelsScreen(Phrase phrase, String availVowels)
	  *		Clear screen
	  *		Display phrase information and enough spaces to center next line
	  *		Display all vowels, previously guessed in red, and avaliable guesses in white
	  *		Prompt user to guess a vowel
	  *		TRY
	  *			Set vowel to the first character in the string that was entered
	  *		END TRY
	  *		CATCH InputMismatchException
	  *			Set vowel to an invalid entry
	  *			Refresh scanner so a new input can be entered
	  *		END CATCH
	  *		WHILE vowel is not valid
	  *			TRY
	  *				Clear Screen
	  *				Dispaly phrase information
	  *				Display all vowels previously guessed in red and avaliable guesses in white
	  *				Promt user to guess a vowel
	  *				Set vowel to the first character in the string that was entered
	  *			END TRY
	  *			CATCH InputMismatchException
	  *				Set vowel to an invalid entry
	  *				Refresh scanner so a new input can be entered
	  *			END CATCH
	  *		END WHILE
	  *		Return vowel
	  *END inputVowelsScreen
	  ***********************************************************************/
	  public static char inputVowelScreen(Phrase phrase, String availVowels)
	  {
		  //local constants
		  char vowel;
		  Scanner scan = new Scanner(System.in);
		  final String allVowels = "AEIOU";

		  //local variables

		  /*******************************************************************/

		  //Clear screen
		  Util.cls();

		  //Display phrase information and enough spaces to center next line
		  System.out.print("\n\n\n\n\n\n\n\n" + phrase + "\n                                                          ");

		  //Display all vowels, previously guessed in red, and avaliable guesses in white
		  for(int pos = 0; pos < allVowels.length(); pos++)
		  {
			  if(availVowels.indexOf(allVowels.charAt(pos)) < 0)
			  {
				  Util.changeColor(Util.RED);
				  System.out.print(allVowels.charAt(pos));
				  Util.changeColor(Util.WHITE);
			  }
			  else
				System.out.print(allVowels.charAt(pos));
		  }

		  //Prompt user to guess a vowel
		  System.out.print("\n\n" + Util.setLeft(52, "Enter vowel: "));

		  //TRY
		  try
		  {

			//Set vowel to the first character in the string that was entered
			vowel = scan.next().toUpperCase().charAt(0);

		  }//END TRY

		  //CATCH InputMismatchException
		  catch(InputMismatchException ex)
		  {

			//Set vowel to an invalid entry
			vowel = 'b';

			//Refresh scanner so a new input can be entered
			scan = new Scanner(System.in);

		  }//END CATCH

		  //WHILE vowel is not valid
		  while(availVowels.indexOf(vowel) < 0)
		  {

				//TRY
				try
				{

					//Clear Screen
					Util.cls();

				    //Dispaly phrase information
					System.out.print("\n\n\n\n\n\n\n\n" + phrase + "\n                                                          ");

					//Display all vowels previously guessed in red and avaliable guesses in white
					for(int pos = 0; pos < allVowels.length(); pos++)
					{
						  if(availVowels.indexOf(allVowels.charAt(pos)) < 0)
						  {
							  Util.changeColor(Util.RED);
							  System.out.print(allVowels.charAt(pos));
							  Util.changeColor(Util.WHITE);
						  }
						  else
							System.out.print(allVowels.charAt(pos));
					}

					//Promt user to guess a vowel
					System.out.print("\n\n" + Util.setLeft(52, "Enter vowel: "));

					//Set vowel to the first character in the string that was entered
					vowel = scan.next().toUpperCase().charAt(0);

				}//END TRY

				//CATCH InputMismatchException
				catch(InputMismatchException ex)
				{

					//Set vowel to an invalid entry
					vowel = 'b';

					//Refresh scanner so a new input can be entered
					scan = new Scanner(System.in);

				}//END CATCH

		  }//END WHILE

		  //Return vowel
		  return vowel;

	  }//END inputVowelsScreen

	  /**********************************************************************
	  *Method Name        :   solveScreen
	  *Author             :   Aero Griffin
	  *Due Date           :   March 1, 2020
	  *Course/Section     :   Java Programming
	  *
	  *Program Description: This method gets the users attempt to solve the puzzle
	  *			and returns the success as a boolean true when successful false when not
	  *
	  *BEGIN solveScreen(Phrase phrase)
	  *		Clear screen
	  *		Dispaly phrase information
	  *		Prompt user to solve the puzzle
	  *		Return true if user is successful false if not
	  *END solveScreen
	  ***********************************************************************/
	  public static boolean solveScreen(Phrase phrase)
	  {
		  //local constants

		  //local variables

		  /*******************************************************************/

		  //Clear screen
		  Util.cls();

		  //Display phrase information
		  System.out.print("\n\n\n\n\n\n\n");
		  System.out.println(phrase + "\n\n");

		  //Prompt user to solve the puzzle
		  System.out.print(Util.setLeft(51, "Solve the puzzle: "));

		  //Return true if user is successful false if not
		  return phrase.guess(Keyboard.readString().toUpperCase());

	  }//END solveScreen

	  /**********************************************************************
	  *Method Name        :   winScreen
	  *Author             :   Aero Griffin
	  *Due Date           :   March 1, 2020
	  *Course/Section     :   Java Programming
	  *
	  *Program Description: This method displays the infomation nessaccerry about
	  *			the provided winning player
	  *
	  *BEGIN winScreen(Player player)
	  *		Clear screen
	  *		Display winning players info
	  *		Wait for user
	  *END winScreen
	  ***********************************************************************/
	  public static void winScreen(Player player)
	  {
		  //local constants

		  //local variables

		  /*******************************************************************/

		  //Clear screen
		  Util.cls();

		  //Display winning players info
		  System.out.print("\n\n\n\n\n\n\n\n\n\n");
		  System.out.println(Util.setLeft(57 - player.getName().length()/2, player.getName() + " Wins!"));
		  System.out.print(Util.setLeft(60 - (player.getTotalScore() + "").length()/2,player.getTotalScore() + ""));
		  System.out.print("\n\n");

		  //Wait for user
          Util.waitForUser();

	  }//END winScreen

	  /**********************************************************************
	  *Method Name        :   roundEndScreen
	  *Author             :   Aero Griffin
	  *Due Date           :   March 1, 2020
	  *Course/Section     :   Java Programming
	  *
	  *Program Description:This method displays nessacarry info about the end of
	  *				a round all the players and their updated totals
	  *
	  *BEGIN roundEndScreen(Player players[], int playerCount, int winner)
	  *		Clear screen
	  *		Display updated player information
	  *		Wait for user
	  *END roundEndScreen
	  ***********************************************************************/
	  public static void roundEndScreen(Player players[], int playerCount)
	  {
		  //local constants

		  //local variables
		  String format;

		  /*******************************************************************/

		  //Clear screen
		  Util.cls();

		  //Display updated player information
		  System.out.print("\n\n\n\n\n\n\n\n");
		  System.out.print(Util.setLeft(53 ,"Updated Totals\n\n"));

		  for(int pos = 0; pos < players.length; pos++)
		  {
			  format = players[pos].getName() + Util.addPadding(20 - players[pos].getName().length()) +
			  					Util.setRight(10, players[pos].getTotalScore() + "");
			  System.out.print(Util.setLeft(60 - format.length() / 2, format) + "\n\n");
		  }

		  //Wait for user
          Util.waitForUser();

  	  }//END roundEndScreen

}//END Screen


