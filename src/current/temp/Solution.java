package current.temp;

import java.util.Collections;
import java.util.List;

import templates.io.FileHandling;

public class Solution
{
	public static void main(String[] args)
	{
		String inputPath = "/Users/jatinsharma/Desktop/links.txt";
		String outputPath = "/Users/jatinsharma/Desktop/linksShuffled.txt";
		
		List<String> lines = FileHandling.readLinesFromFile(inputPath);
		Collections.shuffle(lines);
		FileHandling.writeLinesToFile(lines, outputPath);
	}
}
