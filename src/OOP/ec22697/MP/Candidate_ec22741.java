package OOP.ec22697.MP;// File Candidate_ec22741.java
// Assignment 3

import java.util.Scanner;

class Candidate_ec22741 extends Candidate {
    
    public static void main(String[] args)
    {
        // Taken and adapted from Candidate_ec221017.java
        // methods from A3.java used
        Candidate[] allCandidates = A3.getCandidateArray();
        String addedCandidate;
        int candidateNum;

        candidateNum = inputInt("How many candidates will be in the election?");
        Candidate[] candidateList = new Candidate[candidateNum];
        print("Add candidates to the election: ");

        for (int i = 0; i < candidateNum; i++)
        {
            addedCandidate = inputString("Enter a username: ");
            candidateList[i] = A3.getByUsername(addedCandidate, allCandidates);
        }

        print("The candidates are: ");
        for(int i = 0; i < candidateNum; i++)
        {
            int count = i+1;
            System.out.println(count + ". " + candidateList[i].getName());
        }


        String voteCounterName = inputString("Who should count the votes?");
        Candidate voteCounter = A3.getByUsername(voteCounterName, candidateList);
        

        int electionNum = inputInt("How many times should the election run?");
        Candidate[] votes = new Candidate[candidateNum * electionNum];

        for (int i = 0; i < electionNum; i++)
        {
            for (int j = 0; j < candidateNum; j++)
            {
                votes[j+(i*candidateNum)] = candidateList[j].vote(candidateList);
            }
        }

        // choosing the winner
        Candidate winner = voteCounter.selectWinner(votes);
        System.out.println("The winner is " + winner.getName() + ".");
        return;
    }
    
    
    // general print method
    public static void print(String msg)
    {
        System.out.println(msg);
        return;
    }

    // method that asks to input a value of type String
    public static String inputString(String msg)
    {
        Scanner scanner = new Scanner(System.in);
        print(msg);
        String ans = scanner.nextLine();
        return ans;
    }

    // method that asks to input a value of type int
    public static int inputInt(String msg)
    {
        Scanner scanner = new Scanner(System.in);
        print(msg);
        String stringAns = scanner.nextLine();
        int intAns = Integer.parseInt(stringAns);
        return intAns;
    }


    // returns candidate name
    public String getName()
    {
        return "Bob";
    }
    
    // returns candidate's slogan
    public String getSlogan()
    {
        return "More trees!";
    }
    
    public Candidate vote(Candidate[] candidates)
    {
        Candidate r = new Candidate_ec22741();
        
        if (candidates.length != 0)
        {
            r = candidates[candidates.length-1];
        }
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes)
    {
        Candidate currentWinner = new Candidate_ec22741();
        
        if(votes.length != 0)
        {
            int highestCount = 0;
            for (Candidate c : votes)
            {
                int count = 0;
                for (Candidate x : votes)
                    if (x == c)
                    {
                        count++;
                    }
                if (count > highestCount)
                {
                    highestCount = count;
                    currentWinner = c;
                }
            }
        }
        return currentWinner;
    }
    
}
