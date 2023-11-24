package OOP.ec22697.MP;// File Candidate_ec21636.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
class Candidate_ec21636 extends Candidate {
    
    public String getName() {
        return "Drew";
    }
    
    public String getSlogan() {
        return "More me!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec21636();
        
        if (candidates.length == 0) {
            return r;
        } else {
            Random b = new Random();
            int c = b.nextInt(candidates.length);
            return candidates[c];
        }
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec21636();
        
        if (votes.length == 0) {
            return r;
        } else {
            Random b = new Random();
            int c = b.nextInt(votes.length);
            return votes[c];
        }
    }
    
}
