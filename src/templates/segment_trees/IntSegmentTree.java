package templates.segment_trees;

import java.util.Arrays;

/**
 * This is for range queries and point updates.
 * Range query will be query(l, r) which returns the sum of the elements of the array
 */
public class IntSegmentTree {
    public long elementData[];
    public int size; // size of the original array after readjusting the size

    public IntSegmentTree(int origArr[]) {
        build(origArr);
    }

    private void build(int[] origArr) {
        int closestSizeToPowerOf2 = 1;
        int n = origArr.length;
        while (closestSizeToPowerOf2 < n) {
            closestSizeToPowerOf2 *= 2;
        }
        if (n != closestSizeToPowerOf2) {
            origArr = Arrays.copyOf(origArr, closestSizeToPowerOf2);
            n = closestSizeToPowerOf2;
        }
        this.size = n;
        this.elementData = new long[2 * n - 1];
        buildHelper(origArr, 0, n - 1, 0);
    }

    private void buildHelper(int[] origArr, int l, int r, int i) {
        if (l == r) {
            this.elementData[i] = origArr[l];
            return;
        }

        int m = l + (r - l) / 2;
        buildHelper(origArr, l, m, 2 * i + 1);
        buildHelper(origArr, m + 1, r, 2 * i + 2);
        this.elementData[i] = this.elementData[2 * i + 1] + this.elementData[2 * i + 2];
    }

    public long query(int l, int r) {
        return queryHelper(l, r, 0, this.size - 1, 0);
    }

    private long queryHelper(int ql, int qr, int al, int ar, int i) {
        boolean reject = qr < al || ql > ar;
        if (reject)
            return 0;
        boolean completeOverlap = al >= ql && ar <= qr;
        if (completeOverlap)
            return this.elementData[i];

        int m = al + (ar - al) / 2;
        return queryHelper(ql, qr, al, m, 2 * i + 1) + queryHelper(ql, qr, m + 1, ar, 2 * i + 2);

    }

    public void update(int x, int newVal) {
        updateHelper(0, this.size - 1, 0, x, newVal);
    }

    private void updateHelper(int l, int r, int i, int x, int newVal) {
        if (l == r) {
            this.elementData[i] = newVal;
            return;
        }
        int m = l + (r - l) / 2;
        if (x <= m) { // go left
            updateHelper(l, m, 2 * i + 1, x, newVal);
        } else {
            updateHelper(m + 1, r, 2 * i + 2, x, newVal);
        }
        this.elementData[i] = this.elementData[2 * i + 1] + this.elementData[2 * i + 2];
    }

    public void increment(int i, int diff) {
        int nodesAheadCount = size - 1 - i;
        int segI = this.elementData.length - nodesAheadCount - 1;

        while (segI / 2 != segI) {
            this.elementData[segI] += diff;
            segI = (int) (Math.ceil(1.0 * segI / 2) - 1);
        }
        this.elementData[0] += diff;
    }


    public void updateIterativeSlow(int x, int newVal) {
        int l = 0, r = this.size - 1, i = 0;
        while (l != r) {
            int m = l + (r - l) / 2;
            if (x <= m) {
                r = m;
                i = 2 * i + 1;
            } else {
                l = m + 1;
                i = 2 * i + 2;
            }
        }
        long diff = newVal - this.elementData[i];
        while (i != i / 2) {
            this.elementData[i] += diff;
            i = (int) (Math.ceil((float) i / 2) - 1);
        }
        this.elementData[i] += diff;
    }

    public void updateIterativeFast(int i, int newVal) {
        int nodesAheadCount = size - 1 - i;
        int segI = this.elementData.length - nodesAheadCount - 1;
        long origVal = this.elementData[segI];
        long diff = newVal - origVal;

        while (segI / 2 != segI) {
            this.elementData[segI] += diff;
            segI = (int) (Math.ceil(1.0 * segI / 2) - 1);
        }
        this.elementData[0] += diff;
    }

    public long get(int x) {
        int l = 0, r = this.size - 1, i = 0;
        while (l != r) {
            int mid = l + (r - l) / 2;
            if (x <= mid) { // go left
                r = mid;
                i = 2 * i + 1;
            } else {
                l = mid + 1;
                i = 2 * i + 2;
            }
        }
        return this.elementData[i];
    }
}
