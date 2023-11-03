package current.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Demo
{
	public static void main(String[] args)
	{
		List<Order> orders = new ArrayList<>();

		orders.add(new Order(1001, "Order 1", Arrays.asList("bat", "ball", "helmet")));
		orders.add(new Order(1002, "Order 2", Arrays.asList("pad", "bat", "helmet")));
		orders.add(new Order(1003, "Order 3", Arrays.asList("stumps", "bat", "gloves")));

		// Print all the items ordered
		orders.stream().map(o -> o.items).flatMap(Collection::stream).forEach(System.out::println);

		// Print all the distinct items ordered
		orders.stream().map(o -> o.items).flatMap(items -> items.stream()).distinct().forEach(System.out::println);
	}

}
