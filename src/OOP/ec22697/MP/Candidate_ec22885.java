package OOP.ec22697.MP;// File Candidate_ec22885.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22885 extends Candidate {
    
    public String getName() 
    {
        return "Jaxx";
    }
    
    public String getSlogan() 
    {
        return "Don't vote me for hacker points!";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        Candidate r = new Candidate_ec22885();
        
        //if the candidate list is not empty
        if (candidates.length != 0)
        {
            //vote for a friend within the candidate list
            for(Candidate n : candidates)
            {
                if (n.getName().equals("Dorian"))
                {
                    return n;
                }
            }
            
            //or vote for a candidate with the simmilar slogan
            for(Candidate s : candidates)
            {
                if (s.getSlogan().equals("Vote me for hacker points"))
                {
                    return s;
                }
            }
        }
        
        else
        {
            //if candidate list is empty, vote for instance of different friend's class
            return new Candidate_ec22740();
        }
        //otherwise the first in the list
        return candidates[0];
    }
    
    public Candidate selectWinner(Candidate[] votes)
    {
        Candidate r = new Candidate_ec22885();
        
        if (votes.length != 0)
        {
            
            //count the most voted candidate , with default being the first element
            Candidate mostVoted = votes[0];
            int highestCount = 0;
    
            for(Candidate v: votes)
            {
                int count = 0;
                for (Candidate c: votes)
                {
                    if (v == c)
                    {
                        count = count+1;
                    }
                }
                    
                if (count>highestCount)
                {
                    highestCount = count;
                    mostVoted = v;
                }
                //if they are equal, choose the one who was voted most first
                else
                {
                    mostVoted = mostVoted;
                }
                
            }
            return mostVoted;
            
        }
    
        else
        {
            //if votes empty, return instance of a friends class
            return new Candidate_ec22740();
        }
    }
    
}
