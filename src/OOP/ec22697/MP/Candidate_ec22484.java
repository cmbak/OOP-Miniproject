package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22484 extends Candidate {
    
    public static void main (String[] args)
    {
        Candidate[] allContributions = A3.getCandidateArray();
        Candidate[] votes = new Candidate[allContributions.length];
        int counter = 0;
        boolean end = false;
        
        while (!end)
        {
            String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election");
            
            if (choice.equalsIgnoreCase("a"))
            {
                String specific_name = inputString("Please enter a username");
                Candidate freshCandidate = A3.getByUsername(specific_name, allContributions);
                votes[counter] = freshCandidate;
                counter++;
              
            }   
            
            else if (choice.equalsIgnoreCase("b"))
            {
                Random rand = new Random();
                int rando_num = rand.nextInt(allContributions.length);
                votes[counter] = allContributions[rando_num];
                counter++;
            }
            
            else if (choice.equalsIgnoreCase("c"))
            {
                String username = inputString("Who should count the votes?");
                Candidate countingCandidate = A3.getByUsername(username, allContributions);
                int runs = inputInt("How many times are we running election?");
                
                runElection(votes, counter, runs, countingCandidate);
                end = true;
            }   
                
        }
        
        printCandidates(votes, counter);
        
    }
    
    public static void runElection (Candidate[] votes, int counter, int runs, Candidate countingCandidate)
    {
        Candidate[] ballots = new Candidate[runs * (counter-1)];
        for (int i = 0; i <= runs; i++)
        {
            for (int j = 0; j < counter; j++)
            {
                try {
                    ballots[(counter * i) + j] = votes[j].vote(votes);
                } catch (Exception e) {
                    ballots[i] = new Candidate_ec22484();
                }
            }
        }
        
        Candidate top = countingCandidate.selectWinner(ballots);
        System.out.println("Most common winner: " + top.getName());
    }    
    
    public static void printCandidates (Candidate[] votes, int counter)
    {
        System.out.println("\nCandidates:");
        for (int i = 0; i < counter; i++)
        {
            System.out.println((i+1) + ". " + votes[i].getName() + ": " + votes[i].getSlogan());
        }
    }
        
    public static String inputString (String message)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        String answer = scan.nextLine();

        return answer;
    }    
    
    public static int inputInt (String message)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);

        while (!scan.hasNextInt()) {
            scan.next();
            System.out.print("Try inputting an integer.");
        }

        int num = scan.nextInt();
        return num;
    }   
    
    public String getName() {
        return "Javed Rajabally";
    }
    
    public String getSlogan() {
        return "I'm not seeking penance for what I've done father";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
       
        if (candidates.length == 0) {
            return new Candidate_ec22484();
        }    
        
        
        for (Candidate c : candidates) {
            
            if (c.getName().equals("Andrew")) {
                return c;
            }
        }    
        
        // Otherwise, choose at random from list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) {
            
            return new Candidate_ec22484();
        }    
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate cand : votes) {
                
                if (cand == c) {
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
    
}
