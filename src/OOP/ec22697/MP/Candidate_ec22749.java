package OOP.ec22697.MP;// File Candidate_ec22749.java
//
// Machine generated stub for Assignment 2

import java.util.Random;

class Candidate_ec22749 extends Candidate {
    
    public String getName() {
        return "Alysa";
    }
    
    public String getSlogan() {
        return "Salam from Malaysia";
    }
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, vote for self
        if (candidates.length == 0) 
            return new Candidate_ec22749();
        
        //vote for the king
        for (Candidate c : candidates)
            if (c.getName().equals("Davis"))
                return c;
        
        // otherwise randomly select winner
       Random r = new Random();
        int i = r.nextInt(candidates.length);
        return candidates[i];
        

    }
    
    public Candidate selectWinner(Candidate[] candidates) {
        
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22749();
        
        //select self. 
        for (Candidate iwin : candidates)
            if (iwin.getName().equals("Alysa"))
                return iwin;
        //in the rare instance I wasn't voted select the modal vote
        Candidate currentWinner = candidates[0];
        int highestCount = 0;
        for (Candidate c : candidates) {
            
            int count = 0;
            for (Candidate x : candidates)
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
