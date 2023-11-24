package OOP.ec22697.MP;// File Candidate_ec22599.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22599 extends Candidate {
    
    public String getName() {
        return "Lightning McQueen";
    }
    
    public String getSlogan() {
        return "Ka-Chow";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22599();
        
        if (candidates.length != 0){
         r = new Candidate_ec22599();
        }
        return r;
    }
 

    
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22599();
        
        if (votes.length != 0) 
        {
            r = votes[votes.length-1];
        }
        return r;
    
}
    
}
