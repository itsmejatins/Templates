package current.temp;

import java.util.List;

class SuperClass
{
	List<? extends SuperClass> list;
	
	public SuperClass(List<? extends SuperClass> list)
	{
		this.list = list;
	}
	
}

public class SubClass extends SuperClass
{
	
	List<SubClass> list;
	
	public SubClass(List<SubClass> list)
	{
		super(list);
		this.list= list;
	}

}