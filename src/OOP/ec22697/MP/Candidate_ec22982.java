package OOP.ec22697.MP;// File Candidate_ec22982.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22982 extends Candidate
{
    public String getName()
    {
        return "Dwight";
    }
    
    public String getSlogan()
    {
        return "Yes we can!";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22982();
        
        // First search for Utfur on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Utfur"))
                return c;
        
        // Otherwise, choose at random from list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
    }
    
    public Candidate selectWinner(Candidate[] votes) 
    {
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22982();
        
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
      


public static void main(String[] args)
{
    Candidate[] ca = A3.getCandidateArray();
    int candidate_num = 0;
    Candidate[] candidates = new Candidate[ca.length];
    
    boolean valid = false;
    System.out.println("= Running Repeated Elections =");
    
    while(!valid)
    {
        System.out.println("Candidates are: ");
        if (candidate_num!=0)
        {
            for (int i = 0; i<candidate_num; i++)
            {
                System.out.println(candidates[i]);
            }
        }
        
        char menu = inputChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        
        if (menu == 'a')
        {
            candidates = addCandidate(candidates, ca, candidate_num);
            candidate_num++;
        }
        else if (menu == 'b')
        {
            candidates = randomCandidate(candidates, ca, candidate_num);
            candidate_num++;
        }
        else if (menu == 'c')
        {
            election(candidates, ca, candidate_num);
            
            boolean valid2 = false;
            
            while (!valid2)
            {
                char option = inputChar("Would you like to a) start another elction b) quit?");
                
                if(menu == 'a')
                    valid2 = true;
                else if(option == 'b')
                {
                    valid = true;
                    valid2 = true;
                }
                else 
                    System.out.println("Invalid option. Please try again.");
            }
        }
        else 
            System.out.println("Inavlid option. Please try again.");
    }
}

public static String inputString(String message)
{
    Scanner scanner = new Scanner(System.in);
    System.out.println(message);
    String answer = scanner.nextLine();

    return answer;
}

public static int inputInt(String message)
{
    Scanner scanner = new Scanner(System.in);
    System.out.println(message);
    int answer = Integer.parseInt(scanner.nextLine());

    return answer;
}

public static char inputChar(String message)
{
    Scanner scanner = new Scanner(System.in);
    System.out.println(message);
    char answer = scanner.nextLine().charAt(0);

    return answer;
}

public static Candidate[] addCandidate (Candidate[] candidates, Candidate[] ca, int candidate_num)
{
    String name = inputString("Please enter a username.");

    Candidate addedC = A3.getByUsername(name, ca);

    if (addedC != null)
    {
        candidates[candidate_num] = addedC;
    }
    else
    {
        System.out.println("Could not find this candidate.");
    }

    return candidates;
}

public static Candidate[] randomCandidate (Candidate[] candidates, Candidate[] ca, int candidate_num)
{
    Random ran = new Random();
    int random = ran.nextInt(ca.length);
    Candidate randomC = ca[random];

    candidates[candidate_num] = randomC;

     return candidates;
}

public static void election (Candidate[] candidates, Candidate[] allCandidates, int candidate_num)
{
    Candidate[] fullCandidates = new Candidate[candidate_num];
    for(int i=0;i<candidate_num;i++)
         {
             fullCandidates[i]=candidates[i];
         }
         
    String name = inputString("Who should count the votes?");
    Candidate chosen = A3.getByUsername(name, allCandidates);

    int numRuns = inputInt("How many times shall we run the election?");
    Candidate[] winners = new Candidate[numRuns];
    for (int i=0; i<numRuns; i++)
    {
        Candidate[] votes = new Candidate[allCandidates.length];
        for (int j=0; j<allCandidates.length; j++)
        {
                 try
                 {
                     votes[j] = allCandidates[j].vote(fullCandidates);
                 }
                 catch (Exception e)
                 {

                 }
        }
            winners[i]=chosen.selectWinner(votes);
    }
    
    int highestCount = 0;
    Candidate[] overallWinner = new Candidate[fullCandidates.length];
    int winnerCount = 1;
    for (int i=0;i<fullCandidates.length;i++)
    {
        int count = 0;
        for (int j=0;j<winners.length;j++)
        {
            if(winners[j].equals(fullCandidates[i]))
            {
                count++;
            }
        }
        if(count>highestCount)
        {
            highestCount=count;
            overallWinner[0] = candidates[i];
            winnerCount=1;
        }
        else if(count==highestCount)
        {
            winnerCount++;
            overallWinner[winnerCount-1] = candidates[i];
        }
    }
    
    if(winnerCount==1)
    {
        System.out.println("Most common winner is " + overallWinner[0] + ".");
        System.out.println("There were no other winners.");
    }
    else
    {
        System.out.println("Most common winners:");
        for(int i=0;i<winnerCount;i++)
        {
            System.out.println(overallWinner[i]);
        }
    }
    
    return;
}

}


      
    

