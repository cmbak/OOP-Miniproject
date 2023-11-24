package OOP.ec22697.MP;// File Candidate_ec22661.java
//
// Machine generated stub for Assignment 2


class Candidate_ec22661 extends Candidate
{

    public String getName() 
    {
        return "Luftur";
    }
    
    public String getSlogan() 
    {
        return "Aim to improve";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        Candidate r = new Candidate_ec22661();
        
        if (candidates.length == 0) 
        {
            return new Candidate_ec22661();
        }
        
        for (Candidate c: candidates) 
        {
            if (c.getSlogan().equals("Aim to improve"))
            {
                return c;
            }
        }
                
        for (Candidate c: candidates)
        {
            if (c.getName().equals("Luftur"))
            {
                return c;
            }
        }
                
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) 
    {  
        if (votes.length == 0)
        {
            return new Candidate_ec22661();
        }
        
        Candidate currentWinner = votes[0];
        
        int highest_count = 0;
        
        for (Candidate c: votes)
        {
            int score = 0;
            
            for (Candidate x:votes)
            {
                if (x == c)
                {
                    score++;
                }
            }
            
            if (score >= highest_count)
            {
                highest_count = score;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
