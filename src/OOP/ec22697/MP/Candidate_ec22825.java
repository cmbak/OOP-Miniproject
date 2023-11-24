package OOP.ec22697.MP;// File Candidate_ec22825.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22825 extends Candidate {
   
    public static void main(String args[])
    {
    
        String name;

        Candidate[] allContributions = A3.getCandidateArray();
        
        System.out.println("= Running Repeated Elections =");
        Candidate[] candidates = new Candidate[546];
        int count = 0;
        
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Candidates are");
            
            if (count == 0)
                System.out.println("None");
            else
            {
            
                for (int i = 0; i < count; i++)
                {
                    System.out.println(candidates[i].getName());
                }
            }
            
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            
            char choice = sc.next().charAt(0);
            
            if (choice == 'a')
            {
                System.out.println("Please enter a username");
                Scanner scanner = new Scanner(System.in);
                 name = scanner.nextLine();
                Candidate specificCandidate = A3.getByUsername(name, allContributions);
                if (specificCandidate != null) 
                {
                   candidates[count] = specificCandidate;
                    count = count+1;
                }
                else 
		{
			System.out.println("User not found.");
                }
            }
            
            else if (choice == 'b')
            {
                int len = allContributions.length;
                int r = (int)((Math.random()*(len-1)));
                Candidate c = allContributions[r];
                
                  if (c != null) 
                {
                   candidates[count] = c;
                    count = count+1;
                }
                else 
		{
			System.out.println("User not found.");
		}
                
            }
            
            else if (choice == 'c')
            {
                System.out.println("Please enter a username");
                 name = sc.nextLine();
                     Candidate c = A3.getByUsername(name, allContributions);
                     // Asks the user how many times they would like to run the election
                     System.out.println("How many times should we run the election?");
                     int numTimes = sc.nextInt();
                     // Calls the method to collect the votes
                     elections(candidates, count, numTimes, c, allContributions);
                     // Once completed, exit
                     
                break;
            }
            else
            {
                System.out.println("Invalid choice");
            }
            
                
        }
        
        
    }
    
    public static void elections(Candidate[] candidates, int count, int numTimes, Candidate c, Candidate[] allContributions) {
         Candidate[] cand = new Candidate[count+1];
         for (int i = 0; i < count; i++) {
             cand[i] = candidates[count];
         }
         Candidate[] winCand = new Candidate[numTimes]; //holds all winners of votes
         Candidate[] votes = new Candidate[cand.length];
          for (int i = 0; i<numTimes; i++) {

             for (int j = 0; j < cand.length; j++) {
                 try{
                     votes[i] = cand[j].vote(winCand);
                    throw new NullPointerException();
                 }
                 catch (NullPointerException e){
                    //System.out.println("");
                 }

             }
             try {
                 winCand[i] = c.selectWinner(votes);//gets what the winning candidate this round
                 throw new NullPointerException();
             }
             catch (NullPointerException e){

             }
         }

         while (c == null){
                 c = allContributions[(new Random()).nextInt(allContributions.length)];
         }
         Candidate winningCandidate = c.selectWinner(winCand);//gets winner over all rounds
        while (winningCandidate == null){
            winningCandidate = allContributions[(new Random()).nextInt(allContributions.length)];
        }
         System.out.println("The winner is " + winningCandidate.getName() + " with the slogan " + winningCandidate.getSlogan());

         // Stores instance of the winning candidate
         Candidate winner = c.selectWinner(votes);
         // Outputs the winner to the user
        while (winner == null){
            winner = allContributions[(new Random()).nextInt(allContributions.length)];
        }
         System.out.println("Most common winner is: " + winner.getName());
     }
     



    
    public String getName() {
        return "Amanda"; // user chooses 
    }
    
    public String getSlogan() {
        return "Save Paper!"; // user chooses 
    }
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22981(); // friends candidate ID 
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More investments!")) // friend's slogan 
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Andrew")) // searching by friend's name (candidate name)
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22981(); //friends class /username
        
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
