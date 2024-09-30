package current.temp;

import java.util.*;

class AllOne {

    Map<String, Integer> map;
    TreeMap<Integer, Set<String>> max;
    TreeMap<Integer, Set<String>> min;

    public AllOne() {
        map = new HashMap<>();
        max = new TreeMap<>((a, b) -> b - a);
        min = new TreeMap<>();
    }

    public void inc(String key) {
        int count = map.getOrDefault(key, 0) + 1;
        map.put(key, count);

        if (max.containsKey(count - 1)) {
            max.get(count - 1).remove(key);
            if (max.get(count - 1).isEmpty())
                max.remove(count - 1);
        }
        max.putIfAbsent(count, new HashSet<>());
        max.get(count).add(key);

        if (min.containsKey(count - 1)) {
            min.get(count - 1).remove(key);
            if (min.get(count - 1).isEmpty())
                min.remove(count - 1);
        }
        min.putIfAbsent(count, new HashSet<>());
        min.get(count).add(key);
    }

    public void dec(String key) {
        int count = map.get(key);
        if (count == 1)
            map.remove(key);
        else
            map.put(key, count - 1);

        if (count > 1) {
            max.putIfAbsent(count - 1, new HashSet<>());
            max.get(count - 1).add(key);
        }
        max.get(count).remove(key);
        if (max.get(count).isEmpty()) {
            max.remove(count);
        }

        if (count > 1) {
            min.putIfAbsent(count - 1, new HashSet<>());
            min.get(count - 1).add(key);
        }
        min.get(count).remove(key);
        if (min.get(count).isEmpty()) {
            min.remove(count);
        }

    }

    public String getMaxKey() {
        var firstEntry = max.firstEntry();
        return firstEntry == null ? "" : firstEntry.getValue().iterator().next();
    }

    public String getMinKey() {
        var firstEntry = min.firstEntry();
        return firstEntry == null ? "" : firstEntry.getValue().iterator().next();
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
public class Solution {
    public static void main(String[] args) {
        AllOne a = new AllOne();
        a.inc("helloji");
        a.inc("helloji");
        System.out.println(a.getMaxKey());
        System.out.println(a.getMinKey());
        a.inc("hello");
        System.out.println(a.getMaxKey());
        System.out.println(a.getMinKey());
    }
}
