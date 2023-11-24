package OOP.ec22697.MP;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Candidate_ec22519 extends Candidate {

    // A3 stuff
    public static void main(String[] args)
    {
    	Candidate[] all_given_Candidates = A3.getCandidateArray();
    	int amount = all_given_Candidates.length;

        ArrayList<Candidate> list_Of_Candidates = new ArrayList<Candidate>();
        int amount_in_list = list_Of_Candidates.size();
        

    	boolean inProgram = true;

    	while(inProgram)
    	{

    	    int first_selection = Int_Input("\n What would you like to do? \n 1) See all candidates \n 2) Add candidate \n 3) Run election \n 4) Clear/Remove candidates \n 5) Exit");

    	    if(first_selection == 1) // See all candidates
    	    {
    	    	if(list_Of_Candidates.size() == 0)
    	    	{
    	    	    print("There are currently no candidates! Please add some candidates.");
    	    	}
    	    	else
    	    	{   
    	    	    for(int i = 0; i < list_Of_Candidates.size(); i++)
    	    	    {
    	    	        print(list_Of_Candidates.get(i).getName());
    	    	    }
    	    	}
    	    }
    	    else if(first_selection == 2) // Add candidates
    	    {
    	    	int choice_1 = Int_Input("\n What would you like to do? \n 1) Add a random candidate \n 2) Add a specific candidate");
    	    	
    	    	if(choice_1 == 1) // Random candidate
    	    	{
    	    	     Random rand = new Random();
    	    	     int x = rand.nextInt(amount);
    	    	     boolean inList = false;

    	    	     for(int i = 0; i < list_Of_Candidates.size(); i++)
    	    	     {
    	    	         if(all_given_Candidates[x] == list_Of_Candidates.get(i))
    	    	    	 {
    	    	    	     print("This candidate is already in the list!");
    	    	    	     inList = true;
    	    	    	 }
    	    	    }
    	    	    
    	    	    if(!inList)
    	    	    {
    	    	        list_Of_Candidates.add(all_given_Candidates[x]);
    	    	        print("Candidate: " + all_given_Candidates[x].getName() + " has been added");
    	    	    }
    	    	 }
    	    	 else if(choice_1 == 2) // Specific candidate
    	    	 {
    	    	      String userName = String_Input("Please enter an username (example -> ec12345)");
    	    	      boolean alreadyInList = false;
    	    	      int index = 0;
    	    	      
    	    	      for(int i = 0; i < amount; i++)
    	    	      {
    	    	          if(userName.equals(all_given_Candidates[i].un))
    	    	    	  {
    	    	    	      index = i;
    	    	    	  }
    	    	     }
    	    	     
    	    	     for(int z = 0; z < list_Of_Candidates.size(); z++)
    	    	     {
    	    	         if(all_given_Candidates[index].un == list_Of_Candidates.get(z).un)
    	    	    	 {
    	    	    	     alreadyInList = true;
    	    	    	 }
    	    	    }
    	    	    
    	    	    if(alreadyInList)
    	    	    {
    	    	        print("This candidate is already in the list!");
    	    	    }
    	    	    else
    	    	    {
    	    	    	 list_Of_Candidates.add(all_given_Candidates[index]);
    	    	    	 print("Candidate: " + all_given_Candidates[index].getName() + " has been added");    	    	    
    	    	    }
    	    	 }
    	    	 else
    	    	 {
    	    	       print("This input --> (" + choice_1 + ") is invalid! please enter an valid input");
    	    	 }
    	    }
    	    else if(first_selection == 3) // Run election
    	    {
    	    	if(list_Of_Candidates.size() == 0)
    	    	{
    	    	    print("There are currently no candidates so an election cannot be run.");
    	    	}
    	    	else
    	    	{
    	    	    int choice_3 = Int_Input("\n What would you like to do? \n 1) Choose someone to count the votes \n 2) Find the the most voted candidate");
    	    	    
    	    	    if(choice_3 == 1)
    	    	    {
    	    	        String userName = String_Input("Please enter an username (example -> ec12345)");
    	    	        
    	    	        int numberOfTimes = Int_Input("How many time would you like to run the election?");
    	    	        Candidate winner = list_Of_Candidates.get(0);
    	    	       
    	    	        for(int i = 0; i < amount; i++)
    	    	        {
    	    	            if(userName.equals(all_given_Candidates[i].un))
    	    	    	    {
    	    	    	       winner = all_given_Candidates[i].selectWinner(list_Of_Candidates.toArray(new Candidate[0]));
    	    	    	    }
    	    	        }
    	    	        
    	    	        print("The winner according to " + userName + " is " + winner.getName());
    	    	    }
    	    	    else if(choice_3 == 2)
    	    	    {
    	    	        boolean partOfChosenCandidates = false;
    	    	        Candidate realWinner = list_Of_Candidates.get(0);
    	    	        
    	    	        for(int i = 0; i < amount; i++)
    	    	        {
    	    	            if((all_given_Candidates[i].un).equals("ec22519"))
    	    	    	    {
    	    	    	        realWinner = all_given_Candidates[i].selectWinner(list_Of_Candidates.toArray(new Candidate[0]));
    	    	    	    }
    	    	        }
    	    	        
    	    	        for(int i = 0; i < list_Of_Candidates.size(); i++)
    	    	        {
    	    	             if(realWinner == list_Of_Candidates.get(i))
    	    	    	     {
    	    	    	         partOfChosenCandidates = true;
    	    	    	     }
    	    	        }
    	    	        
    	    	        if(partOfChosenCandidates)
    	    	        {
    	    	            print("The winner is part of the list of Candidates given! The winner is " + realWinner.getName());
    	    	        }
    	    	        else
    	    	        {
    	    	            print("The winner is NOT part of the list of Candidates given! The winner is " + realWinner.getName());
    	    	        }
    	    	    }
    	    	    else
    	    	    {
    	    	    	print("This input --> (" + choice_3 + ") is invalid! please enter an valid input");
    	    	    }
    	    	}
    	    }
    	    else if(first_selection == 4) // Remove/clear candidates
    	    {
	        int choice_2 = Int_Input("\n What would you like to do? \n 1) Clear all candidates \n 2) Remove a specific candidate");
	        
	        if(choice_2 == 1)
	        {
	            list_Of_Candidates.clear();
	        }
	        else if(choice_2 == 2)
	        {
	            String userName = String_Input("Please enter an username (example -> ec12345)");
	            
	            for(int z = 0; z < list_Of_Candidates.size(); z++)
    	    	    {
    	    	        if(userName.equals(list_Of_Candidates.get(z).un))
    	    	    	{
    	    	    	    print("Candidate: " + list_Of_Candidates.get(z) + " has been removed");
    	    	    	    list_Of_Candidates.remove(z);
    	    	    	}
    	    	   }
	        }
	        else
    	    	 {
    	    	       print("This input --> (" + choice_2 + ") is invalid! please enter an valid input");
    	    	 }
    	    }
    	    else if(first_selection == 5) // Exit
    	    {
    	    	inProgram = false;
    	    }		
    	    else
    	    {
    	    	print("This input --> (" + first_selection + ") is invalid");
    	    }            	
    	}
    }

    public static String String_Input(String a)
    {
    	Scanner stringScan = new Scanner(System.in);
    	print(a);
    	print("");
    	return stringScan.nextLine();
    }

    public static int Int_Input(String a)
    {
    	Scanner stringScan = new Scanner(System.in);
    	print(a);
    	print("");
    	return stringScan.nextInt();
    }

    public static void print(String x)
    {
    	System.out.println(x);
    }


    // A2 stuff

    public String getName() {
        return "Bob";
    }
    
    public String getSlogan() {
        return "Trees are very helpful";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22520();
        
        // First search for Utfur on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("rob") && c.getSlogan().equals("Plant the trees"))
                return c;
        
        return new Candidate_ec22519();
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22520();
        
        Candidate currentWinner = new Candidate_ec22519();
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
}
