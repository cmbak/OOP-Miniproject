package OOP.ec22697.MP;// File Candidate_ec221166.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec221166 extends Candidate {
    public static int InputInt (String message)
    {
        Scanner scanner = new Scanner(System.in);
        int answer;
        System.out.println(message);//Print message that passed through to ask the user
        answer = scanner.nextInt();//Get the user input
        return answer;//Return the inputted data
    }
    public static String InputString (String message)
    {
        Scanner scanner = new Scanner(System.in);
        String answer;
        System.out.println(message);//Print message that passed through to ask the user
        answer = scanner.nextLine();//Get the user input
        return answer;//Return the inputted data
    }	
    public static void main(String[]args)
    {
        String choice;
        int count = 0;
    	Candidate[] AllCandidates = A3.getCandidateArray();
    	Candidate[] CandidatesList = new Candidate[557];
    	String username;
    	boolean Exit = false;
    	while (!Exit)
    	{

    	    System.out.println("Candidates are");
    	    if (count == 0)
    	    {
    	        System.out.println("none");
    	    }
    	    else
    	    {
    	        for(int i = 0; i < count;i++)
    	        {
    	            System.out.println(CandidatesList[i].getName());
    	        }   
    	    }
    	    System.out.println("= Running Repeated election =");
    	    choice = InputString("Would you like to a) add a specific cadidate b) add a candidate at random c)run the election? d) exit");
    	    if (choice.equals("a"))
    	    {
    	    	username = InputString("Enter username");
    	    	for(int x = 0; x < AllCandidates.length;x++)
    	        {
    	            if (AllCandidates[x].un.equals(username))
    	            {
    	                CandidatesList[count] = AllCandidates[x];
    	            }
    	        }
    	        count += 1;  
    	    }
    	   else if (choice.equals("b"))
    	   {
    	       Random ran = new Random();
    	       int n = ran.nextInt(AllCandidates.length);
    	       CandidatesList[count] = AllCandidates[n];
    	       count += 1;
    	   }
    	   else if (choice.equals("c"))
    	   {
    	   String counter = InputString("Who should count the votes");
    	   int electionamount = InputInt("How many times shall we run the election");
    	   for (int y = 0;y < AllCandidates.length;y++)
    	   {
    	  	if (AllCandidates[y].un.equals(counter))
    	            {
    	            	Candidate [] FinalCandidates = new Candidate[count];
    	            	for (int z = 0;z<count;z++)
    	            	{
    	            	    FinalCandidates[z] = CandidatesList[z];
    	            	}
    	                Candidate winner = AllCandidates[y].selectWinner(FinalCandidates);
    	                System.out.println("The most common winner is "+winner.getName());
    	                Exit = true;
    	            }
    	   }

    	   }
    	   if (choice.equals("d"))
    	    {
    	        Exit = true;
    	    }

    	}



    }
    public String getName() {
        return "Harry";
    }
    
    public String getSlogan() {
        return "Green Energy";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) 
            return new Candidate_ec221166();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Green Energy"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Harry"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec221166();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
