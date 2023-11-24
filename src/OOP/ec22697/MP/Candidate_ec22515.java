package OOP.ec22697.MP;// File Candidate_ec22515.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22515 extends Candidate {
    
    public String getName() {
        return "Aleksej";
    }
    
    public String getSlogan() {
        return "OOP da Best";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22515();
        
        if (candidates.length == 0) {
            r = new Candidate_ec22515();
        } else {
            for (int i = 0; i < candidates.length; i++) {
                if (candidates[i].getName().equals("Aleksej")) {
                    r = candidates[i];
                }
            }
        }
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22515();
        
        if (votes.length == 0) {
            r = new Candidate_ec22515();
        } else {
            int index = votes.length/2;
            r = votes[index];
        }
        return r;
    }
    
}
