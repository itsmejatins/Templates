package templates.dataStructures;

import java.util.Objects;

public class Pair<T, U>
{
	public T first;
	public U second;

	public Pair(T first, U second)
	{
		this.first = first;
		this.second = second;
	}

	@Override
	public String toString()
	{
		return "[" + first + "," + second + "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(first, second);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!(obj instanceof Pair))
			return false;
		Pair other = (Pair) obj;
		return Objects.equals(first, other.first) && Objects.equals(second, other.second);
	}

}