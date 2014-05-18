package operator;

import static java.math.MathContext.DECIMAL64;

import java.math.BigDecimal;

public class SquareRootOperator extends EvaluativeOperator {
	
	public static final String OPERATOR = "sqrt";
	
	@Override
	protected int getNumOfOperands() {
		return 1;
	}

	@Override
	protected BigDecimal evaluate(BigDecimal... operands) throws InsufficientParametersException {
		validate(operands);
		try {
			return new BigDecimal(Math.sqrt(operands[0].doubleValue()), DECIMAL64).stripTrailingZeros();
		} catch(NumberFormatException e) {
			throw new InvalidOperationException(e);
		}
	}
}
