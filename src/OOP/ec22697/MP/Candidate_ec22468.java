package OOP.ec22697.MP;// Lab4 4.3  change.
// Lorem ipsum dolor
// File Candidate_ec22468.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22468 extends Candidate {
    
    public String getName() {
        return "YoRHa";
    }
    
    public String getSlogan() {
        return "Glory To Mankind";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22468(); // Empty Array

        // Check for candidate called "Walter Hartwell White"
        for (Candidate c : candidates) {
            if (c.getName().equals("Walter Hartwell White")) {
                return r;
            }
        }

        if (candidates.length > 5) r = candidates[4]; // Lucky number 4! (or 5..?)
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {        
        Candidate r = new Candidate_ec22468(); // Empty Array
        
        // Empty Array
        if (votes.length == 0){
            return r;
        }
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.

        // Find least popular candidate
        int leastPopular = 1000;
        for (Candidate i : votes) {

            int count = 0;
            for (Candidate j : votes) {
                if (i == j) {
                    count++; // Count number of votes for candidate i
                }
            }

            if (count < leastPopular) {
                leastPopular = count;
                r = i;
            }
        }
        
        return r;
    }

    /////////////////A3////////////////////

    // input character
    public static char inputChar() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        char c = s.charAt(0);
        return c;
    }

    // input string
    public static String inputString() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        return s;
    }

    //input int
    public static int inputInt() {
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        return i;
    }

    // Find candidate in array
    public static int findCandidate(Candidate[] c) {
        int index = 0; // index of username in array c
        System.out.println("Please enter a username.");
        String username = inputString();
        
        for (int i=0; i<c.length; i++){
            if (c[i].un.equals(username)) {
                index = i;
            }
        } // if not found, default to 0.
        
        return index;
    }

    // Add candidate to array
    public static Candidate[] addCandidate(Candidate[] c, Candidate[] myCandidates, int index) {
        if (index == -1) index = findCandidate(c); // Find index of username in array c
        
        // Place in next null index
        for (int i=0; i<c.length; i++){
            if (myCandidates[i] == null) {
                myCandidates[i] = c[index];
                break;
            }
        }//end for

        return myCandidates;
    }

    // output array
    public static void outputArray(Candidate[] c) {
        for (int i=0; i<c.length; i++){
            if (c[i] != null) {
                System.out.println((i+1) + ". " + c[i].un);
            }
        }
    }

    // Run election
    public static void runElection(Candidate[] c, Candidate[] myCandidates) {
        System.out.println("Who should count the votes?");
        int index = findCandidate(c); // Find index of username in array c

        System.out.println("How many times shall we run the election?");
        int n = inputInt();
        
        for (int i=0; i<n; i++){
            Candidate elected = c[index].selectWinner(myCandidates);
            System.out.println("The most common winner is: " + elected.un);
        }
    }

    // main
    public static void main(String[] args) {
        Candidate[] c = A3.getCandidateArray();
        Candidate[] myCandidates = new Candidate[546];
        
        char choice = 'b'; // default choice
        Random rand;
        int index;

        while (choice != 'c') {
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            choice = inputChar();

            // option a
            if (choice == 'a') {
                myCandidates = addCandidate(c, myCandidates, -1); // -1 to find index of username in array c
                outputArray(myCandidates);
            }

            // Add random candidate
            else if (choice == 'a') {
                // generate random number
                rand = new Random();
                index = rand.nextInt(c.length);
                
                myCandidates = addCandidate(c, myCandidates, index); // index of random candidate in array c
            }
        }

        // Run election
        runElection(c, myCandidates);
        //end

    } // end main
} // end class Candidate_ec22468
