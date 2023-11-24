package OOP.ec22697.MP;// File Candidate_ec22615.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22615 extends Candidate {
    public static void main(String[] args) {
        // adapted from ah21407
        // Getting all the candidate objects and a new array to store each objects votes
        final Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] eachVote = new Candidate[allCandidates.length];

        
        // Getting the user selection
        String option = inputString(
                "Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        if (option.equals("a")) {
            int numberOfCandidates = inputInteger("How many candidates do you want to add?");
            Candidate[] candidates = new Candidate[numberOfCandidates];
            for (int i = 0; i < numberOfCandidates; i++) {
                String candidateUsername = inputString("Enter the username for candidate " + (i + 1) + ":");
                Candidate temp = A3.getByUsername(candidateUsername, allCandidates);
                if (temp == null) {
                    print("User not found");
                } else {
                    candidates[i] = temp;
                }
            }
            print("The Candidates are as follows:");
            for (Candidate temp : candidates) {
                print(temp.getName());
            }
        }
        else if(option.equals("b")){

        }
        else if(option.equals("c")){
            // -- Selecting the counter of the votes option c --
            
            boolean userFound = false;
            while (userFound) {
                String counterUsername = inputString("Enter the username of the counter:");
                Candidate voteCounter = A3.getByUsername(counterUsername, allCandidates);
                if (voteCounter == null) {
                    print("User not found");
                }
                else {
                    userFound = true;
                }  
            }
        }
        

    }

    // print to screen
    public static void print(String str) {
        System.out.println(str);
    }

    // get string
    public static String inputString(String str) {
        Scanner sc = new Scanner(System.in);
        print(str);
        String userResponse = sc.nextLine();
        return userResponse;
    }

    // get integer
    public static int inputInteger(String str) {
        Scanner sc = new Scanner(System.in);
        print(str);
        int userResponse = Integer.parseInt(sc.nextLine());
        return userResponse;
    }
    
    
    public String getName() {
        return "Farhan Keriwala";
    }
    
    public String getSlogan() {
        return "Make changes, produce results!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22615();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        if (candidates.length == 0) {
            return new Candidate_ec22615();
        }
        else{
            Random rand = new Random();
            int randInt = rand.nextInt(candidates.length);
            r = candidates[randInt];
        }
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22615();
        
        if (votes.length != 0) r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        if (votes.length == 0) {
            return new Candidate_ec22615();
        }
        else {
            Random rand = new Random();
            int randInt = rand.nextInt(votes.length);
            r = votes[randInt];
        }
        return r;
    }
    
}
