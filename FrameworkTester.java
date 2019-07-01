import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("unused")
public class FrameworkTester {
	private static final String[] NUMBERS = new String[] {
			"2", "3"//, "4", "5", "10"
	};
	private static final String[] BINARY_OPERATORS = new String[] {
			"+", "-", "*", "^"
	};
	private static final String[] UNARY_OPERATORS = new String[] {
			"sin"
	};
	
	private static final int MAX_COMPLEXITY = 6;
	public static void main(String[] args) {
		for (int i = 0; i < MAX_COMPLEXITY; i++) {
			autoTestFramework(i);
		}
		manuallyTestFramework();
	}
	private static final double USE_CHANCE = 0.0001;
	private static final long SEED = 0;
	private static final Random RANDOMIZER = new Random(SEED);
	private static void autoTestFramework(int complexity) {
		String[] expressions = constructExpressions(complexity, NUMBERS);
		
		for (String expression : expressions) {
			if (RANDOMIZER.nextDouble() < USE_CHANCE) {
				testExpression(expression);
			}
		}
	}
	private static void manuallyTestFramework() {
//		PROBLEM: sin 3 ^ 3 ^ 2 ) = 2.2197155953209642E-8
//		testExpression("sin 3 ^ 3 ^ 2 )");
//		testExpression("sin 19683 )");
//		PROBLEM: sin 3 * 2 ) ^ 3 = -0.9055783620066238
//		testExpression("sin 3 * 2 ) ^ 3");
//		testExpression("sin 6 ) ^ 3");
//		PROBLEM: 3 + sin 3 ^ 3 * 2 ) = 4.912751856809006
//		testExpression("3 + sin 3 ^ 3 * 2 )");
//		PROBLEM: sin 2 ^ 3 * 3 ^ 3 - 2 ) = 24.71267265883131
//		testExpression("sin 2 ^ 3 * 3 ^ 3 - 2 )");
	}
	private static void testExpression(String expression) {
		CalculatorState state = new SimpleCalcState();
		
		Scanner scan = new Scanner(expression);
		
		while (scan.hasNext()) {
			if (scan.hasNextDouble()) {
				state.pushNumber(scan.next());
			}else {
				String next = scan.next();
				
				if (next.equals("(") || next.equals(")")) {
					state.pushControl(next);
				}else {
					try {
						state.pushOperator(next);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		scan.close();
		
		state.pushControl("=");
		
		System.out.println(expression + " = " + state.getValue());
	}
	private static String[] constructExpressions(int length, String[] base) {
		if (length == 0) {
			return base;
		}
		
		int multiply = BINARY_OPERATORS.length*NUMBERS.length*2 + 2;
		String[] dBase = multiplyExpressions(multiply, base);
		for (int i = 0; i < dBase.length; i += multiply) {
			int j = 0;
			for (int k = 0; k < BINARY_OPERATORS.length; k++) {
				for (int l = 0; l < NUMBERS.length; l++) {
					dBase[i+j] = dBase[i+j] + " " + BINARY_OPERATORS[k] + " " + NUMBERS[l];
					j++;
					dBase[i+j] = NUMBERS[l] + " " + BINARY_OPERATORS[k] + " " + dBase[i+j];
					j++;
				}
			}
			
			dBase[i+j] = UNARY_OPERATORS[0] + " " + dBase[i+j] + " )";
			j++;
			dBase[i+j] = "( " + dBase[i+j] + " )";
			j++;
		}
		
		return constructExpressions(length-1, dBase);
	}
	private static String[] multiplyExpressions(int product, String[] original) {
		String[] multiplied = new String[original.length*product];
		
		for (int i = 0; i < original.length; i++) {
			int bigI = i*product;
			for (int j = 0; j < product; j++) {
				multiplied[bigI+j] = original[i];
			}
		}
		
		return multiplied;
	}
}
