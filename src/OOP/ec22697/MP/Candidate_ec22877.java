package OOP.ec22697.MP;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

class Candidate_ec22877 extends Candidate {
    
    public String getName() {
        return "Thors";
    }
    
    public String getSlogan() {
        return "Vote for the dawg";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Thors"))
                return c;
        
        // Otherwise, default to ramdom candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) {
        
        // If array is empty, return instance of this class.
        if (votesCast.length == 0) 
            return new Candidate_ec22877();
        
        // Choose winner at random from votes cast.
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];        
    }
    public static String inputString(String message)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        answer = scanner.nextLine();
        return answer;

    }

    public static int inputInt(String message)
    {
        int answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        answer = scanner.nextInt();
        return answer;

    }
    
    public static String mostFrequent(ArrayList<String> winners )
    {
        String most = winners.get(0);
        int count = 0;
        
        for (int i=0; i<winners.size(); i++)
        {
            int cnt = 0;
                
            for (int j=i+1; j<winners.size(); j++)
            {
                if(winners.get(i).equals(winners.get(j)))
                {
                    cnt++;
                }
                if (count < cnt)
                {
                    most = winners.get(i);
                    count = cnt;    
                }
            }
        }
        return most;
    }
    
    
    
    public static void main(String [] args)
        
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        ArrayList<Candidate> allCandidatesextra = new ArrayList<>(Arrays.asList(A3.getCandidateArray()));
        ArrayList<Candidate> electionCandidates = new ArrayList<>();
        
        String ans = inputString("Please press 'a' to select the username for a specific candidate to enter the election");
        
        // Adding candidates to the election
        while (ans.equals("a"))
        {
            String newCandidate = inputString("Please enter the username for a specific candidate to enter the election");
            electionCandidates.add(A3.getByUsername(newCandidate, allCandidates));
            
            for (int i=0; i<electionCandidates.size(); i++)
            {
                System.out.println((i+1) + ". " + electionCandidates.get(i).getName());
            }
            
            ans = inputString("Would you like to a) Add a specific candidate b) Run the election?");
            
        }
        
        // Running the election
        
	Candidate[] electionCandidatesArray = electionCandidates.toArray(new Candidate[electionCandidates.size()]);
        Candidate[] votesCount = new Candidate[allCandidatesextra.size()];
        ArrayList<String> winners = new ArrayList<>();
        
        
        String voteCounterName = inputString("Who should count the votes?");
        Candidate voteCounter = A3.getByUsername(voteCounterName, allCandidates);
        
        int numOfElections = inputInt("How many times do you want to run the election?");
            
        for (int x=0; x<numOfElections; x++)
        {
            for (int y=0; y<allCandidatesextra.size(); y++)
            {
		try
		{
                    votesCount[y] = allCandidates[y].vote(electionCandidatesArray);
                }
		catch (Exception e)
		{
		    
		}
            }
            
            winners.add(voteCounter.selectWinner(votesCount).getName());
        }        
        
        //Most frequent winner
        
        String mostfreq = mostFrequent(winners);
            
        System.out.println("The most common winner is " + mostfreq);
        
        
            
            
            
            
        
     
            
            
    }
}
