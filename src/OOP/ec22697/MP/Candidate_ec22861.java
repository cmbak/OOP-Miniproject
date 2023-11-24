package OOP.ec22697.MP;// If your code uses libraries, be sure to include the necessary
// import lines in your file Candidate_/un/.java
import java.util.Random;

class Candidate_ec22861 extends Candidate {
    
    public String getName() {
        return "Midul";
    }
    
    public String getSlogan() {
        return "Fast!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Arbor"))
                return c;
        
        // Otherwise, default to ramdom candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) {
        
        // If array is empty, return instance of this class.
        if (votesCast.length == 0) 
            return new Candidate_ec22861();
        
        // Choose winner at random from votes cast.
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];        
    }
}
