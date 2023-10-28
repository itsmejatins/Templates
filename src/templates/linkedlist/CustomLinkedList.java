package templates.linkedlist;

class CustomLinkedList<T>
{
	static class Node<T>
	{
		T val1, val2, val3;
		Node<T> next;
		Node<T> prev;

		public Node(T val1, T val2, T val3)
		{
			this.val1 = val1;
			this.val2 = val2;
			this.val3 = val3;
		}
		
		@Override
		public String toString()
		{
			return this.val1 + "";
		}
	}

	long size = 0;
	public Node<T> head = null;
	public Node<T> tail = null;

	@Override
	public String toString() // wont work if T is not primitive like list as cannot concatenate it with
								// string
	{
		StringBuilder list = new StringBuilder();
		list.append('[');
		Node<T> traverse = head;
		while (traverse != null)
		{
			list.append(", " + traverse.val1);
			traverse = traverse.next;
		}
		list.append(']');
		return list.toString();

	}

	public void add(Node<T> node)
	{
		if (size == Long.MAX_VALUE)
			throw new RuntimeException("Maximum list size reached");

		if (size == 0)
		{
			head = node;
			tail = node;
			head.next = null;
			head.prev = null;
		}
		else
		{
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
	}

	public void remove(Node<T> node)
	{
		if (size == 0)
		{
			throw new RuntimeException("Can't remove elements from an empty list");
		}
		else if(size == 1)
		{
			head = null;
			tail = null;
		}
		else if (node == head)
		{
			head = head.next;
			head.prev.next = null;
			head.prev = null;
		}
		else if (node == tail)
		{
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
		}
		else
		{
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.next = null;
			node.prev = null;
		}
		size--;
	}

}