import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/** 
 * COP 3538: Project 3 - Stacks and Queue Queues as Linked List and Doubly Linked List<p><p>
 * Imports state information from user-provided CSV file and allows for the sorting and 
 * displaying of the state information. Implements Stacks and Queues
 * @author Cameron Alexander - N01242446
 * @version 1.21 (2/25/2017)
 *  
 */
public class Project3 {

	/**
	* Main method to run program. Converts CSV file to state objects and sorts using population size
	* @param args command line arguments 
	*/

	public static void main(String[] args) 
	{
		
		System.out.println("COP3538 Project 3 \nAuthor: Cameron Alexander");
		Stack states = new Stack();
		Queue queue = new Queue();
		
		Scanner input = new Scanner(System.in);
		System.out.print("\nPlease enter file name: ");
		String fileName = input.nextLine();

		//fill Stack with State Objects based on CSV file
		try {
			fillStack(states,fileName);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		states.printStack();
		QueueFill(states, queue); // fill Stack into Queue
		queue.printQueue("Alternating Front and Rear"); // print all items in queue
		deleteStatesinQueue(queue); //delete requested States in Queue
		queue.printQueue("\n\nAfter Removing Requested States");
		QueueFillStack(states, queue); // add items from Queue back into Stack
		System.out.println("\n\n");
		states.printStack(); // print Stack

		System.exit(0);
	}
	




/**
 * Remove State Objects from Queue
 * @param queue Queue to remove State Objects from
 */
private static void deleteStatesinQueue(Queue queue) 
{
		queue.findDelete("Massachusetts");
		queue.findDelete("New Hampshire");
		queue.findDelete("Rhode Island");
		queue.findDelete("Maryland");
		queue.findDelete("New Jersey");
		queue.findDelete("Pennsylvania");
		queue.findDelete("Alabama");
		queue.findDelete("Kentucky");
		queue.findDelete("North Carolina");
}





/**
 * Add States objects from Queue into Stack alternating front to back
 * @param states Stack to be filled
 * @param queue  Queue to be pushed into State Stack
 */
private static void QueueFillStack(Stack states, Queue queue)
{
		int counter = 0;
		State temp = new State();
		
		while(!queue.isEmpty())
		{
			if(counter % 2 == 0)
			{
				temp = queue.removeFirst().data;
				states.push(temp);
			}
			else
			{
				temp = queue.removeLast().data;
				states.push(temp);
			}
			counter++;
		}
	
}


/**
 * Take State Objects from Stack and put into Queue alternating front to end
 * @param states Stack to be used to fill Queue
 * @param queue Queue to be filled from Stack
 *
 **/
private static void QueueFill(Stack states, Queue queue) 
{
	 	int counter = 0; 
	 	System.out.println("***Inserting Stack into Queue***\n\n");
		while(!states.isEmpty())
		{
			State temp = new State();
			temp = states.pop();

			if(counter % 2 == 0)
			{
				queue.insertFront(temp);
			}
			else
			{
				queue.insertEnd(temp);
			}
			counter++;
		}
		
}

	/**
	* Prompts user for CSV file containing State information to be converted
	* into State Objects and put into states array.
	* @param states State object array to be filled with State Objects
	* @throws IOException
	*/
private static void fillStack(Stack states, String file) throws IOException
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
				
				if(sta.getRegion().equals("New England") || sta.getRegion().equals("Middle Atlantic") || sta.getRegion().equals("South"))
				{
					states.push(sta); 
				}
				else
				{
					continue;
				}
			}
		}
		info.close();
		scan.close();
}
	
}
