package OOP.ec22697.MP;// File Candidate_ec22648.java
//
// Machine generated stub for Assignment 2

// Importing libraries for use in main

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22648 extends Candidate {

    public static String inputString(String message)
    {
        String input;
        Scanner scanner = new Scanner(System.in);
    
        System.out.println(message); // Message passed in as an argument during method call
        input = scanner.nextLine();
    
        return input;
    }
    
    // Main method to run election
    public static void main (String [] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Retrieving all candidate information
        Candidate[] allCandidates = A3.getCandidateArray();
        
        // Creating an array with length of the number of candidates for election and keeping track of number of candidates
        Candidate[] candidatesArray = new Candidate[allCandidates.length];
        int noOfCandidates = 0;
        
        // Start of program
        System.out.println("Would you like to a) Add a specific candidate? b) Add a candidate at random? c) Run the election?");
        String answer = scanner.nextLine();
        
        while (!answer.equals("c"))
        {
            if (answer.equals("a"))
            {
                System.out.println("Please enter this candidate's name.");
                String candidateName = scanner.nextLine();
                Candidate specificCandidate = A3.getByUsername(candidateName, allCandidates);
                if (specificCandidate != null) // Checks to see if an existing candidate was searched for
                {
                    candidatesArray[noOfCandidates] = specificCandidate;
                    noOfCandidates++;
                }
                else
                {
                    System.out.println("Sorry, user not found.");
                }
                System.out.println("Would you like to \na) Add a specific candidate? \nb) Add a candidate at random? \nc) Run the election?");
                answer = scanner.nextLine();
            }
            else if (answer.equals("b"))
            {
                // Uses a RNG to pick a random candidate from allCandidates and place them into available slot in candidatesArray
                Random random = new Random();
                int randomInt = random.nextInt(allCandidates.length);
                candidatesArray[noOfCandidates] = allCandidates[randomInt];
                noOfCandidates++;
                
                System.out.println("Would you like to \na) Add a specific candidate? \nb) Add a candidate at random? \nc) Run the election?");
                answer = scanner.nextLine();
            }
            else
            {
                System.out.println("Sorry I don't understand your input, please try again:");
                System.out.println("Would you like to \na) Add a specific candidate? \nb) Add a candidate at random? \nc) Run the election?");
                answer = scanner.nextLine();
            }
        }
        runElection(allCandidates, candidatesArray);
    }
    
    // Run the election for a certain number of times for candidates entered in a) and b) in main
    public static void runElection(Candidate[] allCandidates, Candidate[] candidatesArray)
    {
        Scanner scanner = new Scanner(System.in);

        // Create an array to contain winners of elections
        System.out.println("How many times shall we run the election?");
        int noOfRuns = Integer.parseInt(scanner.nextLine());
        Candidate[] electionWinners = new Candidate[noOfRuns];
        
        // Select a user to count the votes
        System.out.println("Who should count the votes for the election?");
        String name = scanner.nextLine();
        Candidate countingUser = A3.getByUsername(name, allCandidates);
        if (countingUser == null)
        {
            System.out.println("Sorry, user not found. Please try again:");
            name = scanner.nextLine();
            countingUser = A3.getByUsername(name, allCandidates);
        }
        
        // Running the election
        for (int i = 0; i < noOfRuns; i++)
        {
            for (int j = 0; j < allCandidates.length; j++)
            try {
                allCandidates[j].vote(candidatesArray);
            } catch (Exception e) {}
            
            Candidate winningCandidate = countingUser.selectWinner(candidatesArray);
            System.out.println(winningCandidate.getName() + " has won this election!");
            electionWinners[i] = winningCandidate;
        }
        determineCommonWinner(electionWinners);
        
        System.out.println("Would you like to\na) Exit\nb) Run same election many times\nc) Check who counts honestly\nd) Run all possible two-candidate elections\ne) Run a higher order election");
    }
    
    public static void determineCommonWinner(Candidate[] c)
    {
        // Default to first winner
        Candidate r = c[0];
        int largestCountSoFar = 0;
        
        for (int i = 0; i < c.length; i++)
        {
            if (numberOfTimes(c[i], c) > largestCountSoFar)
            {
                largestCountSoFar = numberOfTimes(c[i], c);
                r = c[i];
            }
        }
        
        System.out.println("Most common winner is " + r.getName());
    }
    
    public static int numberOfTimes(Candidate x, Candidate[] a) 
    {
        // Default to 0 times.
        int r = 0;

        for (int i = 0; i < a.length; i++)
        {
            if (x == a[i])
            {
                r = r + 1;
            }
        }
        return r;
    }
    
    public String getName() {
        return "Fahim";
    }
    
    public String getSlogan() {
        return "Free hot dogs!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22648();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Save Earth!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Haytham"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[(candidates.length)-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22648();
        
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
