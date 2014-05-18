package operator;

import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
	
	private static Map<String, IOperator> operators = new HashMap<String, IOperator>();
	static {
		operators.put(AddOperator.OPERATOR, new AddOperator());
		operators.put(SubtractOperator.OPERATOR, new SubtractOperator());
		operators.put(MultiplyOperator.OPERATOR, new MultiplyOperator());
		operators.put(DivideOperator.OPERATOR, new DivideOperator());
		operators.put(SquareRootOperator.OPERATOR, new SquareRootOperator());
		operators.put(ClearOperator.OPERATOR, new ClearOperator());
		operators.put(UndoOperator.OPERATOR, new UndoOperator());
		//operators.put(ExitOperator.OPERATOR, new ExitOperator());
	}

	public static IOperator getOperator(String operator) throws UnsupportedOperatorException {
		if (!operators.containsKey(operator)) {
			throw new UnsupportedOperatorException("Unsupported operator: " + operator);
		}
		return operators.get(operator);
	}
}
