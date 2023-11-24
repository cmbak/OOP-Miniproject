package OOP.ec22697.MP;// File Candidate_ec22462.java
//
// My contribution to A3

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22462 extends Candidate {
    
    public String getName() {
        return "John Cena";
    }
    
    public String getSlogan() {
        return "You can't see me!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length==0) {
            return new Candidate_eey577(); // Returns Adam's class instance if array is empty
        }

        for (Candidate c : candidates) {
            String [] vowels = {"a","e","i","o","u"};
            for (int i=0;i<vowels.length;i++) {
                if (c.getSlogan().contains(vowels[i])) {
                    return c; // Returns the first candidate found with vowels in their slogan.
                }
            }
        }
        return candidates[68]; // Otherwise, returns the 69th candidate if, somehow, no one has vowels in their 
                               // slogans (which is almost impossible since default slogan stub has vowels.)
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length==0) {
            return new Candidate_eey577(); // Returns Adam's class instance if array is empty
        }
        
        for (Candidate c : votes) {
            if ((c.getName().contains("J") || c.getName().contains("j"))&& !c.getName().equals("John Cena")) {
                return c; // Returns any candidate name with any Js or js within it. (excluding mine)
            }
        }

        Random r = new Random();
        int a = r.nextInt(votes.length);
        return votes[a]; // Otherwise, returns a random candidate from list.
    }

    // must:
    // add specific candidates
    // run same election many times + report most common winner(s)
    public static void main (String [] args) {
        Candidate[] everyCandidate = A3.getCandidateArray();
        Scanner s = new Scanner(System.in);
        boolean validSize = false;
        int electionSize=0;
        while (validSize!=true) { //input validation for no. candidates
            System.out.println("How many candidates do you want in the election? (must be int and >0)");
            electionSize = s.nextInt();
            if (electionSize < 1) {System.out.println("Must be >0. Try again.");}
            else {validSize=true;}
        }
        Candidate[] electionList = new Candidate[electionSize]; // makes current candidate list

        System.out.println("Current candidate list:");
        for (int i=0;i<electionSize;i++) {
            if (i<1) {System.out.println("None");} // none as first iteration
            else {
                for (int k=0;k<electionList.length;k++) {
                    if (electionList[k]!=null) {System.out.println(electionList[k].getName());}
                } // only prints if string is not null
            }

            boolean validCandidate=false;
            while (validCandidate!=true) { // input validation for specific candidate
                System.out.println("Which specific username do you want to add?");
                String specificCandidate = s.nextLine();
                Candidate processCandidate = A3.getByUsername(specificCandidate, everyCandidate);
                if (processCandidate!=null) {electionList[i] = processCandidate; validCandidate=true;}
                else {System.out.println("Cannot find username to add. Try again.");}
            }
        }
        boolean validCounter = false;
        Candidate processCounter = new Candidate_ec22462();
        while (validCounter!=true) { // input validation for vote counter
            System.out.println("Which user do you want to count the votes?");
            String specificCounter = s.nextLine();
            processCounter = A3.getByUsername(specificCounter, everyCandidate);
            if (processCounter!=null) {validCounter=true;}
            else {System.out.println("Cannot find username to count votes. Try again.");}
        }

        boolean validCycles = false;
        int cycles=0;
        while (validCycles!=true) { // input validation for election runs
            System.out.println("How many times do you want to run the election? (must be int and >0)");
            cycles = s.nextInt();
            if (cycles < 1) {System.out.println("Must be >0. Try again.");}
            else {validCycles=true;}
        }
        
        Candidate[] votes = new Candidate[cycles*electionSize];
        for (int a=0;a<cycles;a++) { // tallies up votes - code adapted from user ec22446!
            for (int b=0;b<electionSize;b++) {votes[b+(a*electionSize)] = electionList[b].vote(electionList);}
        }
        
        Candidate commonWinner = processCounter.selectWinner(votes); // uses vote counter's process of selecting winner.
        System.out.println("Most common winner: " + commonWinner.getName());
    }
}
