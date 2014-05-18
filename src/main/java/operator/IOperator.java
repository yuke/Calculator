package operator;

import model.CalculatorModel;

public interface IOperator {

	void operate(CalculatorModel model) throws InsufficientParametersException;
}
