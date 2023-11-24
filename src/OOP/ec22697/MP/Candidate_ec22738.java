package OOP.ec22697.MP;

class Candidate_ec22738 extends Candidate {
    
    public String getName() {
        return "Gabriel Jesus";
    }
    
    public String getSlogan() {
        return "Cleaner air!";
    }
    
    public Candidate vote(Candidate[] candidates) {

        //vote for friend if array is empty.
        if (candidates.length == 0) 
            return new Candidate_ec22603();
        
        //vote for candidate who wants more trees planted.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees"))
                return c;
        
        //Otherwise, vote for friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Bukayo Saka"))
                return c;
        
        //Otherwise, vote for middle candidate.
        return candidates[candidates.length/2];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        //if array is empty, choose friend as winner
        if (votes.length == 0) 
            return new Candidate_ec22603();
        
        //select last candidate that has been voted for as winner
        
        return votes[votes.length-1];
    }
    
}

