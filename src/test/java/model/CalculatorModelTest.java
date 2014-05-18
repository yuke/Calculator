package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import operator.InsufficientParametersException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link CalculatorModel}.
 *
 * @author yuke (Yuke Liew)
 */
@RunWith(JUnit4.class)
public class CalculatorModelTest {

	private CalculatorModel model;

	@Before
    public void initModel() {
        model = new CalculatorModel();
    }

    @Test
    public void testPrintStack() throws InsufficientParametersException {
    	BigDecimal number = new BigDecimal("1.0123456789012");
    	model.getInputs().push(number);
    	model.getStack().push(number);
    	
		assertEquals("stack: 1.0123456789", model.printStack());
    }
}
