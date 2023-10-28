package templates.dataStructures;

import java.util.Arrays;

public class DSU
{
	int parent[], rank[];

	public DSU(int n)
	{
		parent = new int[n];
		Arrays.fill(parent, -1);
		rank = new int[n];
		Arrays.fill(rank, -1);
	}

	public void createSet(int i)
	{
		parent[i] = i;
		rank[i] = 0;
	}

	public int getParent(int i)
	{
		if (parent[i] != i)
		{
			parent[i] = getParent(parent[i]);
		}
		return parent[i];
	}

	public void union(int x, int y)
	{
		int pX = getParent(x);
		int pY = getParent(y);

		if (pX == pY)
			return;
		
//		if (rank[pX] > rank[pY])
//		{
//			parent[pY] = pX;
//		}
//		else
//		{
//			parent[pX] = pY;
//			if (rank[pX] == rank[pY])
//			{
//				rank[pY]++;
//			}
//		}
		
		if(pX != pY)
			parent[pY] = pX;
	}
}
