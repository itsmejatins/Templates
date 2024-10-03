package current.temp;

import java.util.*;

class Solution {
    static class DisjointSet {
        int p[], r[], s[], size;

        public DisjointSet(int n) {
            this.size = n;
            this.p = new int[n];
            this.s = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
                s[i] = 1;
            }
            this.r = new int[n];
        }

        public int getParent(int n) {
            if (p[n] == n)
                return n;
            p[n] = getParent(p[n]);
            return p[n];
        }

        public void unionByRank(int n1, int n2) {
            int p1 = getParent(n1), p2 = getParent(n2);
            if (p1 == p2)
                return;
            int r1 = r[p1], r2 = r[p2];

            if (r1 <= r2) {
                p[p1] = p2;
            } else {
                p[p2] = p1;
            }
            if (r1 == r2) {
                r[p2]++;
            }
        }

        public void unionBySize(int n1, int n2) {
            int p1 = getParent(n1), p2 = getParent(n2);
            if (p1 == p2)
                return;
            if (s[p1] <= s[p2]) {
                p[p1] = p2;
                s[p2] += s[p1];
            } else {
                p[p2] = p1;
                s[p1] += s[p2];
            }
        }
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        DisjointSet ds = new DisjointSet(n * m);
        int moves[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                int currNode = i * m + j;
                for (int move[] : moves) {
                    int nr = i + move[0], nc = j + move[1];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || grid[nr][nc] == 0)
                        continue;
                    int neighNode = nr * m + nc;
                    ds.unionBySize(currNode, neighNode);
                }

            }
        }

        int ans = 1;
        for (int i: ds.s)
            ans = Math.max(ans, i);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    continue;
                int mergedSize = 1;
                Set<Integer> parents = new HashSet<>();
                for (int move[] : moves) {
                    int nr = i + move[0], nc = j + move[1];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || grid[nr][nc] == 0)
                        continue;
                    int neighNode = nr * m + nc;
                    parents.add(ds.getParent(neighNode));
                }
                for (int p: parents) {
                    mergedSize += ds.s[p];
                }
                ans = Math.max(ans, mergedSize);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int arr[][] = {{1, 0}, {1, 1}};
        System.out.println(new Solution().largestIsland(arr));
    }
}