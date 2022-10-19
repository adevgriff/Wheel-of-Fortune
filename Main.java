/**********************************************************
 * Class Name     : Main
 * Author         : Trevor Averill
 * Date           : 3/13/20
 * Course/Section : CSC 264
 * Program Description: This class will operate the funcitons
 * needed to run the program.
 *
 * Methods:
 * -------
 * main - Creates a new wheel of fortune game palys it and asks
 *			if user would like to play another game closes if not
 **********************************************************/

import java.util.*;

public class Main
{

	/**********************************************************
	* Method Name    : main
	* Author         : Trevor Averill
	* Date           : 3/13/20
	* Course/Section : CSC 264
	* Program Description: This method creates a new game and plays
	*		it when game is done running asks the user if they would
	*		like to play again if so creates another game and plays it
	*		looping until the user decides to quit
	*
	* BEGIN main
	*	Initialize game
	*	Initialize scan
	*	Call play method in game
	*	Ask user if they would like to play another round
	*	TRY
	*		Set choice to user input
	*	END TRY
	*	CATCH InputMismatchException
	*		Set choice to an invalid value
	*		Recreate scanner
	*	END CATCH InputMismatchException
	*	WHILE choice is not a valid input
	*		Ask user if they would like to play another round
	*		Recreate scanner
	*		TRY
	*			Set choice to user input
	*		END TRY
	*		CATCH InputMismatchException
	*			Set choice to an invalid value
	*			Recreate scanner
	*		END CATCH InputMismatchException
	*	END WHILE
	*	WHILE( player would like to keep playing )
	*		Setup next game
	*		Call play method in game
	*		Ask user if they would like to play another round
	*	END WHILE
	* END main
	**********************************************************/
	public static void main(String [] args)
	{
		//local constants
		final int PLAYING = 1;
		final int QUIT    = 2;

		//local variables
		Game game;      //a three round game
		Scanner scan;   //used to grab user input
		int choice;     //stores players choice on whether too continue or not

		/*******************************************************/

		//Initialize game
		game = new Game();

		//Initialize scan
		scan = new Scanner(System.in);

		//Call play method in game
		game.play();

		//Ask user if they would like to play another round
		Util.cls();
		System.out.print("\n\n\n\t\tWould you like to play another round?");
		System.out.print("\n\n\t\t1: for yes" + "\n\t\t2: for no");
		System.out.print("\n\n\t\tChoice: ");

		//TRY
		try
		{
			//Set choice to user input
			choice = scan.nextInt();

		}//END TRY

		//CATCH InputMismatchException
		catch(InputMismatchException ex)
		{
			//Set choice to an invalid value
			choice = -1;

			//Recreate scanner
			scan = new Scanner(System.in);

		}//END CATCH InputMismatchException

		//WHILE choice is not a valid input
		while(choice < 1 || choice > 2)
		{
			//Ask user if they would like to play another round
			Util.cls();
			System.out.print("\n\n\n\t\tWould you like to play another round?");
			System.out.print("\n\n\t\t1: for yes" + "\n\t\t2: for no");
			System.out.print("\n\n\t\tChoice: ");

			//Recreate scanner
			scan = new Scanner(System.in);

			//TRY
			try
			{
				//Set choice to user input
				choice = scan.nextInt();

			}//END TRY

			//CATCH InputMismatchException
			catch(InputMismatchException ex)
			{
				//Set choice to an invalid value
				choice = -1;

				//Recreate scanner
				scan = new Scanner(System.in);

			}//END CATCH InputMismatchException

		}//END WHILE

		//WHILE( player would like to keep playing )
		while(choice == PLAYING)
		{

			//Setup next game
			game = new Game();

			//Call play method in game
			game.play();

			//Ask user if they would like to play another round
			Util.cls();
			System.out.print("\n\n\n\t\tWould you like to play another round?");
			System.out.print("\n\n\t\t1: for yes" + "\n\t\t2: for no");
		    System.out.print("\n\n\t\tChoice: ");

		}//END WHILE

	}//END main

}//END Main()