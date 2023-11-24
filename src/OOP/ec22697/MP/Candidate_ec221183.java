package OOP.ec22697.MP;// File Candidate_ec221183.java
import java.util.Random;

class Candidate_ec221183 extends Candidate 
{
    public String getName()
    {
        return "Marx";
    }
    
    public String getSlogan()
    {
        return "Workers of the world, unite!"; 
    }
    
    public Candidate vote (Candidate[] candidate)
    {
        if (candidate.length == 0)
        {
            return new Candidate_ec221183();
        }
        Random r = new Random();
        int c = r.nextInt(candidate.length);
        return candidate[c]; 
    }
    
    public Candidate selectWinner (Candidate[] votes) 
    {   
        if (votes.length == 0)
        {
            return new Candidate_ec221183();
        }
        int[] voteNumbers = new int[votes.length];
        Candidate candidate = votes[0]; 
        int r = 0;
        for(int i = 0; i < votes.length; i++)
        {
            if(candidate == votes[i])
            {
                r = r + 1;
            }
            voteNumbers[i] = r;
            int largestCountSoFar = r;
            
            if(r > largestCountSoFar)
            {
                candidate = votes[i];
            }
        }
        return candidate;
    }
} 
