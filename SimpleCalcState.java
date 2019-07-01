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
	
	private int parenLayers;
	private double topValue;
	
	public SimpleCalcState() {
		initStack();
		operatorStack.push(new OperatorCall(0, 0));
	}
	
	private void initStack() {
		operatorStack = new Stack<OperatorCall>();
		parenLayers = 0;
		topValue = 0;
	}
	
	private void evaluate() {
		Popper evaluator = new Popper(operatorStack);
		evaluator.solve();
		initStack();
		pushNumber("" + evaluator.getSolution());
	}
	
	@Override
	public void pushDigit(String digit) throws InputOrderException, UnsupportedOperationException {
		throw new UnsupportedOperationException("entering numbers one digit at a time is not supported");
	}
	
	@Override
	public void pushNumber(String number) throws InputOrderException, NumberFormatException {
		double input = Double.parseDouble(number);
		
		if (!operatorStack.isEmpty()) {
			OperatorCall topCall = operatorStack.peek();
			
			if (topCall.getCallType().equals(OperatorType.NUMBER)) {
				//overwrites the previous number
				operatorStack.pop();
			}
		}
		
		operatorStack.push(new OperatorCall(parenLayers, input));
		topValue = input;
	}
	
	@Override
	public void pushControl(String control) throws InputOrderException, UnsupportedOperationException {
		switch(control) {
		case "(":
			parenLayers++;
			break;
		case ")":
			if (parenLayers <= 0) {
				throw new InputOrderException("add an end parenthese", "there is no beginning parenthese to match it");
			}
			
			parenLayers--;
			break;
		case "=":
			if (parenLayers != 0) {
				throw new InputOrderException("evaluate", "unmatched parenthese");
			}
			if (!operatorStack.peek().getCallType().equals(OperatorType.NUMBER)) {
				throw new InputOrderException("evaluate", "no number at the end");
			}
			
			evaluate();
			break;
		default:
			throw new RuntimeException("control character " + control + " not recognized, only ( and ) are valid control characters");
		}
	}
	
	@Override
	public void pushOperator(String operator) throws InputOrderException, OperationNotFoundException {
		boolean operatorFound = false;
		
		if (!operatorFound) {//UNARY OPERATOR
			UnaryOperator unaryResult = OperatorList.getUnaryOperator(operator);
			if (unaryResult != null) {
				if (!operatorStack.isEmpty()) {
					OperatorCall topCall = operatorStack.peek();
					
					if (topCall.getCallType().equals(OperatorType.NUMBER)) {
						//overwrites the previous number
						operatorStack.pop();
					}
				}
				
				operatorStack.push(new OperatorCall(parenLayers, unaryResult));
				pushControl("(");
				operatorFound = true;
			}
		}//END UNARY
		if (!operatorFound) {//BINARY OPERATOR
			BinaryOperator binaryResult = OperatorList.getBinaryOperator(operator);
			if (binaryResult != null) {
				operatorStack.push(new OperatorCall(parenLayers, binaryResult));
				operatorFound = true;
			}
		}//END BINARY OPERATOR
		
		if (!operatorFound) {
			throw new OperationNotFoundException(operator);
		}
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