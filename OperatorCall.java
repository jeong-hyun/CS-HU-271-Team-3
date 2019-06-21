
public class OperatorCall {
	public static enum CallType {
		NUMBER,
		BINARY_OPERATOR,
		UNARY_OPERATOR
	}
	
	private final CallType CALL_TYPE;
	
	private final BinaryOperator BINARY_OPERATOR;
	private final UnaryOperator UNARY_OPERATOR;
	private final double NUMBER;
	
	private double[] inputs;
	private int remainingInputs;
	
	private double value;
	private boolean hasValue;
	
	public OperatorCall(UnaryOperator operator) {
		
	}
	private OperatorCall(CallType callType, BinaryOperator binaryOperator, UnaryOperator unaryOperator, double number) {
		CALL_TYPE = callType;
		BINARY_OPERATOR = binaryOperator;
		UNARY_OPERATOR = unaryOperator;
		
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
	
	public CallType getCALL_TYPE() {
		return CALL_TYPE;
	}
	
	public void initInputs(int expectedNumberOfInputs) {
		if (expectedNumberOfInputs < 0) {
			throw new RuntimeException("operator or number cannot have a negative number of inputs");
		}
		
		
	}
	
	public void addInput(double input) {
		//TODO
	}
	
	private void initValue() {
		value = Double.NaN;
		hasValue = false;
	}
	private void setValue(double value) {
		if (hasValue) {
			throw new RuntimeException("Cannot overwrite value");
		}
		
		this.value = value;
	}
	private double getValue() {
		if (!hasValue) {
			throw new RuntimeException("value hasn't been set yet");
		}
		
		return value;
	}
}
