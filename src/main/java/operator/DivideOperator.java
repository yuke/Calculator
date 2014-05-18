package operator;

import static java.math.MathContext.DECIMAL64;

import java.math.BigDecimal;

public class DivideOperator extends EvaluativeOperator {
	
	public static final String OPERATOR = "/";

	@Override
	protected BigDecimal evaluate(BigDecimal... operands) throws InsufficientParametersException {
		validate(operands);
		try {
			return operands[0].divide(operands[1], DECIMAL64).stripTrailingZeros();
		} catch(ArithmeticException e) {
			throw new InvalidOperationException(e);
		}
	}
}
