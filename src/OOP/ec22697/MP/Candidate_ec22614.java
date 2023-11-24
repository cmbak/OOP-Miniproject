package OOP.ec22697.MP;// File Candidate_ec22614.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22614 extends Candidate {
    
    public String getName() {
        return "Tony Stark";
    }
    
    public String getSlogan() {
        return "We have a Hulk!";
    }
    
    //returns input as string
    public static String inputString(String message) {
         Scanner scanner = new Scanner(System.in);
         System.out.println(message);
         return scanner.nextLine();
    }

    //returns input as integer
    public static int inputInteger(String message) {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(scanner2.nextLine());
    }
    
    
    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] votes = new Candidate[allCandidates.length];
        int counter = 0;
        String input = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?").toLowerCase();

        if (input.equals("a")){
            String username = inputString("Please enter a username.");
            Candidate newCandidate = A3.getByUsername(username, allCandidates);
            votes[counter] = newCandidate;
            counter++;
            
            printCandidates(votes, counter);
        } 
        else if (input.equals("b")){
            Random random = new Random();
            int randomInt = random.nextInt(allCandidates.length);
            votes[counter] = allCandidates[randomInt];
            counter++;
            printCandidates(votes, counter); 
        }
        
        else if (input.equals("c")){
            String voteCounter = inputString("Who should count the votes?");
            Candidate chosenCounter = A3.getByUsername(voteCounter, allCandidates);
            int howManyTimes = inputInteger("How many times shall we run the election?");
            runElection(votes, counter, howManyTimes, chosenCounter, allCandidates);
        }
    }

    public static void printCandidates(Candidate[] candidates, int counter) {
        System.out.println("Candidates are: ");
        for (int i = 0; i < counter; i++) {
            System.out.println(candidates[i].getName() + " with their slogan: " + candidates[i].getSlogan());
        }
        return;
    }

    public static void runElection(Candidate[] votes, int counter, int howManyTimes, Candidate voteCounter, Candidate[] allCandidates){
        Candidate[] newElection = new Candidate[counter];
        for (int i = 0; i < counter; i++) {
            newElection[i] = votes[counter];
        }

        Candidate[] winningCandidates = new Candidate[howManyTimes * (counter - 1)];
        
        for (int i = 0; i <= howManyTimes; i++) {
            for (int j = 0; j < counter; j++) {
                try {
                    winningCandidates[(counter * i) + j] = newElection[j].vote(newElection);
                } catch (Exception e) {
                     //If voting doesn't work, vote for myself
                    winningCandidates[j] = new Candidate_ec22614();
                }
            }
        }
        //stores the winning candidate
        Candidate actualWinner = voteCounter.selectWinner(winningCandidates);
        System.out.println("Most common winner is: " + actualWinner.getName());
    }
    
    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22614();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("We have a Hulk"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Mr. Bean"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22614();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
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
        
        return currentWinner;
    }
    
}
