package OOP.ec22697.MP;// File Candidate_ec22546.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22546 extends Candidate {
    
    public static String inputString (String output) //outputs a message and returns the user's string answer
    {
        Scanner keyboard = new Scanner (System.in);
        
        String text;
        
        System.out.println(output);
        text = keyboard.nextLine();
        
        return text;
    }
    
    public static int inputInteger (String output) //outputs a message and returns the user's integer answer
    {
        Scanner keyboard = new Scanner (System.in);
        
        String text;
        int integer;
        
        System.out.println(output);
        text = keyboard.nextLine();
        integer = Integer.parseInt(text);
        
        return integer;
    }
    
    public static int generate (int bound) //returns random integer
    {
        Random random = new Random();
        
        return random.nextInt(bound);
    }
    
    public static void main (String [] args)
    {
        boolean running;
        
        int emptySpace = 0;
        
        String answer;
        
        //option a
        String username;
        boolean added = false;
        
        //option b
        int index;
        
        //option c
        String runner;
        int times;
        int nextSpace = 0;
        boolean runnerExists = false;
        int runningIndex = 0;
        int count = 0;
        int winning = 0;
        int winningIndex = 0;
    
        Candidate[] Everyone = A3.getCandidateArray();
        Candidate[] canList = new Candidate[Everyone.length];
        Candidate[] Finalists = new Candidate[Everyone.length];
        
        running = true;
        
        while (running == true)
        {
            System.out.println("= Running Repeated Elections =\r\nCandidates are");
            
            if (canList.length == 0)
            {
                System.out.println("none");
            }
            
            else
            {
                for (int i = 0; i < canList.length; i++)
                {
                    System.out.println(canList[i].getName());
                }
            }
            
            answer = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");
            
            if (answer.equals("a"))
            {
                username = inputString("Please enter a username.");
                
                for (int j = 0; j < Everyone.length; j++)
                {
                    if (username.equals(Everyone[j]))
                    {
                        canList[emptySpace] = Everyone[j];
                        emptySpace += 1;
                        added = true;
                    }
                }
                
                if (added == false)
                {
                    System.out.println("Person does not exist. Candidate not added.");
                }
                     
            }
            
            else if (answer.equals("b"))
            {
                index = generate(Everyone.length);
                canList[emptySpace] = Everyone[index];
                emptySpace += 1;
            }
            
            else if (answer.equals("c"))
            {
                runner = inputString("Who should count the votes?");
                times = inputInteger("How many times shall we run the election?");
                
                //checks if the person to run the election(s) exists
                for (int x = 0; x < Everyone.length; x++)
                {
                    if (runner.equals(Everyone[x]))
                    {
                        runnerExists = true;
                        runningIndex = x;
                    }
                }
                
                if (runnerExists == false)
                {
                    System.out.println("Person does not exist. Election(s) not ran.");
                }
                
                //runs the election(s)
                if (runnerExists == true)
                {
                    for (int y = 0; y < times; y++)
                    {
                        Candidate winner = Everyone[runningIndex].selectWinner(canList);
                        Finalists[nextSpace] = winner;
                    }
                }
                
                for (int z = 0; z < Finalists.length-1; z++)
                {
                    for (int a = z+1; a < Finalists.length; a++)
                    {
                        if (Finalists[z] == Finalists [a])
                        {
                            count += 1;
                        }
                    }
                    
                    if (count > winning)
                    {
                        winning = count;
                        winningIndex = z;
                    }
                }
                
                Candidate ultimateWinner = Finalists[winningIndex];
                System.out.println("Most common winner is " + ultimateWinner.getName());
            }
            
            else if (answer.equals("d"))
            {
                running = false;
            }
            
            else
            {
                System.out.println("Invalid option entered.");
            }
        }
    }
    
    public String getName() {
        return "Zaria";
    }
    
    public String getSlogan() {
        return "Vote4Me";
    }
    
     public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22938();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Vote4Me"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Alesha"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22938();
        
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
