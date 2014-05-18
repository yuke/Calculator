package operator;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Stack;

import model.CalculatorModel;

public abstract class EvaluativeOperator implements IOperator {
	
	private static final int DEFAULT_NUM_OF_OPERANDS = 2;
	
	protected BigDecimal getOperand(Stack<BigDecimal> stack)
			throws InsufficientParametersException {
		try {
			return stack.pop();
		} catch(EmptyStackException e) {
			throw new InsufficientParametersException(e);
		}
	}
	
	protected BigDecimal[] getOperands(Stack<BigDecimal> stack, int numOfOperands)
			throws InsufficientParametersException {
		BigDecimal[] operands = new BigDecimal[numOfOperands];
		for (int i = numOfOperands - 1; i >= 0 ; i--) {
			try {
				operands[i] = getOperand(stack);
			} catch(InsufficientParametersException e) {
				for (i = i + 1; i < numOfOperands; i++)
				stack.push(operands[i]);
				throw e;
			}
		}
		return operands;
	}
	
	public void operate(CalculatorModel model) throws InsufficientParametersException {
		Stack<BigDecimal> outStack = model.getStack();
		BigDecimal[] operands = getOperands(outStack, getNumOfOperands());
		try {
			BigDecimal result = evaluate(operands);
			outStack.push(result);
		} catch(InvalidOperationException e) {
			for (BigDecimal operand : operands) {
				outStack.push(operand);
			}
			throw e;
		}
	}

	protected int getNumOfOperands() {
		return DEFAULT_NUM_OF_OPERANDS;
	}

    /**
     * Validate whether there are sufficient number of operands to be evaluated.
     *
     * @param operands array of operands to be validated
     * @throws InsufficientParametersException when insufficient operand(s) is given
     */
	protected void validate(BigDecimal... operands) throws InsufficientParametersException {
		if (operands.length != getNumOfOperands()) {
			throw new InsufficientParametersException(
					getClass().getName() + " requires " + getNumOfOperands() + " operands");
		}
	}

    /**
     * Evaluate the operands and return the result.
     *
     * @param operands array of operands to be evaluated
     * @return the result of the evaluation
     */
	abstract protected BigDecimal evaluate(BigDecimal... operands)
			throws InsufficientParametersException;
}
