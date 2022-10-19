import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Util
{

	//class Constants
	 public static final String BLACK = "\033[0;30m";   // BLACK
	 public static final String RED = "\033[0;31m";     // RED
	 public static final String GREEN = "\033[0;32m";   // GREEN
	 public static final String YELLOW = "\033[0;33m";  // YELLOW
	 public static final String BLUE = "\033[0;34m";    // BLUE
	 public static final String PURPLE = "\033[0;35m";  // PURPLE
	 public static final String CYAN = "\033[0;36m";    // CYAN
     public static final String WHITE = "\033[0;37m";   // WHITE

   /**********************************************************
	* Method Name    : setLeft
	* Author         : Prof Scheemaker
	* Date           :
	* Course/Section :
	* Program Description:  This method will insert spaces
	*    in front of a string and return the new string. The
	*    number of spaces to insert and the string itself will
	*    be passed in as parameters.
	*
	* BEGIN setLeft (number of spaces, string)
	*   FOR (each space to be inserted)
	*      add a space to be beginning of the string
	*   END FOR
	*   return the new string with the leading spaces
	* END setLeft
	**********************************************************/

	public static String setLeft(int num, String word)
	{
		//local constants

		//local variables

		/*******************************************************/

		for (int i = 0;i < num;i++)

		   word = " " + word;

		return word;

	}
   /**********************************************************
	* Method Name    : setRight
	* Author         : Prof Scheemaker
	* Date           :
	* Course/Section :
	* Program Description:  This method will insert spaces
	*    in front of a string and return the new string. The
	*    number of spaces will be determined by the field
	*    width and the length of the string (Width - Length).
	*    If the string is wider than the field width, no
	*    spaces will be added to the front of the string
	*
	* BEGIN setRight (field width, string)
	*    Find the length of the string
	*    Calc the number of spaces to be added
	*    IF (there is room to add spaces)
	*       FOR (each space to be added)
	*          add a space to the front of the string
	*       END FOR
	*    END IF
	*    return the string
	* END setLeft
	**********************************************************/

	public static String setRight(int width, String word)
	{
		//local constants

        //local variables
		int len = word.length(); //get the string length
		int pad = width - len;   //how many spaces to add to front of string

		/*******************/

        //if there is room to add sapces
		if (len < width)

           //add the spaces to the front of the string
		   for (int i = 0; i < pad; i++)
		      word = " " + word;

        //return the string
		return word;

	}
	/**********************************************
	* Method Name        : cls
	* Author             : Gabriel Mercado
	* Date               : 1/29/2020
	* Course/Section     : CSC-264-501
	* Program Description: This method clears the screen.
	* BEGIN cls
	*	try to create a new processbuilder
	* END cls
	******************************************/
	public static void cls()
	{
		try
		{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch (Exception E)
		{
			System.out.println(E);
		}

	}

	/**********************************************
	* Method Name        : changeColor
	* Author             : Aero Griffin
	* Date               : 2/28/2020
	* Course/Section     : CSC-264-501
	* Program Description: This method prints a color
	*		code which changes the color of text on the
	*		screen the change lasts and must be reset
	*
	* BEGIN changeColor
	*	Print ANSI code
	* END changeColor
	******************************************/
	public static void changeColor(String color)
	{
		//Print provided ANSI code
		System.out.print(color);

	}//END changeColor

	/**********************************************
	* Method Name        : resetColor
	* Author             : Aero Griffin
	* Date               : 2/28/2020
	* Course/Section     : CSC-264-501
	* Program Description: This method prints a color
	*		code which resets the color of text on the
	*		screen
	*
	* BEGIN resetColor
	*	Print ANSI code for reset
	* END resetColor
	******************************************/
	public static void resetColor()
	{
		//Print ANSI code for reset
		System.out.print("\u001B[0m");

	}//END resetColor

	/**********************************************
	* Method Name        : playWav
	* Author             : Aero Griffin
	* Date               : 2/28/2020
	* Course/Section     : CSC-264-501
	* Program Description: This method takes in a wav
	*		file and plays it from start to end
	*
	* BEGIN playWav
	*	Create clip
	*	Play clip
	*	Wait for clip to end
	* END playWav
	******************************************/
	public static void playWav(File wav)
	{
		try
		{
		    //Create clip
		    Clip audioClip = AudioSystem.getClip();
		    audioClip.open(AudioSystem.getAudioInputStream(wav));

		    //Play clip
		    audioClip.start();

			//Wait for clip to end
			Thread.sleep(audioClip.getMicrosecondLength()/ 1000);

		}
		catch(IOException exception)
		{
			exception.printStackTrace();
		}
		catch(LineUnavailableException exception)
		{
			exception.printStackTrace();
		}
		catch(IllegalArgumentException exception)
		{

			System.out.print("No audio source");

		}
		catch (UnsupportedAudioFileException exception)
		{
			exception.printStackTrace();
		}
		catch(InterruptedException exception)
		{
			exception.printStackTrace();
		}

	}//END playWav

	/**********************************************
	* Method Name        : waitForUser
	* Author             : Aero Griffin
	* Date               : 2/28/2020
	* Course/Section     : CSC-264-501
	* Program Description: This method will pause
	*		the program until the user presses
	*		enter
	*
	* BEGIN waitForUser
	*	Ask user to press enter to continue
	*	Get user input
	* END waitForUser
	******************************************/
	public static void waitForUser()
	{

		//Ask user to press enter to continue
		System.out.print("Press the enter key to continue.....");

		try
		{
			//Get user input
			System.in.read();

		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}


	}//END waitForUser

	/**********************************************
	* Method Name        : delay
	* Author             : Aero Griffin
	* Date               : 2/28/2020
	* Course/Section     : CSC-264-501
	* Program Description: This method will pause execution
	*			for a user provided number of milliseconds
	*			handles interrupted exception
	*
	* BEGIN delay
	*	TRY
	*		Sleep program for user provided milliseconds
	*	END TRY
	*	CATCH interrupted exception
	*		Print stack trace
	*	END CATCH
	* END delay
	******************************************/
	public static void delay(int Milliseconds)
	{

		//TRY
		try
		{
			//Sleep program for user provided milliseconds
			Thread.sleep(Milliseconds);

		}//END TRY

		//CATCH interrupted exception
		catch(InterruptedException ex)
		{
			//Print stack trace
			ex.printStackTrace();

		}//END CATCH

	}//END delay

	/**********************************************************
	 * Method Name    : setLeftWithRightPadding
	 * Author         : Aero Griffin
	 * Date           : 2/28/2020
	 * Course/Section : Java Programming
	 * Program Description:  This method will insert spaces
	 *    in front of a string and return the new string. The
	 *    number of spaces to insert and the string itself will
	 *    be passed in as parameters then add spaces to the end
	 *
	 *
	 * BEGIN setLeftWithRightPadding (number of spaces, string)
	 *   Set wordLength to word's length
	 *   Set word to itself with left padding added
	 *   Calculate the number of spaces that need to be added to the end of word
	 *   FOR every space that needs to be added
	 *		Add a space to the end of word
	 *	 END FOR
	 *	 Return word
	 * END setLeftWithRightPadding
	 **********************************************************/
	public static String setLeftWithRightPadding(int num, String word)
	{
		//local constants

		//local variables
		int endSpaces;
		int wordLength;

		/*******************************************************/

		//Set wordLength to word's length
		wordLength = word.length();

		//Set word to itself with left padding added
		word = setLeft(((num - wordLength) / 2), word);

		//Calculate the number of spaces that need to be added to the end of word
		endSpaces = ((num - wordLength) / 2) + (wordLength % 2 == 0 ? 0 : 1);

		//FOR every space that needs to be added
		for (int i = 0;i < endSpaces;i++)

			//Add a space to the end of word
			word = word + " ";

		//END FOR

		//Return word
		return word;

	}//END setLeftWithRightPadding

	/**********************************************************
	 * Method Name    : addPadding
	 * Author         : Aero Griffin
	 * Date           : 2/28/2020
	 * Course/Section : Java Programming
	 * Program Description:  This method will return the required
	 *			number of spaces in a String
	 *
	 *
	 * BEGIN addPadding (number of spaces, string)
	 *   Initialize padding
	 *   FOR every space that is needed
	 *   	Add a space to padding
	 *   END FOR
	 *	 Return padding
	 * END setLeftWithRightPadding
	 **********************************************************/
	public static String addPadding(int numberOfSpaces)
	{
		//local constants

		//local variables
		String padding;  //String that contains the spaces needed

		/*******************************************************/

		//Initialize padding
		padding = "";

		//FOR every space that is needed
		for (int i = 0; i < numberOfSpaces ;i++)

			//Add a space to padding
			padding = padding + " ";

		//END FOR

		//Return padding
		return padding;

	}//END addPadding

}