package com.duykien.practice.datatypes;

public class MaxHeap {
	  int[] A = null;
	  int size = 0;
	 
	  public MaxHeap(int size) {
	    A = new int[size + 1];
	    for (int i = 0; i < A.length; i++) A[i] = 0;
	    size = A.length - 1;
	  }

	  public MaxHeap(int[] a) {
	    A = new int[a.length + 1];
	    for (int i = 0; i < a.length; i++) A[i + 1] = a[i];
	    size = A.length - 1;
	  }

	  public void print() {
	    for (int i = 0; i < A.length; i++) System.out.print(A[i] + " ");
	    System.out.println();
	  }

	  public void sort() {
	    buildMaxHeap();
	    size = A.length - 1;
	    for (int i = A.length - 1; 1 < i; i--) {
	      //exchange A[i] and A[1]
	      int tmp = A[i];
	      A[i] = A[1]; 
	      A[1] = tmp;
	    
	      size--;
	      maxHeapify(1); 
	    }
	  }

	  /**
	    * Build max-heap from current array
	    */
	  public void buildMaxHeap() {
	    int size = A.length;
	    for (int i = size / 2; 0 < i; i--) {
	      maxHeapify(i);
	    }
	  }

	  /** 
	    * Maintain max-heap property for node at position i
	    */
	  public void maxHeapify(int i) {
	    int left = i << 1; //2 * i
	    int right = left + 1; // 2 * i + 1
	    int largest = i;
	    //Chose largest child, if any
	    if (left <= size && A[largest] < A[left]) {
	      largest = left;
	    }
	    if (right <= size && A[largest] < A[right]) {
	      largest = right;
	    }
	    //Exchange value of i and largest, then continue, if needed
	    if (largest != i) {
	      int tmp = A[i];
	      A[i] = A[largest];
	      A[largest] = tmp;
	  
	      maxHeapify(largest);
	    }
	  }
	}
