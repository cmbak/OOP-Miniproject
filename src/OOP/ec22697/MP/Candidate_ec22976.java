package OOP.ec22697.MP;

import java.util.Random;

class Candidate_ec22976 extends Candidate {
    
    public String getName() {
        return "Sasuke Uchiha";
    }
    
    public String getSlogan() {
        return "For Konoha!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        for (Candidate c : candidates)
            if (c.getName().equals("Naruto"))
                return c;
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) {

        if (votesCast.length == 0) 
            return new Candidate_ec22976();

        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];        
    }
}
