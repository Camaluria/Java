
/**
 * Single Link List Class for State Objects
 * @author Cameron Alexander - N01242446
 * @version 1.06 (3/11/2017)
 */
public class LinkList
{
	private Link first; //first Link
	
	/**
	 * Default Constructor for LinkList
	 */
	public LinkList()
	{
		first = null;
	}
	
	
	/**
	 * Delete link
	 * @return previous first link 
	 */
	public Link delete()
	{
		Link temp = first;
		first = first.nextLink;
		return temp;		
	}
	

	
	/**
	 * Delete first link in Link List
	 * @return previous first link
	 */
	public Link deleteFirst()
	{
		Link temp = first;
		first = first.nextLink;
		return temp;
	}
	

	/**
	 * Retrieve first Link in Linked List
	 * @return first Link
	 */
	public Link getFirst()
	{
		Link current = first;
		
		return current;
	}
	
	/**
	 * Determine if LinkList has any Links stored
	 * @return true if LinkList is empty
	 */
	public boolean isEmpty()
	{
		return (first==null);
	}
	
	/**
	 * Insert State into first Link position
	 * @param state State object to add
	 */
	public void insertFirst(State state)
	{
		Link newLink = new Link(state);
		newLink.nextLink = first;
		first = newLink;
	}
	
	/**
	 * Display all objects in Stack
	 * 
	 */
	public void displayStack()
	{
		System.out.println("Displaying Stack Contents");
		System.out.printf("\n%-20s%-20s%-15s%-10s%18s%25s\n","State Name", "Capital City", "State Abbr", "State Population", "Region", "US House Seats"); 
		System.out.println("_____________________________________________________________________________________________________________________");
		
		Link current = first;
		
		while(current != null)
		{
			current.data.displayState();
			current = current.nextLink;
		}
		
	}
}
