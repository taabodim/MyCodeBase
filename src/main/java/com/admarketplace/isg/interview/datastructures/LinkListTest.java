package com.admarketplace.isg.interview.datastructures;

import com.admarketplace.isg.util.Util;

class LinkNode {

	String data;
	LinkNode next;
	LinkNode previous;
	
	public LinkNode(String item) {
		data = item;
	}

}

class MyLinkedList {
	LinkNode head;//fist node
	LinkNode tail;//last node

	public MyLinkedList(String item) {
		head = new LinkNode(item);
		tail = head;
		head.next = null;
		head.previous = null;
		tail.next =null;
		tail.previous = null;
	}

	public LinkNode addToEndOfList(String item) {

		LinkNode node = new LinkNode(item);
		LinkNode lastNode = tail;
		tail = node;
		lastNode.next = tail;
		tail.previous = lastNode;
		return tail;
	}

	public LinkNode addToBeginningofList(String item) {

		LinkNode node = new LinkNode(item);// am
		node.next = head;
		head = node;
		return head;
	}
	
	public LinkNode addToBetweenNodes(String item,LinkNode nodeBefore,LinkNode nodeAfter) {
		LinkNode node = new LinkNode(item);
		nodeBefore.next = node;
		node.next = nodeAfter;
		return node;
	}

	public void printListFromTheBeginning() {
		LinkNode currentNode = head;
		while (currentNode != null) {
			Util.echo(currentNode.data);
			currentNode = currentNode.next;

		}
	}
	
	public void printList() {
		LinkNode currentNode = head;
		while (currentNode != null) {
			Util.echo(currentNode.data);
			currentNode = currentNode.next;

		}
	}
	
	public void printListFromEnd() {
		LinkNode currentNode = head;
		while (currentNode != null) {
			Util.echo(currentNode.data);
			currentNode = currentNode.next;

		}
	}

	public LinkNode find(String data) {
		LinkNode nodeToFind = new LinkNode(data);
		LinkNode currentNode = head;
		while(currentNode!=null)
		{
			Util.echo("checking "+nodeToFind.data+" against "+currentNode.data);
			if(nodeToFind.data.equalsIgnoreCase(currentNode.data))
				{
				return currentNode;
				}
			
			currentNode = currentNode.next;
		}
		return null;
	}

}


public class LinkListTest {

	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList(" ");
//		list.addToEndOfList(" am ");
//		list.addToBeginningofList(" Hi, ");
//		LinkNode mahmoudNode = list.addToEndOfList(" mahmoud ");
//		LinkNode taabodiNode = list.addToEndOfList(" taabodi ");
//		list.addToEndOfList(" ,how are you? ");
//		LinkNode rezaNode =list.addToBetweenNodes(" reza ", mahmoudNode, taabodiNode);
//		
		
	
		list.addToEndOfList("mahmoud");
		list.addToEndOfList("taabodi");
		list.addToBeginningofList("am");
		list.addToBeginningofList("I ");
		Util.echo("data was found "+ list.find("taabodi"));
		list.printList();

		// list.printList();

		// while (!list.isEmpty()) {
		// Node deletedLink = list.delete();
		// System.out.print("deleted: ");
		// deletedLink.printLink();
		// System.out.println("");
		// }
		// list.printList();
	}
}