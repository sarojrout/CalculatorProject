/**
 * 
 */
package com.demo.calculator;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sarojrout
 *
 */
public class Calculator {
	
	private static Set<Character> tokenSet = new HashSet<Character>();
	static{
		tokenSet.add('+');
		tokenSet.add('-');
		tokenSet.add('*');
		tokenSet.add('/');
	}
	private static int evaluate(String values) {
		char[] tokens = values.toCharArray();
		//tokenSet.addAll(tokens);
		// Stack for numbers: 'input'
		Stack<Integer> input = new Stack<Integer>(10);

		// Stack for Operators: 'ops'
		Stack<Character> ops = new Stack<Character>(20);
		
		for (int i = 0; i < tokens.length; i++) {
			// Current token is a whitespace, skip it
			if (tokens[i] == ' ')
				continue;

			// Current token is a number, push it to stack for numbers
			if (tokens[i] >= '0' && tokens[i] <= '9') {
				StringBuffer sbuf = new StringBuffer();
				// There may be more than one digits in number
				while (i < tokens.length && tokens[i] >= '0'
						&& tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				input.push(Integer.parseInt(sbuf.toString()));
			}

			// Current token is an opening brace, push it to 'ops'
			else if (tokens[i] == '(')
				ops.push(tokens[i]);

			// Closing brace encountered, solve entire brace
			else if (tokens[i] == ')') {
				while (ops.peek() != '(')
					input.push(applyOperartor(ops.pop(), input.pop(), input.pop()));
				ops.pop();
			}

			// Current token is an operator.
			else if (tokenSet.contains(tokens[i])) {
				// While top of 'ops' has same or greater precedence to current
				// token, which is an operator. Apply operator on top of 'ops'
				// to top two elements in values stack
				while (!ops.isEmpty() && containsPrecedence(tokens[i], ops.peek()))
					input.push(applyOperartor(ops.pop(), input.pop(), input.pop()));

				// Push current token to 'ops'.
				ops.push(tokens[i]);
			}
		}

		while (!ops.isEmpty())
			input.push(applyOperartor(ops.pop(), input.pop(), input.pop()));

		//return the top of "input" values
		return input.pop();
	}

	/**
	 * Returns true if 'op2' has higher or same precedence as 'op1' otherwise
	 * returns false
	 * 
	 * @param op1
	 * @param op2
	 * @return
	 */
	private static boolean containsPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	/**
	 * It applies an operator on operands 'a' and 'b' and returns the result
	 * 
	 * @param op
	 * @param b
	 * @param a
	 * @return
	 */
	private static int applyOperartor(char op, int a, int b) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(Calculator.evaluate(args[0]));
	}
}
