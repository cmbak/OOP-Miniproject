package OOP.ec22697.MP;// File Candidate_ec22409.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22409 extends Candidate {
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Candidate[] ca = A3.getCandidateArray();
        Candidate[] currentCandidates = new Candidate[ca.length];
        int counter = 0;
        String ans = "";
        
        while (!ans.equals("c"))
        {
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            ans = sc.nextLine();
            if (ans.equals("a"))
            {
                currentCandidates = addCandidate(ca,currentCandidates,sc,counter);
                printCand(counter,currentCandidates);
                counter++;
            }
            else if (ans.equals("b"))
            {
                currentCandidates = randomCandidate(ca,currentCandidates,counter);
                printCand(counter,currentCandidates);
                counter++;
            }
        }
        runElection(ca,currentCandidates,sc,counter);
    }
    public static void runElection(Candidate[] ca, Candidate[] cc, Scanner sc, int counter)
    {
        boolean returns_null = true;
        Candidate voting_cand = null;
        while(returns_null == true)
        {
            System.out.println("Who should count the votes?");
            String vote_counter = sc.nextLine();
            voting_cand = A3.getByUsername(vote_counter, ca);
            if (voting_cand != null)
            {
                returns_null = false;
            }
        }
        System.out.println("How many times should we run this election??");
        int num_of_elections = value_Check(sc);
        
        Candidate[] winners = new Candidate[counter];
        Candidate[] temp_votes = new Candidate[cc.length];
        for (int i = 0; i<counter; i++) {
            for (int j = 0; j < ca.length; j++) {
                try
                {
                    //Attempt to take a vote
                    temp_votes[j] = ca[i].vote(cc);
                }
                catch (Exception e) 
                {
                    //If attempted vote fails then just vote for myself
                    temp_votes[j] = new Candidate_ec22409();
                }
            }
            winners[i] = voting_cand.selectWinner(temp_votes);
        }
        Candidate top_winner = voting_cand.selectWinner(winners);
        System.out.println("The most common winner is : " + top_winner.getName());
    }
    public static int value_Check(Scanner sc)
    {
        //This function will keep asking for an input until a valid value is typed in
        int valid_check = 0; //Used to check whether the value is suitable
        while (valid_check <= 0)//Loops until the user types an integer above 0
        {
            while (!sc.hasNextInt())//Loops until user types an integer
            {
                System.out.println("Invalid Input");
                System.out.println("Try again");
                sc.nextLine();
            }

            valid_check = Integer.parseInt(sc.nextLine().trim());
            if (valid_check <= 0)//If the value is invalid it will print this
            {
                System.out.println("Invalid Input");
                System.out.println("Try again");
            }
        }
        return valid_check; //Returns a valid value back
        
    }
    
    public static Candidate[] addCandidate(Candidate[] ca, Candidate[] cc, Scanner sc, int counter)
    {
        System.out.println("Please enter a username");
        String candidate = sc.nextLine();
        Candidate tempCand = A3.getByUsername(candidate, ca);
        cc[counter] = tempCand;
        return cc;
    }
    public static void printCand(int counter, Candidate[] cc)
    {
        System.out.println("Candidates are");
        for (int i = 0; i<=counter; i++) {
            Candidate printerCand = cc[i];
            System.out.println((i+1) + ". Name: " + printerCand.getName() + " Slogan: " + printerCand.getSlogan()); 
        }
        return;
    }
        
    public static Candidate[] randomCandidate(Candidate[] ca, Candidate[] cc, int counter)
    {
        Random number = new Random();
        int person = number.nextInt(ca.length-1);
        Candidate tempCand = ca[person];
        cc[counter] = tempCand;
        return cc;
    }
    
    
    
    
    
    //A2 CONTRIBUTION

    public String getName() {
        return "GamerGroup";
    }

    public String getSlogan() {
        return "Make games great!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22409();

        if (candidates.length == 0)
        {
            return r;
        }
        for (Candidate c : candidates)
        {
            if(c.getSlogan().equals("Make money!"))
            {
                return c;
            }
        }

        for (Candidate c : candidates)
        {
            if(c.getName().equals("Heisenberg"))
            {
                return c;
            }
        }
        Random number = new Random();
        int person = number.nextInt(candidates.length);
        return candidates[person];
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22409();

        if (votes.length == 0)
        {
            return r;
        }

        // Add code that either counts the votes
        // or uses some other way to return an element
        // of the array vote as the winner of an election.
        Random number = new Random();
        int person = number.nextInt(votes.length);
        return votes[person];
    }

}
