package OOP.ec22697.MP;// File Candidate_ec22662.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22662 extends Candidate {
    public static void main(String [] a){
        int position = 0;
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] candidate = new Candidate[allCandidates.length];
        
        String choice = getUserResponse("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        while (! choice.equals("c")) {
            if (choice.equals("a")) {
                String name = getUserResponse("Please enter a username: ");
                Candidate nominee = A3.getByUsername(name, allCandidates);
                candidate[position] = nominee;
                position = position + 1;
                System.out.println("Candidates are");
                printCandidates(candidate, position);
            }
            //inspired by ec22675
            else if (choice.equals("b")) {
                Candidate randomNominee = getRandomCandidate(allCandidates);
                candidate[position] = randomNominee;
                position = position + 1;
                System.out.println("Candidates are");
                printCandidates(candidate, position);
            }
            else {
                System.out.println("You must enter either a, b or c");
           }
       choice = getUserResponse("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
       }
       String userOfCounter = getUserResponse("Who should count the votes?");
       Candidate counter = A3.getByUsername(userOfCounter , allCandidates);
       int iteration = getIntResponse("How many times shall we run the election?"); 
       //inspired by ec22562
       Candidate[] election = new Candidate[iteration * (position - 1)];
       for (int i=0; i<=iteration; i++) {
           for (int j=0; i<position; j++) {
               if (candidate[j] == null) {
                   continue;
               }
               try {
                   election[(position * i) + j] = candidate[j].vote(candidate);
               } 
               catch (Exception e) {
                   election[j] = new Candidate_ec22662();
               }
           }
       }
       Candidate winner = counter.selectWinner(election);

       System.out.println("Most common winner is " + winner.getName());
       return;             
    }
    
    public static String getUserResponse(String str) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(str);
        String response = scanner.nextLine();
        return response;
    }
    
    public static int getIntResponse(String str) {
        String response = getUserResponse(str);
        return Integer.parseInt(response);
    }
      
    public static void printCandidates(Candidate[] a, int b) {
        for (int i=0; i<b; i++) {
            System.out.println(i+1 + ":" + a[i].getName());
        }
    }
    
    //derived from jhub example
    public static Candidate getRandomCandidate(Candidate[] a) {
        Random random = new Random();
        return a[random.nextInt(a.length - 1)];
    }
        
    public String getName() {
        return "Winner";
    }
    
    public String getSlogan() {
        return "Save the Turtles!";
    }
    
    //derived from jhub example
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) 
            return new Candidate_ec22662();
        
        for (Candidate c : candidates)
            if (c.getName().equals("Winner")) //vote winner
                return c;
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
        return new Candidate_ec22662();

        Candidate Winner = votes[0];

        int mostVotes = 0;
        for (Candidate c : votes) {
            
            int vote = 0;
            for (Candidate x : votes)
                if (x == c) {
                    vote = vote + 1;
                }
            if (vote >= mostVotes) {
                mostVotes = vote;
                Winner = c;
            }
        }
        return Winner;
    }
    
}
