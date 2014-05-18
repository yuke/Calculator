package operator;

import model.CalculatorModel;

public class ExitOperator implements IOperator {

	public static final String OPERATOR = "exit";

	public void operate(CalculatorModel model) throws InsufficientParametersException {
		System.out.println(model.printStack());
		System.exit(0);
	}
}
