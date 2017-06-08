/* Author: Debkanya Mazumder <dmazum2@uic.edu> 
 * 
 */
package debkanyamazumder;
import java.util.ArrayList;
import java.util.List;

public class Command {
	//Class to store an object of all the 8 commands 
	//This class object has been utilized as an array in Command_List.Java class 
	
	//Command number
	public int cno;  
	
	//Command description 
	public String description;
		
	//Stores hot and cold responses 
	public String hot_response; 
	public String cold_response; 
	
	
	//Prerequisite condition is when a command is to be preceded by another command 
	//To store prerequisites for the current command (if any)
	//Stored as a list since there maybe multiple prerequisites 
	public List<Integer> prerequistes = new ArrayList<Integer>(); 
	
	
	//boolean  check is to keep a tab of whether this command has been used by user 
	boolean check = false; 

}
