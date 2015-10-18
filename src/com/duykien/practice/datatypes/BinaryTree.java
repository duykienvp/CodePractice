package com.duykien.practice.datatypes;

public class BinaryTree {
	/**
	 * Binary tree traversal orders
	 * 
	 * @author kiennd
	 *
	 */
	public enum ETreeTraversalOrder {
		PRE_ORDER, IN_ORDER, POST_ORDER
	}

	TreeNode root;
	int size;

	public BinaryTree() {
		this.root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	/**
	 * Traverse binary tree in provide order. Elements is printed to screen
	 * 
	 * @param order
	 */
	public void traverse(ETreeTraversalOrder order) {
		traverse(root, order);
	}

	/**
	 * Traverse binary tree from a node in provide order. Elements is printed to
	 * screen
	 * 
	 * @param node
	 * @param order
	 */
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

	/**
	 * Insert a key to tree
	 * 
	 * @param key
	 */
	public void insert(int key) {
		size++;

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

	/**
	 * Check whether or not a key is in tree
	 */
	public boolean search(int key) {
		return searchNode(key) != null;
	}

	/**
	 * Search node containing a key
	 * 
	 * @param key
	 * @return the node or {@code null} if key is not in the tree
	 */
	private TreeNode searchNode(int key) {
		TreeNode n = root;
		while (n != null) {
			if (n.getKey() == key)
				return n;
			if (key < n.getKey())
				n = n.getLeft();
			else
				n = n.getRight();
		}

		return null;
	}

	/**
	 * Get min value of the tree or 0 if tree is empty
	 * 
	 * @return
	 */
	public int getMin() {
		if (root == null)
			return 0;
		TreeNode n = getMinNode();
		return n.getKey();
	}

	private TreeNode getMinNode() {
		if (root == null)
			return null;
		TreeNode n = root;
		while (n.getLeft() != null)
			n = n.getLeft();
		return n;
	}

	/**
	 * Get max value of the tree or 0 if tree is empty
	 * 
	 * @return
	 */
	public int getMax() {
		if (root == null)
			return 0;
		TreeNode n = getMaxNode();
		return n.getKey();
	}

	// Get node with max value or 0 if tree empty
	private TreeNode getMaxNode() {
		if (root == null)
			return null;
		TreeNode n = root;
		while (n.getRight() != null)
			n = n.getRight();
		return n;
	}

	/**
	 * Get next value of key in the order
	 * 
	 * @param key
	 * @return next value or 0 if key is not in the tree or is the biggest value
	 */
	public int getNextValue(int key) {
		TreeNode n = searchNode(key);
		if (n == null) {
			return 0;
		}
		n = getSuccessor(n);
		if (n == null) {
			return 0;
		}
		return n.getKey();
	}

	/**
	 * Get successor of a node
	 * 
	 * @param n
	 * @return
	 */
	private TreeNode getSuccessor(TreeNode n) {
		if (n == null)
			return null;

		if (n.getRight() != null) {
			// get left-most child of right sub-tree
			TreeNode tmp = n.getRight();
			while (tmp.getLeft() != null)
				tmp = tmp.getLeft();
			return tmp;
		} else {
			// get the first parent whose child is the left child
			TreeNode p = n.getParent();
			TreeNode tmp = n;
			while (p != null) {
				if (tmp == p.getLeft())
					return p;
				else {
					tmp = p;
					p = p.getParent();
				}
			}
			return null; // n is the biggest
		}
	}

	// getPrecessor should be the same

	/**
	 * Delete a key from tree. If there are multiple node with the key = the
	 * given key, only delete the 1 node
	 * 
	 * @param key
	 */
	public void deleteKey(int key) {
		deleteNode(searchNode(key));
	}

	// Delete a node
	private void deleteNode(TreeNode u) {
		if (u == null)
			return;
		if (u.getLeft() == null) {
			// case 1
			transplant(u, u.getRight());
		} else if (u.getRight() == null) {
			// case 2
			transplant(u, u.getLeft());
		} else {
			TreeNode y = getSuccessor(u);
			if (y.getParent() != u) {
				// case 4
				transplant(y, y.getRight());
				y.setRight(u.getRight());
				u.getRight().setParent(y);
			}
			transplant(u, y);
			y.setLeft(u.getLeft());
			y.getLeft().setParent(y);
		}
	}

	/**
	 * Replace subtree rooted at u by subtree rooted at v
	 * 
	 * @param u
	 * @param v
	 */
	private void transplant(TreeNode u, TreeNode v) {
		if (u.getParent() == null) {
			// u is root
			root = v;
			return;
		}
		if (u == u.getParent().getLeft())
			u.getParent().setLeft(v);
		else
			u.getParent().setRight(v);
		if (v != null)
			v.setParent(u.getParent());
	}
}
