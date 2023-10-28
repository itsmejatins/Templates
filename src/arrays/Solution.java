package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution
{
	int moves[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public int maximumSafenessFactor(List<List<Integer>> grid)
	{
		if (grid.get(0).get(0) == 1 || grid.get(grid.size() - 1).get(grid.get(0).size() - 1) == 1)
			return 0;
		Queue<int[]> bfs = new LinkedList<>();

		for (int i = 0; i < grid.size(); i++)
		{
			for (int j = 0; j < grid.get(0).size(); j++)
			{
				if (grid.get(i).get(j) == 1)
				{
					bfs.offer(new int[] { i, j, 0 }); // r c w
					grid.get(i).set(j, 0);
				}
				else
					grid.get(i).set(j, Integer.MAX_VALUE);
			}
		}
		int moves[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		int high = Integer.MIN_VALUE;
		while (!bfs.isEmpty())
		{
			int[] pos = bfs.poll();
			for (int[] move : moves)
			{
				int nR = pos[0] + move[0], nC = pos[1] + move[1];
				if (isValid(nR, nC, grid) && grid.get(nR).get(nC) > pos[2] + 1)
				{
					grid.get(nR).set(nC, pos[2] + 1);
					bfs.offer(new int[] { nR, nC, pos[2] + 1 });
					high = Math.max(high, pos[2] + 1);
				}

			}
		}

		int low = 1;
		while (low <= high)
		{
			int mid = (low + high) >> 1;
			if (reachable(grid, mid))
				low = mid + 1;
			else
				high = mid - 1;
		}

		return low - 1; // or high (check)
	}

	private boolean reachable(List<List<Integer>> grid, int mid)
	{
		int fGrid[][] = new int[grid.size()][grid.get(0).size()];
		for (int i = 0; i < fGrid.length; i++)
		{
			for (int j = 0; j < fGrid[0].length; j++)
			{
				if (grid.get(i).get(j) >= mid)
					fGrid[i][j] = 1;
				else
					fGrid[i][j] = -1;
			}
		}
		if(fGrid[0][0] == -1)
			return false;
		Queue<int[]> bfs = new LinkedList<>();
		bfs.add(new int[] { 0, 0 });
		// visited [0][0]
		while (!bfs.isEmpty())
		{
			int pos[] = bfs.poll();
			int r = pos[0], c = pos[1];
			if (r == fGrid.length - 1 && c == fGrid[0].length - 1)
				return true;
			fGrid[r][c] = 100;
			for (int[] move : moves)
			{
				int nR = pos[0] + move[0], nC = pos[1] + move[1];
				if (isValid(nR, nC, grid) && fGrid[nR][nC] != -1 && fGrid[nR][nC] != 100)
				{
					bfs.offer(new int[] { nR, nC });
				}
			}
		}
		return false;
	}

	private boolean isValid(int nR, int nC, List<List<Integer>> grid)
	{
		int n = grid.size();
		int m = grid.get(0).size();
		if (nR < 0 || nR >= n)
			return false;
		if (nC < 0 || nC >= m)
			return false;

		return true;
	}

	public static void main(String[] args)
	{
		List<List<Integer>> nestedList = new ArrayList<>(
				Arrays.asList(Arrays.asList(0, 1, 1), Arrays.asList(0, 0, 0), Arrays.asList(0, 0, 0)));

		var x = new Solution().maximumSafenessFactor(nestedList);
		System.out.println(x);
	}
}