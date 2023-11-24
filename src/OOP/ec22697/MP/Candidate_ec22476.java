package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22476 extends Candidate
{
    public static void main(String[] args)
    {
        menu();
    }

    public static void printString(String statement)
    {
        System.out.println(statement);
    }
    
    public static void printC(Candidate statement)
    {
        System.out.println(statement);
    }

    public static Candidate getCandidate(String statement, Candidate[] candidates)
    {
        String username = input(statement);

        Candidate candidate = A3.getByUsername(username, candidates);
        while (candidate == null)
        {
            printString("User not found");
            username = input(statement);
            candidate = A3.getByUsername(username, candidates);
        }
        return A3.getByUsername(username, candidates);
    }
    
    public static String input(String statement)
    {
        Scanner scanner = new Scanner(System.in);
        printString(statement);
        return scanner.nextLine();
    }
    
     public static void menu()
    {
        String choice = getChoice("\nWould you like to "
        + "\na) exit \nb) run same election many times"
        + "\nc) check who counts honestly "
        + "\nd) run all possible two-candidate elections"
        + "\ne) run a higher-order election ");
        
        while (!(choice.equals("a")))
        {
            if (choice.equals("b"))
            {
                Candidate[] candidates = A3.getCandidateArray();
                Candidate[] candidates1 = new Candidate[candidates.length];
                runSameElection(candidates1, candidates);
            }

            choice = getChoice("\nWould you like to "
            + "\na) exit \nb) run same election many times"
            + "\nc) check who counts honestly "
            + "\nd) run all possible two-candidate elections"
            + "\ne) run a higher-order election ");
        }
        
        return;        
    }
    
    
    public static String getChoice(String statement)
    {
        String input = input(statement);
        while ((!(input.equals("a"))) && (!(input.equals("b"))) && (!(input.equals("c"))) 
        && (!(input.equals("d"))) && (!(input.equals("e"))))
        {
            printString("Please enter 'a', 'b', 'c', 'd', or 'e'");
            input = input(statement);
        }
        return input;
    }
    
    public static void runSameElection(Candidate[] candidates1, Candidate[] candidates)
    {
    
        printString("\n= Running Repeated Elections =");
    
        String input = input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
	
	while (!(input.equals("c")))
	{
        	while ((!(input.equals("a"))) && (!(input.equals("b"))) && (!(input.equals("c"))))
        	{
           	 printString("Please enter 'a', 'b' or 'c'");
           	 input = input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        	}
		if (input.equals("a"))
       		{	
          	  	addCandidate(candidates1, candidates);
        	}
		else if (input.equals("b"))
        	{
        		addRandomCandidate(candidates, candidates1);
        	}
        input = input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
	}
	runElection(candidates, candidates1);    
     }
    
    public static void addRandomCandidate(Candidate[] candidates, Candidate[] candidates1)
    {
    	Random candNo = new Random();
    	Candidate candidate2 = candidates[candNo.nextInt(candidates.length)];
    	for (int i = 0; i < candidates1.length; i++)
        {
            if (candidates1[i] == null)
            {
                candidates1[i] = candidate2;
		printCandidates(candidates1);
                return;
            }
	}
    	printString("Could not add any more candidates.");
    	return;
    }
    
    public static void runElection(Candidate[] candidates, Candidate[] candidates1)
    {
	if (candidates1[0] == null)
	{
		for (int i = 0; i < candidates1.length; i ++)
		{
			candidates1[i] = new Candidate_ec22476();
		}
	}
    	Candidate voteCounter = getCandidate("Who should count the votes?", candidates);
    	int noOfTimes = integer("How many times shall we run the election?");
	mostCommonWinner(noOfTimes, candidates1, voteCounter);
    }
    
    public static void mostCommonWinner(final int noOfTimes, Candidate[] candidates, Candidate voteCounter)
    {
    	String[] winners = new String[noOfTimes];
    	for (int i = 0; i < noOfTimes; i++)
    	{
            try 
            { 
                Candidate winner = voteCounter.selectWinner(candidates);
                winners[i] = winner.getName();
            }
            catch (Exception e) 
            { 
                Candidate candidate = new Candidate_ec22476();
                Candidate winner = candidate.selectWinner(candidates);  
                winners[i] = winner.getName();
            }
    	}
    	
    	printString("Most common winner is " + oneMostFrequent(winners) + ".");
    	printString("There were no other winners.");
    }
    
    public static int numberOfTimes(String x, String[] a) {
        int r = 0;
        
        for (int i = 0; i < a.length; i++)
        {
            if (x == a[i])
            {
                r+=1;
            }
        }
        return r;
    }
    
    public static String oneMostFrequent(String[] winners) {
        
        String r = winners[0]; 
        int largestCountSoFar = numberOfTimes(r,winners);
        for (int i = 0; i < winners.length; i++)
        {
            if (numberOfTimes(winners[i],winners) >= largestCountSoFar)
            {
            	if (!(winners[i]).equals(r))
            	{
                    r = r + " and " + winners[i];
            	}
            	else
            	{
              		r = winners[i];
            	}
            }
        }
        return r;
    }
    
    public static int integer(String statement)
    {
    	String input = input(statement);
    	while (!isPositiveInteger(input) || input.equals(""))
    	{
    		printString("Please enter a whole number.");
    		input = input(statement);
    	}
    	return Integer.parseInt(input);
    }
    
    public static boolean isPositiveInteger(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(Character.isDigit(c)))
            {
                return false;
            }
        }
        return true;
    }
    
    public static void printCandidates(Candidate[] candidates1)
    {
        printString("Candidates are");
        if (candidates1[0] == null)
        {
        	printString("None");
        	return;
        }
        for (int i = 0; i < candidates1.length; i++)
        {
        	 if (candidates1[i] == null)
             {
             	return;
             }
        	 else
        	 {
        		 printString((i+1) + ". " + candidates1[i].getName());
        	 }
        }
    }

    public static void addCandidate(Candidate[] candidates1, Candidate[] candidates)
    {
	if (candidates1[candidates1.length-1] != null)
	{
		printString("Could not add any more candidates.");
		return;
	}
	Candidate username = getCandidate("Please enter a username.",candidates);

        for (int i = 0; i < candidates1.length; i ++)
        {
            if (candidates1[i] == null)
            {
                candidates1[i] = username;
		printCandidates(candidates1);
                return;
            }
        }
    	printString("Could not add any more candidates.");
    	return;
    }
        
    public String getName()
    {
        return "Farida";
    }
    
    public Candidate vote(Candidate[] candidates)
    {
        int candidateIndex = 0;
        int length = candidates.length;
        if (length == 0)
        {
            return new Candidate_ec22476();
        }
        else
        {
            for (int i = 0; i < length; i++)
            {
                if (((candidates[i]).getSlogan()).equals("Hello"))
                {
                    candidateIndex = i;
                }
            }
            return candidates[candidateIndex];
        }
        
    }
    public Candidate selectWinner(Candidate[] candidates)
    {
	  if (candidates.length == 0)
        {
                return new Candidate_ec22476();
        }
        else
        {
            int len = candidates.length;
            int numberOfTimes = 0;
            int candidateNumber = 0;
            Candidate candidate = candidates[candidateNumber];
            int[] largestCounts = new int[len];
            for (int i = 0; i < len; i++)
            {
                if (candidate == candidates[i])
                {
                    numberOfTimes+=1;
                }
                largestCounts[i] = numberOfTimes;
                numberOfTimes = 0;
                candidate = candidates[candidateNumber+1];
            }
            int largestCount = 0;
            int largestCountIndex = 0;
            for (int i = 0; i < len; i++)
            {
                if (largestCounts[i] > largestCount)
                {
                    largestCount = largestCounts[i];           
                    largestCountIndex = i;  
                }
            }
            Candidate winner = candidates[largestCountIndex]; 
            return winner;
        }
        
    }

    public String getSlogan()
    {
        return "123";
    }
}
