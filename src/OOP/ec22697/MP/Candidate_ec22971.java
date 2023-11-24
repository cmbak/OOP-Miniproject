package OOP.ec22697.MP;// File Candidate_ec22971.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22971 extends Candidate {
    
    public String getName() 
    {
        return "The Flash";
    }
    
    public String getSlogan() 
    {
        return "Being alive means running.";
    }
    
    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22649();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Being alive means running."))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Nikhel"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22649();
        
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
    
    public static String inputString (String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        
        while (!scanner.hasNextLine())
        {
            System.out.println("Invalid input. Try again.");
            scanner.nextLine();
        }
        
        return scanner.nextLine();
    }
    
    public static int inputInt (String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        
        while (!scanner.hasNextInt())
        {
            System.out.println("Invalid input. Try again.");
            scanner.nextLine();
        }
        
        return scanner.nextInt();
    }
    
    public static void main (String[] args)
    {
        // Array of all candidates.
        Candidate[] allCandidates = A3.getCandidateArray();
        // Empty array with length equal to all candidates.
        Candidate[] election = new Candidate[allCandidates.length];
        // Counts next available index in 'candidates' array.
        int counter = 0;
        
        // User choice.
        String choice = inputString("Would you like to: a) Add a specific candidate, b) Add a candidate at random or c) Run the election? ");
        
        if (choice.equals("a"))
        {
            // Add specific candidates to an election.
            String username = inputString("Please enter a username: ");
            Candidate addedCand = A3.getByUsername(username, allCandidates);
            election[counter] = addedCand;
            // Increase index by 1.
            counter++;
            System.out.println("Candidate has been added.");
            return;
        }
        else if (choice.equals("b"))
        {
            // Adds a candidate at random.
            // Generates a random number between 0 and the length of the candidates array.
            Random rand = new Random();
            int result = rand.nextInt(allCandidates.length);
            // Adds candidate to the election array.
            election[counter] = allCandidates[result];
            // Increase index by 1.
            counter++;
            System.out.println("Candidate has been added.");
            return;
        }
        else if (choice.equals("c"))
        {
            // Run the same election many times.
            // Report the most common winners.
            String name = inputString("Who should count the votes? ");
            Candidate voteCounter = A3.getByUsername(name, allCandidates);
            
            int runTimes = inputInt("How many times shall we run the election? ");
            
            // Runs method to collect votes.
            runVotes(election, counter, runTimes, voteCounter);
            return;
        }
    }
    
    public static void runVotes (Candidate[] election, int counter, int runTimes, Candidate voteCounter)
    {
        // Array same as election but with no null elements.
        Candidate[] newElection = new Candidate[counter];
        
        // Copies the elements in the election array that have candidates.
        for (int i=0; i<counter; i++)
        {
            newElection[i] = election[counter];
        }
        
        // Creates a new array:
        // Length = Number of elections * Number of candidates
        Candidate[] votes = new Candidate[runTimes * (counter-1)];
        
        // Repeat as many times as stated by the user.
        for (int j=0; j<=runTimes; j++)
        {
            // Counts each vote for each candidate.
            for (int k=0; k<counter; k++)
            {
                try {
                    votes[((counter-1) * j) + k] = newElection[k].vote(newElection);
                } catch (Exception e) {
                    // If vote doesn't work, vote for me.
                    votes[k] = new Candidate_ec22971();
                }
            }
        }
        
        // Store an instance of the winning candidate.
        Candidate winner = voteCounter.selectWinner(votes);
        // Displays winner to the user.
        System.out.println("The most common winner is " + winner.getName() + "");
    }
}
