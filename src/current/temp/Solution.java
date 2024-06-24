package current.temp;

import java.util.*;
import java.util.function.BinaryOperator;

class Solution {

    /**
     * This is a general segment tree.
     * T: The data type of elements of the input array from which the segment tree has to be constructed.
     * U: The data type of the underlying array of segment tree. This is also the data type of result of queries.
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
    public class SegmentTree<U> {
        private final U DEFAULT_VALUE;
        public U elementData[];
        public int size; // size of the original array after readjusting the size
        BinaryOperator<U> combiner;


        public SegmentTree(U origArr[], BinaryOperator<U> combiner, U DEFAULT_VALUE) {
            this.DEFAULT_VALUE = DEFAULT_VALUE;
            this.combiner = combiner;
            build(origArr);
        }

        private void build(U[] origArr) {
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
            this.elementData = (U[]) new Object[2 * n - 1];
            Arrays.fill(elementData, DEFAULT_VALUE);
            buildHelper(origArr, 0, n - 1, 0);
        }

        private void buildHelper(U[] origArr, int l, int r, int i) {
            if (l == r) {
                this.elementData[i] = origArr[l];
                return;
            }

            int m = l + (r - l) / 2;
            buildHelper(origArr, l, m, 2 * i + 1);
            buildHelper(origArr, m + 1, r, 2 * i + 2);
            this.elementData[i] = combiner.apply(this.elementData[2 * i + 1], this.elementData[2 * i + 2]);
        }

        public U query(int l, int r) {
            return queryHelper(l, r, 0, this.size - 1, 0);
        }

        private U queryHelper(int ql, int qr, int al, int ar, int i) {
            boolean reject = qr < al || ql > ar;
            if (reject)
                return this.DEFAULT_VALUE;
            boolean completeOverlap = al >= ql && ar <= qr;
            if (completeOverlap)
                return this.elementData[i];

            int m = al + (ar - al) / 2;
            U left = queryHelper(ql, qr, al, m, 2 * i + 1);
            U right = queryHelper(ql, qr, m + 1, ar, 2 * i + 2);
            return combiner.apply(left, right);
        }

        public void update(int x, U newVal) {
            updateHelper(0, this.size - 1, 0, x, newVal);
        }

        private void updateHelper(int l, int r, int i, int x, U newVal) {
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

        public U get(int x) {
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


    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int orig[] = Arrays.copyOf(nums, n);
        Arrays.sort(nums);
        Map<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indices.put(nums[i], i);
        }

        Integer marker[] = new Integer[n];
        Arrays.fill(marker, 0);
        SegmentTree<Integer> sg = new SegmentTree<>(marker, Integer::sum, 0);
        List<Integer> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ans.add(0);
        }

        for (int i = n - 1; i >= 0; i--) {
            int idx = indices.get(orig[i]);
            long oldVal = sg.get(idx);
            sg.update(idx, (int) oldVal + 1);
            if (idx > 0)
                ans.set(i, (int) sg.query(0, idx - 1));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countSmaller(new int[]{0,1,2}));
    }
}