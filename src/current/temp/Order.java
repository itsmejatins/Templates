package current.temp;

import java.util.List;

public class Order
{
	int id;
	String name;
	List<String> items;

	public Order(int id, String name, List<String> items)
	{
		this.id = id;
		this.name = name;
		this.items = items;
	}

}
