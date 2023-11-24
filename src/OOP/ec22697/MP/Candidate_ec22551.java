package OOP.ec22697.MP;// File Candidate_ec22551.java

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


class Candidate_ec22551 extends Candidate
{
    //comment+
    public String getName()
    {
        return "RK";
    }
    
    public String getSlogan() 
    {
        return "Go big or go home!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // if array is empty return instance of my own class
        if (candidates.length == 0)
        {
            return new Candidate_ec22551();
        }
        
        // search for a like minded candidate
        for (Candidate c : candidates)
        {
            if (c.getSlogan().equals("Go big or go home!"))
            {
                return c;
            } 
        }
        
        // search for myself otherwise
        for (Candidate c : candidates)
        {
            if (c.un.equals("RK"))
            {
                return c;
            } 
        }
        
        // otherwise return last candidate on list
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // if array is empty return instance of myself.
        if (votes.length == 0) 
        {
            return new Candidate_ec22551();
        }
            
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
    
    public static String inputString(String message)
    {
        final String output;

        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        output = scanner.nextLine();
        return output;
    }
    
    public static int inputInt(String message)
    {
        int output = 0;
        boolean isInt = false;
        Scanner scanner = new Scanner(System.in);

        while (!isInt)
        {
            System.out.println(message);
            if (scanner.hasNextInt())
            {
                output = scanner.nextInt();
                isInt = true;
            }
            else
            {
                System.out.println("ERROR: NON INTEGER INPUT GIVEN");
                scanner.next();
            }
        }
        return output;
    }
    
    private static void displayCandidates(ArrayList<Candidate> candidates)
    {
        System.out.println("Candidates are: ");

        int count = 0;
        for(Candidate c: candidates)
        {
            System.out.println((count + 1) + ". " + c.getName());
            count ++;
        }
    }

    private static void addChosenCandidate(ArrayList<Candidate> candidates)
    {
        String chosenCandidate = inputString("Please enter a username.");
        boolean found = false;

        // search for candidate from name
        for(Candidate c: A3.getCandidateArray())
        {
            if(chosenCandidate.equals(c.un))
            {
                candidates.add(c);
                found = true;
                displayCandidates(candidates);
            }
        }

        // candidate with that name does not exist
        if(!found)
        {
            System.out.println(chosenCandidate + " does not exist\n");
        }
    }
    
    private static void addRandomCandidate(ArrayList<Candidate> candidates)
    {
        // generate random candidate
        Random random = new Random();
        Candidate[] allCandidates = A3.getCandidateArray();
        int random_choice = random.nextInt(allCandidates.length);

        // add random candidate to array
        Candidate value = allCandidates[random_choice];
        candidates.add(value);
        displayCandidates(candidates);
    }

    private static void runElection(ArrayList<Candidate> candidates)
    {
        // populate new array with current candidates
        Candidate[] candidatesDuplicate = new Candidate[(candidates.size())];
        int count = 0;
        for(Candidate c : candidates)
        {
            candidatesDuplicate[count] = c;
            count++;
        }

        // find voting candidate
        String votingCandidate = inputString("Who should count the votes?");
        Boolean votingComplete = false;
        
        // ask again if not found
        while(!votingComplete)
        {
            try
            {
                A3.getByUsername(votingCandidate, candidatesDuplicate);
                votingComplete = true;
            }
            catch(Exception e)
            {
                votingCandidate = inputString("Who should count the votes?");
            }
        }

        // replicate election many times
        // make new array for storing winners
        int numElections = inputInt("How many times shall we run the election?");
        Candidate[] winningCandidates = new Candidate[numElections];
        for(int i = 0; i < numElections; i++)
        {
            winningCandidates[i] = (A3.getByUsername(votingCandidate, A3.getCandidateArray())).selectWinner(candidatesDuplicate);
        }

        Candidate overallWinner = winningCandidates[0];

        // find overall winner by counting winning instances
        for(int i = 0; i < numElections; i++)
        {   
            int max = 0;
            for (Candidate c : winningCandidates) 
                {
                    int count2 = 0;
                    for (Candidate x : winningCandidates)
                    {
                        if (x == c)
                        {
                            count2++;
                        }
                    }
                    if (count2 > max) 
                    {
                        max = count2;
                        overallWinner = c;
                    }
                }
        }

        System.out.println("Most common winner is " + overallWinner.getName());
    }


    private static void runRepeatedElections()
    {
        System.out.println("= Running Repeated Elections =");

        ArrayList<Candidate> candidates = new ArrayList<>();
        String electionDecision = "";
        Boolean done = false;

        while (!done)
        {
            electionDecision = inputString("Would you like to\na) add a specific candidate\nb) add a candidate at random\nc) run the election?");

            if (electionDecision.equals("a"))
            {
                addChosenCandidate(candidates);
            }

            else if (electionDecision.equals("b"))
            {
    
                addRandomCandidate(candidates);
            }

            else if (electionDecision.equals("c"))
            {
                runElection(candidates);                
            }
        }     
    }


    public static void main(String[] args) 
    {     
        String mainDecision = "";
        Boolean done = false;

        while (!done)
        {
            mainDecision = inputString("Would you like to \na) exit\nb) run same election many times");

            if (mainDecision.equals("a"))
            {
                System.out.println("= Exiting system =");
                done = true;
            }

            else if (mainDecision.equals("b"))
            {
                runRepeatedElections();
            }
        }        
    }
    
}
