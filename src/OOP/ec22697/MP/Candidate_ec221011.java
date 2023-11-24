package OOP.ec22697.MP;

class Candidate_ec221011 extends Candidate {
    
    public String getName() {
        return "Bukayoo Saka";
    }
    
    public String getSlogan() {
        return "Small steps are way better than no steps";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec221011();
        
        //return a friend if array is empty
        if(candidates.length == 0)
            return new Candidate_ec22738();
        
        //searching for candidates with same slogan
        for(Candidate c : candidates)
            if(r.getSlogan().equals("Small steps are way better than no steps"))
                return r;
        
        //searching for myself on list of candidates
        for (Candidate c : candidates)
            if (r.getName().equals("Bukayo Saka"))
                return r;
        
        //otherwise, do last candidate
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec221011();
        
        //return a friend if array is empty
        if(votes.length == 0)
            return new Candidate_ec22738();
        
        //otherwise, default to first vote
        Candidate currentWinner = votes[0];
        return currentWinner;
    }
    
}
