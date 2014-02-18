package com.admarketplace.isg.interview.datastructures;

import java.util.ArrayList;
import java.util.Collections;



import com.admarketplace.isg.util.Util;

public class BinarySearchTree<T extends Comparable<T>> {
	private BinaryNode<T> myRootNode;

	public void addNodes(Object[] values) {
		for (int i = 0; i < values.length; i++) {
			T value = (T)values[i];
			this.addChildToTree(value);
		}
	}

	private void addChildToTree(T value) {
		insert(myRootNode, value);
	}

	private void printTheTree() {
		printTree(myRootNode);
	}

	private void printTree(BinaryNode<T> myNode) {

		Util.echo(myNode.toString());
		if (myNode.leftNode != null)
			printTree(myNode.leftNode);
		if (myNode.rightNode != null)
			printTree(myNode.rightNode);
	}

	public void insert(BinaryNode<T> parentNode, T value) {
		if (myRootNode == null) {
			myRootNode = new BinaryNode<T>(value);
			return;
		}
		if (value.compareTo(parentNode.getValue()) < 0) {
			if (parentNode.leftNode == null)
				parentNode.leftNode = new BinaryNode<T>(value);
			else
				insert(parentNode.leftNode, value);
		} else {
			if (parentNode.rightNode == null)
				parentNode.rightNode = new BinaryNode<T>(value);
			else
				insert(parentNode.rightNode, value);
		}

	}

	public BinaryNode<T> findValue(T value) {
		return findValue(myRootNode, value);
	}

	public BinaryNode<T> findValue(BinaryNode<T> parentNode, T value) {

		if (parentNode != null && value.compareTo(parentNode.getValue()) == 0)
			return parentNode;
		else if (parentNode.leftNode != null && value.compareTo(parentNode.leftNode.getValue()) < 0)
			findValue(parentNode.leftNode, value);
		else if (parentNode.rightNode != null && value.compareTo(parentNode.rightNode.getValue()) > 0)
			findValue(parentNode.rightNode, value);

		return null;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>();
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.add(5);
		array.add(6);
		array.add(7);
		array.add(8);
		array.add(9);
		array.add(10);
		Collections.shuffle(array);
		myTree.addNodes(array.toArray());
		Util.echo("1 was found " + myTree.findValue(1));
		Util.echo("20 was NOT found " + myTree.findValue(20));
		myTree.printTheTree();
	}

}

class BinaryNode<V extends Comparable<V>> {
	private V value;
	public BinaryNode<V> leftNode;
	public BinaryNode<V> rightNode;

	public BinaryNode(V value) {

		this.value = value;
		this.leftNode = null;
		this.rightNode = null;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public String toString() {
		return (leftNode != null ? leftNode.getValue() : "empty") + " ===> " + value + "   <=== "
				+ (rightNode != null ? rightNode.getValue() : "empty");
	}
}
