package templates.graph;

import java.util.ArrayList;
import java.util.List;

import templates.dataStructures.Pair;

public class Util
{
	public static List<List<Integer>> getAdj(int n, int edges[][], boolean directed)
	{
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		for (int edge[] : edges)
		{
			int u = edge[0], v = edge[1];
			adj.get(u).add(v);
			if (!directed)
				adj.get(v).add(u);
		}
		return adj;
	}

	public static List<List<Integer>> getReveseAdj(int n, int edges[][])
	{
		List<List<Integer>> radj = new ArrayList<>(n);
		for (int i = 0; i < n; i++)
			radj.add(new ArrayList<>());
		for (int edge[] : edges)
		{
			int u = edge[0], v = edge[1];
			radj.get(v).add(u);
		}
		return radj;
	}

	public static List<List<Integer>> getReverseAdj(int n, List<List<Integer>> adj)
	{
		List<List<Integer>> radj = new ArrayList<>(n);
		for (int i = 0; i < n; i++)
			radj.add(new ArrayList<>());
		for (int u = 0; u < adj.size(); u++)
		{
			for (int v : adj.get(u))
				radj.get(v).add(u);
		}
		return radj;
	}
	
	public static List<List<int[]>> getWeightedAdj(int n, int edges[][], boolean directed)
	{
		List<List<int[]>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		for (int edge[] : edges)
		{
			int u = edge[0], v = edge[1], w = edge[2];
			adj.get(u).add(new int[] { v, w });
			if (!directed)
				adj.get(v).add(new int[] { u, w });
		}
		return adj;
	}
	
	public static List<List<Pair<Integer, Integer>>> getWeightedAdjPair(int n, int edges[][], boolean directed)
	{
		List<List<Pair<Integer, Integer>>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		for (int edge[] : edges)
		{
			int u = edge[0], v = edge[1], w = edge[2];
			adj.get(u).add(new Pair<>(v, w));
			if (!directed)
				adj.get(v).add(new Pair<>(u, w));
		}
		return adj;
	}
}
