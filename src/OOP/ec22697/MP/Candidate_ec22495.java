package OOP.ec22697.MP;

// File Candidate_ec22495.java
//
// Machine generated stub for Assignment 2
class Candidate_ec22495 extends Candidate {
    
    public String getName() {
        return "fingolfin";
    }
    
    public String getSlogan() {
        return "If I win this I'll buy a 24 pack of krispy kreme";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0)
            return new Candidate_ec22495();
        for (Candidate c : candidates)
            if (c.getSlogan().equals("If I win this I'll buy a 24 pack of krispy kreme"))
                return c;
        for (Candidate c : candidates)
            if (c.getName().equals("fingolfin"))
                return c;
        
        return candidates[0];
    }
               
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0)
            return new Candidate_ec22495();
        Candidate currentWinner = votes[0];
        int highestCount = 0;
        for (Candidate c : votes){
            int count = 0;
            for (Candidate v : votes)
                if (v == c)
                    count++;
            if (count > highestCount){
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner;
    }
} 
