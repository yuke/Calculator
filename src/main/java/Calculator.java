import static java.math.MathContext.DECIMAL64;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import model.CalculatorModel;
import operator.EvaluativeOperator;
import operator.IOperator;
import operator.InsufficientParametersException;
import operator.InvalidOperationException;
import operator.OperatorFactory;
import operator.UnsupportedOperatorException;

/**
 * Calculator
 *
 */
public class Calculator {

	private static Logger logger = Logger.getLogger(Calculator.class.getName());
	private static ResourceBundle rb = ResourceBundle.getBundle("message");

	private static final String LOG_FILE = "./calculator.log";
	private static final String UNSUPPORTED_OPERATOR_ERROR = "unsupported_operator_error";
	private static final String INVALID_OPERATION_ERROR = "invalid_operation_error";
	private static final String INSUFFICIENT_PARAMETERS_ERROR = "insufficient_parameters_error"; 

	protected CalculatorModel model = new CalculatorModel();

	public void run() {
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		String line = scanLine(scanner);
		while (true) {
			processLine(line);
			System.out.println(model.printStack());
			line = scanLine(scanner);
		}
	}

	protected void processLine(String line) {
		logger.info(line);
		StringTokenizer inputs = new StringTokenizer(line, " ");
		for (int i = 1; inputs.hasMoreTokens(); i++) {
			String input = inputs.nextToken();
			try {
				processInput(input);
			} catch(InvalidOperationException e) {
				printError(INVALID_OPERATION_ERROR, input, i);
				return;	
			} catch(InsufficientParametersException e) {
				printError(INSUFFICIENT_PARAMETERS_ERROR, input, i);
				return;
			} catch(UnsupportedOperatorException e) {
				printError(UNSUPPORTED_OPERATOR_ERROR, input, i);
				return;
			}
		}
	}

	protected void processInput(String input)
			throws InsufficientParametersException, UnsupportedOperatorException {
		try {
			BigDecimal number = new BigDecimal(input, DECIMAL64).stripTrailingZeros();
			model.getStack().push(number);
			model.getInputs().push(number);
			return;
		} catch(NumberFormatException e) {
			// Not a number. Perhaps an operator?
		}
		
		IOperator operator = OperatorFactory.getOperator(input);
		operator.operate(model);
		// Store ONLY evaluative operators in inputs history
		if (operator instanceof EvaluativeOperator) {
			model.getInputs().push(operator);
		}
	}

	private void printError(String error, String operator, int position) {
		String errorMessage = String.format(rb.getString(error), operator, position);
		logger.warning(errorMessage);
		System.out.println(errorMessage);
	}

	protected String scanLine(Scanner scanner) {
		// System.out.print("> ");	// Display prompt
		return scanner.nextLine();
	}

	private static void configureLogger() {
		try {
			FileHandler fileHandler= new FileHandler(LOG_FILE);
			logger.addHandler(fileHandler);
			logger.setUseParentHandlers(false);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.setUseParentHandlers(false);	// Remove the console handler
		} catch(SecurityException e) {
			logger.warning(e.getMessage());
	    } catch (IOException e) {
			logger.warning(e.getMessage());
		}
	}

	public static void main(String[] args) {
		configureLogger();
	    new Calculator().run();
	}
}