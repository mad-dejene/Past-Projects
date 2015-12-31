import java.util.Scanner;
public class FourthJavaProject
{
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		System.out.print("How many rows?");
		int rows = console.nextInt();
		console.close();
		for (int i = 1; i <= rows; i++)
		{
			System.out.println();

			for (int j = 0; j < i; j++)
			{
				System.out.print("*");
			}


		}

	}
}