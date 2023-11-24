package OOP.ec22697.MP;// File Candidate_ec22597.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22597 extends Candidate {
    
    public String getName() {
        return "Theo Georgiou";
    }
    
    public String getSlogan() {
        return "Vote for Theo!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0)
        {  
            return new Candidate_ec22597();
        }
 
        // Search for a similar candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Dislike OOP")) {
                return c;
            }

        // Otherwise, search for myself again.
        for (Candidate c : candidates) {
            if (c.getName().equals("Theo Georgiou")) {
                return c;
            }
        }
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of my class
        if (votes.length == 0) {
            return new Candidate_ec22597();
        }     

        // Default to first vote, although this will be over-written.
        Candidate currentWinner = votes[0];

        // Count the votes for each object in the array and selects one with the most.
        int highestCount = 0;

        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes)
                if (x == c) {
                    count++;
                }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
         }
        
        return currentWinner;
    }
}
