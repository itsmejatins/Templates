package current.temp;

import templates.trees.Codec;
import templates.trees.LeafIterator;
import templates.trees.TreeNode;

public class Solution {
	
	
	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		LeafIterator leafIterator1 = new LeafIterator(root1);
		LeafIterator leafIterator2 = new LeafIterator(root2);
		

		while (leafIterator1.hasNext() && leafIterator2.hasNext()) {
			Integer next1 = leafIterator1.next();
			Integer next2 = leafIterator2.next();
			if (next1 != next2)
				return false;
		}

		return !leafIterator1.hasNext() && !leafIterator2.hasNext();
	}
	
	public static void main(String[] args) {
		Codec codec = new Codec();
		
		TreeNode root1 = codec.deserialize("41L62VNVL66VNVLNV21VL96VNVL70V74VL");
		TreeNode root2 = codec.deserialize("55LNV84VLNV29VL116VNVL7V74VLNV70VNVNVL");
		
		Solution s = new Solution();
		System.out.println(s.leafSimilar(root1, root2));
		
	}
}