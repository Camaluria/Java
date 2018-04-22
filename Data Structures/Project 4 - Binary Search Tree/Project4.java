import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/** 
 * COP 3538: Project 4 - Add States to Binary Search Tree<p><p>
 * Imports state information from user-provided CSV file and allows for the sorting and 
 * displaying of the state information. Implements Binary Search Tree
 * @author Cameron Alexander - N01242446
 * @version 1.07 (4/03/2017)
 *  
 */
public class Project4 {

	/**
	* Main method to run program. Converts CSV file to state objects and sorts using state name
	* @param args command line arguments 
	*/

	public static void main(String[] args) 
	{
		
		System.out.println("COP3538 Project 4 \nAuthor: Cameron Alexander");
		
		BinarySearchTree bst = new BinarySearchTree(); //create BST
		
		Scanner input = new Scanner(System.in);
		System.out.print("\nPlease enter file name: ");
		String fileName = input.nextLine();
		
		//fill BST with State Objects based on CSV file
		try {
			fillNodes(bst,fileName);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("\n\n*******************************************");
		System.out.println("Printing Binary Search Tree InOrder\n");
		bst.printInorder(bst.root);
		System.out.println("*******************************************\n\n");
		
		deleteNodes(bst,"California");
		deleteNodes(bst,"Florida");
		deleteNodes(bst,"Michigan");
		
		System.out.println("\n\n*******************************************");
		System.out.println("Printing Binary Search Tree PreOrder\n");
		bst.printPreorder(bst.root);
		System.out.println("*******************************************\n\n");
	
		findNodes(bst,"Kentucky");
		findNodes(bst,"Rhode Island");
		findNodes(bst,"Florida");
		
		deleteNodes(bst,"Delaware");
		deleteNodes(bst,"Wyoming");
		deleteNodes(bst,"West Virginia");
		deleteNodes(bst,"South Dakota");
		
		System.out.println("\n\n*******************************************");
		System.out.println("Printing Binary Search Tree PostOrder\n");
		bst.printPostOrder(bst.root);
		System.out.println("*******************************************\n\n");
		
		
		bst.printMaximum();
		bst.printMinimum();
		
		System.exit(0);
	}
	




/**
 * Remove Node from Binary Search Tree
 * @param bst tree to search for Node
 * @param name Node key to search for
 */
private static void deleteNodes(BinarySearchTree bst, String name) 
{
	bst.delete(name);
}

/**
 * Find Node in Binary Search Tree
 * @param bst tree to search for Node
 * @param name Node key to search for
 */
private static void findNodes(BinarySearchTree bst, String name)
{
	System.out.println("\n\n*******************************************");
	System.out.println("Searching for Node: " + name);
	int nodeCounter = 0;
	
	nodeCounter = bst.find(name);
	System.out.println("Nodes visted: " + nodeCounter);
	System.out.println("*******************************************\n\n");
}


	/**
	* Prompts user for CSV file containing State information to be converted
	* into State Objects and put into states array.
	* @param states State object array to be filled with State Objects
	* @throws IOException
	*/
private static void fillNodes(BinarySearchTree bst, String file) throws IOException
{
		
		Scanner info = new Scanner(new File(file));
		Scanner scan = new Scanner(info.nextLine());
		System.out.println("\nReading Contents of : " + file + "\n");
		
		while (info.hasNextLine())
		{
			
			scan = new Scanner(info.nextLine());
			scan.useDelimiter(",");
			while (scan.hasNext())
			{
				
				String name = scan.next();
				String cap = scan.next();
				String abbr = scan.next();
				int pop = scan.nextInt();
				String region = scan.next();
				int seats = scan.nextInt();
				
				State sta = new State(name,cap,abbr,pop,region,seats);
				
				bst.insert(sta.getStateName(), sta.getStatePop());
			}
		}
		info.close();
		scan.close();
}
	
}
