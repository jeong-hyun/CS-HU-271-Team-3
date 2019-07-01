import java.util.Stack;


/**
 * ... this class has 50 methods ...
 * 
 * each of the methods in this class generally follow this pattern:
 * [ST|[[LF][LN][LU|LB][MN][RU|RB][RN][RF][LR|RL][TL|TR]]
 * 
 * ST means start
 * LF means left stack full
 * LN means left number
 * LU means left unary operator
 * LB means left binary operator
 * MN means middle number
 * RB means right binary operator
 * RN means right number
 * RF means right stack full
 * LR means left operator before right operator
 * RL means right operator before left operator
 * TL means test left stack next
 * TR means test right stack next
 * 
 * @author Samuel Lieberman
 *
 */
public class Popper {
	private Stack<OperatorCall> leftStack;
	private Stack<OperatorCall> rightStack;
	
	private double leftNumber;
	private OperatorCall leftOperator;
	private double middleNumber;
	private OperatorCall rightOperator;
	private double rightNumber;
	
	private double solution;
	private boolean startedSolving;
	private boolean solved;
	
	public Popper(Stack<OperatorCall> leftStack) {
		this.leftStack = leftStack;
		rightStack = new Stack<OperatorCall>();
		
		startedSolving = false;
		solved = false;
	}
	
	public void solve() {
		if (startedSolving) {
			throw new RuntimeException("cannot solve in the middle of solving");
		}
		if (solved) {
			throw new RuntimeException("already solved");
		}
		
		ST();
	}
	public double getSolution() {
		if (startedSolving) {
			throw new RuntimeException("cannot getSolution in the middle of solving");
		}
		if (!solved) {
			throw new RuntimeException("cannot solve until already solved");
		}
		
		return solution;
	}
	
	private void ST() {
		startedSolving = true;
		
		if (leftStack.isEmpty()) {
			rightNumber = 0;
			
			RN();
		}else {
			LF();
		}
	}
	private void RN() {
		solution = rightNumber;
		
		startedSolving = false;
		solved = true;
		
//		Thread.dumpStack();
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	private void LF() {
		rightNumber = leftStack.pop().getNumber();
		
		if (leftStack.isEmpty()) {
			RN();
		}else {
			LFRN();
		}
	}
	private void LFRN() {
		rightOperator = leftStack.pop();
		
		switch(rightOperator.getCallType()) {
		case BINARY_OPERATOR:
			LFRBRN();
			break;
		case UNARY_OPERATOR:
			if (leftStack.isEmpty()) {
				RURN();
			}else {
				LFRURN();
			}
			break;
		case NUMBER:
		default:
			throw new RuntimeException("invalid call type");
		}
	}
	private void LFRBRN() {
		middleNumber = leftStack.pop().getNumber();
		
		if (leftStack.isEmpty()) {
			MNRBRN();
		}else {
			LFMNRBRN();
		}
	}
	private void RURN() {
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		
		RN();
	}
	private void LFRURN() {
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		
		LFRN();
	}
	private void MNRBRN() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		
		RN();
	}
	private void LFMNRBRN() {
		leftOperator = leftStack.pop();
		
		switch(leftOperator.getCallType()) {
		case BINARY_OPERATOR:
			LFLBMNRBRN();
			break;
		case UNARY_OPERATOR:
			if (leftStack.isEmpty()) {
				LUMNRBRN();
			}else {
				LFLUMNRBRN();
			}
			break;
		case NUMBER:
		default:
			throw new RuntimeException("invalid call type");
		}
	}
	private void LFLBMNRBRN() {
		leftNumber = leftStack.pop().getNumber();
		
		if (leftStack.isEmpty()) {
			LNLBMNRBRN();
		}else {
			LFLNLBMNRBRN();
		}
	}
	private void LUMNRBRN() {
		if (leftOperator.getParenLayers() < rightOperator.getParenLayers()) {
			LUMNRBRNRL();
		}else {
			LUMNRBRNLR();
		}
	}
	private void LFLUMNRBRN() {
		if (leftOperator.getParenLayers() < rightOperator.getParenLayers()) {
			LFLUMNRBRNRL();
		}else {
			LFLUMNRBRNLR();
		}
	}
	private void LNLBMNRBRN() {
		if (leftOperator.compareTo(rightOperator) < 0) {
			LNLBMNRBRNLR();
		}else {
			LNLBMNRBRNRL();
		}
	}
	private void LFLNLBMNRBRN() {
		if (leftOperator.compareTo(rightOperator) < 0) {
			LFLNLBMNRBRNLR();
		}else {
			LFLNLBMNRBRNRL();
		}
	}
	private void LUMNRBRNLR() {
		leftOperator.addInput(middleNumber);
		middleNumber = leftOperator.getValue();
		
		MNRBRN();
	}
	private void LUMNRBRNRL() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		rightOperator = leftOperator;
		leftOperator = null;
		
		RURN();
	}
	private void LFLUMNRBRNLR() {
		leftOperator.addInput(middleNumber);
		
		middleNumber = leftOperator.getValue();
		leftOperator = null;
		
		LFMNRBRN();
	}
	private void LFLUMNRBRNRL() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		rightOperator = leftOperator;
		middleNumber = Double.NaN;
		leftOperator = null;
		
		LFRURN();
	}
	private void LNLBMNRBRNLR() {
		leftOperator.addInput(leftNumber);
		leftOperator.addInput(middleNumber);
		
		middleNumber = leftOperator.getValue();
		
		MNRBRN();
	}
	private void LNLBMNRBRNRL() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		rightOperator = leftOperator;
		middleNumber = leftNumber;
		leftOperator = null;
		leftNumber = Double.NaN;
		
		MNRBRN();
	}
	private void LFLNLBMNRBRNLR() {
		rightStack.push(new OperatorCall(-1, rightNumber));
		rightStack.push(rightOperator);
		rightNumber = middleNumber;
		rightOperator = leftOperator;
		middleNumber = leftNumber;
		leftOperator = null;
		leftNumber = Double.NaN;
		
		LFMNRBRNRFTL();
	}
	private void LFLNLBMNRBRNRL() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		rightOperator = leftOperator;
		middleNumber = leftNumber;
		leftOperator = null;
		leftNumber = Double.NaN;
		
		LFMNRBRN();
	}
	private void LFMNRBRNRFTL() {
		leftOperator = leftStack.pop();
		
		switch(leftOperator.getCallType()) {
		case BINARY_OPERATOR:
			LFLBMNRBRNRF();
			break;
		case UNARY_OPERATOR:
			if (leftStack.isEmpty()) {
				LUMNRBRNRF();
			}else {
				LFLUMNRBRNRF();
			}
			break;
		case NUMBER:
		default:
			throw new RuntimeException("invalid call type");
		}
	}
	private void LFLBMNRBRNRF() {
		leftNumber = leftStack.pop().getNumber();
		
		if (leftStack.isEmpty()) {
			LNLBMNRBRNRF();
		}else {
			LFLNLBMNRBRNRF();
		}
	}
	private void LUMNRBRNRF() {
		if (leftOperator.getParenLayers() < rightOperator.getParenLayers()) {
			LUMNRBRNRFRL();
		}else {
			LUMNRBRNRFLR();
		}
	}
	private void LFLUMNRBRNRF() {
		if (leftOperator.getParenLayers() < rightOperator.getParenLayers()) {
			LFLUMNRBRNRFRL();
		}else {
			LFLUMNRBRNRFLR();
		}
	}
	private void RURNRF() {
		leftOperator = rightOperator;
		middleNumber = rightNumber;
		rightOperator = rightStack.pop();
		rightNumber = rightStack.pop().getNumber();
		
		if (rightStack.isEmpty()) {
			LUMNRBRN();
		}else {
			LUMNRBRNRF();
		}
	}
	private void LFRURNRF() {
		leftOperator = rightOperator;
		middleNumber = rightNumber;
		rightOperator = rightStack.pop();
		rightNumber = rightStack.pop().getValue();
		
		if (rightStack.isEmpty()) {
			LFLUMNRBRN();
		}else {
			LFLUMNRBRNRF();
		}
	}
	private void LNLBMNRBRNRF() {
		if (leftOperator.compareTo(rightOperator) < 0) {
			LNLBMNRBRNRFLR();
		}else {
			LNLBMNRBRNRFRL();
		}
	}
	private void LFLNLBMNRBRNRF() {
		if (leftOperator.compareTo(rightOperator) < 0) {
			LFLNLBMNRBRNRFLR();//LONGEST?
		}else {
			LFLNLBMNRBRNRFRL();
		}
	}
	private void LUMNRBRNRFRL() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		rightOperator = leftOperator;
		middleNumber = Double.NaN;
		leftOperator = null;
		
		RURNRF();
	}
	private void LUMNRBRNRFLR() {
		leftOperator.addInput(middleNumber);
		
		middleNumber = leftOperator.getValue();
		leftOperator = null;
		
		MNRBRNRF();
	}
	private void LFLUMNRBRNRFRL() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		rightOperator = leftOperator;
		middleNumber = Double.NaN;
		leftOperator = null;
		
		LFRURNRF();
	}
	private void LFLUMNRBRNRFLR() {
		leftOperator.addInput(middleNumber);
		
		middleNumber = leftOperator.getValue();
		leftOperator = null;
		
		LFMNRBRNRFTL();//TODO test left?
	}
	private void LNLBMNRBRNRFLR() {
		leftOperator.addInput(leftNumber);
		leftOperator.addInput(middleNumber);
		
		middleNumber = leftOperator.getValue();
		leftOperator = null;
		leftNumber = Double.NaN;
		
		MNRBRNRF();
	}
	private void LNLBMNRBRNRFRL() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		rightOperator = leftOperator;
		middleNumber = leftNumber;
		leftOperator = null;
		leftNumber = Double.NaN;
		
		MNRBRNRF();
	}
	private void LFLNLBMNRBRNRFLR() {
		rightStack.push(new OperatorCall(-1, rightNumber));
		rightStack.push(rightOperator);
		rightNumber = middleNumber;
		rightOperator = leftOperator;
		middleNumber = leftNumber;
		leftOperator = null;
		leftNumber = Double.NaN;
		
		LFMNRBRNRFTL();//TODO test left?
	}
	private void LFLNLBMNRBRNRFRL() {
		rightOperator.addInput(middleNumber);
		rightOperator.addInput(rightNumber);
		
		rightNumber = rightOperator.getValue();
		rightOperator = leftOperator;
		middleNumber = leftNumber;
		leftOperator = null;
		leftNumber = Double.NaN;
		
		LFMNRBRNRF();
	}
	private void MNRBRNRF() {
		leftNumber = middleNumber;
		leftOperator = rightOperator;
		middleNumber = rightNumber;
		rightOperator = rightStack.pop();
		rightNumber = rightStack.pop().getNumber();
		
		if (rightStack.isEmpty()) {
			LNLBMNRBRN();
		}else {
			LNLBMNRBRNRF();
		}
	}
	private void LFMNRBRNRF() {
		leftNumber = middleNumber;
		leftOperator = rightOperator;
		middleNumber = rightNumber;
		rightOperator = rightStack.pop();
		rightNumber = rightStack.pop().getValue();
		
		if (rightStack.isEmpty()) {
			LFLNLBMNRBRN();
		}else {
			LFLNLBMNRBRNRF();
		}
	}
}
