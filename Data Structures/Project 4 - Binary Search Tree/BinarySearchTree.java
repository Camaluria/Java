
/** 
 * Binary Search Tree class
 * Implements a Binary Search Tree that allows for the addition, removal, and finding of Nodes
 * @author Cameron Alexander - N01242446
 * @version 1.17 (4/03/2017)
 *  
 */
public class BinarySearchTree 
{
	public Node root; //create root of tree
	
	/**
	 * Default Constructor. Sets root node to null
	 */
	public BinarySearchTree()
	{
		root = null;
	}
	
	/**
	 * Find Nodes in Binary Search Tree
	 * @param name state name of Node to find
	 * @return number of nodes visited before the specificed node was found or not found
	 */
	public int find( String name)
	{
		int counter = 0;
		Node current = root;
		while(!current.stateName.equals(name)) // traverse BST starting from root until state name is found or null node encountered
		{
			if( name.compareTo(current.stateName) < 0 )
			{
				current = current.leftChild;
				counter++;
			}
			else 
			{
				current = current.rightChild;
				counter++;
			}
			if(current == null)
			{
				System.out.println( name +" Not Found. Error Code: -1");
				return counter;			
			}
		}
		current.printNode();
		return counter;
	}
	
	/**
	 * Insert Node into Binary Search Tree
	 * @param name name of state
	 * @param pop population of state
	 */
	public void insert(String name, int pop)
	{
		Node newNode = new Node(name, pop);
		
		if(root == null) //if tree is empty make Node the root
		{
			root = newNode;
		}
		else //otherwise add tree to BST based on state name
		{
			Node current = root;
			Node parent;
			
			while(true)
			{
				parent = current;
				if(name.compareTo(current.stateName) < 0)
				{
					current = current.leftChild;
					if(current == null)
					{
						parent.leftChild = newNode;
						return;
					}
				}
				else
				{
					current = current.rightChild;
					if (current == null)
					{
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	} // end insert
	
	/**
	 * Delete Node from Binary Search Tree and restructure tree for all Nodes are connected
	 * @param state
	 * @return
	 */
	public boolean delete(String state)
	{
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true; //determine if node to be deleted is a left child node of a parent node
		
		while(!current.stateName.equals(state)) //traverse tree until state name is found or null node encountered
		{
			parent = current;
			if(state.compareTo(current.stateName) < 0) 
			{
				isLeftChild = true;
				current = current.leftChild;
			}
			else
			{
				isLeftChild = false;
				current = current.rightChild;
			}
			if(current == null) //state not found
			{
				System.out.println(state + " not found!");
				return false;
			}
		}
		
		if(current.leftChild == null && current.rightChild == null) //if tree is empty
		{
			if(current == root)
			{
				root = null;
			}
			else if(isLeftChild)
			{
				parent.leftChild = null;
			}
			else
			{
				parent.rightChild = null;
			}
		}
		
		else if(current.rightChild == null) //if current Node has no right child Node
		{
			if(current == root)
			{
				root = current.leftChild;
			}
			else if(isLeftChild)
			{
				parent.leftChild = current.leftChild;
			}
			else
			{
				parent.rightChild = current.leftChild;
			}	
		}
		
		else if(current.leftChild == null) //if current Node has no right child Node
		{
			if(current == root)
			{
				root = current.rightChild;
			}
			else if(isLeftChild)
			{
				parent.leftChild = current.rightChild;
			}
			else
			{
				parent.rightChild = current.rightChild;
			}	
		}
		else 
		{
			Node successor = getSuccessor(current);
			
			if(current == root)
			{
				root = successor;
			}
			else if(isLeftChild)
			{
				parent.leftChild = successor;
			}
			else
			{
				parent.rightChild = current.leftChild;
			}	
			successor.leftChild = current.leftChild;
		}
		System.out.println("Node Deleted: " + state);
		return true;			
	}
	
	/**
	 * Print BST inorder recursively
	 * @param localRoot Node to print information on
	 */
	public void printInorder(Node localRoot)
	{
		
		if(localRoot != null)
		{
			printInorder(localRoot.leftChild);
			localRoot.printNode();
			System.out.print (" ");
			printInorder(localRoot.rightChild);		
		}
	}
	/**
	 * Print BST preorder recursively
	 * @param localRoot Node to print information on
	 */
	public void printPreorder(Node localRoot)
	{
		if(localRoot != null)
		{
			localRoot.printNode();
			System.out.print (" ");
			printPreorder(localRoot.leftChild);
			printPreorder(localRoot.rightChild);		
		}
	}
	/**
	 * Print BST postorder recursively
	 * @param localRoot Node to print information on
	 */
	public void printPostOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			printPostOrder(localRoot.leftChild);
			printPostOrder(localRoot.rightChild);
			localRoot.printNode();
			System.out.print (" ");
		}
	}
	
	/**
	 * Traverse Binary Search Tree to find node with lowest state population
	 * @param localRoot Node to search
	 */
	public void traverseMin(Node localRoot)
	{
		if(localRoot != null && localRoot.statePopulation != 0)
		{
			//System.out.println(localRoot.stateName);
			if (Population.minPop > localRoot.statePopulation  )
			{
				Population.minPop = localRoot.statePopulation;
				Population.name = localRoot.stateName;
			}
			traverseMin(localRoot.leftChild);
			traverseMin(localRoot.rightChild);
			}
		}
	
	/**
	 * Traverse Binary Search Tree to find node with highest state population
	 * @param localRoot Node to search
	 */
	public void traverseMax(Node localRoot)
	{
		if(localRoot != null)
			
		{
			if (Population.maxPop < localRoot.statePopulation)
			{
				Population.maxPop = localRoot.statePopulation;
				Population.name = localRoot.stateName;
			}
			traverseMax(localRoot.leftChild);
			traverseMax(localRoot.rightChild);
		}
	}
	
	/**
	 * Find Node with lowest state population and print Node information
	 */
	public void printMinimum()
	{
		
		traverseMin(root);
		System.out.println("\n\n*******************************************");
		System.out.println("Printing Minimum State\n");
		System.out.println(Population.name + "    " + Population.minPop );
		System.out.println("*******************************************\n\n");
	}
	/**
	 * Find Node with lowest state population and print Node information
	 */
	public void printMaximum()
	{
		traverseMax(root);
		
		System.out.println("\n\n*******************************************");
		System.out.println("Printing Maximum State\n");
		System.out.println(Population.name + "    " + Population.maxPop );
		System.out.println("*******************************************\n\n");
	}
	

	
	/**
	 * Find successor Node in Binary Search Tree of Node to be deleted
	 * @param delNode Node to be deleted
	 * @return node that is successor to Node to be deleted
	 */
	private Node getSuccessor(Node delNode) 
	{
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; // go to right child
		
		while(current != null) // until no more
		{ // left children,
		successorParent = successor;
		successor = current;
		current = current.leftChild; // go to left child
		}
		
		// if successor not
		if(successor != delNode.rightChild) // right child,
		{ // make connections
		successorParent.leftChild = successor.rightChild;
		successor.rightChild = delNode.rightChild;
		}
		return successor;
	}



	/**
	 * Node Class
	 * Node Objects to be stored in Binary Search Tree
	 * @author Cameron Alexander
	 * @version 1.0 (4/1/2017)
	 *
	 */
	private class Node 
	{    
		String stateName;    
		int statePopulation;
		Node leftChild;
		Node rightChild;
		
	/**
	 * Default Constructor
	 * @param state name of state
	 * @param population population of state
	 */
	public Node(String state, int population) 
	{       
		stateName = state;
		statePopulation = population;    
	}    
	
	/**
	 * Print information stored in Node
	 */
	public void printNode() 
	{       
		System.out.printf("%-25s%,10d\n", stateName, statePopulation);    
		}
	}
	
	/**
	 * Population Class
	 * Population Object to store size of population while traversing tree recursively. Used in traverseMin and traverseMax
	 * @author PC
	 *
	 */
	public static class Population 
	{
		public static int maxPop;
		public static int minPop = 99999999; 
		public static String name;
		
		
	}
}


