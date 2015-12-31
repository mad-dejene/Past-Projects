import java.util.Scanner;

public class NumberGuessingGame
{
	public static void main(String[] srgs)
	{
		int unknownNumber = (int)(Math.random() *100 + 1);
		int guesses = 0;
		int solution = 0;

		Scanner console = new Scanner(System.in);
		System.out.println("Get ready for the number guessing game!");
		while (guesses < 6 && unknownNumber != solution )
		{
			System.out.print("I'm thinking of a number from 1-100");
			solution = console.nextInt();
			if ( solution < unknownNumber)
			{
				System.out.println("Too low.");
			}

			if ( solution > unknownNumber)
			{
				System.out.println ("Too high!");
			}

			if (solution == unknownNumber)
			{
				System.out.println ("Oh man. You figured it out");
			}
			guesses++;
		}

		if (solution == unknownNumber)
		{
			System.out.println ("Congradulation. You won!");
		}

		else
		{
			System.out.println("Sorry, you lost!");
		}


	}
}



