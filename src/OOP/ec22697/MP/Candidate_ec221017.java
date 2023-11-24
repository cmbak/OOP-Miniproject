package OOP.ec22697.MP;// File Candidate_ec221017.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

class Candidate_ec221017 extends Candidate {
    
    //from sample ac1234
    public String getName() {
        return "Robin";
    }
    
    public String getSlogan() {
        return "More trees!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        Candidate r = new Candidate_ec221017();
        
        if (candidates.length != 0) r = candidates[candidates.length-1];
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        Candidate currentWinner = new Candidate_ec221017();
        
        if(votes.length != 0) {
        
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

    // method for storing String input of the user
    //
    public static String inputString(String message)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(message);
        String inp = sc.nextLine();
        return inp;
    }//END inputString


    // method for storing int input of the user
    //
    public static int inputInt(String message)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(message);
        int inp = sc.nextInt();
        return inp;
    }//END inputInt

    public static void main(String[] a)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        String addedCandidate;
        int candidateNum;

        candidateNum = inputInt("How many candidates will be in the election?");
        Candidate[] candidateList = new Candidate[candidateNum];
        System.out.println("Add candidates to the election: ");

        for (int i = 0; i < candidateNum; i++)
        {
            addedCandidate = inputString("Enter a username");
            candidateList[i] = A3.getByUsername(addedCandidate, allCandidates);
        }

        System.out.println("The candidates are:");
        for(int i = 0; i < candidateNum; i++)
        {
            int currentOrderNum = i+1;
            System.out.println(currentOrderNum + ". " + candidateList[i].getName());
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
        //votes[j+(i*candidateNum)] = candidateList[j].vote(candidateList);

        // choosing the winner
        Candidate winner = voteCounter.selectWinner(votes);
        System.out.println("The winner was " + winner.getName());
        return;
    }
    
}
