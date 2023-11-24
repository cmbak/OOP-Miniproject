package OOP.ec22697.MP;// File Candidate_ec22660.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

class Candidate_ec22660 extends Candidate {
    
    //from sample ac1234
    public String getName() {
        return "Jimmy";
    }
    
    public String getSlogan() {
        return "More gin!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        Candidate r = new Candidate_ec22660();
        
        if (candidates.length != 0) r = candidates[candidates.length-1];
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        Candidate currentWinner = new Candidate_ec22660();
        
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

    public static void print(String message)  // method to make printing messages easier
    {
        System.out.println(message);
    }

    // method for storing String input of the user
    //
    public static String inputString(String message)
    {
        Scanner sc = new Scanner(System.in);

        print(message);
        String inp = sc.nextLine();
        return inp;
    }//END inputString


    // method for storing int input of the user
    //
    public static int inputInt(String message)
    {
        Scanner sc = new Scanner(System.in);

        print(message);
        int inp = sc.nextInt();
        return inp;
    }//END inputInt

    public static void main(String[] a)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        String addedCandidate;
        int numOfCandidates;

        numOfCandidates = inputInt("How many candidates will be in the election?");
        Candidate[] candidateList = new Candidate[numOfCandidates];
        print("Add candidates to the election: ");

        for (int i = 0; i < numOfCandidates; i++)
        {
            addedCandidate = inputString("Enter a username");
            candidateList[i] = A3.getByUsername(addedCandidate, allCandidates);
        }

        print("The candidates are:");  // from ec221017
        for(int i = 0; i < numOfCandidates; i++)
        {
            int currentOrderNum = i+1;
            print(currentOrderNum + ". " + candidateList[i].getName());
        }


        String voteCounterName = inputString("Who should count the votes?");
        Candidate voteCounter = A3.getByUsername(voteCounterName, candidateList);
        

        int electionNum = inputInt("How many times should the election run?");
        Candidate[] votes = new Candidate[numOfCandidates * electionNum];

        for (int i = 0; i < electionNum; i++)
        {
            for (int j = 0; j < numOfCandidates; j++)
            {
                votes[j+(i*numOfCandidates)] = candidateList[j].vote(candidateList);
            }
        }
        //votes[j+(i*numOfCandidates)] = candidateList[j].vote(candidateList);

        // choosing the winner
        Candidate winner = voteCounter.selectWinner(votes);
        print("The winner was " + winner.getName());
        return;
    }
    
}
