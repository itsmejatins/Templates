package templates.trees;

import java.util.Stack;

class BstIterator {

	Stack<TreeNode> st;

	public BstIterator(TreeNode root) {
		st = new Stack<>();
		st.push(root);
		TreeNode traverse = root;
		while (traverse.left != null) {
			st.push(traverse.left);
			traverse = traverse.left;
		}
	}

	public int next() {
		TreeNode next = st.pop();
		if (next.right != null) {
			TreeNode traverse = next.right;
			st.push(traverse);
			while (traverse.left != null) {
				st.push(traverse.left);
				traverse = traverse.left;
			}
		}
		return next.val;
	}

	public boolean hasNext() {
		return !st.isEmpty();
	}
}