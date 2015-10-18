package com.duykien.practice.datatypes;

public class BinaryTree {
	public enum ETreeTraversalOrder {
		PRE_ORDER, IN_ORDER, POST_ORDER
	}

	TreeNode root;

	public BinaryTree() {
		this.root = null;
	}

	public void traverse(ETreeTraversalOrder order) {
		traverse(root, order);
	}

	private void traverse(TreeNode node, ETreeTraversalOrder order) {
		if (node == null) {
			return;
		}
		switch (order) {
		case PRE_ORDER:
			System.out.print(node.getKey() + " ");
			traverse(node.getLeft(), order);
			traverse(node.getRight(), order);
			break;
		case IN_ORDER:
			traverse(node.getLeft(), order);
			System.out.print(node.getKey() + " ");
			traverse(node.getRight(), order);
			break;
		case POST_ORDER:
			traverse(node.getLeft(), order);
			traverse(node.getRight(), order);
			System.out.print(node.getKey() + " ");
		}
	}

	// Insert a key to tree
	public void insert(int key) {
		TreeNode node = new TreeNode(key);
		if (this.root == null) { // empty tree
			this.root = node;
			return;
		}

		TreeNode n = this.root;
		TreeNode p = n.getParent();
		while (n != null) {
			p = n;
			if (key < n.getKey()) {
				// move to left tree
				n = n.getLeft();
			} else {
				// move to right
				n = n.getRight();
			}
		}
		if (key < p.getKey()) {
			// left child
			p.setLeft(node);
		} else {
			// right child
			p.setRight(node);
		}
		node.setParent(p);
	}

}
