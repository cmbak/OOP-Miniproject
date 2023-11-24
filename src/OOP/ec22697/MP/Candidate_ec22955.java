package OOP.ec22697.MP;// File Candidate_ec22955.java
//

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22955 extends Candidate {
    
    public static void main(String[] args) {
        
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] candidatesInElection = new Candidate[0];
        System.out.println("= Running Repeated Elections =");
        printMenu(allCandidates, candidatesInElection);
    }
    
    public static void printMenu(Candidate[] allCandidates, Candidate[] candidatesInElection) {
        
        System.out.println("Candidates are");
        if (allCandidates.length==0) {
            System.out.println("None");
        }
        else {
            for (int i=0; i<allCandidates.length; i++) {
                System.out.println(allCandidates[i].getName());
            }
        }
            
        String menuChoice;
        do {
            menuChoice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if (menuChoice.equals("a")) {
                Candidate[] newCandidatesInElection = addSpecificCandidate(allCandidates, candidatesInElection);
                printMenu(allCandidates, newCandidatesInElection);
            }
            else if (menuChoice.equals("b")) {
                Candidate[] newCandidatesInElection = addRandomCandidate(allCandidates, candidatesInElection);
                printMenu(allCandidates, newCandidatesInElection);
            }
            else if (menuChoice.equals("c")) {
                String voteCounter = inputString("Who should count the votes?");
                int howManyElections = Integer.parseInt(inputString("How many times shall we run the election?"));
                runElection(allCandidates, candidatesInElection, voteCounter, howManyElections);
            }
            else {
                System.out.println("enter a/b/c");
            }
        }
        while (menuChoice!="a" && menuChoice!="b" && menuChoice!="c");
    }
    
    public static Candidate[] addSpecificCandidate(Candidate[] allCandidates, Candidate[] candidatesInElection) {
        String specificName = inputString("Which specific user would you like to include?");
        Candidate specificCandidate = A3.getByUsername(specificName, allCandidates);
        if (specificCandidate == null) {
             specificCandidate = new Candidate_ec22548();
        }
        Candidate[] newCandidatesInElection = addCandidateToArray(candidatesInElection, specificCandidate);
        return newCandidatesInElection;
    }
    
    public static Candidate[] addRandomCandidate(Candidate[] allCandidates, Candidate[] candidatesInElection) {
        
        Random r = new Random();
        int c = r.nextInt(allCandidates.length);
        Candidate[] newCandidatesInElection = addCandidateToArray(candidatesInElection, allCandidates[c]);
        return newCandidatesInElection;
    }
    
    public static Candidate[] addCandidateToArray(Candidate[] candidatesInElection, Candidate candidateToAdd) {
        
        Candidate[] newCandidatesInElection = new Candidate[candidatesInElection.length+1];
        for (int i=0; i<candidatesInElection.length; i++) {
            newCandidatesInElection[i] = candidatesInElection[i];
        }
        newCandidatesInElection[candidatesInElection.length+1] = candidateToAdd;
        return newCandidatesInElection;
    }
                                                    
    public static void runElection(Candidate[] allCandidates, Candidate[] candidatesInElection, String counterName, int howManyElections) {
        
        Candidate[] votes = new Candidate[candidatesInElection.length];
        Candidate[] winners = new Candidate[howManyElections];
        Candidate voteCounter = A3.getByUsername(counterName, allCandidates);
        
        for (int i=0; i<howManyElections; i++) {
            for (int j=0; j<allCandidates.length; j++) {
                votes[j] = allCandidates[j].vote(candidatesInElection);
            }
            winners[i] = voteCounter.selectWinner(votes);
        }
        
        Candidate overallWinner = voteCounter.selectWinner(winners);
        System.out.println("Most common winner is " + overallWinner.getName());
        System.out.println("Their slogan is " + overallWinner.getSlogan());
    }
    
    public String getName() {
        return "annie";
    }
    
    public String getSlogan() {
        return "9ams shouldn't exist";
    }
    
    //searches candidates array for a specific slogan, then returns the candidate if found
    public Candidate vote(Candidate[] candidates) {
        
    //if array is empty return a new instance of friend's class
      if (candidates.length == 0) {
            return new Candidate_ec22548();
      }
        
        for (int i=0 ; i<candidates.length; i++) {
            if (candidates[i].getSlogan().equals("Cats should be promoted as gods")) {
                return candidates[i];
            }
        }

        return candidates[candidates.length-1];
    }
    
    //finds and returns the candidate with the most votes
    public Candidate selectWinner(Candidate[] votes) {
        
        //if array is empty, return instance of friend's class.
        if (votes.length == 0) {
            return new Candidate_ec22548();
        }
        
        // default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner;
    }
    
    // a method to input a string using a Scanner
    public static String inputString(String message) {
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        input = scanner.nextLine();

        return input;
    }
}
