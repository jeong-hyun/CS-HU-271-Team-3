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
	
	private String digits;
	private int decimalFromTheLeft;
	private boolean hasDecimal;
	private boolean negative;
	
	public SimpleCalcState() {
		initDigits();
		initStack();
		operatorStack.push(new OperatorCall(0, 0));
	}
	
	private void initDigits() {
		digits = "";
		decimalFromTheLeft = -1;
		hasDecimal = false;
		negative = false;
	}
	
	private void initStack() {
		operatorStack = new Stack<OperatorCall>();
		parenLayers = 0;
	}
	
	private void evaluate() {
		Popper evaluator = new Popper(operatorStack);
		evaluator.solve();
		initStack();
		pushNumber("" + evaluator.getSolution());
	}
	
	@Override
	public void pushDigit(String digit) throws InputOrderException, UnsupportedOperationException {
		if (digit.matches("[0-9]")) {
			digits += digit;
		}else if (digit.equals("-")) {
			negative = !negative;
		}else if (digit.equals(".")) {
			if (hasDecimal) {
				throw new InputOrderException("add decimal point", "can't have two decimal points in the same number");
			}
			
			decimalFromTheLeft = digits.length();
			hasDecimal = true;
		}
		
		String number = "";
		
		if (negative) {
			number += "-";
		}
		
		if (digits.isEmpty()) {
			number += "0";
		}else if (hasDecimal) {
			number += digits.substring(0, decimalFromTheLeft);
			number += ".";
			number += digits.substring(decimalFromTheLeft);
		}else {
			number += digits;
		}
		
		pushNumber(number);
		System.out.println("number: " + number);
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
	}
	
	@Override
	public void pushControl(String control) throws InputOrderException, UnsupportedOperationException {
		//overwrites currently parsed digits
		initDigits();
		
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
		case "C":
			initDigits();
			if (!operatorStack.isEmpty()) {
				operatorStack.pop();
			}
		default:
			throw new RuntimeException("control character " + control + " not recognized, only ( and ) are valid control characters");
		}
	}
	
	@Override
	public void pushOperator(String operator) throws InputOrderException, OperationNotFoundException {
		//overwrites currently parsed digits
		initDigits();
		
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
		if (operatorStack.isEmpty()) {
			return 0;
		}
		
		//searches for the first number on the stack
		int i = operatorStack.size()-1;
		while (i >= 0 && !operatorStack.get(i).getCallType().equals(OperatorType.NUMBER)) {
			i--;
		}
		//if it goes all the way down the stack without finding anything.
		if (i < 0) {
			return 0;
		}
		//return the value of the number that was found.
		return operatorStack.get(i).getNumber();
	}
	
	@Override
	public String getExpression() throws UnsupportedOperationException {
		String expression = "";
		
		for (int i = 0; i < operatorStack.size(); i++) {
			OperatorCall thisCall = operatorStack.get(i);
			
			if (i == 0) {
				for (int j = 0; j < thisCall.getParenLayers(); j++) {
					expression += "(";
				}
			}else {
				expression += " ";
				
				OperatorCall lastCall = operatorStack.get(i-1);
				
				int newParens = thisCall.getParenLayers() - lastCall.getParenLayers();
				
				for (int j = 0; j < newParens; j++) {
					expression += "(";
				}
			}
			
			expression += thisCall;
			
			if (i == operatorStack.size()-1) {
				for (int j = 0; j < thisCall.getParenLayers() - parenLayers; j++) {
					expression += ")";
				}
			}else {
				OperatorCall nextCall = operatorStack.get(i+1);
				
				int lostParens = thisCall.getParenLayers() - nextCall.getParenLayers();
				
				for (int j = 0; j < lostParens; j++) {
					expression += ")";
				}
				
				expression += " ";
			}
		}
		
		return expression;
	}
}