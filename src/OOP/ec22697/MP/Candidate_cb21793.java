package OOP.ec22697.MP;// File Candidate_cb21793.java
//
// Machine generated stub for Assignment 2

import java.util.Random;

class Candidate_cb21793 extends Candidate 
{
    
    public String getName() 
    {
        return "Jeremy Clarkson";
    }
    
    public String getSlogan() 
    {
        return "Power!";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        
        
        for (Candidate c : candidates)
            if (c.getName().equals("Jeremy Clarkson"))
                return c;
        
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) 
    {
        
        
        if (votesCast.length == 0) 
            return new Candidate_cb21793();
        
        
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];        
    }
}
