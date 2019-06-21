
public class OperatorCall {
	private final OperatorType CALL_TYPE;
	
	private final BinaryOperator BINARY_OPERATOR;
	private final UnaryOperator UNARY_OPERATOR;
	private final double NUMBER;
	
	private double[] inputs;
	private int inputsDecided;
	private int remainingInputs;
	
	private double value;
	private boolean hasValue;
	
	public OperatorCall(BinaryOperator operator, double firstInput) {
		this(OperatorType.BINARY_OPERATOR, operator, null, Double.NaN);
		
		addInput(firstInput);
	}
	public OperatorCall(UnaryOperator operator) {
		this(OperatorType.UNARY_OPERATOR, null, operator, Double.NaN);
	}
	public OperatorCall(double number) {
		this(OperatorType.NUMBER, null, null, number);
	}
	private OperatorCall(OperatorType operatorType, BinaryOperator binaryOperator, UnaryOperator unaryOperator, double number) {
		CALL_TYPE = operatorType;
		BINARY_OPERATOR = binaryOperator;
		UNARY_OPERATOR = unaryOperator;
		NUMBER = number;
		
		switch (CALL_TYPE) {
		case NUMBER:
			initInputs(0);
			break;
		case BINARY_OPERATOR:
			initInputs(2);
			break;
		case UNARY_OPERATOR:
			initInputs(1);
			break;
		}
		initValue();
	}
	
	public OperatorType getCallType() {
		return CALL_TYPE;
	}
	
	public BinaryOperator getBinaryOperator() {
		if (CALL_TYPE != OperatorType.BINARY_OPERATOR) {
			throw new RuntimeException("this is not a call to a binary operator");
		}
		
		return BINARY_OPERATOR;
	}
	public UnaryOperator getUnaryOperator() {
		if (CALL_TYPE != OperatorType.UNARY_OPERATOR) {
			throw new RuntimeException("this is not a call to a unary operator");
		}
		
		return UNARY_OPERATOR;
	}
	public double getNumber() {
		if (CALL_TYPE != OperatorType.UNARY_OPERATOR) {
			throw new RuntimeException("this is not a number");
		}
		
		return NUMBER;
	}
	
	public void initInputs(int numberOfInputs) {
		if (numberOfInputs < 0) {
			throw new RuntimeException("operator or number cannot have a negative number of inputs");
		}
		
		inputs = new double[numberOfInputs];
		inputsDecided = 0;
		remainingInputs = numberOfInputs;
	}
	public void addInput(double input) {
		if (hasAllInputs()) {
			throw new RuntimeException("All inputs set");
		}
		
		inputs[inputsDecided] = input;
		inputsDecided++;
		remainingInputs--;
	}
	public int getInputsDecided() {
		return inputsDecided;
	}
	public int getRemainingInputs() {
		return remainingInputs;
	}
	public boolean hasAllInputs() {
		return remainingInputs <= 0;
	}
	
	private void initValue() {
		value = Double.NaN;
		hasValue = false;
	}
	private void setValue() {
		if (!hasAllInputs()) {
			throw new RuntimeException("Cannot evaluate an operator without knowing all inputs");
		}
		
		switch (CALL_TYPE) {
		case NUMBER:
			value = NUMBER;
			break;
		case BINARY_OPERATOR:
			value = BINARY_OPERATOR.evaluate(inputs[0], inputs[1]);
			break;
		case UNARY_OPERATOR:
			value = UNARY_OPERATOR.evaluate(inputs[0]);
			break;
		}
	}
	public double getValue() {
		if (!hasValue) {
			setValue();
		}
		
		return value;
	}
}
