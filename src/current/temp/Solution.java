package current.temp;

import java.util.*;

class DisjointSet {
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

    public int findParent(int n) {
        if (p[n] == n)
            return n;
        p[n] = findParent(p[n]);
        return p[n];
    }

    public void unionByRank(int n1, int n2) {
        int p1 = findParent(n1), p2 = findParent(n2);
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
        int p1 = findParent(n1), p2 = findParent(n2);
        if (s[p1] <= s[p2]) {
            p[p1] = p2;
            s[p2] += s[p1];
        } else {
            p[p2] = p1;
            s[p1] += s[p1];
        }
    }
}