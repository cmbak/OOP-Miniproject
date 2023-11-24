package OOP.ec22697.MP;

import java.util.Random;
class Candidate_ec22596 extends Candidate {
    
    public String getName() {
        return "Omar";
    }
    
    public String getSlogan() {
        return "siiihhh";
    }
    
    public Candidate vote(Candidate[] candidates) {

        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("omar"))
                return c;

        // Otherwise, default to ramdom candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }

    public Candidate selectWinner(Candidate[] votesCast) {

        // If array is empty, return instance of this class.
        // Choose winner at random from votes cast.
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];
    }
}
