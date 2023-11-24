package OOP.ec22697.MP;// File Candidate_ec22998.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22998 extends Candidate {
    
    public String getName() {
        return "No name (ec22998)";
    }
    
    public String getSlogan() {
        return "No slogan (ec22998)";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22998();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22998();
        
        if (votes.length != 0) r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }
    
}
