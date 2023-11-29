package lab10;

import java.util.Arrays;
import java.util.Stack;

public class MyFIFO_App {
	// This method reserves the given array
	// This method reverse the given array
	public static <E> void reverse(E[] array) {
		// TODO
		Stack<E> re = new Stack<>();
		for (E e : array) {
			re.push(e);
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = re.pop();
		}
		System.out.println(Arrays.toString(array));
	}

	// This method checks the correctness of the given input
	// i.e. ()(())[]{(())} ==> true, ){[]}() ==> false
	public static boolean isCorrect(String input) {
		// TODO
		Stack<Character> open = new Stack<>();
		for (Character ch : input.toCharArray()) {
			if (ch == '(' || ch == '{' || ch == '[')

				open.push(ch);

			else if ((ch == ')' && !open.isEmpty() && open.pop() == '(')
					|| (ch == '}' && !open.isEmpty() && open.pop() == '{')
					|| (ch == ']' && !open.isEmpty() && open.pop() == '[')) {

			} else
				return false;

		}
		return open.isEmpty();
	}

	// This method evaluates the value of an expression
	// i.e. 51 + (54 *(3+2)) = 321
	public static int evaluateExpression(String expression) {
		// TODO
		String spacedExpression = addBlanks(expression);
		Stack<Integer> operandStack = new Stack<>();
		Stack<String> operatorStack = new Stack<>();
		String[] characters = spacedExpression.split("\\s+");

		for (String token : characters) {
			if (isNumeric(token)) {
				operandStack.push(Integer.parseInt(token));
			} else if (token.equals("(")) {
				operatorStack.push(token);
			} else if (token.equals(")")) {
				while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
					processOperator(operandStack, operatorStack);
				}
				operatorStack.pop(); // Pop the opening parenthesis
			} else if (isOperator(token)) {
				while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token)) {
					processOperator(operandStack, operatorStack);
				}
				operatorStack.push(token);
			}
		}

		while (!operatorStack.isEmpty()) {
			processOperator(operandStack, operatorStack);
		}

		return operandStack.pop();
	}

	private static void processOperator(Stack<Integer> operandStack, Stack<String> operatorStack) {
		String operator = operatorStack.pop();
		int operand2 = operandStack.pop();
		int operand1 = operandStack.pop();
		int result = applyOperator(operand1, operand2, operator);
		operandStack.push(result);
	}

	private static int applyOperator(int operand1, int operand2, String operator) {
		switch (operator) {
		case "+":
			return operand1 + operand2;
		case "-":
			return operand1 - operand2;
		case "*":
			return operand1 * operand2;
		case "/":
			return operand1 / operand2;
		default:
			throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}

	private static int precedence(String operator) {
		switch (operator) {
		case "+":
		case "-":
			return 1;
		case "*":
		case "/":
			return 2;
		default:
			return 0;
		}
	}

	private static boolean isOperator(String token) {
		return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
	}

	private static boolean isNumeric(String token) {
		return token.matches("\\d+");
	}

	public static String addBlanks(String input) {
		String re = "";
		for (int i = 0; i < input.length(); i++) {
			if ((input.charAt(i) == '+') || (input.charAt(i) == '-') || (input.charAt(i) == '*')
					|| (input.charAt(i) == '/') || (input.charAt(i) == '(') || (input.charAt(i) == ')')) {
				re = re + " " + input.charAt(i);
			} else
				re = re + input.charAt(i);
		}
		return re;
	}

	public static void main(String[] args) {
		Integer[] arr = { 3, 5, 6, 2, 8, 7 };
		reverse(arr);
		String arr1 = "()(({[]{}}))";
		String arr2 = "}{}}(){}";
		String arr3 = "()(())[]{(())}";
		String arr4 = "){[]}()";
		System.out.println(isCorrect(arr1));
		System.out.println(isCorrect(arr2));
		System.out.println(isCorrect(arr3));
		System.out.println(isCorrect(arr4));
		String arr5 = "51+(54*(3+2))";
		System.out.println(evaluateExpression(arr5));
	}
}