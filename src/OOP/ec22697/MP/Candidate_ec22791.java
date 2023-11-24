package OOP.ec22697.MP;// File Candidate_ec22791.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22791 extends Candidate {
    
    public String getName() {
        return "Aurora";
    }
    
    public String getSlogan() {
        return "hi!";
    }
    
    public Candidate vote(Candidate[] a) {

        if (a.length == 0){ 
            return new Candidate_ec22791();
        }
        
        for (Candidate b : a){
            if (b.getSlogan().equals("hi!")){
                return b;
            }
        }
        
        return a[a.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes){
        
        if (votes.length == 0){ 
            return new Candidate_ec22791();
        }
       
        Candidate currentWinner = votes[0];
        
        int highest = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes){
                if (x == c){
                    count++;
                }
            }
            if (count > highest) {
                highest = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }

// A3

     // input character
     public static char inputChar() {
         Scanner scanner = new Scanner(System.in);
         String a = scanner.nextLine();
         char b = a.charAt(0);
         return b;
     }

     // input string
     public static String inputString() {
         Scanner scanner = new Scanner(System.in);
         String c = scanner.nextLine();
         return c;
     }

     //input integer
     public static int inputInt() {
         Scanner scanner = new Scanner(System.in);
         int d = scanner.nextInt();
         return d;
     }

     // Find candidate by username in array
     public static int findCandidate(Candidate[] candidate) {
         int index = 0; // index of username in array c
         System.out.println("Please enter a username.");
         String username = inputString();

         for (int i=0; i<candidate.length; i++){
             if (candidate[i].un.equals(username)) {
                 index = i;
             }
         } // if not found, default to 0.

         return index;
     }

     // Append candidate to array
     public static Candidate[] addCandidate(Candidate[] candidate, Candidate[] myCandidates, int index) {
         if (index == -1) index = findCandidate(candidate); // Find index of username input in array candidate

         // Place in next null index
         for (int i=0; i<candidate.length; i++){
             if (myCandidates[i] == null) {
                 myCandidates[i] = candidate[index];
                 break;
             }
         }

         return myCandidates;
     }

     // output array
     public static void outputArray(Candidate[] candidate) {
         for (int i=0; i<candidate.length; i++){
             if (candidate[i] != null) {
                 System.out.println((i+1) + ". " + candidate[i].un);
             }
         }
     }

     //Running the election
     public static void runElection(Candidate[] candidate, Candidate[] myCandidates) {
         System.out.println("Who should count the votes?");
         int index = findCandidate(candidate); // Find index of username in array candidate

         System.out.println("How many times shall we run the election?");
         int n = inputInt();

         for (int i=0; i<n; i++){
             Candidate elected = candidate[index].selectWinner(myCandidates);
             System.out.println("The most common winner is: " + elected.un);
         }
     }

     // main method
     public static void main(String[] args) {
         Candidate[] candidate = A3.getCandidateArray();
         Candidate[] myCandidates = new Candidate[546];

         char choice = 'b'; // default
         Random rand;
         int index;

         while (choice != 'c') {
             System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
             choice = inputChar();

             // option a
             if (choice == 'a') {
                 myCandidates = addCandidate(candidate, myCandidates, -1); // -1 to find index of username in array candidate
                 outputArray(myCandidates);
             }

             // Adding a random candidate
             else if (choice == 'a') {
                 // generate random number
                 rand = new Random();
                 index = rand.nextInt(candidate.length);

                 myCandidates = addCandidate(candidate, myCandidates, index); // index of random candidate in array candidate
             }
         }

         // Running the election
         runElection(candidate, myCandidates);

     }
 } // end class Candidate_ec22791
