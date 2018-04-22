/** 
 * COP 3538: Project 1 - Array Searching and Sorting Algorithms<p><p>
 * Imports state information from user-provided CSV file and allows for the sorting and 
 * searching of the state information. Implements binary and sequential searching algorithms
 * as well as insertion, bubble, and selection sorting for states.
 * @author Cameron Alexander - 
 * @version 1.56 (2/3/2017)
 *  
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Project1 
{

	public static void main(String[] args) 
	{
		String filename;
		State[] states = new State[50];
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter filepath:  ");
		filename = input.next();
		input.nextLine();
		
		try {
			fillArray(states,filename);
		} catch (IOException e) {
			System.out.println("Unable to load file!");
			e.printStackTrace();
			System.exit(0);
		}
		menu(states);	
	}
	
	/**
	* Displays Menu Options to users and calls the appropriate methods
	* that user requests. Only allows 4 attempts before ending program
	* @param states states array for user to search and sort
	*/
	private static void menu(State[] states) 
	{
		int choice;
		int incorrectAttempts = 0;
		
		while(true)
		{
			displayMenuOptions();
			
			if(incorrectAttempts >= 3)
			{
				System.out.println("Thanks For Using This Program!");
				System.exit(0);
			}
			else
			{
				while(incorrectAttempts <= 3)
				{
					Scanner input = new Scanner(System.in);
					while(!input.hasNextInt())
					{
						if (incorrectAttempts >= 3)
						{
							System.out.println("Thanks For Using This Program!");
							System.exit(0);
						}
						
						input.nextLine();
						System.out.print("Non Menu Number Entered. Please enter a number between 1-6: ");
						incorrectAttempts++;
					}
					choice = input.nextInt();
					
					switch(choice)
					{	
					case 1: displayStateReport(states);	
							displayMenuOptions();
							break;
					case 2: sortByStateName(states);
							System.out.println("\nArray is now sorted by State Name");
							displayMenuOptions();
							break;
					case 3: sortByPopulation(states);
							System.out.println("Array is now sorted by State Population");		
							displayMenuOptions();
							break;
					case 4: sortByRegion(states);
							System.out.println("Array is now sorted by State Region Type");	
							displayMenuOptions();
							break;
					case 5: printStateInfo(states);
							displayMenuOptions();
							break;
					case 6: System.out.println("Thanks For Using This Program!");
							displayMenuOptions();
							System.exit(0);
							break;
					default: System.out.print("Non Menu Number Entered. Please enter a number between 1 and 6: ");
							incorrectAttempts++;
					}
			}
			
		}
	}
}	
	
	/**
	* Searches array of State Objects to find a user specified state name
	* and print the info about the given state. Implements the Binary
	* Search Algorithm in order to locate state objection location in state
	* @param states State Array to be searched
	* @param key user input for state they wanted to search for
	*/
	private static void binarySearch(State[] states, String key)
	{
		boolean found = false;
		int low = 0;
		int high = states.length-1;
		
		while(low <= high)
		{
			int middle = (low + high)/2;
			if(states[middle].getStateName().equals(key))
			{
				states[middle].printStateReport();
				found = true;
				break;
			}
			else if(states[middle].getStateName().compareTo(key) < 0)
			{
				low = middle + 1;
			}
			else if(states[middle].getStateName().compareTo(key) > 0)
			{
				high = middle - 1;
			}
		}
		
		if(found != true)
		{
				System.out.println(key + " Not Found!");
		}
		
	}
	
	/**
	* Determine which Searching Algorithm to use depending on if 
	* the State array is sorted by state name
	* @param states State Array to be searched
	* @return int value that determines which Search Algorithm to use 
	*/
	private static int  determineSearchType(State[] states)
	{
		if(states[0].getStateName().equals("Alabama"))
		{
			System.out.printf("\n%s\n\n", "Binary Search Utilized");
			return 1;
		}
		else
		{
			System.out.printf("\n%s\n\n", "Sequential Search Utilized");
			return 0;
		}
	}
	
	/**
	* Display Menu Options to User for interaction with states array 
	*
	*/
	private static void displayMenuOptions()
	{
		System.out.println("\n____________________________");
		System.out.println("1. Print a State Report");
		System.out.println("2. Sort by State Name");
		System.out.println("3. Sort by Population");
		System.out.println("4. Sort by Region");
		System.out.println("5. Find and Print State");
		System.out.println("6. Quit");
		System.out.print("Enter a choice: ");
	}
	/**
	* Display State Information about all State Objects in states array.
	* @param states State object array to print state information from
	*/
	private static void displayStateReport(State[] states) 
	{
		System.out.printf("\n%-20s%-20s%-15s%-10s%18s%25s\n","State Name", "Capital City", "State Abbr", "State Population", "Region", "US House Seats"); 
		System.out.println("_____________________________________________________________________________________________________________________");
		for(int i = 0; i < states.length; i++)
		{
			states[i].displayState();
		}
		
	}
	
	/**
	* Prompts user for CSV file containing State information to be converted
	* into State Objects and put into states array.
	* @param states State object array to be filled with State Objects
	* @param file  filepath to CSV file to be used to fill states
	* @throws IOException
	*/
	private static void fillArray(State[] states, String file) throws IOException
	{
		int index = 0; //variable to store current index location in array to be filled

		Scanner info = new Scanner(new File(file)); //scanner to load file to be read
		Scanner scan = new Scanner(info.nextLine()); //scanner to read file line by line
		
		//read file contents and create state object
		while (info.hasNextLine())
		{
			scan = new Scanner(info.nextLine()); //removes the csv header line
			scan.useDelimiter(",");
			while (scan.hasNext())
			{
				String name = scan.next();
				String cap = scan.next();
				String abbr = scan.next();
				int pop = scan.nextInt();
				String region = scan.next();
				int seats = scan.nextInt();
				
				State sta = new State(name,cap,abbr,pop,region,seats); //create state object
				states[index] = sta; //add object to array
			}
			index++;
		}
		System.out.println("There were " + index + " state records read.");
		info.close();
		scan.close();
	}
	
	/**
	* Request state name from user and determine the Search Algorithm to use
	* @param states State Object array to be 
	*/
	private static void printStateInfo(State[] states)
	{
		String key; //variable to store state name that user enters
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter State Name: ");
		key = input.next();
		
		int searchType = determineSearchType(states); //determine which Searching Algorithm to use
		if(searchType == 1)
		{
			binarySearch(states,key); // search using binary algorithm and display results
		}
		else
		{
			sequentialSearch(states,key);//search using binary algorithm and display results
		}
	}
	
	/**
	* Search array of state objects in order and print state information if
	* found. Otherwise let user know that element was not found.
	* @param states
	* @param key
	*/
	private static void sequentialSearch(State[] states,String key)
	{
		boolean found = false; //store whether key was found
		
		for(int i = 0; i < states.length; i++)
		{
			if(states[i].getStateName().equals(key))
			{
				states[i].printStateReport();
				found = true;
			}
		}
		//if key was not found let user know
		if(found != true)
		{
				System.out.println(key + " Not Found!");
		}
	}		
   
    /**
    * Sort State Array based on population size using Selection Sort
    * @param states State array to be sorted
    * 
    */
    private static void sortByPopulation(State[] states) 
	{
		State temp = null; //Temporary state object for swapping objects around in states array
		int index; //variable to store current array index location being reviewed
		
		//selection sort
		for(int i = 0; i < states.length-1; i++)
		{ 
			index = i;
			for(int j = i + 1; j < states.length; j++)
			{
				if(states[index].getStatePop() > states[j].getStatePop())
				{
					index = j;				
				}
			}
			if(index != i)
			{
				temp = states[i];
				states[i] = states[index];
				states[index] = temp; 
			}
		}
	}
	
	/**
	* Sort State Array based on region using Insertion Sort 
	* @param states State array to be sorted
	*/
	
	private static void sortByRegion(State[] states)
	{
		State temp = null; //Temporary state object for swapping objects around in states array
		
		//Insertion Sort
		for(int i = 1; i < states.length; i++){
			for(int j = i; j > 0; j--){
				if(states[j].getRegion().compareTo(states[j-1].getRegion()) > 0)
				{
					temp = states[j];
					states[j] = states[j-1];
					states[j-1] = temp;
				}
			}
		}
	}
	
	/**
	* Sort State Array based on state name using Bubble Sort
	* @param states State array to be sorted
	*/
	private static void sortByStateName(State[] states) 
	{
		State temp = null; //Temporary state object for swapping objects around in states array
		
		//bubble sort
		for(int i = 0; i < states.length; i++)
		{
			for(int j = 1; j < states.length-1;j++)
			{
				if( State.compare(states[j-1], states[j]) > 0)
				{
					temp = states[j-1];
					states[j-1] = states[j];
					states[j] = temp; 
				}
			}
		}		
	}
}
