package OOP.ec22697.MP;// File Candidate_ec221174.java
//hello this is an edit
// Machine generated stub for Assignment 2

class Candidate_ec221174 extends Candidate {
    
    public String getName() {
        return "Hedi";
    }
    
    public String getSlogan() {
        return "Hedi is the next President";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0)
        {  
            return new Candidate_ec221174();
        }
 
         // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hedi is the next President"))
            {
                return c;
            }
        // Otherwise, search for myself again.
        for (Candidate c : candidates)
            if (c.getName().equals("Hedi"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
         // If array is empty, return instance of my class
        if (votes.length == 0) 
            return new Candidate_ec221174();
        
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
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
