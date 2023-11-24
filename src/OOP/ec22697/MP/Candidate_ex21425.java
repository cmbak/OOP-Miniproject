package OOP.ec22697.MP;

class Candidate_ex21425 extends Candidate {
    
    public String getName() 
    {
        return "Batman";
    }
    
    public String getSlogan() 
    {
        return "I am Justice!!!!";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        
        if (candidates.length == 0) 
            return new Candidate_ex21425();
        
        for (Candidate c : candidates)
            if (c.getSlogan().equals("I am Justice!!!!"))
                return c;
        
        for (Candidate c : candidates)
            if (c.getName().equals("Ryan"))
                return c;
        
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) 
    {

        if (votes.length == 0) 
            return new Candidate_ex21425();
        
        Candidate currentWinner = votes[0];
        int highestCount = 0;
        for (Candidate c : votes) 
        {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) 
            {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
