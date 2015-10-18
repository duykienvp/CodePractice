package com.duykien.practice;

import java.util.ArrayList;
import java.util.HashMap;

import com.duykien.practice.Student;
import com.duykien.practice.datatypes.BinaryTree;
import com.duykien.practice.datatypes.LinkedList;
import com.duykien.practice.datatypes.MaxHeap;
import com.duykien.practice.datatypes.Node;
import com.duykien.practice.datatypes.BinaryTree.ETreeTraversalOrder;

public class HelloWorld {

	public static ArrayList<Student> mergeList(Student[] s1, Student[] s2) {
		ArrayList<Student> list = new ArrayList<Student>();
		for (int i = 0; i < s1.length; i++) {
			list.add(s1[i]);
		}
		for (int i = 0; i < s2.length; i++) {
			list.add(s2[i]);
		}
		return list;
	}

	public static HashMap<Integer, Student> createMap(Student[] students) {
		HashMap<Integer, Student> map = new HashMap<Integer, Student>();
		for (int i = 0; i < students.length; i++) {
			map.put(students[i].getId(), students[i]);
		}

		return map;
	}

	public static boolean checkUniqueChars(String s) {
		boolean[] charSet = new boolean[256];
		for (int i = 0; i < s.length(); i++) {
			if (charSet[s.charAt(i)])
				return false;
			charSet[s.charAt(i)] = true;
		}
		return true;
	}

	public static void reverseString(char[] str) {
		if (str == null)
			return;
		int s = 0;
		int t = str.length - 1;
		char tmp;
		while (s < t) {
			// swap
			tmp = str[s];
			str[s] = str[t];
			str[t] = tmp;

			s++;
			t--;
		}
	}

	public static boolean checkAnagram(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;
		if (s1.length() != s2.length())
			return false;
		int[] countChars = new int[256];
		for (int i = 0; i < s1.length(); i++) {
			countChars[s1.charAt(i)]++;
		}
		for (int i = 0; i < s2.length(); i++) {
			if (countChars[s2.charAt(i)] == 0)
				return false;
			countChars[s2.charAt(i)]--;
		}
		return true;
	}

	public static void rotate(int[][] m) {
		if (m == null)
			return;

		int n = m.length;
		// check if m is NxN
		if (n == 0)
			return;
		for (int i = 0; i < n; i++) {
			if (m[i].length != n)
				return;
		}

		// now m is NxN
		int midRow = n / 2;
		int midCol = (n + 1) / 2;
		for (int i = 0; i < midRow; i++) {
			for (int j = 0; j < midCol; j++) {
				// rotate counter clock-wise
				int tmp = m[i][j];
				m[i][j] = m[j][n - 1 - i];
				m[j][n - 1 - i] = m[n - 1 - i][n - 1 - j];
				m[n - 1 - i][n - 1 - j] = m[n - 1 - j][i];
				m[n - 1 - j][i] = tmp;
			}
		}
	}

	public static LinkedList addLists(LinkedList list1, LinkedList list2) {
		if (list1 == null)
			return list2;
		if (list2 == null)
			return list1;

		Node p1 = list1.getHead();
		Node p2 = list2.getHead();

		if (p1 == null)
			return list2;
		if (p2 == null)
			return list1;

		LinkedList answer = new LinkedList();
		int remain = 0;
		while (p1 != null && p2 != null) {
			int data = p1.getData() + p2.getData() + remain;
			remain = data / 10;
			data = data % 10;
			answer.append(data);
			p1 = p1.getNext();
			p2 = p2.getNext();
		}
		if (p1 == null)
			p1 = p2;

		if (p1 != null) {
			int data = p1.getData() + remain;
			remain = data / 10;
			data = data % 10;
			answer.append(data);
			p1 = p1.getNext();
		}

		if (remain != 0) {
			answer.append(remain);
		}
		return answer;
	}

	public static void quickSort(int[] a, int l, int r) {
		if (l < 0 || a.length <= r || a.length == 0 || r <= l)
			return;

		int x = a[(l + r) / 2];
		int i = l;
		int j = r;
		int tmp;
		while (i < j) {
			while (a[i] < x)
				i++;
			while (x < a[j])
				j--;
			if (i <= j) {
				tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;

				i++;
				j--;
			}
		}

		System.out.println(l + ", " + r + ", " + i + ", " + j);
		if (l < j)
			quickSort(a, l, j);
		if (i < r)
			quickSort(a, i, r);
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.insert(12);
		tree.insert(5);
		tree.insert(18);
		tree.insert(2);
		tree.insert(9);
		tree.insert(15);
		tree.insert(19);
		tree.insert(13);
		tree.insert(17);
		tree.traverse(ETreeTraversalOrder.IN_ORDER);
	}
}
