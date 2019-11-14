package myUtil;

public class ListItem
{
	
	private Object valueMember;
	private Object displayMember;
	
	public ListItem(Object valueMember, Object displayMember)
	{
		this.valueMember = valueMember;
		this.displayMember = displayMember;
	}

	public Object getValueMember()
	{
		return this.valueMember;
	}

	public Object getDisplayMember()
	{
		return this.displayMember;
	}

	@Override
	public String toString()
	{
		return displayMember.toString();
	}	

}
