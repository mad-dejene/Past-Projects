package fracCalc4;

import java.util.Scanner;

public class FractionalCalculator
{

	public static void program()

	{
		Scanner mad = new Scanner(System.in);
		System.out.println("Welcome to the Fraction Calculator!");
		System.out.print("Enter an expression (or \"quit\"): ");

		String userInput = mad.nextLine();

		while (!userInput.equals("quit"))
		{

			int theFirstSpace = userInput.indexOf(' ');
			int theSecondSpace = theFirstSpace + 2;

			String leftOperand = (userInput.substring(0, theFirstSpace));
			String operator = (userInput.substring(theFirstSpace + 1,
					theSecondSpace));
			String rightOperand = (userInput.substring(theSecondSpace + 1));

			String changedleftOperand = convertToFraction(leftOperand);
			String changedrightOperand = convertToFraction(rightOperand);

			String answer = calculate(changedleftOperand, operator,
					changedrightOperand);
			String result = reduce(answer);
			String finalAnswer = convertToMixed(result);
			System.out.println(finalAnswer);

			System.out.print("Enter an expression(or type quit): ");
			userInput = mad.nextLine();

		}

		System.out.println("Goodbye!");
	}

	public static String convertToFraction(String input)
	{
		if (!input.contains("/"))
		{
			input = input + "/1";
		}
		if (input.contains("_"))
		{
			String numbers[] = input.split("_");
			String wholeNumb = numbers[0];
			boolean negative = false;
			if (wholeNumb.contains("-"))
			{
				wholeNumb = wholeNumb.substring(1);
				negative = true;
			}

			String fraction = numbers[1];
			String numeratorAndDenominator[] = fraction.split("/");
			String numerator = numeratorAndDenominator[0];
			String denom = numeratorAndDenominator[1];
			int n = Integer.parseInt(numerator);
			int d = Integer.parseInt(denom);
			int w = Integer.parseInt(wholeNumb);

			int convertedNumerator = ((w * d) + n);
			String denominator = Integer.toString(d);
			String newNumerator = Integer.toString(convertedNumerator);
			if (negative)
			{
				input = "-" + newNumerator + "/" + denominator;
			} else
			{
				input = newNumerator + "/" + denominator;
			}
		}
		return input;
	}

	public static String calculate(String left, String operator, String right)
	{
		String result = null;
		String lNumbers[] = left.split("/");
		String num = lNumbers[0];
		String denom = lNumbers[1];

		int n = Integer.parseInt(num);
		int d = Integer.parseInt(denom);
		String rNumber[] = right.split("/");
		String othernum = rNumber[0];
		String otherdenom = rNumber[1];
		int othern = Integer.parseInt(othernum);
		int otherd = Integer.parseInt(otherdenom);
		if (operator.equals("+"))
		{
			result = addition(d, otherd, n, othern);
		}
		if (operator.equals("-"))
		{
			result = subraction(d, otherd, n, othern);
		}
		if (operator.equals("/"))
		{
			result = division(d, otherd, n, othern);
		}
		if (operator.equals("*"))
		{
			result = multiplication(d, otherd, n, othern);
		}
		return result;
	}

	public static String addition(int d, int otherd, int n, int othern)
	{
		String answer = null;
		String result = null;
		int denominator = otherd * d;
		int numerator1 = n * otherd;
		int numerator2 = othern * d;
		int numerator = numerator1 + numerator2;
		if (numerator == denominator)
		{
			result = "1";

		} else if (numerator % denominator == 0)
		{
			int a = numerator / denominator;
			answer = Integer.toString(a);
			result = answer;

		} else
		{
			String den = Integer.toString(denominator);
			String num = Integer.toString(numerator);
			result = num + "/" + den;

		}
		return result;
	}

	public static String subraction(int d, int otherd, int n, int othern)
	{
		String result = null;
		if (!(d == otherd))
		{
			int denominator = otherd * d;
			int numerator1 = n * otherd;
			int numerator2 = othern * d;
			int numerator = numerator1 - numerator2;
			if (numerator == denominator)
			{
				result = "1";
			} else
			{
				String den = Integer.toString(denominator);
				String num = Integer.toString(numerator);
				result = num + "/" + den;
			}
		}

		else
		{
			int numerator = n - othern;
			if (numerator == d)
			{
				result = "1";
			} else
			{
				String den = Integer.toString(d);
				String num = Integer.toString(numerator);
				result = num + "/" + den;
			}
		}
		return result;
	}

	public static String division(int d, int otherd, int n, int othern)
	{
		String result = null;
		int numerator = n * otherd;
		int denominator = d * othern;
		if (numerator == 0 / 1 || denominator == 0 / 1)
		{
			result = "0";
		} else if (numerator == denominator)
		{
			result = "1";
		} else
		{
			String den = Integer.toString(denominator);
			String num = Integer.toString(numerator);
			result = num + "/" + den;
		}
		return result;
	}

	public static String multiplication(int d, int otherd, int n, int othern)
	{
		String result = null;
		int denominator = otherd * d;
		int numerator = n * othern;
		if (numerator == 0 || denominator == 0)
		{
			result = "0";
		}
		if (numerator == denominator)
		{
			result = "1";
		}

		String den = Integer.toString(denominator);
		String num = Integer.toString(numerator);
		result = num + "/" + den;

		return result;
	}

	public static String reduce(String fraction)
	{
		if (fraction.contains("/"))
		{
			boolean negative = false;
			if (fraction.contains("-"))
			{
				fraction = fraction.substring(1);
				negative = true;
			}
			String separate[] = fraction.split("/");
			String num = separate[0];
			String den = separate[1];

			int n = Integer.parseInt(num);
			int d = Integer.parseInt(den);
			int greatestSoFar = 1;

			for (int i = 1; i <= n; i++)
			{
				if (Math.abs(n) % i == 0 && d % i == 0)
				{
					greatestSoFar = i;
				}
			}
			int numerator = n / greatestSoFar;
			int denominator = d / greatestSoFar;
			String top = Integer.toString(numerator);
			String bottom = Integer.toString(denominator);
			if (negative)
			{
				fraction = "-" + top + "/" + bottom;
			} else
			{
				fraction = top + "/" + bottom;
			}
		}
		return fraction;
	}

	public static String convertToMixed(String result)
	{
		int slash = result.indexOf("/");
		int underscore = result.indexOf("_");
		boolean negative = false;

		if (slash == -1 && underscore == -1)
		{
			return result;
		}

		if (result.contains("-"))
		{
			result = result.substring(1);
			negative = true;
		}
		String fraction[] = result.split("/");
		String num = fraction[0];
		String den = fraction[1];
		int n = Integer.parseInt(num);
		int d = Integer.parseInt(den);

		if (n == 0)
		{
			result = "0";
		}

		if (n > d)
		{
			if (n % d == 0)
			{
				int a = n / d;
				result = Integer.toString(a);
			} else
			{
				int wholeNumb = n / d;
				int numerator = n - ((wholeNumb) * d);
				int denominator = d;
				String w = Integer.toString(wholeNumb);
				String top = Integer.toString(numerator);
				String bottom = Integer.toString(denominator);
				if (negative)
				{
					result = "-" + w + "_" + top + "/" + bottom;
				} else
				{
					result = w + "_" + top + "/" + bottom;
				}
			}
		}
		return result;

	}

	public static void main(String[] args)
	{
		program();
	}
}
