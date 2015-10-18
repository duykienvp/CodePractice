package com.duykien.practice.datatypes;

import java.util.HashSet;

public class LinkedList {
	Node head = null;
	int size = 0;

	public Node getHead() {
		return head;
	}

	public int getSize() {
		return size;
	}

	/**
	 * Append data to the end of list
	 * 
	 * @param data
	 */
	public void append(int data) {
		size++;
		Node node = new Node(data);
		if (head == null) {
			head = node;
			return;
		}
		Node n = head;
		while (n.getNext() != null)
			n = n.getNext();
		n.setNext(node);
	}

	/**
	 * Print elements in the list
	 */
	public void print() {
		Node n = head;
		while (n != null) {
			System.out.print(n.getData() + " ");
			n = n.getNext();
		}
		System.out.println();
	}

	/**
	 * Delete the first node containing given data
	 * 
	 * @return whether the data is found or not
	 */
	public boolean deleteNode(int data) {
		if (head == null)
			return false;
		if (head.getData() == data) {
			head = head.getNext();
			size--;
			return true;
		}
		Node n = head;
		while (n.getNext() != null) {
			if (n.getNext().getData() == data) {
				deleteNextNode(n);
				return true;
			}
			n = n.getNext();
		}

		return false;
	}

	/**
	 * Delete next node and point the next field of current node to next of next
	 * node
	 */
	private void deleteNextNode(Node n) {
		if (n == null || n.getNext() == null)
			return;
		n.setNext(n.getNext().getNext());
		size--;
	}

	/**
	 * Remove duplicates in this list
	 */
	public void removeDuplicates() {
		if (head == null)
			return;
		HashSet<Integer> oldElements = new HashSet<Integer>();
		oldElements.add(head.getData());
		Node n = head;
		while (n.getNext() != null) {
			if (oldElements.contains(n.getNext().getData())) {
				deleteNextNode(n);
				if (n.getNext() == null)
					break; // we went to tail
				n = n.getNext();
			} else {
				oldElements.add(n.getNext().getData());
				n = n.getNext();
			}
		}
	}

	/**
	 * Find the element at the n-th to the last element
	 */
	public Node findReverseElement(int n) {
		if (head == null || n < 1 || size < n) {
			return null;
		}

		Node p1 = head;
		Node p2 = head;

		// advance p2 n-1 step
		for (int i = 0; i < n - 1; i++) {
			p2 = p2.getNext();
		}

		// move p1, p2 together until p2 is tail
		while (p2.getNext() != null) {
			p1 = p1.getNext();
			p2 = p2.getNext();
		}

		return p1;
	}

}
