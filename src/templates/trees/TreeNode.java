package templates.trees;

public class TreeNode
{
	public int val;
	public TreeNode left;
	public TreeNode right;

	TreeNode()
	{
	}

	public TreeNode(int val)
	{
		this.val = val;
	}

	public TreeNode(int val, TreeNode left, TreeNode right)
	{
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString()
	{
		String leftChild = (this.left == null) ? "null" : this.left.val + "";
		String rightChild = (this.right == null) ? "null" : this.right.val + "";
		return "(" + leftChild + "," + this.val + "," + rightChild + ")";
	}
}
