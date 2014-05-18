package operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import model.CalculatorModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link ClearOperator}.
 *
 * @author yuke (Yuke Liew)
 */
@RunWith(JUnit4.class)
public class ClearOperatorTest {

	private static ClearOperator operator;

	private CalculatorModel model;

    @BeforeClass
    public static void init() {
    	operator = new ClearOperator();
    }
		
    @Before
    public void initModel() {
        model = new CalculatorModel();
    }

    @Test
    public void testClear() throws InsufficientParametersException {
    	model.getInputs().push(BigDecimal.ZERO);
    	model.getStack().push(BigDecimal.ZERO);
    	model.getInputs().push(BigDecimal.TEN);
    	model.getStack().push(BigDecimal.TEN);
    	
		assertEquals(2, model.getInputs().size());
		assertEquals(2, model.getStack().size());
		operator.operate(model);
		assertTrue(model.getInputs().empty());
		assertTrue(model.getStack().empty());
    }

	@Test
    public void testClearNothing() throws InsufficientParametersException {
		assertTrue(model.getInputs().empty());
		assertTrue(model.getStack().empty());
		operator.operate(model);
		assertTrue(model.getInputs().empty());
		assertTrue(model.getStack().empty());
    }
}
