package operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import model.CalculatorModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link ClearOperator}.
 *
 * @author yuke (Yuke Liew)
 */
@RunWith(JUnit4.class)
public class UndoOperatorTest extends UndoOperator {

	private CalculatorModel model;

    @Before
    public void initModel() {
        model = new CalculatorModel();
    }

    @Test
    public void testUndoNumbers() throws InsufficientParametersException {
    	model.getInputs().push(BigDecimal.ZERO);
    	model.getStack().push(BigDecimal.ZERO);
    	model.getInputs().push(BigDecimal.TEN);
    	model.getStack().push(BigDecimal.TEN);

    	assertEquals(2, model.getInputs().size());
		assertEquals(2, model.getStack().size());
		operate(model);
    	assertEquals(1, model.getInputs().size());
		assertEquals(1, model.getStack().size());
		operate(model);
		assertTrue(model.getInputs().empty());
		assertTrue(model.getStack().empty());
    }

    @Test
    public void testUndoOperators() throws InsufficientParametersException, UnsupportedOperatorException {
    	model.getInputs().push(new BigDecimal("9"));
    	model.getInputs().push(new BigDecimal("9"));
    	model.getInputs().push(OperatorFactory.getOperator(MultiplyOperator.OPERATOR));
    	model.getInputs().push(OperatorFactory.getOperator(SquareRootOperator.OPERATOR));
    	model.getStack().push(new BigDecimal("9"));

    	assertEquals(4, model.getInputs().size());
		assertEquals(1, model.getStack().size());
		operate(model);
    	assertEquals(3, model.getInputs().size());
		assertEquals(1, model.getStack().size());
		assertEquals(new BigDecimal("81"), model.getStack().peek());
		operate(model);
    	assertEquals(2, model.getInputs().size());
		assertEquals(2, model.getStack().size());
		assertEquals(new BigDecimal("9"), model.getStack().peek());
    }
    
	@Test
    public void testUndoNothing() throws InsufficientParametersException {
		assertTrue(model.getInputs().empty());
		assertTrue(model.getStack().empty());
		operate(model);
		assertTrue(model.getInputs().empty());
		assertTrue(model.getStack().empty());
    }
}
