package OOP.ec22697.MP;

import java.util.Objects;
import java.util.Scanner;

class Candidate_ec22568 extends Candidate {
    
    public String getName() {
        return "Zakariya";
    }
    
    public String getSlogan() {
        return ":)";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22446();
        
        // Search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Tom"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22446();
        
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
        Candidate[] contributions = new Candidate[]{};
        
        System.out.println("= Running Repeated Elections =");
         
        boolean valid = true;
        while (valid)
        {
            System.out.println("Candidates are");
            
            if (contributions.length == 0)
            {
                System.out.println("None");
            }
            else {
                for (int n = 0; n < contributions.length; n++) {
                    System.out.println(contributions[n].getName());
                }
            }
            
            int selectedOption = users_input_int("Would you like to 1) add a specific candidate 2) run the election? 3) exit");
                
            if (selectedOption == 1)
            {
                String name = users_input_string("Please enter a username.");
                Candidate candidate = A3.getByUsername(name, A3.getCandidateArray());
                contributions = addCandidateToArray(contributions,candidate);
            }
            else if (selectedOption == 2)
            {
                if (contributions.length == 0) {
                    System.out.println("There are no other winners.");
                }
                else {
                    String voteCounter = users_input_string("Who should count the votes?");
                    int runs = users_input_int("How many times shall we run the election?");
                    
                    mostCommonWinner(contributions, voteCounter, runs);
                }
            }
            else if (selectedOption == 3)
            {
                valid = false;
            }
            else
            {
                System.out.println("Enter from the options 1, 2 or 3.");
            }
        }
    }
         
        public static void mostCommonWinner(Candidate[] contributions, String voteCounter, int runs) {
            Candidate[] winnerForEachRun = new Candidate[runs];
                    
            for (int i = 0; i < runs; i++) {
                winnerForEachRun[i] = Objects.requireNonNull(A3.getByUsername(voteCounter, A3.getCandidateArray())).selectWinner(contributions);
            }
            
            Candidate Winner = winnerForEachRun[0];
            
            int maxCount = 0;
            for(int n=0;n< winnerForEachRun.length;n++)
            {
               int count = 0;
               for(int m=0;m<winnerForEachRun.length;m++)
               {
                   if((winnerForEachRun[n]).equals((winnerForEachRun[m]))&&(n!=m))
                   {
                       count++;
                       if(count>maxCount)
                       {
                           maxCount = count;
                           Winner = winnerForEachRun[n];
                       }
                   }
               }
            }
            
            System.out.println("Most common winner is " + Winner.getName() + ".");
            System.out.println("There were no other winners.");
        }
                        
        public static Candidate[] addCandidateToArray (Candidate[] OLDArray, Candidate newCandidate)
        {
            Candidate[] NEWArray = new Candidate[OLDArray.length + 1];
            
            for (int n = 0; n < OLDArray.length; n++)
            {
                NEWArray[n] = OLDArray[n];
            }
            
            NEWArray[OLDArray.length] = newCandidate;
            
            return NEWArray;
        }
    
        
        public static String users_input_string (String message)
        {
            String answer;
            Scanner input = new Scanner(System.in);
    
            System.out.println(message);
            answer = input.nextLine();
            input.close();
    
            return answer;
        }
    
        public static int users_input_int (String message)
        {
            int answer;
            Scanner input = new Scanner(System.in);
    
            System.out.println(message);
            answer = input.nextInt();
            input.close();
    
            return answer;
        }
    }
