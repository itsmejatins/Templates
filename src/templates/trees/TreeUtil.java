package templates.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeUtil
{
	public static TreeNode generateFromArray(Integer[] values)
	{
		if (values.length == 0)
			return null;

		List<TreeNode> nodes = new ArrayList<>();
		for (int i = 0; i < values.length; i++)
		{
			Integer val = values[i];
			if (val != null)
				nodes.add(new TreeNode(val));
			else
				nodes.add(null);
		}

		for (int i = 0; i < nodes.size(); i++)
		{
			if (nodes.get(i) == null)
				continue;
			if (2 * i + 1 < nodes.size())
				nodes.get(i).left = nodes.get(2 * i + 1);
			if (2 * i + 2 < nodes.size())
				nodes.get(i).right = nodes.get(2 * i + 2);
		}

		return nodes.get(0);

	}

	public static void preorder(TreeNode root)
	{
		if (root == null)
			return;
		System.out.println(root.val);
		preorder(root.left);
		preorder(root.right);
	}

	public static void inorder(TreeNode root)
	{
		if (root == null)
			return;

		inorder(root.left);
		System.out.println(root.val);
		inorder(root.right);
	}

	public static boolean fillPath(List<TreeNode> path, TreeNode root, TreeNode target)
	{
		if (root == null)
			return false;
		path.add(root);
		if (root == target)
			return true;

		boolean stop = false;
		int orig = path.size();
		if (root.left != null)
			stop = fillPath(path, root.left, target);

		if (stop)
			return true;

		if (path.size() != orig)
			path.remove(path.size() - 1);

		if (root.right != null)
			stop = fillPath(path, root.right, target);

		if (stop)
			return true;
		if (path.size() != orig)
			path.remove(path.size() - 1);
		return false;

	}

	public static TreeNode search(TreeNode root, int val)
	{
		if (root == null)
			return null;
		if (root.val == val)
			return root;

		TreeNode left = search(root.left, val);
		if (left != null)
			return left;
		TreeNode right = search(root.right, val);
		if (right != null)
			return right;
		return null;

	}
	
	public static void main(String[] args)
	{
		Integer arr[] = {4,8,null,6,1,9,null,-5,4,null,null,null,-3,null,10};
		preorder(generateFromArray(arr));
	}

}
