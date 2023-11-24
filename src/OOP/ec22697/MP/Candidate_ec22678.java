package OOP.ec22697.MP;

import java.util.Random;

class Candidate_ec22678 extends Candidate {
    
    public String getName() {
        return "Andrew";
    }
    
    public String getSlogan() {
        return "Top g";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0)
            return new Candidate_ec22678();
        
        for (Candidate c : candidates)
            if (c.getName().equals("Andrew"))
                return c;
        
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Top g"))
                return c;
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
        
    }
    
    public Candidate selectWinner(Candidate[] votesCast) {
        
       if (votesCast.length == 0) 
            return new Candidate_ec22678();
        
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];  
        
    }
    
}
