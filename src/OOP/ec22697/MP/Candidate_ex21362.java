package OOP.ec22697.MP;// File Candidate_ex21362.java
//
// Machine generated stub for Assignment 2

class Candidate_ex21362 extends Candidate {
    
    public String getName() 
    {
        return "Lala";
    }
    
    public String getSlogan()
    {
        return "You dont know me??";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        
        if (candidates.length == 0) 
        {
            return new Candidate_ex21362(); 
        }
        
        for (Candidate c : candidates) 
        {
            if (c.getSlogan().equals("You don’t know me??")) 
            {
                return c; 
            }
        }
        
        for (Candidate c : candidates) {
            if (c.getName().equals("Batman")) 
            {
                return c; }
        }
        
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) 
    {

        if (votes.length == 0) {
            return new Candidate_ex21362(); }
        
        Candidate currentWinner = votes[0];
        int highestCount = 0;
        for (Candidate c : votes) 
        {
            
            int count = 0;
            for (Candidate x : votes) 
            {
                if (x == c) 
                {
                    count++; 
                } 
            }
            if (count > highestCount) 
            {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
