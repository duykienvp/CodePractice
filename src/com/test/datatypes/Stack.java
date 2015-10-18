package com.test.datatypes;

public class Stack {
	  Node head;
	  int size;
	  public Stack() {
	    head = null;
	    size = 0;
	  }

	  public int size() {
	    return size;
	  }

	  public void push(int data) {
	    Node n = new Node(data);
	    n.setNext(head);
	    head = n;
	    size++;
	  }  
	  
	  public int top() {
	    if (head == null) return 0;
	    else return head.getData();
	  }

	  public int pop() {
	    if (head == null) return 0;
	    int data = head.getData();
	    head = head.getNext();
	    size--;
	    return data;
	  }
	}

