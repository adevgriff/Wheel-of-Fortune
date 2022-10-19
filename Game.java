/**********************************************************
 * Class Name     : Game
 * Author         : Trevor Averill
 * Date           : 3/13/20
 * Course/Section : CSC 264
 * Program Description: This class defines what a game of wheel
 *			of fortune consists of
 *
 * Methods:
 * -------
 * game - this method sets up 3 rounds of a game.
 * play - this method plays a full 3 round game of wheel of
 *        fortune.
 * fileReader - this method reads the puzzles from the input file.
 **********************************************************/
import java.io.*;
import java.util.*;

public class Game
{
	//class variables
    private Player []players;       //an array of players playing the game
    private Phrase []easyPuzzles;   //an array of easy puzzles
    private Phrase []mediumPuzzles; //an array of medium puzzles
    private Phrase []hardPuzzles;   //an array of hard puzzles
    private Round []rounds;         //an array of rounds to be played
    private int playerCount;        //the number of players playing the game
    private String fileName;        //String to hold the file name holding the puzzles
    private Wheel wheel;            //wheel used to get a random spin
    private Random rand;            //random used to get a random spin
    private int maxHard;            //counts the max number of hard puzzles
	private int maxMedium;          //counts the max number of medium puzzles
	private int maxEasy;            //counts the max number of easy puzzles

    //class constants

    /**********************************************************
	* Method Name    : game
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: this method sets up three rounds of a
	* 			game of wheel of fortune
	*
	* BEGIN game
	* 		initialize rand
	*       Initialize rounds to an array of 3 Round
	*       Initialize players to an array of 3 Player
	*       Initialize easyPuzzles to an array of 3 Phrase
	*       Initialize mediumPuzzles to an array of 3 Phrase
	*       Initialize hardPuzzles to an array of 3 Phrase
	*       Set playerCount to 3
	*       Input first players name
	*       Input second players name
	*       Input third players name
	*       Input file name for the puzzles
	*       TRY
	*       	Read and process puzzle data
	*       END TRY
	*       CATCH Exception
	*       END CATCH
	*       Set wheel to a new first round Wheel
	*       Setup first round
	*       Set wheel to a new third round Wheel
	*       Setup thrid round
	* END game
	**********************************************************/

    public Game()
	{
		//local variables

		//local constants

		/****************************************************/

		//Initalize rand
		rand = new Random(System.currentTimeMillis());

		//Initialize rounds to an array of 3 Round
		rounds = new Round[3];

		//Initialize players to an array of 3 Player
		players = new Player[3];

		//Set playerCount to 3
		playerCount = 3;

		//Input first players name
		System.out.print("\n\n\n\t\tEnter Player 1's name: ");
		players[0] = new Player(Keyboard.readString());

		//Input second players name
		System.out.print("\n\n\t\tEnter Player 2's name: ");
		players[1] = new Player(Keyboard.readString());

		//Input third players name
		System.out.print("\n\n\t\tEnter Player 3's name: ");
		players[2] = new Player(Keyboard.readString());

		//Input file name for the puzzles
		System.out.print("\n\n\t\tEnter the puzzle file name: ");
		fileName = Keyboard.readString();

		//TRY
		try
		{
			//Read and process puzzle data
			readFile();

		}//END TRY

		//CATCH Exception
		catch(Exception ex)
		{

		}//END CATCH

		//Set wheel to a new first round Wheel
		wheel = new Wheel(1);

		//Setup first round
		rounds[0] = new Round(players, playerCount, easyPuzzles[rand.nextInt(maxEasy)], wheel, 0);

		//Set wheel to a new second round Wheel
		wheel = new Wheel(2);

		//Setup second round
		rounds[1] = new Round(players, playerCount, mediumPuzzles[rand.nextInt(maxMedium)], wheel, 1);

		//Set wheel to a new third round Wheel
		wheel = new Wheel(3);

		//Setup thrid round
		rounds[2] = new Round(players, playerCount, hardPuzzles[rand.nextInt(maxHard)], wheel, 2);


	}//END Game

	/**********************************************************
	* Method Name    : play
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: this method plays a full 3 round game of
	* wheel of fortune
	*
	* BEGIN play
	* 	   Play first round and set winners total score and call the round end screen when done
	*      Play second round and set winners total score and call the round end screen when done
	*      Play third round and set winners total score
	*      Set winner to first player
	*      IF (first player has a higher total score than the second and second has a higher total score than the third player)
	*			Set winner to second player
	*	   ELSE IF(third player has a higher total score than the first)
	*			Set winner to third player
	*	   END IF
	*	   Call winScreen with the winner
	* END play
	**********************************************************/
	public void play()
	{
		//local variables
		Player winner;    //used to hold the winning player

		//local constants

		/*****************************************************/

		//Play first round and call the round end screen when done
		players[rounds[0].playRound()].roundWin();
		Screen.roundEndScreen(players, playerCount);

		//Play second round and call the round end screen when done
		players[rounds[1].playRound()].roundWin();
		Screen.roundEndScreen(players, playerCount);

		//Play third round
		players[rounds[2].playRound()].roundWin();

		//Set winner to first player
		winner = players[0];

		//IF (first player has a higher total score than the second and second has a higher total score than the third player)
		if(players[0].getTotalScore() < players[1].getTotalScore() && players[1].getTotalScore() > players[2].getTotalScore())
		{
			//Set winner to second player
			winner = players[1];
		}

		//ELSE IF(third player has a higher total score than the first)
		else if(players[2].getTotalScore() > players[0].getTotalScore())
		{
			//Set winner to third player
			winner = players[2];

		}//END IF

		//Call winScreen with the winner
		Screen.winScreen(winner);

	}//END play

	/**********************************************************
    * Method Name    : fileReader
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: This method reads the file containing puzzles
	*			in the format puzzle,category,difficulty and sorts them into the
	*			appropreate arrays so they can be used in the game
	*
	* BEGIN fileReader
	*	Set max easy puzzles to zero
	*	Set max medium puzzles to zero
	*	Set max hard puzzles to zero
	*	set oneLine to the first line in file
	*	WHILE file has puzzles to proccess
	*		Set oneLine to a substring just holding the difficulty of the puzzle
	*		IF oneLine is easy
	*			Add one to maxEasy
	*		ELSE IF oneLine is medium
	*			Add one to maxMedium
	*		ELSE IF oneLine is hard
	*			Add one to maxHard
	*		END IF
	*		Set onLine to the next line in file
	*	END WHILE
	*	Reopen the inputFile and inputFileReader to start over at line one
	*	set oneLine to the first line in file
	*	Initialize easyPuzzles to the number of easy puzzles in the file
	*	Initialize mediumPuzzles to the number of medium puzzles in the file
	*	Initialize hardPuzzles to the number of hard puzzles in the file
	*	set easyCounter = 0
	*	set mediumCounter = 0
	*	set hardCounter = 0
	*	WHILE file has puzzles to proccess
	*		set phrase = to substring of oneline from first character to first comma
	*		set oneLine = manipulated phrase
	*		set category = to substring of oneline from first character to next comma
	*		set oneline = manipulated phrase
	*		set difficulty = to substring of oneline from first character to next comma
	*		IF difficulty = easy
	*			select a puzzle from easy puzzles array
	*			increment easy puzzle counter
	*		ELSE IF difficulty = medium
	*			select puzzle from medium puzzles array
	*			increment medium puzzle counter
	*		ELSE
	*			select puzzle from hard array
	*			increment hard puzzle counter
	*			set oneLine = selected string from input file
	*	END WHILE
	* END fileReader
	**********************************************************/
	private void readFile() throws Exception
	{
		//local variables
		FileReader inputFile = new FileReader(fileName);               //input file reader
		BufferedReader inputFileReader = new BufferedReader(inputFile);//buffered reader to manipulate the data in a file
		String oneLine;                                                //name of manipulated string
		String phrase;                                                 //selected phrase in for the round
		String category;                                               //category of the phrase
		String difficulty;                                             //difficulty of the phrase
		int easyCounter;                                               //counter for easy puzzles
		int mediumCounter;                                             //counter for medium puzzles
		int hardCounter;                                               //counter for hard puzzles

		//local constants
		final String EASY = "easy";
		final String MEDIUM = "medium";
		final String HARD = "hard";

		/*****************************************************/

		//Set max easy puzzles to zero
		maxEasy = 0;

		//Set max medium puzzles to zero
		maxMedium = 0;

		//Set max hard puzzles to zero
		maxHard = 0;

		//set oneLine to the first line in file
		oneLine = inputFileReader.readLine();

		//WHILE file has puzzles to proccess
		while(oneLine != null)
		{

			//Set oneLine to a substring just holding the difficulty of the puzzle
			oneLine = oneLine.substring(oneLine.indexOf(',') + 1);
			oneLine = oneLine.substring(oneLine.indexOf(',') + 1).toUpperCase();

			//IF oneLine is easy
			if(oneLine.equals("EASY"))

				//Add one to maxEasy
				maxEasy++;

			//ELSE IF oneLine is medium
			else if(oneLine.equals("MEDIUM"))

				//Add one to maxMedium
				maxMedium++;

			//ELSE IF oneLine is hard
			else if(oneLine.equals("HARD"))

				//Add one to maxHard
				maxHard++;

			//END IF

			//Set onLine to the next line in file
			oneLine = inputFileReader.readLine();

		}//END WHILE

		//Reopen the inputFile and inputFileReader to start over at line one
		inputFile = new FileReader(fileName);
		inputFileReader = new BufferedReader(inputFile);

		//set oneLine to the first line in file
		oneLine = inputFileReader.readLine();

		//Initialize easyPuzzles to the number of easy puzzles in the file
		easyPuzzles = new Phrase[maxEasy];

		//Initialize mediumPuzzles to the number of medium puzzles in the file
		mediumPuzzles = new Phrase[maxMedium];

		//Initialize hardPuzzles to the number of hard puzzles in the file
		hardPuzzles = new Phrase[maxHard];

		//set easyCounter = 0
		easyCounter = 0;

		//set mediumCounter = 0
		mediumCounter = 0;

		//set hardCounter = 0
		hardCounter = 0;

		//WHILE file has puzzles to proccess
		while(oneLine != null)
		{

			//set phrase = to substring of oneline from first character to first comma
			phrase = oneLine.substring(0, oneLine.indexOf(",")).toUpperCase();

			//set oneLine = manipulated phrase
			oneLine = oneLine.substring(phrase.length() + 1);

			//set category = to substring of oneline from first character to next comma
			category = oneLine.substring(0, oneLine.indexOf(",")).toUpperCase();

			//set oneline = manipulated phrase
			oneLine = oneLine.substring(category.length() + 1);

			//set difficulty = to substring of oneline from first character to next comma
			difficulty = oneLine.substring(0).toUpperCase();

			//IF difficulty = easy
			if(difficulty.equalsIgnoreCase(EASY))
			{
				 //select a puzzle from easy puzzles array
				 easyPuzzles[easyCounter] = new Phrase(phrase, category, difficulty);

				 //increment easy puzzle counter
				 easyCounter++;

			}
			//ELSE IF difficulty = medium
			else if(difficulty.equalsIgnoreCase(MEDIUM))
			{
				 //select puzzle from medium puzzles array
				 mediumPuzzles[mediumCounter] = new Phrase(phrase, category, difficulty);

				 //increment medium puzzle counter
				 mediumCounter++;

			}
			//ELSE
			else
			{
				 //select puzzle from hard array
				 hardPuzzles[hardCounter] = new Phrase(phrase, category, difficulty);

				 //increment hard puzzle counter
				 hardCounter++;

			}

			 //set oneLine = selected string from input file
			oneLine = inputFileReader.readLine();

		 }//END WHILE

	}//END readFile

}//END Game