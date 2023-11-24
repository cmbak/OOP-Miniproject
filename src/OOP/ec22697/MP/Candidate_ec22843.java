package OOP.ec22697.MP;// File Candidate_ec22843.java
//
// Machine generated stub for Assignment 2

import java.util.Random;

class Candidate_ec22843 extends Candidate {
    
    public String getName() {
        return "Stine Koepp";
    }
    
    public String getSlogan() {
        return "Lets remove Winters!!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate candid = new Candidate_ec22843();
        
        if (candidates.length != 0) candid = candidates[0];
        
        if (candidates.length == 0) {
            return new Candidate_ec22843();
        }

        for (Candidate c : candidates) {
            if (c.getName().equals("Stine")) {
                return c;
            }
        }
        Random r = new Random();
        int someone = r.nextInt(candidates.length);
        return candidates[someone];
        
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate candid = new Candidate_ec22843();
        
        if (votes.length != 0) candid = votes[0];
        
        if (votes.length == 0) {
            return new Candidate_ec22843();
        }


        Candidate currentWinner = votes[0];

        int mostVotes = 0;
        for (Candidate ca : votes) {
            
            int counter = 0;
            for (Candidate cb : votes) {
                if (ca == cb) {
                    counter ++;
                }
            }
            if (counter >= mostVotes) {
                mostVotes = counter;
                currentWinner = ca;
            }
        }
        return currentWinner;
    }
    
}


