package OOP.ec22697.MP;

import java.util.Random;

class Candidate_ec22635 extends Candidate
{
    public String getName() 
    {
        return "Morbius";
    }
    public String getSlogan() 
    {
        return "It's morbin time";
    }
        
    public Candidate vote(Candidate[] candidates) 
    {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22635();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("It's morbin time"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Morbius"))
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
            return new Candidate_ec22635();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) 
        {
            
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
    
    
}
    

