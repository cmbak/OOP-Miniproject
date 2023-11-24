package OOP.ec22697.MP;// File Candidate_ex21249.java
//
// Machine generated stub for Assignment 2

class Candidate_ex21249 extends Candidate {
    
    public String getName() {
        return "No name (ex21249)";
    }
    
    public String getSlogan() {
        return "No slogan (ex21249)";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate me = new Candidate_ex21249();
        
        if (candidates.length != 0) me = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return me;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate me = new Candidate_ex21249();
        
        if (votes.length != 0) me = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return me;
    }
    
}
