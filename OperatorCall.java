import java.util.Comparator;

public class OperatorCall implements Comparable<OperatorCall>{
	private final int PAREN_LAYERS;
	
	private final OperatorType CALL_TYPE;
	
	private final BinaryOperator BINARY_OPERATOR;
	private final UnaryOperator UNARY_OPERATOR;
	private final double NUMBER;
	
	private double[] inputs;
	private int inputsDecided;
	private int remainingInputs;
	
	private double value;
	private boolean hasValue;
	
	public OperatorCall(int parenLayers, BinaryOperator operator) {
		this(parenLayers, OperatorType.BINARY_OPERATOR, operator, null, Double.NaN);
	}
	public OperatorCall(int parenLayers, UnaryOperator operator) {
		this(parenLayers, OperatorType.UNARY_OPERATOR, null, operator, Double.NaN);
	}
	public OperatorCall(int parenLayers, double number) {
		this(parenLayers, OperatorType.NUMBER, null, null, number);
	}
	private OperatorCall(int parenLayers, OperatorType operatorType, BinaryOperator binaryOperator, UnaryOperator unaryOperator, double number) {
		PAREN_LAYERS = parenLayers;
		
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
	
	public int getParenLayers() {
		return PAREN_LAYERS;
	}
	public OrderOfOperations getOrder() {
		switch (CALL_TYPE) {
		case BINARY_OPERATOR:
			return BINARY_OPERATOR.stage();
		case UNARY_OPERATOR:
			return UNARY_OPERATOR.stage();
		case NUMBER:
			return OrderOfOperations.NUMBER_STAGE;
		default:
			throw new RuntimeException("call type " + CALL_TYPE + " not recognized");
		}
	}
	@Override//IMPORTANT NOTE: operators should be compareTo'd to operators to the right of them
	public int compareTo(OperatorCall otherOperator) {
		int layerCompare = Integer.compare(PAREN_LAYERS, otherOperator.PAREN_LAYERS);
		if (layerCompare != 0) {
			//more paren layers means this comes first, the opposite order of integers normally
			return -layerCompare;
		}
		
		OrderOfOperations thisStage = this.getOrder();
		OrderOfOperations theirStage = otherOperator.getOrder();
		Comparator<OrderOfOperations> stageComparator = OrderOfOperations.getComp();
		int stageCompare = stageComparator.compare(thisStage, theirStage);
		if (stageCompare != 0) {
			return stageCompare;
		}
		
		if (thisStage.getDirectionOfExecution().equals(OrderOfOperations.Direction.RIGHT_TO_LEFT)) {
			//this operation is left and later than right
			return 1;
		}else {
			return -1;
		}
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
		if (!CALL_TYPE.equals(OperatorType.NUMBER)) {
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
	
	@Override
	public String toString() {
		switch (CALL_TYPE) {
		case BINARY_OPERATOR:
			return BINARY_OPERATOR.betweenSymbol();
		case NUMBER:
			return ("" + NUMBER).replaceAll("()\\.0+$|(\\..+?)0+$", "$2");
		case UNARY_OPERATOR:
			return UNARY_OPERATOR.beforeSymbol();
		default:
			throw new RuntimeException("unknown OperatorType: " + CALL_TYPE);
		}
	}
}
