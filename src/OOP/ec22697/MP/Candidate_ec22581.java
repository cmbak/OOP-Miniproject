package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

class Candidate_ec22581 extends Candidate {
    private static Candidate[] allCands = A3.getCandidateArray();
    private static Candidate[] candList = new Candidate[546];
    private static int candPointer = 0;

    public static void main(String[] args) // Main method
    {
        boolean exit = false;

        pr("= Running Repeated Elections =");

        while (!exit){
            prCands();

            pr("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            p("> ");

            Scanner inp = sc();
            String menuOpt = inp.nextLine();

            switch (menuOpt)
            {
                case "a":
                    addCandidate();
                    break;
                case "b":
                    addRandCandidate();
                    break;
                case "c":
                    runElection();
                    break;
                default:
                    pr("[Error] Enter a valid menu option");
            }
        }
    }

    private static void addCandidate() // Method to add specified candidate
    {
        pr("Please enter a username.");
        p("> ");

        Scanner inp = sc();
        String name = inp.nextLine();

        Candidate cand = A3.getByUsername(name, allCands);

        if (cand == null)
        {
            pr("[Error] Username not found");
            return;
        }

        candList[candPointer] = cand;
        candPointer += 1;
    }

    private static void addRandCandidate() // Method to add random candidate
    {
        Random randGen = new Random();

        int ub = 547;
        int randNum = randGen.nextInt(ub);

        candList[candPointer] = allCands[randNum];
        candPointer += 1;
    }

    private static void prCands()
    {
        pr("Candidates are");
        if (candList[0] == null)
        {
            pr("None");
        }
        else
        {
            for (int i = 0; i < (candPointer + 1); i++)
            {
                pr((i+1) + ". " + candList[i].getName());
            }
        }
    }

    private static void runElection() // Method to run election
    {
        Scanner s = sc();
        boolean counterFound = false;
        boolean runsFound = false;

        int runsCount = 0;
        Candidate counterCand = new Candidate_ec22581();


        while (!counterFound)
        {
            pr("Who should count the votes?");
            p("> ");

            String counterCandName = s.nextLine();
            counterCand = A3.getByUsername(counterCandName, allCands);

            if (counterCand != null)
            {
                counterFound = true;
            }
            else
            {
                pr("[Error] Counter candidate username not found!");
            }
        }

        while (!runsFound)
        {
            pr("How many times shall we run the election?");
            p("> ");

            String runsCountStr = s.nextLine();

            Pattern isNum = Pattern.compile("[0-9]+$");
            if (isNum.matcher(runsCountStr).matches())
            {
                runsCount = Integer.parseInt(runsCountStr);
                runsFound = true;
            }
            else
            {
                pr("[Error] Invalid integer!");
            }
        }

        Candidate[] winnerArray = new Candidate[runsCount];
        int[] winnerVotes = new int[runsCount];
        int winnerPointer = 0;

        int currentHighestVotes = 0;
        Candidate currentWinner = new Candidate_ec22581();

        for (int i = 0; i < runsCount; i++)
        {
            currentWinner = counterCand.selectWinner(candList);
            boolean exists = false;

            for (int j = 0; j < winnerPointer; j++)
            {
                if (currentWinner.equals(winnerArray[j]))
                {
                    winnerVotes[j] += 1;
                    exists = true;

                    if (winnerVotes[j] > currentHighestVotes)
                    {
                        currentHighestVotes = winnerVotes[j];
                        currentWinner = winnerArray[j];
                    }
                }
            }

            if(!exists)
            {
                winnerArray[winnerPointer] = currentWinner;
                winnerVotes[winnerPointer] = 1;
                winnerPointer += 1;
            }
        }

        int otherWinnerCount = winnerArray.length - 1;

        pr("The most common winner is " + currentWinner);
        p("There are ");

        if (otherWinnerCount > 0)
        {
            p(Integer.toString(otherWinnerCount));
        }
        else
        {
            p("no");
        }
        pr(" other winners.");
    }

    private static void pr(String str) {System.out.println(str);} // Print line short method

    private static void p(String str) {System.out.print(str);} // Print short method

    private static Scanner sc() {Scanner s = new Scanner(System.in); return s;} // Scanner short method

    public String getName() {
        return "Andras";
    }
    
    public String getSlogan() {
        return "More trees and less cheez!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) 
            return new Candidate_ec22581();
        
        for (Candidate c : candidates)
            if (c.getName().equals("Raphael"))
                return c;
        
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) 
            return new Candidate_ec22581();
        
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
