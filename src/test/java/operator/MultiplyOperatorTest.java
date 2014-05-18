package operator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link MultiplyOperator}.
 *
 * @author yuke (Yuke Liew)
 */
@RunWith(JUnit4.class)
public class MultiplyOperatorTest extends MultiplyOperator {

    @Test(expected=InsufficientParametersException.class)
    public void thisInsufficientParameters() throws InsufficientParametersException {
    	evaluate(BigDecimal.ONE);
    }

    @Test
    public void testEvaluate() throws InsufficientParametersException {
    	assertEquals(new BigDecimal("1.0918"), evaluate(new BigDecimal("1.03"), new BigDecimal("1.06")));
    	assertEquals(new BigDecimal("-1.0918"), evaluate(new BigDecimal("1.03"), new BigDecimal("-1.06")));
    	assertEquals(new BigDecimal("-1.0918"), evaluate(new BigDecimal("-1.03"), new BigDecimal("1.06")));
    	assertEquals(new BigDecimal("1.0918"), evaluate(new BigDecimal("-1.03"), new BigDecimal("-1.06")));
    }

    @Test
    public void testPrecisionAndRounding() throws InsufficientParametersException {
    	assertEquals(new BigDecimal("1.092745638190725"),
    			evaluate(new BigDecimal("1.0303030303"), new BigDecimal("1.0606060606")));
    }
}
