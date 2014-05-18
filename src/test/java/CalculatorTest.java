import static org.junit.Assert.assertEquals;

import operator.InsufficientParametersException;
import operator.UnsupportedOperatorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link Calculator}.
 *
 * @author yuke (Yuke Liew)
 */
@RunWith(JUnit4.class)
public class CalculatorTest extends Calculator {

	@Test(expected=InsufficientParametersException.class)
	public void testInsufficientParametersException()
			throws InsufficientParametersException, UnsupportedOperatorException {
		processLine("1 2 3 * 5 + *");
		assertEquals("stack: 11", model.printStack());
		processInput("*");
	}

	@Test
	public void testInvalidOperationException() {
		processLine("60 0 / 4 5");
		assertEquals("stack: 60 0", model.printStack());
		processLine("-1 sqrt");
		assertEquals("stack: 60 0 -1", model.printStack());
	}

	@Test
	public void testUnsupportedOperatorException() {
		processLine("1 2 3 redo 4 5");
		assertEquals("stack: 1 2 3", model.printStack());
	}

	@Test
	public void testCase1() {
		processLine("5 2");
		assertEquals("stack: 5 2", model.printStack());
	}

	@Test
	public void testCase2() {
		processLine("2 sqrt");
		assertEquals("stack: 1.4142135624", model.printStack());
		processLine("clear 9 sqrt");
		assertEquals("stack: 3", model.printStack());
	}

	@Test
	public void testCase3() {
		processLine("5 2 -");
		assertEquals("stack: 3", model.printStack());
		processLine("3 -");
		assertEquals("stack: 0", model.printStack());
		processLine("clear");
		assertEquals("stack:", model.printStack());
	}

	@Test
	public void testCase4() {
		processLine("5 4 3 2");
		assertEquals("stack: 5 4 3 2", model.printStack());
		processLine("undo undo *");
		assertEquals("stack: 20", model.printStack());
		processLine("5 *");
		assertEquals("stack: 100", model.printStack());
		processLine("undo");
		assertEquals("stack: 20 5", model.printStack());
	}

	@Test
	public void testCase5() {
		processLine("7 12 2 /");
		assertEquals("stack: 7 6", model.printStack());
		processLine("*");
		assertEquals("stack: 42", model.printStack());
		processLine("4 /");
		assertEquals("stack: 10.5", model.printStack());
	}

	@Test
	public void testCase6() {
		processLine("1 2 3 4 5");
		assertEquals("stack: 1 2 3 4 5", model.printStack());
		processLine("*");
		assertEquals("stack: 1 2 3 20", model.printStack());
		processLine("clear 3 4 -");
		assertEquals("stack: -1", model.printStack());
	}

	@Test
	public void testCase7() {
		processLine("1 2 3 4 5");
		assertEquals("stack: 1 2 3 4 5", model.printStack());
		processLine("* * * *");
		assertEquals("stack: 120", model.printStack());
	}

	@Test
	public void testCase8() {
		processLine("1 2 3 * 5 + * * 6 5");
		assertEquals("stack: 11", model.printStack());
	}
}
