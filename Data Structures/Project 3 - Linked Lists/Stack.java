

/**
 * Stack Class for State Objects 
 * @author Cameron Alexander
 * @version 1.5 (3/12/2017)
 */
public class Stack 
{

private LinkList stackArray = new LinkList(); //create Link List Array


/**
 * Default constructor to create Stack Object
 */
public Stack() 
{
stackArray = new LinkList();
}

/**
 * Add State Objects to Stack array
 * @param sta state object to be added to array
 */
public void push(State sta) 
{
stackArray.insertFirst(sta); 
}

/**
 * Remove top State Object from Stack array
 * @return return State Object at top of Stack
 */
public State pop() 
{ 
State temp = stackArray.getFirst().data;
stackArray.deleteFirst();
return temp;
}

/**
 * Displays all items in the stack array
 */
public void printStack()
{
	
	stackArray.displayStack();
	
	System.out.println("_____________________________________________________________________________________________________________________\n\n");

}

/**
 * Checks if Stack Array has no items in it
 * @return true if stack is empty
 */
public boolean isEmpty() 
{
return (stackArray.isEmpty());
}

} 