package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec221218 extends Candidate {
    
    public String getName() {
        return "Nadal";
    }    
        
    
     public String getSlogan() {
        return "Greatest Player";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec221218();
        
        // Search for a candidate involved in tennis.
        for (Candidate c : candidates)
            if (c.getSlogan().contains("Tennis"))
                return c;
                
        
        // Otherwise, choose at random from list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
       
        if (votes.length == 0) 
            return new Candidate_ec221218();
        
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
    
        public static String inputString(String message) {
          Scanner scanner = new Scanner(System.in);
          System.out.println(message);
          return scanner.nextLine();
     }

     public static int inputInteger(String message) {
         Scanner sc = new Scanner(System.in);
         System.out.println(message);
         return Integer.parseInt(sc.nextLine());
     }
    
        public static void main(String[] args) {
         Candidate[] allCandidates = A3.getCandidateArray();
         Candidate[] votes = new Candidate[allCandidates.length];
         int counter = 0;
         String inputOption = inputString("Would you like to a) add a candidate b) add a random candidate c) run election?");

         if (inputOption.equals("a")){
             String input = inputString("Enter username.");
             Candidate chosenCandidate = A3.getByUsername(input, allCandidates);
             votes[counter] = chosenCandidate;
             counter++;

             printCandidates(votes, counter);
         } 
         else if (inputOption.equals("b")){
             Random r = new Random();
             int randomInt = r.nextInt(allCandidates.length);
             votes[counter] = allCandidates[randomInt];
             counter++;
             printCandidates(votes, counter); 
         }

         else if (inputOption.equals("c")){
             String voteCounter = inputString("Who should count the votes?");
             Candidate chosenCounter = A3.getByUsername(voteCounter, allCandidates);
             int howManyTimes = inputInteger("How many elections should we run?");
             runElection(votes, counter, howManyTimes, chosenCounter, allCandidates);
         }
     }

     public static void printCandidates(Candidate[] c, int counter) {
         System.out.println("The candidates include: ");
         for (int i = 0; i < counter; i++) {
             System.out.println(c[i].getName() + " with a slogan: " + c[i].getSlogan());
         }
         return;
     }

     public static void runElection(Candidate[] votes, int counter, int howManyTimes, Candidate voteCounter, Candidate[] allCandidates){
         Candidate[] election = new Candidate[counter];
         for (int i = 0; i < counter; i++) {
             election[i] = votes[counter];
         }

         Candidate[] winningCandidates = new Candidate[howManyTimes * (counter - 1)];

         for (int i = 0; i <= howManyTimes; i++) {
             for (int j = 0; j < counter; j++) {
                     winningCandidates[(counter * i) + j] = election[j].vote(election);
             }
         }
         Candidate Winner = voteCounter.selectWinner(winningCandidates);
         System.out.println("The most common winner is " + Winner.getName());
     }
    
}
