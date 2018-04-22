/** 
 * Queue Class
 * Creates a Doubly Linked List for referencing State Objects
 * @author Cameron Alexander - N01242446
 * @version 1.12 (3/11/2017)
 *  
 */
public class Queue 
{
	private Link first; //first Link object in Queue
	private Link last; //last Link object in Queue
	
	/**
	 * Default Constructor for Queue
	 */
	public Queue()
	{
		first = null;
		last = null;
	}
	
	/**
	 * Delete Link in queue based on State name
	 * @param name name of state to be removed
	 * @return the current Link
	 */
	public Link findDelete(String name)
	{
		Link current = first;
		
		//iterate through Links until name matches a state object
		while(!current.data.getStateName().equals(name))
		{
			current = current.nextLink;
			if(current == null)
			{
				return null;
			}
		}
		if(current== first)
		{
			first = current.nextLink;
		}
		else
		{
			current.prevLink.nextLink = current.nextLink;
		}
		
		if(current == last)
		{
			last = current.prevLink;
		}
		else
		{
			current.nextLink.prevLink = current.prevLink;
		}
		return current;
	}
	
	
	/**
	 * Determine if Queue has any Link objects
	 * @return If Queue is empty
	 */
	public Boolean isEmpty()
	{
		return (first == null);
	}
	
	/**
	 * Add State Object to Queue as a Link
	 * @param state State object to be inserted
	 */
	public void insertFront(State state)
	{
		Link newLink = new Link(state);
		
		if(isEmpty())
		{
			last = newLink;
		}
		else
		{
			first.prevLink = newLink;
			
		}
		newLink.nextLink = first;
		first = newLink;
	}
	
	/**
	 *  Add State object as Link in Queue at the rear
	 * @param state State object to be inserted
	 */
	public void insertEnd(State state)
	{
		Link newLink = new Link(state);
		
		if(isEmpty())
		{
			first = newLink;
		}
		else
		{
			last.nextLink = newLink;
			newLink.prevLink = last;
		}
		last = newLink;
	}
	
	/**
	 *  Remove first Link in Queue
	 */
	public Link removeFirst()
	{
		Link tempLink = first;
		
		if(first.nextLink == null)
		{
			last = null;
		}
		else
		{
			first.nextLink.prevLink = null;
		}
		first = first.nextLink;
		return tempLink;
	}
	
	/**
	 * Remove last Link in Queue
	 * @return previous last Link in Queue
	 */
	public Link removeLast()
	{
		Link tempLink = last;
		
		if(last.prevLink == null)
		{
			first = null;
		}
		else
		{
			last.prevLink.nextLink = null;
		}
		last = last.prevLink;
		return tempLink;
	}
	
	/**
	 * Print all State Objects in Queue
	 * @param message message to add to title
	 */
	public void printQueue(String message)
	{
		Link current = first;
		
		System.out.println("Displaying " + message +" Queue Contents");
		System.out.printf("\n%-20s%-20s%-15s%-10s%18s%25s\n","State Name", "Capital City", "State Abbr", "State Population", "Region", "US House Seats"); 
		System.out.println("_____________________________________________________________________________________________________________________");
		
		while(current != null)
		{
			current.data.displayState();
			current = current.nextLink;
		}
	}
	
}
