package OOP.ec22697.MP;

// File Candidate_ec22679.java
//
// Machine generated stub for Assignment 2
// Hello Staff
// Hello World
class Candidate_ec22679 extends Candidate {
    
    public String getName() {
        return "Luka Danilenko";
    }
    
    public String getSlogan() {
        return "Welcome Stranger!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22679();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22679();
        
        if (votes.length != 0) r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }
    
}
