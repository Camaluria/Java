/** 
 * Link Class for Link Objects
 * Class used to make Link objects to be used for Linked Lists
 * 
 * @author Cameron Alexander N01242446
 * @version 1.01 (3/10/2017)
 *  
 */
public class Link
{
	public State data; //state object to store in link
	public Link nextLink; //reference to link after this link
	public Link prevLink; //reference to link before this link
	
	
	/**
	 * Constructor for Link
	 * @param state State object to store
	 */
	public Link(State state)
	{
		data = state; 
	}
}
