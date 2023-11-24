package OOP.ec22697.MP;

import java.util.Random;

class Candidate_ec22954 extends Candidate {
    
    public String getName() {
        return "Andres Rengifo";
    }
    
    public String getSlogan() {
        return "More Work!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22954();
        
        // First search for Utfur on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Utfur"))
                return c;
        
        // Otherwise, choose at random from list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22954();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
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
