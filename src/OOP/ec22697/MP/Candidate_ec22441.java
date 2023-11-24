package OOP.ec22697.MP;// File Candidate_ec22441.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22441 extends Candidate {
    
    public String getName() {
        return "Rochelle";
    }
    
    public String getSlogan() {
        return "There's no place like home";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22441();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22579();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("There's no place like home"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Obi-wan Kenobi"))
                return c;
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
 
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22579();
        
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
