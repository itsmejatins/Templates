package current.temp;

import templates.segment_trees.SegmentTree;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class Playground {
    public static void main(String[] args) {
        Long arr[] = {0L, 1L, 2L, 3L};
        SegmentTree<Long> sg = new SegmentTree<>(
                arr,
                Long::sum,
                0L
        );

//        Integer x = 12;
//        Long y = x.longValue();


    }
}
