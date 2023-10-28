package templates.trees;

import java.util.LinkedList;
import java.util.Queue;

public class Codec
{

	public String serialize(TreeNode root)
	{
		if (root == null)
			return "";
		StringBuilder encoding = new StringBuilder();
		encoding.append(root.val + "L"); // notice no V
		Queue<TreeNode> bfs = new LinkedList<>();
		bfs.add(root);
		while (!bfs.isEmpty())
		{
			int n = bfs.size();
			for (int i = 0; i < n; i++)
			{
				TreeNode curr = bfs.poll();
				if (curr.left == null)
					encoding.append("NV");
				else
				{
					encoding.append(curr.left.val + "V");
					bfs.offer(curr.left);
				}
				if (curr.right == null)
					encoding.append("NV");
				else
				{
					encoding.append(curr.right.val + "V");
					bfs.offer(curr.right);
				}
			}
			encoding.append('L');
		}
		return encoding.toString();
	}

	public TreeNode deserialize(String data)
	{
		if (data.isEmpty())
			return null;

		String levels[] = data.split("L");
		TreeNode root = new TreeNode(Integer.parseInt(levels[0]));

		Queue<TreeNode> bfs = new LinkedList<>();
		bfs.offer(root);

		for (int l = 1; l < levels.length; l++)
		{
			String currLevel[] = levels[l].split("V");
			int n = bfs.size();
			int j = 0;
			for (int i = 0; i < n; i++)
			{
				TreeNode node = bfs.poll();
				if (!currLevel[j].equals("N"))
				{
					node.left = new TreeNode(Integer.parseInt(currLevel[j]));
					bfs.offer(node.left);
				}
				j++;
				if (!currLevel[j].equals("N"))
				{
					node.right = new TreeNode(Integer.parseInt(currLevel[j]));
					bfs.offer(node.right);
				}
				j++;
			}
		}
		return root;

	}
}