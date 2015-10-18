package com.test.datatypes;

public class Node {
	Node next = null;
	int data;

	public Node(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return this.next;
	}

	public void setNext(Node n) {
		this.next = n;
	}
}
