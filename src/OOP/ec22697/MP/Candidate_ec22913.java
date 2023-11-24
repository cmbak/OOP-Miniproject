package OOP.ec22697.MP;// File Candidate_ec22913.java
//
// Machine generated stub for Assignment 3

import java.util.Scanner;
import java.util.Random;


class Candidate_ec22913 extends Candidate {
    
    public String getName() {
        return "Aghulan";
    }
    
    public String getSlogan() {
        return "Messi=Goat!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22904();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Messi=Goat!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Aghulan"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22904();
        
        // Default to first vote, but this will be over-written.
        Candidate Winner = votes[0];
        
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
                Winner = c;
            }
        }
        
        return Winner;
    }
    
    
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Candidate[] allCandidates = A3.getCandidateArray();
        int listLength = 1;
        Candidate[] candidates = new Candidate[listLength];

        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        String answer = scanner.nextLine();

        if(answer.equals("a"))
        {
                    System.out.println("Please enter a username.");
                    answer = scanner.nextLine();

                    Candidate specific = A3.getByUsername(answer, allCandidates);

                    if(specific != null)
                    {
                        System.out.println("user not found");
                    }
                    else
                    {
                        candidates[0] = specific;
                        listLength = listLength + 1;
                        Candidate[] temp = new Candidate[listLength];
                        for(int i = 0; i<listLength-1; i++)
                        {
                            temp[i+1] = candidates[i];
                        }
                        candidates = temp;
                    }
            }

        else if(answer.equals("b"))
        {
                    int random = (new Random()).nextInt(allCandidates.length);
                    candidates[0] = allCandidates[random];
                    listLength = listLength + 1;
                    Candidate[] temp = new Candidate[listLength];
                    for(int i = 0; i<listLength-1; i++)
                    {
                        temp[i+1] = candidates[i];
                    }
                    candidates = temp;
        }                    
        else if(answer.equals("c"))
        {
                    Candidate[] votes = new Candidate[allCandidates.length];
                    for(int i = 0; i<allCandidates.length; i++)
                    {
                        votes[i] = (allCandidates[i]).vote(candidates);
                    }
        }



        

        return;
    }
}
