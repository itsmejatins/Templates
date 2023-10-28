package templates.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCA
{

	public int[] minOperationsQueries(int n, int[][] edges, int[][] queries)
	{

		List<List<int[]>> adj = createAdjList(n, edges);

		Object data[] = getFreqAndLevel(adj);
		int freq[][] = (int[][]) data[0], level[] = (int[]) data[1];

		int sparseTable[][] = getSparseTable(adj, level);

		int ans[] = processQueries(queries, sparseTable, freq, level);
		return ans;
	}

	private List<List<int[]>> createAdjList(int n, int edges[][])
	{
		List<List<int[]>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());
		for (int edge[] : edges)
		{
			int u = edge[0], v = edge[1], w = edge[2];
			adj.get(u).add(new int[] { v, w });
			adj.get(v).add(new int[] { u, w });
		}
		return adj;
	}

	private Object[] getFreqAndLevel(List<List<int[]>> adj)
	{
		boolean visited[] = new boolean[adj.size()];

		int freq[][] = new int[adj.size()][0];
		int level[] = new int[adj.size()];

		int rootFreq[] = new int[27];
		helperFL(0, freq, rootFreq, level, adj, visited, 0);

		Object ret[] = { freq, level };
		return ret;
	}

	private void helperFL(int u, int[][] allFreq, int[] currFreq, int level[], List<List<int[]>> adj, boolean visited[],
			int currLevel)
	{
		visited[u] = true;
		allFreq[u] = currFreq;
		level[u] = currLevel;
		for (int[] node : adj.get(u))
		{
			int v = node[0], w = node[1];
			if (visited[v])
				continue;
			int newFreq[] = Arrays.copyOf(currFreq, 27);
			newFreq[w]++;
			helperFL(v, allFreq, newFreq, level, adj, visited, currLevel + 1);
		}
	}

	private int[][] getSparseTable(List<List<int[]>> adj, int level[])
	{
		int maxLevel = Integer.MIN_VALUE;
		for (int l : level)
			maxLevel = Math.max(maxLevel, l);

		int width = (int) Math.ceil(Math.log(maxLevel) / Math.log(2));
		if (width < 0)
			width = 0;
		int sparseTable[][] = new int[width + 1][adj.size()];

		sparseTable[0][0] = -1;
		for (int u = 0; u < level.length; u++)
		{
			for (int node[] : adj.get(u))
			{
				int v = node[0];
				if (level[u] < level[v])
					sparseTable[0][v] = u;
			}

		}

		for (int r = 1; r < sparseTable.length; r++)
		{
			for (int c = 0; c < sparseTable[0].length; c++)
			{
				int halfJumpNode = sparseTable[r - 1][c];
				sparseTable[r][c] = halfJumpNode == -1 ? -1 : sparseTable[r - 1][halfJumpNode];
			}
		}

		return sparseTable;
	}

	private int[] processQueries(int queries[][], int sparseTable[][], int freq[][], int level[])
	{
		int ans[] = new int[queries.length];
		for (int j = 0; j < queries.length; j++)
		{

			int a = queries[j][0], b = queries[j][1], lca = getLca(a, b, sparseTable, level);
			int arr[] = new int[27];
			for (int i = 0; i < 27; i++)
				arr[i] = freq[a][i] + freq[b][i] - 2 * freq[lca][i];

			int sum = 0, max = -1;
			for (int i : arr)
			{
				sum += i;
				max = Math.max(max, i);
			}
			ans[j] = sum - max;
		}
		return ans;
	}

	private int getLca(int a, int b, int st[][], int level[])
	{
		if (a == b)
			return a;

		if (level[a] != level[b])
		{
			int newNodes[] = equalizeLevel(a, b, st, level);
			a = newNodes[0];
			b = newNodes[1];

			if (a == b)
				return a;
		}

		for (int i = st.length - 1; i >= 0; i--)
		{
			if (st[i][a] == st[i][b])
				continue;
			a = st[i][a];
			b = st[i][b];
		}
		return st[0][a];
	}

	private int[] equalizeLevel(int a, int b, int st[][], int level[])
	{
		if (level[a] == level[b])
			return new int[] { a, b };
		if (level[a] > level[b])
		{
			int temp = b;
			b = a;
			a = temp;
		}

		int jumps = level[b] - level[a];
		int nDigits = (int) Math.ceil(Math.log(jumps + 1) / Math.log(2));

		for (int x = 0; x <= nDigits - 1; x++)
			if ((jumps & (1 << x)) != 0)
				b = st[x][b];

		return new int[] { a, b };
	}

	public static void main(String[] args)
	{
		int edges[][] = {};
		int queries[][] = { { 0, 0 } };
		int n = 1;
		System.out.println(new LCA().minOperationsQueries(n, edges, queries));
	}

}
