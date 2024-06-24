package templates.segment_trees;

import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * This is a general segment tree.
 * T: The data type of the underlying array of segment tree. This is also the data type of result of queries.
 * For constructing this segment tree, you need to supply the following -
 * T[] origArray: The array from which you want to construct this tree.
 * BinaryOperator<U> combiner: the operation that you want to apply over the elements or sub-ranges of the underlying
 * segment tree
 * U DEFAULT_VALUE: the default value that needs to be returned when the current segment is out of range of the supplied
 * range from the query.
 * <p>
 * Ex - For range-sum queries, the parameters will be the following -
 * origArray[]: Array of numbers
 * combiner: (u, v) -> u + v
 * DEFAULT_VALUE: 0
 */
public class SegmentTree<T> {
    private final T DEFAULT_VALUE;
    public T elementData[];
    public int size; // size of the original array after readjusting the size
    BinaryOperator<T> combiner;


    public SegmentTree(T origArr[], BinaryOperator<T> combiner, T DEFAULT_VALUE) {
        this.DEFAULT_VALUE = DEFAULT_VALUE;
        this.combiner = combiner;
        build(origArr);
    }

    private void build(T[] origArr) {
        int closestSizeToPowerOf2 = 1;
        int n = origArr.length;
        while (closestSizeToPowerOf2 < n) {
            closestSizeToPowerOf2 *= 2;
        }
        if (n != closestSizeToPowerOf2) {
            origArr = Arrays.copyOf(origArr, closestSizeToPowerOf2);
            Arrays.fill(origArr, n, closestSizeToPowerOf2, DEFAULT_VALUE);
            n = closestSizeToPowerOf2;
        }
        this.size = n;
        this.elementData = (T[]) new Object[2 * n - 1];
        Arrays.fill(elementData, DEFAULT_VALUE);
        buildHelper(origArr, 0, n - 1, 0);
    }

    private void buildHelper(T[] origArr, int l, int r, int i) {
        if (l == r) {
            this.elementData[i] = origArr[l];
            return;
        }

        int m = l + (r - l) / 2;
        buildHelper(origArr, l, m, 2 * i + 1);
        buildHelper(origArr, m + 1, r, 2 * i + 2);
        this.elementData[i] = combiner.apply(this.elementData[2 * i + 1], this.elementData[2 * i + 2]);
    }

    public T query(int l, int r) {
        return queryHelper(l, r, 0, this.size - 1, 0);
    }

    private T queryHelper(int ql, int qr, int al, int ar, int i) {
        boolean reject = qr < al || ql > ar;
        if (reject)
            return this.DEFAULT_VALUE;
        boolean completeOverlap = al >= ql && ar <= qr;
        if (completeOverlap)
            return this.elementData[i];

        int m = al + (ar - al) / 2;
        T left = queryHelper(ql, qr, al, m, 2 * i + 1);
        T right = queryHelper(ql, qr, m + 1, ar, 2 * i + 2);
        return combiner.apply(left, right);
    }

    public void update(int x, T newVal) {
        updateHelper(0, this.size - 1, 0, x, newVal);
    }

    private void updateHelper(int l, int r, int i, int x, T newVal) {
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
        this.elementData[i] = combiner.apply(this.elementData[2 * i + 1], this.elementData[2 * i + 2]);
    }

    public T get(int x) {
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

    public int[] getRange(int i){
//        int l = 0, r = this.size - 1, x = 0;
//        while(l != r) {
//            int mid = l + (r - l) / 2;
//            if(i )
//        }

        return null;
    }
}