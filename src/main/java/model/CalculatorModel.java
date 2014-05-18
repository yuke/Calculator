package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Stack;

public class CalculatorModel implements Serializable {

	private static final long serialVersionUID = -5928743775766070108L;

	private static final DecimalFormat df = new DecimalFormat("#0.##########");

	private Stack<Object> inputs = new Stack<Object>();
	
	private Stack<BigDecimal> stack = new Stack<BigDecimal>();

	public Stack<Object> getInputs() {
		return inputs;
	}

	public Stack<BigDecimal> getStack() {
		return stack;
	}

	public String printStack() {
		StringBuffer sb = new StringBuffer();
		int size = stack.size();
		for (int i = 0; i < size; i++) {
			sb.append(" ").append(df.format(stack.get(i)));
		}
		return "stack:" + sb.toString();
	}
}
