package com.admarketplace.isg.interview.datastructures;

import com.admarketplace.isg.util.Util;


public class BinarySearchTree {
	private static BinaryNode myRootNode = new BinaryNode(0);
	public static void main(String[] args) {
		BinarySearchTree myTree = new BinarySearchTree();
		myTree.addNodes(12, 234, 45, 67, 34);
		Util.echo("67 was found "+myRootNode.findValue(myRootNode, 67).getValue());
		Util.echo("68 was NOT found "+myRootNode.findValue(myRootNode, 68).getValue());
//		printTheTree(myRootNode);
	}

	private void addNodes(int... values) {
		for (int i = 0; i < values.length; i++) {
			int value = values[i];
			this.addChildToTree(value);
		}
	}

	private void addChildToTree(int value) {
		myRootNode.insert(myRootNode, value);
	}
	
	private static void printTheTree(BinaryNode myNode) {
	    
	    Util.echo("myNode value is "+myNode.getValue());
		Util.echo(" left child value is "+ (myNode.leftNode!=null ? myNode.leftNode.getValue() : "empty"));
		Util.echo(" right child value is "+(myNode.rightNode!=null ? myNode.rightNode.getValue() : "empty"));
		if(myNode.leftNode!=null)
	    	printTheTree(myNode.leftNode);
	    if(myNode.rightNode!=null)
	    	printTheTree(myNode.rightNode);
	}
	

}

class BinaryNode {
	private Integer value;
	public BinaryNode leftNode;
	public BinaryNode rightNode;

	public BinaryNode(Integer value) {

		this.value = value;
		this.leftNode = null;
		this.rightNode = null;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void insert(BinaryNode parentNode, int value) {

		if (value < parentNode.getValue()) {
			if (parentNode.leftNode == null)
				parentNode.leftNode = new BinaryNode(value);
			else
				insert(parentNode.leftNode, value);
		} else {
			if (parentNode.rightNode == null)
				parentNode.rightNode = new BinaryNode(value);
			else
				insert(parentNode.rightNode, value);
		}

	}

	public BinaryNode findValue(BinaryNode parentNode, int value) {
		if (parentNode != null && parentNode.getValue() == value)
			return parentNode;
		else if (parentNode.leftNode != null && parentNode.leftNode.getValue() <= value)
			findValue(parentNode.leftNode, value);
		else if (parentNode.rightNode != null && parentNode.rightNode.getValue() >= value)
			findValue(parentNode.rightNode, value);

		return null;
	}
}
