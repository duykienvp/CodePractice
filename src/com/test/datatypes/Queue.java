package com.test.datatypes;

public class Queue {
	Node first, last;
	int size;

	public Queue() {
		first = null;
		last = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(int data) {
		Node n = new Node(data);
		if (size == 0) {
			first = n;
			last = n;
			size++;
			return;
		}

		last.setNext(n);
		last = n;
		size++;
	}

	public int dequeue() {
		if (size == 0)
			return 0;
		int data = first.getData();
		first = first.getNext();
		size--;
		return data;
	}
}
