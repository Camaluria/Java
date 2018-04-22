
/**
 * State Class for the creation of state objects based off of US States
 *  
 * @author Cameron Alexander - N01242446
 * @version 1.56 (2/3/2017)
 */
public class State{
	String stateName;    //variable to store name of state
	String capital;		 //variable to store name of state capital
	String abbreviation; //variable to store state abbreviation
	String region;		 //variable to store region type of state
	int statePop;		 //variable to store state population size
	int usSeats;		 //variable to store number of US Seats that state holds

	
	/**
	 * 
	 * Constructor for the creation of state objects
	 * @param name name of state
	 * @param cap capital of state
	 * @param abbr abbreviation of state
	 * @param pop population of state
	 * @param region state region type
	 * @param seats number of US seats held
	 */
	public State(String name, String cap, String abbr, int pop, String region, int seats){
		this.setState_Name(name);
		this.setCapital(cap);
		this.setAbbreviation(abbr);
		this.setStatePop(pop);
		this.setRegion(region);
		this.setUsSeats(seats);
	}
	
	
	public State() {
		
	}


	/**
	 * Compare two state objects based on name 
	 * @param s1 First state that user wants to compare 
	 * @param s2 Second state that user wants to compare
	 * @return return value of name of String.compareTo for two given states names
	 */
	public static int compare(State s1, State s2){
		int value = 0;
		
		value = s1.getStateName().compareTo(s2.getStateName());
		
		return value;	
	}
	
	/**
	 * Display all information about state in row format
	 */
	public void displayState(){
		
	System.out.printf("%-20s%-20s%-20s%-23d%-25s%d\n", getStateName(), getCapital(), getAbbreviation(), getStatePop(), getRegion(), getUsSeats());
		
	}
	/**
	 * Print information about State Object for easy individual viewing
	 */
	public void printStateReport(){
		
		System.out.printf("State Name: %13s\n", getStateName());
		System.out.printf("Capital: %20s\n", getCapital());
		System.out.printf("State Abbr: %8s\n", getAbbreviation());
		System.out.printf("State Population: %2d\n", getStatePop());
		System.out.printf("Region: %15s\n", getRegion());
		System.out.printf("US House Seats: %4d\n", getUsSeats());
	}
	/**
	 * Get name of state
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * Set name of state
	 * @param stateName the stateName to set
	 */
	public void setState_Name(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * get name of capital
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}
	/**
	 * set name of capital
	 * @param capital the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}
	/**
	 * get state abbreviation name
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}
	/**
	 * set state abbreviation name
	 * @param abbreviation the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	/**
	 * get region type of state
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * set region type of state
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * get state population size
	 * @return the statePop
	 */
	public int getStatePop() {
		return statePop;
	}
	/**
	 * set state population size
	 * @param statePop the state_Pop to set
	 */
	public void setStatePop(int statePop) {
		this.statePop = statePop;
	}
	/**
	 * get number of US seats
	 * @return the USseats
	 */
	public int getUsSeats() {
		return usSeats;
	}
	/**
	 * set number of us seats
	 * @param usSeats the usSeats to set
	 */
	public void setUsSeats(int usSeats) {
		this.usSeats = usSeats;
	}


}	
	
