package OOP.ec22697.MP;// File Candidate_ec22461.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22461 extends Candidate {
    
    public String getName() {
        return "Sulaiman";
    }
    
    public String getSlogan() {
        return "Free Uni For Everyone";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22461();
        
        if (candidates.length == 0)
        {
            return new Candidate_ec22894();
        }
        else
        {
            return r;
        }
            
        
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22461();
        
        if (votes.length != 0) r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }
    
}
