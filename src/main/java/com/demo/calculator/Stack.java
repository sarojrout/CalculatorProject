/**
 * 
 */
package com.demo.calculator;

/**
 * @author sarojrout
 *
 */
public class Stack<T> {

	private int top;
	private int stackSize;
	private T[] stackArr;

	/**
	 * Stack constructor to initialize the stack
	 * 
	 * @param size
	 */
	public Stack(int size) {
		stackSize = size > 0 ? size : 20;
		top = -1;
		stackArr = (T[]) new Object[stackSize];
	}

	/**
	 * push value into stack array
	 * 
	 * @param pushValue
	 */
	public void push(T pushValue) {
		if (top == stackArr.length - 1)
			resize(2 * stackArr.length);

		stackArr[++top] = pushValue;
	}

	/**
	 * 
	 * @return the popped value from stack
	 */
	public T pop() {
		if (top == -1)
			return null;

		T itm = stackArr[top];
		stackArr[top--] = null;

		if (top > 0 && top == stackArr.length / 4)
			resize(stackArr.length / 2);

		return itm;
	}

	/**
	 * 
	 * @return the top element from stack
	 */
	public T peek() {
		if (top == -1) {
			return null;
		}
		return stackArr[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	/**
	 * resizing the stack array when the stack is full or 1/4 empty 
	 * 
	 * @param newSize
	 */
	private void resize(int newSize) {
		T t[] = (T[]) new Object[newSize];
		for (int i = 0; i <= top; i++)
			t[i] = stackArr[i];
		stackArr = t;
	}

}
