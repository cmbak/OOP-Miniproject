package OOP.ec22697.MP;// File Candidate_ec22840.java
//

import java.util.Random;
import java.util.Scanner;

// A3
class Candidate_ec22840 extends Candidate 
{
    public static void main(String[] args) 
    {
        election ();
    }
    
    
    public static void election ()
    {
        Scanner scanner = new Scanner(System.in);
        Candidate[] tcandidates = A3.getCandidateArray();
        Candidate[] candidates = new Candidate[tcandidates.length];
        int currentp = 0;
        
        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) exit?");
        char choice = scanner.next().charAt(0);
        switch (choice) {
            case 'a':
            addcandidate(candidates,tcandidates,currentp);
                break;
            case 'b':
                addrandomc(candidates,tcandidates,currentp);
                break;
            case 'c':
                runelection(candidates,tcandidates);
                break;
            case 'd':
                System.out.println ("bye!");
                break;
            default:
                System.out.println("Input invalid !!Please choose from a,b,c,d only");
                break;
        }
    }
    
    public static void printallCandidates(Candidate[] candidates)
    {
        int length = candidates.length;
        System.out.println("Candidates are");
        for (int i = 0; i < length; i++)
        {
            if (!(candidates[i] == null))
            {
                    System.out.println((i+1) + ". " + (candidates[i].getName()));
            }
            else
            {
                System.out.println("none");
            }
            
        }
    }
    
    
    public static void addcandidate (Candidate[] candidates, Candidate[] tcandidates, int currentp)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println ("Please enter a username");
        String username = scanner.nextLine();
        Candidate the_candidate = A3.getByUsername(username, tcandidates);
        if (the_candidate == null) 
        {
            System.out.println("Username invalid, please try again");
     		addcandidate(candidates,tcandidates,currentp);
     	} 
        else 
        {
            candidates[currentp] = the_candidate;
            printallCandidates(candidates);
     	}
        
        election();
    }
    
    
    public static void addrandomc (Candidate[] candidates, Candidate[] tcandidates, int currentp)
    {
        Random rand = new Random();
        int randnum= rand.nextInt(tcandidates.length-1);
        if (candidates[currentp] == null) 
        {
            candidates[currentp] = tcandidates[randnum];
            
        }
        else 
        {
            for (int i = 0; i < candidates.length; i++) 
            {
                if (!(candidates[currentp]==null))
                currentp++;
            }
            candidates[currentp] = tcandidates[randnum];
        }
        
        election();
        
    }
    
    
    public static void runelection (Candidate[] candidates, Candidate[] tcandidates)
    {
        Scanner scanner = new Scanner(System.in);
        Candidate[] winners = new Candidate[tcandidates.length];
        Candidate winner = tcandidates[0];
        int count = 0;
        for (int i = 0; i < candidates.length; i++)
        {
            if (candidates[i] == null) 
            {
                count = count + 1;
                if (count == candidates.length) 
                {
                    System.out.println("No candidates, please add at least 1 candidates");
                    election ();
                }
            }
        }
        
        System.out.println ("Who should count the vote?");
        String username = scanner.nextLine();
        Candidate the_user = A3.getByUsername(username, tcandidates);
        
        if (the_user != null) 
        {
            Candidate voteCount = A3.getByUsername(username, tcandidates);           
            System.out.println ("How many times should we run the election?");
            int turns = Integer.parseInt(scanner.nextLine());
            for(int i=0; i<=turns; i++)
            {
                winners[i]= candidates[i].vote(candidates);
            } 
            winner = the_user.selectWinner(winners);
            
            System.out.println("Most common winner is " + winner.getName());        
        }
        
        else 
        {
            System.out.println("user not exist please try again.");
            runelection(candidates,tcandidates);
        }      
        
    }
    
    
//A2
    public String getName() 
    {
        return "Meow";
    }
    
    public String getSlogan() 
    {
        return "I always love Cat !!!!!";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        Random x = new Random();
        int w = x.nextInt(candidates.length);
        Candidate m = candidates[w];
        
//        if (candidates.length != 0)
//        {
//            for (int i = 0; i < candidates.length; i++)
//            {
//                w = w / 2 +1;
//                m = candidates[w];
//            }
//
//        }
        return m;
        
    }
    
    public Candidate selectWinner(Candidate[] votes)
    {
        if (votes.length == 0) 
        {
            return new Candidate_ec22840();
        }
        Candidate winner = votes[0];        
        for (int i = 0; i < (votes.length); i ++ )
        {
            String name = votes[i].getName();
            if (!(name.equals("Meow")))
            {
                    return winner = votes[i];    
            }
            
        }
        
        Random rand = new Random ();
        int idk = rand.nextInt(votes.length);
        return votes[idk];
    }
    
}
