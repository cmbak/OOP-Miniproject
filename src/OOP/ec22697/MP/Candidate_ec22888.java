package OOP.ec22697.MP;// File Candidate_ec22888.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22888 extends Candidate {
    
    public String getName() {
        return "Adrian Tobi";
    }
    
    public String getSlogan() {
        return "YASSS Queen SLAYYY";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22929();
        
        if (candidates.length != 0) r = candidates[0];
 
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Naur"))
                return c;
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22929();
        
        if (votes.length != 0) return votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
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
