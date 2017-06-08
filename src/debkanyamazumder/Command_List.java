/* Author - Debkanya Mazumder <dmazum2@uic.edu> 
 * 
 */

//main method below 

package debkanyamazumder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Command_List {
	
	//command list variable 
	static Command[] c = new Command[8]; 
	
	
	static void initialize() { 	
		//Initalizing command list 
		for(int i = 0; i < 8; i++) {
			c[i] = new Command(); 
			c[i].cno = i+1; 
		}
		
		for(int i = 0; i < 7; i++) {
			//Pyjamas must be taken off before anything can be put on 
			c[i].prerequistes.add(7);
		}
		c[0].description = "Put on footwear";
		c[0].hot_response = "sandals";
		c[0].cold_response = "boots";
		//socks must be put before shoes 
		c[0].prerequistes.add(2);
		//pants must be put before shoes
		c[0].prerequistes.add(5);
		
		
		c[1].description = "Put on headwear";
		c[1].hot_response = "sun visor"; 
		c[1].cold_response = "hat";
		//shirt must be put on before headwear 
		c[1].prerequistes.add(3);
		
		c[2].description = "Put on socks";
		c[2].hot_response = "fail";
		c[2].cold_response= "socks";
		
		c[3].description = "Put on shirt";
		c[3].hot_response = "t-shirt";
		c[3].cold_response = "shirt";
		
		c[4].description = "Put on jacket";
		c[4].hot_response = "fail";
		c[4].cold_response = "jacket";
		//shirt must be put on before jacket 
		c[4].prerequistes.add(3);
		
		c[5].description = "Put on pants";
		c[5].hot_response = "shorts";
		c[5].cold_response = "pants"; 
		
		c[6].description = "Leave house"; 
		c[6].hot_response = "leaving house"; 
		c[6].cold_response = "leaving house"; 
		
		c[7].description = "Take off pyajamas"; 
		c[7].hot_response = "Removing pjs";
		c[7].cold_response = "Removing pjs";
	}
	
	static boolean check_LeavingHouse(String input)
	{
		//Method for command number 7 "leaving house" 
		
		/* You cannot leave the house until all items of clothing are on (except socks and a jacket when
		 * it’s hot) 
		 */
		
		if(input.equals("HOT"))
		{
			for(int i = 0; i < 8; i++)
			{
				//if it his hot socks and jacket can be ignored as shown below 
				if(i == 2 || i == 4)
					continue;
				
				//other conditions are checked 
				if(!c[i].check)
					return false; 
			}
		}
		else if(input.equals("COLD"))
		{
			for(int i = 0; i < 8; i++)
			{
				//Testing 
				//System.out.println("i - " + i + " check - "+ c[i].check);
				
				if(!c[i].check)
					return false; 
			}
		}
		return true;
	}
	
	static boolean check_Prerequistes(int i)
	{
		//This function checks prerequisites for every command i 
		//A prerequisite condition is when a command is supposed to be preceded by another command
		//If the pre-requisite condition is not true then the command fails   
		
		if(!c[i].prerequistes.isEmpty())
		{
			for(int elem: c[i].prerequistes)
			{
				//System.out.println(c[i].prerequistes.size());
				//System.out.println("i:" + c[i].description);
				//System.out.println("elem:"+c[elem].description+ " check:" + c[elem].check);
				if(!c[elem].check)
					return false; 
			}
		}
		return true; 
	}
	
	public static void main(String[] args) { 
		
		//to initialize the command structure 
		initialize();
		
		//reads input
		Scanner sc = new Scanner(System.in);
		String inp = sc.next();
		String inp2 = sc.nextLine().trim();
		//System.out.println(inp);
		//System.out.println(inp2);
		String[] arr = inp2.split(",");
		
		
		//stores the command numbers 
		List<Integer> numbers = new ArrayList<Integer>();
		//stores the temperature - hot or cold
		String temperature = inp;
		
		for(int j = 0; j < arr.length; j++)
			numbers.add(Integer.parseInt(arr[j].trim()));
		
		// Testing
		//System.out.println("temperature:"+temperature);
		//System.out.println(numbers); 
	 
		
		if(numbers.get(0) != 8)
		{ //rule - Pajamas must be taken off before anything else can be put on
			System.out.println("fail");
			System.exit(0);
		}
		for(int i: numbers)
		{
			//System.out.println("number:"+(i-1));
			
			if(c[i-1].check)
			{
				System.out.println("fail ");
				System.exit(0);
			}
			c[i-1].check = true;
			if(i == 7)
			{
				//This part checks whether all the items of clothing are on
				//before leaving the house 
				
				if(check_LeavingHouse(temperature) == false) 
				{
					System.out.print("fail\t");
					System.exit(0);
				}
				
			}
			
			//if the temperature is HOT and the input is 1 "put on footwear" then socks can be ignored 
			if((!temperature.equals("HOT")) && (i != 1)) { 
				if(check_Prerequistes(i-1) == false)
				{
					System.out.println("fail\t");
					System.exit(0);
				}
			}
			if(temperature.equals("HOT"))
			{
				
				System.out.print(c[i-1].hot_response+" ");
				if(c[i-1].hot_response.equals("fail"))
					System.exit(0);
			}
			else if(temperature.equals("COLD"))
			{
				System.out.print(c[i-1].cold_response+" ");
			}
			
				
		} 
		
		//closing scanner to avoid leaks 
		sc.close();
		
	}
	
}