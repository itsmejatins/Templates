package templates.trees;

import java.util.Stack;

public class LeafIterator {
	Stack<TreeNode> st;
	Integer ans = null;

	public LeafIterator(TreeNode root) {
		st = new Stack<>();
		while (root != null) {
			st.push(root);
			root = root.left;
		}

		ans = -1;
	}

	public boolean hasNext() { // Unsafe. When a future next() calls returns null, only then hasNext() will
								// start returning false. So one extra true value will be returned.
		return ans != null;
	}

	public Integer next() {
		while (!st.isEmpty() && !isLeaf(st.peek())) {
			TreeNode node = st.pop().right;
			while (node != null) {
				st.push(node);
				node = node.left;
			}
		}
		ans = st.isEmpty() ? null : st.pop().val;
		return ans;

	}

	private boolean isLeaf(TreeNode node) {
		return node.left == null && node.right == null;
	}
}
