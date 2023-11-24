package OOP.ec22697.MP;// File Candidate_ec22795.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

class Candidate_ec22795 extends Candidate {
    
    public String getName() {
        return "Meri";
    }
    
    public String getSlogan() {
        return "More of me!";
    }
    
    //A method that can provide intigers and in the same time catch invalid values.
     public static int inputInt(String message) {
         Scanner scanner = new Scanner(System.in);
         print(message);
         int input = -1;

         try {
             input = Integer.parseInt(scanner.nextLine());
         } catch (Exception e) {
             print("Invalid input! Must be integer.");
         }

         return input;
     }

     //A method that you can input a integer 
     public static String input(String message) {
         Scanner sc = new Scanner(System.in);
         print(message);
         return sc.nextLine();
     }

    //A method that prints messages
     public static void print(String message) {
         System.out.println(message);
     }
    
    public static void main(String[] args) {
        
        Candidate[] array_of_candidates = A3.getCandidateArray();
        Candidate[] allcandidates = new Candidate[0];
        String option = "";
        while(!option.equalsIgnoreCase("c")) {
             option = input("Would you like to a) add a specific candidate b) run the election c) exit?");
             if(option.equalsIgnoreCase("a")) {
                 allcandidates = addCandidate(array_of_candidates);
                 print("Candidates are:");
                 if(allcandidates != null && allcandidates.length > 0)
                     for(Candidate candidate : allcandidates)
                         if(candidate != null)
                             print(candidate.getName() + " : " + candidate.getSlogan());
                 else
                     print("Candidates list can not be empty!");
             } else if(option.equalsIgnoreCase("b")) {
                 
                 int runtimes = inputInt("How many times shall we run the election?");
                 if(runtimes <= 0) {
                     
                     print("Election must be run a positive number of times! (> 0)");
                     return;
                 }
                 Candidate winner = getMost(getWinners(runtimes, allcandidates,array_of_candidates));
                 print("Most common winner is:");
                 print(winner.getName() + ". Slogan: " + winner.getSlogan());
             }
         }
     }

     // Find the winners 
     public static Candidate[] getWinners(int repetition, Candidate[] candidates, Candidate[] allcand) 
     {
         Candidate[] winners = new Candidate[repetition];
         Candidate[] votes = new Candidate[allcand.length];
         Candidate candidate = A3.getByUsername(input("Who should count the votes?"), allcand);
         for(int i = 0; i < repetition; i++) {
             for(int j = 0; j < allcand.length; j++) {
                 votes[j] = allcand[j].vote(candidates);
             }
             winners[i] = candidate.selectWinner(votes);
         }
         return winners;
     }

     // Get the most frequent voted candidate from array of winners
     public static Candidate getMost(Candidate[] winners) {
         Candidate winner = null;
         int high = 0;
         for(Candidate c : winners) {
             int count = 0;
             for(Candidate x : winners)
                 if(x == c)
                     count++;
             if(count >= high) {
                 high = count;
                 winner = c;
             }
         }
         return winner;
     }

     // Add user-inputted candidate(s) to array and return the list
     public static Candidate[] addCandidate(Candidate[] candidates) {
         int num_of_candidates = inputInt("Enter the number of candidates you wish to add:");
         if(num_of_candidates <= 0) {
             System.out.println("Number of candidates must be positive.");
             return null;
         }
         Candidate[] candis = new Candidate[num_of_candidates];
         for(int i = 0; i < num_of_candidates; i++) {
              Candidate candidate = A3.getByUsername(input("Enter username:"), candidates);
              if(candidate != null) {
                  candis[i] = candidate;
              } else {
                  print("Invalid input! Candidate can't be null.");
              }
         }
         return candis;
     }


    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class. 
        if (candidates.length == 0) 
            return new Candidate_ec22795();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More of me!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("GK"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22795();
        
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
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
      
    }
    
}
