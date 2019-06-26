import java.util.Scanner;

/**
 * Driver class
 * 
 * 
 * @author Jeong-Hyun Boo
 *
 */
public class TextUI

{
	private static String num;
	private static String oprtr;

	public static void main(String args[]) 
	{
		CalculatorState state = new SimpleCalcState();
		Scanner scan = new Scanner(System.in);

		while (scan.hasNext()) 
		{
			if (scan.hasNextDouble()) 
			{
				num = scan.next();
				state.pushNumber(num);
			} else 
			{
				try 
				{
					state.pushOperator(scan.next());
				} catch (InputOrderException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OperationNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println(state.getValue());
		}
		scan.close();
	}

}
