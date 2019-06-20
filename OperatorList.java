/**
 * Unistantiable class (meant to only be referenced staticly, like Math) with a list of every operator.
 * 
 * @author Samuel Lieberman
 *
 */
public class OperatorList {
	/**
	 * add instances of any new BinaryOperators to this class
	 * Example: new Add(),
	 */
	private static final BinaryOperator[] ALL_BINARY_OPERATORS = new BinaryOperator[] {
			
	};
	/**
	 * add instances of any new UnaryOperators to this class
	 * Example: new Sqrt(),
	 */
	private static final BinaryOperator[] ALL_UNARY_OPERATORS = new BinaryOperator[] {
			
	};
	
	/**
	 * List of possible operation types
	 */
	public static enum OperatorType{
		BINARY,
		UNARY
	}
	/**
	 * List of locations for symbols Eg. prefix, interfix, suffix
	 * "+" is BETWEEN in "1 + 2"
	 * "sin" is BEFORE in "sin x"
	 */
	public static enum SymbolLocation{
		BEFORE,
		BETWEEN,
		AFTER
	}
	
	/**
	 * Unistantiable class (meant to only be referenced staticly, like Math)
	 */
	private OperatorList() {}
	
	/**
	 * Returns a BinaryOperator matching the inputed symbol. EX "+" returns an instance of Addition.
	 * 
	 * @param symbol the between symbol for the operator
	 * @return the first match
	 * @throws OperationNotFoundException If no matches are found.
	 */
	public static BinaryOperator getBinaryOperator(String symbol) throws OperationNotFoundException {
		return (BinaryOperator) getOperator(OperatorType.BINARY, SymbolLocation.BETWEEN, symbol);
	}
	/**
	 * Returns a BinaryOperator matching the inputed symbol. EX "sin" returns an instance of the Sin class
	 * 
	 * @param symbol the between symbol for the operator
	 * @return the first match
	 * @throws OperationNotFoundException If no matches are found.
	 */
	public static UnaryOperator getUnaryOperator(String symbol) throws OperationNotFoundException {
		return (UnaryOperator) getOperator(OperatorType.BINARY, SymbolLocation.BEFORE, symbol);
	}
	private static Operator getOperator(OperatorType operatorType, SymbolLocation symbolLocation, String symbol) throws OperationNotFoundException {
		Operator[] relevantOperators;
		switch (operatorType) {
		case BINARY:
			relevantOperators = ALL_BINARY_OPERATORS;
			break;
		case UNARY:
			relevantOperators = ALL_UNARY_OPERATORS;
			break;
		default:
			throw new RuntimeException("Unknown OperatorType");
		}
		
		Operator match = null;
		boolean matchFound = false;
		for (int i = 0; !matchFound && i < relevantOperators.length; i++) {
			switch(symbolLocation) {
			case AFTER:
				if (relevantOperators[i].afterSymbol().equals(symbol)) {
					match = relevantOperators[i];
					matchFound = true;
				}
				break;
			case BEFORE:
				if (relevantOperators[i].beforeSymbol().equals(symbol)) {
					match = relevantOperators[i];
					matchFound = true;
				}
				break;
			case BETWEEN:
				if (relevantOperators[i].betweenSymbol().equals(symbol)) {
					match = relevantOperators[i];
					matchFound = true;
				}
				break;
			}
			
			i++;
		}
		
		if (!matchFound) {
			throw new OperationNotFoundException(symbol);
		}
		
		return match;
	}
}
