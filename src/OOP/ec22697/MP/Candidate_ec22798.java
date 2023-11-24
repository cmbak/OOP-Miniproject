package OOP.ec22697.MP;// File Candidate_ec22798.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22798 extends Candidate {
    
    public static void main(String[] args)
    {
        Candidate[] candidateList = A3.getCandidateArray();
        Candidate[] electionList = new Candidate[candidateList.length];

        boolean quit = false;
        int counter = 0;

        while (!quit)
        {
            String menu = inputString("\nDo you wish to:\na) add a candidate to the election\nb) add a random candidate to the election\nc) run the election\n-> ");
            if (menu.toLowerCase().equals("a"))
            {
                String username = inputString("\nEnter Username:");
                Candidate newCandidate = A3.getByUsername(username, candidateList);
                electionList[counter] = newCandidate;
                counter++;
            }
            else if (menu.toLowerCase().equals("b"))
            {
                Random random = new Random();
                int randomNum = random.nextInt(candidateList.length);
                electionList[counter] = candidateList[randomNum];
                counter++;
            }
            else if (menu.toLowerCase().equals("c"))
            {
                String username = inputString("Enter username of the vote counter:");
                Candidate candidateVoteCounter = A3.getByUsername(username, candidateList);
                int numOfRuns = Integer.parseInt(inputString("Enter amount of elections runs:"));
                System.out.println(candidateVoteCounter.getName());
                runElection(electionList, counter, numOfRuns, candidateVoteCounter);
                quit = true;
            }
            printCandidates(electionList, counter);
        }
    }
    
    public static String inputString(String statement)
    {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        while (userInput.isEmpty())
        {
            System.out.println(statement);
            userInput = scanner.nextLine();
            if (userInput.isEmpty())
            {
                System.out.println("Invalid Input");
            }
        }
        return userInput;
    }

    public static void printCandidates(Candidate[] candidates, int counter)
    {
        System.out.println("\nCandidates:");
        for (int i = 0; i < counter; i++)
        {
            System.out.println((i+1) + ". " + candidates[i].getName() + ": " + candidates[i].getSlogan());
        }
    }

    public static void runElection(Candidate[] electionList, int counter, int numOfRuns, Candidate candidateVoteCounter)
    {
        Candidate[] numVotes = new Candidate[numOfRuns * (counter - 1)];

        for (int i = 0; i <= numOfRuns; i++)
        {
            for (int j = 0; j < counter; j++)
            {
                try
                {
                    numVotes[(counter * i) + j] = electionList[j].vote(electionList);
                }
                catch (Exception e)
                {
                    numVotes[i] = new Candidate_ec22798();
                }
            }
        }
        Candidate winner = candidateVoteCounter.selectWinner(numVotes);
        System.out.println("Winner: " + winner.getName());
    }
    
    public String getName() {
        return "Eshan Sharif";
    }
    
    public String getSlogan() {
        return "I am freshie!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        //If array is empty return instance of this class
        if (candidates.length == 0)
        {
            return new Candidate_ec22798();
        }
        
        //Else try and find candidate with these attributes
        for (Candidate candidate : candidates)
        {
            if (candidate.getSlogan().equals("First time was so nce i had 2do it twice") || candidate.getName().equals("Anthony Joshua"))
            {
                return candidate;
            }
        }
        
        //If none found, pick random candidate
        Random random = new Random();
        int randNum = random.nextInt(candidates.length);
        return candidates[randNum];
    }

    public Candidate selectWinner(Candidate[] votes) {
        //If array is empty return instance of this class
        if (votes.length == 0)
        {
            return new Candidate_ec22798();
        }
        
        Candidate currentWinner = votes[0];
        
        //Return candidate with highest votes
        int highestVotes = 0;
        for (Candidate candidate : votes)
        {
            int numVotes = 0;
            for (Candidate c : votes)
            {
                if (candidate == c)
                {
                    numVotes++;
                }
            }
            if (numVotes >= highestVotes)
            {
                highestVotes = numVotes;
                currentWinner = candidate;
            }
        }
        return currentWinner;
    }
}
