package OOP.ec22697.MP;

import java.util.Random; //credit to ec22662
import java.util.Scanner;

 class Candidate_ec22642 extends Candidate {
     public static void main(String [] a){
         int position = 0;
         Candidate[] allCandidates = A3.getCandidateArray();
         Candidate[] candidate = new Candidate[allCandidates.length];

         String question = answer("Would you like to a) add a specific candidate b) add a random candidate c) run the election?");
         while (! question.equals("c")) {
             if (question.equals("a")) {
                 String username = answer("Please enter a username: ");
                 Candidate theone = A3.getByUsername(username, allCandidates);
                 candidate[position] = theone;
                position = position + 1;
                System.out.println("Candidates are");
                 printer(candidate, position);
             }

             else if (question.equals("b")) {
                 Candidate randomtheone = luckyman(allCandidates);
                 candidate[position] = randomtheone;
                 position = position + 1;
                System.out.println("Candidates are:");
                printer(candidate, position);
            }
            else {
                System.out.println("Error,wrong input.");
           }
       question = answer("Would you like to a) add a specific candidate b) add a random candidate c) run the election?");
       }
       String thecounter = answer("Who should count the votes?");
       Candidate counter = A3.getByUsername(thecounter , allCandidates);
       int repeat = numelection("How many times should the election be ran?"); 

       Candidate[] election = new Candidate[repeat * (position - 1)];
       for (int i=0; i<=repeat; i++) {
           for (int j=0; i<position; j++) {
               if (candidate[j] == null) {
                   continue;
               }
               try {
                   election[(position * i) + j] = candidate[j].vote(candidate);
               } 
               catch (Exception e) {
                   election[j] = new Candidate_ec22642();
               }
           }
       }
       Candidate winner = counter.selectWinner(election);
       System.out.println("Most common winner is " + winner.getName());
       return;             
    }
    
    public static String answer(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String response = scanner.nextLine();
        return response;
    }
    
    public static int numelection(String message) {
        String response = answer(message);
        return Integer.parseInt(response);
    }

     public static void printer(Candidate[] a, int b) {
         for (int i=0; i<b; i++) {
             System.out.println(i+1 + ":" + a[i].getName());
         }
     }

    public static Candidate luckyman(Candidate[] a) {
        Random random = new Random();
        return a[random.nextInt(a.length - 1)];
    }
        
    public String getName() {
        return "King Leysan";
    }
    
    public String getSlogan() {
        return "Who else but me.";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) 
            return new Candidate_ec22642();
        
        for (Candidate c : candidates)
            if (c.getName().equals("King Leysan")) 
                return c;
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
        return new Candidate_ec22642();
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
