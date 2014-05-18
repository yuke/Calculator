package operator;

import java.math.BigDecimal;
import java.util.Stack;

import model.CalculatorModel;

public class UndoOperator implements IOperator {

	public static final String OPERATOR = "undo";

	public void operate(CalculatorModel model) throws InsufficientParametersException {
		if (model.getStack().empty()) {
			return;	// Ignore. Nothing to undo.
		}
		
		model.getStack().pop();
		Object last = model.getInputs().pop();
		if (last instanceof BigDecimal) {
			// Nothing more to do
		} else if (last instanceof EvaluativeOperator) {
			EvaluativeOperator operator = (EvaluativeOperator) last;
			int redoNumOfOperands = operator.getNumOfOperands();
			// Undo the last chunk of operation(s)
			Stack<Object> redoStack = new Stack<Object>();
			for (; redoNumOfOperands > 0; redoNumOfOperands--) {
				Object input = model.getInputs().pop();
				redoStack.push(input);
				if (input instanceof EvaluativeOperator) {
					operator = (EvaluativeOperator) input;
					redoNumOfOperands += operator.getNumOfOperands();
				}
			}
			while (!redoStack.empty()) {
				Object input = redoStack.pop();
				redo(model, input);
			}
		} else {
			throw new IllegalStateException(
					"Inputs should ONLY contain numbers and evaluative operators: " + last);
		}
	}

	protected void redo(CalculatorModel model, Object input) throws InsufficientParametersException {
		if (input instanceof BigDecimal) {
			redo(model, (BigDecimal) input);
		} else if (input instanceof EvaluativeOperator) {
			redo(model, (EvaluativeOperator) input);
		} else {
			throw new IllegalStateException(
					"Inputs should ONLY contain numbers and evaluative operators: " + input);
		}
	}
	
	protected void redo(CalculatorModel model, BigDecimal number) {
		model.getStack().push(number);
		model.getInputs().push(number);
	}

	protected void redo(CalculatorModel model, EvaluativeOperator operator)
			throws InsufficientParametersException {
		operator.operate(model);
		model.getInputs().push(operator);
	}
}
