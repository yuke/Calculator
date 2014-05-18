package operator;

import model.CalculatorModel;

public class ClearOperator implements IOperator {

	public static final String OPERATOR = "clear";

	public void operate(CalculatorModel model) throws InsufficientParametersException {
		model.getInputs().clear();
		model.getStack().clear();
	}
}
