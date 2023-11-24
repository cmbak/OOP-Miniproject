package OOP.ec22697.MP;// File Candidate_ec221008.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec221008 extends Candidate {

   public String getName() {
        return "Joey";
    }

    public String getSlogan() {
        return "Vote Joey!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec221008();

        // returns instance of this class
        if (candidates.length == 0)
        {
            return r;
        }

        // votes for Mr Bean
        for (Candidate c: candidates){
            if (c.getName().equals("Mr Bean") && c.getSlogan().equals("Teddy")){
                return c;
            }
        }

        // votes for last candidate in the list
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec221008();

        // returns instance of this class
        if (votes.length == 0)
        {
            return r;
        }

        // returns a random candidate
        else
        {
            Random ra = new Random();
            int c = ra.nextInt(votes.length);
            return votes[c];
        }
        

    }


	


    public static void main (String[] args)
    {
		Candidate[] ca = A3.getCandidateArray();
        int cNum = 0;
		Candidate[] candidates = new Candidate[ca.length];

		boolean flag = false;
        System.out.println("= Running Repeated Elections =");

		while(!flag)
		{
            System.out.println("Candidates are");
            if(cNum!=0)
            {
                for(int i=0;i<cNum;i++)
                {
                    System.out.println(candidates[i].getName());
                }
            }
            char menu = inputChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            
	    	if (menu=='a')
	    	{
				candidates = addCandidate(candidates, ca, cNum);
                cNum++;
	    	}
	    	else if (menu=='b')
			{
				candidates = randomCandidate(candidates, ca, cNum);
                cNum++;
	    	}
			else if (menu=='c')
			{
				election(candidates, ca, cNum);
                boolean flag2 = false;
                while(!flag2)
                {
                    char option = inputChar("Would you like to a) start another election b) quit");
                    if (option=='a')
                    {
                        flag2=true;
                    }
                    else if (option=='b')
                    {
                        flag=true;
                        flag2=true;
                    }
                    else
                    {
                        System.out.println("Invalid option. Please try again.");
                    }
                }
            }
            else
            {
                System.out.println("Invalid option. Please try again.");
            }
		
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

    public static Candidate[] addCandidate (Candidate[] candidates, Candidate[] ca, int cNum)
    {
		String name = inputString("Please enter a username.");

		Candidate addedC = A3.getByUsername(name, ca);

		if (addedC != null)
		{
	    	candidates[cNum] = addedC;
		}
		else
		{
	    	System.out.println("Could not find this candidate.");
		}

	    	return candidates;
	}

    public static Candidate[] randomCandidate (Candidate[] candidates, Candidate[] ca, int cNum)
    {
		Random ran = new Random();
		int random = ran.nextInt(ca.length);
		Candidate randomC = ca[random];

		candidates[cNum] = randomC;

		return candidates;
    }
	
	public static void election (Candidate[] candidates, Candidate[] allCandidates, int cNum)
	{
        Candidate[] fullCandidates = new Candidate[cNum];
        for(int i=0;i<cNum;i++)
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
			System.out.println("Most common winner is " + overallWinner[0].getName() + ".");
			System.out.println("There were no other winners.");
		}
		else
		{
			System.out.println("Most common winners:");
			for(int i=0;i<winnerCount;i++)
			{
				System.out.println(overallWinner[i].getName());
			}
		}
		
		return;
		
	}
		
}
