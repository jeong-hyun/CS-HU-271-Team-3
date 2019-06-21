import java.util.Stack;

public class SimpleCalcState implements CalculatorState{
	private Stack<OperatorCall> operatorStack;
	
	public SimpleCalcState() {
		operatorStack = new Stack<OperatorCall>();
	}

	@Override
	public void pushDigit(String digit) throws InputOrderException, UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pushNumber(String number) throws InputOrderException, NumberFormatException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pushControl(String control) throws InputOrderException, UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pushOperator(String operator) throws InputOrderException, OperationNotFoundException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double getValue() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getExpression() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}
}
