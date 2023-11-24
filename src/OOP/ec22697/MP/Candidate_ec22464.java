package OOP.ec22697.MP;// File Candidate_ec22464.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22464 extends Candidate {
    
    public String getName() {
        String myName = "Lawrence";//8 characters
        return myName;
    }
    
    public String getSlogan() {
        String mySlogan = "I can't think of a slogan";//25 characters
        return mySlogan;
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22464();
        
        if (candidates.length != 0){
            
            r = candidates[0];
            
        }
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        for(Candidate x: candidates){
            if(x.getName().equals("I can't think of a slogan")){
                return x;
            }
        }
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22464();
        
        if (votes.length != 0) r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        return r;
        
    }
    
}
//edited in lab 4
