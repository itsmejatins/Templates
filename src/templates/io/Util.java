package templates.io;

import java.util.ArrayList;
import java.util.List;

public class Util
{
	@SuppressWarnings("unused")
	private void swap(int nums[], int i, int j)
	{
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	public static List<List<Integer>> parseNestedList(String input) {
        List<List<Integer>> nestedList = new ArrayList<>();

        // Remove outer brackets and split inner lists
        String innerListStr = input.substring(1, input.length() - 1);
        String[] innerListStrings = innerListStr.split("\\],\\[");

        for (String innerStr : innerListStrings) {
            List<Integer> innerList = new ArrayList<>();
            String[] values = innerStr.split(",");
            for (String value : values) {
                innerList.add(Integer.parseInt(value));
            }
            nestedList.add(innerList);
        }

        return nestedList;
    }
}