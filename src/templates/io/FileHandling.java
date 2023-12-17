package templates.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandling
{
	public static List<String> readLinesFromFile(String inputPath)
	{
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(inputPath)))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
		}

		catch (IOException e)
		{
			e.printStackTrace();
		}

		return lines;
	}

	public static boolean writeLinesToFile(List<String> lines, String outputPath)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath)))
		{
			for (String line : lines)
			{
				writer.write(line);
				writer.newLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
