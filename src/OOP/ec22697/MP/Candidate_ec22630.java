package OOP.ec22697.MP;// File Candidate_ec22630.java
//
// Machine generated stub for Assignment 2

// File Candidate_ec22630.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22630 extends Candidate {
    
    public String getName() {
        return "Harvinder";
    }
    
    public String getSlogan() {
        return "More trees";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22630();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Kim"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22630();
        
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
    public static int inputint(String message)
    // gets integer input from the user
{
    Scanner scanner = new Scanner(System.in);
    int answer;

    System.out.println(message);
    answer = Integer.parseInt(scanner.nextLine());
   
    return answer;

}
public static String inputString (String message)
{
    // gets inpput from the user
    Scanner scanner = new Scanner(System.in);
    String answer;
    System.out.println(message);
    answer = scanner.nextLine();
    return answer;
}

public static void main(String[] args){
    int counter = 0;
   Candidate[] everyCandidate = A3.getCandidateArray();
   Candidate[] votes = new Candidate[everyCandidate.length];

   String ans = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election");

   if (ans.equals("a")){
    String user = inputString("Please enter a username.");
    Candidate newCandidate = A3.getByUsername(user,everyCandidate);
    votes[counter] = newCandidate;
    counter++;
    printCandidate(votes, counter);
   }
   else if(ans.equals("b")){
    Random r = new Random();
    int intRandom = r.nextInt(everyCandidate.length);
    votes[counter] = everyCandidate[intRandom];
    counter++;
    printCandidate(votes, counter);
   }
   else if (ans.equals("c")){
    String countVote = inputString("Who shold count the votes?");
    Candidate choseCounter = A3.getByUsername(countVote,everyCandidate);
    int times = inputint("How many times shall we run the election?");
    runElection(votes,counter,times,choseCounter,everyCandidate);

   }
}

public static void printCandidate(Candidate[] candidates, int counter){
    System.out.println("Candidates are: ");
    for (int i=0; i< counter; i++){
        System.out.println(candidates[i].getName() + " with their slogan: " + candidates[i].getSlogan());
    }
    return;
}

public static void runElection(Candidate[] votes, int counter, int times, Candidate countVote, Candidate[] everyCandidate){
    Candidate[] newElection = new Candidate[counter];
    for (int i =0; i<counter; i++){
        newElection[i] = votes[counter];
    }

    Candidate[] winningCandidate = new Candidate[times*(counter - 1)];
    for (int i = 0; i<=times; i++){
        for (int j = 0; j<counter; j++ ){
            try{
                winningCandidate[(counter*i) + j] = newElection[j].vote(newElection);
            } catch(Exception e){
                winningCandidate[j] = new Candidate_ec22630();
            }
        }
    }
    Candidate realWinner = countVote.selectWinner(winningCandidate);
    System.out.println("The most common winner is: " + realWinner.getName());
}
}

