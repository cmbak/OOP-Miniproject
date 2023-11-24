package OOP.ec22697.MP;// File Candidate_ec22909.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22909 extends Candidate {

    public String getName() {
        return "ec22909";
    }
    
    public String getSlogan() {
        return "More Stuff!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22909();
        
        if (candidates.length != 0) {
            r = candidates[0];
            for (int i=0; i<candidates.length; i++) {
                Candidate c = candidates[i];
                
                if (c.getName().equals("ec22909")) 
                return c;
            }
        }
        return r;
    }
//adapted from ec22586
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22909();
        
        if (votes.length != 0) {
            int highestCount = 0;
            for (int i = 0; i < votes.length; i++) {
                Candidate c = votes[i];
                int count = 0;
                for (int j = 0; j < votes.length; j++) {
                    Candidate x = votes[j];
                    if (x == c) count++;
                }
                if (count > highestCount) { 
                    highestCount = count; 
                    r = c;
                } 
            }
        }
        return r;
    }

    private static String askQuestion(String question)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        String value = scanner.nextLine();
        return value;
    }

    private static int askQuestionInt(String question)
    {
        int value;
        Scanner scanner = new Scanner(System.in);

        System.out.println(question);
        value = Integer.parseInt(scanner.nextLine());

        return value;
    }

    // Create a copy of array with specific length
    private static Candidate[] copyArray(Candidate[] candidates, int count)
    {
        Candidate[] copy = new Candidate[count];
        for (int i = 0; i < count; i++)
        {
            copy[i] = candidates[i];
        }
        return copy;
    }

    // Displays all the candidates
    public static void showCandidates(Candidate[] electionCandidates, int electionCandidateCount)
    {
        System.out.println("The Candidates are");
        if (electionCandidateCount == 0)
        {
            System.out.println("None");
        }
        else
        {
            for (int i = 0; i < electionCandidateCount; i++) 
            {
                System.out.println((i + 1) + ". " + electionCandidates[i].getName() + ": " + electionCandidates[i].getSlogan());
            }
        }
    }

    private static int addCandidate(Candidate[] electionCandidates, Candidate[] all, int electionCandidateCount)
    {
        String candidateName = askQuestion("Please enter a username.");
        Candidate candidate = A3.getByUsername(candidateName, all);
        if (candidate != null)
        {
            electionCandidates[electionCandidateCount] = candidate;
            return electionCandidateCount + 1;
        }
        else
        {
            System.out.println("Cannot find candidate: " + candidateName);
            return electionCandidateCount;
        }
    }

    private static int addRandomCandidate(Candidate[] electionCandidates, Candidate[] all, int electionCandidateCount)
    {
        if (electionCandidateCount == all.length)
        {
            System.out.println("Candidates are full.");
            return electionCandidateCount;
        }
        Random randInt = new Random();
        boolean found = false;
        while (true)
        {
            found = false;
            // Get a random candidate
            int randomInt = randInt.nextInt(all.length);
            Candidate candidate = all[randomInt];
            // Check if there's already one
            for (int i = 0; i < electionCandidateCount; i++)
            {
                if (electionCandidates[i] == candidate)
                {
                    found = true;
                }
            }
            if (found == false)
            {
                electionCandidates[electionCandidateCount] = candidate;
                return electionCandidateCount + 1;
            }
        }
    }

    public static void runElection(Candidate[] electionCandidates, Candidate[] all, int electionCandidateCount)
    {
        String tellerName = askQuestion("Who should count the votes?");
        Candidate teller = A3.getByUsername(tellerName, all);
        if (teller != null)
        {
            int count = askQuestionInt("How many times shall we run the election?");
            Candidate[] winners = new Candidate[count];
            for (int i = 0; i < count; i++) 
            {
                Candidate[] candidates = copyArray(electionCandidates, electionCandidateCount);
                Candidate[] votes = new Candidate[all.length];
                for (int j = 0; j < all.length; j++) 
                {
                    votes[j] = all[j].vote(candidates);
                }
                winners[i] = teller.selectWinner(votes);
            }
            getCommonWinner(winners);
        }
        else
        {
            System.out.println("Cannot find candidate: " + tellerName);
        }
    }

    private static void getCommonWinner(Candidate[] winners) 
    {
        boolean moreThanOneWinner = false;
        String winnerName = null;
        if (winners.length != 0) 
        {
            // Store a copy of winners for counting (since array is modified during counting)
            Candidate[] countWinner = copyArray(winners, winners.length);
            int highestCount = 0;
            for (int i = 0; i < countWinner.length; i++) 
            {
                Candidate c = countWinner[i];
                // if c is null, c is removed in previous loop.
                if (c != null)
                {
                    int count = 0;
                    for (int j = 0; j < countWinner.length; j++) 
                    {
                        Candidate x = countWinner[j];
                        if (x != null && x.getName() == c.getName()) 
                        {
                            // Remove counted winner (refer to above).
                            countWinner[j] = null;
                            count++;
                        }
                    }
                    if (count == highestCount) 
                    {
                        winnerName = winnerName + " and " + c.getName();
                        moreThanOneWinner = true;
                    }
                    if (count > highestCount) 
                    { 
                        highestCount = count; 
                        winnerName = c.getName();
                        moreThanOneWinner = false;
                    } 
                }
            }
        }
        if (winnerName != null)
        {
            if (moreThanOneWinner == true)
            {
                System.out.println("Most common winners are " + winnerName);
            }
            else
            {
                System.out.println("Most common winner is " + winnerName);
            }
        }
        System.out.println("There were no other winners.");
    }

    public static void main(String[] args) 
    {
        Candidate[] all = A3.getCandidateArray();
        Candidate[] electionCandidates = new Candidate[all.length];
        int electionCandidateCount = 0;

        System.out.println("= Running Repeated Elections =");
        String choice;
        while (true) 
        {
            showCandidates(electionCandidates, electionCandidateCount);
            choice = askQuestion("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit");
            if (choice.equals("a")) 
            {
                electionCandidateCount = addCandidate(electionCandidates, all, electionCandidateCount);
            }
            else if (choice.equals("b")) 
            {
                electionCandidateCount = addRandomCandidate(electionCandidates, all, electionCandidateCount);
            }
            else if (choice.equals("c"))
            {
                runElection(electionCandidates, all, electionCandidateCount);
            }
            else if (choice.equals("d"))
            {
                break;
            }
            else
            {
                System.out.println("Please choose option a, b, c or d!");
            }
        }
    }
}
