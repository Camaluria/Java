
/**
 * COP 3538: Project 5
 * Hash Table Class
 * @author Cameron Alexander n01242446
 * 
 */
public class HashTable 
{
	private LinkedList[] hashArray = new LinkedList[101]; //double ended linked list
	
	/**
	 * Default Constructor
	 */
	public HashTable()
	{
		
	}
	
	/**
	 * Delete State Name from Hash Table
	 * @param key state name to search for
	 */
	public void delete(String key) // delete a link
	{
		
	int hashVal = hash(key); // hash the key
	hashArray[hashVal].delete(key); // delete link
	
	} // end delete()
	
	/**
	 * Display Hash Table
	 */
	public void displayTable()
	{
		
		for(int j=0; j < 101; j++) // for each cell,
		{
			if (hashArray[j] == null)
			{
				System.out.print(j + ".    Empty\n");
			}
			else
			{
			System.out.print(j + ".    "); // display cell number
			hashArray[j].displayList(); // display list
			}
		}
	}
	
    /**
     * Find State Name in Hash Table
     * @param key state name to find
     * @return population if state is found. Otherwise -1
     */
    public double find(String key) // find link
	{
		int hashVal = hash(key); // hash the key
		double value = hashArray[hashVal].find(key); // get link
		return value; 
	}
		
    /**
     * Convert State Name to Hash Value
     * @param key state name
     * @return hash value
     */
    public static int hash(String key) // hash function
    {
    	int total = 0;
    	
    	for(int i = 0; i < key.length(); i++)
    	{
    		total += (int)key.charAt(i);
    	}
    	
    	int hash = (total % 101);
 
    	return hash;
    }
    
    /**
     * Add State to Hash Table
     * @param state state name 
     * @param population state population
     */
    public void insert(String state, int population)
	{
		Node newNode = new Node(state, population);
		int hashVal = hash(state);
		
		if(hashArray[hashVal] == null)
		{
			hashArray[hashVal] = new LinkedList();
			hashArray[hashVal].insert(newNode);
		}
		else
		{
			hashArray[hashVal].insert(newNode);
		}
	}
		
    /**
     * Count Number of Empty Spaces and Collisions 
     * and Display
     */
    public void printFreeAndCollisions()
    {
     int emptyCounter = 0;
     int collisionCounter = 0;
     
     for(int i = 0; i < 101; i++)
     {
    	 if (hashArray[i] == null)
    	 {
    		 emptyCounter++;
    	 }
    	 else
    	 {
    		 for (Node node = hashArray[i].first; node != null; node = node.nextNode)
    		 {
    			 collisionCounter++;
    		 }
    		 
    	 }
     }
     System.out.println("There are " + emptyCounter + " empty spaces and " + collisionCounter + " Collisions");	
    }
    
    
	/**
	 * Double Ended Linked List
	 * @author n01242446
	 *
	 */
	public class LinkedList
	{
		private Node first;
		private Node last;
		
		/**
		 * Default Constructor
		 */
		public LinkedList()
		{
			first = null;
			last = null;
		}
		
		/**
		 * Delete Node from Linked List
		 * @param key State Name
		 */
		public void delete (String key)
		{
		
			Node previous = null; 
			Node current = first;
			
			int hashVal = HashTable.hash(key);
		
			while( current != null && hashVal != HashTable.hash(current.stateName))
			{ 
				previous = current;
				current = current.nextNode;
			}
			
				if(previous == null) 
				{
					System.out.println(key + " is deleted from Hash Table.");
					first = first.nextNode; 
				}
				else 
				{
					previous.nextNode = current.nextNode; 
				}		
		} 

		/**
		 * Find State by name
		 * @param key state name
		 * @return State population if found. Otherwise -1
		 */
		public double find(String key) 
		{
			Node current = first;
			int hashVal = hash(key);
			
			while(current != null && HashTable.hash(current.stateName) <= hashVal)
			{ 
				int currentVal = hash(current.stateName);
				
				if(currentVal == hashVal)
				{
					return current.statePopulation; 
				}
				
				current = current.nextNode; 
			}
		
			return -1; // didn’t find it
		}
		
		/**
		 * Insert State Node into Hash Table
		 * @param node State to be inserted
		 */
		public void insert (Node node)
		{
			int key = hash(node.stateName);
			Node previous = null;
			Node current = first;
			
			while (current != null && key == HashTable.hash(current.stateName))
			{
				previous = current;
				current = current.nextNode;
			}
			
			if(previous == null)
			{
				first = node;
			}
			else
			{
				previous.nextNode = node;
				node.nextNode = current;
			}
		}
		
		/**
		 * Display Linked List
		 */
		public void displayList()
		{
			
			System.out.println("List: ");
			Node current = first; 
			
			while(current != null) // until end of list,
			{
				current.printNode(); // print data
				current = current.nextNode; // move to next link
			}
			System.out.println(" ");
		}
		
	}
  

class Node
{   
	String stateName;   
	int statePopulation;   
	Node nextNode;   
	
	
	public Node(String state, int population)   
	{      
		stateName = state;      
		statePopulation = population;   
	}   
	
	public void printNode()   
	{      
		System.out.printf("%-25s%,10d\n", stateName, statePopulation);   
	}
}

}

