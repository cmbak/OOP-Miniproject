package OOP.ec22697.MP;// File Candidate_ec22836.java

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22836 extends Candidate {
    
    // Get character input from the user
    public static char getUserChar(String message)
    {
        Scanner scanner = new Scanner(System.in);
        char answer;
        String text_input = "";
        System.out.println(message);
        
        text_input = scanner.nextLine();
        answer = text_input.charAt(0);
        
        return answer;
    }
    
    //Get string input from the user
    public static String getUserString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        String text_input = "";
        System.out.println(message);
        
        text_input = scanner.nextLine();
        answer = text_input;
        
        return answer;
    }
    
    //Get int input from the user
    public static int getUserInt(String message)
    {
        Scanner scanner = new Scanner(System.in);
        int answer;
        String text_input = "";
        System.out.println(message);
        
        text_input = scanner.nextLine();
        answer = Integer.parseInt(text_input);
        
        return answer;
    }
    
    //Finds the candidate and returns their position in the candidates array
    public static int searchCandidate(Candidate[] candidates)
    {
        int pos = -1;
        
        while (pos == -1)
        {
            String username = getUserString("Please enter a username:");
        
            for (int i = 0; i < candidates.length; i++)
            {
                if (username.equals(candidates[i].un))
                {
                    pos = i;
                }
            }
            
            if (pos == -1)
            {
                System.out.println("User not found! Please try again!");
            }
        }
        
        return pos;
    }
    
    //Adds specific candidate to the voting array
    public static Candidate[] addSpecificCandidate(Candidate[] candidates, Candidate[] votes, int choice)
    {
        votes[votes.length-1] = candidates[choice];
        
        printVotesArray(votes);
        
        return votes;
    }
    
    //Adds a random candidate to the voting array
    public static Candidate[] addRandomCandidate(Candidate[] candidates, Candidate[] votes)
    {
        Random r = new Random();
        
        votes[votes.length - 1] = candidates[r.nextInt()];
        
        printVotesArray(votes);
        
        return votes;
    }
    
    //Prints out the list of candidates
    public static void printVotesArray(Candidate[] votes)
    {
        for (int i = 0; i < votes.length; i++)
        {
            System.out.println((i + 1) + " " + votes[i]);
        }
        return;
    }
    
    //Runs the election using a specified users selectWinner method
    public static void Election(Candidate[] candidates, Candidate[] votes)
    {
        System.out.println("Who should count the votes?");
        int pos = searchCandidate(candidates); 
        
        int times = getUserInt("How many times shall we run the election?");

        for (int i=0; i < times; i++){
            Candidate winner = candidates[pos].selectWinner(votes);
            System.out.println("The winner is: " + winner.un);
        }
        return;
    }
    
    public String getName() {
        return "Joshua";
    }
    
    public String getSlogan() {
        return "Excelsior!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22836();
        
        // First search for Joshua on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Joshua"))
                return c;
        
        // Otherwise, choose at random from list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22836();
        
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
    
    public static void main(String [] a)
    {
        A3 a3 = new A3();
        
        Candidate[] candidates = a3.getCandidateArray();
        Candidate[] votes = new Candidate[candidates.length];
        
        char option2 = 'b';
        
        while (option2 != 'a')
        {
            char option = getUserChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        
            if (option == 'a')
            {
                int choice = searchCandidate(candidates);
                
                votes = addSpecificCandidate(candidates, votes, choice);
            }
            else if(option == 'b')
            {
                votes = addRandomCandidate(candidates, votes);
            }
            else if(option == 'c')
            {
                while(option2 == 'b')
                {
                    Election(candidates, votes);
                
                    option2 = getUserChar("Would you like to a) exit b) run same election many times");
                }
            }
        }
        
        return;
    }
}
