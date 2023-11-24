package OOP.ec22697.MP;// File Candidate_ec22860.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

class Candidate_ec22860 extends Candidate {

    public String getName() {
        return "Rosanne";
    }

    public String getSlogan() {
        return "Vote for roses! Vote green!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22860();

        if (candidates.length != 0) r = candidates[0];

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {

        // code below is modified from sample ac1234 on JHUB
        Candidate currentWinner = new Candidate_ec22860();

        if (votes.length != 0) {

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
        }

        return currentWinner;
    }

    public static String userInput(String message)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(message);
        String inp = sc.nextLine();
        return inp;
    }
    public static int intInput(String message)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(message);
        int inp = sc.nextInt();
        return inp;
    }
    // adapted from ec221017
    public static void main(String[] a)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        String addedCandidate;
        int numOfCandidates;

        numOfCandidates = intInput("How many candidates will be in the election?");
        Candidate[] candidateList = new Candidate[numOfCandidates];
        System.out.println("Add candidates to the election: ");

        for (int i = 0; i < numOfCandidates; i++)
        {
            addedCandidate = userInput("Enter a username");
            candidateList[i] = A3.getByUsername(addedCandidate, allCandidates);
        }

        System.out.println("The candidates are:");  
        for(int i = 0; i < numOfCandidates; i++)
        {
            int currentOrderNum = i+1;
            System.out.println(currentOrderNum + ". " + candidateList[i].getName());
        }


        String voteCounterName = userInput("Who should count the votes?");
        Candidate voteCounter = A3.getByUsername(voteCounterName, candidateList);


        int electionNum = intInput("How many times should the election run?");
        Candidate[] votes = new Candidate[numOfCandidates * electionNum];

        for (int i = 0; i < electionNum; i++)
        {
            for (int j = 0; j < numOfCandidates; j++)
            {
                votes[j+(i*numOfCandidates)] = candidateList[j].vote(candidateList);
            }
        }
        //votes[j+(i*numOfCandidates)] = candidateList[j].vote(candidateList);

        Candidate winner = voteCounter.selectWinner(votes);
        System.out.println("The winner was " + winner.getName());
        return;
    }

}
