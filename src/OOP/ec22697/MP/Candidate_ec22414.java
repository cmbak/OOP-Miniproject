package OOP.ec22697.MP;// File Candidate_ec22414.java

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22414 extends Candidate {
    
    public String getName() {
        return "Jon";

    }
    
    public String getSlogan() {
        return "Winner!";

    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22559();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Suiii"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Walter"))
                return c;

        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22559();
        
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
    
    // Returns a string entered by the user
    public static String EnterString(String question)
    {
        String string;

        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        string = scanner.nextLine();
        return string;
    }
    
    public static void main(String[] args) {
    Candidate[] allContributions = A3.getCandidateArray();
    Candidate[] candidates = new Candidate[allContributions.length];
    int count = 0;
    
    System.out.println("= Running Repeated Elections =");
    
    boolean loop = true;
    
    while (loop)
    {
        System.out.println("Candidates are");
        if (count == 0)
        {
            System.out.println("None");
        }
        else
        {
            for (int i = 0;i < count; i++)
            {
               System.out.println((i + 1) + ". " + candidates[i].getName());
            }
        }
        
        String option = EnterString("Would you like to a) add a specific candidate b) add a candidate at random c) exit the election?");
        
        
        if (option.equals("a"))
        {
            String username = EnterString("Please enter a username.");
            Candidate candidate = A3.getByUsername(username, allContributions);
            candidates[count] = candidate;
            count ++;
        }
        else if (option.equals("b"))
        {
            Random random = new Random();
            int random_int = random.nextInt(allContributions.length);
            candidates[count] = allContributions[random_int];
            count ++;
        }
        
        else
        {
            loop = false;
        }
    }
}
    
    
}
