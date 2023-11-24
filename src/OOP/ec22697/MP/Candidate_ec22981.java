package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;
// File Candidate_ec22981.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22981 extends Candidate {
    public static void runReapeatedElection(Candidate[] cnds, Candidate[] winners, int how_many_times, Candidate voteCounter) {
        Candidate[] all_votes = new Candidate[cnds.length];
        Candidate[] all_cnds = A3.getCandidateArray();
        
        for(int k = 0;k<cnds.length;k++) {
            all_votes[k] = cnds[k].vote(all_cnds);    
        }
        
        for(int i = 0; i < how_many_times; i++) {
            winners[i] = voteCounter.selectWinner(all_votes);
        }
        return;
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] cands = new Candidate[allCandidates.length];
        
        String inputStart = inputString("Would you like to \na) exit \nb) run same election many times");
        
        while(inputStart.equals("b")) {
            System.out.println("= Running Repeated Elections =");

            int cnt = 0;
            final String STOP = "e";

            String option = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?(exit-e)");

            while(option!=STOP) {
                if(option.equals("a")) {
                    String name = inputString("Please enter a username.");

                    Candidate newCand = A3.getByUsername(name, allCandidates);

                    if (newCand == null)
                    {
                       System.out.println("Username is not found.");
                    }
                    else
                    {    
                       cands[cnt] = newCand;
                       cnt++;
                       printCandidates(cands, cnt);
                    }
		    
		    break;
                }
                else if(option.equals("b")) {
                    Random r = new Random();

                    Candidate newCand = allCandidates[r.nextInt(allCandidates.length)];

                    cands[cnt] = newCand;

                    cnt++;

                    printCandidates(cands, cnt);

		    break;
                }
                else if(option.equals("c")) {
                    String username = inputString("Who should count the votes?");

                    Candidate candCounter = A3.getByUsername(username, allCandidates);
                    
                    int how_many_times = inputInt("How many times shall we run the election?");

                    Candidate[] winners = new Candidate[how_many_times];
                    Candidate[] finalCands = new Candidate[cnt];
                    
                    for (int i = 0; i < cands.length; i++) {
                        finalCands[i] = cands[i];
                    }
                    
                    runReapeatedElection(finalCands, winners, how_many_times, candCounter);
                    
                    Candidate most_common_winner = mostCommonWinner(winners);
                    
                    System.out.println("Most common winner is" + most_common_winner.getName());

		    break;
                }
                else{
                    System.out.println("Choose a or b or c or e!");
                }
                
                option = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?(exit-e)");
            }
        }
    }
    
    public static String inputString(String s) {
        Scanner scnr = new Scanner(System.in);
        System.out.println(s);
        
        String ans = scnr.nextLine();
        
        return ans;
    }
    
    public static int inputInt(String s) {
        Scanner scnr = new Scanner(System.in);
        System.out.println(s);
        
        int ans = Integer.parseInt(scnr.nextLine());
        
        return ans;
    }
    
    public static void printCandidates(Candidate[] candidates, int cnt) {
        System.out.println("Candidates are");

        if(candidates.length==0) {
            System.out.println("None");    
        }

        for(int i = 0;i<cnt;i++) {
            System.out.println((i+1) + ". " + candidates[i].getName());
        }
        
        return;   
    }
    
    
    public static Candidate mostCommonWinner(Candidate[] winners) {
         Candidate w = null;

         if (winners.length == 0)
             return w;

         int count = 0;
         int max_count = 0;
         
         for (int i = 0; i < winners.length; i++) {
             for (int j = 0; j < winners.length; j++) {
                 if (winners[i] == winners[j])
                     count++;
             }
             if (max_count < count) {
                 max_count = count;
                 w = winners[i];
             }
         }
         return w;
    }
    
    public String getName() {
        return "Andrej";
    }
    
    public String getSlogan() {
        return "More investments!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22981();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More investments!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Sisi"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22981();
        
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
