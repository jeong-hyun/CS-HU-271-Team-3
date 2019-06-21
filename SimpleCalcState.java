import java.util.Stack;

/**
 * Keeps track of the state of a calculator. Fulfills the CalculatorTracker
 * interface. Doesn't support PEMDAS, adding digits individually, parentheses, or getting the
 * entire expression.
 * 
 * @author Samuel Lieberman
 *
 */
public class SimpleCalcState implements CalculatorState{
	private Stack<OperatorCall> operatorStack;
	
	private double topValue;
	
	public SimpleCalcState() {
		initStack();
	}
	
	private void initStack() {
		operatorStack = new Stack<OperatorCall>();
		topValue = Double.NaN;
	}
	
	@Override
	public void pushDigit(String digit) throws InputOrderException, UnsupportedOperationException {
		throw new UnsupportedOperationException("entering numbers one digit at a time is not supported");
	}
	
	@Override
	public void pushNumber(String number) throws InputOrderException, NumberFormatException {
		double input = Double.parseDouble(number);
		
		if (operatorStack.isEmpty()) {
			topValue = input;
		}else {
			OperatorCall topCall = operatorStack.peek();
			switch(topCall.getCallType()) {
			case BINARY_OPERATOR:
			case UNARY_OPERATOR:
				//evaluates as far as it can
				pushNumber(input);
				break;
			case NUMBER:
				//overwrites the previous number
				initStack();
				topValue = input;
				break;
			default:
				throw new InputOrderException("push number", "unknown operator type on stack");
			}
		}
	}
	private void pushNumber(double input) {
		if (operatorStack.isEmpty()) {
			topValue = input;
		}else {
			OperatorCall topCall = operatorStack.peek();
			
			topCall.addInput(input);
			operatorStack.pop();
			pushNumber(topCall.getValue());
		}
	}
	
	@Override
	public void pushControl(String control) throws InputOrderException, UnsupportedOperationException {
		throw new UnsupportedOperationException("parentheses are not supported");
	}
	
	@Override
	public void pushOperator(String operator) throws InputOrderException, OperationNotFoundException {
		{//UNARY OPERATOR
			UnaryOperator unaryResult = OperatorList.getUnaryOperator(operator);
			if (unaryResult != null) {
				operatorStack.push(new OperatorCall(unaryResult));
			}
		}//END UNARY
		{//BINARY OPERATOR
			BinaryOperator binaryResult = OperatorList.getBinaryOperator(operator);
			if (binaryResult != null) {
				operatorStack.push(new OperatorCall(binaryResult, topValue));
			}
		}
		
		throw new OperationNotFoundException(operator);
	}
	
	@Override
	public double getValue() throws UnsupportedOperationException {
		return topValue;
	}
	
	@Override
	public String getExpression() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("expressions not supported");
	}
}
