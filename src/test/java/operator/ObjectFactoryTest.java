package operator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link ObjectFactory}.
 *
 * @author yuke (Yuke Liew)
 */
@RunWith(JUnit4.class)
public class ObjectFactoryTest {

    @Test
    public void testGetOperator() throws UnsupportedOperatorException {
    	assertEquals(AddOperator.class, OperatorFactory.getOperator(AddOperator.OPERATOR).getClass());
    	assertEquals(SubtractOperator.class, OperatorFactory.getOperator(SubtractOperator.OPERATOR).getClass());
    	assertEquals(MultiplyOperator.class, OperatorFactory.getOperator(MultiplyOperator.OPERATOR).getClass());
    	assertEquals(DivideOperator.class, OperatorFactory.getOperator(DivideOperator.OPERATOR).getClass());
    	assertEquals(SquareRootOperator.class, OperatorFactory.getOperator(SquareRootOperator.OPERATOR).getClass());
    	assertEquals(ClearOperator.class, OperatorFactory.getOperator(ClearOperator.OPERATOR).getClass());
    	assertEquals(UndoOperator.class, OperatorFactory.getOperator(UndoOperator.OPERATOR).getClass());
    }

	@Test(expected=UnsupportedOperatorException.class)
    public void testClearEmptyModel() throws UnsupportedOperatorException {
    	OperatorFactory.getOperator("redo");
    }
}
