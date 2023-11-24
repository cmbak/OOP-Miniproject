package OOP.ec22697.MP;// File Candidate_ec22919.java
//
// Machine generated stub for Assignment 2
// Comment for Lab 4

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22919 extends Candidate {

    public String getName() {
        return "Jason";
    }
    
    public String getSlogan() {
        return "More Sleep!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return this candidate
        if (candidates.length == 0){
            return new Candidate_ec22919();
        }
       
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        // First search for self on the list of candidates.
        for (Candidate r : candidates)
           if (r.getSlogan().equals("More Sleep!"))
               return r;
        
        // If can't find self, return first candidate
        return candidates[0];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate currentWinner = new Candidate_ec22919();
        
        if (votes.length != 0) currentWinner = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        int largestCount = 0;
        for (int i=0;i<votes.length;i++) {
            int count = 0;
            for (int j=0;j<votes.length;j++)
                if (votes[i] == votes[j])
                    count++;
            if (count > largestCount) {
                largestCount = count;
                currentWinner = votes[i];
            }
        }

        return currentWinner;
        
    }
    
    
    // IO Helpers
    public static String getString(String text) {
        Scanner s = new Scanner(System.in);
        System.out.println(text);
        return s.nextLine();
    }
    public static <T> void print(T text){
        System.out.println(text);
        return;
    }
    
    //Prints currently added and valid candidates from array numbered from 1.
    public static void printCandidates(Candidate[] candidates) {
        int index = 1;
        print("The candidates are:");
        //for loop checking and printing for each candidate in the array
        for (Candidate c : candidates) {
            if (c != null) {
                print(index + ". " + c.getName());
                index++;
            }
        }
        //if index is 1, that means array is empty of candidates
        if (index == 1) {
            System.out.println("There are no candidates so far");
        }
    }
    
    //Running Election and asking if they want to run the election again with different counters and numberof Runs
    public static String Election(Candidate[] Finalcandidates, Candidate[] allContributions, String EndOption){
        while(!EndOption.equals("a")){
            //Who is counting votes
            String specificCounterName = getString("Please enter a username.");
            Candidate specificCounter = A3.getByUsername(specificCounterName, allContributions);
            //Numbers of Runs for elections
            int numberOfRuns = Integer.parseInt(getString("Number of runs for elections"));
            Candidate[] resultOfElections = new Candidate[numberOfRuns];
            //First for loop to run all elections
            for(int i =0;i<numberOfRuns;i++){
                Candidate[] allVotes = new Candidate[allContributions.length];
                    //Second for loop to run all votes for one election
                    for(int j =0; j<allContributions.length;j++){
                        try {
                            allVotes[j] = allContributions[j].vote(Finalcandidates);
                            } catch (Exception e) {
                                allVotes[j] = Finalcandidates[0];
                            }
                        }
                        resultOfElections[i] = specificCounter.selectWinner(allVotes);
            }
            //Print who is the common winner 
            print("Most common winner is " + specificCounter.selectWinner(resultOfElections).getName());
            //Option at the end of program asking if they want to exit or rerun same election with different counters and numberofRuns
            EndOption = getString("a)Exit \nb)Run same election");
            while(!EndOption.equals("a") && !EndOption.equals("b")){
                print("Invalid option");
                EndOption = getString("a)Exit \nb)Run same election");
            }
        }
        return EndOption;
    }
    
    //Main code to run elections
    public static void main(String[] args) {
        Candidate[] allContributions = A3.getCandidateArray();
        int ArrayIndex = 0;
        //There are 546 Possible candidates
        Candidate[] candidates = new Candidate[545];
        String option = "";
        String EndOption = "";
        while(!option.equals("c") ){
        
            //Ask for which option they want
            option = getString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if(option.equals("a")) {// do function a
                String specificName = getString("Please enter a username.");
                Candidate specificCandidate = A3.getByUsername(specificName, allContributions);
                if (specificCandidate != null){
                    candidates[ArrayIndex] = specificCandidate; 
                    ArrayIndex++;
                    printCandidates(candidates);
                }
                else print("User not found."); 
            }

            else if(option.equals("b")) {// do function b
                Random randI = new Random();
                int myRandInt = randI.nextInt(545);
                candidates[ArrayIndex] = allContributions[myRandInt];
                ArrayIndex++;
                printCandidates(candidates);
            }
            else if(option.equals("c")) {// do function c
                //Checking if there are any candidates
                if (candidates[0] != null){
                    //Create Finalcandidate array with max required length
                    Candidate[] Finalcandidates = new Candidate[ArrayIndex];
                    //Moving from one array to another
                    for(int n =0; n< Finalcandidates.length; n++){
                        Finalcandidates[n] = candidates[n];
                    }
                    while(!EndOption.equals("a")){
                        EndOption = Election(Finalcandidates,allContributions, EndOption);
                        }
                }
                else{
                    option = "";
                    print("There are no candidates currently selected. Enter a candidate before running an election!");
                }
            }
            else{
                print("Not valid option");
            }
        }    
    }
}
