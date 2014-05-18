package operator;

import static java.math.MathContext.DECIMAL64;

import java.math.BigDecimal;

public class MultiplyOperator extends EvaluativeOperator {
	
	public static final String OPERATOR = "*";

	@Override
	protected BigDecimal evaluate(BigDecimal... operands) throws InsufficientParametersException {
		validate(operands);
		return operands[0].multiply(operands[1], DECIMAL64).stripTrailingZeros();
	}
}
