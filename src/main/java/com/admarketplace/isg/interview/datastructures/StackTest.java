package com.admarketplace.isg.interview.datastructures;

import com.admarketplace.isg.util.Util;

import java.util.ArrayList;

public class StackTest {

	public static void main(String[] args) {
		Stack<Integer> myStack = new Stack<Integer>();
		myStack.printStack();
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		myStack.push(4);
		myStack.push(5);
		myStack.printStack();
		myStack.pop();
		myStack.pop();
		myStack.printStack();

	}

}

class Stack<T> {
	private ArrayList<T> myArray = new ArrayList<T>();
	private int topIndex = -1;

	public void push(T value) {

		myArray.add(value);
		topIndex++;
	}

	public T pop() {
		if (!IsEmpty()) {
		
			T popped = myArray.get(topIndex);
		
			myArray.remove(topIndex);
			topIndex--;
			return popped;

		} else
			return null;

	}

	public boolean IsEmpty() {
		return topIndex <= -1;
	}

	public void printStack() {
		int index = topIndex;
		while (index >= 0) {
			Util.echo("Stack [" + index + " ] : " + myArray.get(index));
			index--;
		}
	}

}
