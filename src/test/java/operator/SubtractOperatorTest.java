package operator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link SubtractOperator}.
 *
 * @author yuke (Yuke Liew)
 */
@RunWith(JUnit4.class)
public class SubtractOperatorTest extends SubtractOperator {

    @Test(expected=InsufficientParametersException.class)
    public void thisInsufficientParameters() throws InsufficientParametersException {
    	evaluate(BigDecimal.ONE);
    }

    @Test
    public void testEvaluate() throws InsufficientParametersException {
    	assertEquals(new BigDecimal("-0.03"), evaluate(new BigDecimal("1.03"), new BigDecimal("1.06")));
    	assertEquals(new BigDecimal("2.09"), evaluate(new BigDecimal("1.03"), new BigDecimal("-1.06")));
    	assertEquals(new BigDecimal("-2.09"), evaluate(new BigDecimal("-1.03"), new BigDecimal("1.06")));
    	assertEquals(new BigDecimal("0.03"), evaluate(new BigDecimal("-1.03"), new BigDecimal("-1.06")));
    }

    @Test
    public void testPrecisionAndRounding() throws InsufficientParametersException {
    	assertEquals(new BigDecimal("1.0001000001"),
    			evaluate(new BigDecimal("1.00001000001"), new BigDecimal("-0.00009000009")));
    	assertEquals(new BigDecimal("1.0001000001"),
    			evaluate(new BigDecimal("1.0000100000100001"), new BigDecimal("-0.00009000009000009")));
    }
}
