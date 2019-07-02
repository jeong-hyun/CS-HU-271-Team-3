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
	public static void main(String args[]) 
	{
		CalculatorState state = new SimpleCalcState();
		Scanner scan = new Scanner(System.in);

		while (scan.hasNext()) 
		{
			if (scan.hasNextDouble()) 
			{
				state.pushNumber(scan.next());
			} else 
			{
				String next = scan.next();
				
				if (next.matches("[()=]")) {
					state.pushControl(next);
				}else {
					try 
					{
						state.pushOperator(next);
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
			}
			
			System.out.println(state.getExpression());
		}
		
		scan.close();
	}

}
