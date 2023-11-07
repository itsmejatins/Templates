package templates.dataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is similar to pair, but it is an extend version of it. Tuples are
 * compared by the values they contain from start to the end.
 * 
 * @author jatinsharma
 *
 */
public class Tuple<T extends Comparable<T>> implements Comparable<Tuple<T>>
{

	public List<T> elements;

	public Tuple()
	{
		elements = new ArrayList<>();
	}

	public Tuple(int size)
	{
		elements = new ArrayList<>(size);
	}

	public Tuple(T... e)
	{
		elements = new ArrayList<>(e.length);
		for (T t : e)
			elements.add(t);
	}

	public T getItem(int idx)
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
	public int compareTo(Tuple<T> o)
	{
		for (int i = 0; i < Math.min(this.elements.size(), o.elements.size()); i++)
		{
			T x = this.elements.get(i);
			T y = o.elements.get(i);
			int result = x.compareTo(y);
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
		if (!(obj instanceof Tuple))
			return false;
		Tuple other = (Tuple) obj;
		return Objects.equals(elements, other.elements);
	}

	@Override
	public String toString()
	{
		return this.elements.toString();
	}

}
