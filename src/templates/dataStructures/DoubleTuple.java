package templates.dataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoubleTuple implements Comparable<DoubleTuple>
{
	public List<Double> elements;

	public DoubleTuple()
	{
		elements = new ArrayList<>();
	}

	public DoubleTuple(int size)
	{
		elements = new ArrayList<>(size);
	}

	public DoubleTuple(double... e)
	{
		elements = new ArrayList<>(e.length);
		for (double d : e)
			elements.add(d);
	}

	public double getItem(int idx)
	{
		return this.elements.get(idx);
	}

	/**
	 * Compares two tuples and returns the result of the comparison of first
	 * different element found between the two tuples. For tuples of different
	 * length, they are still compared lexicographically. Ex - 1,1,2 < 1,1,2,2 and
	 * 1,3,2 > 1, 1, 2, 3
	 * 
	 */

	@Override
	public int compareTo(DoubleTuple o)
	{
		for (int i = 0; i < Math.min(this.elements.size(), o.elements.size()); i++)
		{
			double x = this.elements.get(i);
			double y = o.elements.get(i);
			int result = Double.compare(x, y);
			if (result == 0)
				continue;
			else
				return result;
		}
		if (this.elements.size() < o.elements.size())
			return -1;
		if (this.elements.size() > o.elements.size())
			return 1;

		return 0;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(elements);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!(obj instanceof DoubleTuple))
			return false;
		DoubleTuple other = (DoubleTuple) obj;
		return Objects.equals(elements, other.elements);
	}

	@Override
	public String toString()
	{
		return this.elements.toString();
	}
}
