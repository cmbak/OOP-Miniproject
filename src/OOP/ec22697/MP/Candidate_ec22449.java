package OOP.ec22697.MP;// File Candidate_ec22449.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22449 extends Candidate {
    
    public String getName() {
        return "esther";
    }
    
    public String getSlogan() {
        return "bucks in four";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22449();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22449();
        
        if (votes.length != 0) r = votes[0];
 
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                r = c;
            }
        }
        
        
        return r;
    }
    
}


