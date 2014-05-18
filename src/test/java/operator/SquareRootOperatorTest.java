package operator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link SquareRootOperator}.
 *
 * @author yuke (Yuke Liew)
 */
@RunWith(JUnit4.class)
public class SquareRootOperatorTest extends SquareRootOperator {

    @Test(expected=InsufficientParametersException.class)
    public void thisInsufficientParameters() throws InsufficientParametersException {
    	evaluate();
    }

    @Test
    public void testEvaluate() throws InsufficientParametersException {
    	assertEquals(new BigDecimal("1.03"), evaluate(new BigDecimal("1.0609")));
    }

    @Test(expected=InvalidOperationException.class)
    public void testSquareRootOfNegativeNumber() throws InsufficientParametersException {
    	evaluate(new BigDecimal("-1.0609"));
    }

    @Test
    public void testPrecisionAndRounding() throws InsufficientParametersException {
    	assertEquals(new BigDecimal("1.045344746096103"), evaluate(new BigDecimal("1.092745638190725")));
    	assertEquals(new BigDecimal("1.045344746096103"), evaluate(new BigDecimal("1.0927456381907251")));
    	assertEquals(new BigDecimal("1.045344746096103"), evaluate(new BigDecimal("1.0927456381907258")));
    }
}
