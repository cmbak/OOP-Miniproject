package OOP.ec22697.MP;// File Candidate_jpp308479.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;
class Candidate_jpp308479 extends Candidate {
    
    public String getName() {
        return "Nikita";
    }
    
    public String getSlogan() {
        return "More doors";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of myself.
        if (candidates.length == 0) 
            return new Candidate_jpp308479();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Kim"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return myself.
        if (votes.length == 0) 
            return new Candidate_jpp308479();
        
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
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] selectedCandidates = new Candidate[allCandidates.length];
        int selectedCandidateCount = 0;

        while (true) {
            System.out.println("= Running Repeated Elections =");
            System.out.println("Candidates are:");

            if (selectedCandidateCount == 0) {
                System.out.println("None");
            } else {
                for (int i = 0; i < selectedCandidateCount; i++) {
                    System.out.println((i + 1) + ". " + selectedCandidates[i].getName());
                }
            }

            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("a")) {
                System.out.println("Please enter a username.");
                String username = scanner.nextLine();
                // Add the candidate with the given username.
                Candidate candidate = A3.getByUsername(username, allCandidates);
                if (candidate != null) {
                    selectedCandidates[selectedCandidateCount++] = candidate;
                } else {
                    System.out.println("Invalid username.");
                }
            } else if (choice.equalsIgnoreCase("b")) {
                // Add a random candidate.
                selectedCandidates[selectedCandidateCount++] = allCandidates[new Random().nextInt(allCandidates.length)];
            } else if (choice.equalsIgnoreCase("c")) {
                System.out.println("Who should count the votes?");
                String counterName = scanner.nextLine();
                // Find the candidate to count the votes
                Candidate counter = null;
                for (int i = 0; i < selectedCandidateCount; i++) {
                    if (selectedCandidates[i].getName().equals(counterName)) {
                        counter = selectedCandidates[i];
                        break;
                    }
                }

                if (counter != null) {
                    System.out.println("How many times shall we run the election?");
                    int repetitions = Integer.parseInt(scanner.nextLine());
                    // Run the election 'repetitions' number of times and report the most common winner(s).
                    // This should be replaced with a more meaningful implementation.
                    System.out.println("Most common winner is " + counter.getName() + ".");
                    System.out.println("There were no other winners.");
                } else {
                    System.out.println("Invalid counter name.");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        }
   
}
    
}
