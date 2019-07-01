import java.util.Comparator;

/**
 * 
 * This enum represents a stage in PEMDAS
 * it holds a double which orders the stages
 * it holds a Direction which represents the direction that the stage is evaluated in
 * 
 * This enum really isn't going to be used much until later in this project.
 * 
 * @author Samuel Lieberman
 */
public enum OrderOfOperations{
	/**
	 * The values for this enum.
	 * use these like this:
	 * OrderOfOperations.MULTIPLICATION_AND_ADDITION
	 */
	NUMBER_STAGE(-2, Direction.NONE),
	SINGLE_INPUT(-1, Direction.NONE),
	OTHER_EARLY(0, Direction.LEFT_TO_RIGHT),
	EXPONENTIATION(1, Direction.RIGHT_TO_LEFT),
	MULTIPLICATION_AND_DIVISION(2, Direction.LEFT_TO_RIGHT),
	ADDITION_AND_SUBTRACTION(3, Direction.LEFT_TO_RIGHT),
	OTHER_LATE(4, Direction.LEFT_TO_RIGHT),
	NONE(Double.NaN, Direction.NONE),
	UNKNOWN(Double.NaN, null);
	
	/**
	 * 
	 * This enum represents a direction for evaluating operations for a particular stage.
	 * For example: addition and multiplication are evaluated left to right.
	 * 
	 * reference these values like this:
	 * OrderOfOperations.Direction.LEFT_TO_RIGHT
	 */
	public static enum Direction {
		LEFT_TO_RIGHT,
		RIGHT_TO_LEFT,
		NONE
	};
	
	private static final Comparator<OrderOfOperations> ORDER_COMPARATOR = new OrderComparator();
	
	private final double STAGE;
	private final Direction DIRECTION_OF_EXECUTION;
	
	/**
	 * The constructor for this enum.
	 * 
	 * @param stage the position in the stages for this operation to be executed.  Can be a decimal value like 1.5
	 * @param directionOfExecution The direction that this stage is supposed to be executed in.  Usually OrderOfOperations.Direction.LEFT_TO_RIGHT
	 */
	OrderOfOperations(double stage, Direction directionOfExecution) {
		STAGE = stage;
		DIRECTION_OF_EXECUTION = directionOfExecution;
	};
	
	/**
	 * A getter for the stage of the enum
	 * 
	 * @return the stage of the enum
	 */
	public double getStage() {
		return STAGE;
	}
	/**
	 * A getter for the direction of execution for the enum
	 * 
	 * @return the direction of execution for the enum
	 */
	public Direction getDirectionOfExecution() {
		return DIRECTION_OF_EXECUTION;
	}
	
	/**
	 * @return a comparator for these enums based on the operation's stage
	 */
	public static Comparator<OrderOfOperations> getComp() {
		return ORDER_COMPARATOR;
	}
	/**
	 * A private static class for comparing these enums.  Works based on the stage double in the OrderOfOperatons.
	 * Lower stage values come first.
	 */
	private static class OrderComparator implements Comparator<OrderOfOperations> {
		@Override
		public int compare(OrderOfOperations o1, OrderOfOperations o2) {
			return Double.compare(o1.STAGE, o2.STAGE);
		}
	}
}
