package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec221015 extends Candidate
{
    public String getName()
    {
        return "isagi";
    }

    public String getSlogan()
    {
        return "more love!";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of this class
        if (candidates.length == 0)
            return new Candidate_ec221015();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("more love!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("isagi"))
                return c;

        // Otherwise, default to last candidate on the list
        return candidates[candidates.length-1];
    }


    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of this class
        if (votes.length == 0)
            return new Candidate_ec221015();

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
    
    public static void print (String message)
    {
        System.out.println(message);
    }
    
    public static String inputString(String message)
    {
        Scanner kb = new Scanner(System.in);
        print(message);
        return kb.nextLine();
    }
    
    public static int inputInt(String message)
    {
        String userInput;
        Scanner kb = new Scanner(System.in);
        int testNumber;
        
        print(message);
        userInput = kb.nextLine();

        try
        {
            testNumber = Integer.parseInt(userInput);
        }
        catch (Exception a)
        {
            print("Error. Enter a number.");
            testNumber = inputInt(message);
        }
        return testNumber;
    }

    
    public static void printCandidates(Candidate[] electionCandidates)
    {
        int length = electionCandidates.length;

        print("The Candidates are: ");
        for (int i = 0; i < length; i++)
        {
            if (!(electionCandidates[i] == null))
            {
                print((i+1) + ". " + (electionCandidates[i].getName()));
            }

        }

    }
    
    public static Candidate[] addSpecificCandidate(Candidate[] allCandidates, Candidate[] electionCandidates)
    {
        String name = "";
        boolean added = false;
        int count = 0;
        boolean found = false;
        int length = electionCandidates.length;

        name = inputString("Please enter a username: ");

        Candidate specificCandidate = A3.getByUsername(name, allCandidates);

        if (!(specificCandidate == null))
        {

            if (!(electionCandidates[(length-1)] == null))
            {
                print("There is insufficient space in the array to add another candidate.");
            }

            else
            {
                while (found == false && (electionCandidates[length-1] == null))
                {
                    if (electionCandidates[count] == null)
                    {
                        found = true;
                    }
                    else
                    {
                        count = count + 1;
                    }
                }

                electionCandidates[count] = specificCandidate;
            }
        }
        else
        {
            print("User not found.");
        }

        printCandidates(electionCandidates);

        return electionCandidates;
    }

    // randomly add a candidate
    public static Candidate[] addRandomCandidate(Candidate[] allCandidates, Candidate[] electionCandidates)
    {
        Random random = new Random ();
        int number = random.nextInt(allCandidates.length);
        int count = 0;
        int length = electionCandidates.length;
        boolean found = false;

        if (!(allCandidates[number] == null))
        {
            if (!(electionCandidates[length-1] == null))
            {
                print("There is insufficient space in the array to add another candidate.");
            }

            else
            {
                while (found == false && (electionCandidates[length-1] == null))
                {
                    if (electionCandidates[count] == null)
                    {
                        found = true;
                    }
                    else
                    {
                        count = count + 1;
                    }
                }

                electionCandidates[count] = allCandidates[number];
            }
        }

        return electionCandidates;
    }

    
    public static void runElection(Candidate[] allCandidates, Candidate[] electionCandidates)
    {
        Candidate[] winningCandidates = new Candidate[allCandidates.length];
        Candidate winner = electionCandidates[0];
        String userCounting = "";
        int total = 0;
        boolean exists = false;

        userCounting = inputString("Who should count the votes?");

        total = inputInt("How many times shall we run the election?");

        try
        {
            for (int j = 0; j<total; j++)
            {
                winningCandidates[j] = electionCandidates[j].vote(electionCandidates);
            }

            winner = A3.getByUsername(userCounting, allCandidates).selectWinner(winningCandidates);

            print("Most common winner is " + winner.getName());

        }
        catch(Exception e)
        {
            Random random = new Random ();
            int pos = random.nextInt(electionCandidates.length);
            winner = electionCandidates[pos];
            print("Most common winner is " + winner.getName());
        }
    }

    public static Candidate[] createAllCandidates()
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        return allCandidates;
    }

    public static void chooseOption(Candidate[] allCandidates, Candidate[] electionCandidates)
    {

        boolean exit = false;
        String choice = "";

        while (exit == false)
        {
            // repeatedly choose an option until the election is ran
            choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

            if (choice.equals("a"))
            {
                electionCandidates = addSpecificCandidate(allCandidates, electionCandidates);
                chooseOption(allCandidates, electionCandidates);
            }
            else if (choice.equals("b"))
            {
                electionCandidates = addRandomCandidate(allCandidates, electionCandidates);
                chooseOption(allCandidates, electionCandidates);
            }
            else if (choice.equals("c"))
            {
                runElection(allCandidates, electionCandidates);
                exit = true;
            }
            else
            {
                print("Enter only a, b or c.");
                chooseOption(allCandidates, electionCandidates);
            }

        }

        print("End.");

    }

    public static void main(String[] args)
    {

        Candidate[] all_candidates = A3.getCandidateArray();
        Candidate[] election_candidates = new Candidate[all_candidates.length];

        chooseOption(all_candidates, election_candidates);
    }
}
