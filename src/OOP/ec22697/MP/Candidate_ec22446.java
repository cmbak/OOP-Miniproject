package OOP.ec22697.MP;// File Candidate_ec22446.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22446 extends Candidate
{
    // Candidate's name
    public String getName()
    {
        return "Gustavo";
    }

    // Candidates slogan
    public String getSlogan()
    {
        return "Lets build not build a wall!";
    }

    // voting method 
    public Candidate vote(Candidate[] candidates)
    {
        Random rand = new Random();

        // if array is empty add my one
        if (candidates.length <= 0)
        {
            return new Candidate_ec22446();
        }

        for (Candidate x : candidates)
        {
            // check for Gustavo
            if (x.getName().equals("Gustavo"))
            {
                return x;
            }
        }
        
        // else pick a random candidate
        int x = rand.nextInt(candidates.length);
        return candidates[x];
    }

     // picks winner
    public Candidate selectWinner(Candidate[] candidates)
    {
        int topVote = 0;
        Candidate winner = new Candidate_ec22446();

        if (candidates.length <= 0)
        {
            return new Candidate_ec22446();
        }

        for (Candidate x : candidates)
        {
            int count = 0;

            // go through array to count all the same votes
            for (Candidate y : candidates)
            {
                if (x == y)
                {
                    count = count + 1;
                }
            }

            // takes the first candidate with the highest vote as the winner
            if (count > topVote)
            {
                topVote = count;
                winner = x;
            }
        }
        return winner;
    }
    
    public static String inptuStr(String x)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(x);
        String inp = sc.nextLine();
        return inp;
    }

    public static int inptuInt(String x)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(x);
        int inp = sc.nextInt();
        return inp;
    }

    // main method
    public static void main(String[] args)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        String EnterCandidate;
        int AmountCand;

        AmountCand = inptuInt("How many candidates in the election?");
        Candidate[] candidatesList = new Candidate[AmountCand];

        for (int i = 0; i < AmountCand; i++)
        {
            System.out.println("Add candidates to the election: ");
            EnterCandidate = inptuStr("Enter a username");
            candidatesList[i] = A3.getByUsername(EnterCandidate, allCandidates);
        }

        // running election

        // selecting the candidate choosing the winner
        String voteCounterName = inptuStr("Who should count the votes?");
        Candidate voteCounter = A3.getByUsername(voteCounterName, candidatesList);

        // candidates vote
        int electionNum = inptuInt("How many times should you run the election?");
        Candidate[] votes = new Candidate[AmountCand * electionNum];

        for (int x = 0; x < electionNum; x++)
        {
            for (int i = 0; i < AmountCand; i++)
            {
                votes[i+(x*AmountCand)] = candidatesList[i].vote(candidatesList);
            }
        }

        // choosing the winner
        Candidate winner = voteCounter.selectWinner(votes);
        System.out.println("The winner was " + winner.getName());
        return;
    }

}