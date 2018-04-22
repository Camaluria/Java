import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/** 
 * COP 3538: Project 5 - Add States to Hash Table<p><p>
 * Imports state information from user-provided CSV file and allows for the sorting and 
 * displaying of the state information. Implements Hash Table
 * @author Cameron Alexander - N01242446
 * @version 1.07 (4/03/2017)
 *  
 */
public class Project5 {

	/**
	* Main method to run program. Converts CSV file to state objects and sorts using state name
	* @param args command line arguments 
	*/

	public static void main(String[] args) 
	{
		
		System.out.println("COP3538 Project 4 \nAuthor: Cameron Alexander");
		
		HashTable hashTable = new HashTable();
		
		
		Scanner input = new Scanner(System.in);
		System.out.print("\nPlease enter file name: ");
		String fileName = input.nextLine();
		
		//fill hashTable with State Objects based on CSV file
		try {
			fillNodes(hashTable,fileName);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("\n\n*******************************************");
		System.out.println("Displaying Hashtable\n");
		hashTable.displayTable();
		System.out.println("*******************************************\n\n");
		
		deleteNodes(hashTable,"Vermont");
		deleteNodes(hashTable,"California");
		deleteNodes(hashTable,"South Carolina");

		findNodes(hashTable,"Florida");
		findNodes(hashTable,"Rhode Island");
		findNodes(hashTable,"California");
		
		deleteNodes(hashTable,"Kentucky");
		deleteNodes(hashTable,"Minnesota");
		deleteNodes(hashTable,"West Virginia");
		deleteNodes(hashTable,"Ohio");
		
		System.out.println("\n\n*******************************************");
		System.out.println("Displaying Hashtable\n");
		hashTable.displayTable();
		System.out.println("*******************************************\n\n");
		
		hashTable.printFreeAndCollisions();

		System.exit(0);
	}
	




/**
 * Remove Node from Hash Table
 * @param hashTable tree to search for Node
 * @param name Node key to search for
 */
private static void deleteNodes(HashTable hashTable, String name) 
{
	int hashVal = HashTable.hash(name);
	hashTable.delete(name);
}

/**
 * Find Node in Hash Table
 * @param hashTable tree to search for Node
 * @param name Node key to search for
 */
private static void findNodes(HashTable hashTable, String name)
{
	double value = hashTable.find(name);
	
	System.out.println("\n\n*******************************************");
	System.out.println("Searching for Node: " + name);
	if(value > 0)
	{
		System.out.println("Population: " + value);
	}
	else
	{
		System.out.println("State Not Found!");
	}
	System.out.println("");
	System.out.println("*******************************************\n\n");
}


	/**
	* Prompts user for CSV file containing State information to be converted
	* into State Objects and put into states array.
	* @param states State object array to be filled with State Objects
	* @throws IOException
	*/
private static void fillNodes(HashTable hashTable, String file) throws IOException
{
		int counter = 0;
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
				
				
				hashTable.insert(sta.getStateName(), sta.getStatePop());
				
				counter++;
			}
		}
		info.close();
		scan.close();
		
		System.out.println("There were " + counter +" records read into the hash table.");
}
	
}
