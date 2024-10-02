package templates.dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DsuAdv {
	private int parent[], rank[];
	private int setsCount = 0;
	private Map<Integer, Integer> setsSizeMap; // (id, size)
	private boolean wantSetsSizeMap = false;
	private boolean maintainSetsMap = false; // do it later

	public DsuAdv(int n) {
		parent = new int[n];
		Arrays.fill(parent, -1);
		rank = new int[n];
		Arrays.fill(rank, -1);
	}

	public DsuAdv(int n, boolean wantSetsSizeMap) {
		this(n);
		if (wantSetsSizeMap) {
			setsSizeMap = new HashMap<>();
			this.wantSetsSizeMap = true;
		}
	}

	public void createSet(int i) {
		parent[i] = i;
		rank[i] = 0;
		setsCount++;
		if (wantSetsSizeMap) {
			setsSizeMap.put(i, 1);
		}
	}

	public int getParent(int i) {
		if (parent[i] != i) {
			parent[i] = getParent(parent[i]);
		}
		return parent[i];
	}

	public void union(int x, int y) {
		int pX = getParent(x);
		int pY = getParent(y);

		if (pX == pY)
			return;

		if (rank[pX] > rank[pY]) {
			if (wantSetsSizeMap) {
				int a = setsSizeMap.get(pX), b = setsSizeMap.get(pY);
				setsSizeMap.put(pX, a + b);
				setsSizeMap.remove(pY);
			}
			parent[pY] = pX;
			setsCount--;
		} else {
			if (wantSetsSizeMap) {
				int a = setsSizeMap.get(pX), b = setsSizeMap.get(pY);
				setsSizeMap.put(pY, a + b);
				setsSizeMap.remove(pX);
			}
			parent[pX] = pY;
			if (rank[pX] == rank[pY]) {
				rank[pY]++;
			}
			setsCount--;
		}

	}

	public int getSetsCount() {
		return setsCount;
	}

	public Map<Integer, Integer> getSetsSizeMap() {
		if (wantSetsSizeMap)
			return setsSizeMap;
		else
			throw new RuntimeException("map not prepared");
	}

	public Map<Integer, List<Integer>> getSets() {
		Map<Integer, List<Integer>> map = new HashMap<>();

		for (int node = 0; node < parent.length; node++) {
			int p = getParent(node);
			map.putIfAbsent(p, new ArrayList<>());
			map.get(p).add(p);
		}

		return map;
	}
}