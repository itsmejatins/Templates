package templates.dataStructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dsu
{
	int parent[], rank[];
	int nComponents = 0;
	Map<Integer, Integer> components; // (id, size)
	boolean wantMap = false;

	public Dsu(int n)
	{
		parent = new int[n];
		Arrays.fill(parent, -1);
		rank = new int[n];
		Arrays.fill(rank, -1);
	}

	public Dsu(int n, boolean wantMap)
	{
		this(n);
		if (wantMap)
		{
			components = new HashMap<>();
			this.wantMap = true;
		}
	}

	public void createSet(int i)
	{
		parent[i] = i;
		rank[i] = 0;
		nComponents++;
		if (wantMap)
		{
			components.put(i, 1);
		}
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

		if (rank[pX] > rank[pY])
		{
			if(wantMap)
			{
				int a = components.get(pX), b = components.get(pY);
				components.put(pX, a + b);
				components.remove(pY);
			}
			parent[pY] = pX;
			nComponents--;
		}
		else
		{
			if(wantMap)
			{
				int a = components.get(pX), b = components.get(pY);
				components.put(pY, a + b);
				components.remove(pX);
			}
			parent[pX] = pY;
			if (rank[pX] == rank[pY])
			{
				rank[pY]++;
			}
			nComponents--;
		}

	}
}
