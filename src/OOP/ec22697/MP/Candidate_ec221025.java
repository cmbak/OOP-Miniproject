package OOP.ec22697.MP;// File Candidate_ec221025.java
//
// Updated stub for Assignment 3
// Adapted from Candidate_ec22562.java and Candidate_ec22576.java

import java.util.Scanner;
import java.util.Random;

class Candidate_ec221025 extends Candidate 
{
    
    public String getName() 
    {
        return "Tony Stark";
    }
    
    public String getSlogan() 
    {
        return "I am Iron Man.";
    }
    
    public Candidate vote(Candidate[] candidates)
    {
        // If array is empty, return instance of my own class.
        if (candidates.length == 0) 
        {
            return new Candidate_ec221025();
        }
        // Search for a like minded candidate.
        for (Candidate c : candidates) 
        {
            if (c.getSlogan().equals("Let's defeat Ultron!")) 
            {
                return c;
            }    
        }
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
        {
            if (c.getName().equals("Captain America")) // But don't tell him I voted for him...
            {
                return c;
            }
        }
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
                
        // If array is empty, return instance of my own class.
        if (votes.length == 0) 
            return new Candidate_ec221025();
        
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
    
    // Assignment 3 new part

    public static String inputString(String message) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);

        return scanner.nextLine();
    } 
    
    public static int inputInt(String message) 
    {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println(message);

        return Integer.parseInt(scanner2.nextLine());
    } 

    public static char inputChar(String message) 
    {
        Scanner scanner3 = new Scanner(System.in);
        System.out.println(message);

        return scanner3.nextLine().charAt(0);
    }
    
    public static Candidate[] getCandidateArray() 
    {
        Candidate[] thisCandidate = new Candidate[5];

        for (int i = 0; i < 5; i++) {
            thisCandidate[i] = new Candidate_ec221025();
        }

        return thisCandidate;
    }
    
    // Main method
    public static void main(String[] args) 
    {
        Candidate[] allCandidates = getCandidateArray();
        Candidate[] election = new Candidate[allCandidates.length];
        
        int counter = 0;
        String username = "";
        boolean exit = false;
        char menu = '0';

        while (!exit) 
        {
            menu = inputChar("Would you like to a) add a specific candidate b) add a random candidate c) run the election?");
            switch (menu) 
            {
                case 'a':
                    username = inputString("Please enter a username.");
                    Candidate newCandidate = A3.getByUsername(username, allCandidates);
                    election[counter] = newCandidate;

                    counter++;
                    displayCandidates(election, counter);
                    break;

                case 'b':
                    Random randInt = new Random();
                    int randomInt = randInt.nextInt(allCandidates.length);

                    election[counter] = allCandidates[randomInt];

                    counter++;
                    displayCandidates(election, counter);
                    break;

                case 'c':
                    username = inputString("Who should count the votes?");
                    Candidate chosenCandidate = A3.getByUsername(username, allCandidates);

                    int repeat = inputInt("How many times should we run the election?");
                    runningVote(election, counter, repeat, chosenCandidate);
                    exit = true;
                    break;

                default:
                    System.out.println("Option '" + menu + "' is not a valid option.");
            }
        }
    }
    
    // case a, b
    public static void displayCandidates(Candidate[] candidateList, int counter) 
    {
        System.out.println("The Candidates are: ");
        for (int i = 0; i < counter; i++) 
        {
            System.out.println(candidateList[i].getName() + ": " + candidateList[i].getSlogan());
        }

        System.out.println("End of List");

        return;
    }

    // case c
    public static void runningVote(Candidate[] election, int counter, int repeat, Candidate chosenCandidate) 
    {
        Candidate[] electionNew = new Candidate[counter];


        for (int i = 0; i < counter; i++) 
        {
            electionNew[i] = election[counter];
        }

        Candidate[] votes = new Candidate[repeat * (counter - 1)];

        for (int j = 0; j <= repeat; j++) 
        {

            for (int i = 0; i < counter; i++) 
            {
                try {
                    votes[(counter * j) + i] = electionNew[i].vote(electionNew);
                } catch (Exception e) {
                    votes[i] = new Candidate_ec221025();
                }
            }
        }

        Candidate winner = chosenCandidate.selectWinner(votes);
        System.out.println("Most common winner is " + winner.getName());
    }
}
