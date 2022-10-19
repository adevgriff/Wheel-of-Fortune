/**********************************************************************
 *Program Name   :   WheelOfFortune - Wheel
 *Author         :   Aero Griffin
 *Due Date       :   March 1, 2020
 *Course/Section :   Java Programming
 *Program Description: This class defines a wheel object for wheel of fortune
 *			contains methods to spin a wheel and display the result and public
 *			constants to decypher the information returned by the Wheel object
 *
 * Wheel            - Constucts a new Wheel object
 * updateWheelRound - updates values on the wheel for the provided round
 * spin             - animates the wheel spining an returns the value wheel lands on
 * wheelFormat      - helps to draw the wheel centering values and changing constants
 *						to the strings they represent
 *********************************************************************/

import java.io.File;
import java.util.Random;

public class Wheel
{

    //Class Constants
    public final int LOSE_A_TURN = -1; //Constant used to know when a player looses a turn
    public final int BANKRUPT     = -2; //Constant used to know when a player goes bankrupt

    //Class Variables
    private int []wheelSpins = new int[24];  //Array representing all the possible wheel outcomes
    private Random rand      = new Random(); //used to pick a random spot on the wheel
    private int currentPos;                  //current position the wheel is at

	/**********************************************************
	* Method Name        : Wheel
	* Author             : Aero Griffin
	* Date               : March 2, 2020
	* Course/Section     : Java Programming
	* Program Description: This method is used to construct a wheel
	*		with a provided round and hardcoded base values
	*
	* BEGIN Wheel(int round)
	*	Initialize the wheel to base values
	*	Update wheel values based on the round
	*	Set the currentPos to a random value
	* END Wheel
	**********************************************************/
    public Wheel(int round)
    {
        //local constants

        //local variables

        /****************Start Here****************/

		//Initialize the wheel to base values
        wheelSpins[0] = 1000;
        wheelSpins[1] = 500;
        wheelSpins[2] = 900;
        wheelSpins[3] = 700;
        wheelSpins[4] = 300;
        wheelSpins[5] = -1;
        wheelSpins[6] = 550;
        wheelSpins[7] = 400;
        wheelSpins[8] = 500;
        wheelSpins[9] = 600;
        wheelSpins[10] = 350;
        wheelSpins[11] = 500;
        wheelSpins[12] = 900;
        wheelSpins[13] = -2;
        wheelSpins[14] = 650;
        wheelSpins[15] = 800;
        wheelSpins[16] = 700;
        wheelSpins[17] = -1;
        wheelSpins[18] = 800;
        wheelSpins[19] = 500;
        wheelSpins[20] = 450;
        wheelSpins[21] = 500;
        wheelSpins[22] = 300;
        wheelSpins[23] = -2;

        //Update wheel values based on the round
        updateWheelRound(round);

		//Set the currentPos to a random value
        currentPos = rand.nextInt(wheelSpins.length);

    }//END Wheel

    /**********************************************************
	* Method Name        : updateWheelRound
	* Author             : Aero Griffin
	* Date               : March 2, 2020
	* Course/Section     : Java Programming
	* Program Description: This method will update a wheel based
	*		on a provided round with a 2 it will update the wheel
	*		to have double the values and with a 3 will triple all
	*		other values leave base numbers
	*
	* BEGIN updateWheelRound(int round)
	*	IF(on the second or third round)
	*		FOR(every possible spin)
	*			IF(the wheelSpins at pos is not loose a turn or bankrupt)
	*				Mutiply wheelSpins at pos by the round value
	*			END IF
	*		END FOR
	*	END IF
	* END updateWheelRound
	**********************************************************/
	private void updateWheelRound(int round)
	{

		//local constants
		final int roundTwo   = 2;
		final int roundThree = 3;

		//local variables

		/****************Start Here****************/

		//IF (on the second or third round)
		if(round == roundTwo || round == roundThree)
		{

			//FOR(every possible spin)
			for(int pos = 0; pos < wheelSpins.length; pos++)
			{

				//IF(the wheelSpins at pos is not loose a turn or bankrupt)
				if(wheelSpins[pos] != LOSE_A_TURN && wheelSpins[pos] != BANKRUPT)

					//Mutiply wheelSpins at pos by the round value
					wheelSpins[pos] *= round;

				//END IF

			}//END FOR

		}//END IF

    }//END updateWheelRound

	/**********************************************************
	* Method Name        : spin
	* Author             : Aero Griffin
	* Date               : March 2, 2020
	* Course/Section     : Java Programming
	* Program Description: This method clears screen and animates a
	*			spinning wheel that will go through a minimum number
	*			of values and return the value that the wheel lands on
	*
	* BEGIN spin
	*	Sets tick to the wav file for tick
	*	Sets the number of spins to a random index on the wheel plus a minimum number of spins
	*	FOR(the number of spins)
	*		IF(currently at the last index on the wheel)
	*			Set current position to zero
	*		ELSE not at the last index on the wheel yet
	*			Add one to the current position
	*		END IF
	*		Play a tick sound
	*		Clear screen
	*		Draw the wheel based on the current position
	*		Sleep for an amount of time that increases with each spin
	*	END FOR
	*	Wait for user to press enter
	*	Return value at current pos
	* END spin
	**********************************************************/
    public int spin()
    {

		//local constants
		final int FRICTION = 12;
		final int MIN_SPINS = 15;

		//local variables
		File tick;       //Holds the audio file for the ticking noise
		int numberOfSpins;    //The minimum number of frames in the wheel spin

        /****************Start Here****************/

		//Sets tick to the wav file for tick
		tick = new File("tick.wav");

		//Sets the number of spins to a random index on the wheel plus a minimum number of spins
		numberOfSpins = rand.nextInt(wheelSpins.length) + MIN_SPINS;

		//FOR(the number of spins)
		for(int spins = 0; spins < numberOfSpins; spins++)
		{

			//IF(currently at the last index on the wheel)
			if (currentPos == 23)

				//Set current position to zero
				currentPos = 0;

			//ELSE not at the last index on the wheel yet
			else

				//Add one to the current position
				currentPos++;

			//END IF

			//Play a tick sound
			Util.playWav(tick);

			//Clear the screen
			Util.cls();

			//Draw the wheel based on the current position
			Util.changeColor(Util.WHITE);
			System.out.print( "\n\n\n\n\n" + Util.setLeft((120 - 16) / 2, "****************") + "\n" +
						Util.setLeft((120 - 16) / 2, "*" + wheelFormat(wheelSpins[(currentPos - 3 < 0 ? 23 - ((currentPos - 2) * -1) : currentPos - 3)])) + "*\n" +
						Util.setLeft((120 - 16) / 2, "****************") + "\n" +
						Util.setLeft((120 - 16) / 2, "*" + wheelFormat(wheelSpins[(currentPos - 2 < 0 ? 23 - ((currentPos - 1) * -1) : currentPos - 2)])) + "*\n" +
						Util.setLeft((120 - 16) / 2, "****************") + "\n" +
						Util.setLeft((120 - 16) / 2, "*" + wheelFormat(wheelSpins[(currentPos - 1 < 0 ? 23 - ((currentPos) * -1) : currentPos - 1)])) + "*\n" +
						Util.setLeft((120 - 30) / 2, "******************************") + "\n" +
						Util.setLeft((120 - 16) / 2, " " + wheelFormat(wheelSpins[currentPos])) + "\n" +
						Util.setLeft((120 - 30) / 2, "******************************") + "\n" +
						Util.setLeft((120 - 16) / 2, "*" + wheelFormat(wheelSpins[(currentPos + 1 > 23 ? currentPos + 1 - 24 : currentPos + 1)])) + "*\n" +
						Util.setLeft((120 - 16) / 2, "****************") + "\n" +
						Util.setLeft((120 - 16) / 2, "*" + wheelFormat(wheelSpins[(currentPos + 2 > 23 ? currentPos + 2 - 24 : currentPos + 2)]) ) + "*\n" +
						Util.setLeft((120 - 16) / 2, "****************") + "\n" +
						Util.setLeft((120 - 16) / 2, "*" + wheelFormat(wheelSpins[(currentPos + 3 > 23 ? currentPos + 3 - 24 : currentPos + 3)])) + "*\n" +
						Util.setLeft((120 - 16) / 2, "****************") + "\n");

			//Sleep for an amount of time that increases with each spin
			Util.delay(spins * FRICTION );

		}//END FOR

		//Wait for user to press enter
		Util.waitForUser();

		//Return value at current pos
		return wheelSpins[currentPos];

    }//END spin

	/**********************************************************
	* Method Name        : wheelFormat
	* Author             : Aero Griffin
	* Date               : March 2, 2020
	* Course/Section     : Java Programming
	* Program Description: This method takes in a value and translates
	*		values that represent loose turn and bankrupt and values
	*		that dont into formated strings that will look good on the
	*		wheel
	*
	* BEGIN wheelFormat
	*	Set the code string to a centered number with left and right padding
	*	IF ( code is loose turn )
	*		Set the code string to a centered loose turn with left and right padding
	*	ELSE IF (code is bankrupt)
	*		Set the code string to a centered bankrupt with left and right padding
	*	END IF
	*	Return code string
	* END wheelFormat
	**********************************************************/
    private String wheelFormat(int code)
    {
        //local constants

        //local variables
        String codeString; //String to format the return in

        /****************Start Here****************/

		//Set the code string to a centered number with left and right padding
        codeString = Util.setLeftWithRightPadding(14 ,code + "");

		//IF ( code is loose turn )
        if(code == LOSE_A_TURN)

        	//Set the code string to a centered loose turn with left and right padding
            codeString = Util.setLeftWithRightPadding(14 ,"Lose Turn");

        //ELSE IF ( code is bankrupt )
        else if(code == BANKRUPT)

        	//Set the code string to a centered bankrupt with left and right padding
            codeString = Util.setLeftWithRightPadding(14 ,"Bankrupt");

		//END IF

		//Return code string
        return codeString;

    }//END wheelFormat


}//END Wheel
