package OOP.ec22697.MP;// File Candidate_ec22764.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22764 extends Candidate {
    
    public String getName() {
        return "Bob";
    }
    
    public String getSlogan() {
        return "Building for the future";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22764();
        
        if (candidates.length == 0)
        {
            return r;
        }
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        for (Candidate c : candidates){
            if (c.getName().equals("Mr.Bean"))
                return c;
                }

        for (Candidate c : candidates){
            if (c.getSlogan().equals("Teddy!"))
                return c;
                }

        return candidates[candidates.length -1];
        
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22764();
        
        if (votes.length == 0)
        {
            return new Candidate_ec22764();
        }
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        Candidate currentWinner = votes[0]; 

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
    
        public static void main (String [] args)
    {
        Candidate[] allCandidates = A3.getCandidateArray(); // Creates array of all candidates
        Candidate[] votes = new Candidate[allCandidates.length]; // Empty array that's the length of all candidates array
    
        Scanner scanner = new Scanner(System.in);
    
        boolean exit = false; // Initialise exit to be false
        int count = 0; // Used to keep track of indexes
    
        while (!exit) // While exit is not true, while loop runs
        {
            String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        
            if (!exit)
            {
                if (choice.equals("a")) // If a is chosen, adds new candidate
                {
                    System.out.println("Please enter a username."); // Asks for their username
                    String username = scanner.nextLine();
                    Candidate AddSpecCandidate = A3.getByUsername(username, allCandidates);
                
                if (AddSpecCandidate == null)
                {
                    System.out.println("Username is not found.");
                }
                else
                {    
                    votes[count] = AddSpecCandidate;
                    count ++;
                    PrintCandidates(votes, count); // Prints all candidates in votes array
                }
                break;
            }
            
            else if (choice.equals("b")) // If b is chosen, adds candidate at random
            {
                // Generates random number between 0 and number of candidates in array
                Random randInt = new Random();
                int randomInt = randInt.nextInt(allCandidates.length);
                System.out.println(randInt);
                
                votes[count] = allCandidates[randomInt]; // Adds candidate to votes array
                count ++;
                PrintCandidates(votes, count); // Prints all candidates in votes array
                
                break;
            }
            
            else if (choice.equals("c")) // If c is chosen, runs election
            {
                String whoCount = inputString("Who should count the votes?");
                Candidate CountCandidate = A3.getByUsername(whoCount, allCandidates);
                
                int repeat = inputInt("How many times should we run this election?");
                RunningVote(votes, count, repeat, CountCandidate); // This method gathers all the votes
                
                exit = true; // Set exit to false once complete
                
                break;
            }
        }
        
            else // If user enters an invalid input
            {
                System.out.println("Option " + choice + " is not a valid option.");
            }
      
        }
    
        return;
        
    }
        
                            
    public static void PrintCandidates(Candidate[] candidateList, int count) // Method prints all candidates
    {
        System.out.println("The candidates are: ");
    
        for (int i = 0; i < count; i++) // For loop to get the name and slogan according to the candidate
        {
            System.out.println(candidateList[i].getName() + ":" + candidateList[i].getSlogan());
        }
    
        System.out.println("This is the end of the list.");
    
        return;
    }
        

    public static void RunningVote(Candidate[] votes, int count, int repeat, Candidate CountCandidate)
    {
        Candidate[] votesNew = new Candidate[count]; // Creates new array so it's like the original votes array
    
        for (int i = 0; i < count; i++) // For loop to copy values which have candidates
        {
            votesNew[i] = votes[count];
        }
    
    
    
        Candidate[] voting = new Candidate[repeat * (count-1)]; // New array to hold enough values so it can run the number of times the user specified for each candidate
    
        for (int j = 0; j <=repeat; j++) // Repreats for the number of times user wanted
        {
            for (int i = 0; i < count; i++) // Counts each vote for each candidate
            {
                try 
                {
                    voting[(count * j) + i] = votesNew[i].vote(votesNew);
                } 
                catch (Exception e) 
                {
                    voting[i] = new Candidate_ec22764();  // If vote doesn't work, votes for me
                }
            }
        }
    
        Candidate winner = CountCandidate.selectWinner(votes);
        System.out.println("Most common winner is: " + winner.getName());

    }

    public static String inputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String answer;
    
        System.out.println(message);
        answer = scanner.nextLine();  // This assigns the variable to the user input
    
        return answer; // Returning the variable for later use
    }

    public static int inputInt (String message)
    {
        Scanner scanner = new Scanner (System.in);
        String answer = inputString(message);
        
        return Integer.parseInt(answer);
    }

    
}
