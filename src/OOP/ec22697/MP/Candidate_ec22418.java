package OOP.ec22697.MP;// File Candidate_ec22418.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22418 extends Candidate {
    
    public String getName() {
        return "Adekoyejo";
    }
    
    public String getSlogan() {
        return "Tax the rich!";
    }
    
    public Candidate vote(Candidate[] candidates) {
     
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
         // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Adekoyejo")) {
                return c;
            }

        // Otherwise search for a like minded candidate.
        for (Candidate b : candidates)
            if (b.getSlogan().equals("Tax the rich!")) {
                return b;
            }
        
        // Otherwise, default to random candidate on list.
        Random r = new Random();
        int d = r.nextInt(candidates.length);
        return candidates[d];
     
    }
    
    public Candidate selectWinner(Candidate[] votes) {
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) {
            return new Candidate_ec22418();
        }
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }
            
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
        
    }
    
    public static void Election(Candidate[] names, Candidate[] votes, int candidates, Candidate cName) {

          for (int i=0; i<candidates; i++) {
              try {
                  votes[i] = names[i].vote(names);
              }
              catch (Exception e) {
                  System.out.println("That vote was invalid.");
              }
              finally {
                  continue;
              }
          }

          Candidate winner = cName.selectWinner(votes);
          System.out.println("The winner is: " + winner.getName());

          return;
     }

     public static void main(String[] args) {

          Scanner scanner = new Scanner(System.in);
          Candidate[] names = A3.getCandidateArray();
          Candidate[] votes = new Candidate[names.length];
          int candidates = names.length;
          String response;

          System.out.println("Who should count the votes?");
          response = scanner.nextLine();
          Candidate cName = A3.getByUsername(response, names);
          Election(names, votes, candidates, cName);
         return;
     }
    
}
